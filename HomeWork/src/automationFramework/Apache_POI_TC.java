package automationFramework;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import PageObjects.LogInPage;

import appModule.SignIn_Action;
import utility.Constant;
import utility.ExcelUtils;


public class Apache_POI_TC {
	private static WebDriver driver = null;
	public static void main(String[] args) throws Exception {
		// This is to open the Excel file. Excel path, file name and the sheet name are parameters to this method
	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData, "Sheet1");
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--remote-allow-origins=*");
	System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver-win64\\chromedriver.exe");
     driver = new ChromeDriver(options);
 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 	driver.get(Constant.URL);
 	
 	
 	WebElement ele = driver.findElement(By.cssSelector("a.toggle-menu"));
 	Actions action = new Actions(driver);
 	action.moveToElement(ele).perform();
 	driver.findElement(By.xpath("/html/body/div[2]/header/nav/div[1]/div/div/div/div[3]/ul/li/ul/li[1]/a")).click();
 	
 	
 	
 	int i=1;
 	while(true) {
 		String sEmail = ExcelUtils.getCellData(i, 1);
		String sPassword = ExcelUtils.getCellData(i,2);
		LogInPage.txtbx_UserName(driver).sendKeys(sEmail);
		LogInPage.txtbx_Password(driver).sendKeys(sPassword);
		LogInPage.btn_LogIN(driver).click();
		
		try {
			String text = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/h1")).getText();
		
		if(text.equals("My Account")){
			ExcelUtils.setCellData("Pass", i, 3);
			
		}
		else {
			ExcelUtils.setCellData("Fail", i, 3);
			LogInPage.txtbx_UserName(driver).clear();
			LogInPage.txtbx_Password(driver).clear();
		}
		}
		
		catch(Exception e) {
			ExcelUtils.setCellData("Fail", i, 3);
			LogInPage.txtbx_UserName(driver).clear();
			LogInPage.txtbx_Password(driver).clear();
		}
		
		i++;
		if(ExcelUtils.getCellData(i, 1)=="") {
			break;
		}
		else {
		 	 ele = driver.findElement(By.cssSelector("a.toggle-menu"));
		 	 action = new Actions(driver);
		 	action.moveToElement(ele).perform();
		 	driver.findElement(By.xpath("/html/body/div[2]/header/nav/div[1]/div/div/div/div[3]/ul/li/ul/li[1]/a")).click();
			
		}
 		
	
 		
 		
 	}
 	System.out.println("Finished Cheaking ");
	driver.quit();
 	
 	
 	
	}}