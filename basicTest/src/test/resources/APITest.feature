Feature: Test API

Scenario: Test API
	Given I authenticate with web service
	When I retrieve the employee data
	Then the status code should be 200

