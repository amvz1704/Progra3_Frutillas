<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClienteHome.aspx.cs" Inherits="LocalWebService.ClienteHome" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <!-- Banner Principal -->
    <div class="banner">
        <div class="texto-banner">
            <h1>Frescura natural en cada bocado</h1>
            <p>Destino favorito de los amantes de las frutas</p>
        </div>
    </div>
    
    <!-- Container Principal -->
    <div class="container">
        <!-- Sección de Productos -->
        <div class="producto-seccion">
            <h2>Productos</h2>
            
            <!-- Producto 1: Jugos -->
            <div class="producto-right">
                <img src="Public/images/homeProductos.jpg" alt="Jugos naturales" class="producto-right-img" />
                <div class="producto-right-texto">
                    <h4>Jugos</h4>
                    <p>
                        Nuestros jugos son explosiones de frescura hechas al instante.
                        Combinamos frutas naturales para hacer jugos de temporada.
                    </p>
                </div>
            </div>
            
            <!-- Producto 2: Snacks (imagen a la derecha) -->
            <div class="producto-right reverse">
                <img src="Public/images/homeSnacks.jpg" alt="Snacks saludables" class="producto-right-img" />
                <div class="producto-right-texto">
                    <h4>Snacks</h4>
                    <p>
                        Nuestros snacks son saludables y con gran variedad, que da
                        muchas opciones para elegir y comer en cualquier momento del
                        día.
                    </p>
                </div>
            </div>
            
            <!-- Producto 3: Frutas -->
            <div class="producto-right">
                <img src="Public/images/homeFrutas.jpg" alt="Frutas frescas" class="producto-right-img" />
                <div class="producto-right-texto">
                    <h4>Frutas</h4>
                    <p>
                        Ofrecemos gran variedad de frutas frescas de temporada, 
                        pensadas en siempre dar la opción de comer fruta dentro del
                        campus PUCP.
                    </p>
                </div>
            </div>
            
            <!-- Botón para ir a productos -->
            <div style="text-align: center; margin-top: 30px;">
                <asp:Button ID="ProductosCliente" OnClick="ProductosCliente_Click" 
                           runat="server" Text="Ir a Productos" CssClass="btn-frutilla" />
            </div>
        </div>

        <!-- Sección de Locales -->
        <div class="producto-seccion">
            <h2>Nuestros Locales</h2>
            
            <!-- Carrusel de Locales -->
            <div class="carousel">
                <input type="radio" name="carousel" id="slide1" checked>
                <input type="radio" name="carousel" id="slide2">

                <div class="slides">
                    <!-- Slide 1: EEGGLL -->
                    <div class="slide">
                        <img src="Public/images/fruteria-letras.jpg" alt="Puesto Frutilla PUCP EEGGLL">
                        <div class="slide-info">
                            <h3>PUCP - EEGGLL</h3>
                            <p>Estudios Generales Letras, Av. Universitaria 1801, San Miguel 15088</p>
                            <div class="buttons">
                                <a href="ClienteListaProducto.aspx">Ver Productos</a>
                                <a href="https://maps.app.goo.gl/BFDuUeQybzcdGoYf6" target="_blank">Ver Mapa</a>
                            </div>
                        </div>
                    </div>

                    <!-- Slide 2: Sociales -->
                    <div class="slide">
                        <img src="Public/images/fruteria-sociales.jpg" alt="Puesto Frutilla Ciencias Sociales">
                        <div class="slide-info">
                            <h3>PUCP - Sociales</h3>
                            <p>Facultad de Ciencias Sociales, Av. Universitaria 1801, San Miguel 15088</p>
                            <div class="buttons">
                                <a href="ClienteListaProducto.aspx">Ver Productos</a>
                                <a href="https://maps.app.goo.gl/ScLL6Lj8cfhXHGu39" target="_blank">Ver Mapa</a>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Navegación del carrusel -->
                <div class="nav-buttons">
                    <label for="slide1"></label>
                    <label for="slide2"></label>
                </div>
            </div>
        </div>

        <!-- Sección adicional con cards de información -->
        <div class="producto-seccion">
            <h2>¿Por qué elegir Frutilla?</h2>
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 20px; margin-top: 30px;">
                
                <div class="card-saludable">
                    <div class="card-header">
                        Frescura Garantizada
                    </div>
                    <div class="card-body">
                        <p>Todos nuestros productos son seleccionados diariamente para garantizar la máxima frescura y calidad.</p>
                    </div>
                </div>

                <div class="card-saludable">
                    <div class="card-header">
                        Ubicación Conveniente
                    </div>
                    <div class="card-body">
                        <p>Estratégicamente ubicados en el campus PUCP para que siempre tengas acceso a opciones saludables.</p>
                    </div>
                </div>

                <div class="card-saludable">
                    <div class="card-header">
                        Variedad Única
                    </div>
                    <div class="card-body">
                        <p>Desde jugos frescos hasta snacks nutritivos, tenemos algo delicioso para cada momento del día.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Script para mejorar la funcionalidad del carrusel (opcional) -->
    <script type="text/javascript">
        // Auto-advance carousel every 5 seconds
        let currentSlide = 1;
        const totalSlides = 2;
        
        setInterval(function() {
            currentSlide = currentSlide >= totalSlides ? 1 : currentSlide + 1;
            document.getElementById('slide' + currentSlide).checked = true;
        }, 5000);
    </script>
</asp:Content>