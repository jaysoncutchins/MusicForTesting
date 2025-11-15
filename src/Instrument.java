public class Instrument {
    private String name;
    private String type;
    private String style;


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
