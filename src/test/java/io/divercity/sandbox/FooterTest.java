package io.divercity.sandbox;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

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

		// Scroll to bottom of page (Sleeps are needed for the page to load properly)
		sleep();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 1500)");
		sleep();

		// Add all web elements into a list
		List<WebElement> links = new ArrayList<WebElement>();

		// Obtain Webelements for links in footer and add it to ArrayList(this is hardcoded)
		// Blog
		WebElement blogLink = driver.findElement(
				By.xpath("//body/div[@id='root']/div[@id='source']/div[1]/footer[1]/div[1]/div[1]/a[1]"));
		links.add(blogLink);

		// TOS
		WebElement tosLink = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/footer[1]/div[1]/div[1]/a[2]"));
		links.add(tosLink);

		// Privacy Policy
		WebElement privacyPolicyLink = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/footer[1]/div[1]/div[2]/a[1]"));
		links.add(privacyPolicyLink);

		// Linked In
		WebElement linkedInLink = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/footer[1]/div[1]/div[3]/a[1]"));
		links.add(linkedInLink);

		// Instagram
		WebElement instagramLink = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/footer[1]/div[1]/div[3]/a[2]"));
		links.add(instagramLink);

		// Twitter
		WebElement twitterLink = driver
				.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/footer[1]/div[1]/div[3]/a[3]"));
		links.add(twitterLink);
		
		// Loop to check all WebElement links
		for(int i = 0; i < links.size(); i++) {
			verifyLinks(links.get(i));
		}
		
		
		sleep();

		// close chrome
		driver.quit();
	}
	
	public void verifyLinks(WebElement element) {
		// Get URL of current link from given web element
		String currentURL = element.getAttribute("href");
		
		//System.out.println("Current Element: " + element.getAttribute("href"));
		
		try {
			URL url = new URL(currentURL);
			
			// create URL connection
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			// Allow 3 seconds to connect
			connection.setConnectTimeout(3000);
			
			// connect to link
			connection.connect();
			
			// If the response code is above 400 it is a bad gateway
			if(connection.getResponseCode() >= 400) {
				System.out.println(currentURL + " is a broken link");
			}
			else {
				System.out.println(currentURL + " " + connection.getResponseMessage());
			}
			
			
		}catch(Exception e) {
			//
		}
	}

	private void sleep() {
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println("Threading issue");
		}
	}

}
