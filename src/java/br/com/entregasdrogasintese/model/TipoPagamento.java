package br.com.entregasdrogasintese.model;

public class TipoPagamento {
    private Integer tipopagamentoido;
    private String descricao;

    public TipoPagamento() {
    }

    public TipoPagamento(Integer tipopagamentoido, String descricao) {
        this.tipopagamentoido = tipopagamentoido;
        this.descricao = descricao;
    }

    public TipoPagamento(Integer tipopagamentoido) {
        this.tipopagamentoido = tipopagamentoido;
    }

    /**
     * @return the tipopagamentoido
     */
    public Integer getTipopagamentoido() {
        return tipopagamentoido;
    }

    /**
     * @param tipopagamentoido the tipopagamentoido to set
     */
    public void setTipopagamentoido(Integer tipopagamentoido) {
        this.tipopagamentoido = tipopagamentoido;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
