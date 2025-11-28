package model;

/**
 * Classe Model para representar um Item de Pedido
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class ItemPedido {
    
    
    private int id;
    private int pedidoId;
    private int produtoId;
    private int quantidade;
    private float precoUnitario;
    private float subtotal;
    
    
    private String produtoNome;
    private String produtoModelo;
    
    
    public ItemPedido() {
    }
    
    
    
    /**
     * @return o ID do item do pedido
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
     * @return o ID do pedido
     */
    public int getPedidoId() {
        return this.pedidoId;
    }
    
    /**
     * @param p_pedidoId o ID do pedido a ser definido
     */
    public void setPedidoId(int p_pedidoId) {
        this.pedidoId = p_pedidoId;
    }
    
    /**
     * @return o ID do produto
     */
    public int getProdutoId() {
        return this.produtoId;
    }
    
    /**
     * @param p_produtoId o ID do produto a ser definido
     */
    public void setProdutoId(int p_produtoId) {
        this.produtoId = p_produtoId;
    }
    
    /**
     * @return a quantidade do produto
     */
    public int getQuantidade() {
        return this.quantidade;
    }
    
    /**
     * @param p_quantidade a quantidade a ser definida
     */
    public void setQuantidade(int p_quantidade) {
        this.quantidade = p_quantidade;
    }
    
    /**
     * @return o preço unitário no momento da compra
     */
    public float getPrecoUnitario() {
        return this.precoUnitario;
    }
    
    /**
     * @param p_precoUnitario o preço unitário a ser definido
     */
    public void setPrecoUnitario(float p_precoUnitario) {
        this.precoUnitario = p_precoUnitario;
    }
    
    /**
     * @return o subtotal do item (preço unitário * quantidade)
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
     * @return o nome do produto
     */
    public String getProdutoNome() {
        return this.produtoNome;
    }
    
    /**
     * @param p_produtoNome o nome do produto a ser definido
     */
    public void setProdutoNome(String p_produtoNome) {
        this.produtoNome = p_produtoNome;
    }
    
    /**
     * @return o modelo do produto
     */
    public String getProdutoModelo() {
        return this.produtoModelo;
    }
    
    /**
     * @param p_produtoModelo o modelo do produto a ser definido
     */
    public void setProdutoModelo(String p_produtoModelo) {
        this.produtoModelo = p_produtoModelo;
    }
    
    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", produtoId=" + produtoId +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
