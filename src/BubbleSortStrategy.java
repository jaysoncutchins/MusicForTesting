import java.util.ArrayList;

/**
 * Bubble sort implementation for sorting compositions by title.
 */
public class BubbleSortStrategy implements SortStrategy {
    
    @Override
    public void sort(ArrayList<Composition> compositions) {
        int n = compositions.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                //ignoring case since in the ascii charts capital letters will always have values less than
                // lowercase letters. This way, a song named "Alice" won't get placed AFTER a song named "zebra"

                //if ele at j is bigger than its neighbor, swap
                if (compositions.get(j).getTitle().compareToIgnoreCase(compositions.get(j + 1).getTitle()) > 0) {
                    Composition temp = compositions.get(j);
                    compositions.set(j, compositions.get(j + 1));
                    compositions.set(j + 1, temp);
                }
            }
        }
    }
    
    @Override
    public String getAlgorithmName() {
        return "Bubble Sort";
    }
}
