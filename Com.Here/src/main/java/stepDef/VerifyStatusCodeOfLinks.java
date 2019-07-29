package stepDef;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VerifyStatusCodeOfLinks {

	WebDriver driver;

	String url = "";
	HttpURLConnection huc = null;
	
	public static int brokenLinks;
	public static int validLinks;
	List<WebElement> allURLs;

	@Given("^I navigate to the application url$")
	public void i_navigate_to_the_application_url() 
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://developer.here.com/documentation");

	}

	@When("^I find all the links present on the current webpage$")
	public void i_find_all_the_links_present_on_the_current_webpage() 
	{	 
		allURLs = driver.findElements(By.tagName("a"));
		System.out.println("size:" + allURLs.size());

		
	}
	
	@When("^I validate status of all the links present on current page$")
	public void i_validate_status_of_all_the_links_present_on_current_page() 
	{
		validLinks = brokenLinks = 0;
		Iterator<WebElement> it = allURLs.iterator();

		while (it.hasNext()) 
		{
			int respCode = 0;
			url = it.next().getAttribute("href");
			try 
			{
				URL link = new URL(url);
				HttpURLConnection hConn = null;
				hConn = (HttpURLConnection) link.openConnection();
				hConn.setRequestMethod("GET");
				hConn.connect();
				respCode = hConn.getResponseCode();

			} 
			catch (Exception e) 
			{
				e.getMessage();
			}
			if (respCode == 200) 
			{
				System.out.println(url);
				++validLinks;
			} 
		}
		System.out.println("Total valid links found# " + validLinks);

	}

	@Then("^I close the browser$")
	public void i_close_the_browser() 
	{
		driver.close();
	}

}
