package Group9_Lim;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class FIMS01_05 {
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

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Item Name
        driver.findElement(By.xpath("//*[@id=\"mdl_store_item\"]/span/span/span[1]/input")).sendKeys("FAIL");
        Thread.sleep(1000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[13]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span/span[2]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualItemName = driver.findElement(By.xpath("//*[@id=\"select2-sit_store_item_id-container\"]")).getText();
        String expectedItemName = "FAIL";

        // Assert to verify if the actual item name contains the expected item name
        Assert.assertTrue("Actual item name contains expected item name", actualItemName.contains(expectedItemName));
        System.out.println("User successfully handled the scenario with valid item input.");
    }

    @Test
    public void TC01_05_01_searchQtyValid() throws InterruptedException {

        // Input quantity
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("10");
        Thread.sleep(1000);

        // Get Text From Page for Quantity
        List<WebElement> actualQuantityText = driver.findElements(By.xpath("//*[@id=\"sit_qty_bal\"]"));

        // Check if the list is not empty
        if (!actualQuantityText.isEmpty()) {
            // Get the text from the first element in the list
            WebElement actualQuantityElement = actualQuantityText.get(0);
            String actualQuantityTextValue = actualQuantityElement.getText();

            // Convert the quantity values to integers
            int expectedQuantity = 10;
            int actualQuantity = Integer.parseInt(actualQuantityTextValue);

            System.out.println("Actual Quantity: " + actualQuantity);

            // Assert to verify if the actual quantity is valid
            Assert.assertTrue("Actual quantity is not less than or equal to expected quantity", actualQuantity <= expectedQuantity);
            Assert.assertTrue("Actual quantity is not greater than or equal to 1", actualQuantity >= 1);
            System.out.println("User successfully entered a valid quantity for the item.");
        } else {
            System.out.println("No element found for actual quantity.");
        }
    }


    @After
    public void afterTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
}
}