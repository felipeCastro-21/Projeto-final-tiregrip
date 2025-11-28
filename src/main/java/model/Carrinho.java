package model;

/**
 * Classe Model para representar um item do Carrinho de Compras
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class Carrinho {
    
    
    private int id;
    private int usuarioId;
    private int produtoId;
    private int quantidade;
    private float subtotal;
    
    
    private String produtoNome;
    private String produtoModelo;
    private float produtoPreco;
    
    
    public Carrinho() {
    }
    
    
    
    /**
     * @return o ID do item do carrinho
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
     * @return o subtotal do item (preço * quantidade)
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
    
    /**
     * @return o preço unitário do produto
     */
    public float getProdutoPreco() {
        return this.produtoPreco;
    }
    
    /**
     * @param p_produtoPreco o preço do produto a ser definido
     */
    public void setProdutoPreco(float p_produtoPreco) {
        this.produtoPreco = p_produtoPreco;
    }
    
    @Override
    public String toString() {
        return "Carrinho{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", produtoId=" + produtoId +
                ", quantidade=" + quantidade +
                ", subtotal=" + subtotal +
                '}';
    }
}
