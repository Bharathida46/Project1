package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataproviders;

public class TC003_LoginDDT extends BaseClass
   {
	@Test(dataProvider="LoginData", dataProviderClass= Dataproviders.class,groups= {"Sanity","Master","DataDriven"})
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		
		logger.info("*******Starting of loginpage*********");
		lp.setEmailAddress(email);  
		lp.setPassword(pwd);
		lp.clickLoginbtn();
		
		//MyAccountPage
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean targetpage=myacc.isMyAccountPageExists();
		
	//Conditions we have to verify from excel
		//Data is valid--Login success--test pass--logout
		//			   --login fail--test fail
		
		//Data is invalid--Login pass--test fail--logout
		//				 --login fail--test pass
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
				myacc.clickOnLogOut();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetpage==true)
				{
					myacc.clickOnLogOut();
					Assert.assertTrue(false);
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
				
				
		}
	
	
	
	
	
}
