package testSelenium.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import testSelenium.pageObjects.CartPage;
import testSelenium.pageObjects.CheckoutPage;
import testSelenium.pageObjects.ConfirmationPage;
import testSelenium.pageObjects.LoginPage;
import testSelenium.pageObjects.OrdersPage;
import testSelenium.pageObjects.ProductCatalogue;
import testSelenium.testComponents.BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;


public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider="getData", groups= {"Purchase"})
	public void SubmitOrder(HashMap<String, String> input) throws IOException {
		//new comments
		
		ProductCatalogue productCatalogue = loginPage.loginApplication(input.get("email"), input.get("password"));
		
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.gotToCartPage();
		
		Assert.assertTrue(cartPage.VerifyProductDisplay(input.get("productName")));
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		Assert.assertEquals(confirmationPage.OrderConfirmationMessage(), "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dependsOnMethods= {"SubmitOrder"})
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = loginPage.loginApplication("test2024@selenium.com", "Selenium2024");
		OrdersPage ordersPage = productCatalogue.goToOrdersHistoryPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\testSelenium\\data\\purchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
}
