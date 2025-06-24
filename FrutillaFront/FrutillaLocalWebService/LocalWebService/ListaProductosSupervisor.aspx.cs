using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalWebService.EmpleadoWS;
using LocalWebService.InventarioWS;
using LocalWebService.PedidoWS;
using LocalWebService.ProductoImagenWS;

namespace LocalWebService
{
    public partial class ListaProductosSupervisor : System.Web.UI.Page
    {
        private InventarioWSClient inventarioWSClient;
        private EmpleadoWSClient empleadoWSClient;
        private ProductoImagenWSClient productoImagenWSClient;
        private int idLocal;
        private char tipoSeleccionado = 'T'; // Por defecto, todos los tipos seleccionados
        public ListaProductosSupervisor()
        {
            inventarioWSClient = new InventarioWSClient();
            empleadoWSClient = new EmpleadoWSClient();
            productoImagenWSClient = new ProductoImagenWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
            if (User.Identity.IsAuthenticated)
            {
                string datos = FormsAuthentication.Decrypt(
                    Request.Cookies[FormsAuthentication.FormsCookieName].Value
                ).UserData;

                string[] partes = datos.Split('|');
                string tipoUsuario = partes[0];
                int idUsuario = int.Parse(partes[1]);
                empleado empleado = empleadoWSClient.obtenerEmpleadoPorId(idUsuario);
                if (empleado != null)
                {
                    idLocal = empleado.idLocal;
                }
                else
                {
                    rptProductos.ItemCommand += rptProductos_ItemCommand;
                    Response.Redirect("Login.aspx");
                }

                // Aca pueden hacer uso del obtener por id
            }
            else
            {
                Response.Redirect("Login.aspx");
            }

            if (!IsPostBack)
            {
                var valores = Enum.GetValues(typeof(FrutasBebida));

                // Limpiar por si acaso
                ChkFrutas.Items.Clear();

                foreach (var valor in valores)
                {
                    string texto = valor.ToString(); // Nombre del enum (ej: "Rojo")
                    int valorInt = (int)valor;       // Valor numérico (ej: 1)

                    ChkFrutas.Items.Add(new ListItem(texto, valorInt.ToString()));
                }
                PaginaActual = 0;
                CargarProductos();
            }
        }


        private int PaginaActual
        {
            get { return ViewState["PaginaActual"] != null ? (int)ViewState["PaginaActual"] : 0; }
            set { ViewState["PaginaActual"] = value; }
        }

        protected void btnAnterior_Click(object sender, EventArgs e)
        {
            if (PaginaActual >= 0)
            {
                PaginaActual--;
                CargarProductos();
            }
        }

        protected void btnSiguiente_Click(object sender, EventArgs e)
        {
            PaginaActual++;
            CargarProductos();
        }

        /*private void CargarProductos()
        {
            try
            {
                //cambiar a session idempleados y con la verificacion de supervisor
                gvProductos.DataSource = inventarioWSClient.listarTodos(idLocal).ToList();
                gvProductos.DataBind();
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar productos" + ex;
            }

        }*/


        private void SeleccionarTipo(char tipo)
        {
            // Marcar visualmente el botón seleccionado
            btnTodos.CssClass = tipo == 'T' ? "btn btn-success" : "btn btn-outline-secondary";
            btnFruta.CssClass = tipo == 'F' ? "btn btn-success" : "btn btn-outline-secondary";
            btnSnack.CssClass = tipo == 'S' ? "btn btn-success" : "btn btn-outline-secondary";
            btnBebidas.CssClass = tipo == 'B' ? "btn btn-success" : "btn btn-outline-secondary";
            btnOtros.CssClass = tipo == 'P' ? "btn btn-success" : "btn btn-outline-secondary";
        }

        protected void FiltroProductos_Click(object sender, EventArgs e)
        {
            var btn = (Button)sender;
            char tipo = btn.CommandArgument[0];
            tipoSeleccionado = tipo; // Actualizar el tipo seleccionado
            SeleccionarTipo(tipo);
            CargarProductos();
        }

        private void CargarProductos()
        {
            try
            {
                var productos = inventarioWSClient.filtrarPorTipo(idLocal, tipoSeleccionado);
                var productosSinDuplicados = productos.GroupBy(p => p.idProducto).Select(g => g.First()).ToList();
                PagedDataSource pagedData = new PagedDataSource();
                pagedData.DataSource = productosSinDuplicados;
                pagedData.AllowPaging = true;
                pagedData.PageSize = 8;
                pagedData.CurrentPageIndex = PaginaActual;
                btnAnterior.Enabled = !pagedData.IsFirstPage;
                btnSiguiente.Enabled = !pagedData.IsLastPage;

                lblPagina.Text = $"Página {PaginaActual + 1} de {pagedData.PageCount}";

                rptProductos.DataSource = pagedData;
                rptProductos.DataBind();
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar productos: " + ex.Message;
            }
        }

