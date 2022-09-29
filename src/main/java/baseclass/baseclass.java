package baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.KeysDemo;
import utilities.Utilities;





public class baseclass {
	
	    public static WebDriver driver;
		public static Properties pro;
		public static FileInputStream fis;
		public static String browser;
		public static WebDriverWait wait;
		public static Logger log = Logger.getLogger("devpinoyLogger");
		public ExcelReader excel=new ExcelReader(KeysDemo.path);
		//WebDriverWait wait=new WebDriverWait(driver, 20);
	
		
		
	
	@BeforeSuite
	 public void BeforeSuite() {
	  ExtentManager.setExtent();
	 }
	 
	 @AfterSuite
	 public void AfterSuite() {
	  ExtentManager.endReport();
	 }
	 
	 @BeforeMethod
	 public void setup() {
					
		 if (driver == null) {
					try {
						 fis= new FileInputStream(KeysDemo.config);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					 pro=new Properties();
					try {
						pro.load(fis);
						log.info("Config file loaded !!!");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					String ss=pro.getProperty("browser");
					System.out.println(ss);
					
					
					//jenkins
					if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()){
						
						browser = System.getenv("browser");
					}else{
						
						browser = pro.getProperty("browser");
						
					}
					
					pro.setProperty("browser", browser);
					
			
				if(pro.getProperty("browser").equals("chrome")) {
					
					System.setProperty(KeysDemo.webdriver,KeysDemo.chrome);
					
					Map<String, Object> prefs = new HashMap<String, Object>();
					prefs.put("profile.default_content_setting_values.notifications", 2);
					prefs.put("credentials_enable_service", false);
					prefs.put("profile.password_manager_enabled", false);
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", prefs);
					options.addArguments("--disable-extensions");
					options.addArguments("--disable-infobars");

					driver = new ChromeDriver(options);
					log.info("launching chrome broswer");
				
				}else if(pro.getProperty("browser").equals("firefox")) {
					
					System.setProperty(KeysDemo.webdriver, KeysDemo.firefox);
					driver = new FirefoxDriver();
					log.info("Open firefox driver !!!");
					
				}else if (pro.getProperty("browser").equals("ie")) {

					System.setProperty(KeysDemo.webdriver, KeysDemo.ie);
						driver = new InternetExplorerDriver();

					}
					
			}
			    
				driver.manage().window().maximize();
				driver.get(pro.getProperty("url"));
				log.info("Hit the URL !!!");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//wait = new WebDriverWait(driver, 5);
		 
	 
	 
	 }
	 
	 public void click(String locator) {

			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(pro.getProperty(locator))).click();
			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(pro.getProperty(locator))).click();
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(pro.getProperty(locator))).click();
			}
			
		}

		public void type(String locator, String value) {

			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(pro.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(pro.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(pro.getProperty(locator))).sendKeys(value);
			}
		}
		
		static WebElement returnwebElement;
		public WebElement returnWebElement(String locator) {
			
			if (locator.endsWith("_CSS")) {
				returnwebElement = driver.findElement(By.cssSelector(pro.getProperty(locator)));
			} else if (locator.endsWith("_XPATH")) {
				returnwebElement = driver.findElement(By.xpath(pro.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				returnwebElement = driver.findElement(By.id(pro.getProperty(locator)));
			}
			//retundemo.getText();
			return returnwebElement;
			
		}
		
		static List<WebElement> returnlistwebElements;
		public List<WebElement> returnListWebElement(String locator) {
			
			if (locator.endsWith("_CSS")) {
				returnlistwebElements = driver.findElements(By.cssSelector(pro.getProperty(locator)));
			} else if (locator.endsWith("_XPATH")) {
				returnlistwebElements = driver.findElements(By.xpath(pro.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				returnlistwebElements = driver.findElements(By.id(pro.getProperty(locator)));
			}
			
			return returnlistwebElements;
			
		}
		
		static WebElement dropdown;

		public void select(String locator, String value) {

			if (locator.endsWith("_CSS")) {
				dropdown = driver.findElement(By.cssSelector(pro.getProperty(locator)));
			} else if (locator.endsWith("_XPATH")) {
				dropdown = driver.findElement(By.xpath(pro.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				dropdown = driver.findElement(By.id(pro.getProperty(locator)));
			}
			
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);

		}

		public boolean isElementPresent(By by) {

			try {

				driver.findElement(by);
				return true;

			} catch (NoSuchElementException e) {

				return false;

			}

		}

		public static void verifyEquals(String expected, String actual) throws IOException {

			try {

				Assert.assertEquals(actual, expected);

			} catch (Throwable t) {

				Utilities.captureScreenshot();
				// ReportNG
				Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
				Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
						+ " height=200 width=200></img></a>");
				Reporter.log("<br>");
				Reporter.log("<br>");
				// Extent Reports
				

			}

		}
	 
	 
	 public static String screenShot(WebDriver driver,String filename) {
		  String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		  File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		  String destination = System.getProperty("user.dir")+"\\ScreenShot\\"+filename+"_"+dateName+".png";
		  File finalDestination= new File(destination);
		  try {
		   FileUtils.copyFile(source, finalDestination);
		  } catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.getMessage();
		  }
		  return destination;
		 }
		 
		 public static String getCurrentTime() {  
		     String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());  
		     return currentDate;  
		 }  
		 
		 

	
		@AfterSuite
		 public void tearDown() throws IOException {
			 if(driver!= null) {
					driver.quit();
					//driver.close();
					}
		 } 
}
