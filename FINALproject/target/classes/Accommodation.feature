Feature: Porto Hotels Tests

  Scenario: Verify Accommodation Page for Porto Marina Resort
    Given User navigated to Porto Hotels website for Accommodation
    When User navigates to Accommodation page of Porto Marina Resort
    Then Room information should be displayed correctly
    When User selects a room and completes the booking
    Then Booking should be confirmed successfully

  Scenario: Verify Meetings & Events Page
    Given User navigated to Porto Hotels website for Meetings and Events
    When User navigates to Meetings and Events page of Porto Sokhna Hotel
    Then Venue details or capacity information should be displayed