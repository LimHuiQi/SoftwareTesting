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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//Test the "save and submit" function in the Authorized Receipting Form on the Authorized Receipting page.
public class FIMS_09_34 {
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
    public void FIMS_09_34_saveSubmitReceipt() throws InterruptedException {
        // Click “+New” button with JavascriptExecutor
        WebElement newButton = driver.findElement(By.xpath("//*[@id=\"dt_listing_container\"]/div[3]/a"));

        // Scroll into view before clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newButton);

        // Click using JavascriptExecutor
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newButton);

        // Click “Collection Type” dropdown menu
        driver.findElement(By.xpath("//*[@id=\"inputArea_are_purposed_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Click “GENERAL” from the submenu displayed
        driver.findElement(By.xpath("//*[@id=\"are_purposed_code\"]/option[3]")).click();
        Thread.sleep(1000);

        // Find the "Save & Submit" button
        WebElement saveSubmitButton = driver.findElement(By.xpath("//*[@id=\"entrySaveSubmit\"]"));

        // Click the "Save & Submit" button using JavascriptExecutor
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveSubmitButton);

        // Find the "OK" button in the confirmation message
        WebElement okButton = driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]"));

        // Click the "OK" button using JavascriptExecutor
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okButton);
        Thread.sleep(1000);

        // Check for the presence of successful message
        By successMsgLocator = By.xpath("//*[@id=\"modalAlert\"]/div/div/div[2]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for the success message to be present and then retrieve and print it
        WebElement successMsg = wait.until(ExpectedConditions.presenceOfElementLocated(successMsgLocator));
        System.out.println("Message displayed: " + successMsg.getText());

        // Assert to verify if the successful message displayed
        Assert.assertTrue("successful message displayed", successMsg.isDisplayed());
        System.out.println("User successfully save and submit the Authorized Receipting Form.");

        // Click “OK” button in the alert msg
        driver.findElement(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[3]/button")).click();
        Thread.sleep(1000);

        // Post Cond: Check if user redirected to Authorized Receipting page
        Assert.assertEquals("User redirected to Authorized Receipting page successfully? ", "Account Receivable / Authorized Receipting", driver.getTitle().trim());
        System.out.println("User redirected to Authorized Receipting page successfully.");
    }

    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
