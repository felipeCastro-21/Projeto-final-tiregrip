package model;

/**
 * Classe Model para representar um Produto (Pneu) do sistema
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class Produto {
    
    
    private int id;
    private String nome;
    private String modelo;
    private float preco;
    private String descricao;
    private String largura;
    private String perfil;
    private String aro;
    private String indiceCarga;
    private String indiceVelocidade;
    private int estoque;
    
    
    public Produto() {
    }
    
    
    
    /**
     * @return o ID do produto
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
     * @return o nome do produto
     */
    public String getNome() {
        return this.nome;
    }
    
    /**
     * @param p_nome o nome a ser definido
     */
    public void setNome(String p_nome) {
        this.nome = p_nome;
    }
    
    /**
     * @return o modelo do produto
     */
    public String getModelo() {
        return this.modelo;
    }
    
    /**
     * @param p_modelo o modelo a ser definido
     */
    public void setModelo(String p_modelo) {
        this.modelo = p_modelo;
    }
    
    /**
     * @return o preço do produto
     */
    public float getPreco() {
        return this.preco;
    }
    
    /**
     * @param p_preco o preço a ser definido
     */
    public void setPreco(float p_preco) {
        this.preco = p_preco;
    }
    
    /**
     * @return a descrição do produto
     */
    public String getDescricao() {
        return this.descricao;
    }
    
    /**
     * @param p_descricao a descrição a ser definida
     */
    public void setDescricao(String p_descricao) {
        this.descricao = p_descricao;
    }
    
    /**
     * @return a largura do pneu
     */
    public String getLargura() {
        return this.largura;
    }
    
    /**
     * @param p_largura a largura a ser definida
     */
    public void setLargura(String p_largura) {
        this.largura = p_largura;
    }
    
    /**
     * @return o perfil do pneu
     */
    public String getPerfil() {
        return this.perfil;
    }
    
    /**
     * @param p_perfil o perfil a ser definido
     */
    public void setPerfil(String p_perfil) {
        this.perfil = p_perfil;
    }
    
    /**
     * @return o aro do pneu
     */
    public String getAro() {
        return this.aro;
    }
    
    /**
     * @param p_aro o aro a ser definido
     */
    public void setAro(String p_aro) {
        this.aro = p_aro;
    }
    
    /**
     * @return o índice de carga do pneu
     */
    public String getIndiceCarga() {
        return this.indiceCarga;
    }
    
    /**
     * @param p_indiceCarga o índice de carga a ser definido
     */
    public void setIndiceCarga(String p_indiceCarga) {
        this.indiceCarga = p_indiceCarga;
    }
    
    /**
     * @return o índice de velocidade do pneu
     */
    public String getIndiceVelocidade() {
        return this.indiceVelocidade;
    }
    
    /**
     * @param p_indiceVelocidade o índice de velocidade a ser definido
     */
    public void setIndiceVelocidade(String p_indiceVelocidade) {
        this.indiceVelocidade = p_indiceVelocidade;
    }
    
    /**
     * @return a quantidade em estoque
     */
    public int getEstoque() {
        return this.estoque;
    }
    
    /**
     * @param p_estoque a quantidade em estoque a ser definida
     */
    public void setEstoque(int p_estoque) {
        this.estoque = p_estoque;
    }
    
    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", modelo='" + modelo + '\'' +
                ", preco=" + preco +
                ", estoque=" + estoque +
                '}';
    }
}
