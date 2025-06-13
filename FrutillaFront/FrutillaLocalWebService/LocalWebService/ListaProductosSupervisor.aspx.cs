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

namespace LocalWebService
{
    public partial class ListaProductosSupervisor : System.Web.UI.Page
    {
        private InventarioWSClient inventarioWSClient;
        private EmpleadoWSClient empleadoWSClient;
        private int idLocal;
        public ListaProductosSupervisor()
        {
            inventarioWSClient = new InventarioWSClient();
            empleadoWSClient = new EmpleadoWSClient();
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
                CargarProductos();
            }
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
        private void CargarProductos()
        {
            try
            {
                var productos = inventarioWSClient.listarTodos(idLocal);
                rptProductos.DataSource = productos;
                rptProductos.DataBind();
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar productos: " + ex.Message;
            }
        }

        /*
        protected void btnAgregarProducto_Click(object sender, EventArgs e)
        {

        }*/

        protected void rptProductos_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "VerMas")
            {
                int idProducto = int.Parse(e.CommandArgument.ToString());

                // Aquí puedes hacer lo que necesites con el idProducto,
                // por ejemplo, redirigir a una página con detalles:
                Response.Redirect($"DetalleProducto.aspx?id={idProducto}");

                // O abrir un modal, cargar info, etc.
            }
        }

        protected void btnAgregarProducto_Click(object sender, EventArgs e)
        {
            // Aquí podrías limpiar los campos si es necesario:
            txtNombre.Text = "";
            txtPrecio.Text = "";
            txtStock.Text = "";

            // Mostrar el modal usando JavaScript
            ScriptManager.RegisterStartupScript(
            this,
            this.GetType(),
            "ShowModal",
            "var myModal = new bootstrap.Modal(document.getElementById('modalAgregarProducto')); myModal.show();",
            true
            );


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
                        fruta.estaEnvasado = ChkFrutaEstaEnvasado.Checked;
                        fruta.requiereEnvase = ChkFrutaRequiereEnvase.Checked;
                        fruta.requiereLimpieza = ChkFrutaLimpieza.Checked;
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
                        break;
                }
                CargarProductos();
            }
            else
            {
                producto producto = new producto();
                Response.Write("Por favor selecciona una opción.");
            }
        }



    }
}