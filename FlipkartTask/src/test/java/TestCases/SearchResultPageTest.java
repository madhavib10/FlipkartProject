package TestCases;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.SearchResultPage;
import Util.TestBase;
import Util.TestUtil;

public class SearchResultPageTest  extends TestBase

{

	TestUtil TestUtil;
	SearchResultPage ResultPage;
	String SheetNmae="Filter";
	
	/*
	 * public SearchResultPageTest() { super(); //it will call super class
	 * constructor to initialize properties }
	 */
	
	@BeforeMethod
	public void setup()
	{
		//initialization();
		ResultPage= new SearchResultPage();
	}

	@DataProvider
	public Object[][] getFilterTestData()
	{
		Object data[][]=TestUtil.getTestData(SheetNmae);
		return data;
		
	}
	
	@Test(dataProvider = "getFilterTestData")
	public void validateCreateNewContact(String MinPrice, String RAM,String Processor) throws InterruptedException, IOException
	{
		ResultPage.SetFilter(MinPrice, RAM, Processor);
		TestUtil.takeScreenshotAtEndOfTest();
		ResultPage.ReadData();
	}
	
	
}
