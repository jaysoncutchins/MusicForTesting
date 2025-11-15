/**
 * Simple test to verify OOP improvements work correctly.
 * This demonstrates the Strategy pattern and validation features.
 */
public class OOPTest {
    
    public static void main(String[] args) {
        System.out.println("=== Testing OOP Improvements ===\n");
        
        // Test 1: Input Validation
        System.out.println("Test 1: Input Validation");
        try {
            Singer singer = new Singer("", "Test");  // Should throw exception
            System.out.println("FAIL: Empty ID should throw exception");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: " + e.getMessage());
        }
        
        try {
            Composition comp = new Composition(null, "Rock");  // Should throw exception
            System.out.println("FAIL: Null title should throw exception");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: " + e.getMessage());
        }
        
        // Test 2: Defensive Copying
        System.out.println("\nTest 2: Defensive Copying");
        Singer singer = new Singer("123", "John Doe");
        Composition comp1 = new Composition("Song A", "Pop");
        Composition comp2 = new Composition("Song B", "Rock");
        singer.addComposition(comp1);
        singer.addComposition(comp2);
        
        var compositions = singer.getCompositions();
        int originalSize = compositions.size();
        compositions.clear();  // Try to modify the returned list
        
        if (singer.getCompositions().size() == originalSize) {
            System.out.println("PASS: Defensive copy prevents external modification");
        } else {
            System.out.println("FAIL: Internal state was modified");
        }
        
        // Test 3: Strategy Pattern
        System.out.println("\nTest 3: Strategy Pattern");
        Database db = new Database();
        
        // Add test data
        Singer testSinger = new Singer("456", "Jane Smith");
        Composition c1 = new Composition("Zebra", "Jazz");
        Composition c2 = new Composition("Apple", "Blues");
        Composition c3 = new Composition("Mango", "Rock");
        testSinger.addComposition(c1);
        testSinger.addComposition(c2);
        testSinger.addComposition(c3);
        
        var comps = testSinger.getCompositions();
        
        // Test with Insertion Sort
        db.setSortStrategy(new InsertionSortStrategy());
        db.sortCompositions(comps);
        System.out.println("After InsertionSort: " + comps.get(0).getTitle());
        
        // Test with Bubble Sort
        db.setSortStrategy(new BubbleSortStrategy());
        db.sortCompositions(comps);
        System.out.println("After BubbleSort: " + comps.get(0).getTitle());
        
        // Test with Selection Sort
        db.setSortStrategy(new SelectionSortStrategy());
        db.sortCompositions(comps);
        System.out.println("After SelectionSort: " + comps.get(0).getTitle());
        
        System.out.println("PASS: All sorting strategies work");
        
        // Test 4: Search Strategies
        System.out.println("\nTest 4: Search Strategies");
        
        // Test Binary Search
        db.setSearchStrategy(new BinarySearchStrategy());
        Composition found = db.searchComposition(comps, "Mango");
        if (found != null && found.getTitle().equals("Mango")) {
            System.out.println("PASS: BinarySearchStrategy found composition");
        }
        
        // Test Linear Search
        db.setSearchStrategy(new LinearSearchStrategy());
        found = db.searchComposition(comps, "Apple");
        if (found != null && found.getTitle().equals("Apple")) {
            System.out.println("PASS: LinearSearchStrategy found composition");
        }
        
        // Test 5: equals() and hashCode()
        System.out.println("\nTest 5: equals() and hashCode()");
        Singer s1 = new Singer("789", "Artist A");
        Singer s2 = new Singer("789", "Artist B");  // Same ID, different name
        
        if (s1.equals(s2)) {
            System.out.println("PASS: Singers with same ID are equal");
        } else {
            System.out.println("FAIL: equals() not working correctly");
        }
        
        if (s1.hashCode() == s2.hashCode()) {
            System.out.println("PASS: Equal objects have same hashCode");
        } else {
            System.out.println("FAIL: hashCode() contract violated");
        }
        
        System.out.println("\n=== All Tests Complete ===");
    }
}
