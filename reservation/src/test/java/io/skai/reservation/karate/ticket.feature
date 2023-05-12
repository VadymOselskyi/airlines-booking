Feature: Testing a TicketController with Karate

  Scenario: Testing the exact response of a GET endpoint
    Given url 'http://localhost:8080/api/tickets/history?email=johnsmith@gmail.com'
    When method GET
    Then status 200
    And match $ contains {  firstName: 'Tony', lastName: 'Stark', email: 'ironman@gmail.com', phone: '380942847', seatNumber: 'B12', price: 120, departureDate: '2023-04-11T05:40:00', departureAirportName: 'NEW YORK INTERNATIONAL', departureCountryCode: 'US', departureCity: 'New York', arrivalDate: '2023-04-12T08:30:00', arrivalAirportName: 'LVIV INTERNATIONAL', arrivalCountryCode: 'UA' ,arrivalCity: 'Lviv'}