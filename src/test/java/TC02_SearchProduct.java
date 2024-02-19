import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;


public class TC02_SearchProduct {

    WebDriver driver = new ChromeDriver();

    @Test
    public void searchProduct() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));

        driver.get("https://hq.qashier.com/#/login");

        sleep(3000);

        driver.findElement(By.xpath("//*[@aria-label='Username']")).sendKeys("nuralia.abdrahman@gmail.com");
        driver.findElement(By.xpath("//*[@aria-label='Password']")).sendKeys("ali4.W0RK");
        driver.findElement(By.xpath("//button//*[text()='Login']")).click();

        sleep(3000);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@aria-label,'Product Management')]//*[text()='Product Management']"))).click();

        sleep(3000);

        driver.findElement(By.xpath("//*[contains(@class,'q-expansion-item__content')]//*[text()='Inventory Management']")).click();

        sleep(3000);

        driver.findElement(By.xpath("//input[contains(@placeholder,'Search')]")).sendKeys("Silver Flower Earrings");


        //String actualTextWarning = "warning\n" + "No matching records found" ;

        sleep(3000);

        String expectedTextWarning = "No matching records found";

        String actualTextWarning = driver.findElement(By.xpath("//*[contains(@class,'bottom--nodata')]")).getText();

        Assert.assertTrue(actualTextWarning.contains(expectedTextWarning));


        driver.findElement(By.xpath("//button[contains(text(),'cancel')]")).click();

        sleep(3000);

        String productName = "Gold Flower Earrings";

        driver.findElement(By.xpath("//input[contains(@placeholder,'Search')]")).sendKeys(productName);

        sleep(3000);

        String actualProductName = driver.findElement(By.xpath("//tbody/tr[1]/td[1]")).getText();

        Assert.assertEquals(actualProductName, productName);

    }

    @AfterTest
    public void quit(){
        driver.quit();
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
