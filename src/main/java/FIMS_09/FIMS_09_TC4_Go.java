package FIMS_09;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class FIMS_09_TC4_Go {
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
        Thread.sleep(3000);
    }

    @Test
    public void FIMS_09_21_PrintWO() throws InterruptedException {
        // Navigate to the specified menu items
        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1136\"]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1667\"]")).click();
        Thread.sleep(4000);

        // Click on the claim_month dropdown
        driver.findElement(By.xpath("//*[@id=\"select2-claim_month-container\"]")).click();
        Thread.sleep(4000);

        // Select the 2nd option in the claim_month dropdown
        WebElement dropdown = driver.findElement(By.id("claim_month"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'));", dropdown);

        // Assert that the selected option is as expected
        String selectedOptionText = driver.findElement(By.id("select2-claim_month-container")).getText();
        Assert.assertEquals("Dropdown selection does not match!", "December 2023", selectedOptionText); // Replace 'Expected Option Text' with your expected option text

        // Click on the claim_month dropdown again
        driver.findElement(By.xpath("//*[@id=\"select2-claim_month-container\"]")).click();
        Thread.sleep(3000);

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

// Wait for the report element to be visible in the new tab
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement reportElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[7]/div[2]/div/div[2]")));

        Assert.assertTrue("Report is generate fail", reportElement.isDisplayed());

        String expectedTitle = "Senarai Arahan Kerja - Senarai_Arahan_Kerja_202312.pdf";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }


    @Test
    public void FIMS_09_22_PrintOT() throws InterruptedException {


        // Navigate to the specified menu items
        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1136\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1667\"]")).click();
        Thread.sleep(4000);

        // Click on the claim_month dropdown
        driver.findElement(By.xpath("//*[@id=\"select2-claim_month-container\"]")).click();
        Thread.sleep(4000);

        // Select the 2nd option in the claim_month dropdown
        WebElement dropdown = driver.findElement(By.id("claim_month"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'));", dropdown);

        // Assert that the selected option in the dropdown is as expected
        String selectedOptionText = driver.findElement(By.id("select2-claim_month-container")).getText();
        Assert.assertEquals("Dropdown selection does not match!", "December 2023", selectedOptionText);

        // Click on the claim_month dropdown again
        driver.findElement(By.xpath("//*[@id=\"select2-claim_month-container\"]")).click();
        Thread.sleep(3000);

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

        String expectedTitle = "Tuntutan Elaun Kerja Lebih Masa - Tuntutan_Elaun_Kerja_Lebih_Masa_202312.pdf";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }

    @Test
    public void FIMS_09_23_UserManual() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1136\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1667\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"user_manual\"]")).click();
        Thread.sleep(3000);
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement reportElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[7]/div[2]/div[1]/div[2]")));
        Assert.assertTrue("Report is generate fail", reportElement.isDisplayed());

        String expectedTitle = "Panduan Pengguna - UserManual_FIMS_OvertimeClaim_V1.0.pdf";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }
    @Test
    public void FIMS_09_24_DownloadFilter() throws InterruptedException {


        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(5000);

        // Click on the filter
        driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvance_smartFilter\"]")).click();
        Thread.sleep(6000);

        // Click on the payment type textfield
        driver.findElement(By.xpath("//*[@id=\"inputArea_sam_payment_type\"]/span/span[2]/span")).click();
        Thread.sleep(2000);

        // Select the 2nd option in the payment type dropdown
        WebElement dropdown = driver.findElement(By.id("sam_payment_type"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'));", dropdown);

        // Close the payment type dropdown
        driver.findElement(By.xpath("//*[@id=\"inputArea_sam_payment_type\"]/span/span[2]/span")).click();
        Thread.sleep(2000);

        // Click on the OK button in the smart filter
        driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvanceSmartFilter\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(4000);

        String expectedTitle = "Portal / Advance Staff / Declaration / List of Activity Advance";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");

        // Assert that the Download Excel button is clickable
        WebElement downloadExcelButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("btn_excel")));
        Assert.assertTrue("Download Excel button is not clickable!", downloadExcelButton.isEnabled());

        // Click on the Download Excel button
        downloadExcelButton.click();


    }
    @Test
    public void FIMS_09_25_SearchSpecialCharacter() throws InterruptedException {


        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(6000);

        // Enter the search term in the text field
        WebElement searchField = driver.findElement(By.xpath("//*[@id='dt_listOfActivityAdvance_filter']/label/div/div/input"));
        searchField.sendKeys("$$");

        // Assert that the search field contains the entered value
        String enteredValue = searchField.getAttribute("value");
        Assert.assertEquals("Search term is not entered correctly!", "$$", enteredValue);

        String expectedTitle = "Portal / Advance Staff / Declaration / List of Activity Advance";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }

    @Test
    public void FIMS_09_26_FilterSearch() throws InterruptedException {
        // Login

        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(3000);

        // Click on the smart filter
        driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvance_smartFilter\"]")).click();
        Thread.sleep(6000);

        // Click on the payment type textfield
        driver.findElement(By.xpath("//*[@id=\"inputArea_sam_payment_type\"]/span/span[2]/span")).click();
        Thread.sleep(2000);

        // Select the 1st option in the payment type dropdown
        WebElement dropdown = driver.findElement(By.id("sam_payment_type"));
        Assert.assertNotNull("Dropdown element is not identified!", dropdown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'));", dropdown);

        // Close the payment type dropdown
        driver.findElement(By.xpath("//*[@id=\"inputArea_sam_payment_type\"]/span/span[2]/span")).click();
        Thread.sleep(2000);

        // Click on the OK button in the smart filter
        driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvanceSmartFilter\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(3000);

        // Assert that the table contains data
        WebElement dataTable = driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvance\"]"));
        Assert.assertTrue("Table does not contain data!", dataTable.isDisplayed());

        String expectedTitle = "Portal / Advance Staff / Declaration / List of Activity Advance";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }
    @Test
    public void FIMS_09_27_DownloadExcel() throws InterruptedException {


        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(6000);

        // Assert that the Excel download button is clickable
        WebElement downloadExcelButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("btn_excel")));
        Assert.assertTrue("Excel download button is not clickable!", downloadExcelButton.isEnabled());

        // Click on the Excel download button
        downloadExcelButton.click();
        Thread.sleep(4000);

        String expectedTitle = "Portal / Advance Staff / Declaration / List of Activity Advance";
        String actualTitle = driver.getTitle().trim();
        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }

    @Test
    public void FIMS_09_28_DisplayFilter() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(2000);

        // Select the second option from the dropdown
        WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[1]/label/span/span[2]/span")));
        dropdown.click();

        // Wait for the dropdown options to be visible
        WebElement dropdownOptions = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/span/span/span[2]/ul")));

        // Get all options
        List<WebElement> options = dropdownOptions.findElements(By.tagName("li"));

        // Assert that there are at least two options
        assert(options.size() >= 2);

        // Click on the second option
        options.get(1).click();
        Thread.sleep(3000);

        String expectedTitle = "Portal / Advance Staff / Declaration / List of Activity Advance";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }

    @Test
    public void FIMS_09_29_KeywordSearch() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(2000);

        // Search for the keyword
        WebElement searchField = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='dt_listOfActivityAdvance_filter']/label/div/div/input")));
        searchField.sendKeys("b");

        // Wait for the search results or any relevant element to be visible
        WebElement searchResults = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[4]/table/tbody/tr[1]/td[16]/a[5]/i")));

        // Assert that the search results are displayed
        assert(searchResults.isDisplayed());

        String expectedTitle = "Portal / Advance Staff / Declaration / List of Activity Advance";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }
    @Test
    public void FIMS_09_30_DeleteFunction() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2315\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_2342\"]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"menu_id_1200\"]")).click();
        Thread.sleep(6000);

        // Delete function
        WebElement deleteButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dt_listOfActivityAdvance\"]/tbody/tr[1]/td[16]/a[6]/i")));
        deleteButton.click();
        Thread.sleep(6000);

        // Confirm delete
        WebElement okButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[18]/div/div/div[3]/button[2]")));
        okButton.click();
        Thread.sleep(6000);

        // Assert that the element is deleted successfully
        WebElement deletedElement = driver.findElement(By.xpath("//*[@id=\"dt_listOfActivityAdvance\"]/tbody/tr[1]"));
        assert(deletedElement.isDisplayed());

        String expectedTitle = "Portal / Advance Staff / Declaration / List of Activity Advance";
        String actualTitle = driver.getTitle().trim();

        Assert.assertEquals("Title mismatch!", expectedTitle, actualTitle);
        System.out.println("Test Passed!");
    }

    @After
    public void afterTest() throws InterruptedException {
        driver.quit();
    }
}
