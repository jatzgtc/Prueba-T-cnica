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
        Thread.sleep(2000);
        Actions actionsAntes = new Actions(driver);
        searchBox.submit();
        Actions actions = new Actions(driver);
    }

    @Test
    public void testSenium() throws InterruptedException, IOException {
        driver.get("https://www.selenium.dev/documentation/");
        String tabOriginal = driver.getWindowHandle();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("capturas/capturaSelenium.png"));
        /// //////////////////////////////////////////////////////
        driver.findElement(By.linkText("Overview")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabOver : driver.getWindowHandles()) {
            driver.switchTo().window(tabOver);
        }
        Thread.sleep(1000);
        File screenshotOver = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotOver, new File("capturas/capturaSeleniumOverview.png"));
        driver.close();
        driver.switchTo().window(tabOriginal);
        /// ////////////////////////////////////
        driver.findElement(By.id("m-documentationwebdriver")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabWebDriver : driver.getWindowHandles()) {
            driver.switchTo().window(tabWebDriver);
        }
        Thread.sleep(1000);
        File screenshotWebDriver = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotWebDriver, new File("capturas/capturaSeleniumWebDriver.png"));
        driver.close();
        driver.switchTo().window(tabOriginal);
        /// //////////////////////////////////////
        driver.findElement(By.id("m-documentationselenium_manager")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabSeleniumManeger : driver.getWindowHandles()) {
            driver.switchTo().window(tabSeleniumManeger);
        }
        Thread.sleep(1000);
        File screenshotSeleniumManager = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotSeleniumManager, new File("capturas/capturaSeleniumManager.png"));
        driver.close();
        driver.switchTo().window(tabOriginal);
        /// ////////////////////////////////////////
        driver.findElement(By.linkText("Grid")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabSeleniumGrid : driver.getWindowHandles()) {
            driver.switchTo().window(tabSeleniumGrid);
        }
        Thread.sleep(1000);
        File screenshotGrid = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotGrid, new File("capturas/capturaSeleniumGrid.png"));
        driver.close();
        driver.switchTo().window(tabOriginal);
        /// /////////////////////////////////
        driver.findElement(By.id("m-documentationie_driver_server")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabSeleniumIE : driver.getWindowHandles()) {
            driver.switchTo().window(tabSeleniumIE);
        }
        Thread.sleep(1000);
        File screenshotIE = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotIE, new File("capturas/capturaSeleniumIE.png"));
        driver.close();
        driver.switchTo().window(tabOriginal);
        /// ///////////////////////////////////////
        driver.findElement(By.linkText("IDE")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabSeleniumIDE : driver.getWindowHandles()) {
            driver.switchTo().window(tabSeleniumIDE);
        }
        Thread.sleep(1000);
        File screenshotIDE = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotIDE, new File("capturas/capturaSeleniumIDE.png"));
        driver.close();
        driver.switchTo().window(tabOriginal);
        /// //////////////////////////////////////////
        driver.findElement(By.id("m-documentationtest_practices")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabSeleniumTestPractice : driver.getWindowHandles()) {
            driver.switchTo().window(tabSeleniumTestPractice);
        }
        Thread.sleep(1000);
        File screenshotTestPractice = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotTestPractice, new File("capturas/capturaSeleniumTestPractice.png"));
        driver.close();
        driver.switchTo().window(tabOriginal);
        /// //////////////////////////////////////////
        driver.findElement(By.linkText("Legacy")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabSeleniumIDE : driver.getWindowHandles()) {
            driver.switchTo().window(tabSeleniumIDE);
        }
        Thread.sleep(1000);
        File screenshotLegacy = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotLegacy, new File("capturas/capturaSeleniumLegacy.png"));
        driver.close();
        driver.switchTo().window(tabOriginal);
        /// //////////////////////////////////////////
        driver.findElement(By.id("m-documentationabout")).sendKeys(Keys.CONTROL, Keys.RETURN);
        for (String tabSeleniumIDE : driver.getWindowHandles()) {
            driver.switchTo().window(tabSeleniumIDE);
        }
        Thread.sleep(1000);
        File screenshotAbout = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshotAbout, new File("capturas/capturaSeleniumAbout.png"));
        driver.close();
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

}
