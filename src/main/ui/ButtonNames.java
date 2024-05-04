package ui;

//Represents all button names used in OfficeUI
public enum ButtonNames {
    ADD_PATIENT("Add patient"),
    SAVE_OFFICE("Save office"),
    LOAD_OFFICE("Load office"),
    REMOVE_PATIENT("Remove"),
    SORT_BY_LAST("Sort by: last");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    //EFFECTS: returns name value of this button
    public String getValue() {
        return name;
    }
}
