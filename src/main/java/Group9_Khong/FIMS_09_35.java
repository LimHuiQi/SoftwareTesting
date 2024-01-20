package Group9_Khong;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

//Test the view function in the Authorized Receipting page.
public class FIMS_09_35 {
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
        driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY1");
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

        // Click Account Receivable
        driver.findElement(By.xpath("//*[@id=\"menu_id_1024\"]")).click();
        Thread.sleep(1000);

        // Click Authorized Receipting
        driver.findElement(By.xpath("//*[@id=\"menu_id_1952\"]")).click();
        Thread.sleep(1000);

        // Assert to verify if the user is navigate to Authorized Receipting page successfully
        Assert.assertEquals("User navigate to Authorized Receipting page successfully", "Account Receivable / Authorized Receipting", driver.getTitle().trim());
        System.out.println("UUser navigate to Authorized Receipting page successfully.");
    }

    @Test
    public void FIMS_09_35_viewReceipt() throws InterruptedException {
        // Click on the eye icon
        By eyeIconLocator = By.xpath("//*[@id=\"view\"]/i");
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(10));

        // Wait for the eye icon to be clickable and then click it
        WebElement eyeIcon = wait.until(ExpectedConditions.elementToBeClickable(eyeIconLocator));
        eyeIcon.click();

        // Post Cond: Assert to verify if the user is navigated to the Authorized Receipting Form details page
        Assert.assertEquals("User successfully navigated to Authorized Receipting Form details page? ", "Account Receivable / Authorized Receipting Form", driver.getTitle().trim());

        // Post Cond: Assert to check if the data for "Details" section is displayed
        WebElement DetailsElement = driver.findElement(By.xpath("//*[@id=\"cm_details\"]"));
        Assert.assertTrue("Data in Details section is displayed", DetailsElement.isDisplayed());

        // Post Cond: Assert to check if the data for "Authorized Staff" section is displayed
        WebElement authorizedElement = driver.findElement(By.xpath("//*[@id=\"dt_authorized_container\"]"));
        Assert.assertTrue("Data in Authorized Staff section is displayed", authorizedElement.isDisplayed());

        // Post Cond: Assert to check if the data for "Process Flow" section is displayed
        WebElement processFlowElement = driver.findElement(By.xpath("//*[@id=\"dt_processFlow_container\"]"));
        Assert.assertTrue("Data in Process Flow section is displayed", processFlowElement.isDisplayed());

        System.out.println("Data for 'Details', 'Authorized Staff', and 'Process Flow' is displayed.");
    }

    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
