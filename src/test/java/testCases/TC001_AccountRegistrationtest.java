package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationtest extends BaseClass
{
 
	@Test(groups= {"Sanity","Master"})
	public void verify_Account_Registration()
	{
		logger.info("**********Starting the Registration Page******");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage accreg=new AccountRegistrationPage(driver);
		accreg.setFirstName(randomString().toUpperCase());
		accreg.setLastname(randomString().toUpperCase());
		accreg.setEmail(randomString()+"@gmail.com");
		accreg.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();
		
		accreg.setPassword(password);
		accreg.setConfirmPassword(password);
		accreg.setPrivacyPolicy();
		accreg.clickContinuebutton();
		
		String confirmmsg= accreg.getConfirmationMsg();
		
		logger.info("********End of the registration testcase*****");
		
		Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");			
		
	}
	
	
	
	
	
}
