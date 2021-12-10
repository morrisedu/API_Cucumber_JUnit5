@non-ui @api @spartans
Feature: Spartans API
  # Enter feature description here

  Background:
    Given the baseUri and basePath are set

  Scenario: Spartans REST API app is running
    When user sends a GET request to the "/hello" endpoint
    Then user should get the status code 200
    And the response format should be "text/plain;charset=UTF-8"
    And returned response body contains the text "Hello from Sparta"

  Scenario: Endpoint /spartans to get all data is running
    And user requests for "JSON" response payload
    # And I set the "Accept" header to "application/json"
    When user sends a GET request to the "/spartans" endpoint
    Then user should get the status code 200
    And the response format should be "application/json"
    
  Scenario Outline: Should get desired data formats from calling /spartans
    And user requests for "<requested_format>" response payload
    When user sends a GET request to the "/spartans" endpoint
    Then user should get the status code 200
    And the response format should be "<expectedContentTypeHeader>"
    
    Examples: 
      |   requested_format    |   expectedContentTypeHeader   |
      |     JSON              |          application/json     |
      |     XML               |          application/xml      |

  Scenario: Should be able to add valid data to the Spartans app via the POST /spartans REST API endpoint
    # Set content type, provide JSON body and send request
    And user sends data in JSON format
    And user sends below data
      |   name    |   Kimberly    |
      |   gender  |   Female      |
      |   phone   |   9876543210  |
    When user sends a POST request to "/spartans" endpoint
    Then user should get the status code 201
#    And the success field value is "A Spartan is Born!"
    And the field value under path "success" should be equal to "A Spartan is Born!"
    And the field value under path "data.name" should be equal to "Kimberly"
    And the field value under path "data.gender" should be equal to "Female"
    And the field value under path "data.phone.toLong()" should be equal to "9876543210"


