package com.dbserver.lojaback;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ProdutoContratoTest extends BaseTest{

    @Test
    public void validaContrato(){
        String filePath = "src/test/resources/json_schemas/contratoV1.json";
        given().log().all().
                basePath(basePath).
                when().log().all().
                get("/produtos").
                then().log().all().
                statusCode(HttpStatus.SC_OK).
                body(matchesJsonSchema(new File(filePath)));
    }
}
