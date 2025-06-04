using LocalWebService.PedidoWS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class ListaPedidos : System.Web.UI.Page
    {
        protected PedidoWSClient cliente;
        protected void Page_Init(object sender, EventArgs e)
        {
            
            PedidoWSClient cliente = new PedidoWSClient();
        }
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void btnBuscar_Click(object sender, EventArgs e)
        {
            try
            {
                int idEmpleado = int.Parse(txtIdEmpleado.Text);
                
                var pedidos = cliente.obtenerPedidosPorEmpleado(idEmpleado);
                gvPedidos.DataSource = pedidos;
                gvPedidos.DataBind();
                lblMensaje.Text = "";
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error: " + ex.Message;
            }
        }
    }
}