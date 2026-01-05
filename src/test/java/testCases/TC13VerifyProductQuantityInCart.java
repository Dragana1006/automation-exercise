package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

import java.util.List;

public class TC13VerifyProductQuantityInCart extends BaseClass {

    @Test
    public void increaseQtyInCart(){
        HomePage homePage = new HomePage(driver);
        String actualHomeTitle = homePage.getHomePageTitle();
        Assert.assertEquals(actualHomeTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home Page title is not displayed as expected");

        HeaderOfPage header = new HeaderOfPage(driver);
        header.clickOnProducts();

        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertTrue(plp.listOfProductsDisplayed(), "List of products is not displayed");

        plp.clickViewProduct();
        ProductDetailPage pdp = new ProductDetailPage(driver);
        String actualPageTitle = pdp.getPageTitle();
        Assert.assertEquals(actualPageTitle, ProductDetailPage.EXPECTED_PDP_TITLE, "Title of the page is not displayed as expected");

        int expectedQty = 4;
        pdp.updateQuantity(expectedQty);
        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
        List<String> productQty = shoppingCart.getProductQuantity("Quantity");
        int actualQty = Integer.parseInt(productQty.getFirst());
        System.out.println(actualQty);

        Assert.assertEquals(actualQty, expectedQty, "Quantity on the PLP and PDP doesn't match");
    }
}
