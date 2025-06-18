<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteNotificaciones.aspx.cs" Inherits="LocalWebService.Notificaciones.ClienteNotificaciones" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
     <header class="py-2 border-bottom bg-frutilla">
     <div class="container m-4">
         <h2 class="m-0">Notificaciones </h2>
     </div>
 </header>
        <div class="container">
        <div style="background-color: #F8FBD9; padding: 15px; border-radius: 5px;">
            <asp:GridView ID="gvNotificaciones" runat="server" AutoGenerateColumns="false"
                AllowPaging="true" PageSize="10" OnPageIndexChanging="gvNotificaciones_PageIndexChanging"
                CssClass="table table-striped table-responsive table-hover">
                <Columns>
                    <asp:BoundField DataField="idNotificacion" HeaderText="Id" />
                    <asp:BoundField DataField="titulo" HeaderText="Titulo" />
                    <asp:BoundField DataField="descripcion" HeaderText="Descripcion" />
                    <asp:BoundField DataField="fechaStr" HeaderText="Fecha" />
                </Columns>
            </asp:GridView>
        </div>
        <asp:Label ID="lblError" runat="server" Text="-" CssClass="text-danger"></asp:Label>
        </div>
</asp:Content>
