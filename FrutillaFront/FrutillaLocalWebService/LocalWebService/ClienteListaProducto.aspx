<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteListaProducto.aspx.cs" Inherits="LocalWebService.ClienteListaProducto" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    
    <div class="container py-3 form-in-line">
      <div class="d-flex align-items-center">
        <!-- Barra de búsqueda -->
          <div class="form-group mb-2">
        <div class="search-bar flex-grow-1 me-3 d-flex align-items-center">
          <asp:TextBox
            ID="txtBuscar"
            runat="server"
            CssClass="form-control"
            Placeholder="Buscar producto..."
              Width="300px"
          />
          <button type="submit" class="btn btn-search">
            <i class="bi bi-search"></i><!-- ícono de Bootstrap Icons -->
          </button>
        </div>
              </div>

        <!-- Dropdown Local -->
          <div class="form-group mx-sm-3 mb-2">
        <asp:DropDownList
          ID="ddlLocal"
          runat="server"
          CssClass="form-select"
            Width="120px"
        >
          <asp:ListItem Value="0">Local</asp:ListItem>
          <asp:ListItem Value="1">Sucursal A</asp:ListItem>
          <asp:ListItem Value="2">Sucursal B</asp:ListItem>
        </asp:DropDownList>
              </div>
          <!-- Filtros como tags -->
          <div class="form-group mb-2">
        <asp:Panel ID="filterTags" runat="server" CssClass="filter-tags mt-2">
            <asp:LinkButton
              ID="btnTodos"
              runat="server"
              CssClass="btn btn-outline-light active"
              CommandArgument="Todos"
              OnClick="Filtro_Click"
            >Todos</asp:LinkButton>

            <asp:LinkButton
              ID="btnSnack"
              runat="server"
              CssClass="btn btn-outline-light"
              CommandArgument="Snack"
              OnClick="Filtro_Click"
            >Snack</asp:LinkButton>
            <asp:LinkButton
              ID="btnFruta"
              runat="server"
              CssClass="btn btn-outline-light"
              CommandArgument="Fruta"
              OnClick="Filtro_Click"
            >Fruta</asp:LinkButton>
            <asp:LinkButton
              ID="btnBebidas"
              runat="server"
              CssClass="btn btn-outline-light"
              CommandArgument="Bebidas"
              OnClick="Filtro_Click"
            >Bebidas</asp:LinkButton>
  
        </asp:Panel>

      </div>
          </div>

    </div>



    <div class="container mt-4">
        <div style="background-color: #F8FBD9; padding: 15px; border-radius: 5px;">
            

            <div class="row">
                <asp:Repeater ID="rptProductos" runat="server" OnItemCommand="rptProductos_ItemCommand">
                    <ItemTemplate>
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <img src="https://via.placeholder.com/300x200?text=Producto" class="card-img-top" alt="Imagen producto" />
                                <div class="card-body">
                                    <h5 class="card-title"><%# Eval("nombre") %></h5>
                                    <p class="card-text">Precio: S/ <%# Eval("precioUnitario", "{0:N2}") %></p>
                                    <p class="card-text">Stock: <%# Eval("stock") %></p>
                                    <asp:Button ID="btnVerMas" runat="server" Text="Detalles" CssClass="btn btn-frutilla"
                                        CommandName="VerMas" CommandArgument='<%# Eval("idProducto") %>'/>
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
