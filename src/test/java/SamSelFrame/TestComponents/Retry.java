package SamSelFrame.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;
	
	//We use this method to rerun the flaky failed selenium tests
	//we cannot put this class in XML file in listeners this is diff 
	//we have to assign this to a specific test method like this in a @Test(retryAnalyzer=SamSelFrame.TestComponents.Retry.class)
	
	@Override
	public boolean retry(ITestResult result) 
	{
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
