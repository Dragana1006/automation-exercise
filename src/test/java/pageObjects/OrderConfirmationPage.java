package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Date;

public class OrderConfirmationPage {

    WebDriver driver;

    public OrderConfirmationPage(WebDriver driver){
        this.driver = driver;
    }

    public static String EXPECTED_HEADING = "ORDER PLACED!";
    public static String EXPECTED_CONFIRMATION_LABEL = "Congratulations! Your order has been confirmed!";


    private By headingOfPage = By.xpath("//b[text()='Order Placed!']");
    private By confirmedParagraph = By.xpath("//div[@class='col-sm-9 col-sm-offset-1']/p");
    private By downloadInvoiceBtn =By.xpath("//a[normalize-space()='Download Invoice']");
    private By continueBtn = By.cssSelector("a[data-qa='continue-button']");

    public String headingDisplayed(){
       return  driver.findElement(headingOfPage).getText();
    }

    public String getConfirmationLabel(){
       return driver.findElement(confirmedParagraph).getText();
    }

    public void downloadInvoice(){
        driver.findElement(downloadInvoiceBtn).click();
    }

    public void continueButton(){
        driver.findElement(continueBtn).click();
    }
}
