<%@ Page Title="" Language="C#" MasterPageFile="~/Cliente.Master" AutoEventWireup="true" CodeBehind="ClientePago.aspx.cs" Inherits="LocalWebService.ClientePago" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
</asp:Content>
<asp:Content ID="Content3" ContentPlaceHolderID="MainContent" runat="server">
    <h2 class="text-center mb-4">Pagos</h2>
    <div class="row g-3 mb-4 p-3 bg-white rounded shadow-sm">
        <div class="col-md-3">
            <label for="txtPedido" class="form-label">Pedido</label>
            <asp:TextBox ID="txtPedido" runat="server" CssClass="form-control" ReadOnly="true" />
        </div>
        <div class="col-md-3">
            <label for="txtSubtotal" class="form-label">Subtotal</label>
            <asp:TextBox ID="txtSubtotal" runat="server" CssClass="form-control" ReadOnly="true" />
        </div>
        <div class="col-md-3">
            <label for="txtIGV" class="form-label">Monto IGV</label>
            <asp:TextBox ID="txtIGV" runat="server" CssClass="form-control" ReadOnly="true" />
        </div>
        <div class="col-md-3">
            <label for="txtTotal" class="form-label">Total</label>
            <asp:TextBox ID="txtTotal" runat="server" CssClass="form-control" ReadOnly="true" />
        </div>
        <div class="col-12">
            <label class="form-label">Método de pago</label>
            <div class="payment-methods">
                <asp:Button ID="btnTarjeta" runat="server" Text="Tarjeta" CssClass="btn btn-success" OnClick="SeleccionarMetodo_Click" CommandArgument="Tarjeta" />
                <asp:Button ID="btnTransferencia" runat="server" Text="Transferencia" CssClass="btn btn-outline-secondary" OnClick="SeleccionarMetodo_Click" CommandArgument="Transferencia" />
                <asp:Button ID="btnPlin" runat="server" Text="Plin" CssClass="btn btn-success" OnClick="SeleccionarMetodo_Click" CommandArgument="Plin" />
                <asp:Button ID="btnYape" runat="server" Text="Yape" CssClass="btn btn-success" OnClick="SeleccionarMetodo_Click" CommandArgument="Yape" />
            </div>
        </div>
    </div>
    <!-- Aplica las clases de Bootstrap para centrar y un tamaño personalizado -->
<div id="qrPlin" runat="server">
    <div class="qr-section">
        <div class="qr-content">
            <div>
                <h4>PLIN</h4>
                <img src="./Public/images/PLIN.png" class="img-fluid my-img-medium" alt="QR" />
            </div>
            <img src="./Public/images/PLIN_LOGO.png" class="img-fluid my-img-medium qr-logo-right" alt="LOGO" />
        </div>
    </div>
</div>

<div id="qrYape" runat="server">
    <div class="qr-section">
        <div class="qr-content">
            <div>
                <h4>YAPE</h4>
                <img src="./Public/images/YAPE.png" class="img-fluid my-img-medium" alt="QR" />
            </div>
            <img src="./Public/images/YAPE_LOGO.png" class="img-fluid my-img-medium qr-logo-right" alt="LOGO" />
        </div>
    </div>
</div>





    <div id="pnlBancos" runat="server" class="row bank-section text-center">
        <div class="col-md-4 mb-3">
            <h4>BCP</h4>
            <label class="form-label">Cuenta en Banco</label>
            <asp:TextBox ID="txtBCP" runat="server" CssClass="form-control mx-auto" ReadOnly="true" Text="2525-2552-6666" />
        </div>
        <div class="col-md-4 mb-3">
            <h4>BBVA</h4>
            <label class="form-label">Cuenta en Banco</label>
            <asp:TextBox ID="txtBBVA" runat="server" CssClass="form-control mx-auto" ReadOnly="true" Text="2525-2552-6666" />
        </div>
        <div class="col-md-4 mb-3">
            <h4>SCOTIABANK</h4>
            <label class="form-label">Cuenta en Banco</label>
            <asp:TextBox ID="txtSCOTIA" runat="server" CssClass="form-control mx-auto" ReadOnly="true" Text="2525-2552-6666" />
        </div>
        <div class="col-12 text-center mt-3">
            <h4>CCI</h4>
            <asp:TextBox ID="txtCCI" runat="server" CssClass="form-control mx-auto" ReadOnly="true" Style="max-width: 250px" Text="2525-2552-6666" />
        </div>
    </div>

    <div class="text-center mt-4">
        <asp:Button ID="btnCancelar" runat="server" Text="✕ Cancelar" CssClass="btn btn-danger me-3" OnClick="btnCancelar_Click" />
        <asp:Button ID="btnPagar" runat="server" Text="🛒 Pagar" CssClass="btn btn-success" OnClick="btnPagar_Click1" />
    </div>

    <!-- javascript:var m=new bootstrap.Modal(document.getElementById('successModal'));m.show(); return false; -->

    <!-- Modal de confirmación -->
    <div class="modal fade" id="successModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header bg-success text-white">
                    <h5 class="modal-title">¡Pago exitoso!</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
                </div>
                <div class="modal-body">
                    <p>Tu pago se ha procesado correctamente.</p>
                    <p>¿Deseas ver tu comprobante de pago?</p>
                </div>
                <div class="modal-footer">
                    <button type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal">
                        Cerrar
                    </button>
                    <!-- Enlaza a tu Comprobante.aspx, puedes pasarle id si lo guardas en un HiddenField -->
                    <asp:Button
                        ID="btnVerComprobante"
                        runat="server"
                        CssClass="btn btn-primary"
                        Text="Ver comprobante"
                        OnClick="btnVerComprobante_Click" />
                </div>
            </div>
        </div>
    </div>

</asp:Content>
