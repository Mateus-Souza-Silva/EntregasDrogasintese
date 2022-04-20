package br.com.entregasdrogasintese.model;

public class Situacao {
    private Integer situacaoido;
    private String descricao;

    public Situacao() {
    }
    
    public Situacao(Integer situacaoido){
        this.situacaoido = situacaoido;
    }

    public Situacao(Integer situacaoido, String descricao) {
        this.situacaoido = situacaoido;
        this.descricao = descricao;
    }

    /**
     * @return the situacaoido
     */
    public Integer getSituacaoido() {
        return situacaoido;
    }

    /**
     * @param situacaoido the situacaoido to set
     */
    public void setSituacaoido(Integer situacaoido) {
        this.situacaoido = situacaoido;
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
