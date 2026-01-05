package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import testBase.BaseProductPage;
import utilities.LoginData;
import utilities.PaymentData;
import utilities.PersonalInformation;
import utilities.SignUpData;

import java.util.Arrays;

public class TC15PlaceOrderRegisterBeforeCheckout extends BaseClass {

    PersonalInformation addressInformation;
    PaymentData paymentData;

    @BeforeClass
    public void initializeData(){
        addressInformation = new PersonalInformation(
                "testName",
                "testLastName",
                "testCompany",
                "testAddress",
                "address2",
                "statetest",
                "citytest",
                "3222",
                "324208933");

        paymentData = new PaymentData("Automation Tester",
                "67889",
                "900",
                "6",
                "2026");
    }

    @Test
    public void registerUserBeforeCheckout() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        String actualHomeTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualHomeTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home Page title is not displayed as expected");

        HeaderOfPage header = new HeaderOfPage(driver);
        header.goToSignUpLoginPage();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setDataForSignUp();
        String signUpName = signUpPage.getSignUpName();
        signUpPage.clickSignUpButton();

        AccountInformationPage accountInformationPage = new AccountInformationPage(driver);
        accountInformationPage.selectUserTitle("Mrs");
        accountInformationPage.fillAccountInformation("testpassword");
        accountInformationPage.fillAddressInformation(addressInformation);
        accountInformationPage.clickCreateAccount();

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(driver);
        String accountCreatedTitle = accountCreatedPage.getHeadingOfPage();
        Assert.assertEquals(accountCreatedTitle, AccountCreatedPage.EXPECTED_HEADING_TEXT, "Title of the page is mismatch");

        accountCreatedPage.clickContinue();
        String headerUsername = header.getLoggedUsername();
        Assert.assertEquals(headerUsername, signUpName, "Name mismatch");

        homePage.add(Arrays.asList("Men Tshirt","Sleeveless Dress"));

        header.clickOnCart();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        String shoppingCartTitle = shoppingCartPage.getShoppingCartTitle();
        Assert.assertEquals(shoppingCartTitle, ShoppingCartPage.EXPECTED_TITLE, "Title is mismatch");

        shoppingCartPage.proceedToCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        String pageHeading = checkoutPage.getHeadingName();
        Assert.assertEquals(pageHeading, CheckoutPage.EXPECTED_HEADING, "Heading of the page is not displayed as expected");

        Assert.assertEquals(checkoutPage.getDeliveryFullName(), checkoutPage.getBillingFullName(), "Full name mismatch");
        Assert.assertEquals(checkoutPage.getBillingCompany(), checkoutPage.getBillingCompany(), "Company name mismatch");
        Assert.assertEquals(checkoutPage.getBillingAddress1(), checkoutPage.getBillingAddress1(), "Address 1 mismatch");
        Assert.assertEquals(checkoutPage.getBillingAddress2(), checkoutPage.getBillingAddress2(), "Address 2 mismatch");
        Assert.assertEquals(checkoutPage.getBillingCityStateZip(), checkoutPage.getBillingCityStateZip(), "Billing, state and zip mismatch");
        Assert.assertEquals(checkoutPage.getBillingCountry(), checkoutPage.getBillingCountry(), "County name mismatch");
        Assert.assertEquals(checkoutPage.getBillingPhoneNumber(), checkoutPage.getBillingPhoneNumber(), "Phone number mismatch");

        checkoutPage.addComment("This is a custom message");
        checkoutPage.placeOrder();

        PaymentPage paymentPage = new PaymentPage(driver);
        String paymentHeading = paymentPage.headingOfPage();
        Assert.assertEquals(paymentHeading, PaymentPage.EXPECTED_HEADING, "Heading of the page is not displayed as expected");

        paymentPage.setPaymentInformation(paymentData);
        paymentPage.clickPay();

        OrderConfirmationPage orderConfirmation = new OrderConfirmationPage(driver);
        String confirmationMessage = orderConfirmation.getConfirmationLabel();
        Assert.assertEquals(confirmationMessage, OrderConfirmationPage.EXPECTED_CONFIRMATION_LABEL, "Label is not displayed as expected");

        header.deleteAccount();
        AccountDeletedPage accountDeletedPage = new AccountDeletedPage(driver);
        String accountDeleteHeading = accountDeletedPage.headingOfThePage();
        Assert.assertEquals(accountDeleteHeading, AccountDeletedPage.EXPECTED_HEADING_TEXT, "Heading of the page is not displayd as expected");

        accountDeletedPage.clickContinue();
    }


}
