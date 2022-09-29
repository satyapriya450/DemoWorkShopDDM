package testcase;

import java.util.Iterator;
import java.util.List;


import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.IOException;
import baseclass.baseclass;

public class ClearShoppingCart extends baseclass {
	
	//If Already Add Any Items in Addtocart, it will remove and go to the books page
	@Test
	public void ClearAddToCartAndGoToBooks() throws InterruptedException, IOException {
		
		
		WebElement returnwebelement = returnWebElement("num_XPATH");
		
		String s="(0)";
		if(returnwebelement.getText().equalsIgnoreCase(s))
		{
			click("books2_XPATH");
			log.info("Go To Books Page.............");
		}
		else
		{
			click("movetoshippingcartlink_XPATH");
			Thread.sleep(1000);
			
		List<WebElement> clist = returnListWebElement("cardlist_XPATH");
    	Iterator<WebElement> cart =clist.iterator();
    	
    	while(cart.hasNext())
    	{
    		cart.next().click();
    	}
    	click("updateshippindcartbtn_XPATH");
		}
	    log.info("Clear Shipping Cart Items............");
	    Thread.sleep(3000);
	}

}
	
