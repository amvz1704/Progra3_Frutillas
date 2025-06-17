using LocalWebService.EmpleadoWS;
using LocalWebService.LocalWS;
using LocalWebService.PedidoWS;
using System;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class DetallePedido : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                CargarEstados(); // ✅ Importante: poblar antes de asignar valor

                int pedidoId;
                if (int.TryParse(Request.QueryString["id"], out pedidoId))
                {
                    CargarPedido(pedidoId);
                }
                else
                {
                    lblMensaje.Text = "ID de pedido inválido.";
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

        // ✅ Método para cargar los valores del enum estadoVenta
        private void CargarEstados()
        {
            ddlEstado.Items.Clear();
            foreach (string nombre in Enum.GetNames(typeof(estadoVenta)))
            {
                ddlEstado.Items.Add(new ListItem(nombre, nombre));
            }
        }

        private void CargarPedido(int pedidoId)
        {
            PedidoWSClient pedidoClient = new PedidoWSClient();
            EmpleadoWSClient empleadoClient = new EmpleadoWSClient();

            try
            {
                var pedido = pedidoClient.obtenerPedidoPorId(pedidoId);

                if (pedido == null)
                {
                    lblMensaje.Text = "Pedido no encontrado.";
                    return;
                }

                lblPedidoNumero.Text = pedido.idOrdenVenta.ToString();

                var productos = pedidoClient.obtenerDetallePedidoList(pedidoId);

                if (productos == null || productos.Length == 0)
                {
                    lblMensaje.Text = "No hay productos asociados al pedido.";
                    gvProductos.DataSource = null;
                    gvProductos.DataBind();
                    return;
                }

                gvProductos.DataSource = productos;
                gvProductos.DataBind();

                double totalPedido = 0;
                foreach (var p in productos)
                    totalPedido += p.subtotal;

                lblTotalPedido.Text = totalPedido.ToString("C");

                ddlEstado.SelectedValue = pedido.estado.ToString();
                txtEmpleadoAsignado.Text = (pedido.idEmpleado > 0)
                    ? pedido.idEmpleado.ToString()
                    : "";
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al cargar el pedido: " + ex.Message;
            }
            finally
            {
                pedidoClient.Close();
                empleadoClient.Close();
            }
        }

        protected void btnGuardar_Click(object sender, EventArgs e)
        {
            PedidoWSClient pedidoClient = new PedidoWSClient();
            EmpleadoWSClient empleadoClient = new EmpleadoWSClient();

            try
            {
                int pedidoId = int.Parse(Request.QueryString["id"]);
                var pedido = pedidoClient.obtenerPedidoPorId(pedidoId);

                if (pedido == null)
                {
                    lblMensaje.Text = "Pedido no encontrado.";
                    return;
                }

                // ✅ Convertir estado seleccionado (asegúrate que ddlEstado tiene valores del enum exactos)
                pedido.estado = (estadoVenta)Enum.Parse(typeof(estadoVenta), ddlEstado.SelectedValue);

                // ✅ Validar ID de empleado
                int empleadoId;
                if (!int.TryParse(txtEmpleadoAsignado.Text, out empleadoId))
                {
                    lblMensaje.Text = "Empleado asignado inválido.";
                    return;
                }

                var empleado = empleadoClient.obtenerEmpleadoPorId(empleadoId);
                if (empleado == null)
                {
                    lblMensaje.Text = "Empleado no encontrado.";
                    return;
                }

                // ✅ Solo actualiza ID del empleado, ya que no tienes propiedad "empleado"
                pedido.idEmpleado = empleado.idUsuario;

                // No toques cliente, local ni fecha si no están disponibles en el modelo
                // No modifiques lineasOrden si no existe

                // ✅ Guardar cambios
                pedidoClient.actualizarOrden(pedido);

                lblMensaje.Text = "Pedido actualizado correctamente.";
            }
            catch (Exception ex)
            {
                lblMensaje.Text = "Error al guardar: " + ex.Message;
            }
            finally
            {
                pedidoClient.Close();
                empleadoClient.Close();
            }
        }
    }
}