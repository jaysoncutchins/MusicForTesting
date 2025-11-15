import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database implements SearchAndSort {
    private ArrayList<Singer> singers = new ArrayList<>();
    private Map<String, Singer> singerIndex = new HashMap<>();


    public void addSinger(Singer singer) {
        singers.add(singer);
        singerIndex.put(singer.getId().toLowerCase(), singer);
    }


    public void removeSinger(Singer singer) {
        singers.remove(singer);
        singerIndex.remove(singer.getId().toLowerCase());
    }


    public Singer getSinger(String id) {
        return singerIndex.get(id.toLowerCase());
    }


    public ArrayList<Singer> getAllSingers() {
        return singers;
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
        // Rebuild the index
        this.singerIndex.clear();
        for (Singer singer : singers) {
            singerIndex.put(singer.getId().toLowerCase(), singer);
        }
    }

    // //bubble sort
    // @Override
    // public void sortCompositions(ArrayList<Composition> compositions) {
    //     System.out.println("The sorting algorithm chosen: Bubble Sort\n");
    //     int n = compositions.size();

    //     for (int i = 0; i < n - 1; i++) {
    //         for (int j = 0; j < n - i - 1; j++) {
    //             //ignoring case since in the ascii charts capital letters will always have values less than
    //             // lowercase letters. This way, a song named "Alice" won't get placed AFTER a song named "zebra"

    //             //if ele at j is bigger than its neighbor, swap
    //             if (compositions.get(j).getTitle().compareToIgnoreCase(compositions.get(j + 1).getTitle()) > 0) {
    //                 Composition temp = compositions.get(j);
    //                 compositions.set(j, compositions.get(j + 1));
    //                 compositions.set(j + 1, temp);
    //             }
    //         }
    //     }
    // }

    
    // merge sort
    @Override
    public void sortCompositions(ArrayList<Composition> compositions) {
        System.out.println("The sorting algorithm chosen: Merge Sort\n");
        if (compositions.size() <= 1) {
            return; //too small to sort! //this is also the base case for the recursion
        }
        int mid = compositions.size() / 2;

        //copy into two halves
        ArrayList<Composition> left = new ArrayList<>();
        for (int i = 0; i < mid; i++) {
            left.add(compositions.get(i));
        }

        ArrayList<Composition> right = new ArrayList<>();
        for (int i = mid; i < compositions.size(); i++) {
            right.add(compositions.get(i));
        }

        //sort each half recursively
        sortCompositions(left);
        sortCompositions(right);

        //merge back into original list
        merge(compositions, left, right);
    }

    public void merge(ArrayList<Composition> compositions, ArrayList<Composition> left, ArrayList<Composition> right) {
        ArrayList<Composition> merged = new java.util.ArrayList<>();
        int i = 0, j = 0;

        //compare elements from both lists
        while (i < left.size() && j < right.size()) {
            //ignoring case, see bubble sort for why
            if (left.get(i).getTitle().compareToIgnoreCase(right.get(j).getTitle()) <= 0) {
                merged.add(left.get(i++));
            } 
            else {
                merged.add(right.get(j++));
            }
        }
        //add remaining elements back that didn't get added in the while loop because one list was emptied
        while (i < left.size()) {
            merged.add(left.get(i));
            i++;
        }
        while (j < right.size()) {
            merged.add(right.get(j));
            j++;
        }
        //overwrite the original list
        compositions.clear();
        for (Composition composition : merged) {
            compositions.add(composition);
        }
    }


    // //Selection sort
    // @Override
    // public void sortCompositions(ArrayList<Composition> compositions) {
    //     System.out.println("The sorting algorithm chosen: Selection Sort\n");
    //     int n = compositions.size();

    //     for (int i = 0; i < n - 1; i++) {
    //         //assume the first index has smallest value
    //         int min = i;

    //         //find the true smallest in the remainder
    //         for (int j = i + 1; j < n; j++) {

    //             //ignoring case, see bubble sort for why
    //             if (compositions.get(j).getTitle().compareToIgnoreCase(compositions.get(min).getTitle()) < 0) {
    //                 min = j;
    //             }
    //         }
    //         //if min has changed, we found a new smallest value, so swap into place
    //         if (min != i) {
    //             Composition tmp = compositions.get(i);
    //             compositions.set(i, compositions.get(min));
    //             compositions.set(min, tmp);
    //         }
    //     }
    // }


    // //merge sort
    // @Override
    // public void sortCompositions(ArrayList<Composition> compositions) {
    //     System.out.println("The sorting algorithm chosen: Merge Sort\n");
    //     if (compositions.size() <= 1) {
    //         return; //too small to sort! //this is also the base case for the recursion
    //     }
    //     int mid = compositions.size() / 2;

    //     //copy into two halves
    //     ArrayList<Composition> left = new ArrayList<>();
    //     for (int i = 0; i < mid; i++) {
    //         left.add(compositions.get(i));
    //     }

    //     ArrayList<Composition> right = new ArrayList<>();
    //     for (int i = mid; i < compositions.size(); i++) {
    //         right.add(compositions.get(i));
    //     }

    //     //sort each half recursively
    //     sortCompositions(left);
    //     sortCompositions(right);

    //     //merge back into original list
    //     merge(compositions, left, right);
    // }

    // public void merge(ArrayList<Composition> compositions, ArrayList<Composition> left, ArrayList<Composition> right) {
    //     ArrayList<Composition> merged = new java.util.ArrayList<>();
    //     int i = 0, j = 0;

    //     //compare elements from both lists
    //     while (i < left.size() && j < right.size()) {
    //         //ignoring case, see bubble sort for why
    //         if (left.get(i).getTitle().compareToIgnoreCase(right.get(j).getTitle()) <= 0) {
    //             merged.add(left.get(i++));
    //         } 
    //         else {
    //             merged.add(right.get(j++));
    //         }
    //     }
    //     //add remaining elements back that didn't get added in the while loop because one list was emptied
    //     while (i < left.size()) {
    //         merged.add(left.get(i));
    //         i++;
    //     }
    //     while (j < right.size()) {
    //         merged.add(right.get(j));
    //         j++;
    //     }
    //     //overwrite the original list
    //     compositions.clear();
    //     for (Composition composition : merged) {
    //         compositions.add(composition);
    //     }
    // }


    // //linear search
    // @Override
    // public Composition searchComposition(ArrayList<Composition> compositions, String key) {
    //     System.out.println("The search algorithm chosen: Linear Search\n");
    //     for (Composition composition : compositions) {
    //         if (composition.getTitle().equalsIgnoreCase(key)) {
    //             return composition;
    //         }
    //     }
    //     return null;
    // }


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
