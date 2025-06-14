<%@ Page Title="" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="ListaProductosSupervisor.aspx.cs" Inherits="LocalWebService.ListaProductosSupervisor" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Header de sección -->

    <header class="py-2 border-bottom bg-frutilla">
        <div class="container">
            <h2 class="m-0">Local > Productos </h2>
        </div>
    </header>

    <div class="container mt-4">
        <div style="background-color: #F8FBD9; padding: 15px; border-radius: 5px;">
            <div class="producto-add mb-3">
                <asp:Button ID="btnAgregarProducto" runat="server" Text="Agregar Producto" OnClick="btnAgregarProducto_Click"
                    CssClass="btn btn-frutilla" />
            </div>

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
                                    <asp:Button ID="btnVerMas" runat="server" Text="Ver más" CssClass="btn btn-frutilla"
                                        CommandName="VerMas" CommandArgument='<%# Eval("idProducto") %>' />

                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>
        </div>

        <asp:Label ID="lblError" runat="server" CssClass="text-danger"></asp:Label>
    </div>

    <!-- Modal para agregar producto -->

    <div class="modal fade" id="modalAgregarProducto" tabindex="-1" aria-labelledby="modalAgregarProductoLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalAgregarProductoLabel">Agregar Nuevo Producto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control mb-2" placeholder="Nombre del producto" />
                    <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control mb-2" placeholder="Precio" />
                    <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control mb-2" placeholder="Descripcion" />
                    <asp:TextBox ID="txtStock" runat="server" CssClass="form-control mb-2" placeholder="Stock" />
                    <asp:TextBox ID="txtStockMinimo" runat="server" CssClass="form-control mb-2" placeholder="StockMinimo" />
                    <asp:TextBox ID="txtCodigo" runat="server" CssClass="form-control mb-2" placeholder="Codigo 3 Letras" />
                    <asp:DropDownList ID="TipoProducto" CssClass="form-select mb-2" runat="server" onchange="mostrarOpciones(this)">
                        <asp:ListItem Text="Seleccione" Value=""></asp:ListItem>
                        <asp:ListItem Text="Fruta" Value="F"></asp:ListItem>
                        <asp:ListItem Text="Bebida" Value="B"></asp:ListItem>
                        <asp:ListItem Text="Snack" Value="S"></asp:ListItem>
                    </asp:DropDownList>

                    
                    <!-- Campos adicionales para Opción Frutas -->
                    <div id="opcionFrutas" style="display: none;" class="mt-2">
                        <asp:Label ID="LblEnvase" runat="server" Text="Tipo de envase"></asp:Label>
                        <asp:TextBox ID="TxtTipoEnvase" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:Label ID="LblFrutaRequiereEnvase" runat="server" Text="¿Requiere envase?"></asp:Label>
                        <asp:CheckBox ID="ChkFrutaRequiereEnvase" runat="server" />
                        <asp:Label ID="LblEnvasado" runat="server" Text="¿Esta envasado?"></asp:Label>
                        <asp:CheckBox ID="ChkFrutaEstaEnvasado" runat="server" />
                        <asp:Label ID="LblFrutaLimpieza" Text="¿Requiere limpieza?" runat="server" />
                        <asp:CheckBox ID="ChkFrutaLimpieza" runat="server" />
                    </div>

                    <!-- Campos adicionales para Opción Bebidas -->
                    <div id="opcionBebidas" style="display: none;" class="mt-2">
                        <asp:Label ID="LblTamanioOz" runat="server" Text="Tamanio(Oz)"></asp:Label>
                        <asp:TextBox ID="TxtTamanioOz" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:Label ID="LblTipoBebida" runat="server" Text="Tipo de Bebida"></asp:Label>
                        <asp:TextBox ID="TxtTipoBebida" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:Label ID="LblBebidaEndulzante" runat="server" Text="Tipo de Endulzante"></asp:Label>
                        <asp:TextBox ID="TxtBebidaEndulzante" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:Label ID="LblTipoLeche" runat="server" Text="Tipo de Leche"></asp:Label>
                        <asp:DropDownList ID="DropDownList1" CssClass="form-select mb-2" runat="server">
                            <asp:ListItem Text="Sin Lactosa" Value="0"></asp:ListItem>
                            <asp:ListItem Text="Con Lactosa" Value="1"></asp:ListItem>
                        </asp:DropDownList>
                        <asp:Label ID="LblFrutasBebida" runat="server" Text="Frutas"></asp:Label>
                        <asp:CheckBoxList ID="ChkFrutas" runat="server" RepeatLayout="Flow" CssClass="form-check"></asp:CheckBoxList>
                    </div>

                    <!-- Campos adicionales para Opción Snacks -->
                    <div id="opcionSnacks" style="display: none;" class="mt-2">
                        <asp:Label ID="LblTipoSnack" runat="server" Text="Tipo de snack"></asp:Label>
                        <asp:TextBox ID="TxtTipoSnack" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:Label ID="LblSnackEnvase" runat="server" Text="Envase"></asp:Label>
                        <asp:TextBox ID="TxtSnackEnvase" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        
                    </div>

                    <script>
                        function mostrarOpciones(select) {
                            var valor = select.value;

                            // Ocultar todos
                            document.getElementById('opcionFrutas').style.display = 'none';
                            document.getElementById('opcionBebidas').style.display = 'none';
                            document.getElementById('opcionSnacks').style.display = 'none';

                            // Mostrar el que corresponde
                            if (valor === "F") {
                                document.getElementById('opcionFrutas').style.display = 'block';
                            } else if (valor === "B") {
                                document.getElementById('opcionBebidas').style.display = 'block';
                            } else if (valor === "S") {
                                document.getElementById('opcionSnacks').style.display = 'block';
                            }

                        }
                    </script>

                </div>
                <div class="modal-footer">
                    <asp:Button ID="btnGuardarProducto" runat="server" Text="Guardar" CssClass="btn btn-frutilla" OnClick="btnGuardarProducto_Click" />
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>


</asp:Content>