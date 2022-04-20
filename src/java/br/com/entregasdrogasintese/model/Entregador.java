package br.com.entregasdrogasintese.model;

import java.util.Date;

public class Entregador extends Pessoa{
    private Integer entregadorido;
    private String senha;

    public Entregador() {
    }

    public Entregador(Integer entregadorido, String senha) {
        this.entregadorido = entregadorido;
        this.senha = senha;
    }

    public Entregador(Integer pessoaido, String nome, Integer idade, Date datanascimento, String nivel, Integer entregadorido, String senha) {
        super(pessoaido, nome, idade, datanascimento, nivel);
        this.entregadorido = entregadorido;
        this.senha = senha;
    }

//    public Entregador(Integer entregadorido, String nome) {
//        super(nome);
//        this.entregadorido = entregadorido;
//    }
//    
    public Entregador(Integer entregadorido){
        this.entregadorido = entregadorido;
    }

    /**
     * @return the entregadorido
     */
    public Integer getEntregadorido() {
        return entregadorido;
    }

    /**
     * @param entregadorido the entregadorido to set
     */
    public void setEntregadorido(Integer entregadorido) {
        this.entregadorido = entregadorido;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
