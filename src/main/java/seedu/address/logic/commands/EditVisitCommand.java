package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_INDEX;

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
 * Edits record of patient by index.
 */
public class EditVisitCommand extends Command {

    public static final String COMMAND_WORD = "editvisit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the visitation log of the patient identified "
            + "by the index number used in the displayed patient list.\n"
            + "Parameters: " + "PATIENT_INDEX "
            + PREFIX_VISIT_INDEX + "VISIT_INDEX (both must be positive numbers) ["
            + PREFIX_VISIT_DATE + "VISIT_DATE]\n"
            + "Input DATE as dd/MM/yyyy. Visit date can be any date up till and including today.\n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_VISIT_INDEX + "2 "
            + PREFIX_VISIT_DATE + "30/10/2020";;

    public static final String EMPTY_VISIT_DATE = "01/01/1900";
    public static final String MESSAGE_EDIT_VISIT_PROMPT = "Please refer to the popup window and edit visitation log.";
    public static final String MESSAGE_MISSING_INDEX_PROMPT = "Please specify the index of visitation to be edited\n"
            + "Usage: " + COMMAND_WORD + " [PATIENT INDEX] "
            + PREFIX_VISIT_INDEX + "[VISIT INDEX]\n";

    private static final int EMPTY_VISIT_LOG = -1;

    private final Index patientIndex;
    private final int visitIndex;
    private final String visitDate;

    /**
     * @param patientIndex Patient's index
     * @param visitIndex Visit's index
     */
    public EditVisitCommand(Index patientIndex, int visitIndex, String visitDate) {
        requireAllNonNull(patientIndex);

        this.patientIndex = patientIndex;
        this.visitIndex = visitIndex;
        this.visitDate = visitDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null.";
        List<Patient> lastShownList = model.getFilteredPatientList();
        int listSize = lastShownList.size();

        if (patientIndex.getZeroBased() >= listSize) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        VisitHistory visitHistory = patientToEdit.getVisitHistory();
        ObservableList<Visit> observableHistory = visitHistory.getObservableVisits();

        if (visitIndex == EMPTY_VISIT_LOG) {
            return new CommandResult(MESSAGE_MISSING_INDEX_PROMPT, observableHistory);
        } else {
            try {
                Visit visit = visitHistory.getVisitByIndex(visitIndex);
                model.commitCliniCal(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT, COMMAND_WORD, patientToEdit));
                return new CommandResult(String.format(MESSAGE_EDIT_VISIT_PROMPT, patientToEdit), visitDate,
                    observableHistory, visit, visitIndex, patientIndex.getOneBased());
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_VISIT_HISTORY_INDEX);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditVisitCommand)) {
            return false;
        }

        // state check
        EditVisitCommand e = (EditVisitCommand) other;
        return patientIndex.equals(e.patientIndex)
                && visitIndex == e.visitIndex
                && visitDate.equals(e.visitDate);
    }
}


