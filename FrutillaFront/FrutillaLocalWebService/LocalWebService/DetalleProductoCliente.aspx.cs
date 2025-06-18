using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalWebService.ClienteWS;
using LocalWebService.ComprobanteWS;
using LocalWebService.EmpleadoWS;
using LocalWebService.InventarioWS;
using LocalWebService.LocalWS;

namespace LocalWebService
{
	public partial class DetalleProductoCliente : System.Web.UI.Page
	{
        private InventarioWSClient inventarioWSClient;
        private ComprobanteWSClient ComprobanteWS;
        const int idPedidoOrden = 1;
        private int idLocal;
        private int idProducto;

        private List<ComprobanteWS.lineaOrdenDeVenta> Carrito
        {
            get
            {
                if (Session["Carrito"] == null)
                    Session["Carrito"] = new List<ComprobanteWS.lineaOrdenDeVenta>();
                return (List<ComprobanteWS.lineaOrdenDeVenta>)Session["Carrito"];
            }
        }
        
        public DetalleProductoCliente(){
            ComprobanteWS = new ComprobanteWSClient();
            inventarioWSClient = new InventarioWSClient();
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                
                int productoId;
                int localId;
                if (int.TryParse(Request.QueryString["id"], out productoId))
                {
                    idProducto = productoId;
                    if (int.TryParse(Request.QueryString["idLocal"], out localId))
                    {
                        // Cargar el producto con el ID y el local especificados
                        CargarProducto(productoId, localId);
                        idLocal = localId;
                    }
                    else
                    {
                        lblMensaje.Text = "ID de local inválido.";
                    }
                }
                else
                {
                    lblMensaje.Text = "ID de producto inválido.";
                }
            }

            if (User.Identity.IsAuthenticated)
            {
                string datos = FormsAuthentication.Decrypt(
                    Request.Cookies[FormsAuthentication.FormsCookieName].Value
                ).UserData;

                string[] partes = datos.Split('|');
                string tipoUsuario = partes[0];
                int idUsuario = int.Parse(partes[1]);

                // Puedes usar idUsuario o tipoUsuario si es necesario
            }
            else
            {
                Response.Redirect("Login.aspx");
            }

        }




        protected void CargarProducto(int idProducto, int idLocal) { 


            
            char tipoProducto = (char)inventarioWSClient.obtenerTipoProducto(idProducto, idLocal);
            imgProducto.Src = ObtenerImagenPorTipo(idProducto, idLocal);
            HiddenTipoProductoEdit.Value = tipoProducto.ToString();

            ScriptManager.RegisterStartupScript(
    this,
    this.GetType(),
    "MostrarOpcionesEditar",
    "mostrarOpcionesEditar('" + tipoProducto + "');",
    true
);
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
                            TxtTipoSnackEdit.Text = snack.tipo;
                            TxtEnvaseSnackEdit.Text = snack.envase;
                            // Asigna los campos específicos de snack si los tienes en el modal
                        }
                        break;
                }
            }


        public void btnAgregarAlCarrito_Click(object sender, EventArgs e)
        {
            int idProd;
            int.TryParse(Request.QueryString["id"], out idProd);
            


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

        public string ObtenerImagenPorTipo(object idProd, int idLocal)
        {
            int idProducto = Convert.ToInt32(idProd);
            ushort tipo = inventarioWSClient.obtenerTipoProducto(idProducto, idLocal);
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