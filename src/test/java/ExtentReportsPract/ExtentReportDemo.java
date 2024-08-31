package ExtentReportsPract;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {

	ExtentReports extent;
	
	@BeforeTest
	public void config()
	{
		//Extent Reports has 2 classes they are 1.ExtentReports 2.ExtentSparkReporter
		String path = System.getProperty("user.dir")+"//reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); //this generates reports
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports(); //this is a main class this is the one responsible to drive all your reporting execution
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sam");
		
	}
	
	@Test
	public void initialDemo() 
	{
       
		ExtentTest test = extent.createTest("Initial Demo");
		System.setProperty("Webdriver.chrome.driver", "D:\\Java Selenium\\Chrome Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com");
		System.out.println(driver.getTitle());
		driver.close();
		test.fail("No Result");
		
		
		extent.flush();
		
	}
}
