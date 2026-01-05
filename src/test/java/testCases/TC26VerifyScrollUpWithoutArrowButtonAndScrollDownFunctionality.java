package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC26VerifyScrollUpWithoutArrowButtonAndScrollDownFunctionality extends BaseClass {

    @Test
    public void verifyScrollingWithoutUsingArrowButton(){
        HomePage homePage = new HomePage(driver);
        String actualHomeTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualHomeTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title of the page is not displayed as expected");
        homePage.scrollToTheBottomOfPage();
        String actualHeading = homePage.getSubscriptionHeading();
        Assert.assertEquals(actualHeading, HomePage.EXPECTED_FOOTER_HEADING, "Subscription Heading is not displayed as expected");
        String actualBannerText = homePage.clickOnArrow();
        Assert.assertEquals(actualBannerText,HomePage.EXPECTED_TOP_BANNER_HEADING, "Heading of the page is not displayed as expected");

    }
}
