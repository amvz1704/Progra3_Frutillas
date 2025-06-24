<%@ Page Title="" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="ListaEmpleadosSupervisor.aspx.cs" Inherits="LocalWebService.ListaEmpleadosSupervisor" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
     <!-- 2) Header de sección -->

    <header class="py-2 border-bottom bg-frutilla">
      <div class="container">
        <h2 class="m-0"> Empleados </h2>
      </div>
    </header>
    <!-- 1. BARRA SUPERIOR: BÚSQUEDA / FILTROS / AGREGAR -->
    <div class="container">

        <div class="row align-items-center mb-3">
            <!-- Columna izquierda: campo de búsqueda -->
            <div class="col-md-5 text-left">
                <div class="input-group">
                    <asp:TextBox ID="txtBuscar" runat="server"
                        CssClass="form-control form-control-sm input-frutilla"
                        Placeholder="Buscar Empleado por nombre..."></asp:TextBox>
                    <asp:LinkButton class="btn-frutilla" runat="server"  type="button"  OnClick="btnBuscarEmpleado_Click">
                        <i class="bi bi-search"></i>
                    </asp:LinkButton>
                </div>
            </div>
            <!-- Columna intermedia: dropdown de filtros 
            <div class="col-md-3 text-md-start text-center mt-2 mt-md-0">
                
                <asp:DropDownList ID="DdlEmpleados" runat="server" CssClass="select-frutilla"
                    DataTextField="Nombre" DataValueField="IdEmpleado" AppendDataBoundItems="true"
                    AutoPostBack="True">
                    <asp:ListItem Text="Filtros" Value="0"></asp:ListItem>
                </asp:DropDownList>
            </div> 
             -->

            <div class="col-md-3 text-md-end text-right mt-2 mt-md-0">
                <asp:Button ID="btnAgregarEmpleado" runat="server"
                    Text="Agregar Empleado"
                    CssClass="btn-frutilla btn-sm"
                    OnClick="btnAgregarEmpleado_Click" />

                     <!-- Modal clientes -->
                <asp:HiddenField ID="hfModo"    runat="server" />  <!-- "Create" ó "Edit" -->
                <asp:HiddenField ID="hfIdCliente" runat="server" /> <!-- el ID sólo en edición -->
            </div>
        </div>
    </div>
   <!-- 1. FIN BARRA SUPERIOR: FIN-->
        <asp:Label 
            ID="lblError" 
            runat="server" 
            CssClass="text-danger" 
            Visible="false" />
   <!-- 2. TABLA RESPONSIVA (GridView) -->
    <div class="container">
        <asp:GridView ID="gvEmpleados" runat="server" AutoGenerateColumns="false"
            AllowPaging="false" ShowHeaderWhenEmpty="true"
            CssClass="table table-striped table-responsive table-hover gridview-saludable"
            HeaderStyle-CssClass="table-light text-center"
            RowStyle-CssClass="text-center"
            PagerStyle-CssClass="pagination justify-content-center"
            OnRowCommand="GvEmpleados_RowCommand">
            <Columns>
                <asp:TemplateField HeaderText="Id" SortExpression="idEmpleado">
                    <HeaderStyle CssClass="text-uppercase text-center text-black" />
                    <ItemTemplate>
                        <%# Eval("idUsuario") %>
                    </ItemTemplate>
                    <ItemStyle Width="40px" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Nombre" SortExpression="Nombre">
                    <HeaderStyle CssClass="text-uppercase text-center text-black" />
                    <ItemTemplate>
                        <%# Eval("nombre") %>
                    </ItemTemplate>
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Apellido Pa" SortExpression="ApellidoPa" >
                    <HeaderStyle CssClass=" text-center text-black" />
                    <ItemTemplate>
                        <%# Eval("apellidoPaterno") %>
                    </ItemTemplate>
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Apellido Ma" SortExpression="ApellidoMa">
                    <HeaderStyle CssClass="text-center text-black" />
                    <ItemTemplate>
                        <%# Eval("apellidoMaterno") %>
                    </ItemTemplate>
                </asp:TemplateField>


                <asp:TemplateField HeaderText="Teléfono" SortExpression="Telefono">
                    <HeaderStyle CssClass="text-center text-black" />
                    <ItemTemplate>
                        <%# Eval("telefono") %>
                    </ItemTemplate>
                    <ItemStyle Width="100px" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Correo" SortExpression="Correo">
                    <HeaderStyle CssClass="text-center text-black" />
                    <ItemTemplate>
                        <a href='mailto:<%# Eval("correoElectronico") %>' class="text-decoration-none">
                            <%# Eval("correoElectronico") %>
                        </a>
                    </ItemTemplate>
                    <ItemStyle Width="200px" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Turno" SortExpression="Turno">
                    <HeaderStyle CssClass="text-center text-black" />
                    <ItemTemplate>
                        <%# (bool)Eval("turnoTrabajo") ? "Mañana" : "Noche" %>
                    </ItemTemplate>
                    <ItemStyle Width="60px" />
                </asp:TemplateField>

                <asp:TemplateField HeaderText="Acciones">
                    <HeaderStyle CssClass="text-center text-black" />
                    <ItemTemplate>
                        <div class="d-flex justify-content-center">

                            <div class="d-flex align-left">
                                <asp:LinkButton ID="lnkVer" runat="server"
                                    CommandName="VerDetalles"
                                    CommandArgument='<%# Eval("idUsuario") %>'
                                    CssClass="btn-frutilla"
                                    ToolTip="Ver Detalles">
                                    <i class="bi bi-eye" title="Ver"></i>
                                </asp:LinkButton>
                            </div>

                            <asp:LinkButton ID="lnkEditar" runat="server"
                                CommandName="Editar"
                                CommandArgument='<%# Eval("idUsuario") %>'
                                CssClass="btn-frutilla-editar"
                                ToolTip="Editar">
                                <i class="bi bi-pencil" title="Editar"></i>
                            </asp:LinkButton>

                            <asp:LinkButton ID="lnkEliminar" runat="server"
                                CommandName="Eliminar"
                                CommandArgument='<%# Eval("idUsuario") %>'
                                CssClass="btn-frutilla-eliminar"
                                OnClientClick="return confirm('¿Eliminar este empleado?');"
                                ToolTip="Eliminar">
                                <i class="bi bi-trash" title="Eliminar"></i>
                            </asp:LinkButton>
                        </div>
                    </ItemTemplate>
                    <ItemStyle Width="200px" />
                </asp:TemplateField>
            </Columns>
        </asp:GridView>
    </div>
   
   
    <!-- 2. FIN TABLA RESPONSIVA (GridView)  -->

            <!-- MODAL VER DETALLES (solo lectura) -->
        <div class="modal fade" id="miModalVerDetalles" tabindex="-1" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
              <!-- Encabezado -->
              <div class="modal-header bg-primary">
                <h5 class="modal-title text-black">Detalles del Empleado</h5>
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
                  <dd class="col-sm-8"><asp:Label ID="lblVerFechaContrato" runat="server"/></dd>

                  <dt class="col-sm-4">Correo:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblVerCorreo" runat="server" /></dd>

                   <dt class="col-sm-4">Turno:</dt>
                  <dd class="col-sm-8"><asp:Label ID="lblTurno" runat="server" /></dd>
                </dl>
              </div>
              <!-- Pie -->
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
              </div>
            </div>
          </div>
        </div>

    <!-- MODAL EDITAR EMPLEADO -->
        <div class="modal fade" id="miModalEditarEmpleado" tabindex="-1" aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
              <!-- Encabezado -->
              <div class="modal-header bg-secondary">
                <h5 ID="lblTituloModalEmpleadoEdicion" class="modal-title text-white"></h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
              </div>
              <!-- Cuerpo -->
              <div class="modal-body">
                <asp:HiddenField ID="hfIdEmpleado" runat="server" />

                <div class="mb-3">
                  <label class="form-label" for="txtNombre">Nombre<span class="text-danger">*</span></label>
                  <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" />
                </div>
                <div class="mb-3">
                  <label class="form-label" for="txtApellidoPa">Apellido Paterno<span class="text-danger">*</span></label>
                  <asp:TextBox ID="txtApellidoPa" runat="server" CssClass="form-control" />
                </div>
                <div class="mb-3">
                  <label class="form-label" for="txtApellidoMa">Apellido Materno<span class="text-danger">*</span></label>
                  <asp:TextBox ID="txtApellidoMa" runat="server" CssClass="form-control" />
                </div>

                  
                <div class="mb-3">
                    <div class="row g-3 align-items-end">
                        <div class="col-6">
                          <label class="form-label" for="txtSalario">Salario<span class="text-danger">*</span></label>
                          <asp:TextBox ID="txtSalario" runat="server" CssClass="form-control" />
                        </div>
                        <div class="col-6">
                          <label class="form-label" for="txtTelefono">Teléfono<span class="text-danger">*</span></label>
                          <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" />
                        </div>
                    </div>
                </div>
                <div class="mb-3">
                    <div class="row g-3 align-items-end">
                    <div class="col-6">
                        
                    <label class="form-label" for="txtFechaContrato">Fecha Contrato<span class="text-danger">*</span></label>
                    <asp:TextBox ID="txtFechaContrato" runat="server"
                        CssClass="form-control"
                        TextMode="Date" />
                    </div> 
                    <div class="col-6">
                        <label class="form-label" for="ddlEstado">Turno<span class="text-danger">*</span></label>
                        <asp:DropDownList 
                            ID="ddlEstado" 
                            runat="server" 
                            CssClass="form-select">
                        <asp:ListItem Value="true"  Text="Mañana" />
                        <asp:ListItem Value="false" Text="Noche" />
                        </asp:DropDownList>
                    </div> 
                        </div>
                </div>


                <div class="mb-3">
                  <label class="form-label" for="txtCorreo">Correo<span class="text-danger">*</span></label>
                  <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" />
                </div>

                <div class="mb-3">
                    <div class="row g-3 align-items-end">
                        <div class="col-6">
                            <label class="form-label" for="txtUsuario">Usuario<span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control"/>
                        </div>
                        <div class="col-6">
                            <label class="form-label" for="txtContrasena">Contrasena<span class="text-danger">*</span></label>
                            <asp:TextBox ID="txtContrasena" runat="server" CssClass="form-control"/>
                        </div>
                    </div>
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
