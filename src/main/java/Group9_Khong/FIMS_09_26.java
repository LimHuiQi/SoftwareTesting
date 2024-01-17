package Group9_Khong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

//Test the search function in the List of Application page for valid data.
public class FIMS_09_26 {
    static WebDriver driver;

    @Before
    public void beforeTest() throws InterruptedException {
        // Set up WebDriver for Edge browser
        System.setProperty("webdriver.edge.driver","C:\\Users\\khongxinqi\\STIW3034 Software Testing\\edgedriver_win64\\msedgedriver.exe" );
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Open the FIMS application
        driver.get("https://fimsclone.kerisi.my/");
        Thread.sleep(3000);

        // Input Username
        driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY4");
        Thread.sleep(1000);

        // Input Password
        driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys("qwertyuiop");
        Thread.sleep(1000);

        // Click Sign In
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        Thread.sleep(1000);

        // Select Side Menu
        driver.findElement(By.xpath("//*[@id=\"sideMenuLeft\"]/div[2]")).click();
        Thread.sleep(1000);

        // Click Portal
        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1000);

        // Click Stock Application
        driver.findElement(By.xpath("//*[@id=\"menu_id_2867\"]")).click();
        Thread.sleep(1000);

        // Click List Of Application
        driver.findElement(By.xpath("//*[@id=\"menu_id_2915\"]")).click();
        Thread.sleep(1000);

        // Assert to verify if the user is navigate to List of Application page successfully
        Assert.assertEquals("User navigate to List of Application page successfully", "Portal / Stock Application / List of Application", driver.getTitle().trim());
        System.out.println("User navigate to List of Application page successfully.");
    }

    @Test
    public void FIMS_09_26_searchAppValid() throws InterruptedException {
        // Click Search Input Field
        driver.findElement(By.xpath("//*[@id=\"dt_store_master_filter\"]/label")).click();
        Thread.sleep(1000);

        // Enter "008" in the Search input field
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[1]/label/input")).sendKeys("008");
        Thread.sleep(1000);

        // To check Post Cond: Get the actual application number from the element (table cell)
        WebElement appNoCell = driver.findElement(By.xpath("//*[@id=\"dt_store_master\"]/tbody/tr/td[2]"));
        String actualAppNo = appNoCell.getText();
        String expectedAppNo = "008";

        // Assert to verify if the actual application number contains the expected application number
        Assert.assertTrue("Actual application no contains expected application no", actualAppNo.contains(expectedAppNo));
        System.out.println("User successfully see the searched result with valid data input.");
    }

    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
