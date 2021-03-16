package com.dbserver.lojaback;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import static io.restassured.RestAssured.given;

public class ProdutoDeleteTest extends BaseTest {

    @Test
    public void deveriaExcluirProdutoExistente(){
        given().when().delete("/produto/{id}", 21).then().log().all().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void naoDeveriaExcluirProdutoNaoExistente(){
        given().when().delete("/produto/{id}", 21).then().log().all().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }



}
