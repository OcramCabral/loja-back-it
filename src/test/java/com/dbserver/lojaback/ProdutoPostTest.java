package com.dbserver.lojaback;

import com.dbserver.lojaback.factory.ProdutoFactory;
import com.dbserver.lojaback.factory.ProdutoFactoryV2;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProdutoPostTest extends BaseTest {

    private ProdutoFactory produto;
    private ProdutoFactoryV2 prod;

    @BeforeClass
    public void setup(){
       produto  = new ProdutoFactory();
       prod = new ProdutoFactoryV2();
    }


    @Test
    public void deveriaCadastrarUmNovoProduto() {

         given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", "Celular", 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoSemDescricao() {

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
    //Problema Aceita nome " " - Prever no teste!(Moacir)
    public void naoDeveriaCadastrarUmNovoProdutoNomeVazio() {
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), " ", 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoMenorQueCinco(){

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
    public void deveriaCadastrarUmNovoProdutoDescricaoIgualCinco(){

        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", RandomStringUtils.randomAlphabetic(5),8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoIgualCinquenta(){
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", RandomStringUtils.randomAlphabetic(50), 8000d, 15)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoMaiorCinquenta(){
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
    //Problema aceita quantidade menor de que 0 - Prever no teste!(Moacir)
    public void naoDeveriaCadastrarUmNovoProdutoQuantidadeMenorZero() {

        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", 8000d, -1)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST).
                extract().
                path("id");
    }

    @Test
    public void deveriaCadastrarUmNovoProdutoQuantidadeIgualUm()  {
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", 8000d, 1)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);

    }
    @Test
    public void deveriaCadastrarUmNovoProdutoQuantidadeIgualMaiorInteger() {
        given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)), "Moto G", 8000d, Integer.MAX_VALUE)).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoPrecoUnitarioMenorZero() {

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
    public void naoDeveriaCadastrarUmNovoProdutoPrecoUnitarioIgualZero() {

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
    public void deveriaCadastrarUmNovoProdutoPrecoUnitarioIgualMaiorInteger() {
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
