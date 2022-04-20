package br.com.entregasdrogasintese.model;

import java.util.Date;

public class Entrega {
    private Integer entregaido;
    private Date dataentrega;
    private String produtos;
    private Double valor;
    private String recebedor;
    private String observacao;
    private Entregador entregador;
    private Pagamento pagamento;
    private Situacao situacao;
    private Cliente cliente;
    private Integer qtdCadastrada;
    private Integer qtdFinalizada;
    private Date datapagamento;

    public Entrega() {
    }

    public Entrega(Integer entregaido, Date dataentrega, String produtos, Double valor, String recebedor, String observacao, Entregador entregador, Pagamento pagamento, Situacao situacao, Cliente cliente, Integer qtdCadastrada, Integer qtdFinalizada, Date datapagamento) {
        this.entregaido = entregaido;
        this.dataentrega = dataentrega;
        this.produtos = produtos;
        this.valor = valor;
        this.recebedor = recebedor;
        this.observacao = observacao;
        this.entregador = entregador;
        this.pagamento = pagamento;
        this.situacao = situacao;
        this.cliente = cliente;
        this.qtdCadastrada = qtdCadastrada;
        this.qtdFinalizada = qtdFinalizada;
        this.datapagamento = datapagamento;
    }

    /**
     * @return the entregaido
     */
    public Integer getEntregaido() {
        return entregaido;
    }

    /**
     * @param entregaido the entregaido to set
     */
    public void setEntregaido(Integer entregaido) {
        this.entregaido = entregaido;
    }

    /**
     * @return the dataentrega
     */
    public Date getDataentrega() {
        return dataentrega;
    }

    /**
     * @param dataentrega the dataentrega to set
     */
    public void setDataentrega(Date dataentrega) {
        this.dataentrega = dataentrega;
    }

    /**
     * @return the produtos
     */
    public String getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(String produtos) {
        this.produtos = produtos;
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
     * @return the recebedor
     */
    public String getRecebedor() {
        return recebedor;
    }

    /**
     * @param recebedor the recebedor to set
     */
    public void setRecebedor(String recebedor) {
        this.recebedor = recebedor;
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
     * @return the entregador
     */
    public Entregador getEntregador() {
        return entregador;
    }

    /**
     * @param entregador the entregador to set
     */
    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
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
     * @return the qtdCadastrada
     */
    public Integer getQtdCadastrada() {
        return qtdCadastrada;
    }

    /**
     * @param qtdCadastrada the qtdCadastrada to set
     */
    public void setQtdCadastrada(Integer qtdCadastrada) {
        this.qtdCadastrada = qtdCadastrada;
    }

    /**
     * @return the qtdFinalizada
     */
    public Integer getQtdFinalizada() {
        return qtdFinalizada;
    }

    /**
     * @param qtdFinalizada the qtdFinalizada to set
     */
    public void setQtdFinalizada(Integer qtdFinalizada) {
        this.qtdFinalizada = qtdFinalizada;
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
    
    
}