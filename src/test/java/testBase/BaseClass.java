package testBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    protected WebDriver driver;
    public Properties p;

    @BeforeMethod
    @Parameters({"browser"})
    public void setUp (String br) throws IOException {

        FileInputStream file = new FileInputStream("/Users/dragana/IdeaProjects/AutomationExercise/src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        switch ((br.toLowerCase())){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case  "edge":
                driver = new EdgeDriver();
                break;
            default:{
                System.out.println("Invalid browser");
                return;
            }


        }

        driver.get("https://automationexercise.com/");
        driver.manage().window().maximize();


    }

    public void clearAllCookies() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }
//
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }


}
