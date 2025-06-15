<%@ Page Title="" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="ListaPedidos.aspx.cs" Inherits="LocalWebService.ListaPedidos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <header class="py-2 border-bottom bg-frutilla">
        <div class="container">
            <h2 class="m-0">Pedido</h2>
        </div>
    </header>
    <div class="col-md-3 ms-auto text-end mt-2 mt-md-0 pe-5">
        <asp:Button ID="btnPedidos_Empleado" runat="server" Text="Buscar Mis Pedidos" OnClick="btnPedidos_Empleado_Click" CssClass="btn-frutilla" />
    </div>
    <div class="container">
        <asp:GridView ID="gvPedidos" runat="server" AutoGenerateColumns="false"
            AllowPaging="true" PageSize="10" OnPageIndexChanging="gvPedidos_PageIndexChanging"
            CssClass="table table-striped table-responsive table-hover"
            OnRowCommand="gvPedidos_RowCommand">
            <Columns>
                <asp:BoundField DataField="idOrdenVenta" HeaderText="Id" />
                <asp:BoundField DataField="descripcion" HeaderText="Descripcion" />
                <asp:BoundField DataField="fechaStr" HeaderText="Fecha" />
                <asp:BoundField DataField="horaStr" HeaderText="Hora" />
                <asp:BoundField DataField="estado" HeaderText="Estado" />
                <asp:TemplateField HeaderText="Acciones">
                    <ItemTemplate>
                        <asp:Button ID="btnVerDetalle" runat="server"
                            Text="Ver Detalle"
                            CommandName="VerDetalle"
                            CommandArgument='<%# Eval("idOrdenVenta") %>'
                            CssClass="btn btn-primary btn-sm" />
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
    <asp:Label ID="lblError" runat="server" Text="-" CssClass="text-danger"></asp:Label>
</asp:Content>
