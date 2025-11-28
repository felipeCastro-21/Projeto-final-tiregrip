package model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Produto;
import config.ConectaDB;

/**
 * Classe DAO para operações CRUD da entidade Produto
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class ProdutoDAO {
    
    public boolean cadastrar(Produto obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "INSERT INTO produtos (nome, modelo, preco, descricao, largura, perfil, aro, indice_carga, indice_velocidade, estoque) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, obj.getNome());
            pstmt.setString(2, obj.getModelo());
            pstmt.setFloat(3, obj.getPreco());
            pstmt.setString(4, obj.getDescricao());
            pstmt.setString(5, obj.getLargura());
            pstmt.setString(6, obj.getPerfil());
            pstmt.setString(7, obj.getAro());
            pstmt.setString(8, obj.getIndiceCarga());
            pstmt.setString(9, obj.getIndiceVelocidade());
            pstmt.setInt(10, obj.getEstoque());
            
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
    
    public List<Produto> consulta_geral() throws ClassNotFoundException {
        List<Produto> lst = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM produtos ORDER BY pk_id ASC";
            rs = stmt.executeQuery(sql);
            
            int n_reg = 0;
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("pk_id"));
                obj.setNome(rs.getString("nome"));
                obj.setModelo(rs.getString("modelo"));
                obj.setPreco(rs.getFloat("preco"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setLargura(rs.getString("largura"));
                obj.setPerfil(rs.getString("perfil"));
                obj.setAro(rs.getString("aro"));
                obj.setIndiceCarga(rs.getString("indice_carga"));
                obj.setIndiceVelocidade(rs.getString("indice_velocidade"));
                obj.setEstoque(rs.getInt("estoque"));
                
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
    
    public Produto consulta_id(Produto obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT * FROM produtos WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, obj.getId());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                obj.setId(rs.getInt("pk_id"));
                obj.setNome(rs.getString("nome"));
                obj.setModelo(rs.getString("modelo"));
                obj.setPreco(rs.getFloat("preco"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setLargura(rs.getString("largura"));
                obj.setPerfil(rs.getString("perfil"));
                obj.setAro(rs.getString("aro"));
                obj.setIndiceCarga(rs.getString("indice_carga"));
                obj.setIndiceVelocidade(rs.getString("indice_velocidade"));
                obj.setEstoque(rs.getInt("estoque"));
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
    
    public List<Produto> consulta_nome(String nome) throws ClassNotFoundException {
        List<Produto> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT * FROM produtos WHERE nome LIKE ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + nome + "%");
            
            rs = pstmt.executeQuery();
            
            int n_reg = 0;
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setId(rs.getInt("pk_id"));
                obj.setNome(rs.getString("nome"));
                obj.setModelo(rs.getString("modelo"));
                obj.setPreco(rs.getFloat("preco"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setLargura(rs.getString("largura"));
                obj.setPerfil(rs.getString("perfil"));
                obj.setAro(rs.getString("aro"));
                obj.setIndiceCarga(rs.getString("indice_carga"));
                obj.setIndiceVelocidade(rs.getString("indice_velocidade"));
                obj.setEstoque(rs.getInt("estoque"));
                
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
    
    public boolean alterar(Produto obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "UPDATE produtos SET nome = ?, modelo = ?, preco = ?, descricao = ?, largura = ?, perfil = ?, aro = ?, indice_carga = ?, indice_velocidade = ?, estoque = ? WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, obj.getNome());
            pstmt.setString(2, obj.getModelo());
            pstmt.setFloat(3, obj.getPreco());
            pstmt.setString(4, obj.getDescricao());
            pstmt.setString(5, obj.getLargura());
            pstmt.setString(6, obj.getPerfil());
            pstmt.setString(7, obj.getAro());
            pstmt.setString(8, obj.getIndiceCarga());
            pstmt.setString(9, obj.getIndiceVelocidade());
            pstmt.setInt(10, obj.getEstoque());
            pstmt.setInt(11, obj.getId());
            
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
            String sql = "DELETE FROM produtos WHERE pk_id = ?";
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
    
    public boolean atualizarEstoque(int produtoId, int quantidade) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "UPDATE produtos SET estoque = estoque - ? WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, quantidade);
            pstmt.setInt(2, produtoId);
            
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
}
