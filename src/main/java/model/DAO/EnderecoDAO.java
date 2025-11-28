package model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Endereco;
import config.ConectaDB;

/**
 * Classe DAO para operações CRUD da entidade Endereco
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class EnderecoDAO {
    
    public boolean cadastrar(Endereco obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "INSERT INTO enderecos (usuario_id, cep, rua, numero, complemento, bairro, cidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, obj.getUsuarioId());
            pstmt.setString(2, obj.getCep());
            pstmt.setString(3, obj.getRua());
            pstmt.setString(4, obj.getNumero());
            pstmt.setString(5, obj.getComplemento());
            pstmt.setString(6, obj.getBairro());
            pstmt.setString(7, obj.getCidade());
            
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
    
    public List<Endereco> consulta_por_usuario(int usuarioId) throws ClassNotFoundException {
        List<Endereco> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT * FROM enderecos WHERE usuario_id = ? ORDER BY pk_id DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuarioId);
            
            rs = pstmt.executeQuery();
            
            int n_reg = 0;
            while (rs.next()) {
                Endereco obj = new Endereco();
                obj.setId(rs.getInt("pk_id"));
                obj.setUsuarioId(rs.getInt("usuario_id"));
                obj.setCep(rs.getString("cep"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                
                lst.add(obj);
                n_reg++;
            }
            
            return (n_reg == 0) ? null : lst;
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return null;
        } finally {
            ConectaDB.fecharResultSet(rs);
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
    
    public Endereco consulta_id(Endereco obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT * FROM enderecos WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, obj.getId());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                obj.setId(rs.getInt("pk_id"));
                obj.setUsuarioId(rs.getInt("usuario_id"));
                obj.setCep(rs.getString("cep"));
                obj.setRua(rs.getString("rua"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
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
    
    public boolean alterar(Endereco obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "UPDATE enderecos SET cep = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ? WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, obj.getCep());
            pstmt.setString(2, obj.getRua());
            pstmt.setString(3, obj.getNumero());
            pstmt.setString(4, obj.getComplemento());
            pstmt.setString(5, obj.getBairro());
            pstmt.setString(6, obj.getCidade());
            pstmt.setInt(7, obj.getId());
            
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
    
    public boolean excluir(int id) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "DELETE FROM enderecos WHERE pk_id = ?";
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
