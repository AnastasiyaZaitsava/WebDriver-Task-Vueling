package com.epam.steps;

import com.epam.bean.Person;
import com.epam.pages.ContactPassengerPage;
import com.epam.pages.MainPage;
import com.epam.pages.ScheduleSelectPage;
import com.epam.util.DriverSingleton;
import org.openqa.selenium.WebDriver;


public class Steps {
    private WebDriver driver;

    public void initBrowser()
    {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver()
    {
        driver.close();
    }

    public void loginToVueling (String user, String psw)
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.login(user,psw);
    }

    public boolean isLoginToVueling ()
    {
        MainPage vuelingMainPage = new MainPage(driver);
        return vuelingMainPage.checkIsLogin().contains("Hi");
    }

    public void fillPassengerInformation(String from, String to, Person person) {
        ContactPassengerPage contactPassengerPage = new ContactPassengerPage(driver);
        ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.startSearch(from, to);
        scheduleSelectPage.chooseFlight();
        contactPassengerPage.enterAndSubmitPassengerContact(person);
    }

    public boolean isChangeContactInfo (String country)
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        return mainPage.toChangeContactInfo(country);
    }

    public boolean checkFlight (String from, String to)
    {
        MainPage mainPage = new MainPage(driver);
        ScheduleSelectPage vuelingScheduleSelectPage = new ScheduleSelectPage(driver);
        mainPage.openPage();
        mainPage.startSearch( from,  to);
       return  vuelingScheduleSelectPage.chooseFlight();
    }

    public boolean isFillInfoCorrect (String from, String to, Person person)
    {
        ContactPassengerPage contactPassengerPage = new ContactPassengerPage(driver);
        contactPassengerPage.enterAndSubmitPassengerContact(person);
        return contactPassengerPage.clickSubmit();
    }

}
