@tag
Feature: Purchase the order from E-Commerce
	
	Background:
	Given I landed on Ecommerce page
	
	@errorValidation
	Scenario Outline: Positive test of submitting the order
		Given I logged in using username <username> and password <password>
		Then "Incorrect email or password." error message is displayed on Login Page
		
		Examples:
			| username         			|  password    		|    
			| test2024@selenium.com     |  Selenium2025    	|      
	