import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC01_AddStaff {

    WebDriver driver = new ChromeDriver();

    @Test
    public void addStaff() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(60));

        driver.get("https://hq.qashier.com/#/login");

        driver.findElement(By.xpath("//*[@aria-label='Username']")).sendKeys("nuralia.abdrahman@gmail.com");
        driver.findElement(By.xpath("//*[@aria-label='Password']")).sendKeys("ali4.W0RK");
        driver.findElement(By.xpath("//button//*[text()='Login']")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@aria-label,'Staff Management')]//*[text()='Staff Management']"))).click();
        driver.findElement(By.xpath("//*[contains(@class,'q-expansion-item__content')]//*[text()='Staff Management']")).click();

        WebElement addStaffBtn = driver.findElement(By.xpath("//button//*[text()='Add Staff']//ancestor::button"));
        wait.until(e -> addStaffBtn.isEnabled());
        addStaffBtn.click();

        String name = "any name";
        String hourlyRate = "1.23";
        String staffPin = "1111";

        driver.findElement(By.xpath("//input[@aria-label='Name']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@aria-label='Hourly Rate ()']")).sendKeys(hourlyRate);
        driver.findElement(By.xpath("//input[@aria-label='Staff PIN']")).sendKeys(staffPin);
        driver.findElement(By.xpath("//button//*[text()='Confirm']")).click();

        String actualName = driver.findElement(By.xpath("//*[@class='q-table']/tbody/tr[1]/td[1]")).getText();
        String actualHourlyRate = "RM" + driver.findElement(By.xpath("//*[@class='q-table']/tbody/tr[1]/td[2]")).getText();
        String actualStaffPin = driver.findElement(By.xpath("//*[@class='q-table']/tbody/tr[1]/td[3]")).getText();

        Assert.assertEquals(actualName, name);

        String expectedHourlyRate = "RM" + hourlyRate;
        // Assert the hourly rate value
        Assert.assertEquals(actualHourlyRate, expectedHourlyRate);
        Assert.assertEquals(actualStaffPin, staffPin);
    }

    @AfterTest
    public void quit(){
        driver.quit();
    }
}
