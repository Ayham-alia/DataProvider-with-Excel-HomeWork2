package automationFramework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TestCases {
	ChromeOptions options;
	WebDriver driver;
	Logger log=Logger.getLogger("Logs");
	
	
 
  @BeforeTest
  public void setUp() {
	  options=new ChromeOptions();
	  options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
      driver = new ChromeDriver(options);
	  
  }
  
  @Test(priority = 1)
  public void navigateToWebsite() {
      driver.get("https://www.petermillar.com/");
      log.info("Navigated to website.");

  }
  
  @Test(priority = 2)
  public void VerifyLogoIsDisplay() {
	  WebElement Ele=driver.findElement(By.cssSelector("a.logo-home"));
	  Assert.assertTrue(Ele.isDisplayed(),"Logo isn't dispaly");
      log.info("Logo is displayed.");

  }
  
  @Test(priority = 3)
  public void ClickOnSignInRegisterLink() {
	  WebElement Ele=driver.findElement(By.cssSelector("a.toggle-menu"));
	  Actions act=new Actions(driver);
	  act.moveToElement(Ele).perform();
	  driver.findElement(By.xpath("/html/body/div[2]/header/nav/div[1]/div/div/div/div[3]/ul/li/ul/li[1]/a")).click();
      log.info("Clicked on Sign In/Register link.");

  }
  
  @Test(priority = 4)
  public void verifySignInPage() {
      String signInText = driver.findElement(By.cssSelector("h1#loginTabHeader")).getText();
      Assert.assertTrue(signInText.equals("Returning Customers"));
    
      log.info("Verified Sign In page.");
  }
  
  @Test(priority = 5)
  public void login() {
      WebElement emailField = driver.findElement(By.name("loginEmail"));
      WebElement passwordField = driver.findElement(By.name("loginPassword"));
      WebElement loginButton = driver.findElement(By.className("signin"));

      emailField.sendKeys("guest@test.com");
      passwordField.sendKeys("Guest123");
      loginButton.click();
      log.info("Logged in with valid data.");
      try {
    	  Thread.sleep(8000);
      }
      catch(Exception e) {
    	  
      }
  }
  
  @Test(priority = 6)
  public void verifyLoggedIn() {
      WebElement myAccount = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div/div[2]/h1"));
      Assert.assertTrue(myAccount.isDisplayed());
      log.info("Verified user is logged in.");
  }
  
  @Test(priority = 7)
  public void verifyUserName() {
      WebElement welcomeMessage = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div/div[2]/div"));
      String welcomeText = welcomeMessage.getText();
      Assert.assertTrue(welcomeText.equals("Welcome, guest"));
    
    
      log.info("Verified user name in welcome message.");
  }
  
  

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
