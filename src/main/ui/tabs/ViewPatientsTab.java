package ui.tabs;

import model.Office;
import model.Patient;
import ui.ButtonNames;
import ui.OfficeUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

//Represents "view patient" tab in GUI side panel
//Displays all patients in the office
public class ViewPatientsTab extends Tab {

    private static final String TITLE_TEXT = "All Patients";

    private static final int COLS_IN_ROW = 5;
    private static final int ROW_HEIGHT = 50;
    private static final int LEFT_PADDING = 70;
    private static final int BOTTOM_PADDING = 5;
    private static final int CENTER = SwingConstants.CENTER;
    private static final int TOP = SwingConstants.TOP;

    private Border border;
    private GridLayout rowLayout;
    private JLabel title;

    //EFFECTS: creates view patients tab that displays all patients in the office with
    //         last name, first name, PHN, birthday, and view appointments columns
    public ViewPatientsTab(OfficeUI controller) {
        super(controller);
        initializeLayout();
        for (Patient p : getController().getOffice().getAllPatients()) {
            this.add(displayPatient(p));
        }
    }

    //MODIFIES: this
    //EFFECTS: builds skeleton format of the tab
    private void initializeLayout() {
        border = BorderFactory.createEmptyBorder(0, LEFT_PADDING, BOTTOM_PADDING, 0);
        rowLayout = new GridLayout(1, COLS_IN_ROW);

        title = new JLabel(TITLE_TEXT);
        JButton sortByLast = new JButton(ButtonNames.SORT_BY_LAST.getValue());
        add(title);
        add(sortByLast);

        sortByLast.addActionListener(e -> {
            Office office = getController().getOffice();
            office.alphabetizeByLast();
            updatePatients(office.getAllPatients());
        });

        JPanel headerRow = new JPanel(rowLayout);
        headerRow.setPreferredSize(new Dimension(OfficeUI.WIDTH - LEFT_PADDING, ROW_HEIGHT));
        headerRow.add(new JLabel("Last", CENTER));
        headerRow.add(new JLabel("First", CENTER));
        headerRow.add(new JLabel("PHN", CENTER));
        headerRow.add(new JLabel("Birthday", CENTER));
        headerRow.add(new JLabel("Remove Patient", CENTER));

        add(headerRow);
    }

    //REQUIRES: p != null
    //MODIFIES: this
    //EFFECTS: creates single row to display a patients last name, first name, phn, birthday, and a button to
    //         view their appointments
    private JPanel displayPatient(Patient p) {
        JPanel row = new JPanel(rowLayout);
        row.setPreferredSize(new Dimension(OfficeUI.WIDTH, ROW_HEIGHT));
        row.setBorder(border);

        JLabel name = new JLabel(p.getClass().getSimpleName());
        name.setVerticalAlignment(TOP);

        JLabel last = new JLabel(p.getLast());
        JLabel first = new JLabel(p.getFirst());
        JLabel phn = new JLabel(String.valueOf(p.getPhn()));
        JLabel birthday = new JLabel(String.valueOf(p.getBirthday()));
        JButton remove = new JButton(ButtonNames.REMOVE_PATIENT.getValue());

        remove.addActionListener(e -> {
            Office office = getController().getOffice();
            office.removePatient(p.getPhn());
            updatePatients(office.getAllPatients());
        });

        row.add(last);
        row.add(first);
        row.add(phn);
        row.add(birthday);
        row.add(remove);

        return row;
    }

    // MODIFIES: this
    // EFFECTS: updates the patients displayed in the tab
    public void updatePatients(ArrayList<Patient> patients) {
        removeAll();
        initializeLayout();
        for (Patient p : patients) {
            add(displayPatient(p));
        }
        revalidate();
        repaint();
    }
}
