package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.WaitHelper;

import java.util.ArrayList;
import java.util.List;


public class ProductListingPage {

    WebDriver driver;
    WaitHelper wait;
    JavascriptExecutor js;

    public ProductListingPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 3);
        js = (JavascriptExecutor) driver;
    }

    public static final String EXPECTED_HEADING = "All Products";

    private final By heading = By.xpath("//h2[text()='All Products']");
    private final By productViewLinks = By.xpath("//a[contains(text(),'View Product')]");
    private final By searchInput = By.id("search_product");
    private final By submitSearch = By.id("submit_search");
    private final By productName = By.xpath("//div[@class='productinfo text-center']//p");
    private final By products = By.xpath("//div[@class='single-products']");
    private final By itemAddedPopUp = By.xpath("//h4[text()='Added!']");
    private final By continueButton = By.xpath("//button[text()='Continue Shopping']");
    private final By viewCartLinkFromHeader = By.xpath("//a[normalize-space()='Cart']");
    private final By brandsProducts= By.xpath("//div[@class='brands_products']");
    private final By brandsHeading = By.xpath("//div[@class='brands_products']//h2");
    private By brandsName = By.xpath("//div[@class='brands-name']//li/a");
    private final By viewCartPopUp = By.xpath("//a[contains(text(),'View Cart')]");
    private final By brandPage = By.xpath("(//div[@class='features_items']//h2)[1]");


    public String getHeadingOfPage() {
        return driver.findElement(heading).getText();
    }

    public boolean listOfProductsDisplayed(){
        List<WebElement> allProducts = driver.findElements((products));
        return !allProducts.isEmpty();
    }
    public void clickViewProduct() {
        List<WebElement> viewProductLinks = driver.findElements(productViewLinks);
        viewProductLinks.getFirst().click();
    }

    public List<String> searchProducts(String searchTerm) {
        WebElement searchField = driver.findElement(searchInput);
        searchField.sendKeys(searchTerm);
        driver.findElement(submitSearch).click();

        List<WebElement> productElements = driver.findElements(productName);
        List<String> matchedProducts = new ArrayList<>();
        for (WebElement product : productElements) {
            String productText = product.getText();
            if (productText.contains(searchTerm)) {
                matchedProducts.add(productText);
            }
        }

        return matchedProducts;
    }

    public void addItemsToCart(List<String> items) throws InterruptedException {
        List<WebElement> productsList = driver.findElements(products);
        List<String> addedItems = new ArrayList<>();

        for (WebElement product : productsList) {
            String productName = product.findElement(By.xpath(".//p")).getText();
            System.out.println(productName);

            Actions action = new Actions(driver);
            for (String item : items) {
                if (productName.contains(item)) {
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
                    action.moveToElement(product).perform();
                    WebElement addButton = product.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));

                    addButton.click();
                    Thread.sleep(300);
                    wait.waitForElementToBeClickable(continueButton);
                    driver.findElement(continueButton).click();
                    addedItems.add(item);
                }
            }
        }
        WebElement cartButton = wait.waitForElementToBeClickable((viewCartLinkFromHeader));
        cartButton.click();

    }

    public boolean facetHeadingDisplayed(){
       return driver.findElement(brandsHeading).isDisplayed();
    }

    public void clickOnBrandFacet(String name) throws InterruptedException {
        List<WebElement> listOfBrands = driver.findElements(brandsName);

        for (WebElement brand : listOfBrands) {
            String facetName = brand.getText().trim();
            if (facetName.contains(name)) {
                brand.click();
                wait.waitForVisibilityOfElementLocated(brandPage);
                break;
            }
        }
    }

    public String getBrandPageHeading(){
      return  driver.findElement(brandPage).getText();
    }

}