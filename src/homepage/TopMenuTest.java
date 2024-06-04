package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class TopMenuTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php";
    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }
    public void selectMenu(String menu){
        WebElement menuElement = driver.findElement(By.xpath(menu));
        menuElement.click();
//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(By.xpath(menu))).click().build().perform();
    }
//1.1 create method with name "selectMenu" it has one parameter name "menu" of type string
    //1.2 This method should click on the menu whatever name is passed as parameter. Write the following Test:
    @Test
    //1. verifyUserShouldNavigateToDesktopsPageSuccessfully()
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully(){
        //1.1 Mouse hover on “Desktops” Tab and click
        mouseHoverOnElement(By.xpath("//a[normalize-space()='Desktops']"));
        //1.2 call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("//a[normalize-space()='Show AllDesktops']");
        //1.3 Verify the text ‘Desktops’
        Assert.assertEquals("Text Not Varify","Desktops",getTextFromElement(By.xpath("//ul[@class='breadcrumb']//a[contains(text(),'Desktops')]")));
    }
    @Test
    //2. verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully()
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully(){
        //2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverOnElement(By.xpath("//a[@class='dropdown-toggle'][normalize-space()='Laptops & Notebooks']"));
        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("//a[normalize-space()='Show AllLaptops & Notebooks']");
        //2.3 Verify the text ‘Laptops & Notebooks’
        Assert.assertEquals("Text Not Verify","Laptops & Notebooks",getTextFromElement(By.xpath("//h2[normalize-space()='Laptops & Notebooks']")));
    }
    @Test
    //3. verifyUserShouldNavigateToComponentsPageSuccessfully()
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){
        //3.1 Mouse hover on “Components” Tab and click
        mouseHoverOnElement(By.xpath("//a[normalize-space()='Components']"));
        //3.2 call selectMenu method and pass the menu = “Show All Components”
        selectMenu("//a[normalize-space()='Show AllComponents']");
        //3.3 Verify the text ‘Components’
        Assert.assertEquals("Text Not Verify", "Components",getTextFromElement(By.xpath("//h2[normalize-space()='Components']")));
    }
}
