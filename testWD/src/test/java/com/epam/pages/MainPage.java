package com.epam.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private final String BASE_URL = "http://www.vueling.com/en";

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

    @FindBy(xpath = "//span[text() = 'Multiple destinations']")
    private WebElement buttonMultipleDestinations;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketOrigin1']")
    private WebElement fieldFromFlight;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketDestination1']")
    private WebElement fieldToFlight;

    @FindBy(xpath = "//div[contains(@class, 'ui-helper-clearfix ui-corner-left')]//span[contains(@class, 'ui-datepicker-month')]")
    private WebElement monthInCalender;

    @FindBy(id = "marketDate1_lab")
    private WebElement dateOne;

    @FindBy(id = "marketDate2_lab")
    private WebElement dateTwo;

    @FindBy(id = "DropDownListPassengerType_ADT_1")
    private WebElement onePasenger;

    @FindBy(id = "DropDownListPassengerType_ADT_2")
    private WebElement twoPasengers;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_btnClickToSearchNormal")
    private WebElement searchForFlights;

    @FindBy(id = "DropDownListPassengerType_ADT_3")
    private WebElement treePasengers;

    @FindBy(id = "centralBilletes")
    private WebElement chooseTicketSales;

    @FindBy(xpath = "//*[@id='stationsList']/ul/li/a[@class = 'optionActive']")
    private WebElement clickOnNeedCity;

    @FindBy(id = "datePickerTitleCloseButton")
    private WebElement buttonCloseSchedule;

    @FindBy(id = "footerPhoneInfoNumber")
    private WebElement phoneNumberBilletes;

//    @FindBy(xpath = "//tbody/*//*[@data-handler='selectDay']")
//    private List<WebElement> selectDays;

    @FindBy(xpath = ".//*[@id='centralBilletes']/option")
    private List<WebElement> selectServiceCenter;

    public void login(String login, String psw) {
        buttonForLogin.click();
        fieldUserNameForLogin.clear();
        fieldUserNameForLogin.sendKeys(login);
        fieldPasswordForLogin.clear();
        fieldPasswordForLogin.sendKeys(psw);
        buttonLogin.click();
    }

    public String checkIsLogin() {
        return fieldForCheckIsLogin.getText();
    }

    public String toChangeContactInfo(String country) {
        driver.findElement(By.id("centralBilletes"));
        for (WebElement webElement : selectServiceCenter) {
            if (webElement.getText().equals(country)) {
                webElement.click();
                return driver.findElement(By.id("footerPhoneNumberBilletes")).getText();
            }
        }
        return null;
    }

    public boolean isPhoneCorrespondsToTheCountry(String phone, String country) {
        HashMap<String, String> arrayPhone = new HashMap<String, String>();
        for (WebElement webElement : selectServiceCenter) {
            String city = webElement.getText();
            webElement.click();
            String phoneNumber = driver.findElement(By.id("footerPhoneNumberBilletes")).getText();
            arrayPhone.put(city, phoneNumber);

            for (Map.Entry<String, String> pair : arrayPhone.entrySet()) {
                if (pair.getKey().equals(country) && pair.getValue().equals(phone)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void chooseFlightOneWay() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", buttonOneWayOnly);
    }

    public void chooseFlightReturn() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", buttonReturn);
    }

    public void chooseTwoPassenger() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", twoPasengers);
        //twoPasengers.click();
    }

    public void clickButtonSearchFlight() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", searchForFlights);
    }

    public void chooseCityForLight(String cityOfDeparture, String cityOfArrival) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        fieldFromFlight.sendKeys(cityOfDeparture);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stationsList\"]/ul/li/a/strong")));
        clickOnNeedCity.click();

        fieldToFlight.sendKeys(cityOfArrival);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stationsList\"]/ul/li/a/strong")));
        clickOnNeedCity.click();
    }

    public void chooseDateFlight(String dateOfFlight) {
        WebDriverWait wait = new WebDriverWait(driver, 50);
        String[] splitDate = dateOfFlight.split("/");
        while (!monthInCalender.getText().equals(splitDate[1])) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='datePickerContainer']//a[@data-handler = 'next']")));
            driver.findElement(By.xpath(".//*[@id='datePickerContainer']//a[@data-handler = 'next']")).click();

        }
        driver.findElement(By.xpath("//*[@id='datePickerContainer']//div[contains(@class, 'ui-datepicker-group-first')]//a[text() ='" + splitDate[0] + "']")).click();
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

}






