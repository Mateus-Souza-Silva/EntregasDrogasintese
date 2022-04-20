package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.TipoPagamento;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoPagamentoDAOImpl implements GenericDAO{
    
    private Connection conn;
    
    public TipoPagamentoDAOImpl() throws Exception{
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        TipoPagamento tipopagamento = (TipoPagamento) object;
        PreparedStatement stmt = null;
        
        String sql = "insert into tipopagamento (descricao) values(?)";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipopagamento.getDescricao());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar TipoPagamento DAO! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }finally{
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("'Problemas ao fechar parametros de conexao! Erro: " + ex.getMessage());                
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM tipopagamento order by tipopagamento.tipopagamentoido";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                TipoPagamento tipopagamento = new TipoPagamento();
                tipopagamento.setTipopagamentoido(rs.getInt("tipopagamentoido"));
                tipopagamento.setDescricao(rs.getString("descricao"));
                resultado.add(tipopagamento);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao listar TipoPagamento DAO! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao! Erro: " + ex.getMessage());
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
