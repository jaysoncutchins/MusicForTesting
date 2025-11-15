import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final Database db = new Database();
    
    //set to true if you're going to use binary search, false if you're going to use linear search
    private static final boolean USE_BINARY_SEARCH = true;


    public void runMenu() {
        while (true) {
            System.out.println("\n--------------- Singer Database system ---------------");
            System.out.println("1. Add singer to database");
            System.out.println("2. Remove singer from database");
            System.out.println("3. Assign composition to singer");
            System.out.println("4. Remove composition from singer");
            System.out.println("5. Add instrument to composition");
            System.out.println("6. Remove instrument from composition");
            System.out.println("7. Update a composition's genre");
            System.out.println("8. View a singer's compositions");
            System.out.println("9. View all singers");
            System.out.println("10. Load database from JSON");
            System.out.println("11. Save database to JSON");
            System.out.println("12. Sort a singer's compositions by title");
            System.out.println("13. Search a singer's compositions by title");
            System.out.println("14. Exit");

            System.out.print("\nSelect an option from the list: ");
            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (choice) {
                case 1 -> addSinger();
                case 2 -> removeSinger();
                case 3 -> addComposition();
                case 4 -> removeComposition();
                case 5 -> addInstrument();
                case 6 -> removeInstrument();
                case 7 -> updateCompositionGenre();
                case 8 -> viewSingerCompositions();
                case 9 -> viewAllSingers();
                case 10 -> loadDatabaseFromJson();
                case 11 -> saveDatabaseToJson();
                case 12 -> sortCompositions();
                case 13 -> searchCompositions();
                case 14 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    //HELPERS
    private Singer promptAndGetSinger() {
        System.out.print("Enter the singer's ID: ");
        String id = scanner.nextLine();
        Singer singer = db.getSinger(id);
        if (singer == null) {
            System.out.println("Singer not found.");
            return null;
        }
        return singer;
    }


    private Composition promptAndGetComposition(Singer singer) {
        System.out.print("Enter the composition's title: ");
        String title = scanner.nextLine();
        Composition composition = singer.getComposition(title);
        if (composition == null) {
            System.out.println("Composition not found.");
            return null;
        }
        return composition;
    }


    private void promptSave() {
        System.out.print("Would you like to save this update to JSON? (Y/N): ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("Y")) {
            saveDatabaseToJson();
            System.out.println("Changes saved.");
        }
    }


    //LIST METHODS
    private void addSinger() {
        System.out.print("Enter the singer's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the singer's ID: ");
        String id = scanner.nextLine();
        Singer singer = new Singer(id, name);
        db.addSinger(singer);
        System.out.println("Singer added.");
        promptSave();
    }


    private void removeSinger() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }
        db.removeSinger(singer);
        System.out.println("Singer removed.");
        promptSave();
    }


    private void addComposition() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }

        System.out.print("Enter composition title: ");
        String title = scanner.nextLine();
        System.out.print("Enter composition genre: ");
        String genre = scanner.nextLine();
        Composition composition = new Composition(title, genre);

        singer.addComposition(composition);
        System.out.println("Composition added.");
        promptSave();
    }


    private void removeComposition() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }
        Composition composition = promptAndGetComposition(singer);
        if (composition == null) {
            return;
        }
        singer.removeComposition(composition.getTitle());
        System.out.println("Composition removed.");
        promptSave();
    }


    private void addInstrument() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }
        Composition composition = promptAndGetComposition(singer);
        if (composition == null) {
            return;
        }

        System.out.print("Instrument name: ");
        String name = scanner.nextLine();
        System.out.print("Instrument type: ");
        String type = scanner.nextLine();
        System.out.print("Instrument style: ");
        String style = scanner.nextLine();

        Instrument instrument = new Instrument(name, type, style);
        composition.addInstrument(instrument);
        System.out.println("Instrument added to composition.");
        promptSave();
    }


    private void removeInstrument() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }
        Composition composition = promptAndGetComposition(singer);
        if (composition == null) {
            return;
        }
        System.out.print("Instrument name: ");
        String name = scanner.nextLine();

        composition.removeInstrument(name);
        System.out.println("Instrument removed from composition.");
        promptSave();
    }


    private void updateCompositionGenre() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }
        Composition composition = promptAndGetComposition(singer);
        if (composition == null) {
            return;
        }
        System.out.print("Enter the new genre: ");
        String genre = scanner.nextLine();

        composition.setGenre(genre);
        System.out.println("Genre updated.");
        promptSave();
    }


    private void viewSingerCompositions() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }
        System.out.println("Compositions for " + singer.getName() + ":");
        singer.displayCompositions();
    }


    private void viewAllSingers() {
        db.displayAllSingers();
    }


    private void loadDatabaseFromJson() {
        ArrayList<Singer> singers = JSONReadAndWrite.readFromFile();
        db.setAllSingers(singers);
        System.out.println("Database loaded from JSON:");
        System.out.println(JSONReadAndWrite.getPrettyJson(singers));
    }


    private void saveDatabaseToJson() {
        JSONReadAndWrite.writeToFile(db.getAllSingers());
    }


    private void sortCompositions() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }
        db.sortCompositions(singer.getCompositions());

        System.out.printf("All %s compositions sorted by title:\n", singer.getName());
        for (Composition composition : singer.getCompositions()) {
            System.out.println(composition);
        }
        promptSave();
    }


    private void searchCompositions() {
        Singer singer = promptAndGetSinger();
        if (singer == null) {
            return;
        }
        System.out.print("Composition title to search for: ");
        String key = scanner.nextLine();

        ArrayList<Composition> compositions = singer.getCompositions();

        if (USE_BINARY_SEARCH) { //we have to sort the list first if we're using binary search
            db.sortCompositions(compositions);
        }

        Composition composition = db.searchComposition(compositions, key);
        if (composition == null) {
            System.out.println("No composition matched that key.");
            return;
        }
        System.out.println("Composition found:\n" + composition);
    }
}
