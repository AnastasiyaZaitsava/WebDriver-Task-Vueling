package com.epam.pages;

import org.openqa.selenium.WebDriver;


public abstract class VuelingAbstractPage
{

    protected WebDriver driver;

    public abstract void openPage();

    public VuelingAbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

}
