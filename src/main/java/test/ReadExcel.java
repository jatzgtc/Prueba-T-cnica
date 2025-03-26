package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    // Leer un excel
    public Object[][] readExcelData(String filePath, String sheetName) throws IOException {
        try (FileInputStream file = new FileInputStream(new File(filePath));
                Workbook workbook = new XSSFWorkbook(file)) {
            // System.out.println("Archivo cargado correctamente");
            // System.out.println("Hojas disponibles en el archivo:");
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                // System.out.println(workbook.getSheetName(i));
            }
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException("La hoja " + sheetName + " no existe en el archivo " + filePath);
            }
            // System.out.println("Hoja " + sheetName + " cargada correctamente.");

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            Object[][] data = new Object[rowCount - 1][colCount];
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = (Cell) row.getCell(j);
                    if (cell == null) {
                        // System.out.println("Celda en fila " + i + ", columna " + j + " es nula.");
                        data[i - 1][j] = "";
                    } else {
                        data[i - 1][j] = cell.toString();
                    }
                }
            }
            return data;
        }
    }
}
