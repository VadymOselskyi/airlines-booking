Feature: the airport can be retrieved

  Scenario: create and retrieve
    Given create tickets
      | id   | firstName | lastName | email             | phone     | seatNumber | price | departureDate       | departureAirportName | departureCountryCode | departureCity | arrivalDate         | arrivalAirportName | arrivalCountryCode | arrivalCity |
      | null | Tony      | Stark    | ironman@gmail.com | +12340857 | B12        | 250   | 2023-05-10 16:40:00 | Main International   | US                   | New York      | 2023-05-11 12:02:00 | International      | UA                 | Kyiv        |
      | null | Steve     | Rodgers  | ironman@gmail.com | +12340857 | B13        | 250   | 2023-05-10 16:40:00 | Main International   | US                   | New York      | 2023-05-11 12:02:00 | International      | UA                 | Kyiv        |
    And wait 7 seconds
    When client get tickets
    Then client receives list of Tickets
      | id   | firstName | lastName | email             | phone     | seatNumber | price | departureDate       | departureAirportName | departureCountryCode | departureCity | arrivalDate         | arrivalAirportName | arrivalCountryCode | arrivalCity |
      | null | Tony      | Stark    | ironman@gmail.com | +12340857 | B12        | 250   | 2023-05-10 16:40:00 | Main International   | US                   | New York      | 2023-05-11 12:02:00 | International      | UA                 | Kyiv        |
      | null | Steve     | Rodgers  | ironman@gmail.com | +12340857 | B13        | 250   | 2023-05-10 16:40:00 | Main International   | US                   | New York      | 2023-05-11 12:02:00 | International      | UA                 | Kyiv        |