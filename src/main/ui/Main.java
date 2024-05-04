package ui;

import model.Appointment;
import model.Office;
import model.Patient;
import javax.swing.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new OfficeApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
