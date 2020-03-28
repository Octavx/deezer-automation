@smokeTest
Feature: Smoke Test for the Deezer page

  @test1 @priority1 @web
  Scenario: Happy flow - Log in and play default song
    Given I am on Deezer main page
    When I login with email address credentials saved in properties file
    Then Deezer user homepage is displayed
    When I go to Music section, expecting page to open
    And I start to play flow selection
    Then Music starts playing


  @test2 @priority1 @web
  Scenario Outline: Log in and search
    Given I am on Deezer main page
    When I login with email address credentials saved in properties file
    Then Deezer user homepage is displayed
    When I search for "<search_key>"
    And I select the "<type>" section
    When I start to play result number 1
    Then Music starts playing

    Examples:
    | search_key        | type     |
    | Guns and roses    | Artists  |
    | Purple rain       | Albums   |

  @this
  @test3 @priority2 @web
  Scenario: Log in and play a recommended playlist
    Given I am on Deezer main page
    When I login with email address credentials saved in properties file
    Then Deezer user homepage is displayed
    When I go to Explore section, expecting page to open
    And I choose RAP channel, expecting page to open
    Then Several playlists are displayed
    When I start to play the playlist number 1
    Then Music starts playing
#
#
#  @test4 @priority1 @api
#  Scenario: Deezer API test - search for song
#    Given I send an API request for a song
#    Then I expect the API request to have the correct format

#
#  @test5 @priority2 @api
#  Scenario: Deezer API test - retrieve song data