        protected void rptProductos_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "VerMas")
            {
                int idProducto = int.Parse(e.CommandArgument.ToString());
                char tipoProducto = (char)inventarioWSClient.obtenerTipoProducto(idProducto, idLocal);

                HiddenTipoProductoEdit.Value = tipoProducto.ToString();
                HiddenIdProductoEdit.Value = e.CommandArgument.ToString();
                // Obtener y asignar datos según el tipo
                switch (tipoProducto)
                {
                    case 'F':
                        var fruta = (fruta)inventarioWSClient.obtenerFrutaPorId(idProducto);
                        if (fruta != null)
                        {
                            HiddenTipoEstadoProductoEdit.Value = fruta.tipoEstado.ToString();
                            TxtEditNombre.Text = fruta.nombre;
                            TxtEditPrecio.Text = fruta.precioUnitario.ToString();
                            TxtEditDescripcion.Text = fruta.descripcion;
                            TxtEditStock.Text = inventarioWSClient.obtenerStockPorId(idProducto, idLocal).ToString();
                            TxtEditStockMin.Text = fruta.stockMinimo.ToString();
                            TxtEditCodigo.Text = fruta.codigoProd;
                            TxtTipoEnvaseEdit.Text = fruta.envase;
                            ChkReqEnvaseEdit.Checked = fruta.requiereEnvase;
                            ChkEnvasadoEdit.Checked = fruta.estaEnvasado;
                            ChkReqLimpiezaEdit.Checked = fruta.requiereLimpieza;
                            // Asigna los campos específicos de fruta si los tienes en el modal
                        }

                        break;
                    case 'B':
                        var bebida = (bebida)inventarioWSClient.obtenerBebidaPorId(idProducto);
                        if (bebida != null)
                        {
                            HiddenTipoEstadoProductoEdit.Value = bebida.tipoEstado.ToString();
                            TxtEditNombre.Text = bebida.nombre;
                            TxtEditPrecio.Text = bebida.precioUnitario.ToString();
                            TxtEditDescripcion.Text = bebida.descripcion;
                            TxtEditStock.Text = inventarioWSClient.obtenerStockPorId(idProducto, idLocal).ToString();
                            TxtEditStockMin.Text = bebida.stockMinimo.ToString();
                            TxtEditCodigo.Text = bebida.codigoProd;
                            TxtTamanioEdit.Text = bebida.tamanioOz.ToString();
                            TxtTipoBebidaEdit.Text = bebida.tipo;
                            TxtTipoEndulzanteEdit.Text = bebida.endulzante;
                            DDTipoLecheEdit.SelectedValue = bebida.tieneLeche.ToString();


                            // Asigna los campos específicos de bebida si los tienes en el modal
                        }

                        break;
                    case 'S':
                        var snack = (snack)inventarioWSClient.obtenerSnackPorId(idProducto);
                        if (snack != null)
                        {
                            HiddenTipoEstadoProductoEdit.Value = snack.tipoEstado.ToString();
                            TxtEditNombre.Text = snack.nombre;
                            TxtEditPrecio.Text = snack.precioUnitario.ToString();
                            TxtEditDescripcion.Text = snack.descripcion;
                            TxtEditStock.Text = inventarioWSClient.obtenerStockPorId(idProducto, idLocal).ToString();
                            TxtEditStockMin.Text = snack.stockMinimo.ToString();
                            TxtEditCodigo.Text = snack.codigoProd;
                            TxtTipoSnackEdit.Text = snack.tipo;
                            TxtEnvaseSnackEdit.Text = snack.envase;
                            // Asigna los campos específicos de snack si los tienes en el modal
                        }
                        break;
                }

                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "ShowEditModal",
                    $@"mostrarOpcionesEditar('{tipoProducto}');
                       var myModal = new bootstrap.Modal(document.getElementById('modalEditarProducto')); myModal.show();",
                    true
                );
                // Mostrar el modal
                ScriptManager.RegisterStartupScript(
                    this,
                    this.GetType(),
                    "ShowEditModal",
                    "var myModal = new bootstrap.Modal(document.getElementById('modalEditarProducto')); myModal.show();",
                    true
                );
                CargarProductos();

            }
            else if (e.CommandName == "Eliminar")
            {
                int idProducto = int.Parse(e.CommandArgument.ToString());
                inventarioWSClient.eliminarProducto(idProducto, idLocal);
                CargarProductos();
            }
        }

        protected void btnAgregarProducto_Click(object sender, EventArgs e)
        {
            // Aquí podrías limpiar los campos si es necesario:
            txtNombre.Text = "";
            txtPrecio.Text = "";
            txtStock.Text = "";
            txtCodigo.Text = "";
            txtDescripcion.Text = "";
            txtStockMinimo.Text = "";

            // Mostrar el modal usando JavaScript
            ScriptManager.RegisterStartupScript(
            this,
            this.GetType(),
            "ShowModal",
            "var myModal = new bootstrap.Modal(document.getElementById('modalAgregarProducto')); myModal.show();",
            true
            );


        }

        protected void btnGuardarEdicionProducto_Click(object sender, EventArgs e)
        {
            string tipo = HiddenTipoProductoEdit.Value;
            int id = int.Parse(HiddenIdProductoEdit.Value);
            if (tipo == "F")
            {
                fruta fruta = new fruta();
                // Convert the string value to the enum type 'tipoEstado'
                if (Enum.TryParse(HiddenTipoEstadoProductoEdit.Value, out InventarioWS.tipoEstado estado))
                {
                    fruta.tipoEstado = estado;
                    fruta.tipoEstadoSpecified = true;
                }
                else
                {
                    lblError.Text = "El estado del producto no es válido.";
                    return;
                }
                fruta.idProducto = id;
                fruta.nombre = TxtEditNombre.Text;
                fruta.precioUnitario = Double.Parse(TxtEditPrecio.Text);
                fruta.descripcion = TxtEditDescripcion.Text;
                fruta.stock = Int32.Parse(TxtEditStock.Text);
                fruta.stockMinimo = Int32.Parse(TxtEditStockMin.Text);
                fruta.codigoProd = TxtEditCodigo.Text;
                fruta.envase = TxtTipoEnvaseEdit.Text;
                fruta.estaLimpio = ChkFrutaLimpieza.Checked;
                fruta.estaLimpioSpecified = true;
                fruta.estaEnvasado = ChkEnvasadoEdit.Checked;
                fruta.estaEnvasadoSpecified = true;
                fruta.requiereEnvase = ChkReqEnvaseEdit.Checked;
                fruta.requiereEnvaseSpecified = true;
                fruta.requiereLimpieza = ChkReqLimpiezaEdit.Checked;
                fruta.requiereLimpiezaSpecified = true;
                inventarioWSClient.actualizarProducto(fruta, idLocal);
            }
            else if (tipo == "B")
            {
                bebida bebida = new bebida();
                if (Enum.TryParse(HiddenTipoEstadoProductoEdit.Value, out InventarioWS.tipoEstado estado))
                {
                    bebida.tipoEstado = estado;
                    bebida.tipoEstadoSpecified = true;
                }
                else
                {
                    lblError.Text = "El estado del producto no es válido.";
                    return;
                }
                bebida.idProducto = id;
                bebida.nombre = TxtEditNombre.Text;
                bebida.precioUnitario = Double.Parse(TxtEditPrecio.Text);
                bebida.descripcion = TxtEditDescripcion.Text;
                bebida.stock = Int32.Parse(TxtEditStock.Text);
                bebida.codigoProd = TxtEditCodigo.Text;
                bebida.stockMinimo = Int32.Parse(TxtEditStockMin.Text);
                bebida.tamanioOz = Int32.Parse(TxtTamanioEdit.Text);
                bebida.tipo = TxtTipoBebidaEdit.Text;
                bebida.endulzante = TxtTipoEndulzanteEdit.Text;
                bebida.tieneLeche = (tipoLeche)Int32.Parse(DDTipoLecheEdit.SelectedValue);
                bebida.tieneLecheSpecified = true;

                // Conversión de string a tipoLeche  
                if (Enum.TryParse(DDTipoLecheEdit.SelectedValue, out tipoLeche tipoLecheSeleccionado))
                {
                    bebida.tieneLeche = tipoLecheSeleccionado;
                }
                else
                {
                    lblError.Text = "El tipo de leche seleccionado no es válido.";
                    return;
                }

                inventarioWSClient.actualizarProducto(bebida, idLocal);
            }
            else if (tipo == "S")
            {
                snack snack = new snack();
                if (Enum.TryParse(HiddenTipoEstadoProductoEdit.Value, out InventarioWS.tipoEstado estado))
                {
                    snack.tipoEstado = estado;
                    snack.tipoEstadoSpecified = true;
                }
                else
                {
                    lblError.Text = "El estado del producto no es válido.";
                    return;
                }
                snack.idProducto = id;
                snack.nombre = TxtEditNombre.Text;
                snack.precioUnitario = Double.Parse(TxtEditPrecio.Text);
                snack.descripcion = TxtEditDescripcion.Text;
                snack.stock = Int32.Parse(TxtEditStock.Text);
                snack.codigoProd = TxtEditCodigo.Text;
                snack.stockMinimo = Int32.Parse(TxtEditStockMin.Text);
                snack.tipo = TxtTipoSnackEdit.Text;
                snack.envase = TxtEnvaseSnackEdit.Text;

                inventarioWSClient.actualizarProducto(snack, idLocal);
            }
        }

        protected void btnGuardarProducto_Click(object sender, EventArgs e)
        {
            // Validar si se seleccionó algo
            if (!string.IsNullOrEmpty(TipoProducto.SelectedValue))
            {
                // Convertir el valor string a char
                char tipo = TipoProducto.SelectedValue[0]; // ← Aquí está el char

                // Ejemplo de uso
                switch (tipo)
                {
                    case 'F':
                        // Lógica para Fruta
                        fruta fruta = new fruta();
                        fruta.nombre = txtNombre.Text;
                        fruta.precioUnitario = Double.Parse(txtPrecio.Text);
                        fruta.descripcion = txtDescripcion.Text;
                        fruta.stock = Int32.Parse(txtStock.Text);
                        fruta.stockMinimo = Int32.Parse(txtStockMinimo.Text);
                        fruta.codigoProd = txtCodigo.Text;
                        fruta.envase = TxtTipoEnvase.Text;
                        fruta.estaLimpio = ChkFrutaLimpieza.Checked;
                        fruta.estaLimpioSpecified = true;
                        fruta.estaEnvasado = ChkFrutaEstaEnvasado.Checked;
                        fruta.estaEnvasadoSpecified = true;
                        fruta.requiereEnvase = ChkFrutaRequiereEnvase.Checked;
                        fruta.requiereEnvaseSpecified = true;
                        fruta.requiereLimpieza = ChkFrutaLimpieza.Checked;
                        fruta.requiereLimpiezaSpecified = true;
                        inventarioWSClient.insertarFruta(fruta, idLocal);
                        break;
                    case 'B':
                        bebida bebida = new bebida();
                        bebida.nombre = txtNombre.Text;
                        bebida.precioUnitario = Double.Parse(txtPrecio.Text);
                        bebida.descripcion = txtDescripcion.Text;
                        bebida.stock = Int32.Parse(txtStock.Text);
                        bebida.codigoProd = txtCodigo.Text;
                        bebida.stockMinimo = Int32.Parse(txtStockMinimo.Text);
                        bebida.tamanioOz = Int32.Parse(TxtTamanioOz.Text);
                        bebida.tipo = TxtTipoBebida.Text;
                        bebida.endulzante = TxtBebidaEndulzante.Text;
                        bebida.tieneLeche = (tipoLeche)Int32.Parse(DropDownList1.SelectedValue);
                        bebida.tieneLecheSpecified = true;


                        // Conversión de string a tipoLeche
                        if (Enum.TryParse(DropDownList1.SelectedValue, out tipoLeche tipoLecheSeleccionado))
                        {
                            bebida.tieneLeche = tipoLecheSeleccionado;
                        }
                        else
                        {
                            // Manejar el caso en que el valor no sea válido
                            lblError.Text = "El tipo de leche seleccionado no es válido.";
                            return;
                        }


                        inventarioWSClient.insertarBebida(bebida, idLocal);
                        break;
                    case 'S':
                        snack snack = new snack();
                        snack.nombre = txtNombre.Text;
                        snack.precioUnitario = Double.Parse(txtPrecio.Text);
                        snack.descripcion = txtDescripcion.Text;
                        snack.stock = Int32.Parse(txtStock.Text);
                        snack.codigoProd = txtCodigo.Text;
                        snack.stockMinimo = Int32.Parse(txtStockMinimo.Text);
                        snack.tipo = TxtTipoSnack.Text;
                        snack.envase = TxtSnackEnvase.Text;

                        inventarioWSClient.insertarSnack(snack, idLocal);
                        break;
                }
                CargarProductos();
            }
            else
            {
                Response.Write("Por favor selecciona una opción.");
            }
        }

        //esta función obtiene la imagen del producto según su tipo
        public string ObtenerImagenPorTipo(object idProd)
        {
            int idProducto = Convert.ToInt32(idProd);
            ushort tipo = inventarioWSClient.obtenerTipoProducto(idProducto, idLocal);
            return productoImagenWSClient.obtenerUrlPorTipo(tipo);
        }

        protected void btnBebidas_Click(object sender, EventArgs e)
        {

        }
    }
}