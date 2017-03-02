package com.epam.steps;

import com.epam.pages.VuelingContactPassengerPage;
import com.epam.pages.VuelingMainPage;
import com.epam.util.DriverSingleton;
import org.openqa.selenium.WebDriver;

/**
 * Created by Anastasiya_Mashkevic on 3/1/2017.
 */
public class Steps {
    private WebDriver driver;

    public WebDriver getDriver()
    {
        return this.driver;
    }

    public void initBrowser()
    {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver()
    {
        driver.quit();
    }

    public void loginToVueling (String user, String psw) {
        VuelingMainPage vuelingMainPage = new VuelingMainPage(driver);
        vuelingMainPage.openPage();
        vuelingMainPage.login(user,psw);
    }

    public boolean isLoginToVueling () {
        VuelingMainPage vuelingMainPage = new VuelingMainPage(driver);
        return vuelingMainPage.checkIsLogin().contains("Hi");
    }

    public void fillPassengerInformation()
    {
        VuelingContactPassengerPage vcpp = new VuelingContactPassengerPage(driver);
        // где будут храниться данные для ввода?
        vcpp.enterAndSubmitPassengerContact("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
    }



}
