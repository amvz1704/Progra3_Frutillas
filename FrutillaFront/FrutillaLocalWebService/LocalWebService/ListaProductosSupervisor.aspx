<%@ Page Title="" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="ListaProductosSupervisor.aspx.cs" Inherits="LocalWebService.ListaProductosSupervisor" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Header de sección -->

    <header class="py-2 border-bottom bg-frutilla">
        <div class="container">
            <h2 class="m-0"> Productos </h2>
        </div>
    </header>

    <div class="container mt-4">
        <div style="background-color: #F8FBD9; padding: 15px; border-radius: 5px;">
            <div class="producto-add mb-3">
                <asp:Button ID="btnAgregarProducto" runat="server" Text="Agregar Producto" OnClick="btnAgregarProducto_Click"
                    CssClass="btn btn-frutilla" />
            </div>

            <div class="row">
                <asp:Repeater ID="rptProductos" runat="server" OnItemCommand="rptProductos_ItemCommand">
                    <ItemTemplate>
                        <div class="col-md-4 mb-4">
                            <div class="card h-100">
                                <img src='<%# ObtenerImagenPorTipo(Eval("idProducto")) %>' class="card-img-top" alt="Imagen producto" />
                                <div class="card-body">
                                    <h5 class="card-title"><%# Eval("nombre") %></h5>
                                    <p class="card-text">Precio: S/ <%# Eval("precioUnitario", "{0:N2}") %></p>
                                    <p class="card-text">Stock: <%# Eval("stock") %></p>
                                    <asp:Button ID="btnVerMas" runat="server" Text="Ver más" CssClass="btn btn-frutilla"
                                        CommandName="VerMas" CommandArgument='<%# Eval("idProducto") %>' />
                                    <asp:Button ID="btnEliminar" runat="server" Text="Eliminar" CssClass="btn btn-danger"
                                        CommandName="Eliminar" CommandArgument='<%# Eval("idProducto") %>' />
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
                <!-- Paginación -->
                <div class="text-center mt-4">
                    <asp:Button ID="Button1" runat="server" Text="&laquo; Anterior" OnClick="btnAnterior_Click" />
                    <asp:Label ID="Label1" runat="server" CssClass="mx-2" />
                    <asp:Button ID="Button2" runat="server" Text="Siguiente &raquo;" OnClick="btnSiguiente_Click" />
                </div>
            </div>

            <!-- Paginación -->
            <div class="text-center mt-4">
                <asp:Button ID="btnAnterior" runat="server" Text="&laquo; Anterior" OnClick="btnAnterior_Click" />
                <asp:Label ID="lblPagina" runat="server" CssClass="mx-2" />
                <asp:Button ID="btnSiguiente" runat="server" Text="Siguiente &raquo;" OnClick="btnSiguiente_Click" />
            </div>

        </div>

        <asp:Label ID="lblError" runat="server" CssClass="text-danger"></asp:Label>
    </div>

    <!-- Modal para agregar producto -->

    <div class="modal fade" id="modalAgregarProducto" tabindex="-1" aria-labelledby="modalAgregarProductoLabel" aria-hidden="true" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalAgregarProductoLabel">Agregar Nuevo Producto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <asp:Label ID="Nombre" runat="server" Text="Nombre del producto"></asp:Label>
                    <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control mb-2" />
                    <asp:Label ID="Precio" runat="server" Text="Precio (S/.)"></asp:Label>
                    <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control mb-2" TextMode="Number" />
                    <asp:Label ID="Descripcion" runat="server" Text="Descripcion"></asp:Label>
                    <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control mb-2" />
                    <asp:Label ID="Stock" runat="server" Text="Stock"></asp:Label>
                    <asp:TextBox ID="txtStock" runat="server" CssClass="form-control mb-2" />
                    <asp:Label ID="StockMinimo" runat="server" Text="Stock Minimo"></asp:Label>
                    <asp:TextBox ID="txtStockMinimo" runat="server" CssClass="form-control mb-2" TextMode="Number" />
                    <asp:Label ID="Codigo" runat="server" Text="Codigo 3 letras"></asp:Label>
                    <asp:TextBox ID="txtCodigo" runat="server" CssClass="form-control mb-2" MaxLength="3" />
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
                        <asp:CheckBox ID="ChkFrutaRequiereEnvase" runat="server" Text="¿Requiere envase?" />
                        <br />
                        <asp:CheckBox ID="ChkFrutaEstaEnvasado" runat="server" Text="¿Está envasado?" />
                        <br />
                        <asp:CheckBox ID="ChkFrutaLimpieza" runat="server" Text="¿Requiere limpieza?" />

                    </div>

                    <!-- Campos adicionales para Opción Bebidas -->
                    <div id="opcionBebidas" style="display: none;" class="mt-2">
                        <asp:Label ID="LblTamanioOz" runat="server" Text="Tamanio(Oz)"></asp:Label>
                        <asp:TextBox ID="TxtTamanioOz" runat="server" CssClass="form-control mb-2" TextMode="Number"></asp:TextBox>
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


    <!-- Modal para editar producto -->

    <div class="modal fade" id="modalEditarProducto" tabindex="-1" aria-labelledby="modalEditarProductoLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modalEditarProductoLabel">Editar Producto</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <asp:HiddenField ID="HiddenTipoProductoEdit" runat="server" />
                    <asp:HiddenField ID="HiddenIdProductoEdit" runat="server" />
                    <asp:HiddenField ID="HiddenTipoEstadoProductoEdit" runat="server" />
                    <asp:Label ID="LblEditNombre" runat="server" Text="Nombre del producto"></asp:Label>
                    <asp:TextBox ID="TxtEditNombre" runat="server" CssClass="form-control mb-2" />
                    <asp:Label ID="LblEditPrecio" runat="server" Text="Precio (S/.)"></asp:Label>
                    <asp:TextBox ID="TxtEditPrecio" runat="server" CssClass="form-control mb-2" />
                    <asp:Label ID="LblEditDescripcion" runat="server" Text="Descripcion"></asp:Label>
                    <asp:TextBox ID="TxtEditDescripcion" runat="server" CssClass="form-control mb-2" />
                    <asp:Label ID="LblEditStock" runat="server" Text="Stock"></asp:Label>
                    <asp:TextBox ID="TxtEditStock" runat="server" CssClass="form-control mb-2" TextMode="Number" />
                    <asp:Label ID="LblEditStockMin" runat="server" Text="Stock Minimo"></asp:Label>
                    <asp:TextBox ID="TxtEditStockMin" runat="server" CssClass="form-control mb-2" TextMode="Number" />
                    <asp:Label ID="LblEditCodigo" runat="server" Text="Codigo 3 letras"></asp:Label>
                    <asp:TextBox ID="TxtEditCodigo" runat="server" CssClass="form-control mb-2" MaxLength="3" />


                    <!-- Campos adicionales para Opción Frutas -->
                    <div id="opcionFrutasEditar" style="display: none;" class="mt-2">
                        <asp:Label ID="LblTipoEnvasoEdit" runat="server" Text="Tipo de envase"></asp:Label>
                        <asp:TextBox ID="TxtTipoEnvaseEdit" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:CheckBox ID="ChkReqEnvaseEdit" runat="server" Text="¿Requiere envase?" />
                        <br />
                        <asp:CheckBox ID="ChkEnvasadoEdit" runat="server" Text="¿Está envasado?" />
                        <br />
                        <asp:CheckBox ID="ChkReqLimpiezaEdit" runat="server" Text="¿Requiere limpieza?" />

                    </div>
                    <!-- Campos adicionales para Opción Bebidas -->
                    <div id="opcionBebidasEditar" style="display: none;" class="mt-2">
                        <asp:Label ID="LblTamanioEdit" runat="server" Text="Tamanio(Oz)"></asp:Label>
                        <asp:TextBox ID="TxtTamanioEdit" runat="server" CssClass="form-control mb-2" TextMode="Number"></asp:TextBox>
                        <asp:Label ID="LblTipoBebidaEdit" runat="server" Text="Tipo de Bebida"></asp:Label>
                        <asp:TextBox ID="TxtTipoBebidaEdit" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:Label ID="LblTipoEndulzanteEdit" runat="server" Text="Tipo de Endulzante"></asp:Label>
                        <asp:TextBox ID="TxtTipoEndulzanteEdit" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:Label ID="LblTipoLecheEdit" runat="server" Text="Tipo de Leche"></asp:Label>
                        <asp:DropDownList ID="DDTipoLecheEdit" CssClass="form-select mb-2" runat="server">
                            <asp:ListItem Text="Sin Lactosa" Value="0"></asp:ListItem>
                            <asp:ListItem Text="Con Lactosa" Value="1"></asp:ListItem>
                        </asp:DropDownList>
                        <asp:Label ID="LblFrutasBebEdit" runat="server" Text="Frutas"></asp:Label>
                        <asp:CheckBoxList ID="ChkFrutasBebEdit" runat="server" RepeatLayout="Flow" CssClass="form-check"></asp:CheckBoxList>
                    </div>
                    <!-- Campos adicionales para Opción Snacks -->
                    <div id="opcionSnacksEditar" style="display: none;" class="mt-2">
                        <asp:Label ID="LblTipoSnackEdit" runat="server" Text="Tipo de snack"></asp:Label>
                        <asp:TextBox ID="TxtTipoSnackEdit" runat="server" CssClass="form-control mb-2"></asp:TextBox>
                        <asp:Label ID="LblEnvaseSnackEdit" runat="server" Text="Envase"></asp:Label>
                        <asp:TextBox ID="TxtEnvaseSnackEdit" runat="server" CssClass="form-control mb-2"></asp:TextBox>

                    </div>

                    <script type="text/javascript">
                        function mostrarOpcionesEditar(tipo) {
                            document.getElementById('opcionFrutasEditar').style.display = 'none';
                            document.getElementById('opcionBebidasEditar').style.display = 'none';
                            document.getElementById('opcionSnacksEditar').style.display = 'none';

                            if (tipo === 'F') {
                                document.getElementById('opcionFrutasEditar').style.display = 'block';
                            } else if (tipo === 'B') {
                                document.getElementById('opcionBebidasEditar').style.display = 'block';
                            } else if (tipo === 'S') {
                                document.getElementById('opcionSnacksEditar').style.display = 'block';
                            }
                        }
</script>
                    <!-- Modal para agregar producto -->
                </div>
                <div class="modal-footer">
                    <asp:Button ID="BtnGuardarEdicion" runat="server" Text="Guardar" CssClass="btn btn-frutilla" OnClick="btnGuardarEdicionProducto_Click" />
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
