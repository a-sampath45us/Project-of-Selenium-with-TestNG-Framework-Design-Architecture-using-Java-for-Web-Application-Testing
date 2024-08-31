package SamSelFrame.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SamSelFrame.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	//we should not hard code in Page Objects 
	//page objects is for only elements and actions
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) //Constructor
	{
		//constructor is used for initialization
		super(driver); //sending driver from child class(Landing Page) to Parent Class(Abstract Component)
		this.driver = driver; //this refers to current class driver at line 2 & 3 
		PageFactory.initElements(driver, this); //beside code will trigger initializing all the elements
	}
	
	
	
	
	//PageFactory(design pattern) //the above code can also be written as below
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement clickOnIndia;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement placeOrder;
	
	@FindBy(css=".hero-primary")
	WebElement orderText;
	
	By results1 = By.xpath("//input[@placeholder='Select Country']");
	
	By results2 = By.cssSelector(".ta-results");
	
	public void enterCountry(String countryName)
	{
		Actions a = new Actions(driver);
        waitForElementToAppear(results1);
		a.sendKeys(selectCountry,countryName).build().perform();
		waitForElementToAppear(results2);
		clickOnIndia.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
