import java.util.ArrayList;

public class Menu {
    private final String dishName;
    private final ArrayList<String> ingredients;

    public Menu(String dishName, ArrayList<String> ingredients) {
        this.dishName = dishName;
        this.ingredients = ingredients;
    }

    public String getDishName() { return dishName; }
    public ArrayList<String> getIngredients() { return ingredients; }
}
