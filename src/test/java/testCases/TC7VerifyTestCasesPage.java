package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import pageObjects.TestCasesPage;
import testBase.BaseClass;

public class TC7VerifyTestCasesPage extends BaseClass {


    @Test
    public void verifyTestCasesPage() {
        HomePage home = new HomePage(driver);
        String homeTitle = home.getHomePageTitle();
        Assert.assertEquals(homeTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home Page title is mismatch");

        HeaderOfPage header = new HeaderOfPage(driver);
        header.clickOnTestCases();

        TestCasesPage testCasesPage = new TestCasesPage(driver);
        String actualHeading = testCasesPage.getHeadingOfPage();
        Assert.assertTrue(actualHeading.equalsIgnoreCase(TestCasesPage.EXPECTED_HEADING), "Heading text does not match (case insensitive)");
    }
}
