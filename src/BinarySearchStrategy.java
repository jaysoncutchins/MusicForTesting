import java.util.ArrayList;

/**
 * Binary search implementation for searching compositions by title.
 * NOTE: Requires sorted list to work correctly.
 */
public class BinarySearchStrategy implements SearchStrategy {
    
    @Override
    public Composition search(ArrayList<Composition> compositions, String key) {
        //remember, binary search requires a sorted list to work correctly
        int low = 0;
        int high = compositions.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midTitle = compositions.get(mid).getTitle();
            int valueToCompare = midTitle.compareToIgnoreCase(key);
            if (valueToCompare == 0) {
                //found it
                return compositions.get(mid);
            } 
            else if (valueToCompare < 0) {
                //mid is alphabetically before key, so search right half
                low = mid + 1;
            } 
            else {
                //mid is after key, so search left half
                high = mid - 1;
            }
        }
        // not found
        return null;
    }
    
    @Override
    public String getAlgorithmName() {
        return "Binary Search";
    }
}
