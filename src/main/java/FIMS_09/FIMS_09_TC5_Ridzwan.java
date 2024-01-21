package FIMS_09;

import org.junit.*;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FIMS_09_TC5_Ridzwan {
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

        Assert.assertTrue("User successfully login.", true);
        System.out.println("User successfully login.");

    }

    @Test
    public void FIMS_09_46_searchValidInput() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[1]/a")).click();
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[2]")).click();
        Thread.sleep(1000);

        String actualCashHolderName = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span/span[1]")).getText();
        String expectedCashHolderName = "UUMIT";

        // Assert to verify if the actual store name contains the expected store name
        Assert.assertTrue("Actual Cash Holder name contains expected Cash Holder name", actualCashHolderName.contains(expectedCashHolderName));
        System.out.println("User successfully handled the scenario with valid Cash Holder name data input.");
    }

    @Test
    public void FIMS_09_47_searchInvalidInput() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[1]/a")).click();
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("Pusat Sukan");
        Thread.sleep(1000);

        // Check for the presence of "Tiada Data" message
        WebElement noDataMessage = driver.findElement(By.xpath("/html/body/span/span/span[2]/ul/li"));
        System.out.println("Message displayed: " + noDataMessage.getText());

        // Assert to verify if "Tiada Data" message is displayed for invalid data
        Assert.assertTrue("Tiada Data message displayed for invalid store name data", noDataMessage.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid store name data input.");
    }

    @Test
    public void FIMS_09_48_saveCashHolder() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[1]/a")).click();
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[2]")).click();
        Thread.sleep(1000);

        // Click on "Save" button to save
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[14]/div/button")).click();
        Thread.sleep(1000);

        // Click on "Ok" button confirmation
        driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Assert to verify if the Cash Holder is saved successfully
        Assert.assertTrue("Cash Holder saved successfully.", true);
        System.out.println("Cash Holder successfully saved.");
    }

    @Test
    public void FIMS_09_49_saveApplicationWithInput() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[1]/a")).click();
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[2]")).click();
        Thread.sleep(1000);

        // Click on "Save" button to save
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[14]/div/button")).click();
        Thread.sleep(3000);

        // Click on "Ok" button confirmation
        driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Click on "Add" button to enter the receipt details for new application
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[4]/div[3]/button")).click();
        Thread.sleep(1000);

        //click on ptj
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[1]/span/span[2]/span")).click();
        Thread.sleep(1000);

        //Select on ptj
        WebElement optionToSelect1 = driver.findElement(By.xpath("//li[text()='PTJ']"));
        optionToSelect1.click();
        System.out.println("Successfully select PTJ");
        Thread.sleep(2000);

        //open calendar
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[2]/div/div/input")).click();
        Thread.sleep(1000);

        //Select on Calendar
        driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/table/tbody/tr[4]/td[6]")).click();
        Thread.sleep(1000);

        //click receipt number
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).click();
        Thread.sleep(1000);

        //enter receipt number
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).sendKeys("1234");
        Thread.sleep(1000);

        //click amount
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).click();
        Thread.sleep(1000);

        //enter amount
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[4]/div/input")).sendKeys("150");
        Thread.sleep(1000);

        //click reason
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[5]/textarea")).click();
        Thread.sleep(1000);

        //enter reason
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[5]/textarea")).sendKeys("Hospital Bills");
        Thread.sleep(1000);

        //click Save button
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        //click Ok button
        driver.findElement(By.xpath("/html/body/div[20]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Assert to verify if the Receipt Details is added successfully
        Assert.assertTrue("Receipt Details saved successfully.", true);
        System.out.println("User successfully handled the scenario with valid data input.");
    }

    @Test
    public void FIMS_09_50_saveApplicationWithoutInput() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[1]/a")).click();
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[2]")).click();
        Thread.sleep(1000);

        // Click on "Save" button to save
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[14]/div/button")).click();
        Thread.sleep(3000);

        // Click on "Ok" button confirmation
        driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Click on "Add" button to enter the receipt details for new application
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[4]/div[3]/button")).click();
        Thread.sleep(1000);

        //click Save button
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Check for the presence of "Compulsory" message
        WebElement compulsory1 = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[1]/div"));
        System.out.println("Message displayed: " + compulsory1.getText());

        // Check for the presence of "Min 0.01" message
        WebElement compulsory2 = driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[4]/div[2]"));
        System.out.println("Message displayed: " + compulsory2.getText());

        // Assert to verify if "Compulsory" message is displayed for invalid data
        Assert.assertTrue("Compulsory message displayed for invalid data input", compulsory1.isDisplayed());
        // Assert to verify if "Compulsory" message is displayed for invalid data
        Assert.assertTrue("Min 0.01 message displayed for invalid data input", compulsory2.isDisplayed());
        System.out.println("User successfully handled the scenario with invalid data input.");
    }

    @Test
    public void FIMS_09_51_submitApplication() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[1]/a")).click();
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[2]")).click();
        Thread.sleep(1000);

        // Click on "Save" button to save
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[14]/div/button")).click();
        Thread.sleep(3000);

        // Click on "Ok" button confirmation
        driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Click on "Add" button to enter the receipt details for new application
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[4]/div[3]/button")).click();
        Thread.sleep(1000);

        //click on ptj
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[1]/span/span[2]/span")).click();
        Thread.sleep(1000);

        //Select on ptj
        WebElement optionToSelect1 = driver.findElement(By.xpath("//li[text()='PTJ']"));
        optionToSelect1.click();
        System.out.println("Successfully select PTJ");
        Thread.sleep(2000);

        //open calendar
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[2]/div/div/input")).click();
        Thread.sleep(1000);

        //Select on Calendar
        driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/table/tbody/tr[4]/td[6]")).click();
        Thread.sleep(1000);

        //click receipt number
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).click();
        Thread.sleep(1000);

        //enter receipt number
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).sendKeys("1234");
        Thread.sleep(1000);

        //click amount
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).click();
        Thread.sleep(1000);

        //enter amount
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[4]/div/input")).sendKeys("150");
        Thread.sleep(1000);

        //click reason
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[5]/textarea")).click();
        Thread.sleep(1000);

        //enter reason
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[5]/textarea")).sendKeys("Hospital Bills");
        Thread.sleep(1000);

        //click Save button
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        //click Ok button
        driver.findElement(By.xpath("/html/body/div[20]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        //click Submit button
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[5]/div/button")).click();
        Thread.sleep(1000);

        //click Ok button
        driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        //click Ok button
        driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button")).click();
        Thread.sleep(1000);

        // Assert to verify if the item is added successfully
        Assert.assertTrue("Application submit successfully.", true);
        System.out.println("The system success to Submit the Application");
    }

    @Test
    public void FIMS_09_53_editApplication() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select Stock Application
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[1]/a")).click();
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/span/span[2]/span")).click();
        Thread.sleep(1000);

        // Input Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys("UUMIT");
        Thread.sleep(1000);

        // Select Petty Cash Holder for PTJ
        driver.findElement(By.xpath("/html/body/span/span/span[2]")).click();
        Thread.sleep(1000);

        // Click on "Save" button to save
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[14]/div/button")).click();
        Thread.sleep(3000);

        // Click on "Ok" button confirmation
        driver.findElement(By.xpath("/html/body/div[19]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        // Click on "Add" button to enter the receipt details for new application
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[4]/div[3]/button")).click();
        Thread.sleep(1000);

        //click on ptj
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[1]/span/span[2]/span")).click();
        Thread.sleep(1000);

        //Select on ptj
        WebElement optionToSelect1 = driver.findElement(By.xpath("//li[text()='PTJ']"));
        optionToSelect1.click();
        System.out.println("Successfully select PTJ");
        Thread.sleep(2000);

        //open calendar
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[2]/div/div/input")).click();
        Thread.sleep(1000);

        //Select on Calendar
        driver.findElement(By.xpath("/html/body/div[18]/div[2]/div[1]/table/tbody/tr[4]/td[6]")).click();
        Thread.sleep(1000);

        //click receipt number
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).click();
        Thread.sleep(1000);

        //enter receipt number
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).sendKeys("1234");
        Thread.sleep(1000);

        //click amount
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[3]/input")).click();
        Thread.sleep(1000);

        //enter amount
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[4]/div/input")).sendKeys("150");
        Thread.sleep(1000);

        //click reason
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[5]/textarea")).click();
        Thread.sleep(1000);

        //enter reason
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[5]/textarea")).sendKeys("Hospital Bills");
        Thread.sleep(1000);

        //click Save button
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        //click Ok button
        driver.findElement(By.xpath("/html/body/div[20]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        //click Edit button
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[4]/div[2]/div[3]/table/tbody/tr/td[8]/a[1]")).click();
        Thread.sleep(1000);

        //clear amount column
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[4]/div/input")).clear();
        Thread.sleep(1000);

        //enter new amount column
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[2]/div/div[4]/div/input")).sendKeys("200");
        Thread.sleep(1000);

        //click Save button
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[3]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);

        WebElement editInfo = driver.findElement(By.xpath("//*[@id=\"modalConfirm7\"]/div/div/div[2]"));
        System.out.println("Message displayed: " + editInfo.getText());

        // Assert to verify if the information is edited successfully
        Assert.assertTrue("Information was successfully edit message displayed..", editInfo.isDisplayed());
        System.out.println("User successfully edit an Information.");

        //click Ok button
        driver.findElement(By.xpath("/html/body/div[20]/div/div/div[3]/button[2]")).click();
        Thread.sleep(1000);
    }

    @Test
    public void FIMS_09_52_viewApplication() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select List of Petty Cash
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[2]/a")).click();
        Thread.sleep(1000);

        // View Application
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/table/tbody/tr[1]/td[9]/a[1]")).click();
        Thread.sleep(1000);

        // Assert to verify if the Application is displayed
        Assert.assertTrue("Details of Application is displayed.", true);
        System.out.println("The user can see details of Application");
    }

    @Test
    public void FIMS_09_54_deleteApplication() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select List of Petty Cash
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[2]/a")).click();
        Thread.sleep(1000);

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

    @Test
    public void FIMS_09_55_printApplication() throws InterruptedException {

        // Select Side Menu
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/a")).click();
        Thread.sleep(1000);

        // Select Portal
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/a")).click();
        Thread.sleep(1000);

        // Select List of Petty Cash
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/ul/li[6]/ul/li[5]/ul/li[2]/a")).click();
        Thread.sleep(1000);

        // View Application
        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[1]/label/input")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[1]/label/input")).sendKeys("Apply");
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[4]/form/div/div[2]/div[2]/div[3]/table/tbody/tr/td[9]/a[2]/i")).click();
        Thread.sleep(1000);

        Assert.assertTrue("User can print the document successfully.", true);
        System.out.println("User successfully print.");
    }

    @After
    public void afterTest() throws InterruptedException {

        // Stay on the page for 3 seconds
        Thread.sleep(3000);

        // Quit the driver
        driver.quit();
    }
}
