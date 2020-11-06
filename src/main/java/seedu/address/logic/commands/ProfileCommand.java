package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;


/**
 * Changes the visit history of an existing person in the address book.
 */
public class ProfileCommand extends Command {
    public static final String COMMAND_WORD = "profile";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the profile of the patient identified "
            + "by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive number) \n\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_VIEW_PROFILE_SUCCESS = "Displayed profile of the following patient: \n%1$s";

    private final Index patientIndex;

    /**
     * @param patientIndex of the person in the last listing whose profile is to be viewed
     */
    public ProfileCommand(Index patientIndex) {
        requireAllNonNull(patientIndex);

        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null.";

        List<Patient> lastShownList = model.getFilteredPatientList();
        int sizeOfList = lastShownList.size();

        if (patientIndex.getZeroBased() >= sizeOfList) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToDisplay = lastShownList.get(patientIndex.getZeroBased());
        VisitHistory visitHistory = patientToDisplay.getVisitHistory();

        ObservableList<Visit> copyOfObservableHistory = visitHistory.getObservableVisits();

        return new CommandResult(String.format(MESSAGE_VIEW_PROFILE_SUCCESS, patientToDisplay), copyOfObservableHistory,
                                 patientToDisplay);
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProfileCommand)) {
            return false;
        }

        // state check
        ProfileCommand e = (ProfileCommand) other;
        return patientIndex.equals(e.patientIndex);
    }
}
