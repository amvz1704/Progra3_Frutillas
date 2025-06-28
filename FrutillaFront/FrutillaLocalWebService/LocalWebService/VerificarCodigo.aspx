<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="VerificarCodigo.aspx.cs" Inherits="LocalWebService.VerificarCodigo" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta charset="utf-8" />
    <title>Frutilla - Verificar Código</title>
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
                        <h4 class="mb-3 text-center">Verifica tu código</h4>
                        <p class="mb-3 text-center">Ingresa el código para continuar con la recuperacion de la cuenta.</p>
                        <div class="form-group mb-3">
                            <label for="txtCodigo">Código de verificación</label>
                            <asp:TextBox ID="txtCodigo" runat="server" CssClass="form-control" placeholder="Ingrese el código"></asp:TextBox>
                        </div>
                        <asp:Label ID="lblMensaje" runat="server" CssClass="text-danger mb-3 d-block text-center"></asp:Label>
                        <div class="d-grid mb-2">
                            <asp:Button ID="btnVerificar" runat="server" Text="Verificar Código" CssClass="btn-frutilla" OnClick="btnVerificar_Click" />
                        </div>
                        <div class="text-center mt-2">
                            <asp:Button ID="btnVolver" runat="server" Text="← Volver" CssClass="btn btn-secondary" OnClick="btnVolver_Click" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</body>
</html>

