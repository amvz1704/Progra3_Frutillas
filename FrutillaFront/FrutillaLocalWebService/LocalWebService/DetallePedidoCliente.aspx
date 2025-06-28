<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="DetallePedidoCliente.aspx.cs" Inherits="LocalWebService.DetallePedidoCliente" MasterPageFile="~/Cliente.Master" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container mt-4 p-4 border rounded bg-light shadow-sm">
        <p class="fs-5 mb-3">
            <strong>Pedido N.º:</strong>
            <asp:Label ID="lblNumeroPedido" runat="server" CssClass="ms-2" />
        </p>

        <h2 class="mb-4">Detalle del Pedido</h2>

        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

        <div class="row mb-3">
            <div class="col-md-6">
                <label><strong>Fecha:</strong></label>
                <asp:Label ID="lblFecha" runat="server" CssClass="form-control border-0 bg-transparent px-0" />
            </div>
            <div class="col-md-6">
                <label><strong>Estado:</strong></label>
                <asp:Label ID="lblEstado" runat="server" CssClass="form-control border-0 bg-transparent px-0" />
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label><strong>Descripción:</strong></label>
                <asp:Label ID="lblDescripcion" runat="server" CssClass="form-control border-0 bg-transparent px-0" />
            </div>
            <div class="col-md-6">
                <label><strong>Local de entrega:</strong></label>
                <asp:Label ID="lblLocal" runat="server" CssClass="form-control border-0 bg-transparent px-0" />
            </div>
        </div>

        <h4 class="mt-4">Productos:</h4>
        <asp:GridView ID="gvDetalle" runat="server" AutoGenerateColumns="False"
            CssClass="table table-bordered table-striped">
            <Columns>
                <asp:BoundField DataField="producto.nombre" HeaderText="Nombre" />
                <asp:BoundField DataField="producto.descripcion" HeaderText="Descripción" />
                <asp:BoundField DataField="cantidad" HeaderText="Cantidad" />
                <asp:BoundField DataField="producto.precioUnitario" HeaderText="Precio Unitario" DataFormatString="{0:C}" />
                <asp:BoundField DataField="subtotal" HeaderText="Subtotal" DataFormatString="{0:C}" />
            </Columns>
        </asp:GridView>

        <div class="row mt-4 text-end">
            <div class="col-md-4 offset-md-8">
                <p><strong>Subtotal:</strong> <asp:Label ID="lblSubtotal" runat="server" /></p>
                <p><strong>IGV (18%):</strong> <asp:Label ID="lblIGV" runat="server" /></p>
                <p><strong>Total:</strong> <asp:Label ID="lblTotal" runat="server" /></p>
            </div>
        </div>
        <div class="d-flex justify-content-center mt-4">
            <asp:Button ID="btnVerPedidos" runat="server"
                CssClass="btn btn-success"
                Text="Ver Pedidos"
                OnClick="btnVerPedidos_Click" />
        </div>
    </div>
</asp:Content>
