package appModule;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;

import PageObjects.LogInPage;
import utility.ExcelUtils;
import utility.Constant;


public class SignIn_Action {
	
	public static void Execute(WebDriver driver) throws Exception{
		// This is to get the values from Excel sheet, passing parameters (Row num & amp; Col num) to getCellData method
	
    	
    			String sEmail = ExcelUtils.getCellData(1, 1);
				String sPassword = ExcelUtils.getCellData(1,2);
				LogInPage.txtbx_UserName(driver).sendKeys(sEmail);
				LogInPage.txtbx_Password(driver).sendKeys(sPassword);
		        LogInPage.btn_LogIN(driver).click();
    			
    		
    	

		    	
		        
	}


}
