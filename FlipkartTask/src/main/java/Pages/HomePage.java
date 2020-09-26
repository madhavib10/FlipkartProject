package Pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.xmlbeans.impl.xb.xsdschema.PatternDocument.Pattern;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;*/
import org.sikuli.script.*;
import Util.TestBase;
import Util.TestUtil;

public class HomePage extends TestBase

{
	TestUtil TestUtil;

	@FindBy(xpath = "//input[@name ='q' and @type='text']")
	WebElement SeartTextBox;

	@FindBy(xpath="//button[@class='vh79eN']")
	WebElement SeartButton;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public void SearchItem(String Brand, String Equipment) throws InterruptedException, IOException, FindFailed {
		try {
					driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			
			driver.findElement(By.className("fk-modal-visible")).sendKeys(Keys.ESCAPE);
			System.out.println("Alert is NOT Displayed");
		}
		SeartTextBox.clear();
		SeartTextBox.sendKeys(Brand+" "+Equipment);
		Actions action = new Actions(driver);
		SeartButton.click();
		Thread.sleep(1000);
		//TestUtil.takeScreenshotAtEndOfTest();

	}
}
