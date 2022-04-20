package br.com.entregasdrogasintese.test;

import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;

public class ConnectionTest {
    public static void main(String[] args) {
        try {
            Connection conn = null;
            conn = ConnectionFactory.getConnection();
            if (conn != null) {
                System.out.println("Conectado com Sucesso!");
                ConnectionFactory.closeConnection(conn);
            }else{
                System.out.println("Erro ao Conectar!");
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao testar conexão!");
            ex.printStackTrace();
        }
    }
}
