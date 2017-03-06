package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;


public class VuelingScheduleSelectPage extends VuelingAbstractPage {
    private final String BASE_URL = "http://www.vueling.com/en";


    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt1Fare1Label")
    private WebElement basicOutboundButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt1Fare2Label")
    private WebElement optimaOutboundButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt1Fare3Label")
    private WebElement excellenceOutboundButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt2Fare1Label")
    private WebElement basicReturnButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt2Fare2Label")
    private WebElement optimaReturnButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_RadioButtonMkt2Fare3Label")
    private WebElement excellenceReturnButton;

    @FindBy(id = "ControlGroupScheduleSelectView_AvailabilityInputScheduleSelectView_LinkButtonSubmit")
    private WebElement continueButton;


    public VuelingScheduleSelectPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public boolean chooseFlight() {
        basicOutboundButton.click();
        basicReturnButton.click();

        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        WebElement element = driver.findElement(By.xpath("//div[@class='travelInfo_list']"));
        if (element.isDisplayed()) {
            continueButton.click();
            return true;
        } else {
            return false;
        }
    }

    public double getPrice(WebElement element){
        String labelID = element.getAttribute("id");
        String tag = element.getTagName();
        String price1 = driver.findElement(By.xpath("//"+ tag +"[@id='"+labelID+"']/span")).getText();
        price1.replace('.', ',');
        String price2 = driver.findElement(By.xpath("//label[@id='"+labelID+"']/span/sup")).getText();
        double price = Double.parseDouble(price1 + price2);
        return price;
    }
}





