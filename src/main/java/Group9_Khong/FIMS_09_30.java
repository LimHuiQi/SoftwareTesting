package Group9_Khong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

//Test the view function in the List of Application page.
public class FIMS_09_30 {
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

        // Assert to verify if the user is logged in successfully
        Assert.assertEquals("User logged in successfully? ", "Portal / Stock Application / List of Application", driver.getTitle().trim());
        System.out.println("User logged in successfully.");
    }

    @Test
    public void FIMS_09_30_viewApp() throws InterruptedException {
        // Click eye icon
        driver.findElement(By.xpath("//*[@id=\"dt_store_master\"]/tbody/tr[1]/td[9]/a[1]")).click();
        Thread.sleep(1000);

        // Assertion to check if the data for "Requisition" section is displayed
        WebElement requisitionElement = driver.findElement(By.xpath("//*[@id=\"requisition\"]/div[2]"));
        Assert.assertTrue("Requisition data is not displayed", requisitionElement.isDisplayed());

        // Assertion to check if the data for "Store's Item" section is displayed
        WebElement storeItemElement = driver.findElement(By.xpath("//*[@id=\"dt_store_item_container\"]"));
        Assert.assertTrue("Store's Item data is not displayed", storeItemElement.isDisplayed());

        // Assertion to check if the data for "Application Status" section is displayed
        WebElement appStatusElement = driver.findElement(By.xpath("//*[@id=\"dt_application_status_container\"]"));
        Assert.assertTrue("Application Status data is not displayed", appStatusElement.isDisplayed());

        System.out.println("Data for 'Requisition', 'Store's Item', and 'Application Status' is displayed.");
    }

    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }


}
