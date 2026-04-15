@api @regression @user @delete
Feature: Delete User

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'

  @smoke
  Scenario: Delete user and verify removal
    * path 'user'
    * def newUser = read('data/new-user.json')
    * request newUser
    * method post

    * path 'user', newUser.username
    When method delete
    Then status 200
    And match response.message == newUser.username

    * path 'user', newUser.username
    When method get
    Then status 404
    And match response.message == 'User not found'
    * print 'User deleted and verified'
