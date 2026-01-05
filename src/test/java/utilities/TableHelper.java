package utilities;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableHelper {

    WebDriver driver;

    public TableHelper(WebDriver driver){
       this.driver = driver;
    }

    private By cartHeader = By.xpath("//tr[@class='cart_menu']//td");
    private By tableRows = By.xpath("//table[@id='cart_info_table']//tbody/tr");


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
}
