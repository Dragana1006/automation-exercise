package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductDetailPage;
import pageObjects.ProductListingPage;
import testBase.BaseClass;

public class TC21AddReviewOnProductTest extends BaseClass {

    public static String EXPECTED_HEADING = "ALL PRODUCTS";
    public static String EXPECTED_REVIEW_ALERT_TEXT  = "Thank you for your review.";

    @Test
    public void addReviewOnProduct(){
        HomePage home = new HomePage(driver);
        home.clickOnProducts();
        ProductListingPage plp = new ProductListingPage(driver);
        String actualHeading = plp.getHeadingOfPage();
        Assert.assertEquals(actualHeading, TC21AddReviewOnProductTest.EXPECTED_HEADING, "Heading of the page is not displayed as expected");

        plp.clickViewProduct();
        ProductDetailPage pdp = new ProductDetailPage(driver);
        boolean isDisplayed = pdp.reviewTabIsDisplayed();
        Assert.assertTrue(isDisplayed, "Review tab is not displayed");
        pdp.setReviewInformation("Automation Tester", "tester@gmail.co", "This is a custom message");
        pdp.sendReview();
        String actualReviewAlertText = pdp.reviewAlert();
        Assert.assertEquals(actualReviewAlertText, TC21AddReviewOnProductTest.EXPECTED_REVIEW_ALERT_TEXT, "Alert text is not displayed as expected");

    }
}
