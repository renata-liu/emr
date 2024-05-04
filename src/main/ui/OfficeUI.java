package ui;

import model.*;
import javax.swing.*;

import model.exceptions.LogException;
import ui.tabs.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

//GUI for "UBC Hospital" office
public class OfficeUI extends JFrame {
    public static final int HOME_TAB_INDEX = 0;
    public static final int VIEW_PATIENT_TAB_INDEX = 1;
    public static final int ADD_PATIENT_TAB_INDEX = 2;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private JTabbedPane sidebar;
    private Office office;

    public static void main(String[] args) {
        new OfficeUI();
    }

    //MODIFIES: this
    //EFFECTS: creates OfficeUI, displays main menu, sidebar, and tabs
    public OfficeUI() {
        super("Office Console");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                LogPrinter lp = new ConsolePrinter();
                try {
                    lp.printLog(EventLog.getInstance());
                } catch (LogException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        office = new Office("UBC Hospital");

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    //EFFECTS: returns Office object controlled by this UI
    public Office getOffice() {
        return office;
    }

    //MODIFIES: this
    //EFFECTS: adds home tab, settings tab and report tab to this UI
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel viewPatientsTab = new ViewPatientsTab(this);
        JPanel addPatientTab = new AddPatientTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(viewPatientsTab, VIEW_PATIENT_TAB_INDEX);
        sidebar.setTitleAt(VIEW_PATIENT_TAB_INDEX, "View Patient");
        sidebar.add(addPatientTab, ADD_PATIENT_TAB_INDEX);
        sidebar.setTitleAt(ADD_PATIENT_TAB_INDEX, "Add Patient");
    }


    //MODIFIES: this
    //EFFECTS: updates view patients tab when an office is loaded from office.json or patient is added
    public void loadNewViewPatientsTab(ArrayList<Patient> patients) {
        ViewPatientsTab viewPatientsTab = (ViewPatientsTab) sidebar.getComponentAt(VIEW_PATIENT_TAB_INDEX);
        viewPatientsTab.updatePatients(patients);
    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }
}
