package logic;

import java.sql.Blob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class FileDownloader {    
    public static void transformBlobToFile(String outputPath, Blob blob) throws SQLException, FileNotFoundException, IOException{
        InputStream inputStream = blob.getBinaryStream();
        File outputFile = new File(outputPath);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
        }
    }
    
    public static void exportToExcel(String folderPath, int[] regionCollaborationCounts, int[] academicAreaCollaborationCounts) throws IOException {
    String filePath = folderPath + File.separator;
    try (Workbook workBook = new XSSFWorkbook()) {
        Sheet regionSheet = workBook.createSheet("Regiones");
        Row regionHeader = regionSheet.createRow(0);
        regionHeader.createCell(0).setCellValue("Región");
        regionHeader.createCell(1).setCellValue("Profesores");
        String[] regions = {"Xalapa", "Veracruz", "Coatzacoalcos", "Orizaba", "Tuxpan"};
        for (int i = 0; i < regions.length; i++) {
            Row regionData = regionSheet.createRow(i + 1);
            regionData.createCell(0).setCellValue(regions[i]);
            regionData.createCell(1).setCellValue(regionCollaborationCounts[i]);
        }
        for (int i=0; i<regions.length; i ++) {
            regionSheet.autoSizeColumn(i);
        }
        Sheet academicAreaSheet = workBook.createSheet("Áreas académicas");
        Row academicAreaHeader = academicAreaSheet.createRow(0);
        academicAreaHeader.createCell(0).setCellValue("Área Académica");
        academicAreaHeader.createCell(1).setCellValue("Profesores");
        String[] academicAreas = {"Artes", "Ciencias Biológicas y Agropecuarias", "Ciencias de la Salud", "Económico Administrativo", "Humanidades", "Técnicas"};
        for (int i=0; i<academicAreas.length; i ++) {
            Row academicAreaData = academicAreaSheet.createRow(i + 1);
            academicAreaData.createCell(0).setCellValue(academicAreas[i]);
            academicAreaData.createCell(1).setCellValue(academicAreaCollaborationCounts[i]);
        }
        for (int i=0; i<academicAreas.length; i ++) {
            academicAreaSheet.autoSizeColumn(i);
        }
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workBook.write(fileOut);
    }
}

}
