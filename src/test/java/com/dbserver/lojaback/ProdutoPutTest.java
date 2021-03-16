package com.dbserver.lojaback;

import com.dbserver.lojaback.models.builder.Produto;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProdutoPutTest extends BaseTest{
    private Integer id;

    @Test
    public void deveriaAtualizarInformacoes() {

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Celular").
                descricao("Moto XX").
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        id =  given().
                contentType(ContentType.JSON).
                body(produto).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().path("id");

        Produto produtoAtualizado = Produto.builder().
                id(id).
                nome("Celular").
                descricao("Moto X").
                precoUnitario(2024d).
                quantidade(10).
                build();

        given().
                contentType(ContentType.JSON).
                body(produtoAtualizado).
                when().log().all().
                put("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaAtualizarInformacoesProdutoNaoExiste() {
        Produto produto = Produto.builder().
                id(9999999).
                nome("Celular").
                descricao("Moto X").
                precoUnitario(2024d).
                quantidade(10).
                build();
       given().
               contentType(ContentType.JSON).
               body(produto).
               when().log().all().
               put("/produto").
               then().log().all().
               assertThat().
               statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
