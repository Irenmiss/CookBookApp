package learnjava.skypro.cookbookapp.services;

import java.io.File;
import java.nio.file.Path;

public interface RecipeFilesService {
    boolean saveToFile(String json);

    String readFromFile();

    boolean cleanDataFile();

    File getDataFile();
    Path createTempFile(String suffix);
}
