package mytests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.applitools.eyes.RectangleSize;

public class V2_VisualAITests extends MainClass{

	WebDriverWait wait;
	@Test(priority = 1)
	public void Appli_loginPageUIElementTest() throws Exception {
		// Navigate the browser to the "ACME" demo app.		
		//driver.get(CONFProp.getProperty("HackathonV1BASEURL"));
		driver.get(CONFProp.getProperty("HackathonV2BASEURL"));

		// Set AUT's name, test name and viewport size (width X height)
		// We have set it to 800 x 600 to accommodate various screens. Feel free to
		// change it.
		eyes.open(driver, "DemoV1", "Appli_loginPageUIElementTest", new RectangleSize(800, 800));

		// Visual checkpoint #1 - Check the login page.
		eyes.checkWindow("Appli_loginPageUIElementTest");

	}
	
	@DataProvider(name = "loginTestDataProvider")
	public static Object[][] credentials() {
		return new Object[][] { 
			{ "", "" },
			{ "testuser_1", "" },
			{ "", "Test@123" },        	 
			{ "testinguser_1", "Testing@123" }};
	}

	@Test(priority = 2,dataProvider = "loginTestDataProvider")
	public void Appli_dataDrivenTest(String username, String password) throws Exception {
		
		driver.navigate().to(CONFProp.getProperty("HackathonV2BASEURL"));
		wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		eyes.open(driver, "DemoV1", "Appli_dataDrivenTest", new RectangleSize(800, 800));

		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("log-in")).click();
		eyes.checkWindow("Appli_dataDrivenTest");

	}

	@Test(priority = 3)
	public void Appli_TableSortingTest() throws Exception {
		driver.navigate().to(CONFProp.getProperty("HackathonV2BASEURL"));
		wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		
		eyes.open(driver, "DemoV1", "Appli_TableSortingTest", new RectangleSize(800, 800));	

		driver.findElement(By.id("username")).sendKeys("adasdasd");
		driver.findElement(By.id("password")).sendKeys("asdasd");
		driver.findElement(By.id("log-in")).click();
		
		driver.findElement(By.id("amount")).click();
		Thread.sleep(2000);
		
		eyes.checkWindow("Appli_TableSortingTest");

	}

	@Test(priority = 4)
	public void Appli_CanvasChartTest() throws Exception {
		
		driver.navigate().to(CONFProp.getProperty("HackathonV2BASEURL"));
		wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		
		

		driver.findElement(By.id("username")).sendKeys("adasdasd");
		driver.findElement(By.id("password")).sendKeys("asdasd");
		driver.findElement(By.id("log-in")).click();
		eyes.open(driver, "DemoV1", "Appli_CanvasChartTest", new RectangleSize(800, 800));	
		driver.findElement(By.linkText("Compare Expenses")).click();
		Thread.sleep(2000);
		
		eyes.checkWindow("Appli_CanvasChartTest");
		Thread.sleep(2000);
		driver.findElement(By.id("addDataset")).click();
		Thread.sleep(2000);
		eyes.checkWindow("Appli_CanvasChartTest");

	}

	@Test(priority = 5)
	public void Appli_DynamicContentTest() throws Exception {
		
		driver.navigate().to(CONFProp.getProperty("HackathonV2BASEURL"));
		wait = new WebDriverWait(getDriver(), 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));				

		driver.findElement(By.id("username")).sendKeys("adasdasd");
		driver.findElement(By.id("password")).sendKeys("asdasd");
		driver.findElement(By.id("log-in")).click();
		eyes.open(driver, "DemoV1", "Appli_DynamicContentTest", new RectangleSize(800, 800));
		eyes.checkWindow("Appli_DynamicContentTest");

	}

}
