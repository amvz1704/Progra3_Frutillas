<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Monitoreo.aspx.cs" Inherits="FrutillaWeb.Monitoreo" %>

<!DOCTYPE html>
<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Monitoreo.aspx.cs" Inherits="LocalWebService.Monitoreo" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Monitoreo - Reportes</title>
</head>
<body>
    <form id="form1" runat="server">
        <div style="margin:20px;">
            <h2>Generar Reporte</h2>
            <asp:Label runat="server" Text="Tipo de Reporte: " />
            <asp:DropDownList ID="ddlTipoReporte" runat="server">
                <asp:ListItem Text="Ventas por Local" Value="local" />
                <asp:ListItem Text="Ventas por Empleado" Value="empleado" />
            </asp:DropDownList>
            <br /><br />
            <asp:Label runat="server" Text="ID: " />
            <asp:TextBox ID="txtId" runat="server" />
            <br /><br />
            <asp:Button ID="btnGenerar" runat="server" Text="Generar PDF" OnClick="btnGenerar_Click" />
        </div>
    </form>
</body>
</html>