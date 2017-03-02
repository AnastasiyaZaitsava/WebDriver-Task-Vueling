package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anastasiya_Mashkevic on 3/2/2017.
 */
public class VuelingFlightsStatusPage extends VuelingAbstractPage {

    private final String BASE_URL = "http://www.vueling.com/en/vueling-services/flight-information/flights-status";

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketOrigin1']")
    private WebElement fieldFromFlight;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketDestination1']")
    private WebElement fieldToFlight;

    @FindBy(id = "datepicker")
    private WebElement dateOfFlights;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_LinkButtonNewSearch")
    private WebElement buttonSearch;





    public VuelingFlightsStatusPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

}
