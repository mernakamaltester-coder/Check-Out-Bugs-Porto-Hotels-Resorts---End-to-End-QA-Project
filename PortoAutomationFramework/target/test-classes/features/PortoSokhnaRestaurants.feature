Feature: Porto Sokhna Hotel Dining Options

  @Regression @UI @Dining
  Scenario: Verify Restaurants Page displays dining choices for Porto Sokhna
    Given the user is on the Porto Hotels home page
    When the user navigates to "Porto Sokhna Hotel & Spa" from the Hotels & Resorts menu
    And the user clicks on the "Restaurants" tab in the sub-navigation menu
    Then the restaurants page should load successfully
    And at least one restaurant profile should be listed with a valid name and description