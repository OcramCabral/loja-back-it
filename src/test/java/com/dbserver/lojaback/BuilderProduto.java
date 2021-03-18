package com.dbserver.lojaback;

import com.dbserver.lojaback.models.builder.Produto;
import org.apache.commons.lang3.RandomStringUtils;

public class BuilderProduto {

    public BuilderProduto(){}

    public Produto completo_todas_informacoes(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Celular").
                descricao("Moto XX").
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        return produto;
    }

    public Produto sem_descricao(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Celular").
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        return produto;
    }

    public Produto nome_vazio(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome(" ").
                descricao("Moto XX").
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        return produto;
    }

    public Produto descricao_menor_que_limite_inferior(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Celu").
                descricao("Moto").
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        return produto;
    }

    public Produto descricao_com_limite_inferior(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Celul").
                descricao("Moto G").
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        return  produto;
    }

    public Produto descricao_com_limite_superior(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao(RandomStringUtils.randomAlphabetic(50)).
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();
        return produto;
    }

    public Produto descricao_maior_limite_superior(){

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao(RandomStringUtils.randomAlphabetic(51)).
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        return  produto;
    }

    public Produto quantidade_menor_limite_inferior(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(8000d).
                quantidade(Integer.MIN_VALUE).
                build();
        return produto;
    }

    public Produto quantidade_igual_limite_inferior(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(8000d).
                quantidade(1).
                build();
        return produto;
    }

    public Produto quantidade_igual_limite_superior(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(8000d).
                quantidade(Integer.MAX_VALUE).
                build();
        return produto;
    }

    public Produto preco_menor_limite_inferior(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(-1d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();
        return produto;
    }

    public Produto preco_igual_zero(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(0d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();
        return produto;
    }

    public Produto preco_igual_limite_superior(){
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario((double) Integer.MAX_VALUE).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();
        return produto;
    }
}
