<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteCarrito.aspx.cs" Inherits="LocalWebService.ClienteCarrito" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">

    <header class="py-2 border-bottom bg-frutilla">
        <div class="container">
            <h2 class="m-0">Carrito de compras</h2>
        </div>
    </header>

    <div style="background-color: #DFF0D8; border-radius: 5px;">

        <div class="container m-4">
            <!-- Dropdown Local -->
        <div class="form-group mb-2 mb-md-0 dropdown-saludable">
            <asp:DropDownList
                ID="ddlLocal"
                runat="server"
                CssClass="form-select select-frutilla w-100"
                Style="min-width: 200px;"
                AutoPostBack="true"
                OnSelectedIndexChanged="ddlLocal_SelectedIndexChanged">
            </asp:DropDownList>
        </div>

            

    </div>
    </div>

    <asp:Label ID="lblError" runat="server" ForeColor="Red" EnableViewState="false" />

    <!-- Lista de productos en carrito con fondo verde y tabla blanca -->
     <div class="container mb-4">
        <div style="background-color: #DFF0D8; padding: 15px; border-radius: 5px;">

    <asp:GridView ID="gvCarrito" runat="server" AutoGenerateColumns="False"
        CssClass="table table-striped table-hover table-bordered bg-white"
        OnRowCommand="gvCarrito_RowCommand">
        <Columns>
            <asp:BoundField DataField="Producto.IdProducto" HeaderText="Id" />
            <asp:BoundField DataField="Producto.Nombre" HeaderText="Nombre" />
            <asp:BoundField DataField="Producto.Descripcion" HeaderText="Descripción" />

            <asp:TemplateField HeaderText="Cantidad">
                <ItemTemplate>
                    <div class="d-flex align-items-center">
                        <asp:LinkButton ID="btnMenos" runat="server" Text="−"
                            CommandName="Disminuir"
                            CommandArgument='<%# Container.DataItemIndex %>'
                            CssClass="btn btn-outline-secondary btn-sm me-2" />

                       <asp:Label ID="lblCantidad" runat="server" Text='<%# Eval("cantidad") %>' CssClass="fw-bold small" />

                        <asp:LinkButton ID="btnMas" runat="server" Text="+"
                            CommandName="Aumentar"
                            CommandArgument='<%# Container.DataItemIndex %>'
                            CssClass="btn btn-outline-secondary btn-sm ms-2" />
                    </div>
                </ItemTemplate>
            </asp:TemplateField>

            <asp:BoundField DataField="Producto.PrecioUnitario" HeaderText="Precio" DataFormatString="{0:F2}" HtmlEncode="false" />
            <asp:BoundField DataField="Subtotal" HeaderText="Subtotal" DataFormatString="{0:C}" />

        <asp:TemplateField HeaderText="Acciones">
            <ItemTemplate>
                <asp:LinkButton ID="btnEliminar" runat="server"
                    Text="Eliminar"
                    CommandName="Eliminar"
                    CommandArgument='<%# Container.DataItemIndex %>'
                    CssClass="btn btn-danger btn-sm" >
                    <i class="bi bi-trash3" title="Eliminar"></i>
                 </asp:LinkButton>
            </ItemTemplate>
        </asp:TemplateField>
    </Columns>
</asp:GridView>

     </div>
    </div>

     <!-- Totales alineados más compactos -->
    <div class="container">
        <div class="row mt-3">
            <div class="col-md-4 offset-md-8">
                <div class="mb-1 d-flex justify-content-end align-items-center">
                    <label class="fw-bold me-2 mb-0">Subtotal:</label>
                    <asp:Label ID="lblSubtotal" runat="server" CssClass="text-success fw-bold small mb-0" />
                </div>
                <div class="mb-1 d-flex justify-content-end align-items-center">
                    <label class="fw-bold me-2 mb-0">IGV (18%):</label>
                    <asp:Label ID="lblIGV" runat="server" CssClass="text-success fw-bold small mb-0" />
                </div>
                <div class="mb-2 d-flex justify-content-end align-items-center">
                    <label class="fw-bold me-2 mb-0">Total:</label>
                    <asp:Label ID="lblTotal" runat="server" CssClass="text-success fw-bold small mb-0" />
                </div>
                <div class="text-end">
                    <asp:Button ID="btnPagar" runat="server"
                        Text="Proceder al Pago"
                        CssClass="btn btn-success px-4"
                        OnClick="btnPagar_Click" />
                </div>
            </div>

            </div>
        </div>

</asp:Content>
