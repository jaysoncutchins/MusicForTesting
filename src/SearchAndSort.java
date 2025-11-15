import java.util.ArrayList;

public interface SearchAndSort {
    void sortCompositions(ArrayList<Composition> compositions);
    Composition searchComposition(ArrayList<Composition> compositions, String key); 
}
