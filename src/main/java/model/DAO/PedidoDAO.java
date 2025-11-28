package model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import config.ConectaDB;

/**
 * Classe DAO para operações da entidade Pedido
 * 
 * @author TireGrip Team
 * @version 1.0
 */
public class PedidoDAO {
    
    public int cadastrar(Pedido obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "INSERT INTO pedidos (usuario_id, endereco_id, subtotal, frete, desconto, total, forma_pagamento, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setInt(1, obj.getUsuarioId());
            pstmt.setInt(2, obj.getEnderecoId());
            pstmt.setFloat(3, obj.getSubtotal());
            pstmt.setFloat(4, obj.getFrete());
            pstmt.setFloat(5, obj.getDesconto());
            pstmt.setFloat(6, obj.getTotal());
            pstmt.setString(7, obj.getFormaPagamento());
            pstmt.setString(8, obj.getStatus());
            
            pstmt.executeUpdate();
            
            
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro - SQL: " + ex.getMessage());
            return 0;
        } finally {
            ConectaDB.fecharResultSet(rs);
            ConectaDB.fecharStatement(pstmt);
            ConectaDB.fecharConexao(conn);
        }
    }
    
    public List<Pedido> consulta_por_usuario(int usuarioId) throws ClassNotFoundException {
        List<Pedido> lst = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT p.*, u.nome as usuario_nome, " +
                        "CONCAT(e.rua, ', ', e.numero, ' - ', e.bairro, ' - ', e.cidade) as endereco_completo " +
                        "FROM pedidos p " +
                        "INNER JOIN usuarios u ON p.usuario_id = u.pk_id " +
                        "INNER JOIN enderecos e ON p.endereco_id = e.pk_id " +
                        "WHERE p.usuario_id = ? " +
                        "ORDER BY p.data_pedido DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, usuarioId);
            
            rs = pstmt.executeQuery();
            
            int n_reg = 0;
            while (rs.next()) {
                Pedido obj = new Pedido();
                obj.setId(rs.getInt("pk_id"));
                obj.setUsuarioId(rs.getInt("usuario_id"));
                obj.setEnderecoId(rs.getInt("endereco_id"));
                obj.setDataPedido(rs.getTimestamp("data_pedido"));
                obj.setSubtotal(rs.getFloat("subtotal"));
                obj.setFrete(rs.getFloat("frete"));
                obj.setDesconto(rs.getFloat("desconto"));
                obj.setTotal(rs.getFloat("total"));
                obj.setFormaPagamento(rs.getString("forma_pagamento"));
                obj.setStatus(rs.getString("status"));
                obj.setUsuarioNome(rs.getString("usuario_nome"));
                obj.setEnderecoCompleto(rs.getString("endereco_completo"));
                
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
    
    public Pedido consulta_id(Pedido obj) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "SELECT p.*, u.nome as usuario_nome, " +
                        "CONCAT(e.rua, ', ', e.numero, ' - ', e.bairro, ' - ', e.cidade) as endereco_completo " +
                        "FROM pedidos p " +
                        "INNER JOIN usuarios u ON p.usuario_id = u.pk_id " +
                        "INNER JOIN enderecos e ON p.endereco_id = e.pk_id " +
                        "WHERE p.pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, obj.getId());
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                obj.setId(rs.getInt("pk_id"));
                obj.setUsuarioId(rs.getInt("usuario_id"));
                obj.setEnderecoId(rs.getInt("endereco_id"));
                obj.setDataPedido(rs.getTimestamp("data_pedido"));
                obj.setSubtotal(rs.getFloat("subtotal"));
                obj.setFrete(rs.getFloat("frete"));
                obj.setDesconto(rs.getFloat("desconto"));
                obj.setTotal(rs.getFloat("total"));
                obj.setFormaPagamento(rs.getString("forma_pagamento"));
                obj.setStatus(rs.getString("status"));
                obj.setUsuarioNome(rs.getString("usuario_nome"));
                obj.setEnderecoCompleto(rs.getString("endereco_completo"));
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
    
    public boolean atualizar_status(int pedidoId, String status) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = ConectaDB.conectar();
            String sql = "UPDATE pedidos SET status = ? WHERE pk_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, status);
            pstmt.setInt(2, pedidoId);
            
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
    
    public List<Pedido> consulta_geral() throws ClassNotFoundException {
        List<Pedido> lst = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = ConectaDB.conectar();
            stmt = conn.createStatement();
            String sql = "SELECT p.*, u.nome as usuario_nome, " +
                        "CONCAT(e.rua, ', ', e.numero, ' - ', e.bairro, ' - ', e.cidade) as endereco_completo " +
                        "FROM pedidos p " +
                        "INNER JOIN usuarios u ON p.usuario_id = u.pk_id " +
                        "INNER JOIN enderecos e ON p.endereco_id = e.pk_id " +
                        "ORDER BY p.data_pedido DESC";
            rs = stmt.executeQuery(sql);
            
            int n_reg = 0;
            while (rs.next()) {
                Pedido obj = new Pedido();
                obj.setId(rs.getInt("pk_id"));
                obj.setUsuarioId(rs.getInt("usuario_id"));
                obj.setEnderecoId(rs.getInt("endereco_id"));
                obj.setDataPedido(rs.getTimestamp("data_pedido"));
                obj.setSubtotal(rs.getFloat("subtotal"));
                obj.setFrete(rs.getFloat("frete"));
                obj.setDesconto(rs.getFloat("desconto"));
                obj.setTotal(rs.getFloat("total"));
                obj.setFormaPagamento(rs.getString("forma_pagamento"));
                obj.setStatus(rs.getString("status"));
                obj.setUsuarioNome(rs.getString("usuario_nome"));
                obj.setEnderecoCompleto(rs.getString("endereco_completo"));
                
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
}
