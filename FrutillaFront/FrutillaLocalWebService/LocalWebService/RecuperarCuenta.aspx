<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="RecuperarCuenta.aspx.cs" Inherits="LocalWebService.RecuperarCuenta" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta charset="utf-8" />
    <title>Frutilla - Recuperar Cuenta</title>
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
        <div class="container login-container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow p-4">
                        <h4 class="mb-3 text-center">Recuperar Cuenta</h4>
                        <p class="mb-3 text-center">Ingresa el correo electrónico asociado a tu cuenta. Te enviaremos un código de verificación para restablecer tu contraseña.</p>
                        <div class="form-group mb-3">
                            <label for="txtCorreo">Correo electrónico</label>
                            <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" placeholder="Ingrese su correo electrónico"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>
                        <div class="d-grid mb-2">
                            <asp:Button ID="btnEnviar" runat="server" Text="Enviar enlace" CssClass="btn-frutilla" OnClick="btnEnviar_Click" />
                        </div>
                        <div class="text-center mt-2">
                            <asp:Button ID="btnVolverLogin" runat="server" Text="← Volver al inicio de sesión" CssClass="btn btn-secondary" OnClick="btnVolverLogin_Click" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>
