Feature: the airport can be retrieved

  Scenario: create and retrieve
    Given create tickets
      | id | firstName | lastName | email             | phone     | seatNumber | price | departureDate    | departureAirportName | departureCountryCode | departureCity | arrivalDate      | arrivalAirportName | arrivalCountryCode | arrivalCity |
      |    | Tony      | Stark    | ironman@gmail.com | +12340857 | B12        | 250   | 2023-05-10 16:40 | Main International   | US                   | New York      | 2023-05-11 12:02 | International      | UA                 | Kyiv        |
      |    | Steve     | Rodgers  | ironman@gmail.com | +12340857 | B13        | 250   | 2023-05-10 16:40 | Main International   | US                   | New York      | 2023-05-11 12:02 | International      | UA                 | Kyiv        |
    When wait 7 seconds
    Then client can get all Tickets by email: "ironman@gmail.com"
      | id | firstName | lastName | email             | phone     | seatNumber | price  | departureDate    | departureAirportName | departureCountryCode | departureCity | arrivalDate      | arrivalAirportName | arrivalCountryCode | arrivalCity |
      | 1  | Tony      | Stark    | ironman@gmail.com | +12340857 | B12        | 250.00 | 2023-05-10 16:40 | Main International   | US                   | New York      | 2023-05-11 12:02 | International      | UA                 | Kyiv        |
      | 2  | Steve     | Rodgers  | ironman@gmail.com | +12340857 | B13        | 250.00 | 2023-05-10 16:40 | Main International   | US                   | New York      | 2023-05-11 12:02 | International      | UA                 | Kyiv        |