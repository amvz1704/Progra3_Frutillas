<%@ Page Title="" Language="C#" MasterPageFile="~/MainLayout.Master" AutoEventWireup="true" CodeBehind="ListaEmpleadosSupervisor.aspx.cs" Inherits="LocalWebService.ListaEmpleadosSupervisor" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
     <!-- 2) Header de sección -->
    <header class="py-2 border-bottom bg-frutilla">
      <div class="container">
        <h2 class="m-0"> Local > Empleados </h2>
      </div>
    </header>

    <!-- 1. BARRA SUPERIOR: BÚSQUEDA / FILTROS / AGREGAR -->
    <div class="row align-items-center mb-3">
        <!-- Columna izquierda: campo de búsqueda -->
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

        <!-- Columna intermedia: dropdown de filtros -->
        <div class="col-md-3 text-md-start text-center mt-2 mt-md-0">
            <!-- Ejemplo de dropdown “Filtros” con checkboxes -->
            <asp:DropDownList ID="DdlEmpleados" runat="server" CssClass="form-select" 
            DataTextField="Nombre" DataValueField="IdEmpleado" AppendDataBoundItems="true"
                OnSelectedIndexChanged="DdlEmpleado_SelectedIndexChanged" AutoPostBack="True">
                <asp:ListItem Text="Filtros" Value="0"></asp:ListItem>
            </asp:DropDownList>
           
        </div>

        <!-- Columna derecha: botón “Agregar Empleado” -->
        <div class="col-md-3 text-md-end text-center mt-2 mt-md-0">
            <!-- Este botón puede, por ejemplo, redirigir a EmpleadoEditar.aspx -->
            <asp:Button ID="btnAgregarEmpleado" runat="server"
                        Text="Agregar Empleado"
                        CssClass="btn btn-success"
                        OnClick="btnAgregarEmpleado_Click" />
        </div>
    </div>
   <!-- 1. FIN BARRA SUPERIOR: FIN-->

   <!-- 2. TABLA RESPONSIVA (GridView) -->
   
    <asp:GridView ID="GvEmpleados" runat="server"
                    AutoGenerateColumns="false"
                    AllowPaging="true" PageSize="5"
                    OnPageIndexChanging="GvEmpleados_PageIndexChanging"
                    CssClass="table table-bordered table-striped table-hover align-middle">
        <!-- Definición de columnas -->
        <Columns>
            <asp:BoundField DataField="Id" HeaderText="Empleado" 
                            ItemStyle-Width="60px" />

            <asp:BoundField DataField="NombreCompleto" HeaderText="Nombre Completo" 
                            ItemStyle-Width="200px" />


            <asp:BoundField DataField="Tipo" HeaderText="Tipo" 
                            ItemStyle-Width="100px" />

            <asp:BoundField DataField="Correo" HeaderText="Correo" 
                            ItemStyle-Width="200px" />

            <!-- Columna 6: Ver detalles (icono de ojo que enlace a página de detalles) -->
            <%--<asp:TemplateField HeaderText="Ver Detalles" ItemStyle-HorizontalAlign="Center" ItemStyle-Width="80px">
                <ItemTemplate>
                    <%-- Puedes usar un LinkButton para postback o simplemente un <a> hacia otra página
                    <asp:LinkButton ID="lnkVer" runat="server" CommandName="VerDetalles"
                                    CommandArgument='<%# Eval("Id") %>' CssClass="btn btn-sm btn-outline-secondary">
                        <i class="bi bi-eye"></i>
                    </asp:LinkButton>
                </ItemTemplate>
            </asp:TemplateField>--%>

            <!-- Columna 7: Editar (icono de lápiz que enlace a página de edición) -->
            <%--<asp:TemplateField HeaderText="Editar" ItemStyle-HorizontalAlign="Center" ItemStyle-Width="80px">
                <ItemTemplate>
                    <asp:LinkButton ID="lnkEditar" runat="server" CommandName="Editar"
                                    CommandArgument='<%# Eval("Id") %>' CssClass="btn btn-sm btn-outline-primary">
                        <i class="bi bi-pencil-square"></i>
                    </asp:LinkButton>
                </ItemTemplate>
            </asp:TemplateField>--%>
        </Columns>

        <!-- Opciones de paginación (puedes ajustar estilos con PagerStyle) -->
        <PagerStyle CssClass="pagination justify-content-center" Mode="NextPreviousFirstLast" />
    </asp:GridView>
            


    <!-- 2. FIN TABLA RESPONSIVA (GridView)  -->
</asp:Content>
