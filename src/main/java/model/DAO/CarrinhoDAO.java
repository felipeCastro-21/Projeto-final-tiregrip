package model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Carrinho;
import config.ConectaDB;


public class CarrinhoDAO {
    
    public boolean adicionar(Carrinho obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "INSERT INTO carrinho (usuario_id, produto_id, quantidade, subtotal) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, obj.getUsuarioId());
            pstmt.setInt(2, obj.getProdutoId());
            pstmt.setInt(3, obj.getQuantidade());
            pstmt.setFloat(4, obj.getSubtotal());
            
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
    
    public List<Carrinho> consulta_por_usuario(int usuarioId) throws ClassNotFoundException {
        List<Carrinho> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT c.*, p.nome as produto_nome, p.modelo as produto_modelo, p.preco as produto_preco " +
                        "FROM carrinho c " +
                        "INNER JOIN produtos p ON c.produto_id = p.pk_id " +
                        "WHERE c.usuario_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuarioId);
            
            rs = pstmt.executeQuery();
            
            int n_reg = 0;
            while (rs.next()) {
                Carrinho obj = new Carrinho();
                obj.setId(rs.getInt("pk_id"));
                obj.setUsuarioId(rs.getInt("usuario_id"));
                obj.setProdutoId(rs.getInt("produto_id"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setSubtotal(rs.getFloat("subtotal"));
                obj.setProdutoNome(rs.getString("produto_nome"));
                obj.setProdutoModelo(rs.getString("produto_modelo"));
                obj.setProdutoPreco(rs.getFloat("produto_preco"));
                
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
    
    public Carrinho consulta_item(int usuarioId, int produtoId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT * FROM carrinho WHERE usuario_id = ? AND produto_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuarioId);
            pstmt.setInt(2, produtoId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Carrinho obj = new Carrinho();
                obj.setId(rs.getInt("pk_id"));
                obj.setUsuarioId(rs.getInt("usuario_id"));
                obj.setProdutoId(rs.getInt("produto_id"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setSubtotal(rs.getFloat("subtotal"));
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
    
    public boolean atualizar_quantidade(int id, int quantidade, float subtotal) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "UPDATE carrinho SET quantidade = ?, subtotal = ? WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, quantidade);
            pstmt.setFloat(2, subtotal);
            pstmt.setInt(3, id);
            
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
    
    public boolean remover(int id) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "DELETE FROM carrinho WHERE pk_id = ?";
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
    
    public boolean limpar_carrinho(int usuarioId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "DELETE FROM carrinho WHERE usuario_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuarioId);
            
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
    
    public float calcular_subtotal(int usuarioId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT SUM(subtotal) as total FROM carrinho WHERE usuario_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuarioId);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getFloat("total");
            } else {
                return 0.0f;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return 0.0f;
        } finally {
            ConectaDB.fecharResultSet(rs);
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
}
