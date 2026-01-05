package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.PaymentData;
import utilities.PersonalInformation;

import java.util.Arrays;

public class TC24DownloadInvoiceAfterPurchaseOrder extends BaseClass {

    PersonalInformation personalInformation;
    PaymentData paymentData;


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

        paymentData = new PaymentData("Marko",
                "893000322",
                "777",
                "09",
                "2026");
    }


    @Test
    public void downloadInvoiceAfterPurchaseOrder() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        String actualTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home page title is not displayed as expected");

        homePage.add(Arrays.asList("Sleeveless Dress", "Frozen Tops For Kids"));
        HeaderOfPage header = new HeaderOfPage(driver);
        header.clickOnCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.proceedToCheckout();
        Assert.assertTrue(shoppingCartPage.checkoutPopUpDisplayed(), "Checkout pop up is not displayed as expected");

        shoppingCartPage.registerLoginLink();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setDataForSignUp();
        String username = signUpPage.getSignUpName();
        signUpPage.clickSignUpButton();

        AccountInformationPage accountInformationPage = new AccountInformationPage(driver);
        String actualHeading = accountInformationPage.getHeadingOfPage();
        Assert.assertEquals(actualHeading, HomePage.EXPECTED_ACCOUNT_INFO_HEADING_TEXT, "Heading of the page is not displayed as expected");

//        accountInformationPage.selectUserTitle("Mr.");
        accountInformationPage.fillAccountInformation("password22!");
        accountInformationPage.fillAddressInformation(personalInformation);

        String expectedFullName = ". " + personalInformation.getFirstName() + " " + personalInformation.getLastName();
        String expectedCompany = personalInformation.getCompany();
        String expectedAddress1 = personalInformation.getAddress();
        String expectedAddress2 = personalInformation.getAddress2();
        String expectedCityStateZip = personalInformation.getCity() + " " + personalInformation.getState() + " " + personalInformation.getZipcode();
        String expectedPhone = personalInformation.getMobileNumber();
        String expectedCountry = accountInformationPage.selectCountry("Canada");

        accountInformationPage.clickCreateAccount();

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String accountActualHeading = accountCreatedPage.getHeadingOfPage();
        Assert.assertEquals(accountActualHeading, AccountCreatedPage.EXPECTED_HEADING_TEXT, "Heading of the page is not displayed as expected");
        accountCreatedPage.clickContinue();

        String usernameInHeader = header.getLoggedUsername();
        Assert.assertEquals(usernameInHeader, username, "The username displayed in the header does not match the one entered during registration.");

        header.clickOnCart();
        shoppingCartPage.proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
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

        checkoutPage.addComment("This is a custom message");
        checkoutPage.placeOrder();

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.setPaymentInformation(paymentData);
        paymentPage.clickPay();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        String confirmationLabel = orderConfirmationPage.getConfirmationLabel();
        Assert.assertEquals(confirmationLabel, OrderConfirmationPage.EXPECTED_CONFIRMATION_LABEL, "Displayed confirmation label does not match the expected confirmation text.");
        orderConfirmationPage.downloadInvoice();

        orderConfirmationPage.continueButton();
        header.deleteAccount();
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);
        String actualAccountDeleteHeading = accountDeletedPage.headingOfThePage();
        Assert.assertEquals(actualAccountDeleteHeading, AccountDeletedPage.EXPECTED_HEADING_TEXT, "Heading of the page is not displayed as expected.");
        accountDeletedPage.clickContinue();




    }
}
