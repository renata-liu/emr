package ui.tabs;

import model.Office;
import model.Patient;
import ui.OfficeUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

//Represents "add patient" tab in GUI sidepanel
//Contains input text fields and creates+adds new patient to the office when user clicks add patient button
public class AddPatientTab extends Tab implements ActionListener {

    private JTextField lastNameField;
    private JTextField firstNameField;
    private JTextField phnField;
    private JTextField birthdayField;
    private Font regularFont;
    private Font italicFont;
    private JLabel patientDisplay;
    private Boolean patientSet = false;
    private static final int GAP = 10;
    private Office office;

    //EFFECTS: constructs "add patient" tab in sidepanel
    public AddPatientTab(OfficeUI controller) {
        super(controller);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel leftHalf = new JPanel() {
            //Don't allow us to stretch vertically.
            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                        pref.height);
            }
        };
        leftHalf.setLayout(new BoxLayout(leftHalf,
                BoxLayout.PAGE_AXIS));
        leftHalf.add(createEntryFields());
        leftHalf.add(createButtons());

        add(leftHalf);
        add(createPatientDisplay());
    }

    //MODIFIES: this
    // EFFECTS: creates and adds "add patient" and "clear patient" buttons to console
    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Add patient");
        button.addActionListener(this);
        panel.add(button);

        button = new JButton("Clear patient");
        button.addActionListener(this);
        button.setActionCommand("clear");
        panel.add(button);

        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                GAP - 5, GAP - 5));

        return panel;
    }

    //MODIFIES: this
    //EFFECTS: action listener for all buttons in this
    public void actionPerformed(ActionEvent e) {
        if ("clear".equals(e.getActionCommand())) {
            patientSet = false;
            lastNameField.setText("");
            firstNameField.setText("");
            phnField.setText("");
            birthdayField.setText("");
        } else {
            patientSet = true;
            String lastName = lastNameField.getText();
            String firstName = firstNameField.getText();
            String phn = phnField.getText();
            String birthday = birthdayField.getText();

            office = getController().getOffice();
            Patient p = new Patient(firstName, lastName, Integer.valueOf(birthday), Integer.valueOf(phn));
            office.addPatient(p);
            getController().loadNewViewPatientsTab(office.getAllPatients());
        }
        updateDisplays();
    }

    //MODIFIES: this
    //EFFECTS: updates text on right side of window
    protected void updateDisplays() {
        patientDisplay.setText(formatPatient());
        if (patientSet) {
            patientDisplay.setFont(regularFont);
        } else {
            patientDisplay.setFont(italicFont);
        }
    }

    //MODIFIES: this
    //EFFECTS: creates layout for patient information on right side of GUI
    protected JComponent createPatientDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        patientDisplay = new JLabel();
        patientDisplay.setHorizontalAlignment(JLabel.CENTER);
        regularFont = patientDisplay.getFont().deriveFont(Font.PLAIN,
                16.0f);
        italicFont = regularFont.deriveFont(Font.ITALIC);
        updateDisplays();

        panel.setBorder(BorderFactory.createEmptyBorder(
                GAP / 2,
                0,
                GAP / 2,
                0));
        panel.add(new JSeparator(JSeparator.VERTICAL),
                BorderLayout.LINE_START);
        panel.add(patientDisplay,
                BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(200, 150));

        return panel;
    }

    //MODIFIES: this
    //EFFECTS: formats patient information for patient display when add patient button is selected
    protected String formatPatient() {
        if (!patientSet) {
            return "No patient set.";
        }

        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String phn = phnField.getText();
        String birthday = birthdayField.getText();
        String empty = "";

        if ((lastName == null) || empty.equals(lastName)) {
            lastName = "<em>(no last name specified)</em>";
        }
        if ((firstName == null) || empty.equals(firstName)) {
            firstName = "<em>(no first name specified)</em>";
        }
        if ((phn == null) || empty.equals(phn)) {
            phn = "<em>(no PHN specified)</em>";
        }
        if ((birthday == null) || empty.equals(birthday)) {
            birthday = "<em>(no birthday specified)</em>";
        }

        return formatPatientText(lastName, firstName, phn, birthday).toString();
    }

    //MODIFIES: this
    //EFFECTS: formats text that will be displayed to patient display
    private StringBuffer formatPatientText(String l, String f, String phn, String dob) {
        StringBuffer sb = new StringBuffer();
        sb.append("<html><p align=left>");
        sb.append("<br> Patient: ");
        sb.append(l);
        sb.append(", ");
        sb.append(f);
        sb.append("<br> PHN: ");
        sb.append(phn);
        sb.append("<br> Birthday: ");
        sb.append(dob);
        sb.append("<br> was successfully added");
        sb.append("</p></html>");

        return sb;
    }

    //MODIFIES: this
    //EFFECTS: created text input fields for user to input patient information
    protected JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());

        String[] labelStrings = {
                "Last name: ",
                "First name: ",
                "PHN (9-digits): ",
                "Birthday (yyyymmdd): "
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        constructInputFields(fields, fieldNum);
        createLabelFieldPairs(labelStrings, labels, panel, fields);

        SpringUtilities.makeCompactGrid(panel,
                labelStrings.length, 2,
                GAP, GAP,
                GAP, GAP / 2);

        return panel;
    }

    //MODIFIES: this
    //EFFECTS: helper method for createEntryFields
    private void constructInputFields(JComponent[] fields, int fieldNum) {
        //Create the text field and set it up.
        lastNameField = new JTextField();
        lastNameField.setColumns(20);
        fields[fieldNum++] = lastNameField;

        firstNameField = new JTextField();
        firstNameField.setColumns(20);
        fields[fieldNum++] = firstNameField;

        phnField = new JTextField();
        phnField.setColumns(20);
        fields[fieldNum++] = phnField;

        birthdayField = new JTextField();
        birthdayField.setColumns(20);
        fields[fieldNum++] = birthdayField;
    }

    //EFFECTS: helper method for createEntryFields
    private void createLabelFieldPairs(String[] labelStrings, JLabel[] labels, JPanel panel, JComponent[] fields) {
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                    JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //Add listeners to each field.
            JTextField tf = (JTextField) fields[i];
            tf.addActionListener(this);
        }
    }

}


