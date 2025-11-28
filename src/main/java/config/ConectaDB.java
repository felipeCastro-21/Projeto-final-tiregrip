package config;

import java.sql.*;


public class ConectaDB {
    
    
    private static final String DB_URL = "jdbc:mysql:
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "P@ssw0rd";
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    
 
    public static Connection conectar() throws ClassNotFoundException {
        Connection conn = null;
        
        try {
            
            Class.forName(DB_DRIVER);
            
            
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            
            
            
        } catch (SQLException ex) {
            
            System.out.println("Erro - SQL: " + ex.getMessage());
            ex.printStackTrace();
        }
        
        return conn;
    }
    
    
    public static void fecharConexao(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar conexão: " + ex.getMessage());
            }
        }
    }
    

    public static void fecharStatement(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar statement: " + ex.getMessage());
            }
        }
    }
    
   
    public static void fecharResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar resultset: " + ex.getMessage());
            }
        }
    }
}
