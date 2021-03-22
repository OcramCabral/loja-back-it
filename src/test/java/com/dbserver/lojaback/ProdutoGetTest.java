package com.dbserver.lojaback;


import com.dbserver.lojaback.builder.Produto;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;

public class ProdutoGetTest extends BaseTest {

    @Test
    public void deveListarProdutos(){

        Response resp = given().
                basePath(basePath).
                when().log().all().
                get("/produtos").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).extract().response();

        List<Produto> listaProdutoModels = resp.jsonPath().getList("", Produto.class);

        Assert.assertEquals(listaProdutoModels.get(0).getDescricao(), "Samsung A01");
    }

    @Test
    public void devePesquisarProdutoIdValido(){
        given().
                basePath(basePath).
                when().log().all().
                get("/produto/{id}", 2).
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                response();

    }

    @Test
    public void devePesquisarProdutoIdInvalido(){
        Response resp =
                given().
                        basePath(basePath).
                        when().log().all().
                        get("/produto/{id}", Long.MAX_VALUE).
                        then().log().all().
                        assertThat().
                        statusCode(HttpStatus.SC_NOT_FOUND).
                        extract().
                        response();
        Assert.assertEquals(resp.getBody().asString(), "O produto informado não foi encontrado.");
    }
}

