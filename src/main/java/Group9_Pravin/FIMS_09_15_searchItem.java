package Group9_Pravin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class FIMS_09_15_searchItem {
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
    public void searchItem() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(5000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);

        // Input Search Item (Store's Item)
        driver.findElement(By.xpath("//*[@id=\"dt_store_item_filter\"]/label/input")).sendKeys("PISAU");
        Thread.sleep(1000);

        // Get Text From Page (Store's Item [Item])
        String actualSearchItem = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[2]")).getText();
        String expectedSearchItem = "PISAU";

        // Assert to verify if the searched store item matches the expected store item
        Assert.assertTrue("The searched item do not match with the expected store item", actualSearchItem.contains(expectedSearchItem));
        System.out.println("Successfully searched for Item!");
    }


    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
