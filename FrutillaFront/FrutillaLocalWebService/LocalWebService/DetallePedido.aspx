<%@ Page Title="" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="DetallePedido.aspx.cs" Inherits="LocalWebService.DetallePedido" %>
<asp:Content ID="Content1" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container my-4">
        <h2 class="mb-4">Detalle del Pedido</h2>

        <!-- Información principal -->
        <div class="row mb-3">
            <div class="col-md-6">
                <h5>Pedido N°: <asp:Label ID="lblPedidoNumero" runat="server" CssClass="fw-bold" /></h5>
            </div>
            <div class="col-md-6 text-end">
                <asp:Button ID="btnGuardar" runat="server" Text="Guardar Cambios" CssClass="btn btn-success" OnClick="btnGuardar_Click" />
            </div>
        </div>

       <asp:GridView ID="gvProductos" runat="server" AutoGenerateColumns="False" CssClass="table table-striped table-bordered">
            <Columns>
                <asp:BoundField DataField="Producto.nombre" HeaderText="Producto" />
                <asp:BoundField DataField="cantidad" HeaderText="Cantidad" />
                <asp:BoundField DataField="Producto.precioUnitario" HeaderText="Precio Unitario" DataFormatString="{0:C}" />
                <asp:BoundField DataField="subtotal" HeaderText="Subtotal" DataFormatString="{0:C}" />
            </Columns>
            <FooterStyle Font-Bold="True" />
        </asp:GridView>
         <FooterTemplate>
            <tr>
                <td colspan="3" class="fw-bold">Total</td>
                <td>
                    <asp:Label ID="lblTotalPedido" runat="server" Text="" CssClass="fw-bold" />
                </td>
            </tr>
        </FooterTemplate>
        <!-- Formulario para Estado y Empleado Asignado -->
        <div class="row mt-4">
            <div class="col-md-6">
                <label for="ddlEstado" class="form-label fw-bold">Estado</label>
                <asp:DropDownList ID="ddlEstado" runat="server" CssClass="form-select">
                    <asp:ListItem Value="Pendiente de Pago">Pendiente de Pago</asp:ListItem>
                    <asp:ListItem Value="Proceso">Proceso</asp:ListItem>
                    <asp:ListItem Value="Por Entregar">Por Entregar</asp:ListItem>
                    <asp:ListItem Value="Entregado">Entregado</asp:ListItem>
                    <asp:ListItem Value="Cancelado">Cancelado</asp:ListItem>
                    <asp:ListItem Value="Cambio">Cambio</asp:ListItem>
                </asp:DropDownList>
            </div>
            <div class="col-md-6">
                <label for="txtEmpleadoAsignado" class="form-label fw-bold">Empleado Asignado</label>
                <asp:TextBox ID="txtEmpleadoAsignado" runat="server" CssClass="form-control" Placeholder="Ingrese ID o nombre" />
            </div>
        </div>
        <!-- Mensaje resultado -->
        <div class="row mt-3">
            <div class="col-12">
                <asp:Label ID="lblMensaje" runat="server" CssClass="text-success fw-bold"></asp:Label>
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