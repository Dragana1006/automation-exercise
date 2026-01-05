package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitHelper;

public class ProductDetailPage {

    WebDriver driver;
    WaitHelper wait;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 3);
    }

    public static String EXPECTED_PDP_TITLE = "Automation Exercise - Product Details";


    private final By quantityInput = By.id("quantity");
    private final By addToCarBtn = By.xpath("//button[@class='btn btn-default cart']");
    private final By viewCartLinkInPopup = By.xpath("//u[text()='View Cart']");
    private final By reviewTab = By.xpath("//ul[@class='nav nav-tabs']//li/a");
    private final By nameInput = By.id("name");
    private final By emailInput = By.id("email");
    private final By reviewInput = By.id("review");
    private final By submitBtn = By.id("button-review");
    private final By reviewAlert = By.xpath("//div[@class='alert-success alert']");
    private final By productName = By.xpath("//div[@class='product-information']//h2");
    private final By categoryName = By.xpath("//div[@class='product-information']//p[1]");
    private final By productPrice = By.xpath("//div[@class='product-information']//span/span");
    private final By availability = By.xpath("//div[@class='product-information']//p[2]");
    private final By condition = By.xpath("//div[@class='product-information']//p[3]");
    private final By brand = By.xpath("//div[@class='product-information']//p[4]");


    public String getPageTitle(){
        return driver.getTitle();
    }

    public boolean isCategoryNamedDisplayed() {
        return driver.findElement(categoryName).isDisplayed();
    }

    public boolean isProductNameDisplayed() {
        return driver.findElement(productName).isDisplayed();
    }

    public boolean isProductPriceDisplayed() {
        return driver.findElement(productPrice).isDisplayed();
    }

    public boolean isAvailabilityDisplayed() {
        return driver.findElement(availability).isDisplayed();
    }

    public boolean isConditionDisplayed() {
        return driver.findElement(condition).isDisplayed();
    }

    public boolean isBrandDisplayed() {
        return driver.findElement(brand).isDisplayed();
    }


    public void updateQuantity(int qty) {
        WebElement input = driver.findElement(quantityInput);
        input.clear();
        input.sendKeys(String.valueOf(qty));

      driver.findElement(addToCarBtn).click();
       wait.waitForElementToBeClickable((viewCartLinkInPopup)).click();
    }

    public  boolean reviewTabIsDisplayed(){
        return driver.findElement(reviewTab).isDisplayed();
    }

    public void setReviewInformation(String name, String email, String reviewText){
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(reviewInput).sendKeys(reviewText);

    }

    public void sendReview(){
        driver.findElement(submitBtn).click();

    }
    public String reviewAlert(){
       return driver.findElement(reviewAlert).getText();
    }



}
