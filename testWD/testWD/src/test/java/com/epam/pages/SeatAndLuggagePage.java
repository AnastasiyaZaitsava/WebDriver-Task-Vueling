package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class SeatAndLuggagePage extends AbstractPage {

    private final static String BASE_URL = "http://www.vueling.com/en";
    private final static String PARAM_FOR_JAVASCRIPT = "arguments[0].click();";

    @FindBy(xpath = "//div[contains(@class, 'module_baggage_passengers')]//a[@data-object='btnBaggageAdd']")
    private WebElement addLuggageButton;


    @FindBy(id = "CONTROLGROUPSERVICES_BaggageViewServicesView_SSRBaggageInputViewServicesView_SSRCode_PASSENGERCOUNT_0_SSRCOUNT_0")
    private WebElement dropDownLuggageList;

    @FindBy(xpath = "//*[@id=\"CONTROLGROUPSERVICES_BaggageViewServicesView\"]//div[contains(@class, 'module_baggage_passengers_rowInsurance')]/div[@class='sectionTable']")
    private WebElement luggageInsuranceBlock;

    @FindBy(xpath = ".//*[@id='paySeats']//a[contains(@class, 'boton_vp bt_yellow')]")
    private WebElement chooseSeat;

    @FindBy(id = "seat_0_2A")
    private WebElement chooseConcreteSeatTo;

    @FindBy(id = "seat_0_3B")
    private WebElement chooseConcreteSeatBack;

    @FindBy(xpath = "//*[@id=\"SBSidebarView_totalPriceSpan\"]")
    private WebElement totalPrice;

    @FindBy(xpath = ".//*[@id='SBSidebarServicesBlock']/dl[contains(@class,'serviceRowseats')]/dd")
    private WebElement totalPriceForSeats;

    @FindBy(xpath = ".//*[@id='PREFPriceDiv']")
    private WebElement priceForSeatsFromTable;

    @FindBy(xpath = ".//*[@id='CONTROLGROUPSERVICES_BaggageViewServicesView_SSRBaggageInputViewServicesView_SSRCode_PASSENGERCOUNT_0_SSRCOUNT_0']/option[@value = 'BAG1']")
    private WebElement priceForLuggageFromTable;

    @FindBy(xpath = ".//*[@id='SBSidebarServicesBlock']/dl[contains(@class,'serviceRowbags')]/dd")
    private WebElement priceForLuggage;


    public SeatAndLuggagePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void addLuggageToTicket() {
        Select dropDownListLuggageSelect = new Select(dropDownLuggageList);
        addLuggageButton.click();
        dropDownListLuggageSelect.selectByValue("BAG1");
    }

    public void addSeatToTicket() throws InterruptedException {
        chooseSeat.click();
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript(PARAM_FOR_JAVASCRIPT, chooseConcreteSeatTo);

        //Thread.sleep(1000);
        List<WebElement> elements = driver.findElements(By.id("seat_0_3B"));
        elements.get(1).click();

    }

    public double getTotalPriceForSeats() {
        String priceStr = totalPriceForSeats.getText();
        String price = priceStr.substring(0, (priceStr.length() - 4));
        return Double.parseDouble(price);
    }

    public double getPriceForSeatsFromTable() {
        String priceStr = priceForSeatsFromTable.getText();
        String price = priceStr.substring(4, (priceStr.length() - 4));
        return Double.parseDouble(price);
    }

    public double getPriceForLuggage() {
        String priceStr = priceForLuggage.getText();
        String price = priceStr.substring(0, (priceStr.length() - 4));
        return Double.parseDouble(price);
    }


    public double getPriceForLuggageFromTable() {
        String priceStr = priceForLuggageFromTable.getText();
        String price = priceStr.substring(12, (priceStr.length() - 4));
        return Double.parseDouble(price);
    }

}