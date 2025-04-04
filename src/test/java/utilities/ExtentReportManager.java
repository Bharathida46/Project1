package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		String timestamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		repName= "Test-Report" + timestamp +".html";
		sparkReporter= new ExtentSparkReporter(".\\reports\\" + repName); //specify the location
		
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent =new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));// will fetch the current user/tester or system user name.
		extent.setSystemInfo("Environment", "QA");
		
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups= testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName()); //display testcase class name in reports
		test.assignCategory(result.getMethod().getGroups());	//display group names in report
		test.log(Status.PASS, result.getName()+"got successfully executed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName()); //display testcase class name in reports
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgpath=new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgpath);
			
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}	
	}
	
	public void onTestSkip(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName()); //display testcase class name in reports
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	public void onTestFinish(ITestResult result)
	{
		 
		extent.flush();    //it will consolidate all the methods in single folder and generate reports.
		
		
		//Below code is to open the reports automatically in the browser.
		String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\"+ repName;
		File extentReport= new File(pathOfExtentReport);
		try
		{
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
		
	
	
	
	
	
	
	
	
	
}
