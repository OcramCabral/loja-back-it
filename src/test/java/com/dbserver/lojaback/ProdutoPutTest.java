package com.dbserver.lojaback;

import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ProdutoPutTest extends BaseTest{
    private Integer id;

    @Test
    public void deveAtualizarInformacoes() {

        id =  given().
                contentType(ContentType.JSON).
                body(prod.factory(Integer.parseInt(RandomStringUtils.randomNumeric(4)),"Celular","Moto XX",8000d,Integer.parseInt(RandomStringUtils.randomNumeric(3)))).
                post("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK).
                extract().path("id");

        given().
                contentType(ContentType.JSON).
                body(prod.factory(id,"Celular","Moto XX",2024d,10)).
                when().log().all().
                put("/produto").
                then().log().all().
                assertThat().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveAtualizarInformacoesProdutoNaoExistente() {

       given().
               contentType(ContentType.JSON).
               body(prod.factory(9999999,"Celular","Moto X",2024d,10)).
               when().log().all().
               put("/produto").
               then().log().all().
               assertThat().
               statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
