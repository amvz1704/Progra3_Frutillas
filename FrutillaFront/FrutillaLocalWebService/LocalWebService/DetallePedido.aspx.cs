using LocalWebService.EmpleadoWS;
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
    public partial class DetallePedido : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
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

                // Aca pueden hacer uso del obtener por id
            }
            else
            {
                Response.Redirect("Login.aspx");
            }
        }

        private void CargarPedido(int pedidoId)
        {
            
            // Crear clientes WS
            PedidoWSClient pedidoClient = new PedidoWSClient();
            EmpleadoWSClient empleadoClient = new EmpleadoWSClient();

            try
            {
                // Obtener pedido general (estado, empleado)
                var pedido = pedidoClient.obtenerPedidoPorId(pedidoId);

                if (pedido == null)
                {
                    lblMensaje.Text = "Pedido no encontrado.";
                    return;
                }

                // Mostrar el ID del pedido
                lblPedidoNumero.Text = pedido.idOrdenVenta.ToString();  // Usamos IdOrdenVenta directamente

                // Obtener detalle (productos)
                var productos = pedidoClient.obtenerDetallePedido(pedidoId);
                gvProductos.DataSource = productos;
                gvProductos.DataBind();

                // Mostrar total pedido (sumar totales)
                double totalPedido = 0;
                foreach (var p in productos)
                    totalPedido += p.subtotal; // Usamos Subtotal directamente

                lblTotalPedido.Text = totalPedido.ToString("C");

                // Llenar estado (EstadoVenta es un enum)
                ddlEstado.SelectedValue = pedido.estado.ToString();  // Usamos el enum para el estado

                // Llenar empleado asignado (verificar si el pedido tiene un empleado asignado)
                if (pedido.idEmpleado > 0)
                    txtEmpleadoAsignado.Text = pedido.idEmpleado.ToString();  // Mostrar el ID del empleado asignado
                else
                    txtEmpleadoAsignado.Text = "";  // Si no tiene, dejar el campo vacío
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

                // Actualizar estado del pedido (convertir de string a enum)
                pedido.estado = (estadoVenta)Enum.Parse(typeof(estadoVenta), ddlEstado.SelectedValue);

                // Actualizar empleado asignado (validar el ID ingresado)
                int empleadoId;
                if (!int.TryParse(txtEmpleadoAsignado.Text, out empleadoId))
                {
                    lblMensaje.Text = "Empleado asignado inválido.";
                    return;
                }

                // Buscar el empleado en el WS
                var empleado = empleadoClient.obtenerEmpleadoPorId(empleadoId);
                if (empleado == null)
                {
                    lblMensaje.Text = "Empleado no encontrado.";
                    return;
                }

                // Asignar el empleado al pedido
                pedido.idEmpleado = empleado.idUsuario;

                // Guardar pedido actualizado
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