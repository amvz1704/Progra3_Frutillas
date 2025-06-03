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

              <asp:Button  ID="lnkEditar" runat="server"
                          CssClass="btn btn-sm btn-outline-primary"
                            CommandName="EditarModal"
                            CommandArgument='<%# Eval("Id") %>'
                            Text="Editar Local">
                            
                </asp:Button>
            </div>
          </div>
        </div>
      </asp:Panel>

        

      <!-- 4) Grid de Cards para los módulos de administración -->
      <h5 class="mt-5 mb-3"> Elige el módulo de administración:</h5>
      <div class="row g-4 justify-content-center">
        <!-- Empleados -->
          <div class="col-6 col-md-3">
              <div class="card h-100 text-center">
                  <div class="position-relative">
                      <img src="/Public/images/empleadosImagen.png"
                          alt="Empleados"
                          class="position-absolute top-0 end-0 m-2"
                          style="height: 40px;" />
                  </div>
                  <div class="card-body">
                      <h6 class="card-title">Administrar Empleados</h6>
                      <asp:Button ID="AdminEmpleados" OnClick="BtnAdminEmpleados" runat="server"
                          Text="Ir" class="btn btn-outline-primary btn-sm mt-2" />
                  </div>
              </div>
          </div>
        <!-- Productos -->
        <div class="col-6 col-md-3">
            <div class="card h-100 text-center">
                <div class="position-relative">
                    <img src="/Public/images/productosImagen.png"
                        alt="Empleados"
                        class="position-absolute top-0 end-0 m-2"
                        style="height: 40px;" />
                </div>
            <div class="card-body">
              <h6 class="card-title">Administrar Productos</h6>
              <asp:Button ID="AdminProductos" OnClick="BtnAdminProductos" runat="server" 
                Text="Ir" class="btn btn-outline-primary btn-sm mt-2" />
            </div>
          </div>
        </div>
        <!-- Ventas -->
        <div class="col-6 col-md-3">
          <div class="card h-100 text-center">
              <div class="position-relative">
                  <img src="/Public/images/ventasImagen.png"
                      alt="Empleados"
                      class="position-absolute top-0 end-0 m-2"
                      style="height: 40px;" />
              </div>
            <div class="card-body">
              <h6 class="card-title">Administrar Ventas</h6>
              <asp:Button ID="AdminVentas" OnClick="BtnAdminVentas" runat="server" 
                    Text="Ir" class="btn btn-outline-primary btn-sm mt-2" />
            </div>
          </div>
        </div>
        <!-- Clientes -->
        <div class="col-6 col-md-3">
          <div class="card h-100 text-center">
              <div class="position-relative">
                  <img src="/Public/images/empleadosImagen.png"
                      alt="Empleados"
                      class="position-absolute top-0 end-0 m-2"
                      style="height: 40px;" />
              </div>
            <div class="card-body">
              <h6 class="card-title">Clientes</h6>
              <NavLink class="btn btn-outline-primary btn-sm mt-2"
                       href="/Monitoreo/Clientes">Ir</NavLink>
            </div>
          </div>
        </div>
      </div>
    </>
    
    <asp:Panel ID="pnlModalEditar" runat="server" CssClass="modal fade" 
           role="dialog" aria-hidden="true" Style="display: none;" >

    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <!-- Encabezado del Modal -->
            <div class="modal-header bg-secondary text-white">
                <h5 class="modal-title" id="lblTituloModal">Editar Empleado</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
            </div>

            <!-- Cuerpo del Modal: aquí va tu formulario de edición -->
            <div class="modal-body">
                <asp:HiddenField ID="hfIdEmpleado" runat="server" />

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="txtNombreModal" class="form-label">Nombre Completo</label>
                        <asp:TextBox ID="txtNombreModal" runat="server" CssClass="form-control" />
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="txtTurnoModal" class="form-label">Turno de Trabajo</label>
                        <asp:TextBox ID="txtTurnoModal" runat="server" CssClass="form-control" />
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="ddlTipoModal" class="form-label">Tipo</label>
                        <asp:DropDownList ID="ddlTipoModal" runat="server" CssClass="form-select">
                            <asp:ListItem Value="Empleado">Empleado</asp:ListItem>
                            <asp:ListItem Value="Supervisor">Supervisor</asp:ListItem>
                        </asp:DropDownList>
                    </div>
                    <div class="col-md-8 mb-3">
                        <label for="txtCorreoModal" class="form-label">Correo</label>
                        <asp:TextBox ID="txtCorreoModal" runat="server" CssClass="form-control" />
                    </div>
                </div>
                <!-- Agrega más campos según la información del empleado -->
            </div>

            <!-- Pie del modal: botones Cancelar / Guardar -->
            <div class="modal-footer">
                <asp:Button ID="btnGuardarModal" runat="server" 
                            CssClass="btn btn-success" Text="Guardar" 
                            OnClick="btnGuardarModal_Click" />
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    Cancelar
                </button>
            </div>
        </div>
    </div>
</asp:Panel>

</asp:Content>

