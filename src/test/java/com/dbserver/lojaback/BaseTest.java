package com.dbserver.lojaback;


import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import org.testng.ITestListener;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import static io.restassured.RestAssured.*;


@Listeners({ExtentITestListenerAdapter.class})
public class BaseTest implements ITestListener {

    @BeforeClass
    public void setup(){
        baseURI = "http://localhost:8080/";
        basePath = "";
    }
}
