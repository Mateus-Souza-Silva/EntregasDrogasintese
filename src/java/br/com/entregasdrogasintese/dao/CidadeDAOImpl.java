package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.Cidade;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAOImpl implements GenericDAO{
    private Connection conn;
    
    public CidadeDAOImpl() throws Exception{
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    @Override
    public Boolean cadastrar(Object object) {
        Cidade cidade = (Cidade) object;
        PreparedStatement stmt = null;
        
        String sql = "insert into cidade (nome) values(?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cidade.getNome());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Cidade! Erro: " + ex.getMessage());
            ex.getStackTrace();
            return false;
        }finally{
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao! Erro: " + ex.getMessage());
                ex.getMessage();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "SELECT * from cidade order by cidade.cidadeido";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setCidadeido(rs.getInt("cidadeido"));
                cidade.setNome(rs.getString("nome"));
                resultado.add(cidade);                
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao listar Cidade! Erro: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parametrode conexao! Errp " + ex.getMessage());
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
