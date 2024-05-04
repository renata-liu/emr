package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Office application
public class OfficeApp {

    private static final String JSON_STORE = "./data/office.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Office office;
    private Scanner input;

    // EFFECTS: runs the office application
    public OfficeApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        office = new Office("UBC Hospital");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runOffice();
    }

    // MODIFIES: this
    // EFFECTS: processes user input for office
    public void runOffice() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayHome();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThanks for visiting!");
    }

    // MODIFIES: this
    // EFFECTS: initializes an office
    public void init() {
        office = new Office("UBC Hospital Clinic");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: prints all user actions from home screen
    public void displayHome() {
        System.out.println("Select from: ");
        System.out.println("\ta = add patient to office");
        System.out.println("\tf = find patient by PHN");
        System.out.println("\tb = book appointment");
        System.out.println("\tv = view all patients at the office");
        System.out.println("\ts = save office to file");
        System.out.println("\tl = load office from file");
        System.out.println("\tq = quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command from home page
    public void processCommand(String command) {
        if (command.equals("a")) {
            doAddPatient();
        } else if (command.equals("f")) {
            doFindPatient();
        } else if (command.equals("b")) {
            doBookAppointment();
        } else if (command.equals("v")) {
            doViewAllPatients();
        } else if (command.equals("s")) {
            saveOffice();
        } else if (command.equals("l")) {
            loadOffice();
        } else {
            System.out.println("Please enter a valid command");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a patient to the office
    public void doAddPatient() {
        System.out.print("Enter the patient's first name: ");
        String first = input.next();
        System.out.print("Enter the patient's last name: ");
        String last = input.next();
        System.out.print("Enter the patient's birthday (YYYYMMDD): ");
        int birthday = Integer.parseInt(input.next());
        System.out.print("Enter the patient's PHN (9-digits): ");
        int phn = Integer.parseInt(input.next());

        Patient newPatient = new Patient(first, last, birthday, phn);
        office.addPatient(newPatient);

        System.out.println(newPatient.getFirst() + " has been successfully added to the office!");
    }

    // MODIFIES: this
    // EFFECTS: searches a patient by PHN number and prints their summary
    public void doFindPatient() {
        System.out.print("Enter the PHN of the patient you would like to find (9-digits): ");
        int phn = Integer.parseInt(input.next());

        if (office.findPatient(phn)) {
            Patient found = office.getPatient(phn);
            System.out.println(found.getFirst() + " " + found.getLast() + ", " + found.getPhn());

            boolean keepGoing = true;
            while (keepGoing) {
                displayPatientAccessors();
                String command = input.next();
                command = command.toLowerCase();

                if (command.equals("b")) {
                    keepGoing = false;
                } else {
                    processPatientAccessorCommand(command, found);
                }
            }

        } else {
            System.out.println("No patient at this office has a the given PHN.");
        }
    }

    // MODIFIES: this
    // EFFECTS: prints possible user commands once in a patient's file
    public void displayPatientAccessors() {
        System.out.println("Select from: ");
        System.out.println("\th = view patient's appointment history");
        System.out.println("\te = set patient's email");
        System.out.println("\tp = set patient's phone number");
        System.out.println("\tb = back to home");
    }

    // MODIFIES: this
    // EFFECTS: processes user command once in a patient's file
    public void processPatientAccessorCommand(String command, Patient p) {
        if (command.equals("h")) {
            doViewPatientHistory(p);
        } else if (command.equals("e")) {
            doSetPatientEmail(p);
        } else if (command.equals("p")) {
            doSetPatientPhone(p);
        } else {
            System.out.println("Please enter a valid command");
        }
    }

    // MODIFIES: this
    // EFFECTS: prints patient's appointment history
    public void doViewPatientHistory(Patient p) {
        if (p.getAppointments().size() != 0) {
            for (Appointment a : p.getAppointments()) {
                System.out.println(a.getAppointmentDetails());
            }
        } else {
            System.out.println(p.getFirst() + " has never had an appointment with this office.");
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the given patient's email per user input
    public void doSetPatientEmail(Patient p) {
        System.out.print("Enter the patient's email: ");
        String email = input.next();
        p.setEmail(email);
        System.out.println(p.getFirst() + "'s email has been successfully set to " + email);
    }

    // MODIFIES: this
    // EFFECTS: sets the given patient's phone number per user input
    public void doSetPatientPhone(Patient p) {
        System.out.print("Enter the patient's phone number: ");
        String phone = input.next();
        p.setPhone(phone);
        System.out.println(p.getFirst() + "'s phone number has been successfully set to " + phone);
    }

    // MODIFIES: this
    // EFFECTS: books an appointment for a patient
    public void doBookAppointment() {
        System.out.print("Enter the PHN of the patient you would like to book an appointment for (9-digits): ");
        int phn = Integer.parseInt(input.next());

        if (office.findPatient(phn)) {
            Appointment appt = createAppointment();
            Patient p = office.getPatient(phn);
            p.bookAppointment(appt);
            System.out.println("An appointment has been booked for " + p.getFirst() + " on "
                    + appt.getAppointmentDate());
        } else {
            System.out.println("The person with the associated PHN is not a patient at this office.");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates an appointment according to user input
    public Appointment createAppointment() {
        System.out.print("Enter the month of appointment: ");
        String month = input.next();
        System.out.print("Enter the date of appointment: ");
        int date = Integer.parseInt(input.next());
        System.out.print("Enter the year of appointment: ");
        int year = Integer.parseInt(input.next());
        System.out.print("Enter the time of appointment (military time, xx:xx): ");
        String time = input.next();
        System.out.print("Enter doctor's name: ");
        String doctor = input.next();
        System.out.print("Will the appointment be in-person (y/n)? ");
        String inPerson = input.next();

        if (inPerson.equals("y")) {
            return (new Appointment(month, date, year, time, doctor, true));
        } else {
            return (new Appointment(month, date, year, time, doctor, false));
        }
    }

    // MODIFIES: this
    // EFFECTS: prints all patients at the offices
    public void doViewAllPatients() {
        System.out.println("All patients at " + office.getName() + ":");
        for (Patient p : office.getAllPatients()) {
            System.out.print(p.getFirst() + " " + p.getLast() + "\n");
        }
    }

    // EFFECTS: saves the office to file
    private void saveOffice() {
        try {
            jsonWriter.open();
            jsonWriter.write(office);
            jsonWriter.close();
            System.out.println("Saved " + office.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads office from file
    private void loadOffice() {
        try {
            office = jsonReader.read();
            System.out.println("Loaded " + office.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
