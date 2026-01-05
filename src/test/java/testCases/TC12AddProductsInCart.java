package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductListingPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;

import java.util.Arrays;
import java.util.List;

public class TC12AddProductsInCart extends BaseClass {

    @Test
    public void addProductsInCart() throws InterruptedException {
        HomePage home = new HomePage(driver);
        String homeTitle = home.getHomePageTitle();
        Assert.assertEquals(homeTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home page title is not displayed");

        home.clickOnProducts();
        ProductListingPage plp = new ProductListingPage(driver);
        Assert.assertTrue(plp.listOfProductsDisplayed(), "List of products is not displayed");

        List<String> itemsToAdd = Arrays.asList("Blue Top", "Men Tshirt");
        plp.addItemsToCart(itemsToAdd);

        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
        String actualShoppingPageTitle = shoppingCart.getShoppingCartTitle();
        Assert.assertEquals(actualShoppingPageTitle,ShoppingCartPage.EXPECTED_TITLE, "Title of the page is not displayed as expected");

        List<String> cartProductNames = shoppingCart.getCartProductNames("Description");
        Assert.assertEquals(cartProductNames, itemsToAdd, "Cart items do not match the added items.");
    }
}
