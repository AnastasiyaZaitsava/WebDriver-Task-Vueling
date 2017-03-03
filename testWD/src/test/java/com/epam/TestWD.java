package com.epam;

<<<<<<< HEAD
import com.epam.util.User;
=======
import com.epam.pages.VuelingContactPassengerPage;
>>>>>>> origin/master
import com.epam.steps.Steps;
import org.openqa.selenium.WebDriver;
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
<<<<<<< HEAD
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


        @AfterTest(description = "Stop Browser")
=======
        public void fillPassengerInformation(){
            WebDriver driver = steps.getDriver();
            VuelingContactPassengerPage vcpp = new VuelingContactPassengerPage(driver);
            vcpp.openPage();
            Assert.assertTrue(vcpp.isPageOpened());
            vcpp.enterAndSubmitPassengerContact("John", "Smith", "Minsk", "456783", "johnsmith@gmail.com", "BY", "+375");
        }

        @AfterMethod(description = "Stop Browser")
>>>>>>> origin/master
        public void stopBrowser()
        {
            steps.closeDriver();
        }

    }