package com.epam.steps;

import com.epam.bean.Person;
import com.epam.pages.ContactPassengerPage;
import com.epam.pages.FlightsStatusPage;
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

    public void fillPassengerInformation(Person person, String dateBack) {
        ContactPassengerPage contactPassengerPage = new ContactPassengerPage(driver);
        MainPage mainPage = new MainPage(driver);
        ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
        mainPage.chooseDateFlight(dateBack);
        mainPage.clickButtonSearchFlight();
        scheduleSelectPage.chooseFlight();
        contactPassengerPage.enterAndSubmitPassengerContact(person);
    }

    public boolean isChangeContactInfo (String country)
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        return mainPage.toChangeContactInfo(country);
    }

    public boolean checkFlight (String dateBack)
    {
        MainPage mainPage = new MainPage(driver);
        ScheduleSelectPage vuelingScheduleSelectPage = new ScheduleSelectPage(driver);
        mainPage.chooseDateFlight(dateBack);
        mainPage.clickButtonSearchFlight();
       return  vuelingScheduleSelectPage.chooseFlight();
    }

    public boolean isFillInfoCorrect (Person person)
    {
        ContactPassengerPage contactPassengerPage = new ContactPassengerPage(driver);
        contactPassengerPage.enterAndSubmitPassengerContact(person);
        return contactPassengerPage.clickSubmit();
    }

    public String checkFlightStatus ()
    {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        return flightsStatusPage.takeStatusFlight();
    }

    public String checkDateFlight ()
    {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        String[] date = flightsStatusPage.takeFlightDate().split(" ");
        return date[1] + "/" + date[2];
    }

    public String checkCityDeparture ()
    {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        return flightsStatusPage.takeCityDeparture();
    }

    public String checkCityArrival ()
    {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        return flightsStatusPage.takeCityArrival();
    }

    public void startWorkWithFlightsStatusPage (String flightNumber, String dateOfFlight)
    {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        flightsStatusPage.openPage();
        flightsStatusPage.flightsStatusForFlightNumber(flightNumber, dateOfFlight);
    }

    public void startWorkWithMainPage (String cityOfDeparture, String cityOfArrival, String dateForward)
    {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.chooseCityForLight(cityOfDeparture, cityOfArrival);
        mainPage.chooseDateFlight(dateForward);
    }
    
      public boolean checkAirport(String city)
    {
        InfoAndSalesOfficesPage infoAndSalesOfficesPage = new InfoAndSalesOfficesPage(driver);
        infoAndSalesOfficesPage.openPage();
        infoAndSalesOfficesPage.getToInfoAndSalesOfficesPage();
        return infoAndSalesOfficesPage.chooseCity(city);
    }

    public boolean isAirportFound()
    {
        InfoAndSalesOfficesPage infoAndSalesOfficesPage = new InfoAndSalesOfficesPage(driver);
        return  infoAndSalesOfficesPage.isAirportDisplayed();
    }
    

}
