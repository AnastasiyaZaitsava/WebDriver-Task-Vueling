package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class SeatAndLuggagePage extends AbstractPage {

    private final String BASE_URL = "http://www.vueling.com/en";

    @FindBy(xpath = "//*[@id=\"CONTROLGROUPSERVICES_BaggageViewServicesView\"]/div/div/div[2]/div/div/div")
    private WebElement addLuggageButton;


    @FindBy(id = "CONTROLGROUPSERVICES_BaggageViewServicesView_SSRBaggageInputViewServicesView_SSRCode_PASSENGERCOUNT_0_SSRCOUNT_0")
    private WebElement dropDownLuggageList;

    @FindBy(xpath = "//*[@id=\"CONTROLGROUPSERVICES_BaggageViewServicesView\"]/div/div/div[3]/div[5]")
    private WebElement luggageInsuranceBlock;

    @FindBy(xpath = "//*[@id=\"paySeats\"]/div[5]/div/a")
    private WebElement chooseSeat;

    @FindBy(xpath = ".//*[@id='seat_0_2A']")
    private WebElement chooseConcreteSeatTo;

    @FindBy(xpath = ".//*[@id='seat_0_3B']")
    private WebElement chooseConcreteSeatBack;

    @FindBy(xpath = "//*[@id=\"SBSidebarView_totalPriceSpan\"]")
    private WebElement totalPrice;

    @FindBy(xpath = ".//*[@id='SBSidebarServicesBlock']/dl[1]/dd")
    private WebElement totalPriceForSeats;

    @FindBy(xpath = ".//*[@id='PREFPriceDiv']")
    private WebElement priceForSeatsFromTable;

    @FindBy(xpath = ".//*[@id='CONTROLGROUPSERVICES_BaggageViewServicesView_SSRBaggageInputViewServicesView_SSRCode_PASSENGERCOUNT_0_SSRCOUNT_0']/option[2]")
    private WebElement priceForLuggageFromTable;

    @FindBy(xpath = ".//*[@id='SBSidebarServicesBlock']/dl/dd")
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
        jse.executeScript("arguments[0].click();", chooseConcreteSeatTo);

        List<WebElement> elements = driver.findElements(By.xpath(".//*[@id='seat_0_3B']"));
        elements.get(1).click();

    }


    public boolean isLuggageInsurance() {
        return luggageInsuranceBlock.isDisplayed();
    }

    public double getTotalPriceForSeats() {
        String price1 = totalPriceForSeats.getText();
        String price = price1.substring(0, (price1.length() - 4));
        return Double.parseDouble(price);
    }

    public double getPriceForSeatsFromTable() {
        String price1 = priceForSeatsFromTable.getText();
        String price = price1.substring(4, (price1.length() - 4));
        return Double.parseDouble(price);
    }

    public double getPriceForLuggage() {
        String price1 = priceForLuggage.getText();
        String price = price1.substring(0, (price1.length() - 4));
        return Double.parseDouble(price);
    }


    public double getPriceForLuggageFromTable() {
        String price1 = priceForLuggageFromTable.getText();
        String price = price1.substring(12, (price1.length() - 4));
        return Double.parseDouble(price);
    }


}

