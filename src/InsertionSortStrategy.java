import java.util.ArrayList;

/**
 * Insertion sort implementation for sorting compositions by title.
 */
public class InsertionSortStrategy implements SortStrategy {
    
    @Override
    public void sort(ArrayList<Composition> compositions) {
        int n = compositions.size();
        // start at the second element (index 1), since index 0 is trivially sorted
        for (int i = 1; i < n; i++) {
            // walk backwards from i down to 1
            for (int j = i; j > 0; j--) {
                //ignoring case since in the ascii charts capital letters will always have values less than
                // lowercase letters. This way, a song named "Alice" won't get placed AFTER a song named "zebra"
                //check if element at j is smaller than the preceeding element
                if (compositions.get(j).getTitle().compareToIgnoreCase(compositions.get(j - 1).getTitle()) < 0) {
                    //if so, swap
                    Composition temp = compositions.get(j);
                    compositions.set(j, compositions.get(j - 1));
                    compositions.set(j - 1, temp);
                }
            }
        }
    }
    
    @Override
    public String getAlgorithmName() {
        return "Insertion Sort";
    }
}
