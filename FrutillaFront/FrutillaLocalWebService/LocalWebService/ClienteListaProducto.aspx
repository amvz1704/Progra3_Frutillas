<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteListaProducto.aspx.cs" Inherits="LocalWebService.ClienteListaProducto" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    
    <div class="container mt-4">
        <div style="background-color: #F8FBD9; padding: 15px; border-radius: 5px;">
            

            <div class="row">
                <asp:Repeater ID="rptProductos" runat="server">
                    <ItemTemplate>
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <img src="https://via.placeholder.com/300x200?text=Producto" class="card-img-top" alt="Imagen producto" />
                                <div class="card-body">
                                    <h5 class="card-title"><%# Eval("nombre") %></h5>
                                    <p class="card-text">Precio: S/ <%# Eval("precioUnitario", "{0:N2}") %></p>
                                    <p class="card-text">Stock: <%# Eval("stock") %></p>
                                    <asp:Button ID="btnVerMas" runat="server" Text="Detalles" CssClass="btn btn-frutilla"
                                        CommandName="VerMas" CommandArgument='<%# Eval("idProducto") %>' />
                                    <asp:Button ID="btnAgregarCarrito" runat="server" Text="Agregar compra" CssClass="btn btn-frutilla"
                                        CommandName="agregarCarrito" CommandArgument='<%# Eval("idProducto") %>' />

                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
        </div>

        <asp:Label ID="lblError" runat="server" CssClass="text-danger"></asp:Label>
    </div>
</asp:Content>
