<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Login.aspx.cs" Inherits="LocalWebService.Login" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta charset="utf-8" />
    <title>Frutilla - Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
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
                <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                    <div class="card shadow p-4 max-auto">
                        <ul class="nav nav-frutilla mb-3 justify-content-center" id="pills-tab" role="tablist">
                            <li class="nav-item text-center" role="presentation">
                                <a class="nav-link active" id="pills-login-tab" data-bs-toggle="pill" href="Login.aspx" role="tab" aria-controls="pills-login" aria-selected="true">Ingresar</a>
                            </li>
                            <li class="nav-item text-center" role="presentation">
                                <a class="nav-link" href="SignIn.aspx">Registrarse</a>
                            </li>
                        </ul>

                        <div class="form-group mb-3">
                            <label for="txtUsuario">Usuario</label>
                            <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblUsuario" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>
                        <div class="form-group mb-4">
                            <label for="txtPassword">Contraseña</label>
                            <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control" TextMode="Password"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblPassword" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>
                        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>
                        <div class="d-grid">
                            <asp:Button ID="btnLogin" runat="server" Text="Ingresar" CssClass="btn-frutilla" OnClick="btnLogin_Click" />
                        </div>
                        <div class="text-center mt-3">
                            <a href="RecuperarCuenta.aspx" class="text-decoration-none">¿Olvidaste tu contraseña?</a>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </form>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>