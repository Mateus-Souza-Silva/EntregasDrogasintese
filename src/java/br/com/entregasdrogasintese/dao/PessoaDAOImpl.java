package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.Pessoa;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.ObjDoubleConsumer;

public class PessoaDAOImpl {

    private Connection conn;

    public PessoaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Integer cadastrar(Pessoa pessoa) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer pessoaido = null;

        String sql = "insert into pessoa(nome, idade, datanascimento, nivel, datacadastro) values(?,?,?,?,NOW());";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            stmt.setDate(3, new java.sql.Date(pessoa.getDatanascimento().getTime()));
            stmt.setString(4, pessoa.getNivel());
            stmt.executeUpdate();

            pessoaido = new PessoaDAOImpl().listarUltimoID();
            System.out.println("Pessoa: " + pessoaido);

        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Pessoa! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao Fechar Conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pessoaido;
    }

    public Pessoa LogarPessoa(String nome, String senha) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pessoa pessoa = null;
        String sql = "select pessoa.pessoaido, pessoa.nome, pessoa.nivel "
                + "from pessoa left join farmaceutico on farmaceutico.pessoaido = pessoa.pessoaido "
                + "LEFT join entregador on entregador.pessoaido = pessoa.nome "
                + "where pessoa.nome like ?"
                + "and farmaceutico.senha = ? or entregador.senha = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            stmt.setString(3, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setPessoaido(rs.getInt("pessoaido"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setNivel(rs.getString("nivel"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao logar pessoa! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parâmetro"
                        + "\n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pessoa;
    }



    public Integer listarUltimoID() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer pessoaIdo = null;

        String sql = "SELECT MAX(pessoaido) as pessoaido FROM pessoa;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                pessoaIdo = rs.getInt("pessoaido");
            }

        } catch (Exception ex) {
            System.out.println("Problemas ao listar última Pessoa! Erro:" + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao Fechar Conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pessoaIdo;
    }
    
    public Boolean alterar(Object object){
        Pessoa pessoa = (Pessoa) object;
        PreparedStatement stmt = null;
        String sql = "update pessoa set\n"
                + "pessoa.nome = ?,\n"
                + "pessoa.idade = ?,\n"
                + "pessoa.datanascimento = ?\n"
                + "where pessoa.pessoaido = ?";
        
        try {
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, pessoa.getNome());
            stmt.setInt(2, pessoa.getIdade());
            stmt.setDate(3, new java.sql.Date(pessoa.getDatanascimento().getTime()));
            
            stmt.setInt(4, pessoa.getPessoaido());
            
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao alterar Pessoa!"
                    + "\n Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros da conexão!"
                        + "\n Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}