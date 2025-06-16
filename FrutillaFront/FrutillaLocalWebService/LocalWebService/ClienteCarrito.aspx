<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteCarrito.aspx.cs" Inherits="LocalWebService.ClienteCarrito" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
   
     <h3>Tu Carrito</h3>
    <asp:GridView ID="gvCarrito" runat="server" AutoGenerateColumns="False"
        CssClass="table table-striped">
      <Columns>
           <asp:BoundField DataField="Producto.IdProducto"    HeaderText="Id" />
          <asp:BoundField DataField="Producto.Nombre"    HeaderText="Nombre" />
          <asp:BoundField DataField="Producto.Descripcion" HeaderText="Descripción" />
        <asp:BoundField DataField="Cantidad"            HeaderText="Cantidad" />
          <asp:BoundField DataField="Producto.PrecioUnitario"    HeaderText="Precio" />
        <asp:BoundField DataField="Subtotal"            HeaderText="Subtotal" DataFormatString="{0:C}" />
      </Columns>
    </asp:GridView>


    <div class="row mt-3">
      <div class="col-md-4">
        <label><strong>Subtotal:</strong></label>
        <asp:Label ID="lblSubtotal" runat="server" />
      </div>
      <div class="col-md-4">
        <label><strong>IGV (18%):</strong></label>
        <asp:Label ID="lblIGV" runat="server" />
      </div>
      <div class="col-md-4">
        <label><strong>Total:</strong></label>
        <asp:Label ID="lblTotal" runat="server" />
      </div>
    </div>

    <asp:Button ID="btnPagar" runat="server"
        Text="Proceder al Pago"
        CssClass="btn btn-success mt-4"
        OnClick="btnPagar_Click" />


</asp:Content>
