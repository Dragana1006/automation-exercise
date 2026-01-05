package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import pageObjects.ProductListingPage;
import testBase.BaseClass;

public class TC19ViewAndCartBrandProducts extends BaseClass {

    @Test
    public void verifyViewAndCartBrandProducts() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        String homePageTitle = homePage.getHomePageTitle();
        Assert.assertEquals(homePageTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title of the page is not displayed as expected");

        HeaderOfPage header = new HeaderOfPage(driver);
        header.clickOnProducts();

        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertTrue(plp.facetHeadingDisplayed(), "Brands heading is not displayed");

        String facetName = "BIBA";
        plp.clickOnBrandFacet(facetName);
        String brandHeader = plp.getBrandPageHeading();
        Assert.assertTrue(brandHeader.contains(facetName), "Brand page is not displayed. Expected header should contain " +
                "["+facetName+"]," + " but actual header was ["+brandHeader+"]");

        String newSelectedFacet = "MADAME";
        plp.clickOnBrandFacet(newSelectedFacet);
        String newBrandHeading = plp.getBrandPageHeading();
        Assert.assertTrue(newBrandHeading.contains(newSelectedFacet), "Brand page is not displayed. " +
                "Expected heading should contain + ["+newSelectedFacet+"]," + " but actual heading was ["+brandHeader+"]");

    }
}
