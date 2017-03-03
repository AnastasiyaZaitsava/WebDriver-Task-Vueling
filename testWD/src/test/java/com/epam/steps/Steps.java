package com.epam.steps;

import com.epam.pages.VuelingChooseSeatAndLuggagePage;
import com.epam.util.User;
import com.epam.pages.VuelingContactPassengerPage;
import com.epam.pages.VuelingMainPage;
import com.epam.pages.VuelingScheduleSelectPage;
import com.epam.util.DriverSingleton;
import org.openqa.selenium.WebDriver;


public class Steps {
    private WebDriver driver;

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeDriver() {
        driver.close();
    }

    public void loginToVueling(String user, String psw) {
        VuelingMainPage vuelingMainPage = new VuelingMainPage(driver);
        vuelingMainPage.openPage();
        vuelingMainPage.login(user, psw);
    }

    public boolean isLoginToVueling() {
        VuelingMainPage vuelingMainPage = new VuelingMainPage(driver);
        return vuelingMainPage.checkIsLogin().contains("Hi");
    }

    public void fillPassengerInformation(String from, String to, User user) {
        VuelingContactPassengerPage vuelingContactPassengerPage = new VuelingContactPassengerPage(driver);
        VuelingScheduleSelectPage vuelingScheduleSelectPage = new VuelingScheduleSelectPage(driver);
        VuelingMainPage vuelingMainPage = new VuelingMainPage(driver);
        vuelingMainPage.openPage();
        vuelingMainPage.startSearch(from, to);
        vuelingScheduleSelectPage.chooseFlight();
        vuelingContactPassengerPage.enterAndSubmitPassengerContact(user);
    }

    public void fillSeatsAndLuggageInformation(String from, String to, User user) {
        VuelingContactPassengerPage vuelingContactPassengerPage = new VuelingContactPassengerPage(driver);
        VuelingScheduleSelectPage vuelingScheduleSelectPage = new VuelingScheduleSelectPage(driver);
        VuelingMainPage vuelingMainPage = new VuelingMainPage(driver);
        VuelingChooseSeatAndLuggagePage vuelingChooseSeatAndLuggagePage = new VuelingChooseSeatAndLuggagePage(driver);
        vuelingMainPage.openPage();
        vuelingMainPage.startSearch(from, to);
        vuelingScheduleSelectPage.chooseFlight();
        vuelingContactPassengerPage.enterAndSubmitPassengerContact(user);
        vuelingChooseSeatAndLuggagePage.chooseSeatAndAddLuggage();
    }

    public boolean isChangeContactInfo(String country) {
        VuelingMainPage vuelingMainPage = new VuelingMainPage(driver);
        vuelingMainPage.openPage();
        //vuelingMainPage.toChangeContactInfo(country);
        if (vuelingMainPage.toChangeContactInfo(country)) {
            return true;
        }
        return false;
    }

    public boolean checkFlight(String from, String to) {
        VuelingMainPage vuelingMainPage = new VuelingMainPage(driver);
        VuelingScheduleSelectPage vuelingScheduleSelectPage = new VuelingScheduleSelectPage(driver);
        vuelingMainPage.openPage();
        vuelingMainPage.startSearch(from, to);

        if (vuelingScheduleSelectPage.chooseFlight()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean isFillInfoCorrect(String from, String to, User user) {
        VuelingContactPassengerPage vuelingContactPassengerPage = new VuelingContactPassengerPage(driver);
        vuelingContactPassengerPage.enterAndSubmitPassengerContact(user);
        if (vuelingContactPassengerPage.clickSubmit()) {
            return true;
        }
        return false;
    }

    public boolean isFillInfoSeatAndLuggageCorrect() {
        VuelingChooseSeatAndLuggagePage vuelingChooseSeatAndLuggagePage = new VuelingChooseSeatAndLuggagePage(driver);
        return vuelingChooseSeatAndLuggagePage.isServiceBlockDisplayed();
    }

}
