package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testBase.BaseClass;
import utilities.PaymentData;
import utilities.PersonalInformation;

import java.util.Arrays;
import java.util.List;

public class PaymentPage {

    WebDriver driver;

    public PaymentPage(WebDriver driver){
        this.driver = driver;
    }

    public static String EXPECTED_HEADING= "Payment";

    public By headingElement= By.xpath("//h2[@class='heading']");
    public By nameOfCardInput =By.xpath("//input[@data-qa='name-on-card']");
    public By cardNumberInput =By.xpath("//input[@data-qa='card-number']");
    public By cvcInput = By.xpath("//input[@data-qa='cvc']");
    public By expirationMonth =By.xpath("//input[@data-qa='expiry-month']");
    public By expirationYear = By.xpath("//input[@data-qa='expiry-year']");
    public By payAndConfirmButton  =By.xpath("//button[@data-qa='pay-button']");

    public String headingOfPage(){

        return driver.findElement(headingElement).getText();
    }

    public void setPaymentInformation(PaymentData validPaymentData) throws InterruptedException {
        driver.findElement(nameOfCardInput).sendKeys(validPaymentData.getNameOfCard());
        driver.findElement(cardNumberInput).sendKeys(validPaymentData.getCardNumber());
        driver.findElement(cvcInput).sendKeys(validPaymentData.getCvcNumber());
        driver.findElement(expirationMonth).sendKeys(validPaymentData.getExpirationMonth());
        driver.findElement(expirationYear).sendKeys(validPaymentData.getExpirationYear());

    }

    public void clickPay(){
        driver.findElement(payAndConfirmButton).click();
    }

}
