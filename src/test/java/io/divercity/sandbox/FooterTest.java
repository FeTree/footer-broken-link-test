package io.divercity.sandbox;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class FooterTest {
	
	@Test
	public void brokenLinkFooterTest() {
		System.out.println("Starting footer test");
		
		// Create and instantiate Driver 
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Maximize chrome window
		driver.manage().window().maximize();
		
		// Open page
		String url = "https://sandbox.divercity.io/";
		driver.get(url);
		System.out.println("Page has been opened");
		
		// Scroll to bottom of page
		sleep();
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 1500)");
		
		
		// Add all web elements into a list
		//List<WebElement> links = driver.findElements(By.tagName("a"));
		
		
		// Check if blog works
		
		WebElement blogLink = driver.findElement(By.linkText(
		  "Blog"
		  ));
		  
		  // click blog link 
		  sleep();
		  blogLink.click();
		 
		
		sleep();
		
		
		
		
		// close chrome
		driver.quit();
	}

	private void sleep() {
		try {
			Thread.sleep(5000);
		}
		catch(Exception e) {
			System.out.println("Threading issue");
		}
	}
	

}
