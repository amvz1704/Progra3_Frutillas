using LocalWebService.ReporteWS;

using System;
using System.IO;
using System.Web.UI;

namespace LocalWebService
{
    public partial class Monitoreo : Page
    {
        
        protected void btnGenerar_Click(object sender, EventArgs e)
        {
            string tipo = ddlTipoReporte.SelectedValue;
            int id = int.Parse(txtId.Text);

            try
            {
                var cliente = new ReporteWSClient.ReporteWS(); // Usa el namespace que elegiste al agregar el servicio

                byte[] pdfBytes;

                if (tipo == "local")
                {
                    pdfBytes = cliente.VentasXLocal(id);
                }
                else
                {
                    pdfBytes = cliente.VentasXEmpleado(id);
                }

                // Descargar el PDF
                string nombreArchivo = $"Reporte_{tipo}_{id}.pdf";
                Response.Clear();
                Response.ContentType = "application/pdf";
                Response.AddHeader("Content-Disposition", $"attachment; filename={nombreArchivo}");
                Response.BinaryWrite(pdfBytes);
                Response.End();
            }
            catch (Exception ex)
            {
                Response.Write($"<h3 style='color:red;'>Error al generar el reporte: {ex.Message}</h3>");
            }
        }
    }
}