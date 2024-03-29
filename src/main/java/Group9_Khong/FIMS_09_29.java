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

//Test the pagination function in the List of Application page.
public class FIMS_09_29 {
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
    public void FIMS_09_29_pagination() throws InterruptedException {
        // Click triangle “next” icon
        driver.findElement(By.xpath("//*[@id=\"dt_store_master_next\"]/a")).click();
        Thread.sleep(1000);

        // Check Post Cond: the list of applications for the next page is displayed
        List<WebElement> nextPageApp = driver.findElements(By.xpath("//*[@id=\"dt_store_master\"]/tbody/tr[1]/td[1]"));

        // Assertion to verify that applications are displayed on the next page
        Assert.assertTrue("The list of applications for the next page is displayed", !nextPageApp.isEmpty());
        System.out.println("The list of applications for the next page is displayed.");
    }

    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
