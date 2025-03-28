package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.io.Files;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.File;
import java.io.IOException;

public class googleTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    @Test
    public void searchSelenium() throws InterruptedException, IOException {
        try (PdfWriter writer = new PdfWriter("capturas/documentoTest.pdf"); // Para crear el pdf
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf)) {

            Paragraph title = new Paragraph("Titulo del documento de pruebas");
            title.setTextAlignment(TextAlignment.CENTER);
            title.setMarginTop(20);
            document.add(title);

            driver.get("https://www.google.com");
            WebElement searchBox = driver.findElement(By.name("q"));
            // Actions actionsAnte = new Actions(driver);
            searchBox.sendKeys("Documentación de selenium");

            Thread.sleep(1000);
            File screen1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Para tomar captura
            File destFile1 = new File("capturas/screenshot1.png");
            Files.copy(screen1, destFile1);

            Image img1 = new Image(ImageDataFactory.create(destFile1.getAbsolutePath())); // Para insertar la imagen en
                                                                                          // pdf
            img1.setAutoScale(true); // Para ajustar la imagen en el documento
            document.add(img1);
            document.add(
                    new Paragraph("Descripcion de la primera captura").setTextAlignment(TextAlignment.LEFT));

            searchBox.submit();

            Thread.sleep(1000);
            File screen2 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile2 = new File("capturas/screenshot2.png");
            Files.copy(screen2, destFile2);

            Image imag2 = new Image(ImageDataFactory.create(destFile2.getAbsolutePath()));
            document.add(imag2);
            document.add(
                    new Paragraph("Descripcion de la segunda captura").setTextAlignment(TextAlignment.LEFT));

            document.close(); // Para cerrar el pdf
        }
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
