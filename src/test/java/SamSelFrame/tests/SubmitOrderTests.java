package SamSelFrame.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SamSelFrame.TestComponents.BaseTest;
import SamSelFrame.pageobjects.CartPage;
import SamSelFrame.pageobjects.CheckoutPage;
import SamSelFrame.pageobjects.ConfirmationPage;
import SamSelFrame.pageobjects.LandingPage;
import SamSelFrame.pageobjects.OrdersPage;
import SamSelFrame.pageobjects.ProductCatalogue;


public class SubmitOrderTests extends BaseTest{
	
	//We have written page object files in main java that is not related to test this page object just gives webelements(locators) and action methods 
	//needs to be performed but there are no tests in main java thats why those packages went in source main 

	String productName = "ZARA COAT 3"; //product to be added
	String countryName = "india";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
        
		//Landing Page
		//Product Catalogue
		ProductCatalogue pc = lp.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(input.get("product"));
		//Going to Cart
		CartPage cp = pc.goToCartPage(); //with any child class we can access methods present in parent class(inheritance)
	    //Cart Page
		Boolean match = cp.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match); //Validations cannot go into page object files
		CheckoutPage chp = cp.goToCheckout();
		//Checkout Page
		chp.enterCountry(countryName);
		ConfirmationPage cfp = chp.submitOrder();
		//Confirmation Page
		String confirmMessage = cfp.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	//To verify "Zara Coat 3" is present in Orders Page
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue pc = lp.loginApplication("samusa@gmail.com", "12345678Sa*");
		OrdersPage op = pc.goToOrdersPage(); //here using any object we can access parent class methods(inheritance)
		Assert.assertTrue(op.VerifyOrderDisplay(productName));
		
	}
	
	
	@DataProvider //this is responsible to feed data for any Test method(parameterization)
	public Object[][] getData() throws IOException
	{
		
	    List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SamSelFrame\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
	/*@DataProvider //this is how it is before now it is modified like below using HashMap 
	public Object[][] getData()
	{
	    return new Object[][] {{"samusa@gmail.com", "12345678Sa*", "ZARA COAT 3"},{"samusa1@gmail.com", "12345678Sa*", "ADIDAS ORIGINAL"}};	
	}*/
	
	//this is manually hardcoded Hashmap but in the above @DataProvider it is generic code for HashMap using json file 
	/*HashMap<String,String> map = new HashMap<String,String>();
	map.put("email", "samusa@gmail.com");
	map.put("password", "12345678Sa*");
	map.put("product", "ZARA COAT 3");
	

	HashMap<String,String> map1 = new HashMap<String,String>();
	map.put("email", "samusa1@gmail.com");
	map.put("password", "12345678Sa*");
	map.put("product", "ADIDAS ORIGINAL");*/
	
	//To run tests parallel in XML file
	/*<suite parallel="tests" name="Suite">
	  
	  <listeners>
		  <listener class-name="SamSelFrame.TestComponents.Listeners"/>
	  </listeners>
	  
	  <test thread-count="5" name="Submit Order Test">
	    <classes>
	      <class name="SamSelFrame.tests.SubmitOrderTests"/> 
	    </classes>
	  </test> <!-- Test -->
	  
	  <test thread-count="5" name="Error Validations Test">
	    <classes>
	      <class name="SamSelFrame.tests.ErrorValidationsTest"/> 
	    </classes>
	  </test> <!-- Test -->
	  
	</suite> <!-- Suite -->*/
	
	
	//Maven commands ->mvn test -PRegression -Dbrowser=edge
	//command to open Jenkins ->java -jar jenkins.war -httpPort=9090
	//java -jar jenkins.war --enable-future-java  --httpPort=8080 - used this
	//Invoke top-level Maven targets ->test -PRegression -Dbrowser="$browserName"
	//->test -P"$Profile"  -Dbrowser="$browserName" used choice parameters in Jenkins configure
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
