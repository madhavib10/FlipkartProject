package Pages;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import Util.TestBase;
import Util.TestUtil;

public class SearchResultPage extends TestBase

{
	
	public SearchResultPage()
	{
		PageFactory.initElements(driver, this);
		
	}
	

	@FindBy(xpath="//div[@class='_3G9WVX _2N3EuE']//div[@class='_3aQU3C']")
	WebElement MinPriceSrollr;
	
	@FindBy(className=".//div[@class='_1gjf4c D_NGuZ']//div[@class='_3xglSm _1iMC4O']" )
	WebElement FilterTestElement;
	
	@FindBy(className=".//div[@class='_1gjf4c D_NGuZ']//div[@class='_3KyMh7 _2Gnm9R']" )
	WebElement FilterElement;
	
	
	
	public void SetFilter(String MinPrice, String RAM, String Processor) throws InterruptedException, IOException
	{
		 Actions act = new Actions(driver);
		  WebDriverWait wait= new WebDriverWait(driver, 15);
		  act.dragAndDropBy(MinPriceSrollr, -40, 0).build().perform();
		Thread.sleep(500);
		
		List <WebElement> FiltersName= FilterTestElement.findElements(By.className("_2yccxO D0YrLF"));
		for(WebElement Name: FiltersName)
		{
			String FilterText=Name.getText();
			if(FilterText.equals("RAM"))
			{
				List <WebElement> FilterValue= FilterElement.findElements(By.className("_2yccxO D0YrLF"));
				for(WebElement Value: FilterValue)
				{
					String Filter=Value.getText();
					if(Filter.equals(RAM))
					{
						Value.click();
					}
				}
			}
			if(FilterText.equals("Processor"))
			{
				List <WebElement> FilterValue= FilterElement.findElements(By.className("_2yccxO D0YrLF"));
				for(WebElement Value: FilterValue)
				{
					String Filter=Value.getText();
					if(Filter.equals(Processor))
					{
						Value.click();
					}
				}
			}

		}
	}
	
	public void ReadData() throws InterruptedException, IOException
	{
	
		//Fetch All the Products Text
				List<WebElement> list_of_products = driver.findElements(By.xpath("//div[@class='_3wU53n']"));
				List<WebElement> list_of_products_price = driver.findElements(By.xpath("//div[@class='_1vC4OE _2rQ-NK']"));
				
				//Use of HashMaop to store Products and Their prices(after conversion to Integer)
				String product_name;
				String product_price;
				int int_product_price;
				HashMap<Integer, String> map_final_products = new HashMap<Integer,String>();
				for(int i=0;i<list_of_products.size();i++) {
					product_name = list_of_products.get(i).getText();//Iterate and fetch product name
					product_price = list_of_products_price.get(i).getText();//Iterate and fetch product price
					product_price = product_price.replaceAll("[^0-9]", "");
					int_product_price = Integer.parseInt(product_price);//Convert to Integer
					map_final_products.put(int_product_price,product_name);//Add product and price in HashMap
				}
				
				 StringWriter output = new StringWriter();
				 
				    try (CsvListWriter listWriter = new CsvListWriter(output, 
				         CsvPreference.STANDARD_PREFERENCE)){
				        for (Map.Entry<Integer, String> entry : map_final_products.entrySet()){
				            listWriter.write(entry.getKey(), entry.getValue());
				        }
				    }
	}
}
