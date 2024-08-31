package SamSelFrame.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SamSelFrame.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	//we should not hard code in Page Objects 
	//page objects is for only elements and actions
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) //Constructor
	{
		//constructor is used for initialization
		super(driver); //sending driver from child class(Landing Page) to Parent Class(Abstract Component)
		this.driver = driver; //this refers to current class driver at line 2 & 3 
		PageFactory.initElements(driver, this); //beside code will trigger initializing all the elements
	}
	
	
	
	
	//PageFactory(design pattern) //the above code can also be written as below
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
		
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
