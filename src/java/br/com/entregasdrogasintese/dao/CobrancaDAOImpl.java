package br.com.entregasdrogasintese.dao;

import br.com.entregasdrogasintese.model.Cliente;
import br.com.entregasdrogasintese.model.Cobranca;
import br.com.entregasdrogasintese.model.Pagamento;
import br.com.entregasdrogasintese.model.Setor;
import br.com.entregasdrogasintese.model.Situacao;
import br.com.entregasdrogasintese.model.TipoPagamento;
import br.com.entregasdrogasintese.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CobrancaDAOImpl implements GenericDAO {

    private Connection conn;

    public CobrancaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Cobranca cobranca = (Cobranca) object;
        PreparedStatement stmt = null;

        String sql = "insert into cobranca (datacobranca, nfcobranca, clienteido, valor, vencimento, setorido, observacao, "
                + "pagamentoido, situacaoido, datapagamento, tipopagamentoido) values(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(cobranca.getDatacobranca().getTime()));
            stmt.setString(2, cobranca.getNfcobranca().toUpperCase());
            stmt.setInt(3, cobranca.getCliente().getClienteido());
            stmt.setDouble(4, cobranca.getValor());
            stmt.setDate(5, new java.sql.Date(cobranca.getVencimento().getTime()));
            stmt.setInt(6, cobranca.getSetor().getSetorido());
            stmt.setString(7, cobranca.getObservacao());

            if (cobranca.getPagamento() != null) {
                stmt.setInt(8, cobranca.getPagamento().getPagamentoido());
            } else {
                stmt.setNull(8, Types.INTEGER);
            }

            stmt.setInt(9, cobranca.getSituacao().getSituacaoido());

            if (cobranca.getDatapagamento() != null) {
                stmt.setDate(10, new java.sql.Date(cobranca.getDatapagamento().getTime()));
            } else {
                stmt.setNull(10, Types.DATE);
            }

            if (cobranca.getTipopagamento() != null) {
                stmt.setInt(11, cobranca.getTipopagamento().getTipopagamentoido());
            } else {
                stmt.setNull(11, Types.INTEGER);
            }
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao cadastrar cobranca DAO! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
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

        String sql = "SELECT\n"
                + "	cobranca.cobrancaido,\n"
                + "	cobranca.datacobranca,\n"
                + "	coalesce(cobranca.nfcobranca,'') AS nfcobranca,\n"
                + "	cobranca.valor,\n"
                + "	coalesce(cobranca.vencimento,'') AS vencimento,\n"
                + "	COALESCE(cobranca.observacao,'') AS observacao,\n"
                + "	cobranca.datapagamento AS datapagamento,\n"
                + "	cobranca.clienteido,\n"
                + "	pessoa.nome as nomecliente,\n"
                + "	cobranca.setorido,\n"
                + "	setor.descricao as descricaosetor,\n"
                + "	cobranca.situacaoido,\n"
                + "	situacao.descricao as descricaosituacao,\n"
                + "	coalesce(cobranca.tipopagamentoido,'') AS tipopagamentoido,\n"
                + "	coalesce(tipopagamento.descricao,'') AS descricaotipopagamento,\n"
                + "	coalesce(cobranca.pagamentoido,'') AS pagamentoido,\n"
                + "	coalesce(pagamento.descricao,'') AS descricaopagamento\n"
                + "FROM cobranca\n"
                + "INNER JOIN cliente ON cliente.clienteido = cobranca.clienteido\n"
                + "INNER JOIN pessoa ON pessoa.pessoaido = cliente.pessoaido\n"
                + "INNER JOIN setor ON setor.setorido = cobranca.setorido\n"
                + "INNER JOIN situacao ON situacao.situacaoido = cobranca.situacaoido\n"
                + "left JOIN tipopagamento ON tipopagamento.tipopagamentoido = cobranca.tipopagamentoido\n"
                + "LEFT JOIN pagamento ON pagamento.pagamentoido = cobranca.pagamentoido\n"
                + "ORDER BY cobranca.cobrancaido desc";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cobranca cobranca = new Cobranca();
                cobranca.setCobrancaido(rs.getInt("cobrancaido"));
                cobranca.setDatacobranca(rs.getDate("datacobranca"));
                cobranca.setNfcobranca(rs.getString("nfcobranca"));
                cobranca.setValor(rs.getDouble("valor"));
                cobranca.setVencimento(rs.getDate("vencimento"));
                cobranca.setObservacao(rs.getString("observacao"));
                cobranca.setDatapagamento(rs.getDate("datapagamento"));

                Cliente cliente = new Cliente();
                cliente.setClienteido(rs.getInt("clienteido"));
                cliente.setNome(rs.getString("nomecliente"));

                Pagamento pagamento = new Pagamento();
                pagamento.setPagamentoido(rs.getInt("pagamentoido"));
                pagamento.setDescricao(rs.getString("descricaopagamento"));

                Setor setor = new Setor();
                setor.setSetorido(rs.getInt("setorido"));
                setor.setDescricao(rs.getString("descricaosetor"));

                TipoPagamento tipopagamento = new TipoPagamento();
                tipopagamento.setTipopagamentoido(rs.getInt("tipopagamentoido"));
                tipopagamento.setDescricao(rs.getString("descricaotipopagamento"));

                Situacao situacao = new Situacao();
                situacao.setSituacaoido(rs.getInt("situacaoido"));
                situacao.setDescricao(rs.getString("descricaosituacao"));

                cobranca.setCliente(cliente);
                cobranca.setPagamento(pagamento);
                cobranca.setSetor(setor);
                cobranca.setTipopagamento(tipopagamento);
                cobranca.setSituacao(situacao);

                resultado.add(cobranca);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao listar Cobranca! Dao Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: " + ex.getMessage());
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

    public Object listarQuantidadeCobranca(Integer cobrancaido) {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "select count(cobranca.cobrancaido)as qtd from cobranca\n"
                + "where cobranca.datapagamento = CURRENT_DATE";

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cobranca cobranca = new Cobranca();
                cobranca.setQtdCobranca(rs.getInt("qtd"));

                resultado.add(cobranca);
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao somar quantidade de cobrancas DAO! Erro:" + ex.getMessage());
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
