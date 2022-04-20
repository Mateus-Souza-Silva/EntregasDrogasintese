package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.Entregador;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EntregadorDAOImpl implements GenericDAO {

    private Connection conn;

    public EntregadorDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Entregador entregador = (Entregador) object;
        PreparedStatement stmt = null;

        String sql = "insert into entregador (senha, pessoaido) values(?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, entregador.getSenha());
            stmt.setInt(2, new PessoaDAOImpl().cadastrar(entregador));
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao Cadastrar Entregador! Erro: " + ex.getMessage());
            ex.getStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar Conexão! Erro: " + ex.getMessage());
                ex.getStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM entregador, pessoa WHERE entregador.pessoaido = pessoa.pessoaido order by entregador.entregadorido";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Entregador entregador = new Entregador();
                entregador.setEntregadorido(rs.getInt("entregadorido"));
                entregador.setSenha(rs.getString("senha"));
                entregador.setDatanascimento(rs.getDate("datanascimento"));
                /*pessoa*/
                entregador.setIdade(rs.getInt("idade"));/*pessoa*/
                entregador.setNivel(rs.getString("nivel"));/*pessoa*/
                entregador.setNome(rs.getString("nome"));/*pessoa*/
                entregador.setPessoaido(rs.getInt("pessoaido"));/*pessoa*/
                resultado.add(entregador);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao Listar Entregador DAO! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
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
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Entregador entregador = null;
        String sql = "select\n"
                + "entregador.entregadorido,\n"
                + "entregador.senha,\n"
                + "entregador.pessoaido,\n"
                + "pessoa.nome,\n"
                + "pessoa.idade,\n"
                + "pessoa.datanascimento\n"
                + "from entregador, pessoa\n"
                + "where entregador.pessoaido = pessoa.pessoaido\n"
                + "and entregador.entregadorido = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                entregador = new Entregador();
                entregador.setDatanascimento(rs.getDate("datanascimento"));
                entregador.setEntregadorido(rs.getInt("entregadorido"));
                entregador.setIdade(rs.getInt("idade"));
                entregador.setNome(rs.getString("nome"));
                entregador.setPessoaido(rs.getInt("pessoaido"));
                entregador.setSenha(rs.getString("senha"));
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Entregador DAO! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexxão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return entregador;
    }

    @Override
    public Boolean alterar(Object object) {
        Entregador entregador = (Entregador) object;
        PreparedStatement stmt = null;
        String sql = "update entregador set\n"
                + "entregador.senha = ?\n"
                + "where entregador.pessoaido = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, entregador.getSenha());

            stmt.setInt(2, entregador.getPessoaido());

            try {
                if (new PessoaDAOImpl().alterar(entregador)) {
                    stmt.executeUpdate();
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("Problemas ao alterar entregador pessoa dao! Erro:" + ex.getMessage());
                ex.printStackTrace();
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar entregador Dao! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexao! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

}
