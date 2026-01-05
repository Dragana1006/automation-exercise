package testBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.WaitHelper;

import java.util.ArrayList;
import java.util.List;

public class BaseProductPage extends BaseClass {

   protected WebDriver driver;
    WaitHelper wait;
    JavascriptExecutor js;

    public BaseProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitHelper(driver, 3);
        js = (JavascriptExecutor) driver;
    }
    private final By products = By.xpath("//div[@class='single-products']");
    private final By itemAddedPopUp = By.xpath("//h4[text()='Added!']");
    private final By continueButton = By.xpath("//button[text()='Continue Shopping']");
    private final By viewCartLinkFromHeader = By.xpath("//a[normalize-space()='Cart']");

    public List<String> getAddedItems(List<String> items) throws InterruptedException {
        List<WebElement> productsList = driver.findElements(products);
        List<String> addedItems = new ArrayList<>();
        for (WebElement product : productsList) {
            String productName = product.findElement(By.xpath(".//p")).getText();

            Actions action = new Actions(driver);
            for (String item : items) {
                if (productName.contains(item)) {
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
                    action.moveToElement(product).perform();
                    WebElement addButton = product.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"));

                    addButton.click();
                    Thread.sleep(3000);
                    String text = driver.findElement(itemAddedPopUp).getText();
                    driver.findElement(continueButton).click();
                    addedItems.add(item);
                }
            }
        }
        WebElement cartButton = wait.waitForElementToBeClickable((viewCartLinkFromHeader));
        cartButton.click();
        return addedItems;

    }

}
