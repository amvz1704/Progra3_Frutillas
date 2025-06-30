<%@ Page Title="" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="ListaProductosSupervisor.aspx.cs" Inherits="LocalWebService.ListaProductosSupervisor" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Header de sección responsive -->
    <header class="py-3 py-md-4 border-bottom bg-frutilla">
        <div class="container">
            <div class="d-flex flex-column flex-sm-row justify-content-between align-items-start align-items-sm-center">
                <h2 class="m-0 text-black mb-2 mb-sm-0">Gestión de Productos</h2>
                <small class="text-black-50 d-none d-md-block">Panel de administración</small>
            </div>
        </div>
    </header>

    <div class="container mt-4">
        <div style="background-color: #F8FBD9; padding: 15px; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);">
            
            <!-- Sección de agregar producto - responsive -->
            <div class="producto-add mb-4 d-flex flex-column flex-sm-row justify-content-between align-items-start align-items-sm-center gap-2">
                <div>
                    <h4 class="mb-1">Productos Disponibles</h4>
                    <p class="text-muted mb-0 small">Administra el catálogo de productos</p>
                </div>
                <asp:Button ID="btnAgregarProducto" runat="server" Text="+ Agregar Producto" 
                    OnClick="btnAgregarProducto_Click" CssClass="btn btn-frutilla" />
            </div>
            <!-- Barra de búsqueda -->
