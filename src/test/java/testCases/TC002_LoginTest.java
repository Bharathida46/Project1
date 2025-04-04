package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups= {"Regression","Master"})
	public void verify_Login() 
	{
		logger.info("******logssssssssssss***********");
		try {
			
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmailAddress(p.getProperty("email"));  //from config file
		lp.setPassword(p.getProperty("password"));
		lp.clickLoginbtn();
		
		//MyAccountPage
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetpage=myacc.isMyAccountPageExists();
		Assert.assertEquals(targetpage, true);
		}
		catch(Exception e)
		{
			Assert.fail();
		}	
	}
	
	
	
	
	
	
}
