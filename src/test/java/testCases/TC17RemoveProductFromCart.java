package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import pageObjects.ShoppingCartPage;
import testBase.BaseClass;
import utilities.ProductHelper;

import java.util.Arrays;
import java.util.List;

public class TC17RemoveProductFromCart extends BaseClass {

    //delete specific product
    @Test
    public void removeProductFromCart() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        String actualTitleText = homePage.getHomePageTitle();
        Assert.assertEquals(actualTitleText, HomePage.EXPECTED_HOME_TITLE_TEXT, "Title of the page is displayed as expected");

        ProductHelper productHelper = new ProductHelper(driver);
        productHelper.addItemsToCart(Arrays.asList("Winter Top", "Summer White Top"));

        HeaderOfPage header = new HeaderOfPage(driver);
        header.clickOnCart();
        ShoppingCartPage shoppingCart = new ShoppingCartPage(driver);
        String shoppingCartPageTitle = shoppingCart.getShoppingCartTitle();
        Assert.assertEquals(shoppingCartPageTitle, ShoppingCartPage.EXPECTED_TITLE, "Title of the page is not displayed as expected");

        String deleteProduct = "Summer White Top";
        shoppingCart.deleteItem(deleteProduct);
        List<String> remainingProducts = shoppingCart.getCartProductNames("Description");
        Assert.assertFalse(remainingProducts.contains(deleteProduct),"Product was not removed from cart");

    }
}
