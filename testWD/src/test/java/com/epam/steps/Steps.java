package com.epam.steps;

import com.epam.bean.Person;
import com.epam.pages.*;
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
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.login(user, psw);
    }

    public boolean isLoginToVueling() {
        MainPage vuelingMainPage = new MainPage(driver);
        return vuelingMainPage.checkIsLogin().contains("Hi");
    }

    public void startWorkWithFlightsStatusPage(String flightNumber, String dateOfFlight) {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        flightsStatusPage.openPage();
        flightsStatusPage.flightsStatusForFlightNumber(flightNumber, dateOfFlight);
    }

    public void startWorkWithMainPage(String cityOfDeparture, String cityOfArrival, String dateForward) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.chooseFlightReturn();
        mainPage.chooseCityForLight(cityOfDeparture, cityOfArrival);
        mainPage.chooseDateFlight(dateForward);
    }

    public void fillPassengerInformation(Person person, String dateBack) {
        ContactPassengerPage contactPassengerPage = new ContactPassengerPage(driver);
        MainPage mainPage = new MainPage(driver);
        ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
        mainPage.chooseDateFlight(dateBack);
        mainPage.clickButtonSearchFlight();
        scheduleSelectPage.chooseFlightTwoWays();
        contactPassengerPage.enterAndSubmitPassengerContact(person);
    }

    public boolean isChangeContactInfo(String country) {
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        return mainPage.isPhoneCorrespondsToTheCountry(mainPage.toChangeContactInfo(country), country);
    }

    public double takePriceFromWebSiteFor1Passenger() {
        ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
        scheduleSelectPage.chooseFlightOneWays();
        return scheduleSelectPage.getPriceForOnePassenger(scheduleSelectPage.getWebElementTocheackPrice());
    }

    public double takeTotalPriceForAllPassenger() {
        ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
        return scheduleSelectPage.getTotalPrice();

    }

     public double takeBookingFee() {
    	 ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
         return scheduleSelectPage.getFee();
    }
    
    public void canChooseFlightOneWay(String cityOfDeparture, String cityOfArrival, String dateForward) {
        ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.openPage();
        mainPage.chooseFlightOneWay();
        mainPage.chooseCityForLight(cityOfDeparture, cityOfArrival);
        mainPage.chooseDateFlight(dateForward);
        mainPage.chooseTwoPassenger();
        mainPage.clickButtonSearchFlight();
    }

    public boolean checkFlight(String dateBack) {
        MainPage mainPage = new MainPage(driver);
        ScheduleSelectPage vuelingScheduleSelectPage = new ScheduleSelectPage(driver);
        mainPage.chooseDateFlight(dateBack);
        mainPage.clickButtonSearchFlight();
        return vuelingScheduleSelectPage.chooseFlightTwoWays();
    }

    public boolean isFillInfoCorrect(Person person) {
        ContactPassengerPage contactPassengerPage = new ContactPassengerPage(driver);
        contactPassengerPage.enterAndSubmitPassengerContact(person);
        return contactPassengerPage.clickSubmit();
    }

    public String checkFlightStatus() {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        return flightsStatusPage.takeStatusFlight();
    }

    public String checkDateFlight() {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        String[] date = flightsStatusPage.takeFlightDate().split(" ");
        return date[1] + "/" + date[2];
    }

    public String checkCityDeparture() {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        return flightsStatusPage.takeCityDeparture();
    }

    public String checkCityArrival() {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        return flightsStatusPage.takeCityArrival();
    }

    public boolean checkAirport(String city) {
        InfoAndSalesOfficesPage infoAndSalesOfficesPage = new InfoAndSalesOfficesPage(driver);
        infoAndSalesOfficesPage.openPage();
        infoAndSalesOfficesPage.getToInfoAndSalesOfficesPage();
        return infoAndSalesOfficesPage.chooseCity(city);
    }

    public boolean isAirportFound() {
        InfoAndSalesOfficesPage infoAndSalesOfficesPage = new InfoAndSalesOfficesPage(driver);
        return infoAndSalesOfficesPage.isAirportDisplayed();
    }

    public void addLuggage(Person person, String dateBack) {
        ContactPassengerPage contactPassengerPage = new ContactPassengerPage(driver);
        MainPage mainPage = new MainPage(driver);
        ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
        SeatAndLuggagePage seatAndLuggagePage = new SeatAndLuggagePage(driver);
        mainPage.chooseDateFlight(dateBack);
        mainPage.clickButtonSearchFlight();
        
     public boolean enterHotelParameters() {
        HotelPage hotelPage = new HotelPage(driver);
        hotelPage.openPage();
        hotelPage.fillInfo();
        return hotelPage.clickSubmit();
    }
        
    public boolean isHotelsFound() {
    	WebElement pageTitle = driver.findElement(By.className("sr_header"));
    	if(pageTitle.getText().contains("properties found")){
                return true;
    	}
    	else
    		return false;
    
    }        scheduleSelectPage.chooseFlightTwoWays();
        contactPassengerPage.enterAndSubmitPassengerContact(person);
        contactPassengerPage.clickSubmit();
        seatAndLuggagePage.addLuggageToTicket();

    }

    public void addSeat(Person person, String dateBack) throws InterruptedException {
        ContactPassengerPage contactPassengerPage = new ContactPassengerPage(driver);
        MainPage mainPage = new MainPage(driver);
        ScheduleSelectPage scheduleSelectPage = new ScheduleSelectPage(driver);
        SeatAndLuggagePage seatAndLuggagePage = new SeatAndLuggagePage(driver);
        mainPage.chooseDateFlight(dateBack);
        mainPage.clickButtonSearchFlight();
        scheduleSelectPage.chooseFlightTwoWays();
        contactPassengerPage.enterAndSubmitPassengerContact(person);
        contactPassengerPage.clickSubmit();
        seatAndLuggagePage.addSeatToTicket();


    }

    public double takeTotalPriceForPassengerWithSeats() {
        SeatAndLuggagePage seatAndLuggagePage = new SeatAndLuggagePage(driver);
        return seatAndLuggagePage.getTotalPriceForSeats();
    }

    public double takePriceForPassengerWithSeatsFromTable() {
        SeatAndLuggagePage seatAndLuggagePage = new SeatAndLuggagePage(driver);
        return seatAndLuggagePage.getPriceForSeatsFromTable();
    }

    public double takePriceForPassengerWithLuggage() {
        SeatAndLuggagePage seatAndLuggagePage = new SeatAndLuggagePage(driver);
        return seatAndLuggagePage.getPriceForLuggage();
    }

    public double takePriceForPassengerWithLuggageFromTable() {
        SeatAndLuggagePage seatAndLuggagePage = new SeatAndLuggagePage(driver);
        return seatAndLuggagePage.getPriceForLuggageFromTable();
    }
    
        public void startWorkWithFlightsStatusPageWithDestinations (String from, String to,  String date)
    {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        flightsStatusPage.openPage();
        flightsStatusPage.flightsStatusForDestinations(from, to, date);
    }

    public boolean severalFlightsTableDisplayed()
    {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        return flightsStatusPage.severalFlightsTableDisplayed();
    }

    public boolean correctMultipleFlightsInfoDisplayed(String from, String to, String date)
    {
        FlightsStatusPage flightsStatusPage = new FlightsStatusPage(driver);
        return flightsStatusPage.correctMultipleFlightsInfoDisplayed(from,to,date);
    }

}
