package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class ProductHelper {

    WebDriver driver;
    WaitHelper wait;
    JavaScriptHelper jsh;

    public ProductHelper(WebDriver driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 3);
        jsh = new JavaScriptHelper(driver);
    }
    private final By products = By.xpath("//div[@class='single-products']");
    private final By itemAddedPopUp = By.xpath("//h4[text()='Added!']");
    private final By continueShoppingBtn = By.xpath("//button[text()='Continue Shopping']");
    private final By cartLink = By.xpath("//a[normalize-space()='Cart']");

    public List<String> addItemsToCart(List<String> items) throws InterruptedException {
        List<WebElement> productsList = driver.findElements(products);
        List<String> addedItems = new ArrayList<>();
        Actions action = new Actions(driver);

        for (WebElement product : productsList) {
            String productName = product.findElement(By.xpath(".//p")).getText();
            for (String item : items) {

                if (productName.contains(item)) {
                    action.moveToElement(product).perform();
                    jsh.scrollUntilElementBecomeVisible(product);

                    WebElement addButton = product.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));
                    addButton.click();

                    wait.waitForElementToBeClickable(continueShoppingBtn).click();
                    addedItems.add(item);
                    break;
                }
            }
        }
        wait.waitForElementToBeClickable((cartLink)).click();
        return addedItems;
    }
}
