package br.com.entregasdrogasintese.model;

public class Pagamento {
    private Integer pagamentoido;
    private String descricao;

    public Pagamento() {
    }
    
    public Pagamento(Integer pagamentoido){
        this.pagamentoido = pagamentoido;
    }

    public Pagamento(Integer pagamentoido, String descricao) {
        this.pagamentoido = pagamentoido;
        this.descricao = descricao;
    }

    /**
     * @return the pagamentoido
     */
    public Integer getPagamentoido() {
        return pagamentoido;
    }

    /**
     * @param pagamentoido the pagamentoido to set
     */
    public void setPagamentoido(Integer pagamentoido) {
        this.pagamentoido = pagamentoido;
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
