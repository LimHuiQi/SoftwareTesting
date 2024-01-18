package Group9_Pravin;

import com.google.common.collect.Ordering;
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
import java.util.stream.Collectors;

public class FIMS_09_23_sortItem {
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

        // Assert to verify if the user is logged in successfully
        Assert.assertEquals("User logged in successfully? ", "Portal / Stock Application / New Application", driver.getTitle().trim());
        System.out.println("User logged in successfully.");
    }

    @Test
    public void sortItem() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);


        // Wait for the table to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"dt_store_item\"]/tbody")));

        // Get all the cells in the Item column
        List<WebElement> itemCells = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[2]"));

        // Log the list of items before sorting
        List<String> originalItemOrder = itemCells.stream().map(WebElement::getText).toList();
        System.out.println("Original Item Order: " + originalItemOrder);

        // Select Item column to trigger sorting
        WebElement itemColumn = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/thead/tr/th[2]"));
        itemColumn.click();

        // Wait for the table to be refreshed after sorting
        wait.until(ExpectedConditions.stalenessOf(itemCells.get(0)));

        // Get the Item values after sorting
        itemCells = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[2]"));
        List<String> sortedItemList = itemCells.stream().map(WebElement::getText).collect(Collectors.toList());

        // Verify if the Item list is sorted in ascending order
        boolean isSorted = Ordering.natural().isOrdered(sortedItemList);

        // Assertion for the sorting result
        Assert.assertTrue("Not in order", isSorted);

        // Log the list of items after sorting
        System.out.println("Sorted Item Order: " + sortedItemList);

        System.out.println("The Store's Item is sorted in ascending order by item.");
    }


    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}

