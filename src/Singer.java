import java.util.ArrayList;

public class Singer {
    private String name;
    private String id;
    private ArrayList<Composition> compositions = new ArrayList<>();

    
    public Singer(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.id = id;
        this.name = name;
    }


    public void addComposition(Composition composition) {
        if (composition == null) {
            throw new IllegalArgumentException("Composition cannot be null");
        }
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Singer singer = (Singer) obj;
        return id.equals(singer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}