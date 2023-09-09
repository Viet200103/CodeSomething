package data.managers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class FileManager implements IFileManager {

    private File inputFile = null;

    private FileManager() {

    }

    public FileManager(String fileName) {
        inputFile = new File(fileName);
    }

    public FileManager(File inputFile) {
        this.inputFile = inputFile;
    }

    @Override
    public List<String> readDataFromFile() throws IOException {
        List<String> result;
        result = Files.readAllLines(inputFile.toPath(), StandardCharsets.UTF_8);
        return result;
    }

    public static final String PRODUCT_FILE_NAME = "product.txt";
    public static final String WAREHOUSE_FILE_NAME = "warehouse.txt";

}
