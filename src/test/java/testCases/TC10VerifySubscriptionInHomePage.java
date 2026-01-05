package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC10VerifySubscriptionInHomePage extends BaseClass {


    @Test
    public void verifySubscriptionInHomePage(){
        HomePage home = new HomePage(driver);
        String homePageTitle = home.getHomePageTitle();
        Assert.assertEquals(homePageTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home page title is not displayed as expected");
        home.scrollToTheBottomOfPage();
        String actualFooterHeading = home.getSubscriptionHeading();
        Assert.assertEquals(actualFooterHeading, HomePage.EXPECTED_FOOTER_HEADING, "Expected footer heading is not displayed");
        String alert = home.subscribeWithEmail("dada@gmail.com");
        Assert.assertEquals(alert, HomePage.EXPECTED_SUBSCRIBE_ALERT, "Subscription success alert text is incorrect");
    }
}
