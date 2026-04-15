@api @regression @user
Feature: CRUD operations for User service

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'
    * def setup = callonce read('create-user.feature')
    * def userData = setup.newUser

  @smoke @create
  Scenario: Create a new user
    * path 'user'
    * request userData
    When method post
    Then status 200
    And match response.message == '#notnull'
    * print 'User created:', userData.username

  @smoke @search
  Scenario: Search the created user
    * path 'user', userData.username
    When method get
    Then status 200
    And match response.username == userData.username
    And match response.firstName == userData.firstName
    And match response.email == userData.email
    And match response.phone == userData.phone
    * print 'User found:', response

  @smoke @update
  Scenario: Update user name and email
    * path 'user', userData.username
    * def updatedUser = read('data/updated-user.json')
    * request updatedUser
    When method put
    Then status 200
    * print 'User updated successfully'

  @smoke @verify
  Scenario: Verify updated user data
    * path 'user', userData.username
    When method get
    Then status 200
    And match response.firstName == 'Updated'
    And match response.email == 'updated.automation@mail.com'
    And match response.lastName == userData.lastName
    * print 'Updated user verified:', response

  @smoke @delete
  Scenario: Delete the user
    * path 'user', userData.username
    When method delete
    Then status 200
    * print 'User deleted:', userData.username

  @smoke @delete-verify
  Scenario: Verify deleted user no longer exists
    * path 'user', userData.username
    When method get
    Then status 404

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
    And match response.message == '#notnull'

    * path 'user', '<username>'
    When method get
    Then status 200
    And match response.username == '<username>'
    And match response.email == '<email>'

    * path 'user', '<username>'
    When method delete
    Then status 200

    Examples:
      | id   | username    | firstName | lastName  | email                | password | phone      | userStatus |
      | 1001 | carlos_qa   | Carlos    | Lopez     | carlos@test.com      | Abc123   | 3109876543 | 1          |
      | 1002 | maria_qa    | Maria     | Garcia    | maria@test.com       | Xyz789   | 3201234567 | 1          |
