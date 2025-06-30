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
                CargarEstados();
                CargarEmpleados();

                if (int.TryParse(Request.QueryString["id"], out int pedidoId))
                {
                    CargarPedido(pedidoId);
                }
                else
                {
                    lblMensaje.Text = "ID de pedido inválido.";
                }
            }

            if (!User.Identity.IsAuthenticated)
            {
                Response.Redirect("Login.aspx");
            }
        }

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
            LocalWSClient localWS = new LocalWSClient();

            try
            {
                var pedido = pedidoClient.obtenerPedidoPorId(pedidoId);

                if (pedido == null)
                {
                    lblMensaje.Text = "Pedido no encontrado.";
                    return;
                }

                lblPedidoNumero.Text = pedido.idOrdenVenta.ToString();
                lblFecha.Text = pedido.fechaStr;
                lblDescripcion.Text = pedido.descripcion;

                if (pedido.idLocal > 0)
                {
                    var local = localWS.obtenerLocal(pedido.idLocal);
                    lblLocal.Text = (local != null) ? local.nombre : "No disponible";
                }

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

                double subtotal = 0;
                foreach (var p in productos)
                    subtotal += p.subtotal;

                double igv = subtotal * 0.18;
                double total = subtotal + igv;

                lblSubtotal.Text = $"S/ {subtotal:F2}";
                lblIGV.Text = $"S/ {igv:F2}";
                lblTotal.Text = $"S/ {total:F2}";

                ddlEstado.SelectedValue = pedido.estado.ToString();
                if (pedido.idEmpleado > 0)
                {
                    ddlEmpleadoAsignado.SelectedValue = pedido.idEmpleado.ToString();
                }
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al cargar el pedido: " + ex.Message;
            }
            finally
            {
                pedidoClient.Close();
                empleadoClient.Close();
                localWS.Close();
            }
        }

        private void CargarEmpleados()
        {
            EmpleadoWSClient empleadoClient = new EmpleadoWSClient();

            try
            {
                int idLocal = (int)Session["idLocal"];
                var empleados = empleadoClient.obtenerEmpleadosxLocal(idLocal);
                ddlEmpleadoAsignado.Items.Clear();
                ddlEmpleadoAsignado.Items.Add(new ListItem("Seleccione un empleado", ""));

                foreach (empleadoDTO emp in empleados)
                {
                    string nombreCompleto = $"{emp.nombre} {emp.apellidoPaterno} {emp.apellidoMaterno}";
                    ddlEmpleadoAsignado.Items.Add(new ListItem(nombreCompleto, emp.idUsuario.ToString()));
                }
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al cargar empleados: " + ex.Message;
            }
            finally
            {
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

                pedido.estado = (estadoVenta)Enum.Parse(typeof(estadoVenta), ddlEstado.SelectedValue);

                if (!string.IsNullOrEmpty(ddlEmpleadoAsignado.SelectedValue))
                {
                    int empleadoId = int.Parse(ddlEmpleadoAsignado.SelectedValue);
                    var empleado = empleadoClient.obtenerEmpleadoPorId(empleadoId);

                    if (empleado == null)
                    {
                        lblMensaje.Text = "Empleado no encontrado.";
                        return;
                    }

                    pedido.idEmpleado = empleado.idUsuario;
                }
                else
                {
                    pedido.idEmpleado = 0;
                }

                pedidoClient.actualizarOrden(pedido);
                lblMensaje.Text = "Pedido actualizado correctamente.";
            }
            catch (System.Exception ex)
            {
                lblMensaje.Text = "Error al guardar: " + ex.Message;
            }
            finally
            {
                pedidoClient.Close();
                empleadoClient.Close();
            }
        }

        protected void btnVerPedidos_Click(object sender, EventArgs e)
        {
            Response.Redirect("ListaPedidos.aspx");
        }
    }
}
