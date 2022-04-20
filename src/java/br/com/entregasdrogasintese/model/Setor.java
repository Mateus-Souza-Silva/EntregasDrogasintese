package br.com.entregasdrogasintese.model;

public class Setor {
    private Integer setorido;
    private String descricao;

    public Setor() {
    }

    public Setor(Integer setorido, String descricao) {
        this.setorido = setorido;
        this.descricao = descricao;
    }

    public Setor(Integer setorido) {
        this.setorido = setorido;
    }

    /**
     * @return the setorido
     */
    public Integer getSetorido() {
        return setorido;
    }

    /**
     * @param setorido the setorido to set
     */
    public void setSetorido(Integer setorido) {
        this.setorido = setorido;
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
