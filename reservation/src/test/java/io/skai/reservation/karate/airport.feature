Feature: Testing a AirportController with Karate

  Scenario: Testing a POST endpoint with request body
    Given url 'http://localhost:8080/api/airports'
    And request { id: '1' , name: 'International', countryCode: 'UA', city: 'Kyiv'}
    When method POST
    Then status 200
    And match $ == { id: "#notnull" , name: 'International', countryCode: 'UA', city: 'Kyiv'}

  Scenario: Testing the exact response of a GET endpoint
    Given url 'http://localhost:8080/api/airports'
    When method GET
    Then status 200
    And match $ contains { id: "#notnull" , name: 'International', countryCode: 'UA', city: 'Kyiv'}