<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteListaProducto.aspx.cs" Inherits="LocalWebService.ClienteListaProducto" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">

    <div class="container py-3 form-in-line">
        <!-- Sección de búsqueda y filtros - Completamente responsive -->
        <div class="d-flex flex-column flex-md-row align-items-stretch align-items-md-center gap-2 gap-md-0">

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

        <!-- Panel de filtros de productos - Responsive con flex-wrap -->
        <asp:Panel ID="FiltroProductos" runat="server" CssClass="mt-3">
            <div class="d-flex flex-wrap gap-2 justify-content-center justify-content-sm-start">
                <asp:Button ID="btnTodos" runat="server"
                    CssClass="btn btn-outline-secondary flex-fill flex-sm-grow-0"
                    CommandArgument="T" OnClick="FiltroProductos_Click" Text="Todos" />

                <asp:Button ID="btnSnack" runat="server"
                    CssClass="btn btn-outline-secondary flex-fill flex-sm-grow-0"
                    CommandArgument="S" OnClick="FiltroProductos_Click" Text="Snack" />

                <asp:Button ID="btnFruta" runat="server"
                    CssClass="btn btn-outline-secondary flex-fill flex-sm-grow-0"
                    CommandArgument="F" OnClick="FiltroProductos_Click" Text="Fruta" />

                <asp:Button ID="btnBebidas" runat="server"
                    CssClass="btn btn-outline-secondary flex-fill flex-sm-grow-0"
                    CommandArgument="B" OnClick="FiltroProductos_Click" Text="Bebidas" />

                <asp:Button ID="btnOtros" runat="server"
                    CssClass="btn btn-outline-secondary flex-fill flex-sm-grow-0"
                    CommandArgument="P" OnClick="FiltroProductos_Click" Text="Otros" />
            </div>
        </asp:Panel>
    </div>

    <!-- Contenedor principal de productos -->
    <div class="container mt-4">
        <div style="background-color: #F8FBD9; padding: 15px; border-radius: 5px;">
            <!-- Grid de productos con breakpoints mejorados -->
            <div class="row g-3 g-md-4">
                <asp:Repeater ID="rptProductos" runat="server" OnItemCommand="rptProductos_ItemCommand">
                    <ItemTemplate>
                        <!-- Responsive grid: 1 col en xs, 2 cols en sm, 3 cols en lg, 4 cols en xl -->
                        <div class="col-12 col-sm-6 col-lg-4 col-xl-3">
                            <div class="card-saludable h-100 d-flex flex-column">
                                <!-- Imagen responsive -->
                                <div class="position-relative" style="padding-top: 60%; overflow: hidden;">
                                    <img src='<%# ObtenerImagenPorTipo(Eval("idProducto")) %>' 
                                         class="position-absolute top-0 start-0 w-100 h-100" 
                                         style="object-fit: cover;" 
                                         alt="<%# Eval("nombre") %>" />
                                </div>
                                
                                <!-- Contenido de la tarjeta -->
                                <div class="card-body d-flex flex-column flex-grow-1 p-3">
                                    <h5 class="card-title text-truncate mb-2" title="<%# Eval("nombre") %>">
                                        <%# Eval("nombre") %>
                                    </h5>
                                    
                                    <!-- Información de precio y stock - responsive -->
                                    <div class="d-flex flex-column flex-sm-row justify-content-between align-items-start align-items-sm-center mb-3 gap-1">
                                        <p class="precio mb-0 fw-bold text-success">
                                            Precio: S/ <%# Eval("precioUnitario", "{0:N2}") %>
                                        </p>
                                        <p class="stock mb-0 text-muted">
                                            <small>Stock: <%# Eval("stock") %></small>
                                        </p>
                                    </div>
                                    
                                    <!-- Botones - siempre al final de la tarjeta -->
                                    <div class="mt-auto">
                                        <div class="d-flex flex-column flex-sm-row gap-2">
                                            <asp:Button ID="btnVerMas" runat="server" Text="Detalles"
                                                CssClass="btn btn-outline-primary flex-fill btn-sm"
                                                CommandName="VerMas"
                                                CommandArgument='<%# Eval("idProducto") %>' />
                                            <asp:Button ID="btnAgregarCarrito" runat="server" Text="Agregar"
                                                CssClass="btn btn-frutilla flex-fill btn-sm"
                                                CommandName="agregarCarrito"
                                                CommandArgument='<%# Eval("idProducto") %>' />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
        </div>

        <!-- Mensaje de error responsive -->
        <div class="mt-3 text-center">
            <asp:Label ID="lblError" runat="server" CssClass="label-importante d-block"></asp:Label>
        </div>
    </div>
</asp:Content>