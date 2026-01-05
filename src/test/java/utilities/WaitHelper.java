package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitHelper {

    WebDriver driver;
    WebDriverWait wait;

    public WaitHelper(WebDriver driver, int timeOutInSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));

    }

    public void waitForVisibilityOfAllElement(List<WebElement> elements){
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForVisibilityOf(WebElement element){
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }
    public WebElement waitForVisibilityOfElementLocated(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return null;
    }

    public WebElement waitForElementToBeClickable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitUntilElementDisappearFromPage(WebElement element){
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void until(ExpectedCondition<WebElement> webElementExpectedCondition) {

    }
}
