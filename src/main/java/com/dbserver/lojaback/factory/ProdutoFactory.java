package com.dbserver.lojaback.factory;

import com.dbserver.lojaback.builder.Produto;

public class ProdutoFactory {

    public ProdutoFactory(){}

    public Produto factory(Integer id, String nome, String descricao, Double precoUnitario, Integer quantidade){
        Produto produto = Produto.builder().
                id(id).
                nome(nome).
                descricao(descricao).
                precoUnitario(precoUnitario).
                quantidade(quantidade).
                build();
        return produto;
    }

    public Produto factory(Integer id, String nome, Double precoUnitario, Integer quantidade){
        Produto produto = Produto.builder().
                id(id).
                nome(nome).
                precoUnitario(precoUnitario).
                quantidade(quantidade).
                build();
        return produto;
    }

}
