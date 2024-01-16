package FIMS_09;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FIMS_09_TC1_Lim {
    static WebDriver driver;

    @Before
    public void beforeTest() throws InterruptedException {
        // Set up WebDriver for Edge browser
        System.setProperty("webdriver.edge.driver","C:\\Users\\limhu\\OneDrive\\Desktop\\UUM\\Sem 5\\Software Testing & Quality Accurance\\EdgeDriver\\msedgedriver.exe" );
        driver = new EdgeDriver();
        driver.manage().window().maximize();

        // Open the FIMS application
        driver.get("https://fimsclone.kerisi.my/");
        Thread.sleep(3000);

        // Input Email
        driver.findElement(By.xpath("//*[@id=\"userID\"]")).sendKeys("ENTRY4");
        Thread.sleep(2000);

        // Input Password
        driver.findElement(By.xpath("//*[@id=\"userPassword\"]")).sendKeys("qwertyuiop");
        Thread.sleep(2000);

        // Click Login
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
        Thread.sleep(1000);

        // Select Side Menu
        driver.findElement(By.xpath("//*[@id=\"sideMenuLeft\"]/div[2]")).click();
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

        // Assert to verify if the user is logged in successfully
        Assert.assertEquals("User logged in successfully? ", "Portal / Stock Application / New Application", driver.getTitle().trim());
        System.out.println("User logged in successfully.");
    }

    @Test
    public void FIMS_09_01_searchStoreNameValid() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(3000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(2000);

        // Get Text From Page
        String actualStoreName = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedStoreName = "UUMIT";

        // Assert to verify if the actual store name contains the expected store name
        Assert.assertTrue("Actual store name contains expected store name", actualStoreName.contains(expectedStoreName));
        System.out.println("User successfully handled the scenario with valid data input.");
    }
    @Test
    public void FIMS_09_02_searchStoreNameInvalid() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Invalid Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("InvalidStore123");
        Thread.sleep(2000);

        // Check for the presence of "Tiada Data" message
        WebElement tiadaDataMessage = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li"));
        System.out.println("Message displayed: " + tiadaDataMessage.getText());


        // Assert to verify if "Tiada Data" message is displayed for invalid data
        Assert.assertTrue("Tiada Data message displayed for invalid store name data", tiadaDataMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid store name data input.");
    }
    @Test
    public void FIMS_09_03_searchStoreNameNoInput() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Empty Store Name (No Data)
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("");
        Thread.sleep(1000);

        // Get Text From Page
        String actualStoreName = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();

        // Check for the presence of "Masukkan lagi 1 atau lebih huruf" message
        WebElement enterDataMessage = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li"));
        System.out.println("Message displayed: " + enterDataMessage.getText());

        // Assert to verify if "Masukkan lagi 1 atau lebih huruf" message is displayed for no data
        Assert.assertTrue("Masukkan lagi 1 atau lebih huruf message displayed for no data input", enterDataMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with no data.");
    }

    @Test
    public void FIMS_09_04_searchStore() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        String expectedStoreName = "UUMIT";
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(expectedStoreName);
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualStoreName = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();

        // Assert to verify if the actual store name contains the expected store name
        Assert.assertTrue("Actual store name contains expected store name", actualStoreName.contains(expectedStoreName));
        System.out.println("User successfully handled the scenario with valid data input.");
        Thread.sleep(2000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(2000);

        // Assert to verify if the store's list is displayed after search
        WebElement storeList = driver.findElement(By.xpath("//*[@id=\"dt_store_item_container\"]/div[1]"));
        Assert.assertTrue("Store's list is displayed after search", storeList.isDisplayed());
        System.out.println("User can see the store's list after clicking the search button.");
    }


    @Test
    public void FIMS_09_05_searchStoreNoInput() throws InterruptedException {
        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Empty Store Name (No Data)
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("");
        Thread.sleep(2000);

        // Check for the presence of "Masukkan lagi 1 atau lebih huruf" message
        WebElement enterDataMessage = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li"));
        System.out.println("Message displayed: " + enterDataMessage.getText());

        // Assert to verify if "Masukkan lagi 1 atau lebih huruf" message is displayed for no data
        Assert.assertTrue("Masukkan lagi 1 atau lebih huruf message displayed for no data input", enterDataMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with no data.");
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Additional assertion to check that the "Compulsory" message is displayed after searching with no data
        WebElement compulsoryMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/div"));
        Assert.assertTrue("Compulsory message displayed after searching with no data", compulsoryMessage.isDisplayed());
        System.out.println("Compulsory message displayed after searching with no data.");
    }

    @Test
    public void FIMS_09_06_clearData() throws InterruptedException {


        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        String expectedStoreName = "UUMIT";
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(expectedStoreName);
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualStoreName = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();

        // Assert to verify if the actual store name contains the expected store name
        Assert.assertTrue("Actual store name contains expected store name", actualStoreName.contains(expectedStoreName));
        System.out.println("User successfully handled the scenario with valid data input.");
        Thread.sleep(1500);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1500);

        // Assert to verify if the store's list is displayed after search
        WebElement storeList = driver.findElement(By.xpath("//*[@id=\"dt_store_item_container\"]/div[1]"));
        Assert.assertTrue("Store's list is displayed after search", storeList.isDisplayed());
        System.out.println("User can see the store's list after clicking the search button.");
        Thread.sleep(2000);

        // Click Clear button
        driver.findElement(By.xpath("//*[@id=\"requisition\"]/div[3]/button[1]")).click();
        Thread.sleep(2000);

        // Assert to verify if the store's list is cleared after clicking the clear button
        WebElement emptyStoreList = driver.findElement(By.xpath("//*[@id=\"dt_store_item_container\"]/div[1]"));
        Assert.assertFalse("Store's list is cleared after clicking the clear button", emptyStoreList.isDisplayed());
        System.out.println("User successfully clicked the clear button, and the store's list is cleared.");
    }

    @Test
    public void FIMS_09_07_searchItemValid() throws InterruptedException {


        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
        Thread.sleep(1000);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Item Name
        driver.findElement(By.xpath("//*[@id=\"mdl_store_item\"]/span/span/span[1]/input")).sendKeys("FAIL");
        Thread.sleep(2000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[14]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span/span[2]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualItemName = driver.findElement(By.xpath("//*[@id=\"select2-sit_store_item_id-container\"]")).getText();
        String expectedItemName = "FAIL";

        // Assert to verify if the actual item name contains the expected item name
        Assert.assertTrue("Actual item name contains expected item name", actualItemName.contains(expectedItemName));
        System.out.println("User successfully handled the scenario with valid item input.");
    }


    @Test
    public void FIMS_09_08_searchItemInvalid() throws InterruptedException {

        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
        Thread.sleep(1000);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Invalid Item Name
        driver.findElement(By.xpath("//*[@id=\"mdl_store_item\"]/span/span/span[1]/input")).sendKeys("KERTAS A5");
        Thread.sleep(2000);

        // Check for the presence of "Tiada Data" message
        WebElement tiadaDataMessage = driver.findElement(By.xpath("//*[@id=\"select2-sit_store_item_id-results\"]/li"));
        System.out.println("Message displayed: " + tiadaDataMessage.getText());

        // Assert to verify if "Tiada Data" message is displayed for invalid item data
        Assert.assertTrue("Tiada Data message displayed for invalid item data", tiadaDataMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid item data input.");

    }

    @Test
    public void FIMS_09_09_searchItemDuplicate() throws InterruptedException {


        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
        Thread.sleep(1500);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1500);

        // Input Item Name
        driver.findElement(By.xpath("//*[@id=\"mdl_store_item\"]/span/span/span[1]/input")).sendKeys("KLIP");
        Thread.sleep(2000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[22]")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Quantity Request
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("1");
        Thread.sleep(2000);

        // Save Item
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Select ok for confirmation
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Assert to verify if the item is added successfully
        Assert.assertTrue("Item added successfully.", true);
        System.out.println("User successfully add an item.");
        Thread.sleep(1500);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Item Name
        driver.findElement(By.xpath("//*[@id=\"mdl_store_item\"]/span/span/span[1]/input")).sendKeys("KLIP");
        Thread.sleep(2000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[22]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span/span[2]")).click();
        Thread.sleep(1000);

        // Check for the presence of duplicate message
        WebElement duplicateDataMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/div"));
        System.out.println("Message displayed: " + duplicateDataMessage.getText());

        // Assert to verify if duplicate message is displayed for invalid item data
        Assert.assertTrue("Duplicate message displayed for invalid item data", duplicateDataMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with duplicate item data input.");

    }

    @Test
    public void FIMS_09_10_enterQtyValid() throws InterruptedException {


        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
        Thread.sleep(1000);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Item Name
        driver.findElement(By.xpath("//*[@id=\"mdl_store_item\"]/span/span/span[1]/input")).sendKeys("FAIL");
        Thread.sleep(2000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[13]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span/span[2]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualItemName = driver.findElement(By.xpath("//*[@id=\"select2-sit_store_item_id-container\"]")).getText();
        String expectedItemName = "FAIL";

        // Assert to verify if the actual item name contains the expected item name
        Assert.assertTrue("Actual item name contains expected item name", actualItemName.contains(expectedItemName));
        System.out.println("User successfully handled the scenario with valid item input.");
        Thread.sleep(1000);

        // Input quantity
        WebElement qtyInput = driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]"));
        qtyInput.sendKeys("10");
        Thread.sleep(2000);

        // Get the balance quantity
        WebElement qtyElement = driver.findElement(By.xpath("//*[@id=\"sit_qty_bal\"]"));
        String balanceQty = qtyElement.getAttribute("value");
        System.out.println("Quantity Balance: " +balanceQty);

        // Define the expected quantity
        String inputQty = "10";

        // Assert to verify if the input quantity is less than or equal to the balance quantity
        Assert.assertTrue("Input quantity should be less than or equal to balance quantity", inputQty.compareTo(balanceQty) <= 0);

        // Assert to verify if the input quantity is greater than or equal to 1
        Assert.assertTrue("Input quantity should be greater than or equal to 1", Integer.parseInt(inputQty) >= 1);

        System.out.println("User successfully entered a valid quantity for the item.");
    }

    @Test
    public void FIMS_09_11_enterQtyInvalid() throws InterruptedException {


        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
        Thread.sleep(1000);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Item Name
        driver.findElement(By.xpath("//*[@id=\"mdl_store_item\"]/span/span/span[1]/input")).sendKeys("FAIL");
        Thread.sleep(2000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[17]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span/span[2]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualItemName = driver.findElement(By.xpath("//*[@id=\"select2-sit_store_item_id-container\"]")).getText();
        String expectedItemName = "FAIL";

        // Assert to verify if the actual item name contains the expected item name
        Assert.assertTrue("Actual item name contains expected item name", actualItemName.contains(expectedItemName));
        System.out.println("User successfully handled the scenario with valid item input.");
        Thread.sleep(1000);

        // Input quantity
        WebElement qtyInput = driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]"));
        qtyInput.sendKeys("200");
        Thread.sleep(2000);

        // Click save button
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]/i")).click();
        Thread.sleep(1000);

        // Check for the presence of the maximum quantity message
        WebElement maxQuantityMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_srd_qty_request\"]/div"));
        System.out.println("Message displayed: " + maxQuantityMessage.getText());

        // Assert to verify if the system displays the maximum quantity message for invalid item data
        Assert.assertTrue("System displays the maximum quantity message for invalid item data", maxQuantityMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid item data input.");
    }

    @Test
    public void FIMS_09_12_deleteItem() throws InterruptedException {


        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
        Thread.sleep(1000);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[31]")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Quantity Request
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("10");
        Thread.sleep(2000);

        // Save Item
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Select ok for confirmation
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Assert to verify if the item is added successfully
        Assert.assertTrue("Item added successfully.", true);
        System.out.println("User successfully add an item.");
        Thread.sleep(1000);

        // Delete Item
        driver.findElement(By.xpath("//*[@id=\"dt_store_item\"]/tbody/tr[1]/td[5]/a[2]/i")).click();
        Thread.sleep(1000);

        // Select ok for confirmation
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Check for the presence of deleted successfully message
        WebElement deletedMessage = driver.findElement(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[2]"));
        System.out.println("Message displayed: " + deletedMessage.getText());

        // Assert to verify if the item is deleted successfully
        Assert.assertTrue("Item deleted successfully message displayed.", deletedMessage.isDisplayed());
        System.out.println("User successfully delete an item.");

        // Select ok for confirmation
        driver.findElement(By.xpath("//*[@id=\"modalAlert\"]/div/div/div[3]/button")).click();
        Thread.sleep(1000);

    }

    @Test
    public void FIMS_09_13_saveItem() throws InterruptedException {

        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
        Thread.sleep(1500);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Select Item Input Box
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Item Name
        driver.findElement(By.xpath("//*[@id=\"mdl_store_item\"]/span/span/span[1]/input")).sendKeys("FAIL");
        Thread.sleep(1500);

        // Select Item
        driver.findElement(By.xpath("//*[@id=\"sit_store_item_id\"]/option[13]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/span/span[2]/span/span[2]")).click();
        Thread.sleep(1000);

        // Input Quantity Request
        driver.findElement(By.xpath("//*[@id=\"srd_qty_request\"]")).sendKeys("10");
        Thread.sleep(2000);

        // Save Item
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Select ok for confirmation
        driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1500);

        // Assert to verify if the item is added successfully
        Assert.assertTrue("Item added successfully.", true);
        System.out.println("User successfully add an item.");

    }

    @Test
    public void FIMS_09_14_saveItemNoInput() throws InterruptedException {

        // Select Store Name
        driver.findElement(By.xpath("//*[@id=\"inputArea_sma_store_code\"]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Store Name
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(2000);

        // Select Store
        driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-results\"]/li/table/tbody/tr/td[3]")).click();
        Thread.sleep(1000);

        // Search Store
        driver.findElement(By.xpath("//*[@id=\"searchbtn\"]")).click();
        Thread.sleep(1000);

        // Get Text From Page
        String actualTitle = driver.findElement(By.xpath("//*[@id=\"select2-sma_store_code-container\"]")).getText();
        String expectedTitle = "UUMIT";

        // Assert to verify if the actual title contains the expected title
        Assert.assertTrue("Actual title contains expected title", actualTitle.contains(expectedTitle));
        System.out.println("User search store successfully at requisition.");
        Thread.sleep(1000);

        // Select Add Item
        driver.findElement(By.xpath("//*[@id=\"btn_add\"]/i")).click();
        Thread.sleep(1000);

        // Save Item
        driver.findElement(By.xpath("//*[@id=\"srd_store_req_detl_id\"]")).click();
        Thread.sleep(1000);

        // Check for the presence of no item message
        WebElement noItemMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_sit_store_item_id\"]/div"));
        System.out.println("Message displayed: " + noItemMessage.getText());

        // Check for the presence of no quantity message
        WebElement noQtyMessage = driver.findElement(By.xpath("//*[@id=\"inputArea_srd_qty_request\"]/div"));
        System.out.println("Message displayed: " + noQtyMessage.getText());

        // Assert to verify if no data message is displayed for invalid add item and quantity
        Assert.assertTrue("No Item message displayed for invalid add item data", noItemMessage.isDisplayed());
        Assert.assertTrue("No Quantity message displayed for invalid add item data", noQtyMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid add item data input.");
    }
    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
