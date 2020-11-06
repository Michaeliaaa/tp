package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Adds a visit record to the visitHistory of a patient.
 */
public class AddVisitCommand extends Command {
    public static final String COMMAND_WORD = "addvisit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a visit record to the patient "
            + "by specifying the patient's index in the list of patients. \n"
            + "Parameters: INDEX (must be a positive number) ["
            + PREFIX_VISIT_DATE + "DATE]\n"
            + "Input DATE as dd/MM/yyyy. Visit date can be any date up till and including today.\n\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_VISIT_DATE + "30/10/2020";

    public static final String MESSAGE_POPUP_PROMPT = "Please refer to the popup window and enter visitation log.";

    private final Index patientIndex;
    private final String visitDate;

    /**
     * @param patientIndex Index of the patient
     * @param visitDate Date of patient visit
     */
    public AddVisitCommand(String visitDate, Index patientIndex) {
        CollectionUtil.requireAllNonNull(patientIndex, visitDate);
        this.visitDate = visitDate;
        this.patientIndex = patientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null.";

        List<Patient> lastShownList = model.getFilteredPatientList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        return new CommandResult(String.format(MESSAGE_POPUP_PROMPT, patientToEdit),
                                 visitDate, patientIndex.getOneBased());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddVisitCommand)) {
            return false;
        }

        // state check
        AddVisitCommand e = (AddVisitCommand) other;
        return patientIndex.equals(e.patientIndex) && visitDate.equals(e.visitDate);
    }
}

