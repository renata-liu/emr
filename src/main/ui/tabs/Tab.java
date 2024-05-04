package ui.tabs;

import ui.OfficeUI;

import javax.swing.*;
import java.awt.*;

public abstract class Tab extends JPanel {

    private final OfficeUI controller;

    //REQUIRES: SmartHomeUI controller that holds this tab
    public Tab(OfficeUI controller) {
        this.controller = controller;
    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    //EFFECTS: returns the OfficeUI controller for this tab
    public OfficeUI getController() {
        return controller;
    }

}
