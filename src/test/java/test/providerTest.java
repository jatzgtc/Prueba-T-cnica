package test;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import io.github.bonigarcia.wdm.WebDriverManager;

public class providerTest {

    private WebDriver driver;
    private PdfDocument pdf;
    private Document document;

    @BeforeSuite
    public void setUpSuite() throws IOException {
        PdfWriter writer = new PdfWriter("capturas/documentoExcelTest.pdf");
        pdf = new PdfDocument(writer);
        document = new Document(pdf);

        Paragraph title = new Paragraph("Titulo");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setMarginTop(20);
        document.add(title);
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws IOException {
        ReadExcel excelReader = new ReadExcel();
        return excelReader.readExcelData(
                "C:\\Users\\jatz\\Desktop\\Quality\\Automatizacion\\Tarea\\Prueba-Tenica\\provider\\Prueba.xlsx",
                "Hoja1");
    }

    @Test(dataProvider = "excelData")
    public void testExcelData(String test) throws InterruptedException, IOException {

        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(test);

        Thread.sleep(1000);
        File screen1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File desFile1 = new File("capturas/screenshot" + test + ".png");
        Files.copy(screen1, desFile1);

        Image img1 = new Image(ImageDataFactory.create(desFile1.getAbsolutePath()));

        img1.setAutoScale(true);
        document.add(img1);
        document.add(new Paragraph("Descripción de captura" + test).setTextAlignment(TextAlignment.LEFT));

        System.out.println("Test " + test);

    }

    @AfterMethod
    public void tearDown() {
        // Eliminar las captuaras en el proyecto
        File folder = new File("capturas");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith("screenshot")) {
                    if (file.delete()) {
                        System.out.println("El archivo: " + file.getName());
                    } else {
                        System.out.println("No se pudo elimintar el archivo" + file.getName());
                    }
                }
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownSuite() {
        document.close();
    }

}