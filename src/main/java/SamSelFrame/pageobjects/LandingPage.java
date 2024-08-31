package SamSelFrame.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SamSelFrame.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	//we should not hard code in Page Objects 
	//page objects is for only elements and actions
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) //Constructor
	{
		//constructor is used for initialization
		super(driver); //sending driver from child class(Landing Page) to Parent Class(Abstract Component)
		this.driver = driver; //this refers to current class driver at line 2 & 3 
		PageFactory.initElements(driver, this); //beside code will trigger initializing all the elements
	}
	
	
	
	//WebElement userEmails = driver.findElement(By.id("userEmail"));
	//PageFactory(design pattern) //the above code can also be written as below
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue pc = new ProductCatalogue(driver);
		return pc;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
