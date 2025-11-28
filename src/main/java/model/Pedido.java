package model;

import java.util.Date;

/**
 * Classe Model para representar um Pedido do sistema
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class Pedido {
    
    
    private int id;
    private int usuarioId;
    private int enderecoId;
    private Date dataPedido;
    private float subtotal;
    private float frete;
    private float desconto;
    private float total;
    private String formaPagamento;
    private String status;
    
    
    private String usuarioNome;
    private String enderecoCompleto;
    
    
    public Pedido() {
    }
    
    
    
    /**
     * @return o ID do pedido
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * @param p_id o ID a ser definido
     */
    public void setId(int p_id) {
        this.id = p_id;
    }
    
    /**
     * @return o ID do usuário
     */
    public int getUsuarioId() {
        return this.usuarioId;
    }
    
    /**
     * @param p_usuarioId o ID do usuário a ser definido
     */
    public void setUsuarioId(int p_usuarioId) {
        this.usuarioId = p_usuarioId;
    }
    
    /**
     * @return o ID do endereço
     */
    public int getEnderecoId() {
        return this.enderecoId;
    }
    
    /**
     * @param p_enderecoId o ID do endereço a ser definido
     */
    public void setEnderecoId(int p_enderecoId) {
        this.enderecoId = p_enderecoId;
    }
    
    /**
     * @return a data do pedido
     */
    public Date getDataPedido() {
        return this.dataPedido;
    }
    
    /**
     * @param p_dataPedido a data do pedido a ser definida
     */
    public void setDataPedido(Date p_dataPedido) {
        this.dataPedido = p_dataPedido;
    }
    
    /**
     * @return o subtotal do pedido (soma dos produtos)
     */
    public float getSubtotal() {
        return this.subtotal;
    }
    
    /**
     * @param p_subtotal o subtotal a ser definido
     */
    public void setSubtotal(float p_subtotal) {
        this.subtotal = p_subtotal;
    }
    
    /**
     * @return o valor do frete
     */
    public float getFrete() {
        return this.frete;
    }
    
    /**
     * @param p_frete o valor do frete a ser definido
     */
    public void setFrete(float p_frete) {
        this.frete = p_frete;
    }
    
    /**
     * @return o valor do desconto
     */
    public float getDesconto() {
        return this.desconto;
    }
    
    /**
     * @param p_desconto o valor do desconto a ser definido
     */
    public void setDesconto(float p_desconto) {
        this.desconto = p_desconto;
    }
    
    /**
     * @return o valor total do pedido
     */
    public float getTotal() {
        return this.total;
    }
    
    /**
     * @param p_total o valor total a ser definido
     */
    public void setTotal(float p_total) {
        this.total = p_total;
    }
    
    /**
     * @return a forma de pagamento (CREDITO ou PIX)
     */
    public String getFormaPagamento() {
        return this.formaPagamento;
    }
    
    /**
     * @param p_formaPagamento a forma de pagamento a ser definida
     */
    public void setFormaPagamento(String p_formaPagamento) {
        this.formaPagamento = p_formaPagamento;
    }
    
    /**
     * @return o status do pedido (PENDENTE, CONFIRMADO, ENVIADO, ENTREGUE)
     */
    public String getStatus() {
        return this.status;
    }
    
    /**
     * @param p_status o status a ser definido
     */
    public void setStatus(String p_status) {
        this.status = p_status;
    }
    
    
    
    /**
     * @return o nome do usuário
     */
    public String getUsuarioNome() {
        return this.usuarioNome;
    }
    
    /**
     * @param p_usuarioNome o nome do usuário a ser definido
     */
    public void setUsuarioNome(String p_usuarioNome) {
        this.usuarioNome = p_usuarioNome;
    }
    
    /**
     * @return o endereço completo formatado
     */
    public String getEnderecoCompleto() {
        return this.enderecoCompleto;
    }
    
    /**
     * @param p_enderecoCompleto o endereço completo a ser definido
     */
    public void setEnderecoCompleto(String p_enderecoCompleto) {
        this.enderecoCompleto = p_enderecoCompleto;
    }
    
    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", dataPedido=" + dataPedido +
                ", total=" + total +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
