package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.Cliente;
import br.com.entregasdrogasintese.model.Entrega;
import br.com.entregasdrogasintese.model.Entregador;
import br.com.entregasdrogasintese.model.Pagamento;
import br.com.entregasdrogasintese.model.Situacao;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAOImpl implements GenericDAO {

    private Connection conn;

    public EntregaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Entrega entrega = (Entrega) object;
        PreparedStatement stmt = null;

        String sql = "insert into entrega (dataentrega, produtos, valor, recebedor, observacao, entregadorido, situacaoido, clienteido) VALUE (?,?,?,?,?,?,?,?);";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(entrega.getDataentrega().getTime()));
            stmt.setString(2, entrega.getProdutos());
            stmt.setDouble(3, entrega.getValor());
            stmt.setString(4, entrega.getRecebedor());
            stmt.setString(5, entrega.getObservacao());
            stmt.setInt(6, entrega.getEntregador().getEntregadorido());
            stmt.setInt(7, entrega.getSituacao().getSituacaoido());
            stmt.setInt(8, entrega.getCliente().getClienteido());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar Entrega! DAO Erro: " + ex.getMessage());
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
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT\n"
                + "entrega.entregaido,\n"
                + "entrega.dataentrega,\n"
                + "entrega.produtos,\n"
                + "entrega.valor,\n"
                + "entrega.recebedor,\n"
                + "entrega.observacao,\n"
                + "entrega.entregadorido,\n"
                + "pessoa.nome as entregador,\n"
                + "coalesce(entrega.pagamentoido,'') pagamentoido,\n"
                + "coalesce(pagamento.descricao,'') as descricaopagamento,\n"
                + "entrega.situacaoido,\n"
                + "situacao.descricao as descricaosituacao,\n"
                + "entrega.clienteido,\n"
                + "entrega.datapagamento,\n"
                + "(SELECT\n"
                + "pessoa.nome\n"
                + "from entrega e\n"
                + "inner join cliente on cliente.clienteido = e.clienteido\n"
                + "INNER join pessoa on pessoa.pessoaido = cliente.pessoaido\n"
                + "where entrega.entregaido = e.entregaido) as clientenome,\n"
                + "(SELECT\n"
                + "concat(cliente.logradouro,', N??:',cliente.numero, ', ', cliente.bairro)\n"
                + "from cliente c\n"
                + "where c.clienteido = cliente.clienteido) as logradouro\n"
                + "FROM entrega\n"
                + "inner join entregador on entregador.entregadorido = entrega.entregadorido\n"
                + "left join pagamento on pagamento.pagamentoido = entrega.pagamentoido\n"
                + "inner join situacao on situacao.situacaoido = entrega.situacaoido\n"
                + "inner join cliente on cliente.clienteido = entrega.clienteido\n"
                + "inner join pessoa on pessoa.pessoaido = entregador.pessoaido\n"
                + "order by entrega.entregaido desc";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setEntregaido(rs.getInt("entregaido"));
                entrega.setDataentrega(rs.getDate("dataentrega"));
                entrega.setProdutos(rs.getString("produtos"));
                entrega.setValor(rs.getDouble("valor"));
                entrega.setRecebedor(rs.getString("recebedor"));
                entrega.setObservacao(rs.getString("observacao"));
                entrega.setDatapagamento(rs.getDate("datapagamento"));

                Pagamento pagamento = new Pagamento();
                pagamento.setPagamentoido(rs.getInt("pagamentoido"));
                pagamento.setDescricao(rs.getString("descricaopagamento"));

                Situacao situacao = new Situacao();
                situacao.setSituacaoido(rs.getInt("situacaoido"));
                situacao.setDescricao(rs.getString("descricaosituacao"));

                Cliente cliente = new Cliente();
                cliente.setClienteido(rs.getInt("clienteido"));
                cliente.setNome(rs.getString("clientenome"));
                cliente.setLogradouro(rs.getString("logradouro"));

                Entregador entregador = new Entregador();
                entregador.setEntregadorido(rs.getInt("entregadorido"));
                entregador.setNome(rs.getString("entregador"));

                entrega.setCliente(cliente);
                entrega.setEntregador(entregador);
                entrega.setPagamento(pagamento);
                entrega.setSituacao(situacao);

                resultado.add(entrega);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Entrega! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os par??metros de conex??o! Erro: " + ex.getMessage());
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

        String sql = "SELECT COUNT(*) AS total FROM entrega;";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalRegistros = rs.getInt("total");
            }

        } catch (SQLException ex) {
            System.out.println("Problemas ao contabilizar total de entregas! ErrO: " + ex.getMessage());
            ex.printStackTrace();
        }

        int valorLimite = (pagina * limite);//20
        int valorInicial = (pagina * limite) - limite;//11

        String sql2 = "SELECT\n"
                + "entrega.entregaido,\n"
                + "entrega.dataentrega,\n"
                + "entrega.produtos,\n"
                + "entrega.valor,\n"
                + "entrega.recebedor,\n"
                + "entrega.observacao,\n"
                + "entrega.entregadorido,\n"
                + "pessoa.nome as entregador,\n"
                + "coalesce(entrega.pagamentoido,'') pagamentoido,\n"
                + "coalesce(pagamento.descricao,'') as descricaopagamento,\n"
                + "entrega.situacaoido,\n"
                + "situacao.descricao as descricaosituacao,\n"
                + "entrega.clienteido,\n"
                + "entrega.datapagamento,\n"
                + "(SELECT\n"
                + "pessoa.nome\n"
                + "from entrega e\n"
                + "inner join cliente on cliente.clienteido = e.clienteido\n"
                + "INNER join pessoa on pessoa.pessoaido = cliente.pessoaido\n"
                + "where entrega.entregaido = e.entregaido) as clientenome,\n"
                + "(SELECT\n"
                + "concat(cliente.logradouro,', N??:',cliente.numero, ', ', cliente.bairro)\n"
                + "from cliente c\n"
                + "where c.clienteido = cliente.clienteido) as logradouro\n"
                + "FROM entrega\n"
                + "inner join entregador on entregador.entregadorido = entrega.entregadorido\n"
                + "left join pagamento on pagamento.pagamentoido = entrega.pagamentoido\n"
                + "inner join situacao on situacao.situacaoido = entrega.situacaoido\n"
                + "inner join cliente on cliente.clienteido = entrega.clienteido\n"
                + "inner join pessoa on pessoa.pessoaido = entregador.pessoaido\n"
                + "order by entrega.entregaido\n"
                + "LIMIT ?, ?;";

        try {
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1, valorInicial);
            stmt2.setInt(2, 10);
            rs2 = stmt2.executeQuery();

            while (rs2.next()) {
                Entrega entrega = new Entrega();
                entrega.setEntregaido(rs2.getInt("entregaido"));
                entrega.setDataentrega(rs2.getDate("dataentrega"));
                entrega.setProdutos(rs2.getString("produtos"));
                entrega.setValor(rs2.getDouble("valor"));
                entrega.setRecebedor(rs2.getString("recebedor"));
                entrega.setObservacao(rs2.getString("observacao"));
                entrega.setDatapagamento(rs2.getDate("datapagamento"));

                Pagamento pagamento = new Pagamento();
                pagamento.setPagamentoido(rs2.getInt("pagamentoido"));
                pagamento.setDescricao(rs2.getString("descricaopagamento"));

                Situacao situacao = new Situacao();
                situacao.setSituacaoido(rs2.getInt("situacaoido"));
                situacao.setDescricao(rs2.getString("descricaosituacao"));

                Cliente cliente = new Cliente();
                cliente.setClienteido(rs2.getInt("clienteido"));
                cliente.setNome(rs2.getString("clientenome"));
                cliente.setLogradouro(rs2.getString("logradouro"));

                Entregador entregador = new Entregador();
                entregador.setEntregadorido(rs2.getInt("entregadorido"));
                entregador.setNome(rs2.getString("entregador"));

                entrega.setCliente(cliente);
                entrega.setEntregador(entregador);
                entrega.setPagamento(pagamento);
                entrega.setSituacao(situacao);
                entrega.setTotalRegistros(totalRegistros);

                resultado.add(entrega);
                System.out.println("Qtde: " + resultado.size() + " " + valorInicial + " " + valorLimite);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar Entrega! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os par??metros de conex??o! Erro: " + ex.getMessage());
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
        Entrega entrega = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT\n"
                + "entrega.entregaido,\n"
                + "entrega.dataentrega,\n"
                + "entrega.produtos,\n"
                + "entrega.valor,\n"
                + "entrega.recebedor,\n"
                + "entrega.observacao,\n"
                + "entrega.entregadorido,\n"
                + "entrega.pagamentoido,\n"
                + "entrega.situacaoido,\n"
                + "entrega.clienteido,\n"
                + "entrega.datapagamento\n"
                + "FROM entrega\n"
                + "where entrega.entregaido = ?\n"
                + "order by entrega.dataentrega desc";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                entrega = new Entrega();
                entrega.setEntregaido(rs.getInt("entregaido"));
                entrega.setDataentrega(rs.getDate("dataentrega"));
                entrega.setProdutos(rs.getString("produtos"));
                entrega.setValor(rs.getDouble("valor"));
                entrega.setRecebedor(rs.getString("recebedor"));
                entrega.setObservacao(rs.getString("observacao"));
                entrega.setPagamento(new Pagamento(rs.getInt("pagamentoido")));
                entrega.setSituacao(new Situacao(rs.getInt("situacaoido")));
                entrega.setCliente(new Cliente(rs.getInt("clienteido")));
                entrega.setEntregador(new Entregador(rs.getInt("entregadorido")));
                entrega.setDatapagamento(rs.getDate("datapagamento"));
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Entrega! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.close(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return entrega;
    }

    @Override
    public Boolean alterar(Object object) {
        Entrega entrega = (Entrega) object;
        PreparedStatement stmt = null;
        String sql = "update entrega\n"
                + "set entrega.dataentrega = ?,\n"
                + "entrega.produtos = ?,\n"
                + "entrega.valor = ?,\n"
                + "entrega.recebedor = ?,\n"
                + "entrega.observacao = ?,\n"
                + "entrega.pagamentoido = ?,\n"
                + "entrega.situacaoido = ?,\n"
                + "entrega.clienteido = ?,\n"
                + "entrega.entregadorido = ?,\n"
                + "entrega.datapagamento = ?\n"
                + "where entrega.entregaido = ?;";
        try {
            stmt = conn.prepareCall(sql);
            stmt.setDate(1, new java.sql.Date(entrega.getDataentrega().getTime()));
            stmt.setString(2, entrega.getProdutos());
            stmt.setDouble(3, entrega.getValor());
            stmt.setString(4, entrega.getRecebedor());
            stmt.setString(5, entrega.getObservacao());

            if (entrega.getPagamento() == null) {
                stmt.setNull(6, 0);
            } else {
                stmt.setInt(6, entrega.getPagamento().getPagamentoido());
            }

            stmt.setInt(7, entrega.getSituacao().getSituacaoido());
            stmt.setInt(8, entrega.getCliente().getClienteido());
            stmt.setInt(9, entrega.getEntregador().getEntregadorido());

            if (entrega.getDatapagamento() == null) {
                stmt.setDate(10, null);
            } else {
                stmt.setDate(10, new java.sql.Date(entrega.getDatapagamento().getTime()));
            }
            stmt.setInt(11, entrega.getEntregaido());

            stmt.executeUpdate();
            return true;

        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Entrega DAO! Erro:" + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public Object listarQuantidadeCadastrada(Integer entregaido) {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select\n"
                + "count(entrega.entregaido) as pagas_hoje,\n"
                + "(select count(e.entregaido) from entrega e where e.dataentrega = CURRENT_DATE) as cadastradas_hoje\n"
                + "from entrega\n"
                + "where entrega.datapagamento = CURRENT_DATE";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setQtdCadastrada(rs.getInt("pagas_hoje"));
                entrega.setQtdFinalizada(rs.getInt("cadastradas_hoje"));

                resultado.add(entrega);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao somar quantidade de entregas cadastradas! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

    public Object listarQuantidadeFinalizadas(Integer entregaido) {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select count(entrega.entregaido)as qtd from entrega\n"
                + "where entrega.datapagamento = CURRENT_DATE";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Entrega entrega = new Entrega();
                entrega.setQtdFinalizada(rs.getInt("qtd"));

                resultado.add(entrega);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao somar quantidade de entregas cadastradas! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar conexao Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return resultado;
    }

}
