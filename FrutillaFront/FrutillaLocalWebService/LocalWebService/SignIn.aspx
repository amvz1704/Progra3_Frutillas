<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="SignIn.aspx.cs" Inherits="LocalWebService.SignIn" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Frutilla - Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
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
        <div>
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow p-4">
                        <ul class="nav nav-pills mb-3 justify-content-center" id="pills-tab" role="tablist">
                            <li class="nav-item text-center" role="presentation">
                                <a class="nav-link" href="Login.aspx">Ingresar</a>
                            </li>
                            <li class="nav-item text-center" role="presentation">
                                <a class="nav-link active" id="pills-register-tab" data-bs-toggle="pill" href="SignIn.aspx" role="tab" aria-controls="pills-register" aria-selected="true">Registrarse</a>
                            </li>
                        </ul>

                        <div class="form-group mb-4">
                            <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" placeholder="Nombre"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblNombre" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>
                        
                        <div class="form-group mb-4">
                            <asp:TextBox ID="txtApPaterno" runat="server" CssClass="form-control" placeholder="Apellido Paterno"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblApPaterno" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>

                        <div class="form-group mb-4">
                            <asp:TextBox ID="txtApMaterno" runat="server" CssClass="form-control" placeholder="Apellido Materno"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblApMaterno" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>

                        <div class="form-group mb-3">
                            <asp:TextBox ID="txtUsuario" runat="server" CssClass="form-control" placeholder="Nombre de usuario"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblUsuario" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>

                        <div class="form-group mb-4">
                            <asp:TextBox ID="txtPassword" runat="server" CssClass="form-control" placeholder="Contraseña"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblPassword" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>

                        <div class="form-group mb-4">
                            <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" placeholder="Correo Electronico"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblCorreo" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>

                        <div class="form-group mb-4">
                            <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" placeholder="Telefono"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblTelefono" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>

                        <div class="d-grid">
                            <asp:Button ID="btnSignIn" runat="server" Text="Registrarse" CssClass="btn btn-primary" OnClick="btnSignIn_Click" />
                        </div>
                        <asp:Label ID="lblRegistro" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js" integrity="sha384-j1CDi7MgGQ12Z7Qab0qlWQ/Qqz24Gc6BM0thvEMVjHnfYGF0rmFCozFSxQBxwHKO" crossorigin="anonymous"></script>
</body>
</html>
