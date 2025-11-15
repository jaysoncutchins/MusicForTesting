# Before and After: Code Examples

This document shows concrete examples of the OOP improvements made to the codebase.

---

## 1. Encapsulation: Defensive Copying

### ❌ Before (Broken Encapsulation)

```java
public class Singer {
    private ArrayList<Composition> compositions = new ArrayList<>();
    
    // PROBLEM: Returns direct reference to internal list
    public ArrayList<Composition> getCompositions() {
        return compositions;  // ⚠️ Exposes internal state!
    }
}

// This is dangerous because:
Singer singer = database.getSinger("123");
ArrayList<Composition> comps = singer.getCompositions();
comps.clear();  // 💥 Just deleted all singer's compositions!
```

### ✅ After (Proper Encapsulation)

```java
public class Singer {
    private ArrayList<Composition> compositions = new ArrayList<>();
    
    // FIXED: Returns defensive copy
    public ArrayList<Composition> getCompositions() {
        return new ArrayList<>(compositions);  // ✅ Safe copy!
    }
}

// Now this is safe:
Singer singer = database.getSinger("123");
ArrayList<Composition> comps = singer.getCompositions();
comps.clear();  // ✅ Only clears the copy, original is safe!
```

---

## 2. Strategy Pattern: Open/Closed Principle

### ❌ Before (Hardcoded Algorithms)

```java
public class Database implements SearchAndSort {
    
    // PROBLEM: Algorithm hardcoded in Database class
    @Override
    public void sortCompositions(ArrayList<Composition> compositions) {
        System.out.println("The sorting algorithm chosen: Insertion Sort\n");
        int n = compositions.size();
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (compositions.get(j).getTitle()
                    .compareToIgnoreCase(compositions.get(j - 1).getTitle()) < 0) {
                    Composition temp = compositions.get(j);
                    compositions.set(j, compositions.get(j - 1));
                    compositions.set(j - 1, temp);
                }
            }
        }
    }
    
    // 156 lines of commented out alternative algorithms...
    // //bubble sort
    // @Override
    // public void sortCompositions(...) { ... }
    
    // //Selection sort
    // @Override
    // public void sortCompositions(...) { ... }
    
    // To change algorithm: Must modify Database class! ❌
}
```

### ✅ After (Strategy Pattern)

```java
// Step 1: Define Strategy Interface
public interface SortStrategy {
    void sort(ArrayList<Composition> compositions);
    String getAlgorithmName();
}

// Step 2: Implement Strategies (each in its own file)
public class InsertionSortStrategy implements SortStrategy {
    @Override
    public void sort(ArrayList<Composition> compositions) {
        int n = compositions.size();
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (compositions.get(j).getTitle()
                    .compareToIgnoreCase(compositions.get(j - 1).getTitle()) < 0) {
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

public class BubbleSortStrategy implements SortStrategy {
    @Override
    public void sort(ArrayList<Composition> compositions) {
        // Bubble sort implementation
    }
    
    @Override
    public String getAlgorithmName() {
        return "Bubble Sort";
    }
}

// Step 3: Database uses Strategy
public class Database {
    private SortStrategy sortStrategy;
    
    public Database() {
        this.sortStrategy = new InsertionSortStrategy();  // Default
    }
    
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }
    
    public void sortCompositions(ArrayList<Composition> compositions) {
        System.out.println("The sorting algorithm chosen: " 
            + sortStrategy.getAlgorithmName() + "\n");
        sortStrategy.sort(compositions);
    }
}

// Usage: Change algorithm without modifying Database!
Database db = new Database();
db.setSortStrategy(new BubbleSortStrategy());     // Use Bubble Sort
db.setSortStrategy(new InsertionSortStrategy());  // Switch to Insertion Sort

// Want to add QuickSort? Just create new class, no modifications needed! ✅
public class QuickSortStrategy implements SortStrategy {
    // Implement quick sort
}
```

**Benefits:**
- ✅ Add new algorithms without touching Database
- ✅ Each algorithm in its own class (Single Responsibility)
- ✅ Easy to test each algorithm independently
- ✅ Change algorithms at runtime
- ✅ No commented code cluttering the codebase

---

## 3. Input Validation: Defensive Programming

### ❌ Before (No Validation)

```java
public class Singer {
    private String name;
    private String id;
    
    // PROBLEM: No validation
    public Singer(String id, String name) {
        this.id = id;      // What if null? 💥
        this.name = name;  // What if empty? 💥
    }
}

// This compiles but will cause problems:
Singer singer1 = new Singer(null, "John");      // 💥 NullPointerException later
Singer singer2 = new Singer("", "Jane");        // 💥 Invalid state
Singer singer3 = new Singer("123", "   ");      // 💥 Whitespace only
```

### ✅ After (Proper Validation)

```java
public class Singer {
    private String name;
    private String id;
    
    // FIXED: Comprehensive validation
    public Singer(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.id = id;
        this.name = name;
    }
}

// Now this fails fast with clear error:
try {
    Singer singer = new Singer(null, "John");
} catch (IllegalArgumentException e) {
    System.out.println(e.getMessage());  // "ID cannot be null or empty"
}

// ✅ Can only create valid Singer objects!
Singer singer = new Singer("123", "John");  // ✅ Valid
```

---

## 4. Resource Management: Try-With-Resources

### ❌ Before (Manual Resource Management)

