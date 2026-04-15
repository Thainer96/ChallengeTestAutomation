@api @regression @user @negative
Feature: Negative tests for User service

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'

  Scenario: Search for a non-existent user returns 404
    * path 'user', 'nonexistent_user_xyz_999'
    When method get
    Then status 404
    And match response.message == 'User not found'
    And match response.type == 'error'

  Scenario: Create user with empty body
    * path 'user'
    * request {}
    When method post
    Then status 200
    And match response.message == '#string'

  Scenario: Delete a non-existent user returns 404
    * path 'user', 'ghost_user_that_does_not_exist'
    When method delete
    Then status 404

  Scenario: Update a non-existent url
    * path 'usera', 'nonexistent_update_user'
    * def fakeUser = read('data/new-user.json')
    * set fakeUser.username = 'usuarioSinExistencia'
    * request fakeUser
    When method put
    Then status 404
