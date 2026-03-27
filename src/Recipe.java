import java.util.ArrayList;

public class Recipe {
    private ArrayList<Menu> allDishes; //all dish available
    public Recipe() {
        allDishes = new ArrayList<>(); // for add all dish
        setupRecipes(); // all ingredient we need for dish
    }

    private void setupRecipes() {
        // Salmon Roll
        ArrayList<String> salmonroll = new ArrayList<>();
        salmonroll.add("Rice");
        salmonroll.add("Salmon");
        salmonroll.add("Seaweed");
        allDishes.add(new Menu("Salmon Roll", salmonroll));

        //Salmon Maki
        ArrayList<String> salmonmaki = new ArrayList<>();
        salmonmaki.add("Rice");
        salmonmaki.add("Salmon");
        allDishes.add(new Menu("Salmon Maki", salmonmaki));

        // ikura maki
        ArrayList<String> ikuramaki = new ArrayList<>();
        ikuramaki.add("Rice");
        ikuramaki.add("Ikura");
        ikuramaki.add("Seaweed");
        allDishes.add(new Menu("Ikura Maki", ikuramaki));

        //Crab maki
        ArrayList<String> crabmaki = new ArrayList<>();
        crabmaki.add("Rice");
        crabmaki.add("Crab");
        crabmaki.add("Seaweed");
        allDishes.add(new Menu("Crab Maki", crabmaki));

        //Tuna Maki
        ArrayList<String> tunamaki = new ArrayList<>();
        tunamaki.add("Rice");
        tunamaki.add("Tuna");
        allDishes.add(new Menu("Tuna Maki", tunamaki));

        //Shrimp Maki
        ArrayList<String> shrimpmaki = new ArrayList<>();
        shrimpmaki.add("Rice");
        shrimpmaki.add("Shrimp");
        allDishes.add(new Menu("Shrimp Maki", shrimpmaki));
    }
    public ArrayList<Menu> getAllDishes() { return allDishes; }
    public Menu getRandomDish() { //random dish for each customer
        int randomIndex = (int)(Math.random() * allDishes.size());
        return allDishes.get(randomIndex);
    }

}