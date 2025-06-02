<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="LocalWebService.Login" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta charset="utf-8" />
    <title>Frutilla - Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" />
    <style>
        body {
            background-color: #f1f1f1;
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
                        <h3 class="text-center mb-4">Iniciar sesión</h3>

                        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger"></asp:Label>

                        <div class="form-group mb-3">
                            <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control" placeholder="Usuario"></asp:TextBox>
                        </div>
                        <div class="form-group mb-4">
                            <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control" TextMode="Password" placeholder="Contraseña"></asp:TextBox>
                        </div>
                        <div class="d-grid">
                            <asp:Button ID="btnLogin" runat="server" Text="Ingresar" CssClass="btn btn-primary" OnClick="btnLogin_Click" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

