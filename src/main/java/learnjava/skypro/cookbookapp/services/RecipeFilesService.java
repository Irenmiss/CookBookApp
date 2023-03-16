package learnjava.skypro.cookbookapp.services;

public interface RecipeFilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
