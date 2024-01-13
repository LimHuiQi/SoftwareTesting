package Group9_Lim;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class FIMS01_01 {
    static WebDriver driver;

    @Before
    public void beforeTest() throws InterruptedException {
        // Set up WebDriver for Edge browser
        System.setProperty("webdriver.edge.driver","C:\\Users\\limhu\\OneDrive\\Desktop\\UUM\\Sem 5\\Software Testing & Quality Accurance\\EdgeDriver\\msedgedriver.exe" );
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
    public void FIMS01_01_01_searchStoreNameValid() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualStoreName = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedStoreName = "UUMIT";

        // Assert to verify if the actual store name contains the expected store name
        Assert.assertTrue("Actual store name contains expected store name", actualStoreName.contains(expectedStoreName));
        System.out.println("User successfully handled the scenario with valid data input.");
    }

    @Test
    public void FIMS01_01_02_searchStoreNameNoData() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Empty Store Name (No Data)
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("");
        Thread.sleep(1000);

        // Get Text From Page
        String actualStoreName = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();

        // Check for the presence of "Masukkan lagi 1 atau lebih huruf" message
        WebElement enterDataMessage = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li"));
        System.out.println("Message displayed: " + enterDataMessage.getText());

        // Assert to verify if "Masukkan lagi 1 atau lebih huruf" message is displayed for no data
        Assert.assertTrue("Masukkan lagi 1 atau lebih huruf message displayed for no data input", enterDataMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with no data.");
    }

    @Test
    public void FIMS01_01_03_searchStoreNameInvalidData() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Invalid Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("InvalidStore123");
        Thread.sleep(1000);

        // Check for the presence of "Tiada Data" message
        WebElement tiadaDataMessage = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li"));
        System.out.println("Message displayed: " + tiadaDataMessage.getText());

        // Assert to verify if "Tiada Data" message is displayed for invalid data
        Assert.assertTrue("Tiada Data message displayed for invalid store name data", tiadaDataMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid store name data input.");
    }


    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
