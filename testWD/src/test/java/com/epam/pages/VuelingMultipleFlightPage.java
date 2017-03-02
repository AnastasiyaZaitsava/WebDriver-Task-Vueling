package com.epam.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Anastasiya_Mashkevic on 3/1/2017.
 */
public class VuelingMultipleFlightPage extends VuelingAbstractPage {
    private final String BASE_URL = "http://www.vueling.com/en"; //изменить на нужный



    public VuelingMultipleFlightPage (WebDriver driver)
    {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }


    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

}