package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.FooterOfPage;
import pageObjects.ShoppingCartPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC11VerifySubscriptionInCartPage extends BaseClass {

    @Test
    public void verifySubscriptionInCartPage(){
        HomePage homePage = new HomePage(driver);
        homePage.clickOnCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        String titleOfPage =shoppingCartPage.getShoppingCartTitle();
        Assert.assertEquals(titleOfPage, ShoppingCartPage.EXPECTED_TITLE, "Title of the page is not displayed as expected");
        shoppingCartPage.scrollToTheBottomOfThePage();

        FooterOfPage footer = new FooterOfPage(driver);
        String footerHeading = footer.getFooterHeading();
        Assert.assertEquals(footerHeading, FooterOfPage.EXPECTED_FOOTER_HEADING, "Footer heading is not displayed as expected");

        footer.enterSubscribeEmail("test@gmail.com");
        footer.clickSubscribeButton();
        Assert.assertEquals(footer.getSubscribeAlertText(), FooterOfPage.EXPECTED_SUBSCRIBE_ALERT, "Subscribe alert is incorrect");

    }
}
