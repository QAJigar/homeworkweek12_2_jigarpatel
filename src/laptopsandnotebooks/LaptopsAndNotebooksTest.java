package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {
    String baseUrl = "http://tutorialsninja.com/demo/index.php";
    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }
    public void mouseHoverAndClick(String hover) {
        WebElement hoverElement = driver.findElement(By.xpath(hover));
        hoverElement.click();
    }
    //This method select value from element
    public void selectValueFromElement(By by, String value){
        Select option = new Select(driver.findElement(by));
        option.selectByValue(value);
}
    @Test
    //1. Test name verifyProductsPriceDisplayHighToLowSuccessfully()
    public void verifyProductsPriceDisplayHighToLowSuccessfully(){
        //1.1 Mouse hover on Laptops & Notebooks Tab.and click
        mouseHoverAndClick("//a[normalize-space()='Laptops & Notebooks']");
        //1.2 Click on “Show All Laptops & Notebooks”
        mouseHoverAndClick("//a[normalize-space()='Show AllLaptops & Notebooks']");
        //1.3 Select Sort By "Price (High > Low)"
       // clickOnElement(By.xpath("//*[@id='input-sort']/option[5]"));
        selectElement(By.xpath("//*[@id='input-sort']/option[5]"));
        //after sorting by price -high to low
        List<WebElement> afterSortingPrice = driver.findElements(By.xpath("(//p[@class='price'])"));
        //remove '$' symbol from price and convert string into double
        List<Double> afterSortingPriceList = new ArrayList<>();
        for (WebElement p : afterSortingPrice) {
            afterSortingPriceList.add(Double.valueOf(p.getText().split("\n")[0].replace("$", "").replace(",", "").trim()));
        }
        //1.4 Verify the Product price will arrange in High to Low order.
        Assert.assertEquals("Price is not sorted in high to low ", afterSortingPriceList, afterSortingPriceList);
    }
    @Test
    //2. Test name verifyThatUserPlaceOrderSuccessfully()
    public void verifyThatUserPlaceOrderSuccessfully(){
        //2.1 Mouse hover on Laptops & Notebooks Tab and click
        mouseHoverAndClick("//a[normalize-space()='Laptops & Notebooks']");
        //2.2 Click on “Show All Laptops & Notebooks”
        mouseHoverAndClick("//a[normalize-space()='Show AllLaptops & Notebooks']");
        //2.3 Select Sort By "Price (High > Low)"
        clickOnElement(By.xpath("//*[@id='input-sort']/option[5]"));
        clickOnElement(By.xpath("//span[normalize-space()='Currency']"));
        clickOnElement(By.xpath("//*[@id='form-currency']/div/ul/li[2]/button"));
        //2.4 Select Product “MacBook”
        clickOnElement(By.xpath("//img[@title='MacBook']"));
        //2.5 Verify the text “MacBook”
        Assert.assertEquals("Text not Match","MacBook", getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']")));
        //2.6 Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
        Assert.assertEquals("Message Not Displeyd","Success: You have added MacBook to your shopping cart!",getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).substring(0,54));
        //2.8 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        //2.9 Verify the text "Shopping Cart"
        Assert.assertEquals("Text not match", "Shopping Cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).substring(0,13));
        //2.10 Verify the Product name "MacBook"
        Assert.assertEquals("Text not Match","MacBook", getTextFromElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[2]/a")));
        //2.11 Change Quantity "2"
        driver.findElement(By.xpath("//input[@type='text'][@value='1']")).clear();
        driver.findElement(By.xpath("//input[@type='text'][@value='1']")).sendKeys("2");
        //2.12 Click on “Update” Tab
        clickOnElement(By.xpath("//i[@class='fa fa-refresh']"));
        //2.13 Verify the message “Success: You have modified your shopping cart!”
        Assert.assertEquals("Text no Match","Success: You have modified your shopping cart!",getTextFromElement(By.xpath("//*[@id='checkout-cart']/div[1]")).substring(0,46));
        //2.14 Verify the Total £737.45
        Assert.assertEquals("Text Not Match","£737.45",getTextFromElement(By.xpath("//*[@id='content']/form/div/table/tbody/tr/td[6]")));
        //2.15 Click on “Checkout” button
        clickOnElement(By.xpath("//*[@id='content']/div[3]/div[2]/a"));
        //2.16 Verify the text “Checkout”
        Assert.assertEquals("Text not match", "Checkout",getTextFromElement(By.xpath("//*[@id='content']/div[3]/div[2]/a")));
        //2.17 Verify the Text “New Customer”
        Assert.assertEquals("Text is not displayed", "New Customer", getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']")));
        //2.18 Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//*[@id='collapse-checkout-option']/div/div/div[1]/div[2]/label"));
        //2.19 Click on “Continue” tab
        clickOnElement(By.xpath("//*[@id='button-account']"));////input[@id='button-account']
        //2.20 Fill the mandatory fields
        sendTextToElement(By.id("input-payment-firstname"), "Jigar");
        sendTextToElement(By.id("input-payment-lastname"), "Patel");
        sendTextToElement(By.id("input-payment-email"), "soniyapatel67@gmail.com");
        sendTextToElement(By.name("telephone"), "07854329219");
        sendTextToElement(By.name("address_1"), "45,HalleY rd");
        sendTextToElement(By.name("city"), "London");
        sendTextToElement(By.name("postcode"), "E13 2GH");
        selectValueFromElement(By.id("input-payment-country"), "United Kingdom");
        selectValueFromElement(By.id("input-payment-zone"), "Greater London");
        //2.21 Click on “Continue” Button
        clickOnElement(By.xpath("//input[@id='button-guest']"));
        // 2.22 Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "Kindly place near by address");
        clickOnElement(By.xpath("//input[@id='button-shipping-method']"));
        // 2.23 Check the Terms & Conditions check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        // 2.24 Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));
        clickOnElement(By.xpath("//input[@id='button-confirm']"));
        //2.25 Verify the message “Warning: Payment method required!”
    }
}
