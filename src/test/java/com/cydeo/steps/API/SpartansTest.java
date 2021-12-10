package com.cydeo.steps.API;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static com.cydeo.utility.ConfigReader.confRead;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SpartansTest{
    RequestSpecification given_part;
    Response response;
    ValidatableResponse then_part;

    @Given("the baseUri and basePath are set")
    public void theBaseUriAndBasePathAreSet() {
        baseURI = "http://" + confRead("vm_ip") + ":8000";
        basePath = "/api";

        given_part = given()
                .log().uri()
                .contentType(ContentType.JSON);
    }

    @When("user sends a GET request to the {string} endpoint")
    public void iSendAGetRequestToTheEndpoint(String endpoint) {
        response = given_part
                .when()
                .get(endpoint);

        then_part = response
                .then();
    }

    @Then("user should get the status code {int}")
    public void iShouldTheStatusCode(int status_code) {
        then_part = response.then().statusCode(status_code);
    }

    @And("returned response body contains the text {string}")
    public void returnedResponseContainsTheText(String resp_txt) {
        then_part.body(is(resp_txt));
    }

    @And("the response format should be {string}")
    public void theReponseFormatShouldBe(String content_type) {
        then_part.contentType(content_type);
    }

    @And("user requests for {string} response payload")
    public void iAskForResponsePayload(String response_format) {
        switch (response_format) {
            case "JSON":
            default:
                given_part.accept(ContentType.JSON);
                break;
            case "XML":
                given_part.accept(ContentType.XML);
                break;
        }
    }

    @And("user sends data in JSON format")
    public void userSendsDataInJSONFormat() {
        given_part.contentType(ContentType.JSON);
    }

    @And("user sends below data")
    public void userSendsBelowData(Map<String, Object> request_body) {
        given_part.body(request_body);
    }

    @When("user sends a POST request to {string} endpoint")
    public void userSendsAPOSTRequestToEndpoint(String endpoint) {
        response = given_part
                .when()
                .post(endpoint);
    }

    @And("the success field value is {string}")
    public void theSuccessFieldValueIs(String txt) {
        then_part.body("success", is(txt));
    }

    @And("the field value under path {string} should be equal to {string}")
    public void theFieldValueUnderPathShouldBeEqualTo(String json_path, String txt) {
        then_part.body(json_path, is(txt));
    }

    @And("the field value under path {string} should be equal to {long}")
    public void theFieldValueUnderPathShouldBeEqualTo(String json_path, long num) {
        then_part.body(json_path, is(num));

    }
}
