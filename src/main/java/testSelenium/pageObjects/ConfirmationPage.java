package testSelenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponents{
	
	WebDriver driver;
	
	@FindBy(css="h1.hero-primary")
	WebElement confirmationMesEl;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String OrderConfirmationMessage() {
		return confirmationMesEl.getText();
	}

}
