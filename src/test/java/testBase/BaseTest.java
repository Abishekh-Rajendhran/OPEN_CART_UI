package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest {

	public static WebDriver driver;
	public Logger logger;

	public Properties properties;

	@SuppressWarnings("deprecation")
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
	    // Initialize logger
	    logger = LogManager.getLogger(this.getClass());

	    // Load configuration properties
	    FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config.properties");
	    properties = new Properties();
	    properties.load(fileInputStream);

	    String environment = properties.getProperty("execution_environment").toLowerCase();

	    if (environment.equals("remote")) {
	        DesiredCapabilities capabilities = new DesiredCapabilities();

	        // Set platform
	        if (os.equalsIgnoreCase("windows")) {
	            capabilities.setCapability("platformName", "Windows");
	        } else if (os.equalsIgnoreCase("mac")) {
	            capabilities.setCapability("platformName", "macOS");
	        } else if (os.equalsIgnoreCase("linux")) {
	            capabilities.setCapability("platformName", "Linux");
	        } else {
	            logger.error("Invalid OS specified: " + os);
	            throw new IllegalArgumentException("Invalid OS specified: " + os);
	        }

	        // Set browser
	        switch (br.toLowerCase()) {
	            case "chrome": 
	                capabilities.setCapability("browserName", "chrome");
	                break;
	            case "edge": 
	                capabilities.setCapability("browserName", "MicrosoftEdge");
	                break;
	            case "firefox": 
	                capabilities.setCapability("browserName", "firefox");
	                break;
	            default: 
	                logger.error("Invalid Browser specified: " + br);
	                throw new IllegalArgumentException("Invalid Browser specified: " + br);
	        }

	        // Initialize RemoteWebDriver
	        try {
	            driver = new RemoteWebDriver(new URL(properties.getProperty("GridURL")), capabilities);
	            logger.info("Remote WebDriver session created successfully");
	        } catch (Exception e) {
	            logger.error("Failed to create Remote WebDriver session", e);
	            throw new RuntimeException("Failed to initialize Remote WebDriver", e);
	        }
	    } else if (environment.equals("local")) {
	        // Initialize local WebDriver
	        switch (br.toLowerCase()) {
	            case "chrome":
	                driver = new ChromeDriver();
	                break;
	            case "firefox":
	                driver = new FirefoxDriver();
	                break;
	            case "edge":
	                driver = new EdgeDriver();
	                break;
	            default:
	                logger.error("Invalid Browser specified: " + br);
	                throw new IllegalArgumentException("Invalid Browser specified: " + br);
	        }
	    } else {
	        logger.error("Invalid execution_environment specified in config.properties: " + environment);
	        throw new IllegalArgumentException("Invalid execution_environment specified in config.properties: " + environment);
	    }

	    // Common WebDriver setup
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    logger.info("Browser window maximized");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    logger.info("Implicit wait set to 10 seconds");
	    driver.get(properties.getProperty("URL"));
	    logger.info("Navigated to URL: " + properties.getProperty("URL"));
	}


	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void tearDown() {
		logger.info("windows Closed!.. .");
		driver.quit();
	}
	
	public String Screenshot(String testName) {
		
		String currentDateStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		TakesScreenshot ss = (TakesScreenshot) driver;
		File sourceFile = ss.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "./screenshots/" + testName+ "_" +currentDateStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	
	}

	@SuppressWarnings("deprecation")
	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(6);
		return generatedString;
	}

	@SuppressWarnings("deprecation")
	public String randomNumeric() {
		String generatedNumerics = RandomStringUtils.randomNumeric(10);
		return generatedNumerics;
	}

	@SuppressWarnings("deprecation")
	public String randomAlphaNumeric() {
		String generatedAlphaNumerics = RandomStringUtils.randomAlphanumeric(8);
		return generatedAlphaNumerics;
	}

}
