package com.epam.pages;

import com.epam.util.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;


public class VuelingContactPassengerPage extends VuelingAbstractPage {

    private final String BASE_URL = "http://www.vueling.com/en";


    @FindBy(xpath = ".//*[@id='ControlGroupMainContact_PassengerInputViewContactView_DropDownListTitle_0Div']//fieldset[contains(@class,'validacion')]/label[contains(text(),'*Mr.')]")
    private WebElement mrButton;

    @FindBy(id = "ControlGroupMainContact_PassengerInputViewContactView_DropDownListTitle_0MRS")
    private WebElement mrsButton;

    @FindBy(id = "ControlGroupMainContact_PassengerInputViewContactView_TextBoxFirstName_0")
    private WebElement textBoxName;

    @FindBy(id = "ControlGroupMainContact_PassengerInputViewContactView_TextBoxLastName_0")
    private WebElement textBoxSurname;

    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_DropDownListCountry")
    private WebElement dropDownListCountryWE;


    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_TextBoxCity")
    private WebElement textBoxCity;

    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_DropDownListPrefix")
    private WebElement dropDownListPhonePrefixWE;

    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_TextBoxHomePhone")
    private WebElement textBoxPhone;

    @FindBy(id = "ControlGroupMainContact_ControlGroupContactControls_ContactInputView_TextBoxEmailAddress")
    private WebElement textBoxEmailAddress;

    @FindBy(xpath = "*//a[@id='ControlGroupMainContact_LinkButtonSubmit']/span")
    private WebElement contactLinkButtonSubmit;

    @FindBy(xpath = "//*[@id='ControlGroupMainContact_PassengerInputViewContactView_DropDownListTitle_0Div']/div/div[contains(@class, 'check--OK')]")
    private WebElement checkOkGender;

    public VuelingContactPassengerPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean isPageOpened() {
        return driver.getCurrentUrl().contains(BASE_URL);
    }

    public void enterAndSubmitPassengerContact(User user) {
        Select dropDownListCountrySelect = new Select(dropDownListCountryWE);
        Select dropDownListPhonePrefixSelect = new Select(dropDownListPhonePrefixWE);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        mrButton.click();
        textBoxName.clear();
        textBoxName.sendKeys(user.getName());
        textBoxSurname.clear();
        textBoxSurname.sendKeys(user.getSurname());
        dropDownListCountrySelect.selectByValue(user.getCountryCode());
        textBoxCity.clear();
        textBoxCity.sendKeys(user.getCity());
        dropDownListPhonePrefixSelect.selectByValue(user.getCountryPhonePrefix());
        textBoxPhone.clear();
        textBoxPhone.sendKeys(user.getPhone());
        textBoxEmailAddress.clear();
        textBoxEmailAddress.sendKeys(user.getEmail());


        contactLinkButtonSubmit.click();

    }

    public boolean clickSubmit() {
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        if (contactLinkButtonSubmit.isDisplayed()) {
            contactLinkButtonSubmit.click();
            return true;
        } else {
            return false;
        }
    }

}
