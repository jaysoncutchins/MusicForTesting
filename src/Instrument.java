public class Instrument {
    private String name;
    private String type;
    private String style;


    public Instrument(String name, String type, String style) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (style == null || style.trim().isEmpty()) {
            throw new IllegalArgumentException("Style cannot be null or empty");
        }
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Instrument that = (Instrument) obj;
        return name.equals(that.name) && type.equals(that.type) && style.equals(that.style);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + style.hashCode();
        return result;
    }
}
