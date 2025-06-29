using System;
using System.Web.UI;
using LocalWebService.EmpleadoWS; // Asegúrate que este namespace sea correcto

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
                // Usa la clase generada por la referencia del servicio
                var cliente = new EmpleadoWSClient();

                byte[] pdfBytes;

                if (tipo == "local")
                {
                    pdfBytes = cliente.VentasXLocal(id);
                }
                else
                {
                    pdfBytes = cliente.VentasXEmpleado(id);
                }

                // Enviar PDF al navegador
                string nombreArchivo = $"Reporte_{tipo}_{id}.pdf";
                Response.Clear();
                Response.ContentType = "application/pdf";
                Response.AddHeader("Content-Disposition", $"attachment; filename={nombreArchivo}");
                Response.BinaryWrite(pdfBytes);
                Response.End();
            }
            catch (System.Exception ex)
            {
                Response.Write($"<h3 style='color:red;'>Error al generar el reporte: {ex.Message}</h3>");
            }
        }
    }
}