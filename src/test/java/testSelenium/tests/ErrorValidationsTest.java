package testSelenium.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import testSelenium.pageObjects.CartPage;
import testSelenium.pageObjects.CheckoutPage;
import testSelenium.pageObjects.ConfirmationPage;
import testSelenium.pageObjects.LoginPage;
import testSelenium.pageObjects.ProductCatalogue;
import testSelenium.testComponents.BaseTest;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;


public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException {
		loginPage.loginApplication("test2024@selenium.com", "Selenium2023");
		Assert.assertEquals("Incorrect email or password.", loginPage.getErrorMessage());		
	}
	
	@Test(groups= {"ErrorHandling"})
	public void ProductErrorValidation() throws IOException {
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = loginPage.loginApplication("test2024@selenium.com", "Selenium2024");
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.gotToCartPage();
		
		Assert.assertFalse(cartPage.VerifyProductDisplay(productName + "1"));
	}
}
