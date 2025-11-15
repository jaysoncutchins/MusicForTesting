import java.util.ArrayList;

/**
 * Strategy interface for sorting compositions.
 * Follows the Strategy Pattern for better adherence to Open/Closed Principle.
 */
public interface SortStrategy {
    void sort(ArrayList<Composition> compositions);
    String getAlgorithmName();
}
