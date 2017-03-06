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

    private final String BASE_URL = "http://www.vueling.com/en";
    private HashMap<String, String> arrayPhone = new HashMap <String, String> ();

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

    @FindBy (id = "footerPhoneInfoNumber")
    private WebElement phoneNumberBilletes;

    @FindBy (xpath = "//tbody/*//*[@data-handler='selectDay']")
    private List <WebElement> selectDays;

    @FindBy (xpath = ".//*[@id='centralBilletes']/option")
    private List <WebElement> selectServiceCenter;


    public void login ( String login, String psw)
    {
        buttonForLogin.click();
        fieldUserNameForLogin.clear();
        fieldUserNameForLogin.sendKeys(login);
        fieldPasswordForLogin.clear();
        fieldPasswordForLogin.sendKeys(psw);
        buttonLogin.click();
    }

    public String checkIsLogin ()
    {
        return fieldForCheckIsLogin.getText();
    }

    public boolean toChangeContactInfo (String country) {

        driver.findElement(By.id("centralBilletes"));
        for (WebElement webElement: selectServiceCenter) {

            if (webElement.getText().equals(country)) {
                webElement.click();
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            }

            String city = webElement.getText();
            webElement.click();

            String phone = driver.findElement(By.id("footerPhoneNumberBilletes")).getText();
            arrayPhone.put(city, phone);

            for (Map.Entry<String, String> pair : arrayPhone.entrySet()) {
                if (pair.getKey().equals(country)) {
                    String phn = pair.getValue();

                    if (phone.equals(phn)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void startSearch (String from, String to) {

        WebDriverWait wait = new WebDriverWait (driver, 50);

        fieldFromFlight.sendKeys(from);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stationsList\"]/ul/li/a/strong")));
        clickOnNeedCity.click();

        fieldToFlight.sendKeys(to);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"stationsList\"]/ul/li/a/strong")));
        clickOnNeedCity.click();

        selectDays.get(1).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        selectDays.get(1).click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", searchForFlights);
    }



    public MainPage(WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }
}


