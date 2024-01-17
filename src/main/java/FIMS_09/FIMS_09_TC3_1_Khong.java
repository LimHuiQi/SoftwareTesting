package FIMS_09;

import com.google.common.collect.Ordering;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.List;
import java.util.stream.Collectors;

//Test the functions in List of Application page. (Module 4: Stock Application)
public class FIMS_09_TC3_1_Khong {
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

        // Assert to verify if the user is navigated to List of Application page successfully
        Assert.assertEquals("User navigate to List of Application page successfully", "Portal / Stock Application / List of Application", driver.getTitle().trim());
        System.out.println("User navigate to List of Application page successfully.");
    }

    //Test the search function in the List of Application page for valid data.
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

    //Test the search function in the List of Application page with invalid data.
    @Test
    public void FIMS_09_27_searchAppInvalid() throws InterruptedException {
        // Click Search Input Field
        driver.findElement(By.xpath("//*[@id=\"dt_store_master_filter\"]/label")).click();
        Thread.sleep(1000);

        // Enter "^&*" in the Search input field
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[1]/div[2]/div[1]/label/input")).sendKeys("^&*");
        Thread.sleep(1000);

        // Post Cond: Check for the presence of "No records" message
        WebElement noRecordsMsg = driver.findElement(By.xpath("//*[@id=\"dt_store_master\"]/tbody/tr/td/a"));

        // Assert to verify if the no records message displayed for invalid data input
        Assert.assertTrue("No records message displayed for invalid application data input", noRecordsMsg.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid data input.");
    }

    //Test the sorting function in the List of Application page.(Application No)
    @Test
    public void FIMS_09_28_sortApp() throws InterruptedException {
        // Click triangle icon in application no cell
        driver.findElement(By.xpath("//*[@id=\"dt_store_master\"]/thead/tr/th[2]")).click();
        Thread.sleep(1000);

        // To Check Post Cond: Get all the cells in the second column (Application No) from the table
        List<WebElement> applicationCells = driver.findElements(By.xpath("//table[@id='dt_store_master']/tbody/tr/td[2]"));

        // Get the application numbers from the elements (applicationCells)
        List<String> applicationNo = applicationCells.stream().map(WebElement::getText).collect(Collectors.toList());

        // Verify if the list is sorted in ascending order
        boolean isSorted = Ordering.natural().isOrdered(applicationNo);

        // Assertion to check the list of applications is sorted in ascending order
        Assert.assertTrue("The list of applications is sorted in ascending order", isSorted);
        System.out.println("The list of applications is sorted in ascending order.");
    }

    //Test the pagination function in the List of Application page.
    @Test
    public void FIMS_09_29_pagination() throws InterruptedException {
        // Click triangle “next” icon
        driver.findElement(By.xpath("//*[@id=\"dt_store_master_next\"]/a")).click();
        Thread.sleep(1000);

        // Check Post Cond: the list of applications for the next page is displayed
        List<WebElement> nextPageApp = driver.findElements(By.xpath("//*[@id=\"dt_store_master\"]/tbody/tr[1]/td[1]"));

        // Assertion to verify that applications are displayed on the next page
        Assert.assertFalse("The list of applications for the next page is not displayed", nextPageApp.isEmpty());
        System.out.println("The list of applications for the next page is displayed.");
    }

    //Test the view function in the List of Application page.
    @Test
    public void FIMS_09_30_viewApp() throws InterruptedException {
        // Click eye icon
        driver.findElement(By.xpath("//*[@id=\"dt_store_master\"]/tbody/tr[1]/td[9]/a[1]")).click();
        Thread.sleep(1000);

        // Post Cond: Assertion to check if the data for "Requisition" section is displayed
        WebElement requisitionElement = driver.findElement(By.xpath("//*[@id=\"requisition\"]/div[2]"));
        Assert.assertTrue("Requisition data is displayed", requisitionElement.isDisplayed());

        // Post Cond: Assertion to check if the data for "Store's Item" section is displayed
        WebElement storeItemElement = driver.findElement(By.xpath("//*[@id=\"dt_store_item_container\"]"));
        Assert.assertTrue("Store's Item data is displayed", storeItemElement.isDisplayed());

        // Post Cond: Assertion to check if the data for "Application Status" section is displayed
        WebElement appStatusElement = driver.findElement(By.xpath("//*[@id=\"dt_application_status_container\"]"));
        Assert.assertTrue("Application Status data is displayed", appStatusElement.isDisplayed());

        System.out.println("Data for 'Requisition', 'Store's Item', and 'Application Status' is displayed.");
    }

    //Test the search function of the Store’s Item section in the List of Application page with valid data.
    @Test
    public void FIMS_09_31_searchStoreItemValid() throws InterruptedException {
        // Click eye icon
        driver.findElement(By.xpath("//*[@id=\"dt_store_master\"]/tbody/tr[1]/td[9]/a[1]")).click();
        Thread.sleep(1000);

        // Enter “S” in the search field
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div[2]/div[1]/label/input")).sendKeys("S");
        Thread.sleep(1000);

        // Check Post Cond: Get the actual item description from the element (table cell)
        WebElement itemCell = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr[1]/td[2]"));
        String actualItem = itemCell.getText();
        String expectedItem = "S";

        // Assert to verify if the actual item description contains the expected item description 'S'
        Assert.assertTrue("Actual item description contains expected item description", actualItem.contains(expectedItem));
        System.out.println("User successfully get the searched store's item with valid data input.");
    }

    //Test the search function of the Store’s Item section in the List of Application page with invalid data.
    @Test
    public void FIMS_09_32_searchStoreItemInvalid() throws InterruptedException {
        // Click eye icon
        driver.findElement(By.xpath("//*[@id=\"dt_store_master\"]/tbody/tr[1]/td[9]/a[1]")).click();
        Thread.sleep(1000);

        // Enter “^&*” in the search field
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div[2]/div[1]/label/input")).sendKeys("^&*");
        Thread.sleep(1000);

        // Post Cond: Check for the presence of "No records" message
        WebElement noRecordsMsg = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td"));

        // Assert to verify if the no records message displayed for invalid data input
        Assert.assertTrue("No records message displayed for invalid application data input", noRecordsMsg.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid data input.");
    }

    @After
    public void afterTest() throws InterruptedException {
        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
