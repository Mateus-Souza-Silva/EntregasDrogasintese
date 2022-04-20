package br.com.entregasdrogasintese.model;

import java.util.Date;

public class Pessoa {
    private Integer pessoaido;
    private String nome;
    private Integer idade;
    private Date datanascimento;
    private String nivel;

    public Pessoa() {
    }

    public Pessoa(String nome) {        
        this.nome = nome;
    }
        

    public Pessoa(Integer pessoaido, String nome, Integer idade, Date datanascimento, String nivel) {
        this.pessoaido = pessoaido;
        this.nome = nome;
        this.idade = idade;
        this.datanascimento = datanascimento;
        this.nivel = nivel;
    }

    /**
     * @return the pessoaido
     */
    public Integer getPessoaido() {
        return pessoaido;
    }

    /**
     * @param pessoaido the pessoaido to set
     */
    public void setPessoaido(Integer pessoaido) {
        this.pessoaido = pessoaido;
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

    /**
     * @return the idade
     */
    public Integer getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    /**
     * @return the datanascimento
     */
    public Date getDatanascimento() {
        return datanascimento;
    }

    /**
     * @param datanascimento the datanascimento to set
     */
    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    /**
     * @return the nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * @param nivel the nivel to set
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
