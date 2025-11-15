# Music Database System

A Java-based console application for managing singers, their compositions, and instruments.

## Features

- **Singer Management**: Add and remove singers from the database
- **Composition Management**: Assign compositions to singers with genre information
- **Instrument Tracking**: Add instruments to compositions with type and style details
- **Search & Sort**: Multiple algorithm implementations (Binary Search, Insertion Sort)
- **Data Persistence**: Save and load database to/from JSON format
- **Interactive Menu**: User-friendly command-line interface

## Project Structure

```
MusicForTesting/
├── src/
│   ├── Main.java              # Entry point
│   ├── Menu.java              # User interface and menu logic
│   ├── Database.java          # Data storage and algorithms
│   ├── Singer.java            # Singer entity
│   ├── Composition.java       # Composition entity
│   ├── Instrument.java        # Instrument entity
│   ├── SearchAndSort.java     # Interface for algorithms
│   └── JSONReadAndWrite.java  # JSON persistence layer
├── lib/
│   └── gson-2.13.1.jar        # JSON library
├── MusicDB.json               # Database file
└── MusicDB_BACKUP.json        # Backup database file
```

## Requirements

- Java 14 or higher (uses switch expressions)
- Gson library (included in `lib/`)

## How to Build

Compile all Java source files:

```bash
javac -cp ".:lib/gson-2.13.1.jar" -d . src/*.java
```

On Windows, use semicolon for classpath:
```bash
javac -cp ".;lib/gson-2.13.1.jar" -d . src/*.java
```

## How to Run

```bash
java -cp ".:lib/gson-2.13.1.jar" Main
```

On Windows:
```bash
java -cp ".;lib/gson-2.13.1.jar" Main
```

## Usage

The application presents a menu with 14 options:

1. **Add singer** - Create a new singer with ID and name
2. **Remove singer** - Delete a singer from the database
3. **Assign composition** - Add a composition to a singer
4. **Remove composition** - Remove a composition from a singer
5. **Add instrument** - Add an instrument to a composition
6. **Remove instrument** - Remove an instrument from a composition
7. **Update genre** - Change a composition's genre
8. **View compositions** - Display all compositions for a singer
9. **View all singers** - Display all singers in the database
10. **Load from JSON** - Load database from MusicDB.json
11. **Save to JSON** - Save database to MusicDB.json
12. **Sort compositions** - Sort a singer's compositions alphabetically
13. **Search compositions** - Search for a composition by title
14. **Exit** - Quit the application

## Algorithm Implementations

The project includes multiple sorting and searching algorithms:

### Active Algorithms
- **Insertion Sort** - Currently active for sorting compositions
- **Binary Search** - Currently active for searching compositions

### Available (Commented) Algorithms
- Bubble Sort
- Selection Sort
- Merge Sort
- Linear Search

To switch algorithms, uncomment the desired implementation in `Database.java` and comment out the current one.

## Data Model

### Singer
- ID (String)
- Name (String)
- Compositions (List)

### Composition
- Title (String)
- Genre (String)
- Instruments (List)

### Instrument
- Name (String)
- Type (String)
- Style (String)

## Example Workflow

1. Start the application
2. Select option 10 to load existing data from JSON
3. Select option 9 to view all singers
4. Select option 8 to view a specific singer's compositions
5. Select option 3 to add a new composition
6. Select option 11 to save changes to JSON
7. Select option 14 to exit

## Known Issues

See [CODE_REVIEW.md](CODE_REVIEW.md) for a detailed analysis of code quality and areas for improvement.

## Future Improvements

- Add error handling for invalid user input
- Implement input validation
- Add unit tests
- Refactor Menu class using MVC pattern
- Add logging framework
- Implement Strategy pattern for algorithm selection
- Add configuration file

## Author

This is a learning project demonstrating:
- Object-oriented programming in Java
- Data structures and algorithms
- JSON serialization/deserialization
- Console-based user interfaces

## License

Educational/Personal Project
