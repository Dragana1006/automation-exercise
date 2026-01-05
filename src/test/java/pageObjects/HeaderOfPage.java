package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WaitHelper;

public class HeaderOfPage {

    WebDriver driver;
    WaitHelper wait;

    public HeaderOfPage(WebDriver driver){
        this.driver = driver;
        wait = new WaitHelper(driver, 3);
    }

    private final By signUpButton = By.xpath("//a[@href='/login']");
    private final By deleteAccount = By.linkText("Delete Account");
    private final By contactUsButton = By.xpath("//i[@class='fa fa-envelope']");
    private final By testCasesButton = By.linkText("Test Cases");
    private final By productButton = By.xpath("//a[@href='/products']");
    private final By cartButton = By.xpath("//a[normalize-space()='Cart']");
    private final By loggedUsername = By.xpath("//a[contains(text(),'Logged in as')]/b");
    private final By logoutButton = By.xpath("//i[@class='fa fa-lock']");

    public void goToSignUpLoginPage() {
        driver.findElement(signUpButton).click();
    }

    public void deleteAccount() {
        driver.findElement(deleteAccount).click();
    }

    public void clickContactUs() {
        driver.findElement(contactUsButton).click();
    }
    public void clickOnTestCases() {
        driver.findElement(testCasesButton).click();
    }

    public void clickOnProducts() {
        driver.findElement(productButton).click();
    }

    public void clickOnCart() {
        driver.findElement(cartButton).click();
    }
    public void logout() {
        WebElement logoutBtn = wait.waitForElementToBeClickable((logoutButton));
        logoutBtn.click();

    }

    public String getLoggedUsername() {
        return driver.findElement(loggedUsername).getText();
    }

    public boolean isUsernameDisplayedInHeader(){
      return driver.findElement(loggedUsername).isDisplayed();

    }
}
