package Group9_Lim;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class FIMS01_03 {
    static WebDriver driver;

    @Before
    public void beforeTest() throws InterruptedException {
        // Set up WebDriver for Edge browser
        System.setProperty("webdriver.edge.driver", "C:\\Users\\limhu\\OneDrive\\Desktop\\UUM\\Sem 5\\Software Testing & Quality Accurance\\EdgeDriver\\msedgedriver.exe");
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

        // Select Side Menu
        driver.findElement(By.xpath("//*[@id=\"sideMenuLeft\"]/div[2]")).click();
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

        // Assert to verify if the user is logged in successfully
        Assert.assertEquals("User logged in successfully? ", "Portal / Stock Application / New Application", driver.getTitle().trim());
        System.out.println("User logged in successfully.");

    }
    @Test
    public void TC01_03_01_clearData() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        String expectedStoreName = "UUMIT";
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(expectedStoreName);
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualStoreName = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();

        // Assert to verify if the actual store name contains the expected store name
        Assert.assertTrue("Actual store name contains expected store name", actualStoreName.contains(expectedStoreName));
        System.out.println("User successfully handled the scenario with valid data input.");

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Assert to verify if the store's list is displayed after search
        WebElement storeList = driver.findElement(By.xpath("//*[@id=\"dt_store_item_container\"]/div[1]"));
        Assert.assertTrue("Store's list is displayed after search", storeList.isDisplayed());
        System.out.println("User can see the store's list after clicking the search button.");

        // Click Clear button
        driver.findElement(By.xpath("//*[@id=\"requisition\"]/div[3]/button[1]")).click();
        Thread.sleep(1000);

        // Assert to verify if the store's list is cleared after clicking the clear button
        WebElement emptyStoreList = driver.findElement(By.xpath("//*[@id=\"dt_store_item_container\"]/div[1]"));
        Assert.assertFalse("Store's list is cleared after clicking the clear button", emptyStoreList.isDisplayed());
        System.out.println("User successfully clicked the clear button, and the store's list is cleared.");
    }


    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}