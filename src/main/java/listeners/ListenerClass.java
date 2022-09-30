package listeners;



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


	public class ListenerClass extends ExtentManager implements ITestListener,ISuiteListener {
		
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

		public void onFinish(ISuite arg0) {
			
			MonitoringMail mail = new MonitoringMail();
			 
			try {
				messageBody = "http://" + InetAddress.getLocalHost().getHostAddress()
						+ ":8080/job/DemoWorkShopDDM/Extent_20Report/";
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			try {
				mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		

        
		
		

           

}
