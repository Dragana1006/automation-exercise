package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;
import utilities.LoginData;

import java.util.List;

public class TC20SearchProductsAndVerifyCartTest extends BaseClass {


    private LoginData loginData;

    @BeforeClass
    public void loginData(){
        loginData= new LoginData("email22232@gmail.com", "dada123");

    }

    @Test
    public void searchProductAndVerifyCartAfterLogin() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnProducts();

        ProductListingPage plp = new ProductListingPage(driver);
        String searchTerm = "Mickey";
        List<String> matchedProducts = plp.searchProducts(searchTerm);

        for (String productName : matchedProducts) {
            Assert.assertTrue(productName.contains(searchTerm), "Product does not contain search term:" + productName);
        }
        plp.addItemsToCart(matchedProducts);

        HeaderOfPage headerPage = new HeaderOfPage(driver);
        headerPage.goToSignUpLoginPage();
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.fillLoginForm(loginData);
        signUpPage.clickLogin();
        headerPage.clickOnCart();

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<String> cartProductNames = shoppingCartPage.getCartProductNames("Description");

        Assert.assertEquals(cartProductNames, matchedProducts, "Cart items did not persist after login.");

    }
}
