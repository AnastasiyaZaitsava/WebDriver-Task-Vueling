package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class VuelingMainPage extends VuelingAbstractPage {

    private final String BASE_URL = "http://www.vueling.com/en";
    private HashMap<String, String> arrayPhone = new HashMap<String, String>();

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

    @FindBy(name = "AvailabilitySearchInputXmlSearchView$TextBoxMarketOrigin1")
    private WebElement fieldFromFlight;

    @FindBy(name = "AvailabilitySearchInputXmlSearchView$TextBoxMarketDestination1")
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

    @FindBy(css = "#stationsList > ul > li > a")
    private WebElement clickOnNeedCity;

    @FindBy(id = "datePickerTitleCloseButton")
    private WebElement buttonCloseSchedule;

    @FindBy(id = "footerPhoneInfoNumber")
    private WebElement phoneNumberBilletes;


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

    public boolean toChangeContactInfo(String country) {
        driver.findElement(By.id("centralBilletes"));
        List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='centralBilletes']/option"));
        for (WebElement webElement : elements) {

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

    public void startSearch(String from, String to) {
        //buttonReturn.click();
        
        fieldFromFlight.sendKeys(from);
        int attempts = 0;
        while (attempts < 5) {
            try {
                clickOnNeedCity.click();
                break;
            } catch (StaleElementReferenceException e) {
               
            }
            attempts++;
        }


        fieldToFlight.sendKeys(to);
        int attempts = 0;
        while (attempts < 5) {
            try {
                clickOnNeedCity.click();
                break;
            } catch (StaleElementReferenceException e) {
                
            }
            attempts++;
        }
        
        List<WebElement> list = driver.findElements(By.xpath("//tbody//*[contains(@class,'ui-state-promo')]"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        list.get(0).click();
        List<WebElement> list1 = driver.findElements(By.xpath("//tbody//*[contains(@class,'ui-state-promo')]"));
        list1.get(0).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", searchForFlights);
    }

    public VuelingMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }
}


