package Group9_Pravin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FIMS_09_20_editQuantityRequest {
    static WebDriver driver;
    static WebDriverWait wait; // Declare WebDriverWait

    @Before
    public void beforeTest() throws InterruptedException {
        // Set up WebDriver for Edge browser
        System.setProperty("webdriver.edge.driver", "C:\\Users\\intgo\\OneDrive\\Documents\\Software Testing\\Test\\WebDriver\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait with a timeout of 10 seconds


        // Open the FIMS application
        driver.get("https://fimsclone.kerisi.my/");
        Thread.sleep(3000);

        // Input Email
        driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY4");
        Thread.sleep(1000);

        // Input Password
        driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys("qwertyuiop");
        Thread.sleep(1000);

        // Click Login
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("//*[@id=\"menu_id_2867\"]")).click();
        Thread.sleep(1000);

        // Select New Application
        driver.findElement(By.xpath("//*[@id=\"menu_id_2868\"]")).click();
        Thread.sleep(1000);

        // Assert to verify if the user successfully access the correct page
        Assert.assertEquals("Checking if user at the correct page", "Portal / Stock Application / New Application", driver.getTitle().trim());
        System.out.println("User properly accessed to the New Application page!");
    }

    @Test
    public void editQuantityRequest() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("BENDAHARI");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(5000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);

        // Select Edit button
        driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[5]/a[1]/i")).click();
        Thread.sleep(3000);

        // Select Quantity Request textbox
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).click();
        Thread.sleep(1000);

        // Clear number
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).clear();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("5");
        Thread.sleep(1000);

        // Select Save button
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Select 'Ok' button
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Wait for the table update using WebDriverWait
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr"), 1));

        // Assertion for the presence of the keyword in the table
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr"));
        int rowCount = rows.size();
        Assert.assertEquals("Expected one row in the table after the edit", 1, rowCount);

        // Get Text for Quantity Request From Page
        WebElement quantityRequestElement = rows.get(0).findElement(By.xpath("./td[3]"));
        String actualEditQuantityRequest = quantityRequestElement.getText();
        String expectedEditQuantityRequest = "5";

        // Assert to verify if quantity request has been changed
        Assert.assertEquals("Edited Quantity Request does not match with the expected Quantity Request", expectedEditQuantityRequest, actualEditQuantityRequest);
        System.out.println("Successfully edited the Quantity Request!");

    }


    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
