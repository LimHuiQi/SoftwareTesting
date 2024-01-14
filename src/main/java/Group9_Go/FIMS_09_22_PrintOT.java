package Group9_Go;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class FIMS_09_22_PrintOT {
    static WebDriver driver;

    @Before
    public void beforeTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\User\\Study\\Testing\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://fimsclone.kerisi.my/");
        Thread.sleep(3000);
        String title = driver.getTitle();
        String url = driver.getCurrentUrl();
        System.out.println("Title: " + title);
        System.out.println("URL " + url);
    }

    @org.junit.Test
    public void printOT() throws InterruptedException {
        // Login
        driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY4");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys("qwertyuiop");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        Thread.sleep(1000);


        // Wait for the side menu and click
        WebElement sideMenu = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"sideMenuLeft\"]/div[2]")));
        sideMenu.click();
        Thread.sleep(1500);

        // Navigate to the specified menu items
        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1136\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1667\"]")).click();
        Thread.sleep(3000);

        // Click on the claim_month dropdown
        driver.findElement(By.xpath("//*[@id=\"select2-claim_month-container\"]")).click();
        Thread.sleep(3000);

        // Select the 2nd option in the claim_month dropdown
        WebElement dropdown = driver.findElement(By.id("claim_month"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'));", dropdown);

        // Assert that the selected option in the dropdown is as expected
        String selectedOptionText = driver.findElement(By.id("select2-claim_month-container")).getText();
        Assert.assertEquals("Dropdown selection does not match!", "December 2023", selectedOptionText);

        // Click on the claim_month dropdown again
        driver.findElement(By.xpath("//*[@id=\"select2-claim_month-container\"]")).click();
        Thread.sleep(2000);

        // Click the button to generate a report
        driver.findElement(By.xpath("//*[@id=\"inputArea_btn_report\"]/div/button[2]")).click();
        Thread.sleep(2000);
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement reportElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[7]/div[2]/div[1]/div[4]")));
        Assert.assertTrue("Report is generate fail", reportElement.isDisplayed());
    }

    @After
    public void afterTest() throws InterruptedException {
        String expectedTitle = "Tuntutan Elaun Kerja Lebih Masa - Tuntutan_Elaun_Kerja_Lebih_Masa_202312.pdf";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
        driver.quit();
    }

}