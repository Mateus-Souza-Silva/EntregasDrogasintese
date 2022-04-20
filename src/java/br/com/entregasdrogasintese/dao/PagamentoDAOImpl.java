package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.Pagamento;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAOImpl implements GenericDAO{
    private Connection conn;
    
    public PagamentoDAOImpl() throws Exception{
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com SUcesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Pagamento pagamento = (Pagamento) object;
        PreparedStatement stmt = null;
        
        String sql = "INSERT into pagamento (descricao) values(?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pagamento.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Pagamento! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return false;
        }finally{
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM pagamento order by pagamento.pagamentoido";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Pagamento pagamento = new Pagamento();
                pagamento.setPagamentoido(rs.getInt("pagamentoido"));
                pagamento.setDescricao(rs.getString("descricao"));
                resultado.add(pagamento);                
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao listar pagamento DAO! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexao! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    @Override
    public void excluir(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object carregar(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean alterar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
