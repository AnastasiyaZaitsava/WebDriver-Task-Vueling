package com.epam.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private final static String BASE_URL = "http://www.vueling.com/en";
    private final static String PATHTOSTATIONLIST = "//*[@id=\"stationsList\"]/ul/li/a/strong";
    private final static String  PATHTOBUTTONNEXTINCALENDER = ".//*[@id='datePickerContainer']//a[@data-handler = 'next']";
    private final static String PARAMFORJAVASCRIPT = "arguments[0].click();";

    @FindBy(id = "openAccountButton")
    private WebElement buttonForLogin;

    @FindBy(id = "ControlGroupMainContact_MemberLoginAndRegisterAboveContactView_TextBoxRegisterEmail")
    private WebElement fieldUserNameForLogin;

    @FindBy(id = "ControlGroupMainContact_MemberLoginAndRegisterAboveContactView_TextBoxRegisterPassword")
    private WebElement fieldPasswordForLogin;

    @FindBy(xpath = "//span[@id='btnEnvContacto']/span[@class='bt_link']")
    private WebElement buttonLogin;

    @FindBy(xpath = "//*[contains(@class, 'header_navBarUser_account_nick')]")
    private WebElement fieldForCheckIsLogin;

    @FindBy(xpath = "//span[text() = 'Return']")
    private WebElement buttonReturn;

    @FindBy(xpath = ".//input[@id='AvailabilitySearchInputXmlSearchView_OneWay']")
    private WebElement buttonOneWayOnly;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketOrigin1']")
    private WebElement fieldFromFlight;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketDestination1']")
    private WebElement fieldToFlight;

    @FindBy(xpath = "//div[contains(@class, 'ui-helper-clearfix ui-corner-left')]//span[contains(@class, 'ui-datepicker-month')]")
    private WebElement monthInCalender;

    @FindBy(id = "DropDownListPassengerType_ADT_1")
    private WebElement onePasenger;

    @FindBy(id = "DropDownListPassengerType_ADT_2")
    private WebElement twoPasengers;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_btnClickToSearchNormal")
    private WebElement searchForFlights;

    @FindBy(xpath = "//*[@id='stationsList']/ul/li/a[@class = 'optionActive']")
    private WebElement clickOnNeedCity;

    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public MainPage login(String login, String psw)
    {
        buttonForLogin.click();
        fieldUserNameForLogin.clear();
        fieldUserNameForLogin.sendKeys(login);
        fieldPasswordForLogin.clear();
        fieldPasswordForLogin.sendKeys(psw);
        buttonLogin.click();
        return this;
    }

    public String checkIsLogin()
    {
        return fieldForCheckIsLogin.getText();
    }

    public void chooseFlightOneWay()
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(PARAMFORJAVASCRIPT, buttonOneWayOnly);
    }

    public void chooseFlightReturn()
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(PARAMFORJAVASCRIPT, buttonReturn);
    }

    public void chooseTwoPassenger ()
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(PARAMFORJAVASCRIPT, twoPasengers);
        //twoPasengers.click();
    }

    public void clickButtonSearchFlight()
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(PARAMFORJAVASCRIPT, searchForFlights);
    }

    public void chooseCityForLight(String cityOfDeparture, String cityOfArrival)
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        fieldFromFlight.sendKeys(cityOfDeparture);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PATHTOSTATIONLIST)));
        clickOnNeedCity.click();

        fieldToFlight.sendKeys(cityOfArrival);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PATHTOSTATIONLIST)));
        clickOnNeedCity.click();
    }

    public void chooseDateFlight(String dateOfFlight)
    {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        String[] splitDate = dateOfFlight.split("/");
        while (!monthInCalender.getText().equals(splitDate[1])) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PATHTOBUTTONNEXTINCALENDER)));
            driver.findElement(By.xpath(PATHTOBUTTONNEXTINCALENDER)).click();
        }
        driver.findElement(By.xpath("//*[@id='datePickerContainer']//div[contains(@class, 'ui-datepicker-group-first')]//a[text() ='" + splitDate[0] + "']")).click();
    }
}






