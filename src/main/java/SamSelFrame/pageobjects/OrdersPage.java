package SamSelFrame.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SamSelFrame.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {

	//we should not hard code in Page Objects 
	//page objects is for only elements and actions
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) //Constructor
	{
		//constructor is used for initialization
		super(driver); //sending driver from child class(Landing Page) to Parent Class(Abstract Component)
		this.driver = driver; //this refers to current class driver at line 2 & 3 
		PageFactory.initElements(driver, this); //beside code will trigger initializing all the elements
	}
	
	
	
	
	//PageFactory(design pattern) //the above code can also be written as below
	
	@FindBy(css="tr td:nth-child(3)")
	private List<WebElement> productNames;
	
	
		
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
