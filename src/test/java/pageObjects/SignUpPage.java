package pageObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.LoginData;
import utilities.SignUpData;
import utilities.WaitHelper;


public class SignUpPage {

    WebDriver driver;
    WaitHelper wait;

    public By inputName = By.xpath("//input[@data-qa='signup-name']");
    public By inputEmail = By.xpath("//input[@data-qa='signup-email']");
    public By signUpButton = By.xpath("//button[@data-qa ='signup-button']");
    public By emailInput = By.xpath( "//input[@data-qa='login-email']");
    public By passwordInput = By.xpath( "//input[@data-qa='login-password']");
    public By signupLoginButton = By.xpath( "//button[@data-qa='login-button']");
    public By errorMessage = By.xpath("//p[text()='Your email or password is incorrect!']");
    public  By errorForEmailAddress = By.xpath("//p[text()='Email Address already exist!']");
    public By headingOfLogin = By.xpath("//div[@class='login-form']//h2");
    public By headingOfSignUp = By.xpath("//div[@class='signup-form']/h2");

    public static String EXPECTED_LOGIN_HEADING = "Login to your account";
    public static String EXPECTED_SIGNUP_HEADING = "New User Signup!";
    public static String EXPECTED_ERROR_MESSAGE = "Your email or password is incorrect!";
    public static String EXPECTED_ERROR_FOR_USING_EXISTING_EMAIL = "Email Address already exist!";



    public SignUpPage (WebDriver driver){
        this.driver =  driver;
        wait = new WaitHelper(driver, 5);

    }
    public String getHeadingForLogin(){
       return driver.findElement(headingOfLogin).getText();
    }

    public String getHeadingForSignUp(){
        return driver.findElement(headingOfSignUp).getText();

    }
    public String titleOfPageDisplayed(){
        return driver.getTitle();
    }

    public void setDataForSignUp(){

        String name = RandomStringUtils.randomAlphabetic(8);
        driver.findElement(inputName).sendKeys(name);

        String email = RandomStringUtils.randomAlphabetic(8) +"@gmail.com";
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void clickSignUpButton(){

       driver.findElement(signUpButton).click();
    }

    public String getSignUpName(){
        return driver.findElement(inputName).getAttribute("value");

    }
    public String getSignUpEmail(){
        return driver.findElement(inputEmail).getAttribute(("value"));
    }

    public void fillLoginForm(LoginData loginData){
        driver.findElement(emailInput).sendKeys(loginData.getEmail());
        driver.findElement(passwordInput).sendKeys(loginData.getPassword());
    }

    public void clickLogin(){
        driver.findElement(signupLoginButton).click();
    }


    public void useExistingEmailForRegistration(LoginData login){
        driver.findElement(inputName).sendKeys("Test Name");
        driver.findElement(inputEmail).sendKeys(login.getEmail());
    }


    public String getErrorMessage(){
        return driver.findElement(errorMessage).getText();
    }

    public String getErrorForEmailAddress(){
        return driver.findElement(errorForEmailAddress).getText();
    }



}
