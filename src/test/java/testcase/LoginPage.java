package testcase;

import java.util.Hashtable;

import org.testng.annotations.Test;

import baseclass.baseclass;
import utilities.Utilities;

public class LoginPage extends baseclass {
	
	//Enter Valid Credentials
	@Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void EnterValidCredentials(Hashtable<String, String> data) throws InterruptedException {
		
		click("loginlink_XPATH");
		Thread.sleep(1000);
		type("email_XPATH", data.get("username"));
		type("password_XPATH",data.get("password"));
		click("loginbtn_XPATH");
		Thread.sleep(3000);
		
		
		//newwwwwwwwwwwwwwww
	}
	
}
