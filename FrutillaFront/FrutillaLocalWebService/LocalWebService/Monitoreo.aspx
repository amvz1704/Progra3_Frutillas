<%@ Page Title="Monitoreo" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="Monitoreo.aspx.cs" Inherits="LocalWebService.Monitoreo" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <!-- Aquí podrías poner estilos o scripts si deseas -->
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-4">
        <h2>Generar Reporte</h2>
        <div class="mb-3">
            <label for="ddlTipoReporte" class="form-label">Tipo de Reporte:</label>
            <asp:DropDownList ID="ddlTipoReporte" runat="server" CssClass="form-select">
                <asp:ListItem Text="Ventas por Local" Value="local" />
                <asp:ListItem Text="Ventas por Empleado" Value="empleado" />
            </asp:DropDownList>
        </div>
        <div class="mb-3">
            <label for="txtId" class="form-label">ID:</label>
            <asp:TextBox ID="txtId" runat="server" CssClass="form-control" />
        </div>
        <asp:Button ID="btnGenerar" runat="server" Text="Generar PDF" CssClass="btn btn-primary" OnClick="btnGenerar_Click" />
    </div>
</asp:Content>