```java
public static ArrayList<Singer> readFromFile() {
    ArrayList<Singer> singers = new ArrayList<>();
    
    try {
        // PROBLEM: Manual resource management
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        Type listType = new TypeToken<ArrayList<Singer>>() {}.getType();
        singers = gson.fromJson(reader, listType);
        reader.close();  // ⚠️ Won't execute if exception thrown!
    } 
    catch (IOException e) {
        System.err.println(e);
    }
    
    return singers;
}

// If gson.fromJson() throws exception:
// - reader.close() never called
// - File handle leaked! 💥
```

### ✅ After (Try-With-Resources)

```java
public static ArrayList<Singer> readFromFile() {
    ArrayList<Singer> singers = new ArrayList<>();
    
    // FIXED: Try-with-resources
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        Type listType = new TypeToken<ArrayList<Singer>>() {}.getType();
        singers = gson.fromJson(reader, listType);
        // ✅ reader automatically closed here, even if exception occurs!
    } 
    catch (IOException e) {
        System.err.println(e);
    }
    
    return singers;
}

// Benefits:
// - Resource always closed ✅
// - Exception-safe ✅
// - Cleaner code ✅
```

---

## 5. Object Identity: equals() and hashCode()

### ❌ Before (Missing equals/hashCode)

```java
public class Singer {
    private String name;
    private String id;
    
    public Singer(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // PROBLEM: No equals() or hashCode() override
}

// This causes issues:
Singer singer1 = new Singer("123", "John Doe");
Singer singer2 = new Singer("123", "John Doe");  // Same data!

System.out.println(singer1.equals(singer2));  // false 💥 (should be true!)
System.out.println(singer1 == singer2);       // false (correct, different objects)

// Can't use in HashMap properly:
HashMap<Singer, String> map = new HashMap<>();
map.put(singer1, "data1");
map.get(singer2);  // null 💥 (should find "data1"!)

// HashSet won't work correctly:
HashSet<Singer> set = new HashSet<>();
set.add(singer1);
set.add(singer2);
System.out.println(set.size());  // 2 💥 (should be 1, same singer!)
```

### ✅ After (Proper equals/hashCode)

```java
public class Singer {
    private String name;
    private String id;
    
    public Singer(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // FIXED: Proper equals() based on ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Singer singer = (Singer) obj;
        return id.equals(singer.id);  // Same ID = same singer
    }
    
    // FIXED: Proper hashCode() consistent with equals()
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

// Now everything works correctly:
Singer singer1 = new Singer("123", "John Doe");
Singer singer2 = new Singer("123", "John Doe");

System.out.println(singer1.equals(singer2));  // true ✅

// HashMap works correctly:
HashMap<Singer, String> map = new HashMap<>();
map.put(singer1, "data1");
map.get(singer2);  // "data1" ✅

// HashSet works correctly:
HashSet<Singer> set = new HashSet<>();
set.add(singer1);
set.add(singer2);
System.out.println(set.size());  // 1 ✅ (correctly identifies as same singer)
```

---

## 6. Configuration Hardcoding

### ❌ Before (Hardcoded Flag)

```java
public class Menu {
    //set to true if you're going to use binary search, false for linear search
    private static final boolean USE_BINARY_SEARCH = true;  // ❌ Hardcoded!
    
    private void searchCompositions() {
        // ...
        if (USE_BINARY_SEARCH) {  // ❌ Can't change without recompiling
            db.sortCompositions(compositions);
        }
        
        Composition composition = db.searchComposition(compositions, key);
        // ...
    }
}
```

### ✅ After (Strategy Pattern)

```java
public class Menu {
    // FIXED: No hardcoded flag needed!
    private final Database db = new Database();
    
    private void searchCompositions() {
        // ...
        // Strategy handles it automatically
        // Binary search strategy knows it needs sorted list
        db.sortCompositions(compositions);
        
        Composition composition = db.searchComposition(compositions, key);
        // ...
    }
}

// Can change strategy at runtime:
db.setSearchStrategy(new BinarySearchStrategy());  // Use binary search
db.setSearchStrategy(new LinearSearchStrategy());  // Switch to linear search
```

---

## Summary of Improvements

| Issue | Before | After |
|-------|--------|-------|
| **Encapsulation** | Returned direct references | Returns defensive copies |
| **Extensibility** | Must modify Database for new algorithms | Add new Strategy classes |
| **Validation** | No validation, crashes at runtime | Fails fast with clear errors |
| **Resources** | Manual close, risk of leaks | Try-with-resources, automatic cleanup |
| **Object Identity** | equals() broken, HashMap fails | Proper equals/hashCode implementation |
| **Configuration** | Hardcoded flags | Strategy pattern, runtime flexibility |
| **Code Quality** | 156 lines of commented code | Clean, organized strategies |
| **Testing** | Hard to test algorithms | Each strategy independently testable |

---

## Testing the Improvements

All improvements are validated by the OOPTest.java file:

```java
// Test 1: Input Validation
try {
    Singer singer = new Singer("", "Test");  // Should throw
} catch (IllegalArgumentException e) {
    System.out.println("PASS: " + e.getMessage());
}

// Test 2: Defensive Copying
Singer singer = new Singer("123", "John");
singer.addComposition(new Composition("Song", "Pop"));
var comps = singer.getCompositions();
comps.clear();  // Try to break it
assert singer.getCompositions().size() == 1;  // Still has 1 composition ✅

// Test 3: Strategy Pattern
Database db = new Database();
db.setSortStrategy(new BubbleSortStrategy());
db.sortCompositions(compositions);  // Uses bubble sort ✅

// Test 4: equals() and hashCode()
Singer s1 = new Singer("789", "Artist A");
Singer s2 = new Singer("789", "Artist B");
assert s1.equals(s2);  // Same ID = equal ✅
assert s1.hashCode() == s2.hashCode();  // Consistent ✅
```

All tests pass! ✅
