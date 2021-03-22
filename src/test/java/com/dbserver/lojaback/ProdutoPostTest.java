package com.dbserver.lojaback;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProdutoPostTest extends BaseTest {

    @Test
    public void deveCadastrarUmNovoProduto() {

         given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", "Celular", 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveCadastrarUmNovoProdutoSemDescricao() {

        Response resp = given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();

        Assert.assertEquals(resp.getBody().asString(), "[\"O nome é obrigatório\"]");

    }

    @Test
    public void naoDeveCadastrarUmNovoProdutoNomeVazio() {
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), " ", 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void naoDeveCadastrarUmNovoProdutoDescricaoMenorQueCinco(){

        Response resp =  given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", RandomStringUtils.randomAlphabetic(4), 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();

        Assert.assertEquals(resp.getBody().asString(), "[\"A descrição deve ter no mínimo 5 caracteres e no máximo 50 caracteres.\"]");
    }

    @Test
    public void deveCadastrarUmNovoProdutoDescricaoIgualCinco(){

        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", RandomStringUtils.randomAlphabetic(5),8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveCadastrarUmNovoProdutoDescricaoIgualCinquenta(){
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", RandomStringUtils.randomAlphabetic(50), 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveCadastrarUmNovoProdutoDescricaoMaiorCinquenta(){
        Response resp =  given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", RandomStringUtils.randomAlphabetic(51), 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                response();
        Assert.assertEquals(resp.getBody().asString(), "[\"A descrição deve ter no mínimo 5 caracteres e no máximo 50 caracteres.\"]");
    }

    @Test
    public void naoDeveCadastrarUmNovoProdutoQuantidadeMenorZero() {

        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", 8000d, -1)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract();
    }

    @Test
    public void deveCadastrarUmNovoProdutoQuantidadeIgualUm()  {
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", 8000d, 1)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);

    }
    @Test
    public void deveCadastrarUmNovoProdutoQuantidadeIgualMaiorInteger() {
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", 8000d, Integer.MAX_VALUE)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveCadastrarUmNovoProdutoPrecoUnitarioMenorZero() {

        Response resp =
                given().
                        contentType(ContentType.JSON).
                        body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", -1d, 15)).
                        post("/produto").
                        then().log().all().
                        assertThat().
                        statusCode(HttpStatus.SC_BAD_REQUEST).
                        extract().
                        response();

        Assert.assertEquals(resp.getBody().asString(), "[\"O preço unitário está zerado, informe um valor válido\"]");

    }

    @Test
    public void naoDeveCadastrarUmNovoProdutoPrecoUnitarioIgualZero() {

        Response resp =
                given().
                        contentType(ContentType.JSON).
                        body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", 0d, 15)).
                        post("/produto").
                        then().log().all().
                        assertThat().
                        statusCode(HttpStatus.SC_BAD_REQUEST).
                        extract().
                        response();

        Assert.assertEquals(resp.getBody().asString(), "[\"O preço unitário está zerado, informe um valor válido\"]");


    }

    @Test
    public void deveCadastrarUmNovoProdutoPrecoUnitarioIgualMaiorInteger() {
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", (double) Integer.MAX_VALUE, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                path("id");
    }
    
}
