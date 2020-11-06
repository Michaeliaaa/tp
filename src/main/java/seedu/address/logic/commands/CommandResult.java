package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /** The application should display patient's profile. */
    private final boolean isDisplayProfile;

    /** The application should display current visit history. */
    private final boolean isDisplayVisitHistory;

    /** The application should add a new visit. */
    private final boolean isAddVisit;

    /** The application should edit a specified visit. */
    private final boolean isEditVisit;

    private final String feedbackToUser;
    private String visitDate;
    private Patient patientProfile;
    private ObservableList<Visit> visitHistory;
    private Visit previousVisit;
    private int visitIndex;
    private int patientIndex;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isShowHelp, boolean isAddVisit, boolean isDisplayVisitHistory,
                         boolean isEditVisit, boolean isDisplayProfile, boolean isExit) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = isShowHelp;
        this.isAddVisit = isAddVisit;
        this.isDisplayVisitHistory = isDisplayVisitHistory;
        this.isEditVisit = isEditVisit;
        this.isDisplayProfile = isDisplayProfile;
        this.isExit = isExit;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     * All other parameters are set to false for this constructor.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     * This constructor is used for adding visits.
     */
    public CommandResult(String feedbackToUser, String visitDate, int patientIndex) {
        this(feedbackToUser, false, true, false, false, false, false);
        this.visitDate = visitDate;
        this.patientIndex = patientIndex;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     * This constructor is used for displaying visit history.
     */
    public CommandResult(String feedbackToUser, ObservableList<Visit> visitHistory) {
        this(feedbackToUser, false, false, true, false,
            false, false);
        this.visitHistory = visitHistory;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     * This constructor is used for editing visits.
     */
    public CommandResult(String feedbackToUser, String visitDate, ObservableList<Visit> visitHistory,
                         Visit previousVisit, int visitIndex, int patientIndex) {
        this(feedbackToUser, false, false, false, true, false, false);
        this.visitDate = visitDate;
        this.visitHistory = visitHistory;
        this.previousVisit = previousVisit;
        this.visitIndex = visitIndex;
        this.patientIndex = patientIndex;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser}.
     * This constructor is used for displaying patient profile.
     */
    public CommandResult(String feedbackToUser, ObservableList<Visit> visitHistory, Patient patientProfile) {
        this(feedbackToUser, false, false, false, false, true, false);
        this.visitHistory = visitHistory;
        this.patientProfile = patientProfile;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isDisplayProfile() {
        return isDisplayProfile;
    }

    public boolean isAddVisit() {
        return isAddVisit;
    }

    public boolean isDisplayVisitHistory() {
        return isDisplayVisitHistory;
    }

    public boolean isEditVisit() {
        return isEditVisit;
    }

    public Patient getPatientProfile() {
        return patientProfile;
    }

    public int getPatientIndex() {
        return patientIndex;
    }

    public int getVisitIndex() {
        return visitIndex;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public Visit getPreviousVisit() {
        return this.previousVisit;
    }

    public ObservableList<Visit> getObservableVisitHistory() {
        return this.visitHistory;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isAddVisit == otherCommandResult.isAddVisit
                && isEditVisit == otherCommandResult.isEditVisit
                && isDisplayVisitHistory == otherCommandResult.isDisplayVisitHistory
                && isDisplayProfile == otherCommandResult.isDisplayProfile
                && isExit == otherCommandResult.isExit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isAddVisit, isDisplayProfile, isExit);
    }
}
