package com.epam.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class DriverSingleton {

    private static WebDriver driver;
    private DriverSingleton(){};


    public static WebDriver getDriver()
    {
        if (null == driver){
            driver = new FirefoxDriver();
            driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        return driver;
    }

    public static void closeDriver()
    {
        driver.close();
        driver = null;
    }
}