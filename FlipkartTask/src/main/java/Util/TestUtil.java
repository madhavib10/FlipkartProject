package Util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;
import com.sun.media.sound.InvalidFormatException;

public class TestUtil extends TestBase{

	static int Page_Load_Timeout=50;
	
	static int Implicit_wait=30;
	public static String TESTDATA_SHEET_PATH="C:\\Users\\dell\\eclipse-workspace\\FlipkartTask\\src\\"
			+ "main\\java\\TestData\\TestData.xlsx";
	
	
	
	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try 
		{
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try 
		{
			book = WorkbookFactory.create(file);
		}
		catch (InvalidFormatException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//Files.copy(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		FileUtils.copyFile(scrFile, new File("C:\\Users\\dell\\eclipse-workspace\\FlipkartTask" + System.currentTimeMillis() + ".png"));
	}
	
	
	public boolean isAlertPresent(){
	    boolean foundAlert = false;
	    WebDriverWait wait = new WebDriverWait(driver, 0 /*timeout in seconds*/);
	    try {
	        wait.until(ExpectedConditions.alertIsPresent());
	        foundAlert = true;
	    } catch (TimeoutException e) {
	        foundAlert = false;
	    }
	    return foundAlert;
	}
	
	
}
