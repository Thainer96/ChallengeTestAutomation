@ignore
Feature: Helper - Create a user for reuse

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'

  Scenario: Create a single user
    * path 'user'
    * def newUser = read('data/new-user.json')
    * request newUser
    When method post
    Then status 200
