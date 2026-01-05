package testCases;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HeaderOfPage;
import pageObjects.HomePage;
import pageObjects.ProductListingPage;
import testBase.BaseClass;

import javax.sql.rowset.Joinable;
import java.util.List;

public class TC9SearchProduct extends BaseClass {

    HomePage homePage;
    HeaderOfPage header;
    ProductListingPage plp;

    @BeforeMethod
    public void initial(){
        homePage = new HomePage(driver);
        header = new HeaderOfPage(driver);
        plp = new ProductListingPage(driver);

    }
    @Test
    public void searchProduct() throws InterruptedException {
        String homePageTitle = homePage.getHomePageTitle();
        Assert.assertEquals(homePageTitle, HomePage.EXPECTED_HOME_TITLE_TEXT, "Home page title is not displayed as expected");

        header.clickOnProducts();
        Assert.assertTrue(plp.listOfProductsDisplayed(), "List of products is not displayed");

        String searchKeyword = "Top";
        List<String> getSearchedProducts =  plp.searchProducts(searchKeyword);
        for(String product:getSearchedProducts){
          Assert.assertTrue(product.contains(searchKeyword), "Product [+ product +] does not contain search keyword ["+ searchKeyword +"]");
        }
        plp.addItemsToCart(getSearchedProducts);

        header.clickOnCart();
    }
}
