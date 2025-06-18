<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteListaProducto.aspx.cs" Inherits="LocalWebService.ClienteListaProducto" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">

    <div class="container py-3 form-in-line">
        <div class="d-flex align-items-center">
            <!-- Barra de búsqueda -->
            <div class="form-group mb-2 d-flex align-items-center">
                <asp:TextBox
                    ID="txtBuscar"
                    runat="server"
                    CssClass="input-frutilla me-2"
                    Placeholder="Buscar producto..."
                    Width="300px" />
                <button type="submit" class="btn btn-frutilla">
                    <i class="bi bi-search"></i>
                </button>
            </div>

            <!-- Dropdown Local -->
            <div class="form-group mx-sm-3 mb-2 dropdown-saludable">
                <asp:DropDownList
                    ID="ddlLocal"
                    runat="server"
                    CssClass="form-select select-frutilla"
                    Width="200px"
                    AutoPostBack="true"
                    OnSelectedIndexChanged="ddlLocal_SelectedIndexChanged">
                </asp:DropDownList>
            </div>
        </div>
        <asp:Panel ID="filterTags" runat="server" CssClass="mt-3">
            <asp:LinkButton ID="btnTodos" runat="server"
                CssClass="btn btn-frutilla me-2"
                CommandArgument="Todos" OnClick="Filtro_Click">Todos</asp:LinkButton>

            <asp:LinkButton ID="btnSnack" runat="server"
                CssClass="btn btn-frutilla me-2"
                CommandArgument="Snack" OnClick="Filtro_Click">Snack</asp:LinkButton>

            <asp:LinkButton ID="btnFruta" runat="server"
                CssClass="btn btn-frutilla me-2"
                CommandArgument="Fruta" OnClick="Filtro_Click">Fruta</asp:LinkButton>

            <asp:LinkButton ID="btnBebidas" runat="server"
                CssClass="btn btn-frutilla"
                CommandArgument="Bebidas" OnClick="Filtro_Click">Bebidas</asp:LinkButton>
        </asp:Panel>


    </div>


    <div class="container mt-4">
        <div style="background-color: #F8FBD9; padding: 15px; border-radius: 5px;">


            <div class="row">
                <asp:Repeater ID="rptProductos" runat="server" OnItemCommand="rptProductos_ItemCommand">
                    <ItemTemplate>
                        <div class="col-md-4 mb-4">
                            <div class="card-producto h-100">
                                <img src='<%# ObtenerImagenPorTipo(Eval("idProducto")) %>' class="card-img-top" alt="Imagen producto" />
                                <div class="card-body">
                                    <h5 class="card-title"><%# Eval("nombre") %></h5>
                                    <p class="card-text">Precio: S/ <%# Eval("precioUnitario", "{0:N2}") %></p>
                                    <p class="card-text">Stock: <%# Eval("stock") %></p>

                                    <asp:Button ID="btnVerMas" runat="server" Text="Detalles"
                                        CssClass="btn btn-frutilla me-2"
                                        CommandName="VerMas"
                                        CommandArgument='<%# Eval("idProducto") %>' />

                                    <asp:Button ID="btnAgregarCarrito" runat="server" Text="Agregar compra"
                                        CssClass="btn btn-frutilla"
                                        CommandName="agregarCarrito"
                                        CommandArgument='<%# Eval("idProducto") %>' />
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>

                </asp:Repeater>
            </div>
        </div>

        <asp:Label ID="lblError" runat="server" CssClass="mb-2"></asp:Label>
    </div>
</asp:Content>
