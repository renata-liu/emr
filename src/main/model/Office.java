package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a healthcare office with a name (String)
public class Office implements Writable {

    private String name;
    private ArrayList<Patient> allPatients;

    // EFFECTS: constructs new office with given name
    public Office(String name) {
        this.name = name;
        allPatients = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given patient to allPatients
    public void addPatient(Patient p) {
        allPatients.add(p);
        EventLog.getInstance().logEvent(new Event(p.getFirst() + " " + p.getLast() + " was added to the office"));
    }

    // EFFECTS: returns true if patient is in allPatients
    public boolean findPatient(int phn) {
        for (Patient pt: allPatients) {
            if (pt.getPhn() == phn) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: sorts patients by last name from A-Z
    public List<Patient> alphabetizeByLast() {
        ArrayList<Patient> sortedPatients = new ArrayList<>(allPatients);

        Collections.sort(sortedPatients, new Comparator<Patient>() {
            @Override
            public int compare(Patient p1, Patient p2) {
                return p1.getLast().compareTo(p2.getLast());
            }
        });

        allPatients = sortedPatients;
        EventLog.getInstance().logEvent(new Event("All patients were sorted by last name"));
        return sortedPatients;
    }

    // REQUIRES: patient with given phn is in the office
    // MODIFIES: this
    // EFFECTS: removes patient with given PHN from the office
    public void removePatient(int phn) {
        Patient p = getPatient(phn);
        allPatients.remove(p);
        EventLog.getInstance().logEvent(new Event("Patient with the PHN " + phn + " was removed"));
    }

    // REQUIRES: findPatient == true
    // EFFECTS: returns patient with given PHN
    public Patient getPatient(int phn) {
        for (Patient pt: allPatients) {
            if (pt.getPhn() == phn) {
                return pt;
            }
        }
        return null;
    }

    // EFFECTS: returns office as a JSON array
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("allPatients", patientsToJson());
        return json;
    }

    // EFFECTS: returns patients in this office as a JSON array
    private JSONArray patientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Patient p : allPatients) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    //getters
    public String getName() {
        return name;
    }

    public ArrayList<Patient> getAllPatients() {
        return allPatients;
    }

    public int getNumPatients() {
        return allPatients.size();
    }
}
