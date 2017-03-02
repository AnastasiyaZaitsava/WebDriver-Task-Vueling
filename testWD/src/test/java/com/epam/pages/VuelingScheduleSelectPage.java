package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anastasiya_Mashkevic on 3/1/2017.
 */
public class VuelingScheduleSelectPage extends VuelingAbstractPage {
    private final String BASE_URL = "http://www.vueling.com/en"; //изменить на нужный

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
        driver.navigate().to(BASE_URL);
    }
    
    public void checkTravelInfo(){
    	basicOutboundButton.click();
    	basicReturnButton.click();
    	WebElement element = driver.findElements(By.xpath("//div[@class='travelInfo_list']"));
    	//if(element.) я не знаю, какая там функция для того чтобы проверить наличие на странице
    }

    

}
