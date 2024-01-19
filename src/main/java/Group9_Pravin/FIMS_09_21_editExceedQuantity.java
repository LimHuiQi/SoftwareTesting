package Group9_Pravin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class FIMS_09_21_editExceedQuantity {
    static WebDriver driver;

    @Before
    public void beforeTest() throws InterruptedException {
        // Set up WebDriver for Edge browser
        System.setProperty("webdriver.edge.driver", "C:\\Users\\intgo\\OneDrive\\Documents\\Software Testing\\Test\\WebDriver\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();

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
    public void editExceedQuantity() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("BENDAHARI");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(3000);

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
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("1000");
        Thread.sleep(1000);

        // Select Save button
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Check to see if "Max +++" message is displayed
        WebElement maxNumberMessage = null;
        try {
            maxNumberMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_srd_qty_request\"]/div"));
            System.out.println("Invalid message displayed: " + maxNumberMessage.getText());
        } catch (NoSuchElementException e) {
            // Log a custom message indicating absence of the element
            System.out.println("Element not found: " + e.getMessage());
        }

        // Assert to verify if "Max +++" message is displayed for exceeding available quantity
        Assert.assertTrue("Max number message did not display after entering number exceeding Quantity Available of the item in Store's Item",
                maxNumberMessage != null && maxNumberMessage.isDisplayed());
        System.out.println("Successfully confirming the invalid input.");
    }


    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
