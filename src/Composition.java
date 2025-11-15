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
        instruments.removeIf(instrument -> instrument.getName().equals(name));
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
        StringBuilder outputString = new StringBuilder("Title: " + title + "\n" +
                "Genre: " + genre + "\n" +
                "Instrument count: " + size + "\n");

        if (size >= 1) {
            outputString.append("Intruments:\n");
            for (Instrument instrument : instruments) {
                outputString.append(instrument).append("\n");
            }
        }
        return outputString.toString();

    }

}