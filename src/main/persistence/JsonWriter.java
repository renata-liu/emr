package persistence;

import org.json.JSONObject;
import model.Office;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
/***************************************************************************************
 *    (1)
 *    Title: CPSC210 JsonSerializationDemo
 *    Date: 2020
 *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 ***************************************************************************************/

// Represents a writer that writes JSON representation of office to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // (1)
    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // (1)
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(Office o) {
        JSONObject json = o.toJson();
        saveToFile(json.toString(TAB));
    }

    // (1)
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // (1)
    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}