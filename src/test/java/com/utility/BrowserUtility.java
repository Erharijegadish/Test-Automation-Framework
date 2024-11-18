package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public BrowserUtility(WebDriver driver) {
        BrowserUtility.driver.set(driver); //initialize the instance variable driver
    }

    public BrowserUtility(String browserName) {
        logger.info("Launching  the browser: " + browserName);
        if(browserName.equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver());
        }
        else if(browserName.equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver());
        }
        else {
            logger.error("Invalid Browser Name...Please select chrome or edge");
            System.err.println("Invalid Browser Name...Please select chrome or edge");
        }
    }

    public BrowserUtility(Browser browserName) {
        logger.info("Launching  the browser: " + browserName);
        if(browserName == Browser.CHROME) {
            driver.set(new ChromeDriver());
        }
        else if(browserName == Browser.EDGE) {
            driver.set(new EdgeDriver());
        }
        else {
            logger.error("Invalid Browser Name...Please select chrome or edge");
            System.err.println("Invalid Browser Name...Please select chrome or edge");
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless) {
        logger.info("Launching  the browser: " + browserName);
        if(browserName == Browser.CHROME) {
            if(isHeadless) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=old");
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }
            else {
                driver.set(new ChromeDriver());
            }
        }
        else if(browserName == Browser.EDGE) {
            if(isHeadless) {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old");
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options));
            }
            else {
                driver.set(new EdgeDriver());
            }
        }
        else if(browserName == Browser.FIREFOX) {
            if(isHeadless) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old");
                options.addArguments("disable-gpu");
                driver.set(new FirefoxDriver(options));
            }
            else {
                driver.set(new FirefoxDriver());
            }
        }
        else {
            logger.error("Invalid Browser Name...Please select chrome or edge");
            System.err.println("Invalid Browser Name...Please select chrome or edge");
        }
    }

    public void goToWebsite(String url) {
        logger.info("Visiting the website: " + url);
        driver.get().get(url);
    }

    public void maximizeWindow() {
        logger.info("Maximizing the browser window");
        driver.get().manage().window().maximize();
    }

    public void clickOn(By locator) {
        logger.info("Finding element with the locator: " + locator);
        WebElement element = driver.get().findElement(locator); //finds the element
        logger.info("Element found and performing click operation");
        element.click();
    }

    public void enterText(By locator, String textToEnter) {
        logger.info("Finding element with the locator: " + locator);
        WebElement element = driver.get().findElement(locator);
        element.sendKeys(textToEnter);
        logger.info("Element found and entering the text: " + textToEnter);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public String getVisibleText(By locator) {
        logger.info("Finding element with the locator: " + locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and returning the visible text: " + element.getText());
        return element.getText();
    }

    public String takeScreenshot(String name) {
        String path = System.getProperty("user.dir")+"//screenshots//" + name + "-" + getTimeStamp() + ".png";
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }

    public String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        return format.format(date);
    }

    public void quit() {
        driver.get().quit();
    }
}
