package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import testBase.BaseProductPage;
import utilities.PersonalInformation;

import java.util.Arrays;

public class TC23VerifyAddressDetailsInCheckoutPage extends BaseClass {

    PersonalInformation personalInformation;
    HomePage homePage;
    HeaderOfPage header;
    SignUpPage signUp;
    AccountInformationPage accountInformation;
    AccountCreatedPage accountCreated;
    BaseProductPage baseProductPage;
    ShoppingCartPage shoppingCart;
    CheckoutPage checkoutPage;
    AccountDeletedPage accountDeletedPage;


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
        header = new HeaderOfPage(driver);
        signUp = new SignUpPage(driver);
        accountInformation = new AccountInformationPage(driver);
        accountCreated = new AccountCreatedPage(driver);
        baseProductPage = new BaseProductPage(driver);
        shoppingCart = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        accountDeletedPage = new AccountDeletedPage(driver);
    }
    @Test
    public void verifyAddressDetailsInCheckoutPage() throws InterruptedException {
        String actualTitleText =  homePage.getHomePageTitle();
        Assert.assertEquals(actualTitleText,HomePage.EXPECTED_TITLE, "Title of the page is not displayed as expected");

        header.goToSignUpLoginPage();
        signUp.setDataForSignUp();
        signUp.clickSignUpButton();
        accountInformation.fillAccountInformation("565662");
        accountInformation.fillAddressInformation(personalInformation);
        // The web application currently adds a "." before the user's full name,so we replicate it here to match the displayed format.
        String expectedFullName = ". " + personalInformation.getFirstName() + " " + personalInformation.getLastName();
        String expectedCompany = personalInformation.getCompany();
        String expectedAddress1 = personalInformation.getAddress();
        String expectedAddress2 = personalInformation.getAddress2();
        String expectedCityStateZip = personalInformation.getCity() + " " + personalInformation.getState() + " " + personalInformation.getZipcode();
        String expectedPhone = personalInformation.getMobileNumber();
        String expectedCountry = accountInformation.selectCountry("Canada");

        accountInformation.clickCreateAccount();
        String actualHeadingText = accountCreated.getHeadingOfPage();
        Assert.assertEquals(actualHeadingText, AccountCreatedPage.EXPECTED_HEADING_TEXT, "Heading of the page is not displayed as expected");

        accountCreated.clickContinue();
        boolean usernameIsDisplayed =homePage.usernameDisplayed();
        Assert.assertTrue(usernameIsDisplayed, "Username is not displayed on Home page");

        baseProductPage.getAddedItems(Arrays.asList("Men Tshirt", "Winter Top"));
        homePage.clickOnCart();

        boolean isDisplayed = shoppingCart.shoppingCartBreadcrumbDisplayed("Shopping Cart");
        Assert.assertTrue(isDisplayed, "Breadcrumb 'Shopping Cart' not displayed!");

        shoppingCart.proceedToCheckout();
        Assert.assertEquals(checkoutPage.getDeliveryFullName(), expectedFullName);
        Assert.assertEquals(checkoutPage.getDeliveryCompany(), expectedCompany);
        Assert.assertEquals(checkoutPage.getDeliveryAddress1(), expectedAddress1);
        Assert.assertEquals(checkoutPage.getDeliveryAddress2(), expectedAddress2);
        Assert.assertEquals(checkoutPage.getDeliveryCityStateZip(), expectedCityStateZip);
        Assert.assertEquals(checkoutPage.getDeliveryCountryName(), expectedCountry);
        Assert.assertEquals(checkoutPage.getDeliveryPhoneNumber(), expectedPhone);

        Assert.assertEquals(checkoutPage.getBillingFullName(), expectedFullName);
        Assert.assertEquals(checkoutPage.getBillingCompany(), expectedCompany);
        Assert.assertEquals(checkoutPage.getBillingAddress1(), expectedAddress1);
        Assert.assertEquals(checkoutPage.getBillingAddress2(), expectedAddress2);
        Assert.assertEquals(checkoutPage.getBillingCityStateZip(), expectedCityStateZip);
        Assert.assertEquals(checkoutPage.getBillingCountry(), expectedCountry);
        Assert.assertEquals(checkoutPage.getBillingPhoneNumber(), expectedPhone);

        header.deleteAccount();
        String actualAccountDeletedHeading = accountDeletedPage.headingOfThePage();
        Assert.assertEquals(actualAccountDeletedHeading, AccountDeletedPage.EXPECTED_HEADING_TEXT, "Heading of the page is not displayed as expected");
        accountDeletedPage.clickContinue();





    }

}
