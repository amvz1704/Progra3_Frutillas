<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteHome.aspx.cs" Inherits="LocalWebService.ClienteHome" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <div class="banner">
        <div class="texto-banner">
            <h1>Frescura natural en cada bocado</h1>
            <p>Destino favorito de los amantes de las frutas</p>
        </div>
    </div>
    <div class="container">
        <div class="producto-seccion">
            <h2>Productos</h2>
            <div class="producto-right">
                <img src="Public/images/homeProductos.jpg" alt="Jugos naturales" class="producto-right-img" />
                <div class="container">
                    <div class="producto-right-texto">
                        <h4>Jugos</h4>
                        <p>
                            Nuestros jugos son explosiones de frescura hechas al instante.
                            Combinamos frutas naturales para hacer jugos de temporada.
                        </p>
                    </div>
                </div>
            </div>
            <div class="producto-right">
                <div class="container">
                    <div class="producto-right-texto">
                        <h4>Snacks</h4>
                        <p>
                            Nuestros snacks son saludable y con gran variedad, que da
                            muchas opciones para elegir y comer en cualquier momento del
                            día.
                        </p>
                    </div>
                </div>
                <img src="Public/images/homeSnacks.jpg" alt="Snacks saludables" class="producto-right-img" />
            </div>
            <div class="producto-right">
                <img src="Public/images/homeFrutas.jpg" alt="Frutas frescas" class="producto-right-img" />
                <div class="container">
                    <div class="producto-right-texto">
                        <h4>Frutas</h4>
                        <p>
                            Ofercemos gran variedad de frutas frescas de temporada, 
                              pensadas en siempre dar la opción de comer fruta dentro del
                              campus PUCP.
                        </p>
                    </div>
                </div>
            </div>
            <div style="text-align: center">
                <asp:Button ID="ProductosCliente" OnClick="ProductosCliente_Click" runat="server" Text="Ir a Productos" CssClass="btn-frutilla" />
            </div>
        </div>
        <div class="producto-seccion">
            <h2>Locales</h2>
            <div class="carousel">
                <input type="radio" name="carousel" id="slide1" checked>
                <input type="radio" name="carousel" id="slide2">

                <div class="slides">
                    <!-- Slide 1 -->
                    <div class="slide">
                        <img src="Public/images/fruteria-letras.jpg" alt="Puesto Frutilla PUCP">
                        <div class="slide-info">
                            <h3>PUCP - EEGGLL</h3>
                            <p>Estudios Generales Letras, Av. Universitaria 1801, San Miguel 15088</p>
                            <div class="buttons">
                                <a href="ClienteListaProducto.aspx">Ver Productos</a>
                                <a href="https://maps.app.goo.gl/BFDuUeQybzcdGoYf6">Ver Mapa</a>
                            </div>
                        </div>
                    </div>

                    <!-- Slide 2 -->
                    <div class="slide">
                        <img src="Public/images/fruteria-sociales.jpg" alt="Puesto Frutilla Ciencias">
                        <div class="slide-info">
                            <h3>PUCP - Sociales</h3>
                            <p>Facultad de Ciencias Sociales, Av. Universitaria 1801, San Miguel 15088</p>
                            <div class="buttons">
                                <a href="ClienteListaProducto.asp">Ver Productos</a>
                                <a href="https://maps.app.goo.gl/ScLL6Lj8cfhXHGu39">Ver Mapa</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="nav-buttons">
                    <label for="slide1"></label>
                    <label for="slide2"></label>
                </div>
            </div>
    </div>
</asp:Content>
