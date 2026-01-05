package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC25VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality extends BaseClass {


    @Test
    public void verifyScrollUpAndScrollDownFunctionality(){
        HomePage home = new HomePage(driver);
        String actualHomeTitle = home.getHomePageTitle();
        Assert.assertEquals(actualHomeTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home Page title is not displayed as expected");
        String actualHeading = home.scrollToTheBottomOfPage();
        Assert.assertEquals(actualHeading, HomePage.EXPECTED_FOOTER_HEADING, "Footer heading displayed as expected");
        String actualHeadingText = home.clickOnArrow();
        Assert.assertEquals(actualHeadingText, HomePage.EXPECTED_TOP_BANNER_HEADING, "Heading of the page is not displayed as expected");



    }
}
