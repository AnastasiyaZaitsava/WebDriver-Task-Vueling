package com.epam.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class VuelingChooseSeatAndLuggagePage extends VuelingAbstractPage {

    private final String BASE_URL = "http://www.vueling.com/en";

    @FindBy(id = "sec1")
    private WebElement chooseSeat;

    @FindBy(id = "seat_0_4A")
    private WebElement chooseConcreteSeat;

    @FindBy(className = "wrap_btSmall_plus")
    private WebElement addLuggageButton;

    @FindBy(id = "CONTROLGROUPSERVICES_BaggageViewServicesView_SSRBaggageInputViewServicesView_SSRCode_PASSENGERCOUNT_0_SSRCOUNT_0")
    private WebElement dropDownLuggageList;

    @FindBy(className = "travelInfo_list travelInfo_list--services")
    private WebElement serviceBlock;


    public VuelingChooseSeatAndLuggagePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void chooseSeatAndAddLuggage() {
        Select dropDownListLuggageSelect = new Select(dropDownLuggageList);
        chooseSeat.click();
        chooseConcreteSeat.click();
        addLuggageButton.click();
        dropDownListLuggageSelect.selectByVisibleText("1 Suitcase");
    }

    public boolean isServiceBlockDisplayed() {
        return serviceBlock.isDisplayed();
    }

}

