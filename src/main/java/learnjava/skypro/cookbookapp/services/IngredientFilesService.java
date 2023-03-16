package learnjava.skypro.cookbookapp.services;

public interface IngredientFilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
