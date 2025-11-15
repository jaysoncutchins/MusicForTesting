import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JSONReadAndWrite {
    private static final String FILE_NAME = "MusicDB.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public static ArrayList<Singer> readFromFile() {
        ArrayList<Singer> singers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            Type listType = new TypeToken<ArrayList<Singer>>() {
            }.getType();
            singers = gson.fromJson(reader, listType);
        } 
        catch (IOException e) {
            System.err.println(e);
        }

        return singers;
    }

    
    public static void writeToFile(ArrayList<Singer> singers) {
        String jsonStr = gson.toJson(singers);

        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            writer.write(jsonStr);
        } 
        catch (IOException e) {
            System.err.println(e);
        }
    }

    
    public static String getPrettyJson(ArrayList<Singer> singers) {
        return gson.toJson(singers);
    }
}
