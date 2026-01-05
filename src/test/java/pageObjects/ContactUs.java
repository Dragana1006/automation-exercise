package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ContactUsData;


public class ContactUs {

    WebDriver driver;

    public ContactUs(WebDriver driver){
        this.driver = driver;
    }

    private By formHeading = By.xpath("//h2[normalize-space()='Get In Touch']");
    private By nameInput = By.xpath("//input[@data-qa = 'name']");
    private By emailInput = By.xpath("//input[@data-qa = 'email']");
    private By subjectInput = By.xpath("//input[@data-qa = 'subject']");
    private By messageInput = By.xpath("//textarea[@data-qa ='message']");
    private By uploadFile = By.xpath("//input[@name='upload_file']");
    private By submitButton = By.xpath("//input[@data-qa='submit-button']");
    private By successMessage = By.xpath("//div[@class='status alert alert-success']");
    private By homeButton = By.cssSelector(".btn.btn-success");

    public static String EXPECTED_FORM_HEADING = "GET IN TOUCH";
    public static String EXPECTED_SUCCESS_MESSAGE = "Success! Your details have been submitted successfully.";


    public String getHeading(){
      return  driver.findElement(formHeading).getText();
    }

    public void setContactData(ContactUsData contact){
        driver.findElement(nameInput).sendKeys(contact.getName());
        driver.findElement(emailInput).sendKeys(contact.getEmail());
        driver.findElement(subjectInput).sendKeys(contact.getSubject());
        driver.findElement(messageInput).sendKeys(contact.getMessage());
    }

    public void uploadFile(){
        String filePath = System.getProperty("user.dir") + "/src/test/resources/uploads/Products.xlsx";
        driver.findElement(uploadFile).sendKeys(filePath);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public void clickHomeButton(){
        driver.findElement(homeButton).click();
    }

}
