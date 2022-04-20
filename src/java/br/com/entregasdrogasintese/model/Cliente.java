package br.com.entregasdrogasintese.model;

import java.util.Date;

public class Cliente extends Pessoa{
    private Integer clienteido;
    private String cep;
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private Cidade cidade;
    private String telefone;

    public Cliente() {
    }

    public Cliente(Integer clienteido, String cep, String logradouro, String bairro, String numero, String complemento, Cidade cidade, String telefone) {
        this.clienteido = clienteido;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public Cliente(Integer clienteido, String cep, String logradouro, String bairro, String numero, String complemento, Cidade cidade, String telefone, Integer pessoaido, String nome, Integer idade, Date datanascimento, String nivel) {
        super(pessoaido, nome, idade, datanascimento, nivel);
        this.clienteido = clienteido;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
    }

    public Cliente(Integer clienteido){
        this.clienteido = clienteido;
    }
    
    public Integer getClienteido() {
        return clienteido;
    }

    public void setClienteido(Integer clienteido) {
        this.clienteido = clienteido;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}