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

public class TC3LoginUserWithIncorrectEmailAndPassword extends BaseClass {


    LoginData invalidLoginData;
    HomePage homePage;
    HeaderOfPage header;
    SignUpPage signUpPage;

    @BeforeClass
    public void loginData() {
        invalidLoginData = new LoginData("invalidemail@ya.com", "test111");
    }

    @BeforeMethod
    public void initial() {
        homePage = new HomePage(driver);
        header = new HeaderOfPage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void verifyLoginWithInvalidData(){
        String actualHomePageTitle =  homePage.getHomePageTitle();
        Assert.assertEquals(actualHomePageTitle, HomePage.EXPECTED_TITLE, "Home page title is not displayed as expected");

        header.goToSignUpLoginPage();
        String actualLoginHeading = signUpPage.getHeadingForLogin();
        Assert.assertEquals(actualLoginHeading, SignUpPage.EXPECTED_LOGIN_HEADING, "Login heading is not displayed as expected");

        signUpPage.fillLoginForm(invalidLoginData);
        signUpPage.clickLogin();

        String actualErrorMsg = signUpPage.getErrorMessage();
        Assert.assertEquals(actualErrorMsg, SignUpPage.EXPECTED_ERROR_MESSAGE, "Error message for invalid login is not displayed as expected");
    }


}
