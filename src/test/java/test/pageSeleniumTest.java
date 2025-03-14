package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class pageSeleniumTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @Ignore
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
    public void tearDown() {
        // Eliminar las captuaras en el proyecto
        for (int i = 1; i <= 100; i++) {
            File screenshot = new File("capturas/screenshot" + i + ".png");
            if (screenshot.exists()) {
                if (screenshot.delete()) {
                    System.out.println("Archivo eliminado: " + screenshot.getName());
                } else {
                    System.err.println("No se puedo eliminar el archivo: " + screenshot.getName());
                }
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }

}
