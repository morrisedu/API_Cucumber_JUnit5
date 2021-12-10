package com.cydeo.utility;

/*
    This class will return a single driver object, regardless of the number of times it is called
    This class employs the Singleton pattern
*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static com.cydeo.utility.ConfigReader.confRead;

public class Driver {
    private static WebDriver obj;
    private static String browser = confRead("browser");

    private Driver() {}

    /**
        Return object witj only one WebDriver instance
        Will return the same object if it already exists. It will only create a new one if it is null
    */
    public static WebDriver getDriver() {
        if (obj == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    obj = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    obj = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    obj = new EdgeDriver();
                    break;
                default:
                    obj = null;
                    break;
            }

            return obj;
        } else {
            return obj;
        }
    }

    /**
        Quitting the browser and resetting the value of WebDriver instance to null to be able to reuse already quit driver
     */
    public static void closeBrowser() {
        /**
            1. Check if there is a driver instance or not i.e. check whether the obj is null or not
            2. if obj isn't null, quit browser & make it null
            ** Once quit, the driver cannot be used again
         */
        if (obj != null) {
            obj.quit();
            obj = null;
        }
    }
}
