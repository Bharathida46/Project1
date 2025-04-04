package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtemail;

	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txttelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnConfirm;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastname(String lname)
	{
		txtLastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txtemail.sendKeys(email);
	}
	
	public void setTelephone(String tel)
	{
		txttelephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		chkPrivacyPolicy.click();
	}
	
	public void clickContinuebutton()
	{
		//sol1
		btnConfirm.click();
		
		/*sol2
		//btnConfirm.submit();
		
		//sol3
		Actions act=new Actions(driver);
		act.moveToElement(btnConfirm).click().perform();
		
		//sol4
		JavascriptExecutor js=(javaScriptExecutor)driver;
		js.executeScript("arguments[0].click()", btnConfirm);
		
		//sol5
		btnConfirm.sendKeys(Keys.RETURN);
		
		//sol6
		WebDriverWait*/
	}
	
	public String getConfirmationMsg()
	{
		try {
			return (msgConfirmation.getText());
		}catch(Exception e)
		{
			return(e.getMessage());
		}
	}
	

}
