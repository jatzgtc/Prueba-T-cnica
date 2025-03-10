package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class googleTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        driver = new ChromeDriver(options);
    }

    @Test
    public void searchSelenium() throws InterruptedException, IOException {
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        Actions actionsAnte = new Actions(driver);
        searchBox.sendKeys("Documentación de selenium");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("capturas/capturaGoogle.png"));
        Thread.sleep((long) (Math.random() * 5000));
        Actions actionsAntes = new Actions(driver);
        searchBox.submit();
        Thread.sleep((long) (Math.random() * 5000));
        Actions actions = new Actions(driver);
        //WebElement selectDocSelenium = driver.findElement(By.className("LC20lb"));
        //actions.moveToElement(selectDocSelenium).perform();
        Thread.sleep((long) (Math.random() * 5000));
        //selectDocSelenium.click();
    }

    @Test
    public void testSenium() throws InterruptedException, IOException {
        driver.get("https://www.selenium.dev/documentation/");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("capturas/capturaSelenium.png"));
        driver.findElement(By.linkText("Overview")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabOver : driver.getWindowHandles()) {
            driver.switchTo().window(tabOver);
        }
        File screenshotOver = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("capturas/capturaSeleniumOverview.png"));
        driver.close();
        driver.findElement(By.id("m-documentationwebdriver")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabWebDriver : driver.getWindowHandles()) {
            driver.switchTo().window(tabWebDriver);
        }
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

}
