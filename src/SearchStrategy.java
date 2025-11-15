import java.util.ArrayList;

/**
 * Strategy interface for searching compositions.
 * Follows the Strategy Pattern for better adherence to Open/Closed Principle.
 */
public interface SearchStrategy {
    Composition search(ArrayList<Composition> compositions, String key);
    String getAlgorithmName();
}
