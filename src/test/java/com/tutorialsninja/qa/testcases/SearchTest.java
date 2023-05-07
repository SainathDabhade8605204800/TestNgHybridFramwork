package com.tutorialsninja.qa.testcases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninjaqa.pages.HomePage;
import com.tutorialsninjaqa.pages.LogInPage;
import com.tutorialsninjaqa.pages.ProductComparisonPage;
import com.tutorialsninjaqa.pages.SearchPage;

public class SearchTest extends Base{
	
	public SearchTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebDriver driver;
	@BeforeMethod
	public void setup() {	
		driver= initiaseBrowserAndOpenApplicqationURL(prop.getProperty("browserName"));
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.selectLogInOption();
		/*
		 * driver.findElement(By.xpath("//span[contains(text(), 'My Account')]")).click(
		 * ); driver.findElement(By.linkText("Login")).click();
		 */
		LogInPage loginpage = new LogInPage(driver);
		loginpage.enterEmailAdress(prop.getProperty("validUsername"));
		loginpage.enterPassword(prop.getProperty("validPass"));
		loginpage.clickOnLogInButton();
	}

	@AfterMethod (enabled = true)
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test (priority = 1)
	public void verifySearchWithValidProduct()
	
	{
		SearchPage searchPage = new SearchPage(driver);
		searchPage.enterValidProductNameInSerachField(dataprop.getProperty("validProduct"));
		searchPage.clickOnSearchButton();
		Assert.assertTrue(searchPage.checkValidSearchProducttextdisplaying());
	}
	
	@Test (priority = 2)
	public void verifySearchWithInValidProduct()
	{
		SearchPage searchPage = new SearchPage(driver);
		searchPage.enterInvalidProductIntoSearchField(dataprop.getProperty("inValidProduct"));
		searchPage.clickOnSearchButton();
		String actualInvalidSearchMessage = searchPage.retriveInvalidProductSearchtext();
		System.out.println(actualInvalidSearchMessage);
		Assert.assertTrue("abcd".contains(dataprop.getProperty("productNotMarchingWarningMessage")));
	}

	
	@Test (priority = 3, dependsOnMethods = {"verifySearchWithValidProduct","verifySearchWithInValidProduct" })
	public void verifySearchWithMultipleValidProduct()
	
	
	{
		String Product = "Mac";
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterValidCommonProduct(dataprop.getProperty("multipleProduct"));
		searchpage.clickOnSearchButton();
		List<WebElement> collectioOfProducts = driver.findElements(By.xpath("//div[@class='caption']/h4/a"));
		// Validate the Search results display corresponding to the string which was search
		for(int i=0; i<collectioOfProducts.size(); i++)
		{
			String temp = collectioOfProducts.get(i).getText();
			if ((temp.toLowerCase().contains(Product.toLowerCase()))){
				Assert.assertTrue(true, Product +" is displayed on product title Product Title: " + temp);
			}else {
				Assert.assertTrue(false, Product + " is not displayed on product title Product Title: " + temp);
			}
		}	
	}
	
	@Test (priority = 4)
	public void verifySearchAndNavigateCompareProductPage()
	{	
		SearchPage searchpage = new SearchPage(driver);
		searchpage.enterValidProductNameInSerachField(dataprop.getProperty("validProduct"));
		searchpage.clickOnSearchButton();
		searchpage.clickOnProductCompareLink();
		ProductComparisonPage productcomparisonpage = new ProductComparisonPage(driver);
		String actualInvalidSearchMessage = productcomparisonpage.retriveProductCompariosnText();	
		System.out.println(actualInvalidSearchMessage);		
		Assert.assertTrue(actualInvalidSearchMessage.contains("Product Comparison"));			
	}
	
}
