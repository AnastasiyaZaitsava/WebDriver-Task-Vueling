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

<<<<<<< HEAD
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
=======
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
    
    

   
    public VuelingScheduleSelectPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    
    public void openPage()
    {
>>>>>>> origin/master
        driver.navigate().to(BASE_URL);
    }
    
    public void checkTravelInfo(){
    	basicOutboundButton.click();
    	basicReturnButton.click();
    	WebElement element = driver.findElements(By.xpath("//div[@class='travelInfo_list']"));
    	//if(element.) я не знаю, какая там функция для того чтобы проверить наличие на странице
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
}



