Feature: Application login

  @FlightBooking_SearchFLights
  Scenario Outline: Flight Page Navigation
    Given User is on MakeMyTrip landing page
    When User Enters "<FromLocation>" and "<ToLocation>"
    And User Selects DepartureDate and ReturnDate
    And User Clicks on Search Button
    Then Search page is displayed
    And User Close the WebPage
    Examples:
      | FromLocation | ToLocation |
      | MAA          | HYD        |