package SamSelFrame.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SamSelFrame.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	//we should not hard code in Page Objects 
	//page objects is for only elements and actions
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) //Constructor
	{
		//constructor is used for initialization
		super(driver);
		this.driver = driver; //this refers to current class driver at line 2 & 3 
		PageFactory.initElements(driver, this); //beside code will trigger initializing all the elements
	}
	
	
	
	//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	//PageFactory(design pattern) //the above code can also be written as below
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	
	public List<WebElement> getProductList() 
	{
	    waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		Thread.sleep(3000);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
