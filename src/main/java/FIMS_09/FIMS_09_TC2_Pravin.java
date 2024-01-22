package FIMS_09;

import com.google.common.collect.Ordering;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class FIMS_09_TC2_Pravin {
    static WebDriver driver;
    static WebDriverWait wait; // Declare WebDriverWait

    @Before
    public void beforeTest() throws InterruptedException {
        // Set up WebDriver for Edge browser
        System.setProperty("webdriver.edge.driver", "C:\\Users\\intgo\\OneDrive\\Documents\\Software Testing\\Test\\WebDriver\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Initialize WebDriverWait with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the FIMS application
        driver.get("https://fimsclone.kerisi.my/");
        Thread.sleep(3000);

        // Input Email
        driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY4");
        Thread.sleep(1000);

        // Input Password
        driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys("qwertyuiop");
        Thread.sleep(1000);

        // Click Login
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("//*[@id=\"menu_id_1533\"]")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("//*[@id=\"menu_id_2867\"]")).click();
        Thread.sleep(1000);

        // Select New Application
        driver.findElement(By.xpath("//*[@id=\"menu_id_2868\"]")).click();
        Thread.sleep(1000);

        // Assert to verify if the user successfully access the correct page
        Assert.assertEquals("Checking if user at the correct page", "Portal / Stock Application / New Application", driver.getTitle().trim());
        System.out.println("User properly accessed to the New Application page!");
    }

    @Test
    public void FIMS_09_15_searchItem() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(5000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);

        // Input Search Item (Store's Item)
        driver.findElement(By.xpath("//*[@id=\"dt_store_item_filter\"]/label/input")).sendKeys("PISAU");
        Thread.sleep(1000);

        // Get Text From Page (Store's Item [Item])
        String actualSearchItem = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[2]")).getText();
        String expectedSearchItem = "PISAU";

        // Assert to verify if the searched store item matches the expected store item
        Assert.assertTrue("The searched item do not match with the expected store item", actualSearchItem.contains(expectedSearchItem));
        System.out.println("Successfully searched for Item!");
    }

    @Test
    public void FIMS_09_16_searchQuantityRequest() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Input Search Item (Store's Item)
        driver.findElement(By.xpath("//*[@id=\"dt_store_item_filter\"]/label/input")).sendKeys("13");
        Thread.sleep(1000);

        // Get Text From Page (Store's Item [Quantity Request])
        String actualSearchQuantityRequest = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[3]")).getText();
        String expectedSearchQuantityRequest = "13";

        // Assert to verify if the searched quantity request matches the expected quantity request
        Assert.assertTrue("The searched Quantity Request do not match with the expected Quantity Request", actualSearchQuantityRequest.contains(expectedSearchQuantityRequest));
        System.out.println("Successfully searched for Quantity Request!");

    }

    @Test
    public void FIMS_09_17_searchQuantityAvailable() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Input Search Item (Store's Item)
        driver.findElement(By.xpath("//*[@id=\"dt_store_item_filter\"]/label/input")).sendKeys("29");
        Thread.sleep(1000);

        // Get Text From Page (Store's Item [QuantityAvailable])
        String actualSearchQuantityAvailable = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[4]")).getText();
        String expectedSearchQuantityAvailable = "29";

        // Assert to verify if the searched quantity available matches the expected quantity available
        Assert.assertTrue("The searched Quantity Available do not match with the expected Quantity Available", actualSearchQuantityAvailable.contains(expectedSearchQuantityAvailable));
        System.out.println("Successfully searched for Quantity Available!");

    }

    @Test
    public void FIMS_09_18_searchInvalidData() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(3000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);

        // Input Search Item (Store's Item)
        driver.findElement(By.xpath("//*[@id=\"dt_store_item_filter\"]/label/input")).sendKeys("abc");
        Thread.sleep(1000);

        // Check to see if "No records" message is displayed
        WebElement noRecordsMessage = null;
        try {
            noRecordsMessage = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td/a[text()='No records']"));
            System.out.println("Invalid message displayed: " + noRecordsMessage.getText());
        } catch (NoSuchElementException e) {
            // Log a custom message indicating the absence of the "No records" message
            System.out.println("No records message not found: " + e.getMessage());
        }

        // Assert to verify if "No records" message is displayed for invalid data
        Assert.assertTrue("No records message did not display after searching invalid data of Store's item",
                noRecordsMessage != null && noRecordsMessage.isDisplayed());
        System.out.println("Successfully confirming the empty table.");

    }

    @Test
    public void FIMS_09_19_editItem() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("BENDAHARI");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(5000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);

        // Select Edit button
        driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[5]/a[1]/i")).click();
        Thread.sleep(3000);

        // Select Item field
        driver.findElement(By.xpath("//*[@id=\"select2-sit_store_item_id-container\"]")).click();
        Thread.sleep(1000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[2]")).click();
        Thread.sleep(1000);

        // Select Item field
        driver.findElement(By.xpath("//*[@id=\"select2-sit_store_item_id-container\"]")).click();
        Thread.sleep(1000);

        // Select Save button
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Select 'Ok' button
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(3000);

        // Wait for table update
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr"), 1));

        // Assertion for the presence of the keyword in the table
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr"));
        int rowCount = rows.size();
        Assert.assertEquals("Expected one row in the table after the edit", 1, rowCount);

        // Get Text for STICKER 50MM From Page (Store's Item [Item])
        WebElement itemElement = rows.get(0).findElement(By.xpath("./td[2]"));
        String actualEditItem = itemElement.getText();
        String expectedEditItem = "S00250005 - STICKER TAGGING ASET (50MM X 80MM)";

        // Assert to verify if item has been changed
        Assert.assertEquals("Edited item does not match with the expected Item", expectedEditItem, actualEditItem);
        System.out.println("Successfully edited the Item!");

    }

    @Test
    public void FIMS_09_20_editQuantityRequest() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("BENDAHARI");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(5000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);

        // Select Edit button
        driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[5]/a[1]/i")).click();
        Thread.sleep(3000);

        // Select Quantity Request textbox
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).click();
        Thread.sleep(1000);

        // Clear number
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).clear();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("5");
        Thread.sleep(1000);

        // Select Save button
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Select 'Ok' button
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Wait for the table update using WebDriverWait
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr"), 1));

        // Assertion for the presence of the keyword in the table
        List<WebElement> rows = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr"));
        int rowCount = rows.size();
        Assert.assertEquals("Expected one row in the table after the edit", 1, rowCount);

        // Get Text for Quantity Request From Page
        WebElement quantityRequestElement = rows.get(0).findElement(By.xpath("./td[3]"));
        String actualEditQuantityRequest = quantityRequestElement.getText();
        String expectedEditQuantityRequest = "5";

        // Assert to verify if quantity request has been changed
        Assert.assertEquals("Edited Quantity Request does not match with the expected Quantity Request", expectedEditQuantityRequest, actualEditQuantityRequest);
        System.out.println("Successfully edited the Quantity Request!");

    }

    @Test
    public void FIMS_09_21_editExceedQuantity() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("BENDAHARI");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(3000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);

        // Select Edit button
        driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[5]/a[1]/i")).click();
        Thread.sleep(3000);

        // Select Quantity Request textbox
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).click();
        Thread.sleep(1000);

        // Clear number
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).clear();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("1000");
        Thread.sleep(1000);

        // Select Save button
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Check to see if "Max +++" message is displayed
        WebElement maxNumberMessage = null;
        try {
            maxNumberMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_srd_qty_request\"]/div"));
            System.out.println("Invalid message displayed: " + maxNumberMessage.getText());
        } catch (NoSuchElementException e) {
            // Log a custom message indicating absence of the element
            System.out.println("Element not found: " + e.getMessage());
        }

        // Assert to verify if "Max +++" message is displayed for exceeding available quantity
        Assert.assertTrue("Max number message did not display after entering number exceeding Quantity Available of the item in Store's Item",
                maxNumberMessage != null && maxNumberMessage.isDisplayed());
        System.out.println("Successfully confirming the invalid input.");
    }

    @Test
    public void FIMS_09_22_saveWithData() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("BENDAHARI");
        Thread.sleep(3000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(3000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Select Save and Submit button
        driver.findElement(By.xpath("//*[@id=\"srm_store_request_id\"]")).click();
        Thread.sleep(3000);

        // Select Ok button
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(5000);

        // Check to see if the recently added item message is displayed
        WebElement addedItem = null;
        try {
            addedItem = driver.findElement(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[2]"));
            System.out.println("Message displayed: " + addedItem.getText());
        } catch (NoSuchElementException e) {
            // Log a custom message indicating intentional absence of the element
            System.out.println("Element not found: " + e.getMessage());
        }

        // Assert to verify if the item is added successfully
        Assert.assertTrue("Added item message fails to display.",
                addedItem != null && addedItem.isDisplayed());
        System.out.println("Item successfully has been saved and submitted into the database.");
    }

    @Test
    public void FIMS_09_23_sortItem() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(3000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);


        // Wait for the table to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"dt_store_item\"]/tbody")));

        // Get all the cells in the Item column
        List<WebElement> itemCells = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[2]"));

        // Log the list of items before sorting
        List<String> originalItemOrder = itemCells.stream().map(WebElement::getText).toList();
        System.out.println("Original Item Order: " + originalItemOrder);

        // Select Item column to trigger sorting
        WebElement itemColumn = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/thead/tr/th[2]"));
        itemColumn.click();

        // Wait for the table to be refreshed after sorting
        wait.until(ExpectedConditions.stalenessOf(itemCells.get(0)));

        // Get the Item values after sorting
        itemCells = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[2]"));
        List<String> sortedItemList = itemCells.stream().map(WebElement::getText).collect(Collectors.toList());

        // Verify if the Item list is sorted in ascending order
        boolean isSorted = Ordering.natural().isOrdered(sortedItemList);

        // Assertion for the sorting result
        Assert.assertTrue("Not in order", isSorted);

        // Log the list of items after sorting
        System.out.println("Sorted Item Order: " + sortedItemList);

        System.out.println("The Store's Item is sorted in ascending order by item.");
    }

    @Test
    public void FIMS_09_24_sortQuantityRequest() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(5000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);


        // Wait for the table to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"dt_store_item\"]/tbody")));

        // Get all the cells in the Quantity Request column
        List<WebElement> quantityRequestCells = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[3]"));

        // Log the list of items before sorting
        List<Integer> originalQuantityRequestOrder = quantityRequestCells
                .stream()
                .map(WebElement::getText)
                .map(Integer::parseInt) // Convert to Integer
                .collect(Collectors.toList());

        System.out.println("Original Quantity Request Order: " + originalQuantityRequestOrder);

        // Select Quantity Request column to trigger sorting
        WebElement quantityAvailableColumn = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/thead/tr/th[3]"));
        quantityAvailableColumn.click();

        // Wait for the table to be refreshed after sorting
        wait.until(ExpectedConditions.stalenessOf(quantityRequestCells.get(0)));

        // Get the Quantity Request values after sorting
        quantityRequestCells = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[3]"));
        List<Integer> sortedQuantityRequestNumber = quantityRequestCells
                .stream()
                .map(WebElement::getText)
                .map(Integer::parseInt) // Convert to Integer
                .collect(Collectors.toList());

        // Verify if the Quantity Available list is sorted in ascending order
        boolean isSorted = Ordering.natural().isOrdered(sortedQuantityRequestNumber);

        // Assertion for the sorting result
        Assert.assertTrue("Not in order", isSorted);

        // Log the list of items after sorting
        System.out.println("Sorted Quantity Request Order: " + sortedQuantityRequestNumber);

        System.out.println("The Store's Item is sorted in ascending order by quantity request.");
    }

    @Test
    public void FIMS_09_25_sortQuantityAvailable() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("IT");
        Thread.sleep(1000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Select Search button
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(3000);


        // Wait for the table to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"dt_store_item\"]/tbody")));

        // Get all the cells in the Quantity Available column
        List<WebElement> quantityAvailableCells = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[4]"));

        // Log the list of items before sorting
        List<Integer> originalQuantityAvailableOrder = quantityAvailableCells
                .stream()
                .map(WebElement::getText)
                .map(Integer::parseInt) // Convert to Integer
                .collect(Collectors.toList());

        System.out.println("Original Quantity Available Order: " + originalQuantityAvailableOrder);

        // Select Quantity Available column to trigger sorting
        WebElement quantityAvailableColumn = driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/thead/tr/th[4]"));
        quantityAvailableColumn.click();

        // Wait for the table to be refreshed after sorting
        wait.until(ExpectedConditions.stalenessOf(quantityAvailableCells.get(0)));

        // Get the Quantity Available values after sorting
        quantityAvailableCells = driver.findElements(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr/td[4]"));
        List<Integer> sortedQuantityAvailableNumber = quantityAvailableCells
                .stream()
                .map(WebElement::getText)
                .map(Integer::parseInt) // Convert to Integer
                .collect(Collectors.toList());

        // Verify if the Quantity Available list is sorted in ascending order
        boolean isSorted = Ordering.natural().isOrdered(sortedQuantityAvailableNumber);

        // Assertion for the sorting result
        Assert.assertTrue("Not in order", isSorted);

        // Log the list of items after sorting
        System.out.println("Sorted Quantity Available Order: " + sortedQuantityAvailableNumber);

        System.out.println("The Store's Item is sorted in ascending order by quantity available.");
    }


    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
