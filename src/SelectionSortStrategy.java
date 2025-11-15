import java.util.ArrayList;

/**
 * Selection sort implementation for sorting compositions by title.
 */
public class SelectionSortStrategy implements SortStrategy {
    
    @Override
    public void sort(ArrayList<Composition> compositions) {
        int n = compositions.size();

        for (int i = 0; i < n - 1; i++) {
            //assume the first index has smallest value
            int min = i;

            //find the true smallest in the remainder
            for (int j = i + 1; j < n; j++) {
                //ignoring case since in the ascii charts capital letters will always have values less than
                // lowercase letters. This way, a song named "Alice" won't get placed AFTER a song named "zebra"
                if (compositions.get(j).getTitle().compareToIgnoreCase(compositions.get(min).getTitle()) < 0) {
                    min = j;
                }
            }
            //if min has changed, we found a new smallest value, so swap into place
            if (min != i) {
                Composition tmp = compositions.get(i);
                compositions.set(i, compositions.get(min));
                compositions.set(min, tmp);
            }
        }
    }
    
    @Override
    public String getAlgorithmName() {
        return "Selection Sort";
    }
}
