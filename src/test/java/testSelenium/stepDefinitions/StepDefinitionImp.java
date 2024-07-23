package testSelenium.stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;
import testSelenium.pageObjects.CartPage;
import testSelenium.pageObjects.CheckoutPage;
import testSelenium.pageObjects.ConfirmationPage;
import testSelenium.pageObjects.LoginPage;
import testSelenium.pageObjects.ProductCatalogue;
import testSelenium.testComponents.BaseTest;

public class StepDefinitionImp<landingPage> extends BaseTest {
	public LoginPage loginPage; 
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce page") 
	public void I_landed_on_Ecommerce_page() throws IOException {
		loginPage = launchApplication();
	}
	
	@Given("^I logged in using username (.+) and password (.+)$") 
	public void logged_in_username_and_password(String username, String password) {
		productCatalogue = loginPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) from cart$") 
	public void add_product_from_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
	@When("^Checkout (.+) and submit the order$") 
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.gotToCartPage();
		Assert.assertTrue(cartPage.VerifyProductDisplay(productName));
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on Confirmation Page") 
	public void message_displayed_on_Confirmation_Page(String confirmationMessage) {
		Assert.assertEquals(confirmationPage.OrderConfirmationMessage(), confirmationMessage);
		driver.close();
	}
	
	@Then("{string} error message is displayed on Login Page") 
	public void error_message_displayed_on_Login_Page(String errorMessage) {
		Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
		driver.close();
	}
}
