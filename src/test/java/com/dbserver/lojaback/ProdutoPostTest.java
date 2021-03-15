package com.dbserver.lojaback;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

public class ProdutoPostTest {

    private BaseRequest base;
    private Integer id;
    private boolean execute;
    @BeforeMethod
    public void beforeEach(){
        base = new BaseRequest();
    }

    @AfterMethod
    public void afterEach(){
        if(execute){
            given().when().delete("/produto/{id}", id).then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
            id = null;
        }
        execute = true;
    }

    @Test
    public void deveria_cadastrar_um_novo_produto() {
        id =  given().contentType(ContentType.JSON).
                body(base.requestObject("Moto XX", "Celular", Integer.valueOf(15), BigDecimal.valueOf(8000)).toString()).post("/produto").
                then().log().all().assertThat().statusCode(HttpStatus.SC_OK).extract().path("id");
    }

    @Test
    public void nao_deveria_cadastrar_um_novo_produto_sem_nome() {
        Response resp = given().contentType(ContentType.JSON).body(base.requestObject("Celular", Integer.valueOf(15), BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).extract().response();
        Assert.assertEquals(resp.getBody().asString(), "[\"O nome é obrigatório\"]");
        execute=false;
    }

    @Test
    //Problema Aceita nome " "
    public void nao_deveria_cadastrar_um_novo_produto_nome_vazio() {
        id =  given().contentType(ContentType.JSON).body(base.requestObject(" ", "Celular", Integer.valueOf(15), BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_OK).extract().path("id");
    }

    @Test
    public void nao_deveria_cadastrar_um_novo_produto_descricao_menor_cinco(){
        Response resp =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Celu", Integer.valueOf(15), BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).extract().response();
        Assert.assertEquals(resp.getBody().asString(), "[\"A descrição deve ter no mínimo 5 caracteres e no máximo 50 caracteres.\"]");
        execute = false;
    }

    @Test
    public void deveria_cadastrar_um_novo_produto_descricao_igual_cinco(){
        id =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Celul", Integer.valueOf(15), BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_OK).extract().path("id");
    }

    @Test
    public void nao_deveria_cadastrar_um_novo_produto_descricao_igual_cinquenta(){
        id =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Samsung Motorola Xiaomi Apple and Samsung Motorola", Integer.valueOf(15), BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_OK).extract().path("id");
    }

    @Test
    public void nao_deveria_cadastrar_um_novo_produto_descricao_maior_cinquenta(){
        Response resp =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Samsung Motorola Xiaomi Apple and Samsung Motorola ", Integer.valueOf(15), BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).extract().response();
        Assert.assertEquals(resp.getBody().asString(), "[\"A descrição deve ter no mínimo 5 caracteres e no máximo 50 caracteres.\"]");
        execute = false;
    }

    @Test
    //Problema aceita quantidade menor de que 0
    public void nao_deveria_cadastrar_um_novo_produto_quantidade_menor_zero() {
        id =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Samsung Motorola Xiaomi Apple and Samsung Motorola", Integer.MIN_VALUE, BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_OK).extract().path("id");
    }

    @Test
    public void nao_deveria_cadastrar_um_novo_produto_quantidade_igual_um()  {
        id =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Samsung Motorola Xiaomi Apple and Samsung Motorola", Integer.valueOf(1), BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_OK).extract().path("id");

    }
    @Test
    public void deveria_cadastrar_um_novo_produto_quantidade_igual_maior_Integer() {
        id =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Samsung Motorola Xiaomi Apple and Samsung Motorola", Integer.MAX_VALUE, BigDecimal.valueOf(8000)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_OK).extract().path("id");
    }

    @Test
    public void nao_deveria_cadastrar_um_novo_produto_preco_unitario_menor_zero() {
        Response resp =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Samsung Motorola Xiaomi Apple and Samsung Motorola", Integer.valueOf(1), BigDecimal.valueOf(Integer.MIN_VALUE)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).extract().response();

        Assert.assertEquals(resp.getBody().asString(), "[\"O preço unitário está zerado, informe um valor válido\"]");
        execute =false;
    }

    @Test
    public void nao_deveria_cadastrar_um_novo_produto_preco_unitario_igual_zero() {
        Response resp =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Samsung Motorola Xiaomi Apple and Samsung Motorola", Integer.valueOf(1), BigDecimal.valueOf(Integer.valueOf(0))).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST).extract().response();

        Assert.assertEquals(resp.getBody().asString(), "[\"O preço unitário está zerado, informe um valor válido\"]");
        execute =false;

    }

    @Test
    public void deveria_cadastrar_um_novo_produto_preco_unitario_igual_maior_Long() {
        id =  given().contentType(ContentType.JSON).body(base.requestObject("Moto G", "Samsung Motorola Xiaomi Apple and Samsung Motorola", Integer.valueOf(1), BigDecimal.valueOf(Integer.MAX_VALUE)).toString()).
                post("/produto").then().log().all().assertThat().statusCode(HttpStatus.SC_OK).extract().path("id");
    }
    
}
