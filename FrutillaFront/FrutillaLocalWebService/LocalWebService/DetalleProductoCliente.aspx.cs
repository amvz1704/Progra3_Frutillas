using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using LocalWebService.ClienteWS;
using LocalWebService.EmpleadoWS;
using LocalWebService.InventarioWS;
using LocalWebService.LocalWS;

namespace LocalWebService
{
	public partial class DetalleProductoCliente : System.Web.UI.Page
	{
        private InventarioWSClient inventarioWSClient;

        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {

                int productoId;
                int localId;
                if (int.TryParse(Request.QueryString["id"], out productoId))
                {
                    if (int.TryParse(Request.QueryString["idLocal"], out localId))
                    {
                        // Cargar el producto con el ID y el local especificados
                        CargarProducto(productoId, localId);
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


            inventarioWSClient = new InventarioWSClient();
            char tipoProducto = (char)inventarioWSClient.obtenerTipoProducto(idProducto, idLocal);

                HiddenTipoProductoEdit.Value = tipoProducto.ToString();
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
            }

        
    }
}