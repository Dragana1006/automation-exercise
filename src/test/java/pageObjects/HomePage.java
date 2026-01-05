package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import testBase.BaseProductPage;
import utilities.WaitHelper;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HomePage extends BaseProductPage {

    ProductListingPage plp;
    WaitHelper wait;
    JavascriptExecutor js;

    public static String EXPECTED_TITLE = "Automation Exercise";

    public HomePage(WebDriver driver) {
        super(driver);
        plp = new ProductListingPage(driver);
        wait = new WaitHelper(driver, 2);
        js = (JavascriptExecutor) driver;
    }

    public static String EXPECTED_HOME_TITLE_TEXT = "Automation Exercise";
    public static String EXPECTED_ACCOUNT_INFO_HEADING_TEXT = "ENTER ACCOUNT INFORMATION";
    public static String EXPECTED_FOOTER_HEADING = "SUBSCRIPTION";
    public static String EXPECTED_TOP_BANNER_HEADING = "Full-Fledged practice website for Automation Engineers";
    public static String EXPECTED_SUBSCRIBE_ALERT = "You have been successfully subscribed!";


    private final By signUpButton = By.xpath("//a[@href='/login']");
    private final By loggedUsername = By.xpath("//a[contains(text(),'Logged in as')]/b");
    private final By deleteAccount = By.linkText("Delete Account");
    private final By logoutButton = By.xpath("//i[@class='fa fa-lock']");
    private final By contactUsButton = By.xpath("//i[@class='fa fa-envelope']");
    private final By testCasesButton = By.linkText("Test Cases");
    private final By productButton = By.xpath("//a[@href='/products']");
    private final By cartButton = By.xpath("//a[normalize-space()='Cart']");
    private final By nameOfProduct = By.xpath("//div[@class='single-products']//p");
    private final By recommendedProduct = By.xpath("//div[@class='recommended_items']//div[@class='col-sm-4']");
    private final By categoryFilterHeading = By.xpath("//div[@class='left-sidebar']//h2");
    private final By categoryFilter = By.xpath("//div[@id='accordian']//div");
    private final By subscriptionHeading = By.xpath("//div[@class='single-widget']//h2");
    private final By subscriptionEmail = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By subscribeAlert = By.id("success-subscribe");
    private final By scrollUpButton = By.id("scrollUp");
    private final By topBanner = By.xpath("//div[@class='col-sm-6']//h2");
    private By product = By.xpath("//div[@class='product-image-wrapper']");
    private By continueShoppingBtn = By.xpath("//button[contains(text(),'Continue Shopping')]");
    private final By viewCartPopUp = By.xpath("//a[contains(text(),'View Cart')]");
    private By carouselNextButton = By.xpath("//a[@data-slide='next']");
    private By subcategoryFilters = By.xpath("//div[@class='panel-body']//ul/li/a");
    private By categoryHeading = By.xpath("//div[@class='panel-heading']//a");
    private By recommendedItemsHeading = By.xpath("//div[@class='recommended_items']//h2");

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public void goToSignUpLoginPage() {
        driver.findElement(signUpButton).click();
    }

    public String getLoggedUsername() {
        return driver.findElement(loggedUsername).getText();
    }


    public boolean usernameDisplayed() {
        return driver.findElement(loggedUsername).isDisplayed();
    }

    public void deleteAccount() {
        driver.findElement(deleteAccount).click();
    }

    public void clickOnProducts() {
        driver.findElement(productButton).click();
    }

    public void clickOnCart() {
        driver.findElement(cartButton).click();
    }

    public void addProductsToCart(List<String> items) throws InterruptedException {
        plp.addItemsToCart(items);
    }

    public void add(List<String> productsForAdding) throws InterruptedException {
        List<WebElement> listOfProducts = driver.findElements(product);

        for (WebElement product : listOfProducts) {
            String productName = product.findElement(By.xpath(".//p")).getText();
            Actions actions = new Actions(driver);
            for (String item : productsForAdding) {

                if (productName.contains(item)) {
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
                    actions.moveToElement(product).perform();

                    System.out.println(productName.toUpperCase());

                    WebElement p = product.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));
                    p.click();
                    Thread.sleep(300);
                    wait.waitForElementToBeClickable(continueShoppingBtn);
                    driver.findElement(continueShoppingBtn).click();

                }
            }
        }
    }


    public boolean headingDisplayed() {
        return driver.findElement(recommendedItemsHeading).isDisplayed();
    }



    public String getCategoryName(String categoryName) {
       List<WebElement> categories =  driver.findElements(By.xpath("//div[@class='panel-heading']//a"));
        for(WebElement category:categories){
           String text =  category.getText();

            if(text.equalsIgnoreCase(categoryName)){
            return text;
            }
        }
        return null;
    }

    public void expandCategory(String name){
        List<WebElement> headings = driver.findElements(categoryHeading);
        for(WebElement heading:headings){
            String headingText = heading.getText();
            if(headingText.equalsIgnoreCase(name)){
                WebElement plusBadge = heading.findElement(By.xpath(".//span[@class='badge pull-right']"));
                plusBadge.click();

            }
        }
    }

    public List<String> getSubcategoryNames(String categoryName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='" + categoryName + "']//ul")
        ));

        By subCategoryLocator = By.xpath(
                "//div[@id='" + categoryName + "']//ul/li/a"
        );

        List<WebElement> subCategories = driver.findElements(subCategoryLocator);
        List<String> names = new ArrayList<>();
        for (WebElement subCategory : subCategories) {
            String name = ((String) js.executeScript("return arguments[0].textContent;", subCategory)).trim();
            names.add(name);
        }

        return names;

    }

    public void selectFirstSubcategory(){
        By subcategoryLocator =
                By.xpath("//div[contains(@class,'panel-collapse') and contains(@class,'in')]//ul/li/a");

        wait.until(ExpectedConditions.visibilityOfElementLocated(subcategoryLocator));

        List<WebElement> subcategoryNames =
                driver.findElements(subcategoryLocator);

        System.out.println(subcategoryNames.size());

        for (WebElement name : subcategoryNames) {
            System.out.println("Displayed: " + name.isDisplayed());
            System.out.println("Text: " + name.getText());
        }






       //       List<String> subCategoryNames =  getSubcategoryNames(categoryName);
//        System.out.println(subCategoryNames.isEmpty());
//        String firstName = subCategoryNames.get(0);
//        WebElement firstSubCategory = driver.findElement(
//                By.xpath("//div[@id='"+categoryName+"']//a[normalize-space()='" + firstName + "']")
//        );
//
//        firstSubCategory.click();


    }



    public String getCategoryPageHeading(){
        String heading = driver.findElement(By.xpath("//h2[@class='title text-center']")).getText();
        return heading;
    }


    public String scrollToTheBottomOfPage(){
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        return driver.findElement(subscriptionHeading).getText();

    }

    public String clickOnArrow(){
        driver.findElement(scrollUpButton).click();
        return driver.findElement(topBanner).getText();
    }

    public String getSubscriptionHeading(){
      return  driver.findElement(subscriptionHeading).getText();
    }

    public String subscribeWithEmail (String email){
        driver.findElement(subscriptionEmail).sendKeys(email);
        driver.findElement(subscribeButton).click();
        String alertText = driver.findElement(subscribeAlert).getText();
        return alertText;
    }

}
