package SamSelFrame.tests;

import io.github.bonigarcia.wdm.WebDriverManager;

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

import SamSelFrame.pageobjects.LandingPage;


public class StandAloneTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3"; //product to be added
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //given global wait(implicit)
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("samusa@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("12345678Sa*");
		driver.findElement(By.id("login")).click();
		LandingPage lp = new LandingPage(driver);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); //given explicit wait(specific)
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); //given explicit wait for page loading
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //grabbing all products webelements in to a list
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); //used java streams 
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); //clicking on add to card of a product
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); //given explicit wait to invisible loading icon
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); //given explicit wait to visible added to cart highlight
		
		
		WebElement Submit = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")); //clicking on cart & going into cart
		JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", Submit);
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3")); //webelements of products present in a cart
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName)); //checking whether added product is in the cart or not
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click(); //clicking on checkout
		
        Actions a = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
		
		
		
		
		
		
		
		
	}

}
