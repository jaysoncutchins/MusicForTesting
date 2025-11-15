import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Singer {
    private String name;
    private String id;
    private ArrayList<Composition> compositions = new ArrayList<>();
    private Map<String, Composition> compositionIndex = new HashMap<>();

    
    public Singer(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public void addComposition(Composition composition) {
        compositions.add(composition);
        compositionIndex.put(composition.getTitle().toLowerCase(), composition);
    }


    public Composition getComposition(String title) {
        return compositionIndex.get(title.toLowerCase());
    }


    public void removeComposition(String title) {
        Composition composition = getComposition(title);
        if (composition == null) {
            return;
        }
        compositions.remove(composition);
        compositionIndex.remove(title.toLowerCase());
    }


    public ArrayList<Composition> getCompositions() {
        return compositions;
    }


    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "ID: " + id + "\n" +
                "Number of compositions: " + compositions.size() + "\n";
    }


    public void displayCompositions() {
        for (Composition composition : compositions) {
            System.out.println(composition);
        }
    }
}