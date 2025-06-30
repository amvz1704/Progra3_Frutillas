<%@ Page Title="Monitoreo" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="Monitoreo.aspx.cs" Inherits="LocalWebService.Monitoreo" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <!-- Aquí podrías poner estilos o scripts si deseas -->
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-4">
        <h2>Generar Reporte</h2>
        <div class="mb-3">
            <label for="ddlTipoReporte" class="form-label fw-bold">Tipo de Reporte:</label>
            <asp:DropDownList ID="ddlTipoReporte" runat="server" CssClass="form-select" AutoPostBack="true" OnSelectedIndexChanged="ddlTipoReporte_SelectedIndexChanged">
                <asp:ListItem Text="Ventas por Local" Value="local" />
                <asp:ListItem Text="Ventas por Empleado" Value="empleado" />
            </asp:DropDownList>
        </div>
        <div class="mb-3">
            <label for="txtId" class="form-labe fw-boldl">ID de Local:</label>
            <asp:TextBox ID="txtId" runat="server" CssClass="form-control" ReadOnly="true" />
            <label for="txtEmpleadoAsignado" class="form-label fw-bold">Empleado</label>
            <asp:DropDownList ID="ddlEmpleadoAsignado" runat="server" CssClass="form-select" >
                <asp:ListItem Text="Sin asignar" Value="" />
            </asp:DropDownList>
        </div>
        <asp:Button ID="btnGenerar" runat="server" Text="Generar PDF" CssClass="btn btn-primary" OnClick="btnGenerar_Click" />
        <div class="row mt-3">
            <div class="col-12">
                <asp:Label ID="lblMensaje" runat="server" CssClass="text-success fw-bold"></asp:Label>
            </div>
        </div>
    </div>
</asp:Content>
