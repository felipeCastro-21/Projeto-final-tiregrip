package model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.ItemPedido;
import config.ConectaDB;

/**
 * Classe DAO para operações da entidade ItemPedido
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class ItemPedidoDAO {
    
    public boolean cadastrar(ItemPedido obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, obj.getPedidoId());
            pstmt.setInt(2, obj.getProdutoId());
            pstmt.setInt(3, obj.getQuantidade());
            pstmt.setFloat(4, obj.getPrecoUnitario());
            pstmt.setFloat(5, obj.getSubtotal());
            
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
    
    public List<ItemPedido> consulta_por_pedido(int pedidoId) throws ClassNotFoundException {
        List<ItemPedido> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT ip.*, p.nome as produto_nome, p.modelo as produto_modelo " +
                        "FROM itens_pedido ip " +
                        "INNER JOIN produtos p ON ip.produto_id = p.pk_id " +
                        "WHERE ip.pedido_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pedidoId);
            
            rs = pstmt.executeQuery();
            
            int n_reg = 0;
            while (rs.next()) {
                ItemPedido obj = new ItemPedido();
                obj.setId(rs.getInt("pk_id"));
                obj.setPedidoId(rs.getInt("pedido_id"));
                obj.setProdutoId(rs.getInt("produto_id"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setPrecoUnitario(rs.getFloat("preco_unitario"));
                obj.setSubtotal(rs.getFloat("subtotal"));
                obj.setProdutoNome(rs.getString("produto_nome"));
                obj.setProdutoModelo(rs.getString("produto_modelo"));
                
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
    
    public boolean cadastrar_lote(List<ItemPedido> itens) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "INSERT INTO itens_pedido (pedido_id, produto_id, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            for (ItemPedido obj : itens) {
                pstmt.setInt(1, obj.getPedidoId());
                pstmt.setInt(2, obj.getProdutoId());
                pstmt.setInt(3, obj.getQuantidade());
                pstmt.setFloat(4, obj.getPrecoUnitario());
                pstmt.setFloat(5, obj.getSubtotal());
                pstmt.addBatch();
            }
            
            pstmt.executeBatch();
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
