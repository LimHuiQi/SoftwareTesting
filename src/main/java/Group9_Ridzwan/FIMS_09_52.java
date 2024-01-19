package Group9_Ridzwan;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class FIMS_09_52 {

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

        // Select List of Petty Cash
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[2]/a")).click();
        Thread.sleep(1000);

        // Assert to verify if the user is logged in successfully
        Assert.assertEquals("User logged in successfully? ", "Petty Cash / List of Petty Cash", driver.getTitle().trim());
        System.out.println("User logged in successfully.");
    }

    @Test
    public void FIMS_09_54_deleteApplication() throws InterruptedException {

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[1]/label/input")).sendKeys("Draft");
        Thread.sleep(1000);

        // View draft Application
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/table/tbody/tr[1]/td[9]/a[4]")).click();
        Thread.sleep(1000);

        //click delete button
        driver.findElement(By.xpath("/html/body/div[18]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        WebElement deletedMessage = driver.findElement(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[2]"));
        System.out.println("Message displayed: " + deletedMessage.getText());

        // Assert to verify if the item is deleted successfully
        Assert.assertTrue("Information was successfully deleted message displayed..", deletedMessage.isDisplayed());
        System.out.println("User successfully delete an Information.");

        //click ok
        driver.findElement(By.xpath("/html/body/div[18]/div/div/div[3]/button")).click();
        Thread.sleep(1000);

    }

    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(5000);
        System.out.println("done test");

        // Quit the driver
        driver.quit();
    }

}
