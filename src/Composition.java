import java.util.ArrayList;

public class Composition {
    private String title;
    private String genre;
    private ArrayList<Instrument> instruments = new ArrayList<>();

    
    public Composition(String title, String genre) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }
        this.title = title;
        this.genre = genre;
    }


    public void addInstrument(Instrument instrument) {
        if (instrument == null) {
            throw new IllegalArgumentException("Instrument cannot be null");
        }
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
            outputString += "Instruments:\n";
            for (Instrument instrument : instruments) {
                outputString += instrument + "\n";
            }
        }
        return outputString;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Composition that = (Composition) obj;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

}