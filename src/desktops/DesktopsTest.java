package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @Test
    //1.Test name verifyProductArrangeInAlphaBaticalOrder()
    public void verifyProductArrangeInAlphaBaticalOrder() {
        //1.1 Mouse hover on Desktops Tab.and click
        mouseHoverOnElement(By.xpath("//a[normalize-space()='Desktops']"));
        //1.2 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));
        //product list before filtering
        List<WebElement> prodcutList = driver.findElements(By.xpath("//h4//a"));
        List<String> productNames = new ArrayList<>();
        for (WebElement p : prodcutList) {
            productNames.add(p.getText());
        }
        Collections.reverse(productNames);
        //1.3 Select Sort By position "Name: Z to A"
        selectElement(By.xpath("//select[@id='input-sort']"));
        selectElement(By.xpath("//*[@id='input-sort']/option[3]"));
        // 1.4 Verify the Product will arrange in Descending order.
        List<WebElement> productListAfterFilter = driver.findElements(By.xpath("//h4//a"));
        List<String> productNamesAfterFilter = new ArrayList<>();
        for (WebElement p : productListAfterFilter) {
            productNamesAfterFilter.add(p.getText());
        }
        Assert.assertEquals("Products are not in descending order", productNames, productNames);
    }

    @Test
    //2. Test name verifyProductAddedToShoppingCartSuccessFully()
    public void verifyProductAddedToShoppingCartSuccessFully() {
        //2.1 Mouse hover on Currency Dropdown and click
        mouseHoverOnElement(By.xpath("//span[normalize-space()='Currency']"));
        //2.2 Mouse hover on £Pound Sterling and click
        selectElement(By.xpath("//*[@id='form-currency']/div/ul/li[2]/button"));
        //2.3 Mouse hover on Desktops Tab.
        clickOnElement(By.xpath("//a[normalize-space()='Desktops']"));
        //2.4 Click on “Show All Desktops”
        clickOnElement(By.xpath("//a[normalize-space()='Show AllDesktops']"));
        //2.5 Select Sort By position "Name: A to Z"
        selectElement(By.xpath("//select[@id='input-sort']"));
        selectElement(By.xpath("//*[@id='input-sort']/option[2]"));
        //2.6 Select product “HP LP3065”
        selectElement(By.xpath("//img[@title='HP LP3065']"));
        //2.7 Verify the Text "HP LP3065"
        Assert.assertEquals("Text Not Match", "HP LP3065", getTextFromElement(By.xpath("//a[normalize-space()='HP LP3065']")));
        //2.8 Select Delivery Date "2023-11-27"
        String year = "2023";
        String month = "November";
        String date = "27";
        driver.findElement(By.xpath("//i[@class='fa fa-calendar']")).click(); // Open the calendar
        while (true) {
            String monthAndYear = getTextFromElement(By.cssSelector("div[class='datepicker-days'] th[class='picker-switch']"));
            System.out.println(monthAndYear);
            String[] a = monthAndYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equals(month) && yer.equals(year)) {
                break;
            } else {
                clickOnElement(By.cssSelector("div[class='datepicker-days'] th[class='next']"));
            }
        }

        // Select the Date
        List<WebElement> allDates = driver.findElements(By.xpath("//tbody//tr/td[@class='day']"));
        for (WebElement dt : allDates) {
            if (dt.getText().equals(date)) {
                dt.click();
                break;
            }
            //2.9.Enter Qty "1” using Select class.
            driver.findElement(By.id("input-quantity")).clear();
            sendTextToElement(By.id("input-quantity"), "1");
            //2.10 Click on “Add to Cart” button
            clickOnElement(By.xpath("//button[@id='button-cart']"));
            //2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
            Assert.assertEquals("Message Not Displeyd", "Success: You have added HP LP3065 to your shopping cart!", getTextFromElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).substring(0, 56));
            //2.12 Click on link “shopping cart” display into success message
            clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
            //2.13 Verify the text "Shopping Cart"
            Assert.assertEquals("Text not match", "Shopping Cart", getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]")).substring(0, 13));
            //2.14 Verify the Product name "HP LP3065"
            Assert.assertEquals("Text not Match", "HP LP3065", getTextFromElement(By.xpath("/html[1]/body[1]/div[2]/div[1]/div[1]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/a[1]")));
            //2.15 Verify the Delivery Date "2023-11-27"
            String actualDate = getAttributeFromElement(By.xpath("//small[contains(text(),'Delivery Date:')]")).replace("Delivery Date:", "").trim();
            Assert.assertEquals("Data is incorrect", "2023-11-27", actualDate);
            //2.16 Verify the Model "Product21"
            Assert.assertEquals("Text not Match", "Product 21", getTextFromElement(By.xpath("//td[normalize-space()='Product 21']")));
            //2.17 Verify the Total "£74.73"
            Assert.assertEquals("Text not Match", "£74.73", getTextFromElement(By.xpath("//tbody//tr//td[6]")));
        }
    }
}

