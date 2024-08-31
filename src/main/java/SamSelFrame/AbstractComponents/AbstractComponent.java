package SamSelFrame.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SamSelFrame.pageobjects.CartPage;
import SamSelFrame.pageobjects.OrdersPage;

public class AbstractComponent {

	//this class is for reusing the common methods/code in framework 
	//Here we can write so many utilities of Selenium like switching to frame, switching to window, javascript executors, alert handlings, visibility 
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		

	public void waitForElementToAppear(By findBy)
	{
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); //given explicit wait(specific)
	  wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	

	public void waitForWebElementToAppear(WebElement findBy)
	{
      WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); //given explicit wait(specific)
	  wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	/*public void waitForElementToDisappear(WebElement Ele)
	{
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(Ele));
	}*/
	
	public CartPage goToCartPage()
	{
		WebElement Submit = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")); //clicking on cart & going into cart
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", Submit);
        CartPage cp = new CartPage(driver);
        return cp;
	}
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	public OrdersPage goToOrdersPage()
	{
		orderHeader.click();
		OrdersPage op = new OrdersPage(driver);
		return op;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
