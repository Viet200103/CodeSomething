package data.managers;

import java.util.List;

public interface IFileManager {

    List<String> readDataFromFile() throws Exception;
}
