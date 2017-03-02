package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Anastasiya_Mashkevic on 3/1/2017.
 */
public class VuelingMainPage extends VuelingAbstractPage {

    private final String BASE_URL = "http://www.vueling.com/en";

    private HashMap<String, String> arrayPhone = new  HashMap <String, String> ();

    public HashMap<String, String>  getArrayPhone () {
        return arrayPhone;
    }

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

    @FindBy(xpath = "//span[text() = 'One way only']")
    private WebElement buttonOneWayOnly;

    @FindBy(xpath = "//span[text() = 'Multiple destinations']")
    private WebElement buttonMultipleDestinations;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketOrigin1']")
    private WebElement fieldFromFlight;

    @FindBy(xpath = "//input[@id='AvailabilitySearchInputXmlSearchView_TextBoxMarketDestination1']")
    private WebElement fieldToFlight;

    @FindBy(id = "marketDate1_lab")
    private WebElement dateOne;

    @FindBy(id = "marketDate2_lab")
    private WebElement dateTwo;

    @FindBy(id = "DropDownListPassengerType_ADT_1")
    private WebElement onePasenger;

    @FindBy(id = "DropDownListPassengerType_ADT_2")
    private WebElement twoPasengers;

    @FindBy(id = "DropDownListPassengerType_ADT_3")
    private WebElement searchForFlights;

    @FindBy(id = "AvailabilitySearchInputXmlSearchView_btnClickToSearchNormal")
    private WebElement treePasengers;

    @FindBy(id = "centralBilletes")
    private WebElement chooseTicketSales;

    @FindBy(xpath = ".//*[@id='stationsList']/ul/li/a")
    private WebElement clickOnNeedCity;

    @FindBy(id = "datePickerTitleCloseButton")
    private WebElement buttonCloseSchedule;


//    @FindBy(id = "centralReservas")
//    private WebElement chooseCustomerServices;


    public void login ( String login, String psw) {
        buttonForLogin.click();
        fieldUserNameForLogin.clear();
        fieldUserNameForLogin.sendKeys(login);
        fieldPasswordForLogin.clear();
        fieldPasswordForLogin.sendKeys(psw);
        buttonLogin.click();

    }

    public String checkIsLogin () {
        return fieldForCheckIsLogin.getText();
    }

    public HashMap <String, String> arrayCountryWithPhoneService () {
        chooseTicketSales.click();
        List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='centralBilletes']/option"));
        for (int i = 0; i < elements.size(); i++) {
            String country = elements.get(i).getText();
            elements.get(i).click();
            String phone = driver.findElement(By.id("footerPhoneNumberBilletes")).getText();
            arrayPhone.put(country, phone);
        }
        return getArrayPhone();
    }

    public void startSearch (String from, String to, int dataFrom, int dataTo, int numberOfPassenger) {
        buttonReturn.click();
        fieldFromFlight.click();
        fieldFromFlight.sendKeys(from);
        clickOnNeedCity.click();
        fieldToFlight.click();
        fieldToFlight.sendKeys(to);
        clickOnNeedCity.click();
        //добавить выбор дат

    }





    public VuelingMainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

}


