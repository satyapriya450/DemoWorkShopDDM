package testdata;



import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import baseclass.baseclass;
import utilities.ExtentManager;
import utilities.MonitoringMail;
import utilities.TestConfig;


	public class ListenerClass extends ExtentManager implements ITestListener {
		
		public 	String messageBody;

		public void onTestStart(ITestResult result) {
			test = extent.createTest(result.getName());
		}

		public void onTestSuccess(ITestResult result) {
			if (result.getStatus() == ITestResult.SUCCESS) {
				   test.log(Status.PASS, "Pass Test case is: " + result.getName());
			}
		}

		public void onTestFailure(ITestResult result) {
			if (result.getStatus() == ITestResult.FAILURE) {
				   test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				   test.log(Status.FAIL,
				     MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

				   String pathString = baseclass.screenShot(baseclass.driver, result.getName());
				   try {
				    test.addScreenCaptureFromPath(pathString);
				   } catch (IOException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				   }
				  }
		}

		public void onTestSkipped(ITestResult result) {
			if (result.getStatus() == ITestResult.SKIP) {
				   test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
				  }
		}

		

        
		
		

           

}
