package ScreenShot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Screenshot {
	public  WebDriver driver ;

	@Test
	public class AutomatedTest {

	    private static final String WEBSITE_URL = "https://www.getcalley.com";
	    private static final String SCREENSHOTS_FOLDER = "screenshots";
	    private static final String DATE_FORMAT = "yyyyMMdd-HHmmss";

	    public void main(String[] args) {
	        // Define the list of devices and resolutions
	        Map<String, String[]> devices = new HashMap<>();
	        devices.put("Desktop", new String[]{"1920x1080", "1366x768", "1536x864"});
	        devices.put("Mobile", new String[]{"360x640", "414x896", "375x667"});

	        // Define the list of browsers
	        String[] browsers = {"chrome", "firefox", "safari"};

	        // Create a folder for screenshots
	        createScreenshotsFolder();

	        // Loop through devices, resolutions, and browsers
	        for (Map.Entry<String, String[]> entry : devices.entrySet()) {
	            String device = entry.getKey();
	            String[] resolutions = entry.getValue();

	            for (String resolution : resolutions) {
	                for (String browser : browsers) {
	                    // Set up the WebDriver
	                driver = getDriver(browser);

	                    // Set the window size based on the resolution
	                    String[] dimensions = resolution.split("x");
	                    int width = Integer.parseInt(dimensions[0]);
	                    int height = Integer.parseInt(dimensions[1]);
	                    driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));

	                    // Open the website
	                    driver.get(WEBSITE_URL);

	                  

	                    // Take a screenshot
	                    takeScreenshot(device, resolution, browser, driver);

	                    // Close the browser
	                    driver.quit();
	                }
	            }
	        }

	        // Print a message when the testing is complete
	        System.out.println("Automated testing completed successfully.");
	    }

	    private WebDriver getDriver(String browser) {
	        switch (browser) {
	            case "chrome":
	                return new ChromeDriver();
	            case "firefox":
	                return new FirefoxDriver();
	            case "safari":
	                return new SafariDriver();
	            default:
	                throw new IllegalArgumentException("Unsupported browser: " + browser);
	        }
	    }

	    private void createScreenshotsFolder() {
	        // Create a folder for screenshots
	        new File(SCREENSHOTS_FOLDER).mkdir();
	    }

	    private void takeScreenshot(String device, String resolution, String browser, WebDriver driver) {
	        // Take a screenshot
	        try {
	            TakesScreenshot screenshot = (TakesScreenshot) driver;
	            File source = screenshot.getScreenshotAs(OutputType.FILE);

	            String screenshotName = String.format("%s/%s/%s/Screenshot-%s-%s.png", SCREENSHOTS_FOLDER,
	                    device, resolution, getCurrentDateTime(), browser);
	            File destination = new File(screenshotName);
	            FileUtils.copyFile(source, destination);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private String getCurrentDateTime() {
	        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	        return dateFormat.format(new Date());
	    }
	}

}
