using LocalWebService.EmpleadoWS;
using LocalWebService.PedidoWS;
using LocalWebService.UsuarioWS;
using LocalWebService.ComprobanteWS; 
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace LocalWebService
{
    public partial class ComprobantePago : System.Web.UI.Page
    {
        //sacar las lineas de venta relacionadas con un comprobante
        private ComprobanteWSClient daoComprobante;
        protected void Page_Load(object sender, EventArgs e)
        {
            daoComprobante = new ComprobanteWSClient(); 
            if (!IsPostBack)
                BindGrid();
        }

        private void BindGrid()
        {
            

            //funciona pero existe un problema que quiza se demore / no se actualice la informacion y siempre jale 
            //la informacion del producto actual -- casos descontinuados por ejemplo (pro ahora no hago cambios hasta conversarlo con el grupo) 
            
            gvDetalles.DataSource = daoComprobante.obtenerLineasPorIdComprobante(1);
            gvDetalles.DataBind();
        }

        protected void gvComprobante_RowCommand(object sender, GridViewCommandEventArgs e) { 
        
        }

        protected void gvDetalles_PageIndexChanging(object sender, GridViewPageEventArgs e)
        {
            gvDetalles.PageIndex = e.NewPageIndex;
            BindGrid();
        }
    }
}