package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.CobrancaParcela;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class CobrancaParcelaDAOImpl implements GenericDAO{
    
    private Connection conn;
    
        public CobrancaParcelaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        CobrancaParcela cobrancaParcela = (CobrancaParcela) object;
        PreparedStatement stmt = null;

        String sql = "insert into pagamento_parcela(valor_pagamento_parcela, cobrancaido) value(?,?)";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, cobrancaParcela.getValor_pagamento_parcela());
            stmt.setInt(2, cobrancaParcela.getCobranca().getCobrancaido());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Cobranca Parcela! DAO Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao DAO Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
