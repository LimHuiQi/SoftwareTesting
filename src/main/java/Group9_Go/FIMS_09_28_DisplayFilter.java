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
import java.util.List;

public class FIMS_09_28_DisplayFilter {
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

        driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY4");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys("qwertyuiop");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        Thread.sleep(1000);
        WebElement sideMenu = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"sideMenuLeft\"]/div[2]")));
        sideMenu.click();
        Thread.sleep(1500);
        //1533 = Portal
        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        //*[@id="menu_id_2315"] = Advance Staff
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(1500);
        //*[@id="menu_id_2342"] = Activity Advance Application
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(1500);
        //*[@id="menu_id_1200"] = List of Activity Advance
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(2000);
        //*[@id="select2-dt_listOfActivityAdvance_length-mv-container"] = display
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[1]/label/span/span[2]/span")).click();
        Thread.sleep(2000);
        WebElement dropdownOptions = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul"));
        List<WebElement> options = dropdownOptions.findElements(By.tagName("li"));
        if (options.size() >= 2) {
            // Click on the second option
            options.get(1).click();
        } else {
            System.out.println("Dropdown does not contain enough options.");
        }
        Thread.sleep(3000);


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