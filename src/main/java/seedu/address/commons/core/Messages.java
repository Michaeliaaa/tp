package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_PICTURE_DOES_NOT_EXIST = "Profile picture cannot be found";
    public static final String MESSAGE_PICTURE_HAS_WRONG_EXTENSION =
            "Profile picture should be a file with extension .jpg or .png";
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n\n%1$s";
    public static final String MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX = "The patient index provided is invalid";
    public static final String MESSAGE_PATIENTS_LISTED_OVERVIEW = "%1$d patients listed!";
    public static final String MESSAGE_UNDONE_REDONE_INPUT = "%1$s%2$s";
    public static final String MESSAGE_INVALID_APPOINTMENT_START_END = "Invalid appointment time range."
        + " Starting time of the appointment should be earlier than ending time.";
    public static final String MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX =
        "The appointment index provided is invalid";
    public static final String MESSAGE_INVALID_VISIT_HISTORY_INDEX = "The visit history index provided is invalid";
    public static final String MESSAGE_INVALID_VISIT_INDEX = "The visit index provided is invalid";
}
