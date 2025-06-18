<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="DetalleProductoCliente.aspx.cs" Inherits="LocalWebService.DetalleProductoCliente" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
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
    <div class="row mt-3">
    <div class="col-12">
        <asp:Label ID="lblMensaje" runat="server" CssClass="text-success fw-bold"></asp:Label>
    </div>
</div>
</asp:Content>
