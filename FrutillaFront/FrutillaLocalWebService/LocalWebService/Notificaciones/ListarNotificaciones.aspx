<%@ Page Title="" Language="C#" MasterPageFile="~/MainLayout.Master" AutoEventWireup="true" CodeBehind="ListarNotificaciones.aspx.cs" Inherits="LocalWebService.Notificaciones.ListarNotificaciones" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <header class="bg-light py-2 border-bottom">
        <div class="container">
            <h2 class="m-0">Notificaciones </h2>
        </div>
    </header>
    <div class="container">
        <input type="date" id="datePicker" runat="server" />
        <asp:Button ID="btnSubmitDate" runat="server" Text="Buscar Notificaciones" OnClick="btnSubmitDate_Click" />
    </div>
    <asp:GridView ID="gvNotificaciones" runat="server" AutoGenerateColumns="false" 
            AllowPaging="true" PageSize="10" OnPageIndexChanging="gvNotificaciones_PageIndexChanging">
        <Columns>
            <asp:BoundField DataField ="idNotificacion" HeaderText ="Id" />
            <asp:BoundField DataField ="titulo" HeaderText ="Titulo" />
            <asp:BoundField DataField ="descripcion" HeaderText ="Descripcion" />
            <asp:BoundField DataField ="fecha" HeaderText ="Descripcion" DataFormatString="{0:dd/MM/yyyy}" />
        </Columns>
    </asp:GridView>
    <asp:Label ID="lblError" runat="server" Text="-"></asp:Label>
</asp:Content>
