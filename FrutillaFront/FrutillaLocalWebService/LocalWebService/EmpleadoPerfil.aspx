<%@ Page Title="Mi Perfil - Empleado" Language="C#" MasterPageFile="~/Empleado.Master" AutoEventWireup="true" CodeBehind="EmpleadoPerfil.aspx.cs" Inherits="LocalWebService.EmpleadoPerfil" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    <style>
        body {
            background-color: #F8FBD9;
        }

        .perfil-container {
            margin-top: 80px;
        }

        .perfil-card {
            border-radius: 1rem;
            background-color: #fff;
            padding: 30px;
        }

        .perfil-title {
            font-weight: bold;
            color: #5c3c92;
        }

        .perfil-label {
            font-weight: 600;
        }

        .perfil-info {
            margin-bottom: 15px;
        }

        .btn-editar {
            background-color: #6c63ff;
            color: white;
            font-weight: bold;
        }

        .btn-editar:hover {
            background-color: #5a52d4;
        }

        .text-danger {
            font-size: 0.9em;
            margin-top: 2px;
        }
    </style>
</asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server" />

<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <div class="container perfil-container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="perfil-card shadow">
                    <asp:Label ID="lblEstado" runat="server" CssClass="alert alert-danger text-center d-none"></asp:Label>
                    <h3 class="text-center mb-4 perfil-title">Mi Perfil</h3>

                    <asp:Label ID="lblMensaje" runat="server" CssClass="text-success text-center d-block mb-3"></asp:Label>

                    <div class="perfil-info">
                        <span class="perfil-label">Nombre:</span><br />
                        <asp:Label ID="lblNombre" runat="server" CssClass="form-control-plaintext"></asp:Label>
                        <asp:TextBox ID="txtNombre" runat="server" CssClass="form-control" Visible="false" />
                        <asp:Label ID="lblNombreError" runat="server" CssClass="text-danger" />
                    </div>

                    <div class="perfil-info">
                        <span class="perfil-label">Apellido Paterno:</span><br />
                        <asp:Label ID="lblApellidoPaterno" runat="server" CssClass="form-control-plaintext"></asp:Label>
                        <asp:TextBox ID="txtApellidoPaterno" runat="server" CssClass="form-control" Visible="false" />
                        <asp:Label ID="lblApPaternoError" runat="server" CssClass="text-danger" />
                    </div>

                    <div class="perfil-info">
                        <span class="perfil-label">Apellido Materno:</span><br />
                        <asp:Label ID="lblApellidoMaterno" runat="server" CssClass="form-control-plaintext"></asp:Label>
                        <asp:TextBox ID="txtApellidoMaterno" runat="server" CssClass="form-control" Visible="false" />
                        <asp:Label ID="lblApMaternoError" runat="server" CssClass="text-danger" />
                    </div>

                    <div class="perfil-info">
                        <span class="perfil-label">Correo electrónico:</span><br />
                        <asp:Label ID="lblCorreo" runat="server" CssClass="form-control-plaintext"></asp:Label>
                        <asp:TextBox ID="txtCorreo" runat="server" CssClass="form-control" Visible="false" />
                        <asp:Label ID="lblCorreoError" runat="server" CssClass="text-danger" />
                    </div>

                    <div class="perfil-info">
                        <span class="perfil-label">Teléfono:</span><br />
                        <asp:Label ID="lblTelefono" runat="server" CssClass="form-control-plaintext"></asp:Label>
                        <asp:TextBox ID="txtTelefono" runat="server" CssClass="form-control" Visible="false" />
                        <asp:Label ID="lblTelefonoError" runat="server" CssClass="text-danger" />
                    </div>

                    <div class="perfil-info">
                        <span class="perfil-label">Usuario:</span><br />
                        <asp:Label ID="lblUsuarioSistema" runat="server" CssClass="form-control-plaintext"></asp:Label>
                    </div>

                    <div class="text-center mt-4">
                        <asp:Button ID="btnEditar" runat="server" Text="Editar Perfil" CssClass="btn btn-editar" OnClick="btnEditar_Click" />
                        <asp:Button ID="btnGuardar" runat="server" Text="Guardar" CssClass="btn btn-success me-2" Visible="false" OnClick="btnGuardar_Click" />
                        <asp:Button ID="btnCancelar" runat="server" Text="Cancelar" CssClass="btn btn-secondary" Visible="false" OnClick="btnCancelar_Click" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
