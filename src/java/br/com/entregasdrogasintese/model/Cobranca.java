package br.com.entregasdrogasintese.model;

import java.util.Date;

public class Cobranca {

    private Integer cobrancaido;
    private Date datacobranca;
    private String nfcobranca;
    private Cliente cliente;
    private Double valor;
    private Date vencimento;
    private Setor setor;
    private String observacao;
    private Pagamento pagamento;
    private Situacao situacao;
    private Date datapagamento;
    private TipoPagamento tipopagamento;
    private Integer qtdCobranca;
    private Integer totalRegistros;

    public Cobranca() {
    }

    public Cobranca(Integer cobrancaido, Date datacobranca, String nfcobranca, Cliente cliente, Double valor, Date vencimento, Setor setor, String observacao, Pagamento pagamento, Situacao situacao, Date datapagamento, TipoPagamento tipopagamento, Integer qtdCobranca) {
        this.cobrancaido = cobrancaido;
        this.datacobranca = datacobranca;
        this.nfcobranca = nfcobranca;
        this.cliente = cliente;
        this.valor = valor;
        this.vencimento = vencimento;
        this.setor = setor;
        this.observacao = observacao;
        this.pagamento = pagamento;
        this.situacao = situacao;
        this.datapagamento = datapagamento;
        this.tipopagamento = tipopagamento;
        this.qtdCobranca = qtdCobranca;
    }

    /**
     * @return the cobrancaido
     */
    public Integer getCobrancaido() {
        return cobrancaido;
    }

    /**
     * @param cobrancaido the cobrancaido to set
     */
    public void setCobrancaido(Integer cobrancaido) {
        this.cobrancaido = cobrancaido;
    }

    /**
     * @return the datacobranca
     */
    public Date getDatacobranca() {
        return datacobranca;
    }

    /**
     * @param datacobranca the datacobranca to set
     */
    public void setDatacobranca(Date datacobranca) {
        this.datacobranca = datacobranca;
    }

    /**
     * @return the nfcobranca
     */
    public String getNfcobranca() {
        return nfcobranca;
    }

    /**
     * @param nfcobranca the nfcobranca to set
     */
    public void setNfcobranca(String nfcobranca) {
        this.nfcobranca = nfcobranca;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the vencimento
     */
    public Date getVencimento() {
        return vencimento;
    }

    /**
     * @param vencimento the vencimento to set
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    /**
     * @return the setor
     */
    public Setor getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the pagamento
     */
    public Pagamento getPagamento() {
        return pagamento;
    }

    /**
     * @param pagamento the pagamento to set
     */
    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    /**
     * @return the situacao
     */
    public Situacao getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the datapagamento
     */
    public Date getDatapagamento() {
        return datapagamento;
    }

    /**
     * @param datapagamento the datapagamento to set
     */
    public void setDatapagamento(Date datapagamento) {
        this.datapagamento = datapagamento;
    }

    /**
     * @return the tipopagamento
     */
    public TipoPagamento getTipopagamento() {
        return tipopagamento;
    }

    /**
     * @param tipopagamento the tipopagamento to set
     */
    public void setTipopagamento(TipoPagamento tipopagamento) {
        this.tipopagamento = tipopagamento;
    }

    /**
     * @return the QtdCobranca
     */
    public Integer getQtdCobranca() {
        return qtdCobranca;
    }

    /**
     * @param QtdCobranca the QtdCobranca to set
     */
    public void setQtdCobranca(Integer QtdCobranca) {
        this.qtdCobranca = QtdCobranca;
    }
    
    /**
     * @return the totalRegistros
     */
    public Integer getTotalRegistros() {
        return totalRegistros;
    }

    /**
     * @param totalRegistros the totalRegistros to set
     */
    public void setTotalRegistros(Integer totalRegistros) {
        this.totalRegistros = totalRegistros;
    }     
    
}
