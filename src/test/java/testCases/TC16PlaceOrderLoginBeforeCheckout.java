package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.LoginData;
import utilities.PaymentData;
import utilities.ProductHelper;
import utilities.TableHelper;

import java.util.Arrays;
import java.util.List;

public class TC16PlaceOrderLoginBeforeCheckout extends BaseClass {

    LoginData login;
    TableHelper tableHelper;
    PaymentData paymentData;

    @BeforeClass
    public void initializeUserData(){
        login = new LoginData(
                "validemail111@gmail.com",
                "test123!");
        tableHelper = new TableHelper(driver);
        paymentData = new PaymentData(
                "Automation Tester",
                "890290033",
                "222",
                "7",
                "2030");
    }

    /// 11 step nisam ubacila
    @Test
    public void loginBeforePlaceOrder() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        String homePageTitle = homePage.getHomePageTitle();
        Assert.assertEquals(homePageTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title of the page is not displayed as expected");

        HeaderOfPage header = new HeaderOfPage(driver);
        header.goToSignUpLoginPage();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.fillLoginForm(login);
        signUpPage.clickLogin();

        boolean usernameDisplayed = header.isUsernameDisplayedInHeader();
        Assert.assertTrue(usernameDisplayed, "Username is not displayed in header");

        ProductHelper productHelper = new ProductHelper(driver);
        productHelper.addItemsToCart(Arrays.asList("Men Tshirt", "Sleeves Printed Top - White"));

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
