<%@ Page Title="VistaLocal" Language="C#" MasterPageFile="~/MainLayout.Master" AutoEventWireup="true" CodeBehind="LocalSupervisor.aspx.cs" Inherits="LocalWebService.LocalSupervisor" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    
</asp:Content>

<asp:Content ID="MainContent" ContentPlaceHolderID="MainContent" runat="server">
      <!-- 2) Header de sección -->
    <header class="bg-frutilla py-2 border-bottom">
      <div class="container">
        <h2 class="m-0"> Local </h2>
      </div>
    </header>

      <asp:Panel ID="pnlLocal" runat="server" CssClass="card mb-4 shadow-sm">
        <div class="row g-0">
          <!-- Imagen -->
          <div class="col-md-4">
            <asp:Image ID="imgLocal" runat="server" CssClass="img-fluid rounded-start" />
          </div>
          <!-- Datos -->
          <div class="col-md-8">
            <div class="card-body">
              <h3 class="card-title">
                <asp:Label ID="lblNombre" runat="server" Text="—"></asp:Label>
              </h3>
              <p class="card-text mb-1">
                <strong>Dirección:</strong>
                <asp:Label ID="lblDireccion" runat="server" Text="—"></asp:Label>
              </p>
              <p class="card-text mb-1">
                <strong>Descripción:</strong>
                <asp:Label ID="lblDescripcion" runat="server" Text="—"></asp:Label>
              </p>
              
              <p class="card-text mb-3">
                <strong>Teléfono:</strong>
                <asp:Label ID="lblTelefono" runat="server" Text="—"></asp:Label>
              </p>

                 <div class="mb-5">
                <asp:Button ID="btnEditarLocal" runat="server"
                            CssClass="btn-frutilla"
                            Text="Editar Local"
                            OnClick="btnEditarLocal_Click" />
                
            </div>

            </div>
          </div>
        </div>
      </asp:Panel>



    <!-- 4) Grid de Cards para los módulos de administración -->
    <h5 class="mt-5 mb-3">Elige el módulo de administración:</h5>
    <div class="row g-4 justify-content-center">
        <!-- Empleados -->
        <div class="col-6 col-md-3">
            <div class="card h-100 text-center">
                <div class="position-relative">
                    <img src="/Public/images/empleadosImagen.png" class="position-absolute top-0 end-0 m-2" alt="Empleados" height="40" />
                </div>
                <div class="card-body">
                    <h6 class="card-title">Administrar Empleados</h6>
                    <p style="text-align: center;"> </p>
                    <p style="text-align: center;">Desde aquí puedes ver y editar a los empleados del local.</p>
                    <asp:Button ID="AdminEmpleados" OnClick="BtnAdminEmpleados" runat="server"
                        Text="Ir" CssClass="btn-frutilla" />
                </div>
            </div>
        </div>
        <!-- Productos -->
        <div class="col-6 col-md-3">
            <div class="card h-100 text-center">
                <div class="position-relative">
                    <img src="/Public/images/productosImagen.png" class="position-absolute top-0 end-0 m-2" alt="Empleados" height="40" />
                </div>
                <div class="card-body">
                    <h6 class="card-title">Administrar Productos</h6>
                    <p style="text-align: center;"> </p>
                    <p style="text-align: center;">Desde aquí puedes ver y editar a los productos del inventario del local.</p>
                    <asp:Button ID="AdminProductos" OnClick="BtnAdminProductos" runat="server"
                        Text="Ir" CssClass="btn-frutilla" />
                </div>
            </div>
        </div>
        <!-- Ventas -->
        <div class="col-6 col-md-3">
            <div class="card h-100 text-center">
                <div class="position-relative">
                    <img src="/Public/images/ventasImagen.png" class="position-absolute top-0 end-0 m-2" alt="Empleados" height="40" />
                </div>
                <div class="card-body">
                    <h6 class="card-title">Administrar Ventas</h6>
                    <p style="text-align: center;"> </p>
                    <p style="text-align: center;">Desde aquí puedes ver y editar a los pedidos recibidos del inventario del local.</p>
                    <asp:Button ID="AdminVentas" OnClick="BtnAdminVentas" runat="server"
                        Text="Ir" CssClass="btn-frutilla" />
                </div>
            </div>
        </div>
    </div>
    </>

    <!-- ======================================
        4) DEFINICIÓN DEL MODAL (inicialmente oculto)
        ====================================== -->
     <asp:Label 
 ID="lblError" 
 runat="server" 
 CssClass="text-danger" 
 Visible="false" />
    <asp:Panel ID="pnlModalLocal" runat="server" CssClass="modal fade" Style="display: none;" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
            <div class="modal-content">
                <!-- Encabezado del modal -->
                <div class="modal-header bg-secondary text-white">
                    <h5 id="lblTituloModalLocal" class="modal-title">Editar Local</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>

                <!-- Cuerpo del modal: formulario de edición -->
                <div class="modal-body">

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="txtNombreLocal" class="form-label">Nombre</label>
                            <asp:TextBox ID="txtNombreLocal" runat="server" CssClass="form-control" />
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="txtTelefonoLocal" class="form-label">Teléfono</label>
                            <asp:TextBox ID="txtTelefonoLocal" runat="server" CssClass="form-control" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="txtDireccionLocal" class="form-label">Dirección</label>
                            <asp:TextBox ID="txtDireccionLocal" runat="server" 
                                            CssClass="form-control" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="txtDescripcionLocal" class="form-label">Descripcion</label>
                            <asp:TextBox ID="txtDescripcionLocal" runat="server" 
                                            CssClass="form-control" TextMode="MultiLine" Rows="2" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="ddlEstadoLocal" class="form-label">Estado</label>
                            <asp:DropDownList ID="ddlEstadoLocal" runat="server" CssClass="form-select">
                                <asp:ListItem Value="true">Activo</asp:ListItem>
                                <asp:ListItem Value="false">Inactivo</asp:ListItem>
                            </asp:DropDownList>
                        </div>
                    </div>
                </div>

                <!-- Pie del modal: botones Guardar / Cancelar -->
                <div class="modal-footer">
                    <asp:Button ID="btnGuardarLocalModal" runat="server"
                                CssClass="btn btn-success" Text="Guardar"
                                OnClick="btnGuardarLocalModal_Click" />
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                        Cancelar
                    </button>
                </div>
            </div>
        </div>
    </asp:Panel>
    <!-- /FIN DEL MODAL -->
</asp:Content>

