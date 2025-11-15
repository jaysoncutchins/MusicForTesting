public class Instrument {
    private final String name;
    private final String type;
    private final String style;


    public Instrument(String name, String type, String style) {
        this.name = name;
        this.type = type;
        this.style = style;
    }


    public String getName() {
        return name;
    }

    
    @Override
    public String toString() {
        return "\tName: " + name + "\n" +
                "\t\tType: " + type + "\n" +
                "\t\tStyle: " + style;
    }
}
