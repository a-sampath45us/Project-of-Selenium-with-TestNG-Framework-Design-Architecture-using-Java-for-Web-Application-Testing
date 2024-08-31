package SamSelFrame.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SamSelFrame.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent = ExtentReporterNG.getReportObject(); //for accesing static method(className.methodName)
	ExtentTest test; //The "ITestResult" when it executes a Test Method it also creates a object which is unique and specific to that Test Method that is ExtentTest object("test" is variable) using that variable we can use it in further Listeners methods 
	
	//when we run test methods in parallel these all test methods multiple times access the same variable "test" at a time which results in mismatch in results of Test Methods in Reports
	//So this is called concurrency issue. now "test" variable not synchronized and not supporting concurrency and not thread safe.
	//Using "ThreadLocal" we will make "test" variable synchronized, supporting concurrency and Thread Safe
	//Thread Safe means no matter if we run concurrently each object creation have its own thread it won't interrupt with other overriding variable
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) //the "result" variable here will have all info of the Test Method Which is going to be executed
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //it will assign unique thread id(in the behind scenes it sets them as a Map(ErrorValidationTest->test))
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable()); //failing test in html report using listeners
		//test.fail --> extentTest.get().fail for making it thread safe
		try 
		{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance()); //here we are giving life to driver in getScreenshot method using result variable
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		String filePath = null;
		
		try 
		{
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} 
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		//Step 1.Take Screenshot Step 2.Attach to report
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) 
	{
        extent.flush(); 
	}

}
