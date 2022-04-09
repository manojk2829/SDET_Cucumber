package sdet.test.stepDefinition;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import extentReport_Manoj.ExtentReporting;

public class Login_step {
	
	public WebDriver dr;
	public ExtentReports ext=ExtentReporting.getReporting();
	public ExtentTest test;
	
	@Before
	public void reporting(){
		test=ext.startTest("Start My Reporting");
		test.log(LogStatus.INFO, "Reporting Start");
	}
	
	@Given("^Opening the browser (.*)$")
	public void Opening_browser(String BroName){
		if(BroName.equalsIgnoreCase("chrome")){
		System.out.println("Opening the browser chrome");
		ChromeOptions op=new ChromeOptions();
		op.addArguments("-start-maximized");
		op.addArguments("-disable-infobars");		
	    dr=new ChromeDriver(op);
		}
	    else{
	    	dr=new FirefoxDriver();
	    	dr.manage().window().maximize();
	    }
	    dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    test.log(LogStatus.INFO, "Browser Open successfully = > " + BroName);
	}
	
	@And("^enter the (.*) in chrome browser$")
	public void navigate_URL(String url){
		System.out.println("enter the url in chrome browser");
		dr.get(url);
	}
	
	@When("^Login page open$")
	public void login_Page_open(){
		System.out.println("Login page open");
		WebElement element=dr.findElement(By.id("loginbutton"));
		if(element.isDisplayed()==true){
			System.out.println("Login page open successfully....");
		}else{
			System.out.println("***************** Login page not getting opened *************");
			screenshot_Fail();
			WebDriverWait wt=new WebDriverWait(dr, 10);
			boolean button_Available =wt.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginbutton"))).isDisplayed();
		    System.out.println(button_Available);
		}
	}
	
	@And("^enter (.*) and (.*) in textbox$")
	public void enter_username_password(String Username,String Password){
		System.out.println("enter username and password in textbox");
		dr.findElement(By.id("email")).sendKeys(Username);
		dr.findElement(By.id("pass")).sendKeys(Password);
	}
	
	@And("^click on submit button$")
	public void click_button(){
		dr.findElement(By.id("pass")).sendKeys(Keys.ENTER);
	}
	
	@Then("^welcome home page open$")
	public void Validate_HomePage(){
		System.out.println("welcome home page open");
		boolean status = dr.findElement(By.xpath("//div[contains(text(),'Manoj Kushwaha')]")).isDisplayed();
        if(status==true){
        	screenshot();
        	test.log(LogStatus.PASS, "Login Done successfully....");

        }else{
        	test.log(LogStatus.FAIL, "Login not Done successfully....");
        	screenshot_Fail();
        }
	}
	
	public void screenshot(){
		Date d=new Date();
		String FN=d.toString().replace(" ", "_").replace(":", "_")+".jpg";
		File src=((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try{
			FileHandler.copy(src, new File("C://Manoj_Data//report//"+FN));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		test.log(LogStatus.PASS, "Screenshot taken --> " + test.addScreenCapture("C://Manoj_Data//report//"+FN));
	}
	
	public void screenshot_Fail(){
		Date d=new Date();
		String FN_Fail=d.toString().replace(" ", "_").replace(":", "_")+"_Fail_Test.jpg";
		File src=((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try{
			FileHandler.copy(src, new File("C://Manoj_Data//report//"+FN_Fail));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		test.log(LogStatus.FAIL, "Screenshot taken --> " + test.addScreenCapture("C://Manoj_Data//report//"+FN_Fail));
	}
	
	@After
	public void quiteBro(){
		wait(5);
		ext.endTest(test);
		ext.flush();
		dr.quit();
	}
	
	public void wait(int s){
		try{
			Thread.sleep(s*1000);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

}
