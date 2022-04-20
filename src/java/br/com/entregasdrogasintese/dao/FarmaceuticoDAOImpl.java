package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.Farmaceutico;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FarmaceuticoDAOImpl implements GenericDAO {

    private Connection conn;

    public FarmaceuticoDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Farmaceutico farmaceutico = (Farmaceutico) object;
        PreparedStatement stmt = null;

        String sql = "insert into farmaceutico (funcao, senha, pessoaido) values(?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, farmaceutico.getFuncao());
            stmt.setString(2, farmaceutico.getSenha());
            stmt.setInt(3, new PessoaDAOImpl().cadastrar(farmaceutico));
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Farmaceutico! Erro: " + ex.getMessage());
            ex.getStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao! Erro: " + ex.getMessage());
                ex.getStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "select * from farmaceutico, pessoa where pessoa.pessoaido = farmaceutico.pessoaido order by farmaceutico.farmaceuticoido";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Farmaceutico farmaceutico = new Farmaceutico();
                farmaceutico.setDatanascimento(rs.getDate("datanascimento"));
                farmaceutico.setFarmaceuticoido(rs.getInt("farmaceuticoido"));
                farmaceutico.setFuncao(rs.getString("funcao"));
                farmaceutico.setIdade(rs.getInt("idade"));
                farmaceutico.setNivel(rs.getString("nivel"));
                farmaceutico.setNome(rs.getString("nome"));
                farmaceutico.setPessoaido(rs.getInt("pessoaido"));
                farmaceutico.setSenha(rs.getString("senha"));

                resultado.add(farmaceutico);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao listar Farmaceuticos! DAO Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao! Erro:" + ex.getMessage());
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
        Farmaceutico farmaceutico = null;
        String sql = "SELECT\n"
                + "farmaceutico.farmaceuticoido,\n"
                + "farmaceutico.funcao,\n"
                + "farmaceutico.senha,\n"
                + "farmaceutico.pessoaido,\n"
                + "pessoa.nome,\n"
                + "pessoa.idade,\n"
                + "pessoa.datanascimento\n"
                + "from farmaceutico, pessoa\n"
                + "where farmaceutico.pessoaido = pessoa.pessoaido\n"
                + "and farmaceutico.farmaceuticoido = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                farmaceutico = new Farmaceutico();
                farmaceutico.setDatanascimento(rs.getDate("datanascimento"));
                farmaceutico.setFarmaceuticoido(rs.getInt("farmaceuticoido"));
                farmaceutico.setFuncao(rs.getString("funcao"));
                farmaceutico.setIdade(rs.getInt("idade"));
                farmaceutico.setNome(rs.getString("nome"));
                farmaceutico.setPessoaido(rs.getInt("pessoaido"));
                farmaceutico.setSenha(rs.getString("senha"));

            }
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar FArmaceutico DAO! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexxão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return farmaceutico;
    }

    @Override
    public Boolean alterar(Object object) {
        Farmaceutico farmaceutico = (Farmaceutico) object;
        PreparedStatement stmt = null;
        String sql = "UPDATE farmaceutico set\n"
                + "farmaceutico.funcao = ?,\n"
                + "farmaceutico.senha = ?\n"
                + "WHERE farmaceutico.pessoaido = ?";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, farmaceutico.getFuncao());
            stmt.setString(2, farmaceutico.getSenha());
            
            stmt.setInt(3, farmaceutico.getPessoaido());
            
            try {
                if (new PessoaDAOImpl().alterar(farmaceutico)) {
                    stmt.executeUpdate();
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("Problemas ao alterar farmaceutico pessoa dao! Erro:" + ex.getMessage());
                ex.printStackTrace();
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar farmaceutico Dao! Erro: " + ex.getMessage());
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
