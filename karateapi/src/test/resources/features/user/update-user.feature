@api @regression @user @update
Feature: Update User

  Background:
    * url baseUrl
    * header Accept = 'application/json'
    * header Content-Type = 'application/json'
    * def userSchema = read('data/user-schema.json')

  @smoke
  Scenario: Update user name and email using dynamic response data
    * path 'user'
    * def newUser = read('data/new-user.json')
    * request newUser
    * method post

    * path 'user', newUser.username
    * method get
    * def currentUser = response

    * set currentUser.firstName = 'Updated'
    * set currentUser.email = 'pepitoActualizo@mail.com'

    * path 'user', currentUser.username
    * request currentUser
    When method put
    Then status 200

    * path 'user', currentUser.username
    When method get
    Then status 200
    And match response.firstName == 'Updated'
    And match response.email == 'updated.automation@mail.com'
    And match response.lastName == currentUser.lastName
    And match response.phone == currentUser.phone
    And match response contains userSchema
    * print 'User updated and verified dynamically'

    * path 'user', currentUser.username
    * method delete
