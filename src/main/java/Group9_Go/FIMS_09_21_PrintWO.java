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

public class FIMS_09_21_PrintWO {
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
    public void printWO() throws InterruptedException {
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

        // Assert that the selected option is as expected
        String selectedOptionText = driver.findElement(By.id("select2-claim_month-container")).getText();
        Assert.assertEquals("Dropdown selection does not match!", "December 2023", selectedOptionText); // Replace 'Expected Option Text' with your expected option text

        // Click on the claim_month dropdown again
        driver.findElement(By.xpath("//*[@id=\"select2-claim_month-container\"]")).click();
        Thread.sleep(2000);

        // Click the button to generate a report
        driver.findElement(By.xpath("//*[@id=\"inputArea_btn_report\"]/div/button[1]")).click();
        Thread.sleep(3000);

// Switch to the new tab
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

// Specify the URL of the link you want to wait for
        String linkUrl = driver.getCurrentUrl();

//        WebElement reportElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("https://fimsclone.kerisi.my/custom/report/senarai/Overtime/WorkOrder_pdf.php")));

        // Get the href attribute of the reportElement
        String expectedURL = "https://fimsclone.kerisi.my/custom/report/senarai/Overtime/WorkOrder_pdf.php";

        // Assert that the href attribute matches the linkUrl
        Assert.assertEquals("URL does not match!", linkUrl, expectedURL);
    }


    @After
    public void afterTest() throws InterruptedException {
        String expectedTitle = "Senarai Arahan Kerja - Senarai_Arahan_Kerja_202312.pdf";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
      driver.quit();
    }

}