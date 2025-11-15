import java.util.ArrayList;

/**
 * Linear search implementation for searching compositions by title.
 */
public class LinearSearchStrategy implements SearchStrategy {
    
    @Override
    public Composition search(ArrayList<Composition> compositions, String key) {
        for (Composition composition : compositions) {
            if (composition.getTitle().equalsIgnoreCase(key)) {
                return composition;
            }
        }
        return null;
    }
    
    @Override
    public String getAlgorithmName() {
        return "Linear Search";
    }
}
