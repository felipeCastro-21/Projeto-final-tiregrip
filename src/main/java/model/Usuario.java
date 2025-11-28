package model;

import java.util.Date;

/**
 * Classe Model para representar um Usuário do sistema
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class Usuario {
    
    
    private int id;
    private String nome;
    private String email;
    private String senha;
    private Date dataCadastro;
    
    
    public Usuario() {
    }
    
    
    
    /**
     * @return o ID do usuário
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
     * @return o nome do usuário
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
     * @return o email do usuário
     */
    public String getEmail() {
        return this.email;
    }
    
    /**
     * @param p_email o email a ser definido
     */
    public void setEmail(String p_email) {
        this.email = p_email;
    }
    
    /**
     * @return a senha do usuário
     */
    public String getSenha() {
        return this.senha;
    }
    
    /**
     * @param p_senha a senha a ser definida
     */
    public void setSenha(String p_senha) {
        this.senha = p_senha;
    }
    
    /**
     * @return a data de cadastro do usuário
     */
    public Date getDataCadastro() {
        return this.dataCadastro;
    }
    
    /**
     * @param p_dataCadastro a data de cadastro a ser definida
     */
    public void setDataCadastro(Date p_dataCadastro) {
        this.dataCadastro = p_dataCadastro;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
