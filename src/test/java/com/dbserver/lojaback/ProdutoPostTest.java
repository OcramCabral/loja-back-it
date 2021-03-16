package com.dbserver.lojaback;

import com.dbserver.lojaback.models.builder.Produto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProdutoPostTest extends BaseTest {

    private Integer id;
    private boolean execute;


/*
    ######Não utilizar esta anotação, procurar fazer o delete dentro do método#####
    @AfterMethod
    public void afterEach(){
        if(execute){
            given().when().delete("/produto/{id}", id).then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
            id = null;
        }
        execute = true;
    }*/

    @Test
    public void deveriaCadastrarUmNovoProduto() {

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
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoSemDescricao() {

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Celular").
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        Response resp = given().
                contentType(ContentType.JSON).
                body(produto).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();

        Assert.assertEquals(resp.getBody().asString(), "[\"O nome é obrigatório\"]");
        execute=false;
    }

    @Test
    //Problema Aceita nome " " - Prever no teste!(Moacir)
    public void naoDeveriaCadastrarUmNovoProdutoNomeVazio() {

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome(" ").
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
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                path("id");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoMenorQueCinco(){

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Celu").
                descricao("Moto G").
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        Response resp =  given().
                contentType(ContentType.JSON).
                body(produto).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();

        Assert.assertEquals(resp.getBody().asString(), "[\"A descrição deve ter no mínimo 5 caracteres e no máximo 50 caracteres.\"]");
        execute = false;
    }

    @Test
    public void deveriaCadastrarUmNovoProdutoDescricaoIgualCinco(){

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Celul").
                descricao("Moto G").
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
                extract().
                path("id");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoIgualCinquenta(){

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao(RandomStringUtils.randomAlphabetic(50)).
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
                extract().
                path("id");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoMaiorCinquenta(){

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao(RandomStringUtils.randomAlphabetic(51)).
                precoUnitario(8000d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        Response resp =  given().
                contentType(ContentType.JSON).
                body(produto).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();
        Assert.assertEquals(resp.getBody().asString(), "[\"A descrição deve ter no mínimo 5 caracteres e no máximo 50 caracteres.\"]");
        execute = false;
    }

    @Test
    //Problema aceita quantidade menor de que 0 - Prever no teste!(Moacir)
    public void naoDeveriaCadastrarUmNovoProdutoQuantidadeMenorZero() {

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(8000d).
                quantidade(Integer.MIN_VALUE).
                build();

        id =  given().
                contentType(ContentType.JSON).
                body(produto).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                path("id");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoQuantidadeIgualUm()  {

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(8000d).
                quantidade(1).
                build();

        id =  given().
                contentType(ContentType.JSON).
                body(produto).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                path("id");

    }
    @Test
    public void deveriaCadastrarUmNovoProdutoQuantidadeIgualMaiorInteger() {
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(8000d).
                quantidade(Integer.MAX_VALUE).
                build();

        id =  given().
                contentType(ContentType.JSON).
                body(produto).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                path("id");
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoPrecoUnitarioMenorZero() {

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(-1d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        Response resp =
                given().
                        contentType(ContentType.JSON).
                        body(produto).
                        post("/produto").
                        then().log().all().
                        assertThat().
                        statusCode(HttpStatus.SC_BAD_REQUEST).
                        extract().
                        response();

        Assert.assertEquals(resp.getBody().asString(), "[\"O preço unitário está zerado, informe um valor válido\"]");
        execute =false;
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoPrecoUnitarioIgualZero() {

        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario(0d).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();

        Response resp =
                given().
                        contentType(ContentType.JSON).
                        body(produto).
                        post("/produto").
                        then().log().all().
                        assertThat().
                        statusCode(HttpStatus.SC_BAD_REQUEST).
                        extract().
                        response();

        Assert.assertEquals(resp.getBody().asString(), "[\"O preço unitário está zerado, informe um valor válido\"]");
        execute =false;

    }

    @Test
    public void deveriaCadastrarUmNovoProdutoPrecoUnitarioIgualMaiorLong() {
        Produto produto = Produto.builder().
                id(Integer.parseInt(RandomStringUtils.randomNumeric(4))).
                nome("Moto G").
                descricao("Samsung Motorola Xiaomi Apple and Samsung Motorola").
                precoUnitario((double) Integer.MAX_VALUE).
                quantidade(Integer.parseInt(RandomStringUtils.randomNumeric(3))).
                build();
        id =  given().
                contentType(ContentType.JSON).
                body(produto).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                path("id");
    }
    
}
