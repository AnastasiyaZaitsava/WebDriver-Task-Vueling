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
    private WebElement buttonDestination;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketOrigin1']")
    private WebElement fieldFromFlight;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketDestination1']")
    private WebElement fieldToFlight;

    @FindBy(id = "datepicker")
    private WebElement fieldDateOfFlights;

    @FindBy(id = "flightNumber")
    private WebElement fieldFlightNumber;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_LinkButtonNewSearch")
    private WebElement buttonSearch;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_LinkButtonNewSearch")
    private WebElement buttonNextMonth;

    @FindBy(xpath = "//div[contains(@class, 'ui-helper-clearfix ui-corner-left')]//span[contains(@class, 'ui-datepicker-month')]")
    private WebElement monthInCalender;

    @FindBy(xpath = "//*[@id='stationsList']/ul/li/a[@class = 'optionActive']")
    private WebElement clickOnNeedCity;

    @FindBy(xpath = "//*[@id='main']//div[contains(@class, 'status_tag')]")
    private WebElement flightStatus;

    @FindBy(xpath = "//*[@id='main']//h3[contains(@class, 'header')]/span[contains(@class, 'floatLeft')]")
    private WebElement dateOnCheckFlightBox;

    @FindBy(xpath = "//div[contains(@class, 'colHalf_1')]/span")
    private WebElement checkCityDeparture;

    @FindBy(xpath = "//div[contains(@class, 'colHalf_2')]/span")
    private WebElement checkCityArrival;

    @FindBy(xpath = "//*[@id='main']//h3[contains(@class, 'header')]/span[contains(@class, 'floatRight')]")
    private WebElement checkNumberFlight;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_LinkButtonNewSearch")
    private WebElement searchForFlights;

    private final String severalFlightTablePath = "//div[@class = 'wrap-table-estado-vuelos fullWidth']";
    @FindBy(xpath = severalFlightTablePath)
    private WebElement severalFlightsTable;

    @FindBy(xpath = "//div[@class = 'wrap-table-estado-vuelos fullWidth']/table/tbody/tr")
    private List<WebElement> flightRawsList;

    @FindBy(xpath = "//div[@class = 'wrap-table-estado-vuelos fullWidth']/div[@class='intxt']")
    private WebElement flightsTableHeading;



    public void flightsStatusForDestinations(String from, String to, String date)
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        fieldFromFlight.sendKeys(from);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stationsList\"]/ul/li/a/strong")));
        clickOnNeedCity.click();

        fieldToFlight.sendKeys(to);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stationsList\"]/ul/li/a/strong")));
        clickOnNeedCity.click();

        fieldDateOfFlights.click();
        String[] splitDate = date.split("/");
        while (!monthInCalender.getText().equals(splitDate[1]))
        {
            driver.findElement(By.xpath(".//*[@id='datePickerContainer']//a[@data-handler = 'next']")).click();
        }
        driver.findElement(By.xpath("//*[@id='datePickerContainer']//div[contains(@class, 'ui-datepicker-group-first')]//a[text() ='" + splitDate[0] + "']")).click();
        clickButtonSearchFlight();
    }

    public void flightsStatusForFlightNumber(String flightNumber, String dateOfFlight)
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        buttonFlightNumber.click();
        fieldFlightNumber.sendKeys(flightNumber);
        fieldDateOfFlights.click();
        String[] splitDate = dateOfFlight.split("/");
        while (!monthInCalender.getText().equals(splitDate[1]))
        {
            driver.findElement(By.xpath(".//*[@id='datePickerContainer']//a[@data-handler = 'next']")).click();
        }
        driver.findElement(By.xpath("//*[@id='datePickerContainer']//div[contains(@class, 'ui-datepicker-group-first')]//a[text() ='" + splitDate[0] + "']")).click();
        driver.findElement(By.id("searchForFlightButton")).click();
    }

    private void clickButtonSearchFlight() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", searchForFlights);
    }

    public boolean severalFlightsTableDisplayed()
    {
        return driver.findElements( By.xpath(severalFlightTablePath) ).size() != 0;
    }

    public boolean correctMultipleFlightsInfoDisplayed(String from, String to, String date)
    {
        if(severalFlightsTableDisplayed())
        {
            for(WebElement element: flightRawsList)
            {
                if(!element.findElement(By.xpath("td[2]")).getText().equals(from))
                {
                    return false;
                }
                if(!element.findElement(By.xpath("td[3]")).getText().equals(to))
                {
                    return false;
                }
            }

            if(!(flightsTableHeading.getText().contains(date)
                    && flightsTableHeading.getText().contains(from)
                    && flightsTableHeading.getText().contains(to)))
            {
                return false;
            }
            return true;
        }
        return false;
    }

    public String takeStatusFlight()
    {
        return flightStatus.getText();
    }

    public String takeFlightDate()
    {
        return dateOnCheckFlightBox.getText();
    }

    public String takeCityDeparture()
    {
        return checkCityDeparture.getText();
    }

    public String takeCityArrival()
    {
        return checkCityArrival.getText();
    }

    public FlightsStatusPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

}
