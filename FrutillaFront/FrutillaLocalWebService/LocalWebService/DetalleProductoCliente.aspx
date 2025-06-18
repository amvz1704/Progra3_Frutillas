<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="DetalleProductoCliente.aspx.cs" Inherits="LocalWebService.DetalleProductoCliente" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <asp:HiddenField ID="HiddenTipoProductoEdit" runat="server" />
    <asp:HiddenField ID="HiddenIdProductoEdit" runat="server" />
    <asp:HiddenField ID="HiddenTipoEstadoProductoEdit" runat="server" />

    <div class="container mt-4">
        <!-- Card Principal con Estilo Saludable -->
        <div class="card-saludable shadow-saludable">
            <div class="card-header text-center">
                <h3 class="mb-0">🌱 Detalle del Producto</h3>
            </div>
            <div class="card-body">
                <!-- Imagen del producto -->
                <div class="text-center mb-4">
                    <img id="imgProducto" runat="server" class="img-fluid rounded shadow-saludable"
                        alt="Imagen producto" style="max-height: 350px; border-radius: 15px;" />
                </div>

                <!-- Información general -->
                <div class="bg-gradient-saludable p-4 rounded mb-4">
                    <h4 class="text-saludable mb-4 text-center">📋 Información del Producto</h4>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="label-saludable">🏷️ Nombre del producto</label>
                            <asp:TextBox ID="TxtEditNombre" runat="server"
                                CssClass="textbox-saludable"
                                ReadOnly="true"
                                BorderStyle="None" />
                        </div>

                        <div class="col-md-6 mb-3">
                            <label class="label-saludable">💰 Precio (S/.)</label>
                            <asp:TextBox ID="TxtEditPrecio" runat="server"
                                CssClass="textbox-saludable"
                                ReadOnly="true"
                                BorderStyle="None" />
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="label-saludable">📝 Descripción</label>
                        <asp:TextBox ID="TxtEditDescripcion" runat="server"
                            CssClass="textbox-saludable"
                            ReadOnly="true"
                            BorderStyle="None"
                            TextMode="MultiLine"
                            Rows="3" />
                    </div>

                    <div class="mb-3">
                        <label class="label-importante">📦 Stock disponible</label>
                        <asp:TextBox ID="TxtEditStock" runat="server"
                            CssClass="textbox-saludable"
                            TextMode="Number"
                            ReadOnly="true"
                            BorderStyle="None" />
                    </div>
                </div>

                <!-- Sección Frutas -->
                <div id="opcionFrutasEditar" style="display: none;" class="card-producto mt-4">
                    <h5 class="text-saludable mb-4 text-center">🍎 Detalles para Frutas</h5>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label class="label-saludable">📦 Tipo de envase</label>
                            <asp:TextBox ID="TxtTipoEnvaseEdit" runat="server"
                                CssClass="textbox-saludable"
                                ReadOnly="true"
                                BorderStyle="None" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <div class="bg-gradient-saludable p-3 rounded text-center">
                                <asp:CheckBox ID="ChkReqEnvaseEdit" runat="server"
                                    CssClass="form-check-input me-2"
                                    Enabled="false" />
                                <label class="label-saludable">📦 ¿Requiere envase?</label>
                            </div>
                        </div>
                        <div class="col-md-4 mb-3">
                            <div class="bg-gradient-saludable p-3 rounded text-center">
                                <asp:CheckBox ID="ChkEnvasadoEdit" runat="server"
                                    CssClass="form-check-input me-2"
                                    Enabled="false" />
                                <label class="label-saludable">✅ ¿Está envasado?</label>
                            </div>
                        </div>
                        <div class="col-md-4 mb-3">
                            <div class="bg-gradient-saludable p-3 rounded text-center">
                                <asp:CheckBox ID="ChkReqLimpiezaEdit" runat="server"
                                    CssClass="form-check-input me-2"
                                    Enabled="false" />
                                <label class="label-saludable">🧽 ¿Requiere limpieza?</label>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Sección Bebidas -->
                <div id="opcionBebidasEditar" style="display: none;" class="card-producto mt-4">
                    <h5 class="text-saludable mb-4 text-center">🥤 Detalles para Bebidas</h5>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="label-saludable">📏 Tamaño (Oz)</label>
                            <asp:TextBox ID="TxtTamanioEdit" runat="server"
                                CssClass="textbox-saludable"
                                TextMode="Number"
                                ReadOnly="true"
                                BorderStyle="None" />
                        </div>

                        <div class="col-md-6 mb-3">
                            <label class="label-saludable">🍹 Tipo de Bebida</label>
                            <asp:TextBox ID="TxtTipoBebidaEdit" runat="server"
                                CssClass="textbox-saludable"
                                ReadOnly="true"
                                BorderStyle="None" />
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="label-saludable">🍯 Tipo de Endulzante</label>
                            <asp:TextBox ID="TxtTipoEndulzanteEdit" runat="server"
                                CssClass="textbox-saludable"
                                ReadOnly="true"
                                BorderStyle="None" />
                        </div>

                        <div class="col-md-6 mb-3">
                            <label class="label-saludable">🥛 Tipo de Leche</label>
                            <div class="dropdown-saludable">
                                <asp:DropDownList ID="DDTipoLecheEdit"
                                    CssClass="select-frutilla"
                                    runat="server"
                                    Enabled="false" />
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label class="label-importante">🍓 Frutas Incluidas</label>
                        <div class="bg-gradient-saludable p-3 rounded">
                            <asp:CheckBoxList ID="ChkFrutasBebEdit" runat="server"
                                RepeatLayout="Flow"
                                CssClass="form-check-list"
                                Enabled="false" />
                        </div>
                    </div>
                </div>

                <!-- Sección Snacks -->
                <div id="opcionSnacksEditar" style="display: none;" class="card-producto mt-4">
                    <h5 class="text-saludable mb-4 text-center">🥜 Detalles para Snacks</h5>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="label-saludable">🍿 Tipo de snack</label>
                            <asp:TextBox ID="TxtTipoSnackEdit" runat="server"
                                CssClass="textbox-saludable"
                                ReadOnly="true"
                                BorderStyle="None" />
                        </div>

                        <div class="col-md-6 mb-3">
                            <label class="label-saludable">📦 Envase</label>
                            <asp:TextBox ID="TxtEnvaseSnackEdit" runat="server"
                                CssClass="textbox-saludable"
                                ReadOnly="true"
                                BorderStyle="None" />
                        </div>
                    </div>
                </div>

                <!-- Botones de Acción -->
                <div class="text-center mt-4 pt-3 border-top">
                    <button type="button" class="btn-aceptar me-3" onclick="window.history.back();">
                        Volver al Catálogo
   
                    </button>
                    <asp:Button
                        ID="btnAgregarAlCarrito"
                        runat="server"
                        CssClass="btn-frutilla"
                        Text="Agregar al carrito"
                        OnClick="btnAgregarAlCarrito_Click" />
                </div>


                <!-- Mensaje -->
                <div class="row mt-4">
                    <div class="col-12 text-center">
                        <asp:Label ID="lblMensaje" runat="server" CssClass="label-importante text-success" />
                    </div>
                </div>
            </div>

            <!-- Footer de la Card -->
            <div class="card-footer text-center">
                <small class="text-muted">🌱 Producto 100% Natural y Saludable</small>
            </div>
        </div>
    </div>

    <!-- Estilos adicionales específicos -->
    <style>
        .form-check-list label {
            color: #4F7E38;
            font-weight: 500;
            margin-left: 8px;
            display: inline-block;
            margin-bottom: 8px;
        }

        .form-check-list input[type="checkbox"] {
            margin-right: 8px;
            accent-color: #6FA257;
        }

        .card-producto h5 {
            position: relative;
            padding-bottom: 10px;
        }

            .card-producto h5::after {
                content: '';
                position: absolute;
                bottom: 0;
                left: 50%;
                transform: translateX(-50%);
                width: 60px;
                height: 2px;
                background: linear-gradient(90deg, #6FA257, #8BC34A);
                border-radius: 2px;
            }

        .textbox-saludable[readonly] {
            background: linear-gradient(135deg, #f9f9f9, #ffffff);
            cursor: default;
        }

        .img-fluid:hover {
            transform: scale(1.02);
            transition: transform 0.3s ease;
        }

        .container {
            animation: fadeIn 0.6s ease-in;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }

            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>

    <script type="text/javascript">
        function mostrarOpcionesEditar(tipo) {
            // Ocultar todas las secciones
            document.getElementById('opcionFrutasEditar').style.display = 'none';
            document.getElementById('opcionBebidasEditar').style.display = 'none';
            document.getElementById('opcionSnacksEditar').style.display = 'none';

            // Mostrar la sección correspondiente con animación
            let seccionAMostrar = null;
            if (tipo === 'F') {
                seccionAMostrar = document.getElementById('opcionFrutasEditar');
            } else if (tipo === 'B') {
                seccionAMostrar = document.getElementById('opcionBebidasEditar');
            } else if (tipo === 'S') {
                seccionAMostrar = document.getElementById('opcionSnacksEditar');
            }

            if (seccionAMostrar) {
                seccionAMostrar.style.display = 'block';
                seccionAMostrar.style.animation = 'slideIn 0.4s ease-out';
            }
        }

        function agregarAlCarrito() {
            // Función para agregar al carrito (implementar según necesidades)
            alert('¡Producto agregado al carrito! 🛒✅');
        }

        // Animación de entrada
        document.addEventListener('DOMContentLoaded', function () {
            const cards = document.querySelectorAll('.card-producto, .card-saludable');
            cards.forEach((card, index) => {
                card.style.animationDelay = (index * 0.1) + 's';
            });
        });
    </script>

    <style>
        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateX(-20px);
            }

            to {
                opacity: 1;
                transform: translateX(0);
            }
        }
    </style>
</asp:Content>
