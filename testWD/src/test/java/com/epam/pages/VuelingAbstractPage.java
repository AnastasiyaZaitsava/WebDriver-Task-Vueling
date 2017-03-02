package com.epam.pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Anastasiya_Mashkevic on 3/1/2017.
 */
public abstract class VuelingAbstractPage
{

    protected WebDriver driver;

    public abstract void openPage();

    public VuelingAbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

}
