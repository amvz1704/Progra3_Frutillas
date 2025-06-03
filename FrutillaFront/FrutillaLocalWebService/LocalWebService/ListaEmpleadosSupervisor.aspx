<%@ Page Title="" Language="C#" MasterPageFile="~/MainLayout.Master" AutoEventWireup="true" CodeBehind="ListaEmpleadosSupervisor.aspx.cs" Inherits="LocalWebService.ListaEmpleadosSupervisor" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
     <!-- 2) Header de sección -->
    
    <header class="bg-light py-2 border-bottom">
      <div class="container">
        <h2 class="m-0"> Local > Empleados </h2>
      </div>
   

    </header>

    <!-- 1. BARRA SUPERIOR: BÚSQUEDA / FILTROS / AGREGAR -->
    <div class="row align-items-center mb-3">

        <!-- Columna izquierda: campo de búsqueda -->
        

        <!-- Columna intermedia: dropdown de filtros FALTA IMPLEMENTAR -->
        

        <!-- Título + Botón "Agregar Empleado" -->
        <div class="d-flex justify-content-between align-items-center mb-3">

            <div class="col-md-6">
                <div class="input-group">
                    <asp:TextBox ID="txtBuscar" runat="server"
                                    CssClass="form-control"
                                    Placeholder="Buscar Empleado"></asp:TextBox>
                    <button class="btn btn-outline-secondary" type="button" onclick="javascript:buscar_Click()">
                        <i class="bi bi-search"></i>
                    </button>
                </div>
            </div>
            

            <asp:Button ID="btnAgregarEmpleado" runat="server"
                CssClass="btn btn-success"
                Text="Agregar Empleado"
                OnClick="btnAgregarEmpleado_Click" />
        </div>
    </div>
   <!-- 1. FIN BARRA SUPERIOR: FIN-->

        <asp:Label 
            ID="lblError" 
            runat="server" 
            CssClass="text-danger" 
            Visible="false" />




   <!-- 2. TABLA RESPONSIVA (GridView) -->
    <asp:GridView ID="gvEmpleados" runat="server" AutoGenerateColumns="false" 
    AllowPaging="false" ShowHeaderWhenEmpty="true"
    CssClass="table table-hover table-striped table-bordered align-middle"
        
    HeaderStyle-CssClass="table-light text-center"
    RowStyle-CssClass="text-center"
    AlternatingRowStyle-CssClass="table-secondary"
    PagerStyle-CssClass="pagination justify-content-center"
         OnRowCommand="GvEmpleados_RowCommand">
       
    <Columns>
         <asp:TemplateField HeaderText="Id" SortExpression="idEmpleado">
            <HeaderStyle CssClass="text-uppercase" />
            <ItemTemplate>
                <%# Eval("idEmpleado") %>
            </ItemTemplate>
            <ItemStyle Width="60px" />
        </asp:TemplateField>

        <asp:TemplateField HeaderText="Nombre" SortExpression="Nombre">
            <HeaderStyle CssClass="text-uppercase" />
            <ItemTemplate>
                <span class="fw-semibold"><%# Eval("nombre") %></span>
            </ItemTemplate>
        </asp:TemplateField>

        <asp:TemplateField HeaderText="Apellido Pa" SortExpression="ApellidoPa">
            <ItemTemplate>
                <%# Eval("apellidoPaterno") %>
            </ItemTemplate>
        </asp:TemplateField>

        <asp:TemplateField HeaderText="Apellido Ma" SortExpression="ApellidoMa">
            <ItemTemplate>
                <%# Eval("apellidoMaterno") %>
            </ItemTemplate>
        </asp:TemplateField>

         <asp:TemplateField HeaderText="Salario" SortExpression="Salario">
            <ItemTemplate>
                $<%# String.Format("{0:N2}", Eval("salario")) %>
            </ItemTemplate>
            <ItemStyle Width="100px" />
        </asp:TemplateField>

        <asp:TemplateField HeaderText="Teléfono" SortExpression="Telefono">
            <ItemTemplate>
                <%# Eval("telefono") %>
            </ItemTemplate>
            <ItemStyle Width="100px" />
        </asp:TemplateField>


        <%--<asp:TemplateField HeaderText="Fecha Contrato" SortExpression="FechaContrato">
            <ItemTemplate>
        <%# 
            // Hacemos cast al tipo concreto localDate y tomamos su Value (que es DateTime)
            ((LocalWebService.EmpleadoWS.localDate) Eval("FechaContrato"))
                .Value.ToString("dd/MM/yyyy") 
        %>
            </ItemTemplate>
            <ItemStyle Width="120px" />
        </asp:TemplateField>--%>


        <asp:TemplateField HeaderText="Correo" SortExpression="Correo">
            <ItemTemplate>
                <a href='mailto:<%# Eval("correoElectronico") %>' class="text-decoration-none">
                    <%# Eval("correoElectronico") %>
                </a>
            </ItemTemplate>
            <ItemStyle Width="200px" />
        </asp:TemplateField>


        <asp:TemplateField HeaderText="Acciones">
            <HeaderStyle CssClass="text-uppercase text-center" />
            <ItemTemplate>
                <div class="d-flex justify-content-center">

                    <asp:LinkButton ID="lnkVer" runat="server"
                        CommandName="VerDetalles"
                        CommandArgument='<%# Eval("idEmpleado") %>'
                        CssClass="btn btn-outline-primary me-1"
                        ToolTip="Ver Detalles">
                        <i class="bi bi-eye-fill"></i>
                    </asp:LinkButton>

                    <asp:LinkButton ID="lnkEditar" runat="server"
                        CommandName="Editar"
                        CommandArgument='<%# Eval("idEmpleado") %>'
                        CssClass="btn btn-outline-success me-1"
                        ToolTip="Editar">
                        <i class="bi bi-pencil-fill"></i>
                    </asp:LinkButton>

                    <asp:LinkButton ID="lnkEliminar" runat="server"
                        CommandName="Eliminar"
                        CommandArgument='<%# Eval("idEmpleado") %>'
                        CssClass="btn btn-outline-danger"
                        OnClientClick="return confirm('¿Eliminar este empleado?');"
                        ToolTip="Eliminar">
                        <i class="bi bi-trash-fill"></i>
                    </asp:LinkButton>
                </div>
            </ItemTemplate>
            <ItemStyle Width="150px" CssClass="text-center" />
        </asp:TemplateField>

