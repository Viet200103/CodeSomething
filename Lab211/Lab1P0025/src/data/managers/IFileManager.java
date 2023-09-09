package data.managers;

import java.util.List;

public interface IFileManager {

    List<String> readDataFromFile() throws Exception;

    void saveItem(String rawData) throws Exception;

    boolean isCodeExists(String itemCode) throws Exception;
}
