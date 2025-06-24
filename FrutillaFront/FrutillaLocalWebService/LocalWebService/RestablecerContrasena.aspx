<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="RestablecerContrasena.aspx.cs" Inherits="LocalWebService.RestablecerContrasena" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta charset="utf-8" />
    <title>Restablecer Contraseña</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/Public/css/site.css" rel="stylesheet" />
    <style>
        body {
            background-color: #F8FBD9;
        }

        .login-container {
            margin-top: 100px;
        }

        .card {
            border-radius: 1rem;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card p-4">
                        <h4 class="text-center mb-4">Restablecer contraseña</h4>

                        <asp:Label ID="lblEstado" runat="server" CssClass="text-danger d-block text-center"></asp:Label>

                        <asp:Panel ID="pnlFormulario" runat="server" Visible="false">
                            <div class="form-group mb-3">
                                <label for="txtNuevaContrasena">Nueva Contraseña</label>
                                <asp:TextBox ID="txtNuevaContrasena" runat="server" CssClass="form-control" TextMode="Password"></asp:TextBox>
                            </div>
                            <div class="form-group mb-4">
                                <label for="txtCorreotxtCorreo">Confirmar nueva contraseña</label>
                                <asp:TextBox ID="txtConfirmarContrasena" runat="server" CssClass="form-control" TextMode="Password"></asp:TextBox>
                            </div>
                            <div class="d-grid">
                                <asp:Button ID="btnRestablecer" runat="server" CssClass="btn btn-frutilla" Text="Guardar contraseña" OnClick="btnRestablecer_Click" />
                            </div>
                            <div class="text-center mt-2">
                                <asp:Button ID="btnVolver" runat="server" Text="← Volver al inicio de sesión" CssClass="btn btn-secondary" OnClick="btnVolver_Click" />
                            </div>
                        </asp:Panel>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
