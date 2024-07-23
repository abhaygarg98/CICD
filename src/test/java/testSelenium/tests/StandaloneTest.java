package testSelenium.tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import testSelenium.pageObjects.LoginPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String productName = "ZARA COAT 3";
		driver.get("https://rahulshettyacademy.com/client/");
		LoginPage loginPage = new LoginPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("test2024@selenium.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium2024");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement toastElement = driver.findElement(By.id("toast-container"));
		wait.until(ExpectedConditions.visibilityOf(toastElement));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		
		wait.until(ExpectedConditions.visibilityOf(toastElement));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div.cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));		
		Assert.assertTrue(match);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".totalRow button")));
	
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")));
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".action__submit")));
		driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.hero-primary")).getText(), "THANKYOU FOR THE ORDER.");
		driver.close();
	}

}