<div class="form-group mb-2 mb-md-0 d-flex align-items-center flex-grow-1">
    <asp:TextBox
        ID="txtBuscar"
        runat="server"
        CssClass="input-frutilla me-2 flex-grow-1"
        Placeholder="Buscar producto..."
        Style="min-width: 200px;" />
    <button type="submit" class="btn btn-frutilla flex-shrink-0">
        <i class="bi bi-search"></i>
    </button>
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

            <!-- Grid de productos responsive -->
            <div class="row g-3 g-md-4">
                <asp:Repeater ID="rptProductos" runat="server" OnItemCommand="rptProductos_ItemCommand">
                    <ItemTemplate>
                        <!-- Responsive grid: 1 col en xs, 2 cols en sm, 3 cols en md, 4 cols en lg -->
                        <div class="col-12 col-sm-6 col-md-4 col-lg-3">
                            <div class="card-saludable h-100 d-flex flex-column">
                                <!-- Imagen responsive con aspect ratio fijo -->
                                <div class="position-relative" style="padding-top: 60%; overflow: hidden; border-radius: 8px 8px 0 0;">
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

                                    <!-- Información de precio, stock y estado - responsive -->
                                    <div class="mb-3">
                                        <div class="d-flex justify-content-between align-items-center mb-1">
                                            <span class="text-muted small">Precio:</span>
                                            <span class="fw-bold text-success">S/ <%# Eval("precioUnitario", "{0:N2}") %></span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <span class="text-muted small">Stock:</span>
                                            <span class="badge bg-secondary"><%# Eval("stock") %></span>
                                        </div>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <span class="text-muted small">Estado:</span>
                                            <span class="badge bg-secondary"><%# Eval("tipoEstado") %></span>
                                        </div>
                                    </div>

                                    <!-- Botones de acción - responsive -->
                                    <div class="mt-auto">
                                        <div class="d-flex flex-column gap-2">
                                            <asp:Button ID="btnVerMas" runat="server" Text="✏️ Editar" 
                                                CssClass="btn btn-outline-primary btn-sm w-100"
                                                CommandName="VerMas" CommandArgument='<%# Eval("idProducto") %>' />
                                            <asp:Button ID="btnEliminar" runat="server" Text="🗑️ Eliminar" 
                                                CssClass="btn btn-outline-danger btn-sm w-100"
                                                CommandName="Eliminar" CommandArgument='<%# Eval("idProducto") %>'
                                                OnClientClick="return confirm('¿Está seguro de eliminar este producto?');" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </ItemTemplate>
                </asp:Repeater>
            </div>

            <!-- Paginación responsive -->
            <div class="d-flex flex-column flex-sm-row justify-content-center align-items-center mt-4 gap-2">
                <asp:Button ID="btnAnterior" runat="server" Text="‹ Anterior" 
                    OnClick="btnAnterior_Click" CssClass="btn btn-outline-secondary btn-sm" />
                <asp:Label ID="lblPagina" runat="server" CssClass="mx-2 text-muted small" />
                <asp:Button ID="btnSiguiente" runat="server" Text="Siguiente ›" 
                    OnClick="btnSiguiente_Click" CssClass="btn btn-outline-secondary btn-sm" />
            </div>
       

        <!-- Mensaje de error responsive -->
        <div class="mt-3 text-center">
            <asp:Label ID="lblError" runat="server" CssClass="text-danger d-block"></asp:Label>
        </div>

    <!-- Modal para agregar producto - responsive -->
    <div class="modal fade" id="modalAgregarProducto" tabindex="-1" aria-labelledby="modalAgregarProductoLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header bg-frutilla text-black">
                    <h5 class="modal-title" id="modalAgregarProductoLabel">
                        <i class="bi bi-plus-circle me-2"></i>Agregar Nuevo Producto
                    </h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <!-- Formulario responsive -->
                    <div class="row g-3">
                        <div class="col-12 col-md-6">
                            <label for="txtNombre" class="form-label">Nombre del producto <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Ingrese el nombre" />
                            <asp:Label ID="lblNombre" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-6">
                            <label for="txtPrecio" class="form-label">Precio (S/.) <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtPrecio" runat="server" CssClass="form-control decimal-input" TextMode="Number"
                                step="0.01" placeholder="0.00" />
                            <asp:Label ID="lblPrecio" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12">
                            <label for="txtDescripcion" class="form-label">Descripción <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtDescripcion" runat="server" CssClass="form-control"
                                TextMode="MultiLine" Rows="3" placeholder="Ingrese la descripción" />
                            <asp:Label ID="LblDescripcion" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-4">
                            <label for="txtStock" class="form-label">Stock <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtStock" runat="server" CssClass="form-control" TextMode="Number"
                                placeholder="0" />
                            <asp:Label ID="LblStock" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-4">
                            <label for="txtStockMinimo" class="form-label">Stock Mínimo <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtStockMinimo" runat="server" CssClass="form-control" TextMode="Number" 
                                placeholder="0" />
                            <asp:Label ID="LblStockMinimo" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-4">
                            <label for="txtCodigo" class="form-label">Código (6 caracteres A-z 0-9) <span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtCodigo" runat="server" CssClass="form-control text-uppercase" 
                                MaxLength="6" placeholder="ABC123" />
                            <asp:Label ID="LblCodigo" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12">
                            <label for="TipoProducto" class="form-label">Tipo de Producto <span class="text-danger">*</span></label>
                            <asp:DropDownList ID="TipoProducto" CssClass="form-select" runat="server" 
                                onchange="mostrarOpciones(this)">
                                <asp:ListItem Text="🍎 Fruta" Value="F"></asp:ListItem>
                                <asp:ListItem Text="🥤 Bebida" Value="B"></asp:ListItem>
                                <asp:ListItem Text="🍪 Snack" Value="S"></asp:ListItem>
                                <asp:ListItem Text="Otro" Value="P" Selected="True"></asp:ListItem>
                            </asp:DropDownList>
                        </div>
                    </div>

                    <!-- Campos adicionales para Frutas -->
                    <div id="opcionFrutas" style="display: none;" class="mt-4">
                        <div class="border-top pt-3">
                            <h6 class="text-muted mb-3">🍎 Configuración de Fruta</h6>
                            <div class="row g-3">
                                <div class="col-12">
                                    <label for="TxtTipoEnvase" class="form-label">Tipo de Envase <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTipoEnvase" runat="server" CssClass="form-control" 
                                        placeholder="Ej: Bandeja, bolsa, etc."></asp:TextBox>
                                    <asp:Label ID="LblTipoEnvase" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12">
                                    <div class="form-check mb-2">
                                        <asp:CheckBox ID="ChkFrutaRequiereEnvase" runat="server" Text="¿Requiere envase?" 
                                            CssClass="form-check form-check-inline" />
                                    </div>
                                    <div class="form-check mb-2">
                                        <asp:CheckBox ID="ChkFrutaEstaEnvasado" runat="server" Text="¿Está envasado?" 
                                            CssClass="form-check form-check-inline" />
                                    </div>
                                    <div class="form-check ">
                                        <asp:CheckBox ID="ChkFrutaLimpieza" runat="server" Text="¿Requiere limpieza?" 
                                            CssClass="form-check form-check-inline" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Campos adicionales para Bebidas -->
                    <div id="opcionBebidas" style="display: none;" class="mt-4">
                        <div class="border-top pt-3">
                            <h6 class="text-muted mb-3">🥤 Configuración de Bebida</h6>
                            <div class="row g-3">
                                <div class="col-12 col-md-6">
                                    <label for="TxtTamanioOz" class="form-label">Tamaño (Oz) <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTamanioOz" runat="server" CssClass="form-control" 
                                        TextMode="Number" placeholder="16" />
                                    <asp:Label ID="LblTamanioOz" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="TxtTipoBebida" class="form-label">Tipo de Bebida <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTipoBebida" runat="server" CssClass="form-control" 
                                        placeholder="Ej: Jugo, smoothie, etc."></asp:TextBox>
                                    <asp:Label ID="LblTipoBebida" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="TxtTipoBebida" class="form-label">Tipo de Endulzante <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtBebidaEndulzante" runat="server" CssClass="form-control" 
                                        placeholder="Ej: Stevia, azúcar, etc."></asp:TextBox>
                                    <asp:Label ID="LblBebidaEndulzante" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12 col-md-6">
                                    <asp:Label ID="LblTipoLeche" runat="server" Text="Tipo de Leche" CssClass="form-label"></asp:Label>
                                    <asp:DropDownList ID="DropDownList1" CssClass="form-select" runat="server">
                                        <asp:ListItem Text="Sin Lactosa" Value="0"></asp:ListItem>
                                        <asp:ListItem Text="Con Lactosa" Value="1"></asp:ListItem>
                                    </asp:DropDownList>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Campos adicionales para Snacks -->
                    <div id="opcionSnacks" style="display: none;" class="mt-4">
                        <div class="border-top pt-3">
                            <h6 class="text-muted mb-3">🍪 Configuración de Snack</h6>
                            <div class="row g-3">
                                <div class="col-12 col-md-6">
                                    <label for="TxtTipoSnack" class="form-label">Tipo de Snack <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTipoSnack" runat="server" CssClass="form-control" 
                                        placeholder="Ej: Galletas, frutos secos, etc."></asp:TextBox>
                                    <asp:Label ID="LblTipoSnack" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="TxtSnackEnvase" class="form-label">Envase <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtSnackEnvase" runat="server" CssClass="form-control" 
                                        placeholder="Ej: Bolsa, caja, etc."></asp:TextBox>
                                    <asp:Label ID="LblSnackEnvase" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <asp:Button ID="btnGuardarProducto" runat="server" Text="💾 Guardar Producto" 
                        CssClass="btn btn-frutilla me-2" OnClick="btnGuardarProducto_Click" />
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal para editar producto - responsive -->
    <div class="modal fade" id="modalEditarProducto" tabindex="-1" aria-labelledby="modalEditarProductoLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header bg-primary text-black">
                    <h5 class="modal-title" id="modalEditarProductoLabel">
                        <i class="bi bi-pencil-square me-2"></i>Editar Producto
                    </h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <!-- Campos ocultos -->
                    <asp:HiddenField ID="HiddenTipoProductoEdit" runat="server" />
                    <asp:HiddenField ID="HiddenIdProductoEdit" runat="server" />
                    <asp:HiddenField ID="HiddenTipoEstadoProductoEdit" runat="server" />
                    
                    <!-- Formulario de edición responsive -->
                    <div class="row g-3">
                        <div class="col-12 col-md-6">
                            <label for="TxtEditNombre" class="form-label">Nombre del producto <span class="text-danger">*</span></label>
                            <asp:TextBox ID="TxtEditNombre" runat="server" CssClass="form-control" />
                            <asp:Label ID="LblEditNombre" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-3">
                            <label for="TxtEditPrecio" class="form-label">Precio (S/.) <span class="text-danger">*</span></label>
                            <asp:TextBox ID="TxtEditPrecio" runat="server" CssClass="form-control" />
                            <asp:Label ID="LblEditPrecio" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-3">
                            <label for="TxtEditEstado" class="form-label">Estado <span class="text-danger">*</span></label>
                            <asp:DropDownList ID="DdlEditEstado" runat="server" CssClass="form-select">
                                <asp:ListItem Text="DISPONIBLE" Value="DISPONIBLE"></asp:ListItem>
                                <asp:ListItem Text="AGOTADO" Value="AGOTADO"></asp:ListItem>
                            </asp:DropDownList>
                        </div>
                        <div class="col-12">
                            <label for="TxtEditDescripcion" class="form-label">Descripción <span class="text-danger">*</span></label>
                            <asp:TextBox ID="TxtEditDescripcion" runat="server" CssClass="form-control"
                                TextMode="MultiLine" Rows="3" />
                            <asp:Label ID="LblEditDescripcion" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-4">
                            <label for="TxtEditStock" class="form-label">Stock <span class="text-danger">*</span></label>
                            <asp:TextBox ID="TxtEditStock" runat="server" CssClass="form-control" />
                            <asp:Label ID="LblEditStock" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-4">
                            <label for="TxtEditStockMin" class="form-label">Stock Mínimo <span class="text-danger">*</span></label>
                            <asp:TextBox ID="TxtEditStockMin" runat="server" CssClass="form-control"  />
                            <asp:Label ID="LblEditStockMin" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                        <div class="col-12 col-md-4">
                            <label for="TxtEditCodigo" class="form-label">Código (6 letras) <span class="text-danger">*</span></label>
                            <asp:TextBox ID="TxtEditCodigo" runat="server" CssClass="form-control text-uppercase" MaxLength="6" />
                            <asp:Label ID="LblEditCodigo" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                        </div>
                    </div>

                    <!-- Campos adicionales para edición de Frutas -->
                    <div id="opcionFrutasEditar" style="display: none;" class="mt-4">
                        <div class="border-top pt-3">
                            <h6 class="text-muted mb-3">🍎 Configuración de Fruta</h6>
                            <div class="row g-3">
                                <div class="col-12">
                                    <label for="TxtTipoEnvaseEdit" class="form-label">Tipo de envase <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTipoEnvaseEdit" runat="server" CssClass="form-control"></asp:TextBox>
                                    <asp:Label ID="LblTipoEnvaseEdit" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12">
                                    <div class="form-check">
                                        <asp:CheckBox ID="ChkReqEnvaseEdit" runat="server" Text="¿Requiere envase?" 
                                            CssClass="form-check form-check-inline" />
                                    </div>
                                    <div class="form-check">
                                        <asp:CheckBox ID="ChkEnvasadoEdit" runat="server" Text="¿Está envasado?" 
                                            CssClass="form-check form-check-inline" />
                                    </div>
                                    <div class="form-check">
                                        <asp:CheckBox ID="ChkReqLimpiezaEdit" runat="server" Text="¿Requiere limpieza?" 
                                            CssClass="form-check form-check-inline" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Campos adicionales para edición de Bebidas -->
                    <div id="opcionBebidasEditar" style="display: none;" class="mt-4">
                        <div class="border-top pt-3">
                            <h6 class="text-muted mb-3">🥤 Configuración de Bebida</h6>
                            <div class="row g-3">
                                <div class="col-12 col-md-6">
                                    <label for="TxtTamanioEdit" class="form-label">Tamaño(Oz) <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTamanioEdit" runat="server" CssClass="form-control" TextMode="Number"></asp:TextBox>
                                    <asp:Label ID="LblTamanioEdit" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="TxtTipoBebidaEdit" class="form-label">Tipo de bebida <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTipoBebidaEdit" runat="server" CssClass="form-control"></asp:TextBox>
                                    <asp:Label ID="LblTipoBebidaEdit" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="TxtTipoEndulzanteEdit" class="form-label">Tipo de endulzante <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTipoEndulzanteEdit" runat="server" CssClass="form-control"></asp:TextBox>
                                    <asp:Label ID="LblTipoEndulzanteEdit" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="TxtSnackEnvase" class="form-label">Tipo de leche <span class="text-danger">*</span></label>
                                    <asp:DropDownList ID="DDTipoLecheEdit" CssClass="form-select" runat="server">
                                        <asp:ListItem Text="Sin Lactosa" Value="0"></asp:ListItem>
                                        <asp:ListItem Text="Con Lactosa" Value="1"></asp:ListItem>
                                    </asp:DropDownList>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Campos adicionales para edición de Snacks -->
                    <div id="opcionSnacksEditar" style="display: none;" class="mt-4">
                        <div class="border-top pt-3">
                            <h6 class="text-muted mb-3">🍪 Configuración de Snack</h6>
                            <div class="row g-3">
                                <div class="col-12 col-md-6">
                                    <label for="TxtTipoSnackEdit" class="form-label">Tipo de snack <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtTipoSnackEdit" runat="server" CssClass="form-control"></asp:TextBox>
                                    <asp:Label ID="LblTipoSnackEdit" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                                <div class="col-12 col-md-6">
                                    <label for="TxtEnvaseSnackEdit" class="form-label">Envase <span class="text-danger">*</span></label>
                                    <asp:TextBox ID="TxtEnvaseSnackEdit" runat="server" CssClass="form-control"></asp:TextBox>
                                    <asp:Label ID="LblEnvaseSnackEdit" runat="server" CssClass="text-danger mb-3 d-block"></asp:Label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <asp:FileUpload ID="FileUpload" runat="server" class="form-control-file"/>
                    <asp:Button ID="BtnGuardarEdicion" runat="server" Text="💾 Guardar Cambios" 
                        CssClass="btn btn-primary me-2" OnClick="btnGuardarEdicionProducto_Click" />
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>
            </div>
        </div>
    </div>

    <!-- JavaScript mejorado para funcionalidad responsive -->
    <script type="text/javascript">
        function mostrarOpciones(select) {
            var valor = select.value;

            // Ocultar todos los paneles
            document.getElementById('opcionFrutas').style.display = 'none';
            document.getElementById('opcionBebidas').style.display = 'none';
            document.getElementById('opcionSnacks').style.display = 'none';

            // Mostrar el panel correspondiente con animación
            if (valor === "F") {
                document.getElementById('opcionFrutas').style.display = 'block';
            } else if (valor === "B") {
                document.getElementById('opcionBebidas').style.display = 'block';
            } else if (valor === "S") {
                document.getElementById('opcionSnacks').style.display = 'block';
            }
        }

        function mostrarOpcionesEditar(tipo) {
            // Ocultar todos los paneles de edición
            document.getElementById('opcionFrutasEditar').style.display = 'none';
            document.getElementById('opcionBebidasEditar').style.display = 'none';
            document.getElementById('opcionSnacksEditar').style.display = 'none';

            // Mostrar el panel correspondiente
            if (tipo === 'F') {
                document.getElementById('opcionFrutasEditar').style.display = 'block';
            } else if (tipo === 'B') {
                document.getElementById('opcionBebidasEditar').style.display = 'block';
            } else if (tipo === 'S') {
                document.getElementById('opcionSnacksEditar').style.display = 'block';
            }
        }

        // Función para mejorar la experiencia en móviles
        document.addEventListener('DOMContentLoaded', function () {
            // Agregar efectos hover solo en dispositivos no táctiles
            if (!('ontouchstart' in window)) {
                document.querySelectorAll('.card-saludable').forEach(function (card) {
                    card.addEventListener('mouseenter', function () {
                        this.style.transform = 'translateY(-4px)';
                        this.style.transition = 'transform 0.2s ease';
                    });
                    card.addEventListener('mouseleave', function () {
                        this.style.transform = 'translateY(0)';
                    });
                });
            }
        });
    </script>

    <script>
  function validarDecimalInline(input) {
      const regex = /^-?\d+([.,]\d+)?$/;
      const errorSpan = input.nextElementSibling;
      const esValido = regex.test(input.value.trim());
      errorSpan.style.display = esValido ? 'none' : 'inline';
  }
    </script>
    </asp:Content>