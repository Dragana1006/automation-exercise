package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testBase.BaseClass;

public class AccountCreatedPage  {
    WebDriver driver;

    public AccountCreatedPage(WebDriver driver){

        this.driver = driver;
    }
    public static String EXPECTED_HEADING_TEXT = "ACCOUNT CREATED!";

    public By headingText = By.xpath("//h2[@data-qa='account-created']");
    public By continueBtn = By.xpath("//a[@class='btn btn-primary']");

    public String getHeadingOfPage(){
        return driver.findElement(headingText).getText();
    }
    public void clickContinue(){
        driver.findElement(continueBtn).click();
    }


}
