package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Anastasiya_Mashkevic on 3/1/2017.
 */
public class VuelingContactPassengerPage extends VuelingAbstractPage  {
    private final String BASE_URL = "https://tickets.vueling.com/Contact.aspx";

//    private ArrayList<String> arrayCountry = new ArrayList<String>();
//    private ArrayList<String> arrayPhonePrefixAndCountry = new ArrayList<String>();
//
//    public  ArrayList<String> getArrayCountry () {
//        return arrayCountry;
//    }
//    public  ArrayList<String> getArrayPhonePrefixAndCountry () {
//        return arrayPhonePrefixAndCountry;
//    }

    @FindBy(id = "ControlGroupMainContact_PassengerInputViewContactView_DropDownListTitle_0MR")
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

    @FindBy(id = "ControlGroupMainContact_LinkButtonSubmit")
    private WebElement contactLinkButtonSubmit;

    public VuelingContactPassengerPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public boolean isPageOpened()
    {
        return driver.getCurrentUrl().contains(BASE_URL);
    }

    public void enterAndSubmitPassengerContact(String name, String surname, String city, String phone, String email, String countryCode, String countryPhonePrefix)
    {
        Select dropDownListCountrySelect = new Select(dropDownListCountryWE);
        Select dropDownListPhonePrefixSelect = new Select(dropDownListPhonePrefixWE);

        mrButton.click();
        textBoxName.clear();
        textBoxName.sendKeys(name);
        textBoxSurname.clear();
        textBoxSurname.sendKeys(surname);
        dropDownListCountrySelect.selectByValue(countryCode);
        textBoxCity.clear();
        textBoxCity.sendKeys(city);
        dropDownListPhonePrefixSelect.selectByValue(countryPhonePrefix);
        textBoxPhone.clear();
        textBoxPhone.sendKeys(phone);
        textBoxEmailAddress.clear();
        textBoxEmailAddress.sendKeys(email);
        contactLinkButtonSubmit.click();
    }

//    public ArrayList<String> arrayCountry () {
//        dropDownListCountry.click();
//        List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='ControlGroupMainContact_ControlGroupContactControls_ContactInputView_DropDownListCountry']/option"));
//        for (int i = 0; i < elements.size(); i++) {
//            String country = elements.get(i).getText();
//            elements.get(i).click();
//            arrayCountry.add(country);
//        }
//        return getArrayCountry();
//    }
//
//    public ArrayList<String> arrayPhonePrefixAndCountry () {
//        dropDownListPhonePrefix.click();
//        List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='ControlGroupMainContact_ControlGroupContactControls_ContactInputView_DropDownListPrefix']/option"));
//        for (int i = 0; i < elements.size(); i++) {
//            String phonePrefixAndCountry = elements.get(i).getText();
//            elements.get(i).click();
//            arrayPhonePrefixAndCountry.add(phonePrefixAndCountry);
//        }
//        return getArrayPhonePrefixAndCountry();
//    }


}
