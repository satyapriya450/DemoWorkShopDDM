package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseclass.baseclass;




public class ExtentManager {
 
 public static ExtentHtmlReporter htmlReporter;
 public static ExtentReports extent;
 public static ExtentTest test;
 
 public static void setExtent() {
  htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"//target/surefire-reports/html/"+"extent.html");
  htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"\\src\\main\\java\\utilities\\extent-config.xml");
 
  //htmlReporter.config().setDocumentTitle("Automation Test Report");
  //htmlReporter.config().setReportName("OrangeHRM Test Automation Report");
  //htmlReporter.config().setTheme(Theme.DARK);
  
  extent = new ExtentReports();
  extent.attachReporter(htmlReporter);
  
//  extent.setSystemInfo("HostName", "MyHost");
//  extent.setSystemInfo("ProjectName", "Way2Message");
//  extent.setSystemInfo("Tester", "Hitendra");
//  extent.setSystemInfo("OS", "Win10");
//  extent.setSystemInfo("Browser", "Chrome");
  
 }
 public static void endReport() {
  extent.flush();
 }
}
