package keywordEngine;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.Base;

public class KeywordEngine {
	public Base base ;
	public WebDriver driver;
	public Properties prop;
	public WebElement element;

	public static XSSFWorkbook book;
	public static XSSFSheet  sheet ;

	String projectpath = System.getProperty("user.dir");
	public  String Scenario_sheet_path = projectpath+"/src/main/resources/Scenarios/keywordDriven.xlsx";

	public void startExecution(String sheetname) {
		
		 try {
			book = new XSSFWorkbook(Scenario_sheet_path);
			sheet = book.getSheet(sheetname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		int k =0;
		for(int i = 0 ; i<sheet.getLastRowNum();i++) {
			try {
				String locatorType=sheet.getRow(i+1).getCell(k+1).toString().trim();
				String locatorValue = sheet.getRow(i+1).getCell(k+2).toString().trim();

				String action= sheet.getRow(i+1).getCell(k+3).getStringCellValue().trim();
				String value = sheet.getRow(i+1).getCell(k+4).getStringCellValue().trim();

				switch(action) {
				case "open browser":
					base= new Base();
					prop=base.init_properties();
					if(value.isEmpty()||value.equalsIgnoreCase("NA")){
						driver=base.init_driver(prop.getProperty("browser"));
					}else {
						driver= base.init_driver(value);
					}
					break;

				case "launch url":
					if(value.isEmpty()||value.equalsIgnoreCase("NA")){
						driver.get(prop.getProperty("url"));
					}else {
						driver.get(value);
					}
					break;

				case "quit":
					driver.quit();
					break;


				default:
					break;
				}

				switch(locatorType) {
				case "id":
					element =driver.findElement(By.id(locatorValue));
					if(action.equalsIgnoreCase("sendkeys")) {
						element.sendKeys(value);
					}else if(action.equalsIgnoreCase("click")) {
						element.click();
					}
					locatorType=null;
					break;
				default:
					break;

				}
			}
			catch(Exception e) {
				
			}
			


		}


	}

}

