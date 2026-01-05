package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.TableHelper;
import utilities.WaitHelper;
import java.util.List;
import java.util.ArrayList;


public class ShoppingCartPage {

    WebDriver driver;
    WaitHelper wait;
    TableHelper tableHelper;

    public ShoppingCartPage(WebDriver driver){
        this.driver = driver;
        wait = new WaitHelper(driver, 10);
        tableHelper = new TableHelper(driver);

    }

    public  static String EXPECTED_TITLE = "Automation Exercise - Checkout";


    private By subscriptionHeading = By.xpath("//h2[text() ='Subscription']");
    private By subscribeEmailInput = By.id("susbscribe_email");
    private By submitButton = By.id("subscribe");
    private By successMessage = By.xpath("//input[@name ='csrfmiddlewaretoken']");
    private By cartProductName = By.xpath("//td[@class='cart_description']//a");
    private By cartHeader = By.xpath("//tr[@class='cart_menu']//td");
    private By tableRows = By.xpath("//table[@id='cart_info_table']//tbody/tr");
    private By proceedToCheckoutBtn = By.xpath("//a[@class='btn btn-default check_out']");
    private By registerLoginLink =  By.xpath("//u[text()='Register / Login']");
    private By deleteButton = By.xpath("//a[@class='cart_quantity_delete']");
    private By productsInCart = By.xpath("//table[@id='cart_info_table']//tbody/tr");
    private By checkoutPopUp = By.xpath("//div[@class='modal-content']");

    public String getShoppingCartTitle(){
        return driver.getTitle();
    }

    public boolean shoppingCartBreadcrumbDisplayed(String name) {
        List<WebElement> breadcrumbsElement = driver.findElements(By.xpath("//ol[@class='breadcrumb']//li"));
        for (WebElement element : breadcrumbsElement) {
            String breadcrumbText = element.getText();
            if (breadcrumbText.equalsIgnoreCase(name)) {
                return element.isDisplayed();

            }
        }
        return false;
    }
    public String scrollToTheBottomOfThePage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        return driver.findElement(subscriptionHeading).getText();
    }


    public List<String> getCartProductNames(String headerName){
        int indexDescription = -1;
        List<WebElement> headers =  driver.findElements(cartHeader);
        List<String> cartProductNames = new ArrayList<>();

        for(int h= 0; h< headers.size();h++){
            String headerText = headers.get(h).getText();
            if(headerText.equalsIgnoreCase(headerName)){
                indexDescription = h+1;
            }
        }
        List<WebElement> rows = driver.findElements(tableRows);
        for(WebElement row: rows){
            WebElement cell =  row.findElement(By.xpath("./td["+indexDescription+"]//h4/a"));
            String nameOfProduct = cell.getText();
            cartProductNames.add(nameOfProduct);
        }
        return cartProductNames;

    }

    public List<String> getProductQuantity(String headerName){
        int indexQuanity = -1;
        boolean found = false;
        List<WebElement> headers =  driver.findElements(cartHeader);
        List<String> productQuantity = new ArrayList<>();
        for(int q = 0; q< headers.size();q++){
           String headerText =  headers.get(q).getText();
           if(headerText.equalsIgnoreCase(headerName)){
               indexQuanity = q+1;
               found = true;
               break;
           }
        }
        if(!found) {
            System.out.println("Expected header name of column " + headerName + " doesn't found");
        }
        List<WebElement> rows = driver.findElements(tableRows);
        for(WebElement row:rows){
           WebElement qtyField =  row.findElement(By.xpath(".//td["+indexQuanity+"]"));
           String value = qtyField.getText();
           productQuantity.add(value);
        }
        return productQuantity;
    }

    public void proceedToCheckout(){
        driver.findElement(proceedToCheckoutBtn).click();
    }

    public boolean checkoutPopUpDisplayed(){
        return driver.findElement(checkoutPopUp).isDisplayed();
    }

    public void registerLoginLink(){
        driver.findElement(registerLoginLink).click();
    }

    public void deleteItem(String deleteProduct) throws InterruptedException{
        List<WebElement> rows = driver.findElements(tableRows);
        for(WebElement row:rows){
          String name = row.findElement(By.xpath(".//td[2]//h4/a")).getText();
          if (name.equalsIgnoreCase(deleteProduct)){
             WebElement deleteBtn =  row.findElement(By.xpath(".//a[@class='cart_quantity_delete']"));
              deleteBtn.click();
              wait.waitUntilElementDisappearFromPage(row);
          }
      }
    }
}
