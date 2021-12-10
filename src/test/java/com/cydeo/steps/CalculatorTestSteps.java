package com.cydeo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTestSteps {
    @Given("calculator is open")
    public void calculatorIsOpen() {
        System.out.println("Calculator is open");
    }

    @When("I add one with two")
    public void iAddOneWithTwo() {
        System.out.println("Add one and two");
        assertEquals(5, 5);
    }

    @Then("I should get three")
    public void iShouldGetThree() {
        System.out.println("I should get three");
    }

    @When("I add one with {int}")
    public void iAddOneWith(int num) {
        System.out.println("Add one with" + num);
    }

    @Then("I should get {int}")
    public void iShouldGet(int num) {
        System.out.println("I should get" + num);
    }
}
