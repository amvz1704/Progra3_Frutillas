using LocalWebService.InventarioWS;
using LocalWebService.LocalWS;
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
        protected void Page_Load(object sender, EventArgs e)
        {

            inventarioWSClient = new InventarioWSClient();
            if (!IsPostBack)
            {
                CargarProductos();
            }
        }

        private void CargarProductos()
        {
            try
            {
                var productos = inventarioWSClient.listarTodos(1); //cambiar por el local seleccionado 
                rptProductos.DataSource = productos;
                rptProductos.DataBind();
            }
            catch (Exception ex)
            {
                lblError.Text = "Error al cargar productos: " + ex.Message;
            }
        }

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
            if (e.CommandName == "agregarCarrito") { 
                //se crea un nuevo Pedido, que se podra editar al ver carrito 

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


    }
}