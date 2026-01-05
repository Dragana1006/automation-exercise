package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.JavaScriptHelper;
import utilities.WaitHelper;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPage {

    WebDriver driver;
    WaitHelper wait;
    JavaScriptHelper jsh;

    public static String EXPECTED_TITLE = "Automation Exercise - Checkout";
    public static String EXPECTED_HEADING = "Address Details";

    public  CheckoutPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WaitHelper(driver, 3);
        this.jsh = new JavaScriptHelper(driver);

    }

    private By addressDelivery = By.xpath("//ul[@id='address_delivery']//li");
    private By billingAddress = By.xpath("//ul[@id='address_invoice']//li");
    private By textArea = By.xpath("//textarea[@class='form-control']");
    private By placeOrderButton = By.xpath("//a[@href='/payment']");

    private final By deliveryFullName = By.xpath("//li[@class='address_firstname address_lastname']");
    private final By deliveryCompany = By.xpath("(//li[@class='address_address1 address_address2'])[1]");
    private final By deliveryAddress1 = By.xpath("(//li[@class='address_address1 address_address2'])[2]");
    private final By deliveryAddress2 = By.xpath("(//li[@class='address_address1 address_address2'])[3]");
    private final By deliveryCityStateZip = By.xpath("//li[@class='address_city address_state_name address_postcode']");
    private final By deliveryCountryName = By.xpath("//li[@class='address_country_name']");
    private final By deliveryPhoneNumber = By.xpath("//li[@class='address_phone']");

    private final By billingFullName = By.xpath("//li[@class='address_firstname address_lastname']");
    private final By billingCompany = By.xpath("(//li[@class='address_address1 address_address2'])[1]");
    private final By billingAddress1 = By.xpath("(//li[@class='address_address1 address_address2'])[2]");
    private final By billingAddress2 = By.xpath("(//li[@class='address_address1 address_address2'])[3]");
    private final By billingCityStateZip = By.xpath("//li[@class='address_city address_state_name address_postcode']");
    private final By billingCountry = By.xpath("//li[@class='address_country_name']");
    private final By billingPhoneNumber = By.xpath("//li[@class='address_phone']");
    private final By heading = By.xpath("//div[@class='step-one']//h2");

    public String getHeadingName(){
      return driver.findElement(heading).getText();
    }
    public String getDeliveryCompany() {
        return driver.findElement(deliveryCompany).getText();
    }

    public String getDeliveryFullName() {
        return driver.findElement(deliveryFullName).getText();
    }

    public String getDeliveryAddress1() {
        return driver.findElement(deliveryAddress1).getText();
    }

    public String getDeliveryAddress2() {
        return driver.findElement(deliveryAddress2).getText();
    }

    public String getDeliveryCityStateZip() {
        return driver.findElement(deliveryCityStateZip).getText();
    }
    public String getDeliveryCountryName(){
        return driver.findElement(deliveryCountryName).getText();
    }
    public String getDeliveryPhoneNumber(){
        return driver.findElement(deliveryPhoneNumber).getText();
    }

    public String getBillingCompany() {
        return driver.findElement(billingCompany).getText();
    }

    public String getBillingFullName() {
        return driver.findElement(billingFullName).getText();
    }

    public String getBillingAddress1() {
        return driver.findElement(billingAddress1).getText();
    }

    public String getBillingAddress2() {
        return driver.findElement(billingAddress2).getText();
    }

    public String getBillingCityStateZip() {
        return driver.findElement(billingCityStateZip).getText();
    }

    public String getBillingCountry() {
        return driver.findElement(billingCountry).getText();
    }

    public String getBillingPhoneNumber() {
        return driver.findElement(billingPhoneNumber).getText();
    }

    public List<String> getDeliveryAddressData() {
        List<String> delivery= new ArrayList<>();
        List<WebElement> deliveryData = driver.findElements(addressDelivery);
        for (WebElement data : deliveryData) {
            String text = data.getText();
            delivery.add(text);

        }
        return delivery;
    }

    public List<String> getBilling(){
        List<String> billing = new ArrayList<>();
        List<WebElement> billingAddressData = driver.findElements(billingAddress);
        for(WebElement data:billingAddressData){
           String dataText =  data.getText();
            billing.add(dataText);

        }
        return billing;
    }

    public void addComment(String comment) {
        WebElement textAreaField = driver.findElement(textArea);
        jsh.scrollUntilElementBecomeVisible(textAreaField);
        textAreaField.sendKeys(comment);
    }

    public void placeOrder(){
        WebElement placeOrder =  wait.waitForElementToBeClickable((placeOrderButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", placeOrder);
        placeOrder.click();

//        WebElement placeOrder =  wait.waitForElementToBeClickable(driver.findElement(placeOrderButton));
//    placeOrder.click();
    }


}