</Columns>

    </asp:GridView>
   
    <!-- 2. FIN TABLA RESPONSIVA (GridView)  -->

            <!-- MODAL VER DETALLES (solo lectura) -->
        <div class="modal fade" id="miModalVerDetalles" tabindex="-1" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
              <!-- Encabezado -->
              <div class="modal-header bg-primary">
                <h5 class="modal-title text-white">Detalles del Empleado</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
              </div>
              <!-- Cuerpo -->
              <div class="modal-body">
                <dl class="row mb-0">
                  <dt class="col-sm-4">ID Empleado:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerID" runat="server" /></dd>

                  <dt class="col-sm-4">Nombre completo:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerNombre" runat="server" /></dd>

                  <dt class="col-sm-4">Apellido Paterno:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerApellidoPa" runat="server" /></dd>

                  <dt class="col-sm-4">Apellido Materno:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerApellidoMa" runat="server" /></dd>

                  <dt class="col-sm-4">Salario:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerSalario" runat="server" /></dd>

                  <dt class="col-sm-4">Teléfono:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerTelefono" runat="server" /></dd>

                  <dt class="col-sm-4">Fecha Contrato:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerFechaContrato" runat="server" /></dd>

                  <dt class="col-sm-4">Correo:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerCorreo" runat="server" /></dd>
                </dl>
              </div>
              <!-- Pie -->
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
              </div>
            </div>
          </div>
        </div>

    <!-- MODAL AGREGAR/EDITAR EMPLEADO -->
        <div class="modal fade" id="miModalEditarEmpleado" tabindex="-1" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <!-- Encabezado -->
              <div class="modal-header bg-secondary">
                <h5 ID="lblTituloModalEmpleadoEdicion" class="modal-title text-white">Editar Empleado</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
              </div>
              <!-- Cuerpo -->
              <div class="modal-body">
                <asp:HiddenField ID="hfIdEmpleado" runat="server" />

                <div class="mb-3">
                  <label class="form-label" for="txtNombre">Nombre</label>
                  <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" />
                </div>
                <div class="mb-3">
                  <label class="form-label" for="txtApellidoPa">Apellido Paterno</label>
                  <asp:TextBox ID="txtApellidoPa" runat="server" CssClass="form-control" />
                </div>
                <div class="mb-3">
                  <label class="form-label" for="txtApellidoMa">Apellido Materno</label>
                  <asp:TextBox ID="txtApellidoMa" runat="server" CssClass="form-control" />
                </div>
                <div class="mb-3">
                  <label class="form-label" for="txtSalario">Salario</label>
                  <asp:TextBox ID="txtSalario" runat="server" CssClass="form-control" />
                </div>
                <div class="mb-3">
                  <label class="form-label" for="txtTelefono">Teléfono</label>
                  <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" />
                </div>
                <div class="mb-3">
                  <label class="form-label" for="txtFechaContrato">Fecha Contrato</label>
                  <asp:TextBox ID="txtFechaContrato" runat="server"
                      CssClass="form-control"
                      TextMode="Date" />
                </div>
                <div class="mb-3">
                  <label class="form-label" for="txtCorreo">Correo</label>
                  <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" />
                </div>
              </div>
              <!-- Pie -->
              <div class="modal-footer">
                <asp:Button ID="btnGuardarModal" runat="server"
                    CssClass="btn btn-success"
                    Text="Guardar"
                    OnClick="btnGuardarModal_Click" />
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                  Cancelar
                </button>
              </div>
            </div>
          </div>
        </div>

</asp:Content>
