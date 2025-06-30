<%@ Page Title="" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="DetallePedido.aspx.cs" Inherits="LocalWebService.DetallePedido" %>

<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container my-4">
        <h2 class="mb-4">Detalle del Pedido</h2>

        <!-- Info del pedido -->
        <div class="row mb-3">
            <div class="col-md-6">
                <p class="fs-5"><strong>Pedido N.º:</strong> <asp:Label ID="lblPedidoNumero" runat="server" /></p>
            </div>
            <div class="col-md-6 text-end">
                <asp:Button ID="btnVerPedidos" runat="server" Text="Ver Pedidos" CssClass="btn btn-success" OnClick="btnVerPedidos_Click" />
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label><strong>Fecha:</strong></label>
                <asp:Label ID="lblFecha" runat="server" CssClass="form-control border-0 bg-transparent px-0" />
            </div>
            <div class="col-md-6">
                <label><strong>Descripción:</strong></label>
                <asp:Label ID="lblDescripcion" runat="server" CssClass="form-control border-0 bg-transparent px-0" />
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label><strong>Local:</strong></label>
                <asp:Label ID="lblLocal" runat="server" CssClass="form-control border-0 bg-transparent px-0" />
            </div>
        </div>

        <!-- Productos -->
        <asp:GridView ID="gvProductos" runat="server" AutoGenerateColumns="False" CssClass="table table-striped table-bordered">
            <Columns>
                <asp:BoundField DataField="Producto.nombre" HeaderText="Producto" />
                <asp:BoundField DataField="cantidad" HeaderText="Cantidad" />
                <asp:BoundField DataField="Producto.precioUnitario" HeaderText="Precio Unitario" DataFormatString="{0:C}" />
                <asp:BoundField DataField="subtotal" HeaderText="Subtotal" DataFormatString="{0:C}" />
            </Columns>
        </asp:GridView>

        <!-- Totales -->
        <div class="row mt-4 text-end">
            <div class="col-md-4 offset-md-8">
                <p><strong>Subtotal:</strong> <asp:Label ID="lblSubtotal" runat="server" /></p>
                <p><strong>IGV (18%):</strong> <asp:Label ID="lblIGV" runat="server" /></p>
                <p><strong>Total:</strong> <asp:Label ID="lblTotal" runat="server" /></p>
            </div>
        </div>

        <!-- Formulario estado y asignado -->
        <div class="row mt-4">
            <div class="col-md-6">
                <label for="ddlEstado" class="form-label fw-bold">Estado</label>
                <asp:DropDownList ID="ddlEstado" runat="server" CssClass="form-select" />
            </div>
            <div class="col-md-6">
                <label for="ddlEmpleadoAsignado" class="form-label fw-bold">Empleado Asignado</label>
                <asp:DropDownList ID="ddlEmpleadoAsignado" runat="server" CssClass="form-select">
                    <asp:ListItem Text="Sin asignar" Value="" />
                </asp:DropDownList>
            </div>
        </div>

        <!-- Botón guardar -->
        <div class="d-flex justify-content-center mt-4">
            <asp:Button ID="btnGuardar" runat="server"
                CssClass="btn btn-success"
                Text="Guardar Cambios"
                OnClick="btnGuardar_Click" />
        </div>

        <!-- Mensaje -->
        <div class="row mt-3">
            <div class="col-12">
                <asp:Label ID="lblMensaje" runat="server" CssClass="text-success fw-bold" />
            </div>
        </div>
    </div>
</asp:Content>