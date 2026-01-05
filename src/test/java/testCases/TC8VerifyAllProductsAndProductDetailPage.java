package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import pageObjects.ProductDetailPage;
import pageObjects.ProductListingPage;
import testBase.BaseClass;

import java.sql.SQLOutput;

public class TC8VerifyAllProductsAndProductDetailPage extends BaseClass {

    HomePage homePage;
    HeaderOfPage header;
    ProductListingPage plp;
    ProductDetailPage pdp;

    @BeforeMethod
    public void initial() {
        homePage = new HomePage(driver);
        header = new HeaderOfPage(driver);
        plp = new ProductListingPage(driver);
        pdp = new ProductDetailPage(driver);
    }

    @Test
    public void verifyAllProductsAndProductDetailPage(){
        String homePageTitle = homePage.getHomePageTitle();
        Assert.assertEquals(homePageTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home page title is mismatch");

        header.clickOnProducts();
        String actualHeading = plp.getHeadingOfPage();
        Assert.assertTrue(actualHeading.equalsIgnoreCase(ProductListingPage.EXPECTED_HEADING), "Heading text is not diplayed as expected");

        boolean productDisplayed = plp.listOfProductsDisplayed();
        Assert.assertTrue(productDisplayed, "Product missing from the Product Listing Page");

        plp.clickViewProduct();
        String pdpTitle = pdp.getPageTitle();
        Assert.assertEquals(pdpTitle, ProductDetailPage.EXPECTED_PDP_TITLE, "Title of the page is not displayed as expected");

        Assert.assertTrue(pdp.isCategoryNamedDisplayed(), "Category name is not displayed");
        Assert.assertTrue(pdp.isProductNameDisplayed(), "Product name is not displayed");
        Assert.assertTrue(pdp.isProductPriceDisplayed(), "Product price is not displayed");
        Assert.assertTrue(pdp.isAvailabilityDisplayed(), "Availability of the product is not displayed");
        Assert.assertTrue(pdp.isConditionDisplayed(), "Condition is not displayed");
        Assert.assertTrue(pdp.isBrandDisplayed(), "Brand of the product is not displayed");


    }
}
