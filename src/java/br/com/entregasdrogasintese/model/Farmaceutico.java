package br.com.entregasdrogasintese.model;

import java.util.Date;

public class Farmaceutico extends Pessoa{
    private Integer farmaceuticoido;
    private String funcao;
    private String senha;

    public Farmaceutico() {
    }

    public Farmaceutico(Integer farmaceuticoido, String funcao, String senha) {
        this.farmaceuticoido = farmaceuticoido;
        this.funcao = funcao;
        this.senha = senha;
    }

    public Farmaceutico(Integer farmaceuticoido, String funcao, String senha, Integer pessoaido, String nome, Integer idade, Date datanascimento,String nivel) {
        super(pessoaido, nome, idade, datanascimento, nivel);
        this.farmaceuticoido = farmaceuticoido;
        this.funcao = funcao;
        this.senha = senha;
    }

    /**
     * @return the farmaceuticoido
     */
    public Integer getFarmaceuticoido() {
        return farmaceuticoido;
    }

    /**
     * @param farmaceuticoido the farmaceuticoido to set
     */
    public void setFarmaceuticoido(Integer farmaceuticoido) {
        this.farmaceuticoido = farmaceuticoido;
    }

    /**
     * @return the funcao
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * @param funcao the funcao to set
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
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
