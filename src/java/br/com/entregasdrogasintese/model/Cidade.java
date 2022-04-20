package br.com.entregasdrogasintese.model;

public class Cidade{
    private Integer cidadeido;
    private String nome;

    public Cidade() {
    }

    public Cidade(Integer cidadeido, String nome) {
        this.cidadeido = cidadeido;
        this.nome = nome;
    }

    public Cidade(Integer cidadeido){
        this.cidadeido = cidadeido;
    }
    
    /**
     * @return the cidadeido
     */
    public Integer getCidadeido() {
        return cidadeido;
    }

    /**
     * @param cidadeido the cidadeido to set
     */
    public void setCidadeido(Integer cidadeido) {
        this.cidadeido = cidadeido;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
        
}
