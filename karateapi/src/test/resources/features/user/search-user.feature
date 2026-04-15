@api @regression @user @search
Feature: Search User

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'
    * def userSchema = read('data/user-schema.json')

  @smoke
  Scenario: Search user and validate response schema
    * path 'user'
    * def newUser = read('data/new-user.json')
    * request newUser
    * method post

    * path 'user', newUser.username
    When method get
    Then status 200
    And match response contains userSchema
    And match response.id == '#number'
    And match response.email == '#regex .+@.+\\..+'
    And match response.userStatus == '#? _ == 0 || _ == 1 || _ == 2'
    * print 'Schema validated for user:', response.username

    * path 'user', newUser.username
    * method delete
