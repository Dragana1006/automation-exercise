package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FooterOfPage {

    WebDriver driver;
    JavascriptExecutor js;

    public FooterOfPage(WebDriver driver){
       this.driver = driver;
       js = (JavascriptExecutor) driver;
   }

    public static String EXPECTED_FOOTER_HEADING = "SUBSCRIPTION";
    public static String EXPECTED_SUBSCRIBE_ALERT = "You have been successfully subscribed!";


    private By subscriptionHeading = By.xpath("//div[@class='single-widget']//h2");
    private final By subscriptionEmail = By.id("susbscribe_email");
    private final By subscribeButton = By.id("subscribe");
    private final By subscribeAlert = By.id("success-subscribe");


    public String getFooterHeading() {
        return driver.findElement(subscriptionHeading).getText();
    }

    public void enterSubscribeEmail(String email) {
        driver.findElement(subscriptionEmail).sendKeys(email);
    }
    public void clickSubscribeButton(){
        driver.findElement(subscribeButton).click();
    }
    public String getSubscribeAlertText(){
        return driver.findElement(subscribeAlert).getText();
    }
}