package test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class providerTest {

    @DataProvider(name = "excelData")
    public Object[][] excelDataProvider() throws IOException {
        ReadExcel excelReader = new ReadExcel();
        return excelReader.readExcelData(
                "C:\\Users\\jatz\\Desktop\\Quality\\Automatizacion\\Tarea\\Prueba-Tenica\\provider\\Prueba.xlsx",
                "Hoja1");
    }

    @Test(dataProvider = "excelData")
    public void testExcelData(String userName, String pass, String fecha) {
        System.out.println("UserName " + userName + ", Password: " + pass + ", Fecha " + fecha);
    }
}