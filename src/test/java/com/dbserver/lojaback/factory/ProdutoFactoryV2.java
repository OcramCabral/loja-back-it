package com.dbserver.lojaback.factory;

import com.dbserver.lojaback.models.builder.Produto;
import org.apache.commons.lang3.RandomStringUtils;

public class ProdutoFactoryV2 {

    public ProdutoFactoryV2(){}

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
