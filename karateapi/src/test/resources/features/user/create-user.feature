@api @regression @user @create
Feature: Create User

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'
    * def userSchema = read('data/user-schema.json')

  @smoke
  Scenario: Create a new user and verify with GET
    * path 'user'
    * def newUser = read('data/new-user.json')
    * request newUser
    When method post
    Then status 200
    And match response.message == '#string'
    And match response.code == '#number'

    * path 'user', newUser.username
    When method get
    Then status 200
    And match response == '#object'
    And match response contains deep newUser
    And match response contains userSchema
    * print 'User created and verified:', response.username

  @smoke
  Scenario Outline: Create multiple users with different data
    * path 'user'
    * def body =
    """
    {
      "id": <id>,
      "username": "<username>",
      "firstName": "<firstName>",
      "lastName": "<lastName>",
      "email": "<email>",
      "password": "<password>",
      "phone": "<phone>",
      "userStatus": <userStatus>
    }
    """
    * request body
    When method post
    Then status 200

    * path 'user', '<username>'
    When method get
    Then status 200
    And match response.username == '<username>'
    And match response.email == '<email>'
    And match response contains userSchema

    * path 'user', '<username>'
    * method delete

    Examples:
      | id   | username    | firstName | lastName | email           | password | phone      | userStatus |
      | 1001 | carlos_qa   | Carlos    | Lopez    | carlos@test.com | Abc123   | 3109876543 | 1          |
      | 1002 | maria_qa    | Maria     | Garcia   | maria@test.com  | Xyz789   | 3201234567 | 1          |
