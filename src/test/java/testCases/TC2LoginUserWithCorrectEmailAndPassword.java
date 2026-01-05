package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import pageObjects.SignUpPage;
import testBase.BaseClass;
import utilities.LoginData;

public class TC2LoginUserWithCorrectEmailAndPassword extends BaseClass {

    HomePage homePage;
    SignUpPage signUp;
    LoginData validLoginData;
    HeaderOfPage header;


    @BeforeClass
    public void loginData(){
        validLoginData = new LoginData("emailaddress1@gmail.com", "test123!");
    }

    @BeforeMethod
    public void setUp(){
        homePage = new HomePage(driver);
        signUp = new SignUpPage(driver);
        header = new HeaderOfPage(driver);
    }

    @Test
    public void verifyLoginUserWithCorrectEmailAndPassword() {
        String actualTitleText = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitleText, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title of the page is displayed as expected");
        homePage.goToSignUpLoginPage();

        signUp.fillLoginForm(validLoginData);
        signUp.clickLogin();
        boolean usernameDisplayed = header.isUsernameDisplayedInHeader();
        Assert.assertTrue(usernameDisplayed, "Username is not displayed in Header section of the page");


    }


}
