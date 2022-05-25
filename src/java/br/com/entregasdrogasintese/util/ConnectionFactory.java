package br.com.entregasdrogasintese.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {
    public static Connection getConnection() throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //return DriverManager.getConnection("jdbc:mysql://localhost:3306/entregasdrogasintese", "root", "");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/entregasonline_entregasdrogasintese", "entregasonline_mateus", "entregasonline123");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
        
    public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        close(conn, stmt, rs);
    }
    public static void closeConnection(Connection conn, Statement stmt) throws Exception{
        close(conn, stmt, null);
    }
    public static void closeConnection(Connection conn) throws Exception{
        close(conn, null, null);
    }
    
    public static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception{
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
