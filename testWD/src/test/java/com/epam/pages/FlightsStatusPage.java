package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FlightsStatusPage extends AbstractPage {

    private final String BASE_URL = "http://www.vueling.com/en/vueling-services/flight-information/flights-status";

    @FindBy(xpath = "//*[@id='radiosBuscador']//label[contains(text(), 'Flight number')]")
    private WebElement buttonFlightNumber;

    @FindBy(xpath = "//*[@id='radiosBuscador']//label[contains(text(), 'Origin / Destination')]")
    private WebElement buttonDestination ;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketOrigin1']")
    private WebElement fieldFromFlight;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketDestination1']")
    private WebElement fieldToFlight;

    @FindBy(id = "datepicker")
    private WebElement dateOfFlights;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_LinkButtonNewSearch")
    private WebElement buttonSearch;

    @FindBy(xpath = "//*[@id='stationsList']/ul/li/a[@class = 'optionActive']")
    private WebElement clickOnNeedCity;


    public void flightsStatus (String from, String to, String date) {
        WebDriverWait wait = new WebDriverWait (driver, 50);
        buttonDestination.click();

        fieldFromFlight.sendKeys(from);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stationsList\"]/ul/li/a/strong")));
        clickOnNeedCity.click();

        fieldFromFlight.sendKeys(to);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stationsList\"]/ul/li/a/strong")));
        clickOnNeedCity.click();
    }


    public FlightsStatusPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

}
