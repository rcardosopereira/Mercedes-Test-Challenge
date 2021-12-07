Feature: Validate A Class models price are between £15,000 and £60,000

  As a Mercedes Benz customer
  I want the shopping link
  choose the Hatchbacks model
  Filter the type of gasoline by Diesel
  To know the highest and lowest value

  Scenario: Validate A Class models price and save the highest and lowest results in a text file
    Given that I open Mercedes Benz United Kingdom market
    When I click in our cars
    And I select model Hatchbacks
    And I Mouse over the A Class model available and proceed to Build your car
    And I filter by Fuel type Diesel
    Then I take and save a screenshot of the results
    And I save the value £ of the highest and lowest price results in a text file