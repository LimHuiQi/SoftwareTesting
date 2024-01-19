package Group9_Ridzwan;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FIMS_09_46 {
    static WebDriver driver;

    @Before
    public void beforeTest() throws InterruptedException {
        // Set up WebDriver for Edge browser
        System.setProperty("webdriver.edge.driver","C:\\Selenium Webdriver\\driver\\edgedriver\\msedgedriver.exe" );
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Open the FIMS application
        driver.get("https://fimsclone.kerisi.my/");
        Thread.sleep(3000);

        // Input Email
        driver.findElement(By.xpath("/html/body/div/div[2]/form/div[2]/input")).sendKeys("ENTRY4");
        Thread.sleep(1000);

        // Input Password
        driver.findElement(By.xpath("/html/body/div/div[2]/form/div[4]/input")).sendKeys("qwertyuiop");
        Thread.sleep(1000);

        // Click Login
        driver.findElement(By.xpath("/html/body/div/div[2]/form/input")).click();
        Thread.sleep(1000);

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[1]/a")).click();
        Thread.sleep(1000);

        // Assert to verify if the user is logged in successfully
        Assert.assertEquals("User logged in successfully? ", "Portal / Petty Cash / New Application", driver.getTitle().trim());
        System.out.println("User logged in successfully.");
    }

    @Test
    public void FIMS_09_46_searchValidInput() throws InterruptedException {

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[2]")).click();
        Thread.sleep(1000);

        System.out.println("User successfully handled the scenario with valid data input.");
    }

    @Test
    public void FIMS_09_46_searchInvalidInput() throws InterruptedException {

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("Pusat Sukan");
        Thread.sleep(1000);

        // Check for the presence of "Tiada Data" message
        WebElement noDataMessage = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li"));
        System.out.println("Message displayed: " + noDataMessage.getText());

        // Assert to verify if "Tiada Data" message is displayed for invalid data
        Assert.assertTrue("Tiada Data message displayed for invalid store name data", noDataMessage.isDisplayed());
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
