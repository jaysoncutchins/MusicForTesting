import java.util.ArrayList;

public class Composition {
    private String title;
    private String genre;
    private ArrayList<Instrument> instruments = new ArrayList<>();

    
    public Composition(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }


    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }


    public void removeInstrument(String name) {
        for (Instrument instrument : instruments) {
            if (instrument.getName().equals(name)) {
                instruments.remove(instrument);
                return;
            }
        }
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        int size = instruments.size();
        String outputString = "Title: " + title + "\n" +
                "Genre: " + genre + "\n" +
                "Instrument count: " + size + "\n";

        if (size >= 1) {
            outputString += "Intruments:\n";
            for (Instrument instrument : instruments) {
                outputString += instrument + "\n";
            }
        }
        return outputString;

    }

}