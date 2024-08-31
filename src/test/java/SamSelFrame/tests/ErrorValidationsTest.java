package SamSelFrame.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SamSelFrame.TestComponents.BaseTest;
import SamSelFrame.pageobjects.CartPage;
import SamSelFrame.pageobjects.CheckoutPage;
import SamSelFrame.pageobjects.ConfirmationPage;
import SamSelFrame.pageobjects.LandingPage;
import SamSelFrame.pageobjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest{
	
	//We have written page object files in main java that is not related to test this page object just gives webelements(locators) and action methods 
	//needs to be performed but there are no tests in main java thats why those packages went in source main 

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=SamSelFrame.TestComponents.Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException
	{
        
		
		//Landing Page
		//Product Catalogue
		lp.loginApplication("samusa1@gmail.com", "123456789Sa*");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
        
		String productName = "ZARA COAT 3"; //product to be added
		//Landing Page
		//Product Catalogue
		ProductCatalogue pc = lp.loginApplication("samusa2@gmail.com", "12345678Sa*");
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
		//Going to Cart
		CartPage cp = pc.goToCartPage(); //with any child class we can access methods present in parent class(inheritance)
	    //Cart Page
		Boolean match = cp.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match); //Validations cannot go into page object files
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
