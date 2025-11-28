package model;

/**
 * Classe Model para representar um Endereço de entrega
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class Endereco {
    
    
    private int id;
    private int usuarioId;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    
    
    public Endereco() {
    }
    
    
    
    /**
     * @return o ID do endereço
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
     * @return o CEP
     */
    public String getCep() {
        return this.cep;
    }
    
    /**
     * @param p_cep o CEP a ser definido
     */
    public void setCep(String p_cep) {
        this.cep = p_cep;
    }
    
    /**
     * @return o nome da rua
     */
    public String getRua() {
        return this.rua;
    }
    
    /**
     * @param p_rua o nome da rua a ser definido
     */
    public void setRua(String p_rua) {
        this.rua = p_rua;
    }
    
    /**
     * @return o número
     */
    public String getNumero() {
        return this.numero;
    }
    
    /**
     * @param p_numero o número a ser definido
     */
    public void setNumero(String p_numero) {
        this.numero = p_numero;
    }
    
    /**
     * @return o complemento
     */
    public String getComplemento() {
        return this.complemento;
    }
    
    /**
     * @param p_complemento o complemento a ser definido
     */
    public void setComplemento(String p_complemento) {
        this.complemento = p_complemento;
    }
    
    /**
     * @return o bairro
     */
    public String getBairro() {
        return this.bairro;
    }
    
    /**
     * @param p_bairro o bairro a ser definido
     */
    public void setBairro(String p_bairro) {
        this.bairro = p_bairro;
    }
    
    /**
     * @return a cidade
     */
    public String getCidade() {
        return this.cidade;
    }
    
    /**
     * @param p_cidade a cidade a ser definida
     */
    public void setCidade(String p_cidade) {
        this.cidade = p_cidade;
    }
    
    /**
     * Retorna o endereço formatado completo
     * 
     * @return endereço formatado
     */
    public String getEnderecoCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append(rua).append(", ").append(numero);
        if (complemento != null && !complemento.isEmpty()) {
            sb.append(" - ").append(complemento);
        }
        sb.append(" - ").append(bairro);
        sb.append(" - ").append(cidade);
        sb.append(" - CEP: ").append(cep);
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "Endereco{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }
}
