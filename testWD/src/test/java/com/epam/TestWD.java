package com.epam;

import com.epam.util.User;
import com.epam.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestWD
    {
        private Steps steps;
        private final String USERNAME = "mashkevich.nastya@mail.ru";
        private final String PASSWORD = "wertyu1";
        private final String FROM = "Warsaw";
        private final String TO = "Barcelona";
        private final String USERCOUNTRY = "France";


        @BeforeTest(description = "Init browser")
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
        public void twoCanChooseServiceCenter ()
        {
            Assert.assertTrue(steps.isChangeContactInfo(USERCOUNTRY));
        }

        @Test
        public void treeCanSearch ()
        {
            Assert.assertTrue(steps.checkFlight(FROM, TO));
        }

        @Test
        public void fourFillInfo ()
        {
            steps.fillPassengerInformation(FROM, TO, User.USER1);
            Assert.assertTrue(steps.isFillInfoCorrect(FROM, TO, User.USER1));
        }

        @Test
        public void fiveFillSeatAndLuggage ()
        {
            steps.fillSeatsAndLuggageInformation(FROM, TO, User.USER1);
            Assert.assertTrue(steps.isFillInfoSeatAndLuggageCorrect());
        }


        @AfterTest(description = "Stop Browser")
        public void stopBrowser()
        {
            steps.closeDriver();
        }

    }