package mytests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;

public class MainClass {

	public WebDriver driver;
	public Properties CONFProp;
	public Properties OBJECTProp;
	public EyesRunner runner;
	public Eyes eyes;
	public static BatchInfo batch;
	public TestResultsSummary allTestResults;


	public WebDriver getDriver() {
		return driver;
	}

	public Properties getObjectProp() {
		return OBJECTProp;
	}



	@BeforeSuite
	public void setupConfigFileRead() {		
		CONFProp = new Properties();		

		try {
			FileInputStream configfile = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\propertiesFiles\\Configuation.properties"));
			CONFProp.load(configfile);			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@BeforeSuite
	public void setupObjectFileRead() {		
		OBJECTProp = new Properties();		

		try {
			FileInputStream objectRepofile = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\propertiesFiles\\ObjectRepository.properties"));
			OBJECTProp.load(objectRepofile);			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void openBrowser() {
		Map<String, Object> prefs = new HashMap<String, Object>(); 
		prefs.put("profile.default_content_settings.popups", 0);			
		prefs.put("download.prompt_for_download", "false"); 
		prefs.put("plugins.always_open_pdf_externally",true);

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs",prefs);
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("test-type");
		options.addArguments("--no-sandbox");// Bypass OS security model
		options.addArguments("enable-automation"); 		

		options.addArguments("--disable-infobars");
		options.addArguments("--disable-popup-blocking");//To Enable window to open FOR INTRANET
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-dev-shm-usage");// overcome limited resource problems
		options.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\drivers\\chromedriver.exe");

		// Must be before ALL tests (at Class-level)
		batch = new BatchInfo("Demo batch");
		// Initialize the Runner for your test.
		runner = new ClassicRunner();		

		driver = new ChromeDriver(options);	

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	} 

	@BeforeMethod	
	public void openEyes() {
		// Initialize the eyes SDK
		eyes = new Eyes(runner);		
		// Set your personal Applitols API Key from your environment variables.
		eyes.setApiKey("WeUnFG6CIROJLKtUAz3jx1RvJkjmccHOMH9refqNrmI110");
		// set batch name
		eyes.setBatch(batch);
	}

	@AfterTest
	public void tearDown() {

		if (driver !=null) {
			driver.quit();
		}
		// If the test was aborted before eyes.close was called, ends the test as
		// aborted.
		eyes.abortIfNotClosed();

		/*
		 * // Wait and collect all test results allTestResults =
		 * runner.getAllTestResults();
		 */
	}

	@AfterMethod
	public void closeEyes() {
		//End the tests
		eyes.closeAsync();
	}

}
