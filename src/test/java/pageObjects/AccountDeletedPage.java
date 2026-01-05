package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage {
    WebDriver driver;

    public AccountDeletedPage(WebDriver driver) {
        this.driver = driver;
    }

    public static String EXPECTED_HEADING_TEXT = "ACCOUNT DELETED!";

    public By heading = By.xpath("//h2[@data-qa='account-deleted']");
    public By continueBtn = By.xpath("//a[@data-qa='continue-button']");

    public String headingOfThePage(){
       return driver.findElement(heading).getText();
    }
    public void clickContinue(){
        driver.findElement(continueBtn).click();

    }
}
