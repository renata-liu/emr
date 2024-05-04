package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// represents a patient with a first and last name, birthday, PHN number, phone number, email, and list of appointments
public class Patient implements Writable {

    private String first;
    private String last;
    private int birthday;
    private int phn;
    private String phone;
    private String email;
    private ArrayList<Appointment> appointments;

    // REQUIRES: birthday is in YYYYMMDD format, PHN is 9 digits
    // EFFECTS: creates new patient with given first name, last name, age, birthday, PHN,
    //          and empty appointment list
    public Patient(String first, String last, int birthday, int phn) {
        this.first = first;
        this.last = last;
        this.birthday = birthday;
        this.phn = phn;
        appointments = new ArrayList<>();
    }


    // REQUIRES: phone number is in xxx-xxx-xxxx format
    // MODIFIES: this
    // EFFECTS: sets this.phone to phone
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // MODIFIES: this
    // EFFECTS: sets this.email to email
    public void setEmail(String email) {
        this.email = email;
    }

    // MODIFIES: this
    // EFFECTS: adds given appointment to appointments
    public void bookAppointment(Appointment a) {
        appointments.add(a);
    }

    // EFFECTS: returns patient as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("first", first);
        json.put("last", last);
        json.put("birthday", birthday);
        json.put("phn", phn);
        json.put("phone", phone);
        json.put("email", email);
        json.put("appointments", appointmentsToJson());
        return json;
    }

    // EFFECTS: returns appointments for this patient as a JSON array
    private JSONArray appointmentsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Appointment a : appointments) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }

    //getters
    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public int getBirthday() {
        return birthday;
    }

    public int getPhn() {
        return phn;
    }

    // EFFECTS: returns phone number
    //          if no phone number found, return "no phone number on file"
    public String getPhone() {
        if (phone != null) {
            return phone;
        }
        return "no phone number on file";
    }

    // EFFECTS: returns email
    //          if no email found, return "no email on file"
    public String getEmail() {
        if (email != null) {
            return email;
        }
        return "no email on file";
    }

    // REQUIRES: !appointments.isEmpty()
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }
}
