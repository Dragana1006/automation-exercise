package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import testBase.BaseClass;

import java.util.List;

public class TC18ViewCategoryProducts extends BaseClass {

    @Test
    public void verifyCategoryFlow() {
        String categoryName = "WOMEN";
        HomePage homePage = new HomePage(driver);
        homePage.getCategoryName(categoryName);

        homePage.expandCategory(categoryName);
        homePage.selectFirstSubcategory();
//
//        String actualPageHeading = homePage.getCategoryPageHeading();

//        Assert.assertTrue(
//                actualPageHeading.toUpperCase().contains(expectedCategoryName.toUpperCase()),
//                "Category page heading does not contain selected subcategory. " +
//                        "Expected heading to contain: " + expectedCategoryName +
//                        " but actual page heading was: " + actualPageHeading
//        );
    }
}

//    @Test
//    public void viewCategoryProducts() {
//
//        verifyCategoryFlow("Women");
//        verifyCategoryFlow("Men");
//    }
//}
