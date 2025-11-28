package model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import config.ConectaDB;

/**
 * Classe DAO para operações CRUD da entidade Usuario
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class UsuarioDAO {
    
    /**
     * Cadastra um novo usuário no banco de dados
     * 
     * @param obj objeto Usuario com os dados a serem cadastrados
     * @return true se cadastrado com sucesso, false caso contrário
     * @throws ClassNotFoundException se o driver JDBC não for encontrado
     */
    public boolean cadastrar(Usuario obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, obj.getNome());
            pstmt.setString(2, obj.getEmail());
            pstmt.setString(3, obj.getSenha());
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return false;
        } finally {
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
    
    /**
     * Consulta todos os usuários cadastrados
     * 
     * @return List de Usuario ou null se não houver registros
     * @throws ClassNotFoundException se o driver JDBC não for encontrado
     */
    public List<Usuario> consulta_geral() throws ClassNotFoundException {
        List<Usuario> lst = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM usuarios ORDER BY pk_id ASC";
            rs = stmt.executeQuery(sql);
            
            int n_reg = 0;
            while (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("pk_id"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setDataCadastro(rs.getTimestamp("data_cadastro"));
                
                lst.add(obj);
                n_reg++;
            }
            
            return (n_reg == 0) ? null : lst;
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return null;
        } finally {
            ConectaDB.fecharResultSet(rs);
            ConectaDB.fecharStatement(stmt);
            ConectaDB.fecharConexao(conn);
        }
    }
    
    /**
     * Consulta um usuário específico por ID
     * 
     * @param obj objeto Usuario com o ID a ser consultado
     * @return Usuario encontrado ou null se não existir
     * @throws ClassNotFoundException se o driver JDBC não for encontrado
     */
    public Usuario consulta_id(Usuario obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT * FROM usuarios WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, obj.getId());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                obj.setId(rs.getInt("pk_id"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setDataCadastro(rs.getTimestamp("data_cadastro"));
                return obj;
            } else {
                return null;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return null;
        } finally {
            ConectaDB.fecharResultSet(rs);
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
    
    /**
     * Consulta um usuário por email
     * 
     * @param email email do usuário a ser consultado
     * @return Usuario encontrado ou null se não existir
     * @throws ClassNotFoundException se o driver JDBC não for encontrado
     */
    public Usuario consulta_email(String email) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT * FROM usuarios WHERE email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("pk_id"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setDataCadastro(rs.getTimestamp("data_cadastro"));
                return obj;
            } else {
                return null;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return null;
        } finally {
            ConectaDB.fecharResultSet(rs);
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
    
    /**
     * Autentica um usuário verificando email e senha
     * 
     * @param email email do usuário
     * @param senha senha do usuário
     * @return Usuario se credenciais válidas, null caso contrário
     * @throws ClassNotFoundException se o driver JDBC não for encontrado
     */
    public Usuario autenticar(String email, String senha) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, senha);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Usuario obj = new Usuario();
                obj.setId(rs.getInt("pk_id"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setDataCadastro(rs.getTimestamp("data_cadastro"));
                return obj;
            } else {
                return null;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return null;
        } finally {
            ConectaDB.fecharResultSet(rs);
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
    
    /**
     * Altera os dados de um usuário existente
     * 
     * @param obj objeto Usuario com os dados atualizados
     * @return true se alterado com sucesso, false caso contrário
     * @throws ClassNotFoundException se o driver JDBC não for encontrado
     */
    public boolean alterar(Usuario obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "UPDATE usuarios SET nome = ?, email = ?, senha = ? WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, obj.getNome());
            pstmt.setString(2, obj.getEmail());
            pstmt.setString(3, obj.getSenha());
            pstmt.setInt(4, obj.getId());
            
            pstmt.executeUpdate();
            return true;
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return false;
        } finally {
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
    
    /**
     * Exclui um usuário do banco de dados
     * 
     * @param id ID do usuário a ser excluído
     * @return true se excluído com sucesso, false caso contrário
     * @throws ClassNotFoundException se o driver JDBC não for encontrado
     */
    public boolean excluir(int id) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "DELETE FROM usuarios WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            
            int result = pstmt.executeUpdate();
            return (result > 0);
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return false;
        } finally {
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
}
