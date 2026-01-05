package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.PersonalInformation;

public class TC1RegisterUserTest extends BaseClass {

    PersonalInformation personalInformation;
    HomePage homePage;
    SignUpPage signUpPage;
    AccountInformationPage accountInformationPage;
    AccountCreatedPage accountCreatedPage;
    AccountDeletedPage accountDeletedPage;
    HeaderOfPage header;

    @BeforeClass
    private void instatePersonalInformation() {
        personalInformation = new PersonalInformation(
                "Marko",
                "Gonzales",
                "Metronic",
                "51th street",
                "12313",
                "Florida",
                "Orlando",
                "56256",
                "+32525233");
    }


    @BeforeMethod
    public void initial(){
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
        accountInformationPage = new AccountInformationPage(driver);
        accountCreatedPage = new AccountCreatedPage(driver);
        accountDeletedPage =  new AccountDeletedPage(driver);
        header = new HeaderOfPage(driver);

    }

    @Test
    public void registerUser() throws InterruptedException {
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle,HomePage.EXPECTED_HOME_TITLE_TEXT, "Home page title is not displayed as expected");
        homePage.goToSignUpLoginPage();

        signUpPage.setDataForSignUp();
        String expectedUsername = signUpPage.getSignUpName();
        signUpPage.clickSignUpButton();

        String actualHeading = accountInformationPage.getHeadingOfPage();
        Assert.assertEquals(actualHeading, HomePage.EXPECTED_ACCOUNT_INFO_HEADING_TEXT, "Heading of the page is not displayed as expected");

        accountInformationPage.selectUserTitle("Mrs.");
        accountInformationPage.fillAccountInformation("password22!");
        accountInformationPage.fillAddressInformation(personalInformation);
        accountInformationPage.clickCreateAccount();

        String accountActualHeading = accountCreatedPage.getHeadingOfPage();
        Assert.assertEquals(accountActualHeading, AccountCreatedPage.EXPECTED_HEADING_TEXT, "Heading of the page is not displayed as expected");
        accountCreatedPage.clickContinue();

        String actualUsername = header.getLoggedUsername();
        Assert.assertEquals(actualUsername, expectedUsername, "Username mismatch");

        homePage.deleteAccount();
        String deleteHeading = accountDeletedPage.headingOfThePage();
        Assert.assertEquals(deleteHeading, AccountDeletedPage.EXPECTED_HEADING_TEXT, "Heading of the page is not displayed as expected" );
        accountDeletedPage.clickContinue();




    }
}
