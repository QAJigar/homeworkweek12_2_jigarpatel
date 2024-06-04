package utilities;

import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {
    //this method clicks on element
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }
    public String getTextFromElement(By by){
        return  driver.findElement(by).getText();
    }
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }
    public void findElement(){
    }
    //this method clear value from input element
    public void clearTextFromInputField(By by) {
        driver.findElement(by).clear();
    }
    //this method getAttribute from element
    public String getAttributeFromElement(By by) {
        return driver.findElement(by).getAttribute("value");
    }
    //selectElement
    public void selectElement(By by){
        driver.findElement(by);
    }

    //this method select option from dropdown
    public void selectDropdownOptionByValue(By by, String value) {
        Select option = new Select(driver.findElement(by));
        option.selectByValue(value);
    }

    //this method mouse hover on element
    public void mouseHoverOnElement(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }
}
