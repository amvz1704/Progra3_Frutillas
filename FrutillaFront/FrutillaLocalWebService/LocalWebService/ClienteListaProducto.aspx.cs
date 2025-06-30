using LocalWebService.InventarioWS;
using LocalWebService.LocalWS;
using LocalWebService.PedidoWS;
using LocalWebService.ProductoImagenWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{

    public partial class ClienteListaProducto : System.Web.UI.Page
    {

        private InventarioWSClient inventarioWSClient;
        private ProductoImagenWSClient ProductoImagenWS;
        private LocalWSClient localWSClient;
        private PedidoWSClient pedidoWS;
        const int idPedidoOrden = 1;
        char tipoSeleccionado = 'T'; // Por defecto, todos los tipos seleccionados

        // Propiedad helper para el carrito en Session
        private List<PedidoWS.lineaOrdenDeVenta> Carrito
        {
            get
            {
                if (Session["Carrito"] == null)
                    Session["Carrito"] = new List<PedidoWS.lineaOrdenDeVenta>();
                return (List<PedidoWS.lineaOrdenDeVenta>)Session["Carrito"];
            }   
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            pedidoWS = new PedidoWSClient();
            inventarioWSClient = new InventarioWSClient();
            ProductoImagenWS = new ProductoImagenWSClient();
            if (!IsPostBack)
            {
                CargarLocales();
                CargarProductos();
            }
        }

        private void CargarLocales()
        {
            localWSClient = new LocalWSClient();
            ddlLocal.Items.Clear();
            foreach (var local in localWSClient.listarLocales()) // Cambiar por locales activos
            {
                ddlLocal.Items.Add(new ListItem(local.nombre, local.idLocal.ToString()));
            }


        }

        protected void ddlLocal_SelectedIndexChanged(object sender, EventArgs e)
        {
            int idLocal;

            // Guardas en Session
            

            if (int.TryParse(ddlLocal.SelectedValue, out idLocal))
            {
                // Llama a tu método para cargar productos filtrados por el local seleccionado
                Session["idLocal"] = idLocal;
                CargarProductos();

            }
        }


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
                var productos = inventarioWSClient.filtrarPorTipo(int.Parse(ddlLocal.SelectedValue), tipoSeleccionado); //cambiar por el local seleccionado 
                
                
                
                if (productos != null)
                {
                    var productosSinDuplicados = productos.GroupBy(p => p.idProducto).Select(g => g.First()).ToList();
                    rptProductos.DataSource = productosSinDuplicados;
                    rptProductos.DataBind();
                    lblError.Text = ""; // Limpiar mensaje de error si todo va bien
                }
                else
                {
                    // Si no hay productos, asegúrate de limpiar el repeater
                    rptProductos.DataSource = null;
                    rptProductos.DataBind();
                    lblError.Text = "Local no cuenta con productos disponibles";
                }
            }
            catch (Exception ex)
            {
                rptProductos.DataSource = null;
                rptProductos.DataBind();
                lblError.Text = "Debe seleccionar un local "+ex.Message;
            }
        }

        protected void rptProductos_ItemCommand(object source, RepeaterCommandEventArgs e)
        {
            if (e.CommandName == "VerMas")
            {
                int idProducto = int.Parse(e.CommandArgument.ToString());

                // Aquí puedes hacer lo que necesites con el idProducto,
                // por ejemplo, redirigir a una página con detalles:
                Response.Redirect($"DetalleProductoCliente.aspx?id={idProducto}&idLocal={ddlLocal.SelectedValue}");

                // O abrir un modal, cargar info, etc.
            }
            if (e.CommandName == "agregarCarrito")
            {

                //se crea un nuevo Pedido, que se podra editar al ver carrito 
                int idProd = Convert.ToInt32(e.CommandArgument);



                // Si ya existe la línea, puedes aumentar cantidad 
                PedidoWS.lineaOrdenDeVenta existente = Carrito.Find(x => x.producto.idProducto == idProd);
                if (existente != null)
                {
                    existente.cantidad++;
                    existente.subtotal = existente.cantidad * existente.producto.precioUnitario;
                }
                else
                {
                    //creas una nueva orden de linea ordenDeVenta para agregar a linea ordenDeVenta
                    PedidoWS.producto copia = pedidoWS.obtenerProductoPorId(idProd);

                    Carrito.Add(new PedidoWS.lineaOrdenDeVenta
                    {
                        producto = copia,
                        cantidad = 1,
                        idOrdenVenta = idPedidoOrden

                    });
                }

            }
        }


        //esta función obtiene la imagen del producto según su tipo
        public string ObtenerImagenPorTipo(object idProd)
        {
            int idProducto = Convert.ToInt32(idProd);
            string v = ProductoImagenWS.obtenerProductoImagen(idProducto);
            return v;
        }
    }
}