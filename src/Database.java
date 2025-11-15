import java.util.ArrayList;

public class Database implements SearchAndSort {
    private ArrayList<Singer> singers = new ArrayList<>();


    public void addSinger(Singer singer) {
        singers.add(singer);
    }


    public void removeSinger(Singer singer) {
        singers.remove(singer);
    }


    public Singer getSinger(String id) {
        for (Singer singer : singers) {
            if (id.equalsIgnoreCase(singer.getId())) {
                return singer;
            }
        }
        return null;
    }


    public ArrayList<Singer> getAllSingers() {
        return new ArrayList<>(singers);
    }


    public void displayAllCompositions() {
        for (Singer singer : singers) {
            singer.displayCompositions();
        }
    }


    public void displayAllSingers() {
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }


    public void setAllSingers(ArrayList<Singer> newList) {
        this.singers = newList;
    }

    // insertion sort
    @Override
    public void sortCompositions(ArrayList<Composition> compositions) {
        System.out.println("The sorting algorithm chosen: Insertion Sort\n");
        int n = compositions.size();
        // start at the second element (index 1), since index 0 is trivially sorted
        for (int i = 1; i < n; i++) {

            // walk backwards from i down to 1
            for (int j = i; j > 0; j--) {

                //ignoring case, see bubble sort for why
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


    //binary search
    @Override
    public Composition searchComposition(ArrayList<Composition> compositions, String key) {
        //remember, binary search requires a sorted list to work correctly
        
        System.out.println("The search algorithm chosen: Binary Search\n");
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
}
