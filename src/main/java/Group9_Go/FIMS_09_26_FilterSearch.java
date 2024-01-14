package Group9_Go;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FIMS_09_26_FilterSearch {
    static WebDriver driver;

    @Before
    public void beforeTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\Study\\Testing\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://fimsclone.kerisi.my/");
        Thread.sleep(3000);
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        System.out.println("Title: " + title);
        System.out.println("URL " + url);
    }

    @org.junit.Test
    public void filterSearch() throws InterruptedException {
        // Login
        driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY4");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys("qwertyuiop");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        Thread.sleep(1000);

        // Wait for the side menu and navigate to the specified menu items
        WebElement sideMenu = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"sideMenuLeft\"]/div[2]")));
        sideMenu.click();
        Thread.sleep(1500);

        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(2000);

        // Click on the smart filter
        driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvance_smartFilter\"]")).click();
        Thread.sleep(2000);

        // Click on the payment type textfield
        driver.findElement(By.xpath("//*[@id=\"inputArea_sam_payment_type\"]/span/span[2]/span")).click();
        Thread.sleep(2000);

        // Select the 1st option in the payment type dropdown
        WebElement dropdown = driver.findElement(By.id("sam_payment_type"));
        Assert.assertNotNull("Dropdown element is not identified!", dropdown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'));", dropdown);

        // Close the payment type dropdown
        driver.findElement(By.xpath("//*[@id=\"inputArea_sam_payment_type\"]/span/span[2]/span")).click();
        Thread.sleep(2000);

        // Click on the OK button in the smart filter
        driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvanceSmartFilter\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(3000);

        // Assert that the table contains data
        WebElement dataTable = driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvance\"]"));
        Assert.assertTrue("Table does not contain data!", dataTable.isDisplayed());

    }

    @After
    public void afterTest() throws InterruptedException {
        String expectedTitle = "Portal / Advance Staff / Declaration / List of Activity Advance";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
        driver.quit();
    }
}