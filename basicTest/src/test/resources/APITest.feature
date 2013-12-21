Feature: Test API

Scenario: Test API
	Given I authenticate with web service
	When I retrieve the results
	Then the status code should be 200

