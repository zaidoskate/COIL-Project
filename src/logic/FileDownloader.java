package logic;

import java.sql.Blob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

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
}
