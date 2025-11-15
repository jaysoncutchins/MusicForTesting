import java.util.ArrayList;

public class Singer {
    private final String name;
    private final String id;
    private final ArrayList<Composition> compositions = new ArrayList<>();

    
    public Singer(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public void addComposition(Composition composition) {
        compositions.add(composition);
    }


    public Composition getComposition(String title) {
        for (Composition composition : compositions) {
            if (composition.getTitle().equalsIgnoreCase(title)) {
                return composition;
            }
        }
        return null;
    }


    public void removeComposition(String title) {
        Composition composition = getComposition(title);
        if (composition == null) {
            return;
        }
        compositions.remove(composition);
    }


    public ArrayList<Composition> getCompositions() {
        return new ArrayList<>(compositions);
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