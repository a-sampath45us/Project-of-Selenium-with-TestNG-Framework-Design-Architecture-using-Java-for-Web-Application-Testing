package SamSelFrame.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() //method is given static bcoz so we can access this method without even declaring object of this class and we can access this method by className.thisMethod
	{
		//Extent Reports has 2 classes they are 1.ExtentReports 2.ExtentSparkReporter
	    String path = System.getProperty("user.dir")+"//reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); //this generates reports
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
				
		ExtentReports extent = new ExtentReports(); //this is a main class this is the one responsible to drive all your reporting execution
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Sam");
		return extent;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
