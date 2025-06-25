<%@ Page Title="VistaLocal" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="LocalSupervisor.aspx.cs" Inherits="LocalWebService.LocalSupervisor" %>


<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>

<asp:Content ID="MainContent" ContentPlaceHolderID="MainContent" runat="server">

    <!-- Header -->
    <header class="bg-frutilla py-2 border-bottom">
        <div class="container">
            <h2 class="m-0">Local</h2>
        </div>
    </header>

    <!-- Panel de Información del Local -->
    <asp:Panel ID="pnlLocal" runat="server" CssClass="card mb-4 shadow-sm">
        <div class="row g-0">
            <!-- Imagen -->
            <div class="col-md-4">
                <div class="px-0 px-md-3 px-lg-5">
                    <asp:Image ID="imgLocal" ImageUrl="~/Public/images/fruteria-letras.jpg" runat="server" CssClass="img-fluid rounded-start w-100" />
                </div>
            </div>

            <!-- Datos -->
            <div class="col-md-6">
                <div class="card-body">
                    <h3 class="card-title">
                        <asp:Label ID="lblNombre" runat="server" Text="—" />
                    </h3>
                    <p class="card-text mb-1">
                        <strong>Dirección:</strong>
                        <asp:Label ID="lblDireccion" runat="server" Text="—" />
                    </p>
                    <p class="card-text mb-1">
                        <strong>Descripción:</strong>
                        <asp:Label ID="lblDescripcion" runat="server" Text="—" />
                    </p>
                    <p class="card-text mb-3">
                        <strong>Teléfono:</strong>
                        <asp:Label ID="lblTelefono" runat="server" Text="—" />
                    </p>

                    <!-- Botón Editar -->
                    <div class="mb-5">
                        <asp:LinkButton ID="btnEditar" runat="server" OnClick="btnEditarLocal_Click" CssClass="btn-frutilla-editar">
              ✎ Editar
                        </asp:LinkButton>
                    </div>
                </div>
            </div>
        </div>
    </asp:Panel>

    <!-- Módulos de Administración -->
    <div class="container">
        <h5 class="mt-5 mb-3">Elige el módulo de administración:</h5>
    </div>

    <div class="row g-4 justify-content-center">
        <!-- Empleados -->
        <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-4">
            <div class="card h-100">
                <div class="card-header position-relative bg-transparent border-0">
                    <img src="/Public/images/empleadosImagen.png" class="position-absolute top-0 end-0 m-2" alt="Empleados" height="40" />
                </div>
                <div class="card-body d-flex flex-column text-center px-3 py-4">
                    <h6 class="card-title fw-bold mb-3">Administrar Empleados</h6>
                    <p class="card-text flex-grow-1">Desde aquí puedes ver y editar a los empleados del local.</p>
                    <asp:Button ID="AdminEmpleados" runat="server" Text="Ir" CssClass="btn-frutilla" OnClick="BtnAdminEmpleados" />
                </div>
            </div>
        </div>

        <!-- Productos -->
        <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-4">
            <div class="card h-100">
                <div class="card-header position-relative bg-transparent border-0">
                    <img src="/Public/images/productosImagen.png" class="position-absolute top-0 end-0 m-2" alt="Productos" height="40" />
                </div>
                <div class="card-body d-flex flex-column text-center px-3 py-4">
                    <h6 class="card-title fw-bold mb-3">Administrar Productos</h6>
                    <p class="card-text flex-grow-1">Desde aquí puedes ver y editar a los productos del inventario del local.</p>
                    <asp:Button ID="AdminProductos" runat="server" Text="Ir" CssClass="btn-frutilla" OnClick="BtnAdminProductos" />
                </div>
            </div>
        </div>

        <!-- Ventas -->
        <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-4">
            <div class="card h-100">
                <div class="card-header position-relative bg-transparent border-0">
                    <img src="/Public/images/ventasImagen.png" class="position-absolute top-0 end-0 m-2" alt="Ventas" height="40" />
                </div>
                <div class="card-body d-flex flex-column text-center px-3 py-4">
                    <h6 class="card-title fw-bold mb-3">Administrar Ventas</h6>
                    <p class="card-text flex-grow-1">Desde aquí puedes ver y editar los pedidos recibidos del inventario del local.</p>
                    <asp:Button ID="AdminVentas" runat="server" Text="Ir" CssClass="btn-frutilla" OnClick="BtnAdminVentas" />
                </div>
            </div>
        </div>
    </div>

    <!-- Mensaje de error (oculto por defecto) -->
    <asp:Label ID="lblError" runat="server" CssClass="text-danger" Visible="false" />

    <!-- Campo oculto para ID -->
    <asp:HiddenField ID="hfLocalId" runat="server" />

    <!-- Modal de Edición del Local -->
    <asp:Panel ID="pnlModalLocal" runat="server" CssClass="modal fade" TabIndex="-1" Role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
            <div class="modal-content">

                <!-- Encabezado -->
                <div class="modal-header bg-secondary text-white">
                    <h5 id="lblTituloModalLocal" class="modal-title">Editar Local</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>

                <!-- Cuerpo -->
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="txtNombreLocal" class="form-label">Nombre<span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtNombreLocal" runat="server" CssClass="form-control" />
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="txtTelefonoLocal" class="form-label">Teléfono<span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtTelefonoLocal" runat="server" CssClass="form-control" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="txtDireccionLocal" class="form-label">Dirección<span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtDireccionLocal" runat="server" CssClass="form-control" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="txtDescripcionLocal" class="form-label">Descripción<span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtDescripcionLocal" runat="server" TextMode="MultiLine" Rows="2" CssClass="form-control" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="ddlEstadoLocal" class="form-label">Estado<span class="text-danger">*</span></label>
                            <asp:DropDownList ID="ddlEstadoLocal" runat="server" CssClass="form-select">
                                <asp:ListItem Value="true">Activo</asp:ListItem>
                                <asp:ListItem Value="false">Inactivo</asp:ListItem>
                            </asp:DropDownList>
                        </div>
                    </div>
                </div>

                <!-- Pie -->
                <div class="modal-footer">
                    <asp:LinkButton ID="btnGuardarLocalModal" runat="server" CssClass="btn btn-success" Text="Guardar" OnClick="btnGuardarLocalModal_Click" />
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                </div>

            </div>
        </div>
    </asp:Panel>

</asp:Content>
