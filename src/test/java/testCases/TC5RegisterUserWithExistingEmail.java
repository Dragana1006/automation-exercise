package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SignUpPage;
import testBase.BaseClass;
import utilities.LoginData;
import utilities.SignUpData;

public class TC5RegisterUserWithExistingEmail extends BaseClass {

    LoginData login;
    HomePage homePage;
    SignUpPage signUpPage;

    @BeforeClass
    public void login(){
        login= new LoginData("validemail@gmail.com", "test123!");
    }

    @BeforeMethod
    public void initial(){
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void verifyRegistrationWithExistingEmail(){
        String actualHomePageTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualHomePageTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title of the page is not displayed as expected");
        homePage.goToSignUpLoginPage();

        String actualSignUpHeading = signUpPage.getHeadingForSignUp();
        Assert.assertEquals(actualSignUpHeading, SignUpPage.EXPECTED_SIGNUP_HEADING, "Sing up heading is not displayed as expected");

        signUpPage.useExistingEmailForRegistration(login);
        signUpPage.clickSignUpButton();

        String actualErrorMsg = signUpPage.getErrorForEmailAddress();
        Assert.assertEquals(actualErrorMsg, SignUpPage.EXPECTED_ERROR_FOR_USING_EXISTING_EMAIL, "Error message for existing email is not correct");
    }
}
