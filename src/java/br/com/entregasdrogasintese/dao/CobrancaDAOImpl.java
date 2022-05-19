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
import java.sql.SQLException;
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
                + "situacaoido) values(?,?,?,?,?,?,?,?)";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setDate(1, new java.sql.Date(cobranca.getDatacobranca().getTime()));
            stmt.setString(2, cobranca.getNfcobranca().toUpperCase());
            stmt.setInt(3, cobranca.getCliente().getClienteido());
            stmt.setDouble(4, cobranca.getValor());
            stmt.setDate(5, new java.sql.Date(cobranca.getVencimento().getTime()));
            stmt.setInt(6, cobranca.getSetor().getSetorido());
            stmt.setString(7, cobranca.getObservacao());
            stmt.setInt(8, cobranca.getSituacao().getSituacaoido());
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
                + "	if(COALESCE(cobranca.valor - SUM(pagamento_parcela.valor_pagamento_parcela),0)>0,COALESCE(cobranca.valor - SUM(pagamento_parcela.valor_pagamento_parcela),0),cobranca.valor) AS valor,\n"
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
                + "	FROM cobranca\n"
                + "	INNER JOIN cliente ON cliente.clienteido = cobranca.clienteido\n"
                + "	INNER JOIN pessoa ON pessoa.pessoaido = cliente.pessoaido\n"
                + "	INNER JOIN setor ON setor.setorido = cobranca.setorido\n"
                + "	INNER JOIN situacao ON situacao.situacaoido = cobranca.situacaoido\n"
                + "	left JOIN tipopagamento ON tipopagamento.tipopagamentoido = cobranca.tipopagamentoido\n"
                + "	LEFT JOIN pagamento ON pagamento.pagamentoido = cobranca.pagamentoido\n"
                + "	LEFT JOIN pagamento_parcela ON (cobranca.cobrancaido = pagamento_parcela.cobrancaido)	\n"
                + "	GROUP BY 1\n"
                + "	ORDER BY cobrancaido desc";
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

    public List<Object> listar(int pagina) {
        int limite = 10;
        Integer totalRegistros=null;
        List<Object> resultado = new ArrayList<>();
        resultado.clear();
        System.out.println("Qtde Inicia: " + resultado.size());
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        String sql = "SELECT COUNT(*) AS total FROM cobranca;";

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
                + "	cobranca.cobrancaido,\n"
                + "	cobranca.datacobranca,\n"
                + "	coalesce(cobranca.nfcobranca,'') AS nfcobranca,\n"
                + "	if(COALESCE(cobranca.valor - SUM(pagamento_parcela.valor_pagamento_parcela),0)>0,COALESCE(cobranca.valor - SUM(pagamento_parcela.valor_pagamento_parcela),0),cobranca.valor) AS valor,\n"
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
                + "	FROM cobranca\n"
                + "	INNER JOIN cliente ON cliente.clienteido = cobranca.clienteido\n"
                + "	INNER JOIN pessoa ON pessoa.pessoaido = cliente.pessoaido\n"
                + "	INNER JOIN setor ON setor.setorido = cobranca.setorido\n"
                + "	INNER JOIN situacao ON situacao.situacaoido = cobranca.situacaoido\n"
                + "	left JOIN tipopagamento ON tipopagamento.tipopagamentoido = cobranca.tipopagamentoido\n"
                + "	LEFT JOIN pagamento ON pagamento.pagamentoido = cobranca.pagamentoido\n"
                + "	LEFT JOIN pagamento_parcela ON (cobranca.cobrancaido = pagamento_parcela.cobrancaido)	\n"
                + "	GROUP BY 1\n"
                + "	ORDER BY cobrancaido desc\n"
                + "     LIMIT ?, ?;";
        try {
            stmt2 = conn.prepareStatement(sql2);
            stmt2.setInt(1, valorInicial);
            stmt2.setInt(2, 10);
            rs2 = stmt2.executeQuery();
            
            while (rs2.next()) {
                Cobranca cobranca = new Cobranca();
                cobranca.setCobrancaido(rs2.getInt("cobrancaido"));
                cobranca.setDatacobranca(rs2.getDate("datacobranca"));
                cobranca.setNfcobranca(rs2.getString("nfcobranca"));
                cobranca.setValor(rs2.getDouble("valor"));
                cobranca.setVencimento(rs2.getDate("vencimento"));
                cobranca.setObservacao(rs2.getString("observacao"));
                cobranca.setDatapagamento(rs2.getDate("datapagamento"));

                Cliente cliente = new Cliente();
                cliente.setClienteido(rs2.getInt("clienteido"));
                cliente.setNome(rs2.getString("nomecliente"));

                Pagamento pagamento = new Pagamento();
                pagamento.setPagamentoido(rs2.getInt("pagamentoido"));
                pagamento.setDescricao(rs2.getString("descricaopagamento"));

                Setor setor = new Setor();
                setor.setSetorido(rs2.getInt("setorido"));
                setor.setDescricao(rs2.getString("descricaosetor"));

                TipoPagamento tipopagamento = new TipoPagamento();
                tipopagamento.setTipopagamentoido(rs2.getInt("tipopagamentoido"));
                tipopagamento.setDescricao(rs2.getString("descricaotipopagamento"));

                Situacao situacao = new Situacao();
                situacao.setSituacaoido(rs2.getInt("situacaoido"));
                situacao.setDescricao(rs2.getString("descricaosituacao"));

                cobranca.setCliente(cliente);
                cobranca.setPagamento(pagamento);
                cobranca.setSetor(setor);
                cobranca.setTipopagamento(tipopagamento);
                cobranca.setSituacao(situacao);
                cobranca.setTotalRegistros(totalRegistros);

                resultado.add(cobranca);
                System.out.println("Qtde: " + resultado.size() + " " + valorInicial + " " + valorLimite);
            }
        } catch (SQLException ex) {
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
        Cobranca cobranca = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT\n"
                + "	cobranca.cobrancaido,\n"
                + "	cobranca.datacobranca,\n"
                + "	coalesce(cobranca.nfcobranca,'') AS nfcobranca,\n"
                + "	if(COALESCE(cobranca.valor - SUM(pagamento_parcela.valor_pagamento_parcela),0)>0,COALESCE(cobranca.valor - SUM(pagamento_parcela.valor_pagamento_parcela),0),cobranca.valor) AS valor,\n"
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
                + "	FROM cobranca\n"
                + "	INNER JOIN cliente ON cliente.clienteido = cobranca.clienteido\n"
                + "	INNER JOIN pessoa ON pessoa.pessoaido = cliente.pessoaido\n"
                + "	INNER JOIN setor ON setor.setorido = cobranca.setorido\n"
                + "	INNER JOIN situacao ON situacao.situacaoido = cobranca.situacaoido\n"
                + "	left JOIN tipopagamento ON tipopagamento.tipopagamentoido = cobranca.tipopagamentoido\n"
                + "	LEFT JOIN pagamento ON pagamento.pagamentoido = cobranca.pagamentoido\n"
                + "	LEFT JOIN pagamento_parcela ON (cobranca.cobrancaido = pagamento_parcela.cobrancaido)	\n"
                + "	where cobranca.cobrancaido = ?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            if (rs.next()) {
                cobranca = new Cobranca();
                cobranca.setCobrancaido(rs.getInt("cobrancaido"));
                cobranca.setDatacobranca(rs.getDate("datacobranca"));
                cobranca.setNfcobranca(rs.getString("nfcobranca"));
                cobranca.setCliente(new Cliente(rs.getInt("clienteido")));
                cobranca.setValor(rs.getDouble("valor"));
                cobranca.setVencimento(rs.getDate("vencimento"));
                cobranca.setSetor(new Setor(rs.getInt("setorido")));
                cobranca.setObservacao(rs.getString("observacao"));
                cobranca.setPagamento(new Pagamento(rs.getInt("pagamentoido")));
                cobranca.setSituacao(new Situacao(rs.getInt("situacaoido")));
                cobranca.setDatapagamento(rs.getDate("datapagamento"));
                cobranca.setTipopagamento(new TipoPagamento(rs.getInt("tipopagamentoido")));
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao carregar Cobranca! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar parametros de conexao! Erro:" + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return cobranca;
    }

    @Override
    public Boolean alterar(Object object) {
        Cobranca cobranca = (Cobranca) object;
        PreparedStatement stmt = null;
        String sql = "UPDATE cobranca set\n"
                + "cobranca.datacobranca = ?,\n"
                + "cobranca.nfcobranca = ?,\n"
                + "cobranca.clienteido = ?,\n"
                + "cobranca.vencimento = ?,\n"
                + "cobranca.setorido = ?,\n"
                + "cobranca.observacao = ?,\n"
                + "cobranca.pagamentoido = ?,\n"
                + "cobranca.situacaoido = ?,\n"
                + "cobranca.datapagamento = ?,\n"
                + "cobranca.tipopagamentoido = ?\n"
                + "where cobranca.cobrancaido = ?;";

        String sqll = "insert into pagamento_parcela(valor_pagamento_parcela, cobrancaido)values(?,?)";

        try {
            stmt = conn.prepareCall(sql);
            stmt.setDate(1, new java.sql.Date(cobranca.getDatacobranca().getTime()));
            stmt.setString(2, cobranca.getNfcobranca());
            stmt.setInt(3, cobranca.getCliente().getClienteido());
            stmt.setDate(4, new java.sql.Date(cobranca.getVencimento().getTime()));
            stmt.setInt(5, cobranca.getSetor().getSetorido());
            stmt.setString(6, cobranca.getObservacao());

            if (cobranca.getPagamento() == null) {
                stmt.setNull(7, 0);
            } else {
                stmt.setInt(7, cobranca.getPagamento().getPagamentoido());
            }

            stmt.setInt(8, cobranca.getSituacao().getSituacaoido());

//            System.out.println("Data: " + cobranca.getDatapagamento());
            if (cobranca.getDatapagamento() == null) {
                stmt.setDate(9, null);
            } else {
                stmt.setDate(9, new java.sql.Date(cobranca.getDatapagamento().getTime()));
            }

            if (cobranca.getTipopagamento() == null) {
                stmt.setNull(10, 0);
            } else {
                stmt.setInt(10, cobranca.getTipopagamento().getTipopagamentoido());
            }

            stmt.setInt(11, cobranca.getCobrancaido());

            stmt.executeUpdate();

            try {
                stmt = conn.prepareCall(sqll);
                System.out.println("Valor:" + cobranca.getValor());
                stmt.setDouble(1, cobranca.getValor());

                System.out.println("Cobrancaido " + cobranca.getCobrancaido());
                stmt.setInt(2, cobranca.getCobrancaido());

                stmt.executeUpdate();
            } catch (Exception ex) {
                System.out.println("Problemas ao alterar cobranca DAO inserir! Erro:" + ex.getMessage());
                ex.printStackTrace();
                return false;
            }

            return true;

        } catch (Exception ex) {
            System.out.println("Problemas ao alterar cobranca DAO! Erro:" + ex.getMessage());
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
