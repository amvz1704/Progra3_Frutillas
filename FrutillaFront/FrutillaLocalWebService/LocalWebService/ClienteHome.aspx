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
     <div class="producto-seccion">
        <h2>Productos</h2>
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
         <div class="producto-right">
             <img src="Public/images/homeSnacks.jpg" alt="Snacks saludables" class="producto-right-img" />
             <div class="producto-right-texto">
                 <h4>Snacks</h4>
                 <p>
                     Nuestros snacks son saludable y con gran variedad, que da
                     muchas opciones para elegir y comer en cualquier momento del
                     día.
                 </p>
             </div>
         </div>
         <div class="producto-right">
             <img src="Public/images/homeFrutas.jpg" alt="Frutas frescas" class="producto-right-img" />
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
</asp:Content>
