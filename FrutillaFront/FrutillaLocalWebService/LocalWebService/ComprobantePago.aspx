<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ComprobantePago.aspx.cs" Inherits="LocalWebService.ComprobantePago" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    
    <!-- Datos -->
    <div class="card">
        <h3 class="card-body">
            <strong>Comprobante de pago:</strong>
            <asp:Label ID="lblId" runat="server" Text=" ### "></asp:Label>
        </h3>
    </div>
     <!-- Datos completos-->
    <div class="d-flex justify-content-center mt-2">
     <div class="card" style="width:60rem;">
         <div class="card-body">
            <div class="row mb-4">
              <div class="col-6">
                <label class="form-label" for="txtFecha">Fecha</label>
                <asp:TextBox ID="txtFecha" runat="server"
                             CssClass="form-control"
                             ReadOnly="true" />
              </div>
              <div class="col-4">
                <label class="form-label " for="txtNumeroArticulos">
                  Número de artículos
                </label>
                <asp:TextBox ID="txtNumeroArticulos" runat="server"
                             CssClass="form-control"
                             ReadOnly="true" />
              </div>
            </div>

            <div class="row mb-4">
              <div class="col-6">
                <label class="form-label" for="txtMetodoPago">
                  Método de pago
                </label>
                <asp:TextBox ID="txtMetodoPago" runat="server"
                             CssClass="form-control"
                             ReadOnly="true" />
              </div>
              <div class="col-4">
                <label class="form-label" for="txtLocal">Local</label>
                <asp:TextBox ID="txtLocal" runat="server"
                             CssClass="form-control"
                             ReadOnly="true" />
              </div>
           
        <div class="mb-4">
            <label class="form-label" for="txtDescripcion">
            Descripción
            </label>
            <asp:TextBox ID="txtDescripcion" runat="server"
                        CssClass="form-control"
                        TextMode="MultiLine"
                        Rows="2"
                        ReadOnly="true" />
        </div>
     </div> 
    </div>
     </div>
        </div>

     <!-- Datos completos-->
    
    <div class="container my-5" >
      
      <!-- Encabezado de comprobante omitido para brevedad -->

        <h5 class="mb-3">Detalle de artículos</h5>

        <div class="container mt-2"  style="width:60rem;">
             <asp:GridView
                    ID="gvDetalles"
                    runat="server"
                    CssClass="table table-striped"
                    AutoGenerateColumns="False"
                    AllowPaging="True"
                    PageSize="10">

                    <Columns>
                      <asp:BoundField DataField="idLineaVenta" HeaderText="Id" />
                        <asp:TemplateField HeaderText="Nombre">
                          <ItemTemplate>
                            <%# Eval("Producto.Nombre") %>
                          </ItemTemplate>
                        </asp:TemplateField>
                        <asp:TemplateField HeaderText="Descripcion">
                          <ItemTemplate>
                            <%# Eval("Producto.Descripcion") %>
                          </ItemTemplate>
                        </asp:TemplateField>
                        <asp:TemplateField HeaderText="Precio">
                          <ItemTemplate>
                            <%# Eval("Producto.PrecioUnitario") %>
                          </ItemTemplate>
                        </asp:TemplateField>

                        
                      <asp:BoundField DataField="cantidad" HeaderText="Cantidad" />
                      
                      <asp:BoundField
                        DataField="subtotal"
                        HeaderText="Subtotal"
                        DataFormatString="{0:N2}"
                      />

                        
                      

                    </Columns>
                    <PagerStyle CssClass="pagination justify-content-center" />
                    <PagerSettings
                      Mode="Numeric"
                      FirstPageText="«"
                      LastPageText="»"
                      NextPageText="›"
                      PreviousPageText="‹"
                    />
                  </asp:GridView>

              <div class="row mt-4">
                <div class="col-md-4 mb-3">
                  <label for="txtSubtotal" class="form-label"><strong>Subtotal</strong></label>
                  <asp:TextBox
                    ID="txtSubtotal"
                    runat="server"
                    CssClass="form-control"
                    ReadOnly="true"
                  />
                </div>
                <div class="col-md-4 mb-3">
                  <label for="txtIGV" class="form-label"><strong>Monto IGV</strong></label>
                  <asp:TextBox
                    ID="txtIGV"
                    runat="server"
                    CssClass="form-control"
                    ReadOnly="true"
                  />
                </div>
                <div class="col-md-4 mb-3">
                  <label for="txtTotal" class="form-label"><strong>Total</strong></label>
                  <asp:TextBox
                    ID="txtTotal"
                    runat="server"
                    CssClass="form-control"
                    ReadOnly="true"
                  />
                </div>
              </div>

            <div class="d-flex justify-content-center mt-5">
                <asp:Button ID="btnRegresar" runat="server"
                    CssClass="btn-frutilla"
                    Text="Ver Pedidos" OnClick="btnRegresar_Click" />
            </div>

         </div>
          <!-- Totales -->
          

    
      </div>  
    

</asp:Content>
