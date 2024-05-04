package persistence;

import model.Office;
import model.Appointment;
import model.Patient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
/***************************************************************************************
 *    (1)
 *    Title: CPSC210 JsonSerializationDemo
 *    Date: 2020
 *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 ***************************************************************************************/

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // (1)
    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads office from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Office read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseOffice(jsonObject);
    }

    // (1)
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses office from JSON object and returns it
    private Office parseOffice(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Office o = new Office(name);
        addPatients(o, jsonObject);
        return o;
    }

    // MODIFIES: o
    // EFFECTS: parses patients from JSON object and adds them to the office
    private void addPatients(Office o, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("allPatients");
        for (Object json : jsonArray) {
            JSONObject nextPatient = (JSONObject) json;
            addPatient(o, nextPatient);
        }
    }

    // MODIFIES: o
    // EFFECTS: parses patient from JSON object and adds it to the office
    private void addPatient(Office o, JSONObject jsonObject) {
        String first = jsonObject.getString("first");
        String last = jsonObject.getString("last");
        int birthday = jsonObject.getInt("birthday");
        int phn = jsonObject.getInt("phn");
        Patient patient = new Patient(first, last, birthday, phn);
        addAppointments(patient, jsonObject);
        o.addPatient(patient);
    }

    // MODIFIES: o
    // EFFECTS: parses appointments from JSON patient object and adds them to the patient
    private void addAppointments(Patient p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("appointments");
        for (Object json : jsonArray) {
            JSONObject nextAppointment = (JSONObject) json;
            addAppointment(p, nextAppointment);
        }
    }

    // MODIFIES: o
    // EFFECTS: parses appointments from JSON patient object and adds it to the patient
    private void addAppointment(Patient p, JSONObject jsonObject) {
        String month = jsonObject.getString("month");
        int day = jsonObject.getInt("day");
        int year = jsonObject.getInt("year");
        String time = jsonObject.getString("time");
        String doctor = jsonObject.getString("doctor");
        boolean mode = jsonObject.getBoolean("mode");
        Appointment a = new Appointment(month, day, year, time, doctor, mode);
        p.bookAppointment(a);
    }
}