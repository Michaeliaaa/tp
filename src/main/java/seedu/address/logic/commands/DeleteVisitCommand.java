package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_INDEX;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

public class DeleteVisitCommand extends Command {
    public static final String COMMAND_WORD = "deletevisit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the visitation log of the patient identified"
        + " by the index number used in the displayed patient list.\n"
        + "Parameters: " + "PATIENT_INDEX "
        + PREFIX_VISIT_INDEX + "VISIT_INDEX (both must be positive numbers)\n\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_VISIT_INDEX + "2";

    public static final String MESSAGE_DELETE_VISIT_SUCCESS = "Deleted visitation log for the "
                                                              + "following patient: \n%1$s";
    public static final String MESSAGE_MISSING_INDEX_PROMPT = "Please specify index of visitation to be deleted\n"
        + "Usage: " + COMMAND_WORD + " [PATIENT INDEX] "
        + PREFIX_VISIT_INDEX + "[VISIT INDEX]";

    private static final int EMPTY_VISIT_LOG = -1;

    private final Index patientIndex;
    private final int visitIndex;

    /**
     * @param patientIndex Patient's index
     * @param visitIndex Visit's index
     */
    public DeleteVisitCommand(Index patientIndex, int visitIndex) {
        CollectionUtil.requireAllNonNull(patientIndex);

        this.patientIndex = patientIndex;
        this.visitIndex = visitIndex;
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
        Patient patientEdited = patientToEdit;
        VisitHistory visitHistory = patientEdited.getVisitHistory();
        ObservableList<Visit> observableHistory = visitHistory.getObservableVisits();

        if (visitIndex == EMPTY_VISIT_LOG) {

            if (!observableHistory.isEmpty()) {
                return new CommandResult(MESSAGE_MISSING_INDEX_PROMPT, observableHistory);
            } else {
                return new CommandResult("", observableHistory);
            }
        } else {
            try {
                VisitHistory newVisitHistory = VisitHistory.deepCopyVisitHistory(patientToEdit.getVisitHistory());
                patientEdited = new Patient(patientToEdit.getName(), patientToEdit.getPhone(),
                        patientToEdit.getIcNumber(), newVisitHistory.deleteVisit(visitIndex),
                        patientToEdit.getAddress(), patientToEdit.getEmail(), patientToEdit.getProfilePicture(),
                        patientToEdit.getSex(), patientToEdit.getBloodType(), patientToEdit.getAllergies(),
                        patientToEdit.getColorTag());

                model.setPatient(patientToEdit, patientEdited);
            } catch (IndexOutOfBoundsException exception) {
                throw new CommandException(Messages.MESSAGE_INVALID_VISIT_INDEX);
            }
        }

        model.updateFilteredPatientList(Model.PREDICATE_SHOW_ALL_PATIENTS);
        model.commitCliniCal(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT, COMMAND_WORD + " for the following "
            + "patient:\n", patientEdited));

        return new CommandResult(String.format(MESSAGE_DELETE_VISIT_SUCCESS, patientToEdit), observableHistory);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteVisitCommand)) {
            return false;
        }

        // state check
        DeleteVisitCommand e = (DeleteVisitCommand) other;
        return patientIndex.equals(e.patientIndex) && visitIndex == e.visitIndex;
    }
}
