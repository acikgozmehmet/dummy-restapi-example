Feature: Employee REST API requests

  @wip
  Scenario: Delete employee data with the given ID
    Given Content type and Accept type is JSON
    When  I send a DELETE request for a single employee with the ID 595
    Then  Status code is 200
    And   Response JSON should contain the expression "successfully! deleted Records"


  @wip
  Scenario: Delete all the employee data
    Given Content type and Accept type is JSON
    When  I send a GET request with <employees> pathParam
    Then  Status code is 200
    And   Delete all the employee data in the Response JSON
    And   Response JSON should contain no data

  @wip
  Scenario: Post a single employee data
    Given Content type and Accept type is JSON
#    When  I send a POST request with the name "Ali" and the salary "10000" and the age "25"
    When  I send a POST request with the following information
      | name   | Ali    |
      | salary | 100000 |
      | age    | 25     |
    Then  Status code is 200
#    And   Response JSON should contain the employee data


  @wip
  Scenario: Post a single employee data with POJO
    Given Content type and Accept type is JSON
#    When  I send a POST request with the name "Ali" and the salary "10000" and the age "25"
    When  I send a POST request with the following information with POJO
      | name   | Ali    |
      | salary | 100000 |
      | age    | 25     |
    Then  Status code is 200


  @wip
  Scenario: Post multiple employee data by using excel file
    Given Excel filename and sheetname are given as "employee_data.xlsx" and "data"
    And   Content type and Accept type is JSON
    When  I send a POST request for a each record in the file
#    Then  Status code is 200
#    And   Response JSON should contain the each record


   @wip
  Scenario: Post multiple employee data with POJO by using excel file
    Given Excel filename and sheetname are given as "employee_data.xlsx" and "data"
    And   Content type and Accept type is JSON
    When  I send a POST request for a each record in the file with POJO


  Scenario: Get all employee data
    Given Content type and Accept type is JSON
    When  I send a GET request with <employees> pathParam
    Then  Status code is 200
    And   Response JSON should contain all employee data


  Scenario: Get a single employee data
    Given Content type and Accept type is JSON
    When  I send a GET request for a single employee data with the ID "16436"
    Then  Status code is 200
    And   Response JSON should contain the employee data with the ID "16436"


  Scenario Outline: Get multiple employee data
    Given Content type and Accept type is JSON
    When  I send a GET request for a multiple employee data with the following "<ID>" list
    Then  Status code is 200
    And   Response JSON should contain the employee data with the given "<ID>" list

    Examples:
      | ID    |
      | 16436 |
      | 16437 |
      | 16439 |

#  @wip
#  Scenario: Get multiple employee data bu using excel file
#    Given File path and name is "<excel_file_name>"
#    And   Content type and Accept type is JSON
#    When  I send a GET request for a each employee data with the ID
#    Then  Status code is 200
#    And   Response JSON should contain the employee data with the ID





  @del
  Scenario: Delete the employee data more than 300
    Given Content type and Accept type is JSON
    When  I send a GET request with <employees> pathParam
    Then  Status code is 200
    And   Delete all the employee data which is more than 20 from Response JSON


  Scenario: Check the number of  employee data
    Given Content type and Accept type is JSON
    When  I send a GET request with <employees> pathParam
    Then  Status code is 200
    And   Response JSON should contain 20 employee data


#   scenraoi outline ile tablo vererek post yapmak. queryParam ile tanimlayabilirsin.

#   get for only record
#   post for only record
#   delete for only record
#   update for only record