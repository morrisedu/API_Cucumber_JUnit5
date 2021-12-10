package com.cydeo.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.cydeo.utility.ConfigReader.confRead;
import static io.restassured.RestAssured.*;

public class Hooks {
    @Before("@spartans")
    public static void spartansSetup() {
        baseURI = "http://" + confRead("vm_ip") + ":8000";
        basePath = "/api";
    }

    @After("spartans")
    public static void tearDownAPI() {
        reset();
    }
}
