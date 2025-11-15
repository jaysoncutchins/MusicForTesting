import java.util.ArrayList;

public class Database {
    private ArrayList<Singer> singers = new ArrayList<>();
    private SortStrategy sortStrategy;
    private SearchStrategy searchStrategy;

    public Database() {
        // Default strategies
        this.sortStrategy = new InsertionSortStrategy();
        this.searchStrategy = new BinarySearchStrategy();
    }

    public void setSortStrategy(SortStrategy sortStrategy) {
        if (sortStrategy == null) {
            throw new IllegalArgumentException("Sort strategy cannot be null");
        }
        this.sortStrategy = sortStrategy;
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        if (searchStrategy == null) {
            throw new IllegalArgumentException("Search strategy cannot be null");
        }
        this.searchStrategy = searchStrategy;
    }


    public void addSinger(Singer singer) {
        if (singer == null) {
            throw new IllegalArgumentException("Singer cannot be null");
        }
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
        if (newList == null) {
            throw new IllegalArgumentException("Singer list cannot be null");
        }
        this.singers = new ArrayList<>(newList);
    }

    public void sortCompositions(ArrayList<Composition> compositions) {
        System.out.println("The sorting algorithm chosen: " + sortStrategy.getAlgorithmName() + "\n");
        sortStrategy.sort(compositions);
    }

    public Composition searchComposition(ArrayList<Composition> compositions, String key) {
        System.out.println("The search algorithm chosen: " + searchStrategy.getAlgorithmName() + "\n");
        return searchStrategy.search(compositions, key);
    }
}
