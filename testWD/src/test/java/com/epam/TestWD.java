package com.epam;

import com.epam.bean.Person;
import com.epam.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestWD {

    private Steps steps;
    private final String LOGIN = "mashkevich.nastya@mail.ru";
    private final String PASSWORD = "wertyu1";
    private final String CITYOFDEPARTURE = "Warsaw";
    private final String CITYOFARRIVLE = "Barcelona";
    private final String SERVICECOUNTRY = "France";
    private final String DATEOFFLIGHT = "5/April";
    private final String DATEFORWARD = "2/April";
    private final String DATEBACK = "14/April";
    private final String FLIGHTNUMBER = "8845";
    private final int NUMBEROFPESENGER = 2;
    private final String CITY = "Madrid";
    private final int FLIGHTWITHRETURN = 2;

    @BeforeTest(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.initBrowser();
    }

    @Test
    public void oneCanLoginGithub() {
        steps.loginToVueling(LOGIN, PASSWORD);
        Assert.assertTrue(steps.isLoginToVueling());
    }

    @Test
    public void oneCanChooseServiceCenter() {
        Assert.assertTrue(steps.isChangeContactInfo(SERVICECOUNTRY));
    }

    @Test
    public void oneCanSearchFlight() {
        steps.startWorkWithMainPage(CITYOFDEPARTURE, CITYOFARRIVLE, DATEFORWARD);
        Assert.assertTrue(steps.checkFlight(DATEBACK));
    }

    @Test
    public void oneCanFillInfoAboutPassenger() {
        Person person = new Person("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
        steps.startWorkWithMainPage(CITYOFDEPARTURE, CITYOFARRIVLE, DATEFORWARD);
        steps.fillPassengerInformation(person, DATEBACK);
        Assert.assertTrue(steps.isFillInfoCorrect(person));
    }

    @Test
    public void oneCanCheckStatusFlight() {
        steps.startWorkWithFlightsStatusPage(FLIGHTNUMBER, DATEOFFLIGHT);
        Assert.assertEquals(steps.checkFlightStatus(), ("Not operating"));
        Assert.assertEquals(steps.checkDateFlight(), DATEOFFLIGHT);
        Assert.assertEquals(steps.checkCityDeparture(), CITYOFDEPARTURE);
        Assert.assertEquals(steps.checkCityArrival(), CITYOFARRIVLE);
    }

    @Test
    public void oneCanCheckWrightPriceForTwoPassenger() {
        steps.canChooseFlightOneWay(CITYOFDEPARTURE, CITYOFARRIVLE, DATEFORWARD);
        Assert.assertEquals((steps.takePriceFromWebSiteFor1Passenger() * NUMBEROFPESENGER), steps.takeTotalPriceForAllPassenger());
    }

    @Test
    public void canFindAirport() {
        steps.checkAirport(CITY);
        Assert.assertTrue(steps.isAirportFound());
    }

    @Test
    public void oneCanCheckAddLuggage() {
        Person person = new Person("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
        steps.startWorkWithMainPage(CITYOFDEPARTURE, CITYOFARRIVLE, DATEFORWARD);
        steps.addLuggage(person, DATEBACK);
        Assert.assertEquals(steps.takePriceForPassengerWithLuggage(), steps.takePriceForPassengerWithLuggageFromTable());
    }

    @Test
    public void oneCanCheckAddSeat() throws InterruptedException {
        Person person = new Person("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
        steps.startWorkWithMainPage(CITYOFDEPARTURE, CITYOFARRIVLE, DATEFORWARD);
        steps.addSeat(person, DATEBACK);
        Assert.assertEquals(steps.takeTotalPriceForPassengerWithSeats()/FLIGHTWITHRETURN, steps.takePriceForPassengerWithSeatsFromTable());

    }

    @AfterTest(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();
    }

}
