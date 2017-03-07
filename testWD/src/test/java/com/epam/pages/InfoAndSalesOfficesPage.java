package com.epam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Volha_Hitskaya on 3/7/2017.
 */
public class InfoAndSalesOfficesPage extends AbstractPage {
    private final String BASE_URL = "http://www.vueling.com/en";


    @FindBy(xpath = "//*[@id='NavMain']/ul/li/div/a[@class='NavMain_itemHover']/span[text()='Vueling services']")
    private WebElement servicesButton;

    @FindBy(xpath = "//*[@id='NavMain']//a[contains(text(),'Information and sales offices')]")
    private WebElement InfoAndSalesOfficesSpan;

    @FindBy(xpath = "//*[@id='main']//div[contains(@class, 'column_3 sectionTable_cell')]/ul/li/ul/li/a")
    private List<WebElement> cityList;

    @FindBy(xpath = "//*[@id='sAirport']/option[@selected='selected']")
    private WebElement chosenCity;

    @FindBy(xpath = "//*[@id='BuscarAeropuertoResultsContainer']/div/div/div/h1")
    private WebElement airportSectionHeading;


    public InfoAndSalesOfficesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void getToInfoAndSalesOfficesPage()
    {
        servicesButton.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        InfoAndSalesOfficesSpan.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public boolean chooseCity(String city) {
        for (WebElement webElement : cityList) {

            if (webElement.getText().equals(city)) {
                webElement.click();
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                return true;
            }
        }
        return false;
    }

    public boolean isAirportDisplayed()
    {

        String cityStr = chosenCity.getText();
        String[]city = cityStr.split(" ");

        String airport = airportSectionHeading.getText();

        if(airport.contains("Airport") && airport.contains(city[0]))
        {
            return true;
        }

        return false;
    }


}
