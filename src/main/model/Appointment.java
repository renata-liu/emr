package model;

import org.json.JSONObject;
import persistence.Writable;

// represents an appointment booked for a specific month, day, year, time, and reason with a doctor
// either in-person or virtual (mode)
public class Appointment implements Writable {

    private String month;
    private int day;
    private int year;
    private String time;
    private String doctor;
    private boolean mode;
    private String reason;

    // REQUIRES: time in military time xx:xx
    // EFFECTS: creates new appointment on given month, day, year with given doctor
    //          for mode, true = in-person, false = virtual
    public Appointment(String month, int day, int year, String time, String doctor, boolean mode) {
        this.month = month;
        this.day = day;
        this.year = year;
        this.time = time;
        this.doctor = doctor;
        this.mode = mode;
    }

    // EFFECTS: returns appointment date in "month day, year" format
    public String getAppointmentDate() {
        return (month + " " + day + ", " + year);
    }

    // MODIFIES: this
    // EFFECTS: sets reason for appointment as given reason
    public void setReason(String reason) {
        this.reason = reason;
    }

    // EFFECTS: returns appointment details with mode, doctor, appointment date, and reason if applicable
    public String getAppointmentDetails() {
        if (reason != null) {
            return (getMode() + " appointment with " + doctor + " on " + getAppointmentDate() + " for " + reason);
        }
        return (getMode() + " appointment with " + doctor + " on " + getAppointmentDate());
    }

    // EFFECTS: returns "in-person" if mode is true and "virtual" if mode is false
    public String getMode() {
        if (isInPerson()) {
            return "In-person";
        } else {
            return "Virtual";
        }
    }

    // EFFECTS: returns appointment as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("month", month);
        json.put("day", day);
        json.put("year", year);
        json.put("time", time);
        json.put("doctor", doctor);
        json.put("mode", mode);
        json.put("reason", reason);
        return json;
    }


    // getters

    // EFFECTS: returns true if appointment is in-person
    public boolean isInPerson() {
        return mode;
    }

    public String getTime() {
        return time;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String getReason() {
        return reason;
    }

}
