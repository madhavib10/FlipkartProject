package TestCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import Pages.HomePage;
import Pages.SearchResultPage;
import Util.TestBase;
import Util.TestUtil;
public class HomePageTest extends TestBase
{
	
	HomePage homePage;
	TestUtil TestUtil;
	
	String SheetName="Search";
	
		public HomePageTest()
		{
			super(); //it will call super class constructor to initialize properties
			
		}
		
		@BeforeMethod
		public void setup()
		{
			initialization();
			homePage= new HomePage();
		}

		@DataProvider
		public Object[][] getSearchTestData()
		{
			Object data[][]=TestUtil.getTestData(SheetName);
			return data;
			
		}
		
		@Test(dataProvider = "getSearchTestData")
		public SearchResultPage SearchData(String Brand, String Equipment) throws InterruptedException, IOException, FindFailed
		{
			homePage.SearchItem(Brand,Equipment);
			return new SearchResultPage();
		}
		
		@AfterMethod
		public void GoToResultPage()
		{
			//return null;
			//take Screenshot
		}

}
