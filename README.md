# My Personal Project

## Project Description

This project aims to design an **electronic medical record (EMR)** 
application tailored for **clinics, hospitals, and other medical settings.** 
It is intended for use by medical personnel, offering a streamlined
and aesthetically pleasing solution to manage patient files. 
As a **medical office assistant** at a local doctor's office, 
my motivation for undertaking this project stems from the 
observation that many existing EMRs are visually straining. By 
initiating the design of a more visually appealing EMR, I aim to 
enhance the user experience for medical professionals and contribute
to the efficiency of healthcare management.

The proposed EMR-like application will function as a comprehensive 
**system for storing patient data** within a doctor's office. It will 
facilitate the entry of important information such as patient first
names, last names, and upcoming appointments. One of the distinctive
features of this application is the provision of a visual chart 
showcasing the patient's latest vitals, including heart rate, 
blood pressure, and oxygen levels. Additionally, the system will 
allow for the inclusion of patient relatives associated with the 
medical office. The application will also store a patient’s relevant
medical history such as lab results, specialist consultation notes, 
and other healthcare-related documents. This project is driven by a 
commitment to improving the functionality and aesthetics of EMRs, 
ultimately contributing to more efficient and user-friendly 
healthcare data management.


## User Stories

- As a user, I want to be able to ***make a patient*** and ***add that patient 
to a list of patients***
- As a user, I want to be able to ***create an appointment*** and ***add it to
list of that patient's appointments***
- As a user, I want to be able to ***view a patient's appointment history***
- As a user, I want to be able to ***edit a patient's contact information***
- As a user, I want to be able to ***view all patients*** in the office
- As a user, I want to be able to ***save an office's entire patient list (with the patient's information) to file***
- As a user, I want to be able to ***load an office's saved patient list from file***

## Typical User Flow
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by
selecting the **button** labelled **"Add patient"** in the main menu
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by
selecting the **tab** labelled **"View all patients"** in the side panel
- You can **remove** a patient from the office by selecting the **remove** button next to a patient's name
- You can **sort** the office's list of patients by last name by selecting the **sort by: last button** in
the patient viewing menu
- You can locate my **visual component** by observing the ubc logo on the home page
- You can **save the state of my application** by selecting the **"Save office" button** in the main menu
- You can **reload the state of my application** by selecting the **"Load office" button** in the main menu

## Phase 4: Task 2
- Add patient
- Add another patient 
- Add another patient
- Go to view patients panel in the sidebar
- Select sort patients 
- Remove a patient 
- Go back to home panel in the sidebar
- Save office
- Close application

## Phase 4: Task 3
Since there is only one instance of the Office class across the program, I would refactor the Office class to 
implement the singleton pattern to improve the project's architecture and maintainability. This eliminates redundancy 
and guarantees data integrity by guaranteeing that all office-related operations, including adding patients, are carried
out on a single shared instance. Furthermore, implementing the single responsibility principle and streamlining code 
organization can be achieved by abstracting common functionalities into abstract classes. Better code reusability and 
concern separation can be attained by abstracting out common behaviors—like file reading and writing operations—into 
distinct abstract classes. Furthermore, readability and maintainability can be improved by breaking up large classes 
that manage several jobs into smaller, more focused ones. This would facilitate easier debugging and encourage a clearer
code structure. The methods within these classes can also be made more readable by extracting helper methods with 
informative names, which would also facilitate debugging. 
