package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

   WebDriver driver;
   JavascriptExecutor js;

   public JavaScriptHelper(WebDriver driver){
       this.driver = driver;
       this.js = (JavascriptExecutor) driver;

   }

   public void scrollToTheBottom(){
       js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

   }
   public void scrollUntilElementBecomeVisible(WebElement element){
       js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

   }

    public String getElementText(WebElement element) {
        return ((String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].textContent;", element))
                .trim();
    }
}
