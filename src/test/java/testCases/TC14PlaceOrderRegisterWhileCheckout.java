package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.PaymentData;
import utilities.PersonalInformation;

import java.util.Arrays;
import java.util.List;

public class TC14PlaceOrderRegisterWhileCheckout extends BaseClass {

    PersonalInformation personalInformation;
    PaymentData validPaymentData;

    @BeforeClass
    public void initializeData(){
        personalInformation = new PersonalInformation(
                "TestName",
                "TestLastName",
                "TestCompany",
                "TestAddress",
                "TestAddress2",
                "TestState",
                "TestCity",
                "535353",
                "352525252");

        validPaymentData = new PaymentData(
                "TestCardName",
                "54362462",
                "554",
                "September",
                "2027");
    }

    @Test
    public void placeOrderRegisterWhileCheckout() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.addProductsToCart(Arrays.asList("Madame Top For Women", "Premium Polo T-Shirts"));

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.proceedToCheckout();
        shoppingCartPage.registerLoginLink();

        SignUpPage signUp = new SignUpPage(driver);
        signUp.setDataForSignUp();
        signUp.clickSignUpButton();

        AccountInformationPage accountInformationPage = new AccountInformationPage(driver);
        accountInformationPage.selectUserTitle("Mr.");
        accountInformationPage.fillAccountInformation("687687");
        accountInformationPage.fillAddressInformation(personalInformation);
        accountInformationPage.clickCreateAccount();

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        accountCreatedPage.clickContinue();
        boolean isDisplayed = homePage.usernameDisplayed();
        Assert.assertTrue(isDisplayed, "Username is not displayed");

        homePage.clickOnCart();
        shoppingCartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        String actualTitle = checkoutPage.getHeadingName();
        Assert.assertEquals(actualTitle, CheckoutPage.EXPECTED_HEADING, "Title of the page is not displayed as expected");

        List<String> delivery = checkoutPage.getDeliveryAddressData();
        List<String> billing = checkoutPage.getBilling();

        delivery.removeFirst();
        billing.removeFirst();
        Assert.assertEquals(delivery, billing, "Delivery and Billing addresses do not match ");

        checkoutPage.addComment("This is a custom message");
        checkoutPage.placeOrder();

        PaymentPage paymentPage = new PaymentPage(driver);
        String actualHeading = paymentPage.headingOfPage();
        Assert.assertEquals(actualHeading,PaymentPage.EXPECTED_HEADING,  "Heading of the page is not displayed as expected");

        paymentPage.setPaymentInformation(validPaymentData);
        paymentPage.clickPay();

        OrderConfirmationPage orderConfirmationPage = new OrderConfirmationPage(driver);
        String actualHeadingOfPage = orderConfirmationPage.headingDisplayed();
        Assert.assertEquals(actualHeadingOfPage,OrderConfirmationPage.EXPECTED_HEADING, "Heading of the page is not displayed as expected");


        String actualConformationLabel = orderConfirmationPage.getConfirmationLabel();
        Assert.assertEquals(actualConformationLabel,OrderConfirmationPage.EXPECTED_CONFIRMATION_LABEL, "Label is not displayed as expected");

        homePage.deleteAccount();
        AccountDeletedPage accountDeleted = new AccountDeletedPage(driver);
        accountDeleted.clickContinue();

    }
}
