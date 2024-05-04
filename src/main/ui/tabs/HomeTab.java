package ui.tabs;

import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.ButtonNames;
import model.*;
import ui.OfficeUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the "Home" menu tab in GUI side panel
public class HomeTab extends Tab {

    private static final String INIT_GREETING = "Welcome to the office!";
    private JLabel greeting;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/office.json";
    private Office office;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(OfficeUI controller) {
        super(controller);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setLayout(new GridLayout(5, 1));

        placeGreeting();
        placeHomeButtons();
        placeAddPatientButton();
        placeLogo();
    }

    //MODIFIES: this
    //EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    //MODIFIES: this
    //EFFECTS: creates "add patient", "save office", and "load office" buttons to screen
    private void placeHomeButtons() {
        JButton b1 = new JButton(ButtonNames.SAVE_OFFICE.getValue());
        JButton b2 = new JButton(ButtonNames.LOAD_OFFICE.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(e -> {
            Office office = getController().getOffice();
            writeOffice(office);
            EventLog.getInstance().logEvent(new Event("Office was saved to file"));
        });

        b2.addActionListener(e -> {
            loadOffice();
        });

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: writes office represented by getController() to office.json
    private void writeOffice(Office office) {
        try {
            jsonWriter.open();
            jsonWriter.write(office);
            jsonWriter.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads office from office.json
    private void loadOffice() {
        try {
            office = jsonReader.read();
            getController().loadNewViewPatientsTab(office.getAllPatients());
            EventLog.getInstance().logEvent(new Event("Office was loaded from file"));
        } catch (IOException exception) {
            System.out.println("Unable to read from file");
        }
    }

    //MODIFIES: this
    //EFFECTS: constructs an add patient button that switches to the add patient tab on the console
    private void placeAddPatientButton() {
        JPanel addPatientBlock = new JPanel();
        JButton addPatientButton = new JButton(ButtonNames.ADD_PATIENT.getValue());
        addPatientBlock.add(formatButtonRow(addPatientButton));

        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.ADD_PATIENT.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(OfficeUI.ADD_PATIENT_TAB_INDEX);
                }
            }
        });

        this.add(addPatientBlock);
    }

    // MODIFIES: this
    // EFFECTS: places the logo in the top-right corner of the panel
    private void placeLogo() {
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/ui/images/ubc-logo.png"));
        JLabel logoLabel = new JLabel(logoIcon);

        logoLabel.setPreferredSize(new Dimension(600, 600));

        add(logoLabel);
    }

}
