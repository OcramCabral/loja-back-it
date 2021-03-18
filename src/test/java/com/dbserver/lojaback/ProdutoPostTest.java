package com.dbserver.lojaback;

import com.dbserver.lojaback.factory.ProdutoFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProdutoPostTest extends BaseTest {

    private ProdutoFactory produto;

    @BeforeClass
    public void setup(){
       produto  = new ProdutoFactory();
    }


    @Test
    public void deveriaCadastrarUmNovoProduto() {

         given().
                contentType(ContentType.JSON).
                body(produto.completo_todas_informacoes()).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoSemDescricao() {

        Response resp = given().
                contentType(ContentType.JSON).
                body(produto.sem_descricao()).
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
                body(produto.nome_vazio()).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoMenorQueCinco(){

        Response resp =  given().
                contentType(ContentType.JSON).
                body(produto.descricao_menor_que_limite_inferior()).
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
                body(produto.descricao_com_limite_inferior()).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoIgualCinquenta(){
        given().
                contentType(ContentType.JSON).
                body(produto.descricao_com_limite_superior()).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaCadastrarUmNovoProdutoDescricaoMaiorCinquenta(){
        Response resp =  given().
                contentType(ContentType.JSON).
                body(produto.descricao_maior_limite_superior()).
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
                body(produto.quantidade_menor_limite_inferior()).
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
                body(produto.quantidade_igual_limite_inferior()).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);

    }
    @Test
    public void deveriaCadastrarUmNovoProdutoQuantidadeIgualMaiorInteger() {
        given().
                contentType(ContentType.JSON).
                body(produto.quantidade_igual_limite_superior()).
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
                        body(produto.preco_menor_limite_inferior()).
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
                        body(produto.preco_igual_zero()).
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
                body(produto.preco_igual_limite_superior()).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().
                path("id");
    }
    
}
