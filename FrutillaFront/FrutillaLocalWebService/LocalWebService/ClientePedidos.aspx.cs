using LocalWebService.ClienteWS;
using LocalWebService.EmpleadoWS;
using LocalWebService.LocalWS;
using LocalWebService.NotificionesWS;
using LocalWebService.PedidoWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class ClientePedidos : System.Web.UI.Page
    {
        private ClienteWSClient clienteWS;
        private PedidoWSClient pedidoWS;
        private LocalWSClient localWS;
        private int idCliente;
        private int idLocalSeleccionado = 0;

        public ClientePedidos()
        {
            clienteWS = new ClienteWSClient();
            pedidoWS = new PedidoWSClient();
            localWS = new LocalWSClient();
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
                cliente cliente = clienteWS.obtenerClientePorId(idUsuario);

                if (cliente != null)
                {
                    idCliente = cliente.idUsuario;
                }
                else
                {
                    Response.Redirect("Login.aspx");
                }
            }
            else
            {
                Response.Redirect("Login.aspx");
            }

            if (!IsPostBack)
            {
                llenarDropdownLocales();
                CargarPedidos(idLocalSeleccionado);
            }
        }

        private void CargarPedidos(int idLocal)
        {
            try
            {
                if (idLocal == 0)
                {
                    gvPedidosCliente.DataSource = pedidoWS.obtenerPedidosPorCliente(idCliente);
                }
                else
                {
                    gvPedidosCliente.DataSource = pedidoWS.listarPedidoPorLocalCliente(idLocal, idCliente);
                }
                gvPedidosCliente.DataBind();
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar pedidos: " + ex.Message;
            }
        }

        private void llenarDropdownLocales()
        {
            ddlLocales.DataSource = localWS.listarLocales();
            ddlLocales.DataTextField = "nombre";
            ddlLocales.DataValueField = "idLocal";
            ddlLocales.DataBind();
            ddlLocales.Items.Insert(0, new ListItem("-- Todos los locales --", "0"));
        }


        protected void gvPedidosCliente_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvPedidosCliente.PageIndex = e.NewPageIndex;
            CargarPedidos(idLocalSeleccionado);
        }

        protected void gvPedidosCliente_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "VerComprobante")
            {
                int pedidoId;
                if (int.TryParse(e.CommandArgument.ToString(), out pedidoId))
                {
                    ordenVenta ordenVenta = pedidoWS.obtenerPedidoPorId(pedidoId);
                    if(ordenVenta.idComprobante != 0)
                    {
                        Response.Redirect("ComprobantePago.aspx?idComprobante=" + ordenVenta.idComprobante);
                    }
                }
            }
            if(e.CommandName == "VerDetalle")
            {
                int pedidoId;
                if (int.TryParse(e.CommandArgument.ToString(), out pedidoId))
                {
                    Response.Redirect("DetallePedidoCliente.aspx?idPedido=" + pedidoId);
                }
            }
        }

        protected void ddlLocales_SelectedIndexChanged(object sender, EventArgs e)
        {
            idLocalSeleccionado = int.Parse(ddlLocales.SelectedValue);
            CargarPedidos(idLocalSeleccionado);
        }
    }
}