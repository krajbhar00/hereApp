package stepDef;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.paulhammant.ngwebdriver.NgWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class VerifyLinkOfDocumentationTab 
{
	
	WebDriver driver;
	NgWebDriver ngDriver;
	JavascriptExecutor js;

	int relToDocumentationTabLink = 0, notRelToDocumentationTabLink = 0;

	String pageUrl = "https://developer.here.com/documentation";
	String url = "";
	List<String> documentationTabLinks=new ArrayList<String>();
	
	
	@Given("^I navigate to the application URL$")
	public void i_navigate_to_the_application_URL()  
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(pageUrl);
	}

	@When("^I validate the internal links of Documentation tab$")
	public void i_validate_the_internal_links_of_Documentation_tab()  
	{	 
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) 
		{
			url = it.next().getAttribute("href");

			if (url == null || url.isEmpty()) 
			{
				System.out.println("URL is either not configured for anchor tag or it is empty : "+ url);
				notRelToDocumentationTabLink++;
				continue;
			}
			if (!url.startsWith(pageUrl)) 
			{
				System.out.println("URL belongs to another domain : "+ url );
				notRelToDocumentationTabLink++;
				continue;
			}
			else
			{
				documentationTabLinks.add(url);
			}
		}	

	}
	
	@Then("^I check the page load and anglur is initialized for the links$")
	public void i_check_the_page_load_and_anglur_is_initialized_for_the_links() 
	{
		for(int i=0;i<documentationTabLinks.size();i++)
		{
			if(documentationTabLinks.get(i).startsWith(pageUrl))
			{
				ngDriver = new NgWebDriver((JavascriptExecutor) driver);
				ngDriver.waitForAngularRequestsToFinish();
				
				js = (JavascriptExecutor) driver;
				js.executeScript("return document.readyState").toString().equals("complete");

				System.out.println("URL belongs to the documentaion tab : "+ url);
				relToDocumentationTabLink++;
			}
		}		
		System.out.println("Total links relates to documentation tab are : " +relToDocumentationTabLink);

	}

	@Then("^I quit the browser$")
	public void i_quit_the_browser()
	{
		driver.close();
	}

}
