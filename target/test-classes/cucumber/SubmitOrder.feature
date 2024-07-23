@tag
Feature: Purchase the order from E-Commerce
	
	Background:
	Given I landed on Ecommerce page
	
	@submitOrder
	Scenario Outline: Positive test of submitting the order
		Given I logged in using username <username> and password <password>
		When I add product <orderItem> from cart
		And Checkout <orderItem> and submit the order
		Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page
		
		Examples:
			| username         			|  password    		|      orderItem		|
			| test2024@selenium.com     |  Selenium2024    	|      ADIDAS ORIGINAL	|
	