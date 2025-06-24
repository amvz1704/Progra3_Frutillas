using LocalWebService.ComprobanteWS;
using LocalWebService.InventarioWS;
using LocalWebService.LocalWS;
using LocalWebService.PedidoWS;
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
        private ComprobanteWSClient ComprobanteWS;
        private LocalWSClient localWSClient;
        const int idPedidoOrden = 1; 

        // Propiedad helper para el carrito en Session
        private List<ComprobanteWS.lineaOrdenDeVenta> Carrito
        {
            get
            {
                if (Session["Carrito"] == null)
                    Session["Carrito"] = new List<ComprobanteWS.lineaOrdenDeVenta>();
                return (List<ComprobanteWS.lineaOrdenDeVenta>)Session["Carrito"];
            }   
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            ComprobanteWS = new ComprobanteWSClient(); 
            inventarioWSClient = new InventarioWSClient();
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
            ddlLocal.Items.Add(new ListItem("Selecciona un local", "0"));
            foreach (var local in localWSClient.listarLocales()) // Cambiar por locales activos
            {
                ddlLocal.Items.Add(new ListItem(local.nombre, local.idLocal.ToString()));
            }

            //debo guardar el local seleccionado
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


        private void CargarProductos()
        {
            try
            {
                var productos = inventarioWSClient.listarTodos(int.Parse(ddlLocal.SelectedValue)); //cambiar por el local seleccionado 
                
                
                
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
                lblError.Text = "Debe seleccionar un local ";
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
            if (e.CommandName == "agregarCarrito") {

                //se crea un nuevo Pedido, que se podra editar al ver carrito 
                int idProd = Convert.ToInt32(e.CommandArgument);



                // Si ya existe la línea, puedes aumentar cantidad 
                ComprobanteWS.lineaOrdenDeVenta existente = Carrito.Find(x => x.producto.idProducto == idProd);
                if (existente != null)
                {
                    existente.cantidad++;
                    existente.subtotal = existente.cantidad * existente.producto.precioUnitario;
                }
                else
                {
                    //creas una nueva orden de linea ordenDeVenta para agregar a linea ordenDeVenta
                    ComprobanteWS.producto copia = ComprobanteWS.obtenerProductoPorId(idProd);

                    Carrito.Add(new ComprobanteWS.lineaOrdenDeVenta
                    {
                        producto = copia,
                        cantidad = 1,
                        idOrdenVenta = idPedidoOrden

                    });
                }

            }
        }


        protected void Filtro_Click(object sender, EventArgs e)
        {
           // sender será el LinkButton que clickeaste
            var btn = (LinkButton)sender;
            string categoria = btn.CommandArgument;

            // Marcar botón activo
            foreach (var ctrl in filterTags.Controls)
            {
                if (ctrl is LinkButton lb)
                    lb.CssClass = "btn btn-outline-light";
            }
            btn.CssClass = "btn btn-outline-light active";

            // Filtrar tu listado con esa categoría...
            BuscarYBind(txtBuscar.Text.Trim(), ddlLocal.SelectedValue, categoria);
        }

        private void BuscarYBind(string criterio, string localId, string categoria)
        {
            // Tu lógica para buscar productos y bindearlos a un repeater/grid...
        }


        //esta función obtiene la imagen del producto según su tipo
        public string ObtenerImagenPorTipo(object idProd)
        {
            int idProducto = Convert.ToInt32(idProd);
            inventarioWSClient = new InventarioWSClient();
            ushort tipo = inventarioWSClient.obtenerTipoProducto(idProducto, int.Parse(ddlLocal.SelectedValue));
            switch (tipo)
            {
                case 'F': return "/Public/images/frutas.jpg";
                case 'B': return "/Public/images/bebida.jpg";
                case 'S': return "/Public/images/snack.jpg";
                default: return "/Public/images/producto.jpg";
            }
        }
    }
}