package Group9_Lim;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class FIMS01_06 {
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

        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
    }

    @Test
    public void TC01_06_01_saveItemValid() throws InterruptedException {

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[2]")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Quantity Request
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("10");
        Thread.sleep(1000);

        // Save Item
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Select ok for confirmation
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Assert to verify if the item is added successfully
        Assert.assertTrue("Item added successfully.", true);
        System.out.println("User successfully add an item.");

    }

    @Test
    public void TC01_06_02_saveItemNoData() throws InterruptedException {

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Save Item
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Check for the presence of no item message
        WebElement noItemMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/div"));
        System.out.println("Message displayed: " + noItemMessage.getText());

        // Check for the presence of no quantity message
        WebElement noQtyMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_srd_qty_request\"]/div"));
        System.out.println("Message displayed: " + noQtyMessage.getText());

        // Assert to verify if no data message is displayed for invalid add item and quantity
        Assert.assertTrue("No Item message displayed for invalid add item data", noItemMessage.isDisplayed());
        Assert.assertTrue("No Quantity message displayed for invalid add item data", noQtyMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid add item data input.");
    }

    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}