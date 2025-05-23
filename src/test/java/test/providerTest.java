package test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

        String rutaCarpeta = "capturas";
        File carpeta = new File(rutaCarpeta);

        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Carpeta creada exitosamente.");
            } else {
                System.out.println("Error al crear la caperta.");
            }
        } else {
            System.out.println("La carpeta ya existe.");
        }

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
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
        searchBox.sendKeys(test);

        Thread.sleep(2500);
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
                        System.out.println("Se elimino el archivo: " + file.getName());
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