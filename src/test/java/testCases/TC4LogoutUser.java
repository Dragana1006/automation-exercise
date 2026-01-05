package testCases;

import org.junit.experimental.theories.Theories;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.AccountInformationPage;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import pageObjects.SignUpPage;
import testBase.BaseClass;
import utilities.LoginData;
import utilities.SignUpData;
import utilities.WaitHelper;

public class TC4LogoutUser extends BaseClass {

    LoginData loginData;
    WaitHelper wait;
    HomePage homePage;
    HeaderOfPage header;
    SignUpPage signUpPage;
    AccountInformationPage accountInformationPage;

    @BeforeClass
    public void login() {
        loginData = new LoginData("validemail@gmail.com", "test111");
    }

    @BeforeMethod
    public void setUp() {
        wait = new WaitHelper(driver, 3);
        homePage = new HomePage(driver);
        header = new HeaderOfPage(driver);
        signUpPage = new SignUpPage(driver);
        accountInformationPage = new AccountInformationPage(driver);
    }

    @Test
    public void verifyLogout() {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title of Home Page is not displayed as expected");
        header.goToSignUpLoginPage();

        signUpPage.fillLoginForm(loginData);
        signUpPage.clickLogin();
        String actualHeading = homePage.getHomePageTitle();
        Assert.assertEquals(actualHeading, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title of the page is not displayed as expected");
        header.logout();
        String actualSignUpHeading = signUpPage.getHeadingForSignUp();
        Assert.assertEquals(actualSignUpHeading, SignUpPage.EXPECTED_SIGNUP_HEADING, "Heading of the page is not displayed as expected");
    }
}