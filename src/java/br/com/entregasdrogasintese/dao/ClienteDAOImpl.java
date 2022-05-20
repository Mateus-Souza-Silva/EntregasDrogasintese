package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.Cidade;
import br.com.entregasdrogasintese.model.Cliente;
import br.com.entregasdrogasintese.model.Pessoa;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements GenericDAO {

    private Connection conn;

    public ClienteDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Cliente cliente = (Cliente) object;
        PreparedStatement stmt = null;

        String sql = "insert into cliente (cep, logradouro, bairro, cidadeido, numero, complemento, telefone, pessoaido) values(?,?,?,?,?,?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, cliente.getCep());
            stmt.setString(2, cliente.getLogradouro());
            stmt.setString(3, cliente.getBairro());
            stmt.setInt(4, cliente.getCidade().getCidadeido());
            stmt.setString(5, cliente.getNumero());
            stmt.setString(6, cliente.getComplemento());
            stmt.setString(7, cliente.getTelefone());
            stmt.setInt(8, new PessoaDAOImpl().cadastrar(cliente));
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Cliente DAO! Erro: " + ex.getMessage());
            ex.getStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexao! Erro: " + ex.getMessage());
                ex.getStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT\n"
                + "	cliente.clienteido,\n"
                + "    cliente.cep,\n"
                + "    cliente.logradouro,\n"
                + "    cliente.bairro,\n"
                + "    cliente.numero,\n"
                + "    cliente.complemento,\n"
                + "    cliente.pessoaido,\n"
                + "    cliente.telefone,\n"
                + "    pessoa.nome,\n"
                + "    pessoa.idade,\n"
                + "    pessoa.datanascimento,\n"
                + "    cliente.cidadeido,\n"
                + "    cidade.nome as nomecidade\n"
                + "FROM cliente, pessoa, cidade\n"
                + "WHERE cliente.pessoaido = pessoa.pessoaido\n"
                + "and cliente.cidadeido = cidade.cidadeido\n"
                + "order by cliente.clienteido";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteido(rs.getInt("clienteido"));
                cliente.setCep(rs.getString("cep"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setPessoaido(rs.getInt("pessoaido"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setNome(rs.getString("nome"));
                cliente.setIdade(rs.getInt("idade"));
                cliente.setDatanascimento(rs.getDate("datanascimento"));

                Cidade cidade = new Cidade();
                cidade.setCidadeido(rs.getInt("cidadeido"));
                cidade.setNome(rs.getString("nomecidade"));

                cliente.setCidade(cidade);

                resultado.add(cliente);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao listar cliente! Erro: " + ex.getMessage());
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

    public List<Object> listar(int pagina) {
        int limite = 10;
        Integer totalRegistros = null;
        List<Object> resultado = new ArrayList<>();
        resultado.clear();
        System.out.println("Qtde Inicia: " + resultado.size());
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        String sql = "SELECT COUNT(*) AS total FROM cliente;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalRegistros = rs.getInt("total");
            }

        } catch (SQLException ex) {
            System.out.println("Problemas ao contabilizar total de clientes! ErrO: " + ex.getMessage());
            ex.printStackTrace();
        }

        int valorLimite = (pagina * limite);//20
        int valorInicial = (pagina * limite) - limite;//11

        String sql2 = "SELECT\n"
                + "	cliente.clienteido,\n"
                + "    cliente.cep,\n"
                + "    cliente.logradouro,\n"
                + "    cliente.bairro,\n"
                + "    cliente.numero,\n"
                + "    cliente.complemento,\n"
                + "    cliente.pessoaido,\n"
                + "    cliente.telefone,\n"
                + "    pessoa.nome,\n"
                + "    pessoa.idade,\n"
                + "    pessoa.datanascimento,\n"
                + "    cliente.cidadeido,\n"
                + "    cidade.nome as nomecidade\n"
                + "FROM cliente, pessoa, cidade\n"
                + "WHERE cliente.pessoaido = pessoa.pessoaido\n"
                + "and cliente.cidadeido = cidade.cidadeido\n"
                + "order by cliente.clienteido\n"
                + "LIMIT ?, ?;";
        try {
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1, valorInicial);
            stmt2.setInt(2, 10);
            rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteido(rs2.getInt("clienteido"));
                cliente.setCep(rs2.getString("cep"));
                cliente.setLogradouro(rs2.getString("logradouro"));
                cliente.setBairro(rs2.getString("bairro"));
                cliente.setNumero(rs2.getString("numero"));
                cliente.setComplemento(rs2.getString("complemento"));
                cliente.setPessoaido(rs2.getInt("pessoaido"));
                cliente.setTelefone(rs2.getString("telefone"));
                cliente.setNome(rs2.getString("nome"));
                cliente.setIdade(rs2.getInt("idade"));
                cliente.setDatanascimento(rs2.getDate("datanascimento"));

                Cidade cidade = new Cidade();
                cidade.setCidadeido(rs2.getInt("cidadeido"));
                cidade.setNome(rs2.getString("nomecidade"));

                cliente.setCidade(cidade);
                cliente.setTotalRegistros(totalRegistros);

                resultado.add(cliente);
                System.out.println("Qtde: " + resultado.size() + " " + valorInicial + " " + valorLimite);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar cliente! Erro: " + ex.getMessage());
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
        Cliente cliente = null;

        String sql = "SELECT\n"
                + "	cliente.clienteido,\n"
                + "    cliente.cep,\n"
                + "    cliente.logradouro,\n"
                + "    cliente.bairro,\n"
                + "    cliente.numero,\n"
                + "    cliente.complemento,\n"
                + "    cliente.pessoaido,\n"
                + "    cliente.telefone,\n"
                + "    pessoa.nome,\n"
                + "    pessoa.idade,\n"
                + "    pessoa.datanascimento,\n"
                + "    cliente.cidadeido,\n"
                + "    cidade.nome as nomecidade\n"
                + "FROM cliente, pessoa, cidade\n"
                + "WHERE cliente.pessoaido = pessoa.pessoaido\n"
                + "and cliente.cidadeido = cidade.cidadeido\n"
                + "and cliente.clienteido= ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setClienteido(rs.getInt("clienteido"));
                cliente.setCep(rs.getString("cep"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setComplemento(rs.getString("complemento"));
                cliente.setPessoaido(rs.getInt("pessoaido"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setNome(rs.getString("nome"));
                cliente.setIdade(rs.getInt("idade"));
                cliente.setDatanascimento(rs.getDate("datanascimento"));
                cliente.setCidade(new Cidade(rs.getInt("cidadeido")));
            }
        } catch (Exception ex) {
            System.out.println("Erro ao carregar cliente DAO! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexao! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return cliente;
    }

    @Override
    public Boolean alterar(Object object) {
        Cliente cliente = (Cliente) object;
        PreparedStatement stmt = null;
        String sql = "Update cliente set\n"
                + "cliente.cep = ?,\n"
                + "cliente.logradouro = ?,\n"
                + "cliente.bairro = ?,\n"
                + "cliente.cidadeido = ?,\n"
                + "cliente.numero = ?,\n"
                + "cliente.complemento = ?,\n"
                + "cliente.telefone = ?\n"
                + "where cliente.pessoaido = ?";
        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, cliente.getCep());
            stmt.setString(2, cliente.getLogradouro());
            stmt.setString(3, cliente.getBairro());
            stmt.setInt(4, cliente.getCidade().getCidadeido());
            stmt.setString(5, cliente.getNumero());
            stmt.setString(6, cliente.getComplemento());
            stmt.setString(7, cliente.getTelefone());

            stmt.setInt(8, cliente.getPessoaido());

            try {
                if (new PessoaDAOImpl().alterar(cliente)) {
                    stmt.executeUpdate();
                    return true;
                } else {
                    return false;
                }
            } catch (Exception ex) {
                System.out.println("Problemas ao alterar cliente pessoa dao! Erro:" + ex.getMessage());
                ex.printStackTrace();
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Cliente Dao! Erro: " + ex.getMessage());
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
