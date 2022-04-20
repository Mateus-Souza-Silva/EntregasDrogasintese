package br.com.entregasdrogasintese.dao;

import java.util.List;

public interface GenericDAO {
    
    public Boolean cadastrar(Object object); //o metodo cadastra so em V ou F

    public List<Object> listar(); //o retorno é em objetos

    public void excluir(int idObject); //não tem retorno

    public Object carregar(int idObject); //ele retorna e recebe o objeto

    public Boolean alterar(Object object); //ele retorna o Boolean e recebe o objeto
}
