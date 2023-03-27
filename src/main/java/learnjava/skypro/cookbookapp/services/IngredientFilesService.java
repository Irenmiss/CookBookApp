package learnjava.skypro.cookbookapp.services;

import java.io.File;

public interface IngredientFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
}
