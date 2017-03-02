package com.epam;

import com.epam.pages.VuelingContactPassengerPage;
import com.epam.steps.Steps;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Created by Anastasiya_Mashkevic on 3/1/2017.
 */
public class TestWD
    {
        private Steps steps;
        private final String USERNAME = "mashkevich.nastya@mail.ru";
        private final String PASSWORD = "wertyu1";

        @BeforeMethod(description = "Init browser")
        public void setUp()
        {
            steps = new Steps();
            steps.initBrowser();
        }

        @Test
        public void oneCanLoginGithub()
        {
            steps.loginToVueling(USERNAME, PASSWORD);
            Assert.assertTrue(steps.isLoginToVueling());
        }

        @Test
        public void fillPassengerInformation(){
            WebDriver driver = steps.getDriver();
            VuelingContactPassengerPage vcpp = new VuelingContactPassengerPage(driver);
            vcpp.openPage();
            Assert.assertTrue(vcpp.isPageOpened());
            vcpp.enterAndSubmitPassengerContact("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
        }

        @AfterMethod(description = "Stop Browser")
        public void stopBrowser()
        {
            steps.closeDriver();
        }

    }