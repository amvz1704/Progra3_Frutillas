<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClientePedidos.aspx.cs" Inherits="LocalWebService.ClientePedidos" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <header class="py-2 border-bottom bg-frutilla">
        <div class="container">
            <h2 class="m-0">Pedidos Realizados</h2>
        </div>
    </header>

    <div class="container m-4">
        <asp:DropDownList ID="ddlLocales" runat="server" AutoPostBack="true" 
            OnSelectedIndexChanged="ddlLocales_SelectedIndexChanged"
             CssClass="form-control"></asp:DropDownList>
    </div>

    <div class="container">
        <asp:GridView ID="gvPedidosCliente" runat="server" AutoGenerateColumns="false"
            AllowPaging="true" PageSize="10" OnPageIndexChanging="gvPedidosCliente_PageIndexChanging"
            CssClass="table table-striped table-responsive table-hover"
            OnRowCommand="gvPedidosCliente_RowCommand">
            <Columns>
                <asp:BoundField DataField="idOrdenVenta" HeaderText="Id" />
                <asp:BoundField DataField="descripcion" HeaderText="Descripcion" />
                <asp:BoundField DataField="fechaStr" HeaderText="Fecha" />
                <asp:BoundField DataField="horaStr" HeaderText="Hora" />
                <asp:BoundField DataField="estado" HeaderText="Estado" />
                <asp:TemplateField HeaderText="Acciones">
                    <ItemTemplate>
                        <asp:LinkButton ID="btnVerComprobante" runat="server"
                            Text="Ver Comprobante"
                            CommandName="VerComprobante"
                            CommandArgument='<%# Eval("idOrdenVenta") %>'
                            CssClass="btn-frutilla me-2" style="margin-right: 8px;">
                            <i class="bi bi-file-earmark-text" title="Ver Comprobante"></i>
                        </asp:LinkButton>
                        <asp:LinkButton ID="btnVerDetalle" runat="server"
                            Text="Ver Detalle"
                            CommandName="VerDetalle"
                            CommandArgument='<%# Eval("idOrdenVenta") %>'
                            CssClass="btn-frutilla">
                             <i class="bi bi-eye" title="Ver Detalle"></i>
                        </asp:LinkButton>
                    </ItemTemplate>
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>

    <asp:Label ID="lblError" runat="server" Text="-" CssClass="text-danger"></asp:Label>
</asp:Content>
