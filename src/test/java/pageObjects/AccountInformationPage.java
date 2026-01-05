package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.PersonalInformation;
import utilities.WaitHelper;

import java.util.List;

public class AccountInformationPage{
    WebDriver driver;
    WaitHelper wait;

    public AccountInformationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WaitHelper(driver, 3);
    }

    public static String EXPECTED_HEADING = "ENTER ACCOUNT INFORMATION";

    @FindBy(xpath = "//input[@type='radio']")
    List<WebElement> radioButtons;
    @FindBy(id="name") WebElement nameInput;
    @FindBy(id="email") WebElement emailInput;
    @FindBy(id = "password") WebElement passwordInput;


    private final By heading = By.xpath("//b[normalize-space()='Enter Account Information']");
    private final By daysDropdown = By.id("days");
    private final By monthsDropdown = By.id("months");
    private final By yearsDropdown = By.id("years");
    private final By firstNameInput = By.id("first_name");
    private final By lastNameInput = By.id("last_name");
    private final By companyInput = By.id("company");
    private final By address1Input = By.id("address1");
    private final By address2Input = By.id("address2");
    private final By countryDropdown = By.id("country");
    private final By stateInput = By.id("state");
    private final By cityInput = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobilePhone = By.id("mobile_number");
    private final By createAccountBtn = By.xpath("//button[@data-qa='create-account']");

    public String getHeadingOfPage(){
        return driver.findElement(heading).getText();
    }

    public boolean selectUserTitle(String radioName) {
        for (WebElement radio : radioButtons) {
            wait.waitForVisibilityOfAllElement(radioButtons);
            String value = radio.getAttribute("value");
            if(radioName.contains(value)){
                radio.click();
                return radio.isSelected();
            }
        }
        return false;
    }

    public String getName() {
        wait.waitForVisibilityOf(nameInput);
        return nameInput.getAttribute("value");
    }

    public String getEmail(){
        wait.waitForVisibilityOf(emailInput);
        return nameInput.getAttribute("value");
    }

    public void fillAccountInformation(String password) throws InterruptedException {
        passwordInput.sendKeys(password);
        WebElement dayElement = driver.findElement(daysDropdown);
        new Select(dayElement).selectByIndex(6);
        WebElement monthElement = driver.findElement(monthsDropdown);
        new Select(monthElement).selectByIndex(8);
        WebElement yearElement = driver.findElement(yearsDropdown);
        new Select(yearElement).selectByIndex(6);
    }

    public void fillAddressInformation(PersonalInformation personalInformation){
        driver.findElement(firstNameInput).sendKeys(personalInformation.getFirstName());
        driver.findElement(lastNameInput).sendKeys(personalInformation.getLastName());
        driver.findElement(companyInput).sendKeys(personalInformation.getCompany());
        driver.findElement(address1Input).sendKeys(personalInformation.getAddress());
        driver.findElement(address2Input).sendKeys(personalInformation.getAddress2());
        driver.findElement(stateInput).sendKeys(personalInformation.getState());
        driver.findElement(cityInput).sendKeys(personalInformation.getCity());
        driver.findElement(zipcode).sendKeys(personalInformation.getZipcode());
        driver.findElement(mobilePhone).sendKeys(personalInformation.getMobileNumber());

    }

    public String selectCountry(String countryName){
        WebElement countryElement = driver.findElement(countryDropdown);
        Select countrySelect = new Select(countryElement);
        countrySelect.selectByVisibleText(countryName);
        return countrySelect.getFirstSelectedOption().getText();
    }

    public void clickCreateAccount(){
        driver.findElement(createAccountBtn).click();
    }






}
