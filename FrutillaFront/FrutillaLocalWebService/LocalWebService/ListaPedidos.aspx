<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="ListaPedidos.aspx.cs" Inherits="LocalWebService.ListaPedidos" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta charset="utf-8" />
    <title>Lista de Pedidos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
    <form id="form1" runat="server" class="container mt-4">
        <h2>Buscar Pedidos por Empleado</h2>

        <div class="mb-3">
            <label for="txtIdEmpleado" class="form-label">ID Empleado</label>
            <asp:TextBox ID="txtIdEmpleado" runat="server" CssClass="form-control" />
        </div>

        <div class="mb-3">
            <asp:Button ID="btnBuscar" runat="server" Text="Buscar" CssClass="btn btn-primary" OnClick="btnBuscar_Click" />
        </div>

        <asp:GridView ID="gvPedidos" runat="server" AutoGenerateColumns="false" CssClass="table table-bordered">
            <Columns>
                <asp:BoundField DataField="idOrden" HeaderText="ID Orden" />
                <asp:BoundField DataField="fecha" HeaderText="Fecha" />
                <asp:BoundField DataField="montoTotal" HeaderText="Monto Total" />
                <asp:BoundField DataField="estado" HeaderText="Estado" />
            </Columns>
        </asp:GridView>

        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger mt-2" />
    </form>
</body>
</html>