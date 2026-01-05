package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.decorators.WebDriverDecorator;

public class TestCasesPage {

    WebDriver driver;

    public TestCasesPage(WebDriver driver){
        this.driver = driver;
    }

    public static String EXPECTED_HEADING = "TEST CASES";

    public By headingOfPage = By.xpath("//div[@class='col-sm-9 col-sm-offset-1']//h2/b");

    public String getHeadingOfPage(){
        return driver.findElement(headingOfPage).getText();
    }
}
