package mytests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utility.General;

public class V1_TraditionalTests extends MainClass{

	WebDriverWait wait;
	General general = new General(getDriver(), getObjectProp());
		
	@Test(priority = 1)
	public void v1loginPageUIElementTest() throws Exception {
		System.out.println("loginPageUIElementTest !");		
		driver.get(CONFProp.getProperty("HackathonV1BASEURL"));

		assertEquals(driver.findElement(general.getLocator(getObjectProp().getProperty("AuthHeader"))).getText(), "Login Form");

		assertEquals(driver.findElement(general.getLocator(getObjectProp().getProperty("UsernameLabel"))).getText(), "Username");
		assertEquals(driver.findElement(general.getLocator(getObjectProp().getProperty("PasswordLabel"))).getText(), "Password");

		assertEquals(driver.findElement(By.id("username")).getAttribute("placeholder"), "Enter your username");
		assertEquals(driver.findElement(By.id("password")).getAttribute("placeholder"), "Enter your password");
		assertEquals(driver.findElement(By.id("log-in")).getText(), "Log In");

		assertEquals(driver.findElement(general.getLocator(getObjectProp().getProperty("RememberMeLabel"))).getText(), "Remember Me");

		assertTrue(driver.findElement(general.getLocator(getObjectProp().getProperty("TwitterIcon"))) instanceof WebElement);
		assertTrue(driver.findElement(general.getLocator(getObjectProp().getProperty("FacebookIcon"))) instanceof WebElement);
		assertTrue(driver.findElement(general.getLocator(getObjectProp().getProperty("LinkdinIcon"))) instanceof WebElement);

	}

	@DataProvider(name = "loginTestData")
	public static Object[][] credentials() {
		return new Object[][] { 
			{ "", "" },
			{ "testuser_1", "" },
			{ "", "Test@123" },        	 
			{ "testinguser_1", "Testing@123" }};
	}


	@Test(priority = 2, dataProvider = "loginTestData")
	public void v1dataDrivenTest(String username, String password) throws Exception {
		System.out.println("dataDrivenTest :: Username -"+username+" == Password - "+password);		

		driver.navigate().to(CONFProp.getProperty("HackathonV1BASEURL"));
		wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("log-in")).click();
		Thread.sleep(2000);
		if(username.isEmpty() && password.isEmpty()) {
			assertEquals(driver.findElement(general.getLocator(getObjectProp().getProperty("LoginErrorAlertWarning"))).getText(), "Both Username and Password must be present");
		} else if (username.isEmpty()){
			assertEquals(driver.findElement(general.getLocator(getObjectProp().getProperty("LoginErrorAlertWarning"))).getText(), "Username must be present");
		} else if (password.isEmpty()){
			assertEquals(driver.findElement(general.getLocator(getObjectProp().getProperty("LoginErrorAlertWarning"))).getText(), "Password must be present");
		} else {
			assertTrue(driver.findElement(general.getLocator(getObjectProp().getProperty("LoggedInUserIcon"))) instanceof WebElement);
		}
	}
	
	
	@Test(priority = 3)
	public void v1TableSortingTest() throws Exception {
		driver.navigate().to(CONFProp.getProperty("HackathonV1BASEURL"));
		wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

		driver.findElement(By.id("username")).sendKeys("adasdasd");
		driver.findElement(By.id("password")).sendKeys("asdasd");
		driver.findElement(By.id("log-in")).click();
		Thread.sleep(2000);
		assertTrue(driver.findElement(general.getLocator(getObjectProp().getProperty("LoggedInUserIcon"))) instanceof WebElement);
		
		
		wait = new WebDriverWait(getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("amount")));
		driver.findElement(By.id("amount")).click();
		
		ArrayList<String> obtainedList  = new ArrayList();
		List<WebElement> preSortingTableColumnList = driver.findElements(By.cssSelector("tr td:nth-child(5)"));
				
		for (WebElement tableColumnElement : preSortingTableColumnList) {
			obtainedList.add(tableColumnElement.getText());			
		}
		
		System.out.println(obtainedList);		
		
		ArrayList<String> sortedList = new ArrayList<>();
		for(String s:obtainedList){
			sortedList.add(s);
		}
		System.out.println(sortedList);
		System.out.println("SORT NOW");
		Collections.sort(sortedList);
		System.out.println(sortedList);
		
		assertTrue(sortedList.equals(obtainedList));
		
	}
	
	@Test(priority = 4)
	public void v1CanvasChartTest() throws Exception {
		/* Unable to automate it,
		 * We can't inspect canvas chart to make validations.
		 */		
		
	}
	
	@Test(priority = 5)
	public void v1DynamicContentTest() throws Exception {
				
		driver.navigate().to(CONFProp.getProperty("HackathonV1BASEURL"));
		wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

		driver.findElement(By.id("username")).sendKeys("adasdasd");
		driver.findElement(By.id("password")).sendKeys("asdasd");
		driver.findElement(By.id("log-in")).click();
		Thread.sleep(2000);
		assertTrue(driver.findElement(general.getLocator(getObjectProp().getProperty("LoggedInUserIcon"))) instanceof WebElement);
		
		assertTrue(driver.findElement(By.id("flashSale")) instanceof WebElement);
		assertTrue(driver.findElement(By.id("flashSale2")) instanceof WebElement);		
	}
}
