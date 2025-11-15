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
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n")
          .append("Genre: ").append(genre).append("\n")
          .append("Instrument count: ").append(size).append("\n");

        if (size >= 1) {
            sb.append("Intruments:\n");
            for (Instrument instrument : instruments) {
                sb.append(instrument).append("\n");
            }
        }
        return sb.toString();
    }

}