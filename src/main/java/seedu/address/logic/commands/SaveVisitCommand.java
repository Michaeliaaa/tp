package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.time.format.DateTimeParseException;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

public class SaveVisitCommand extends Command {
    public static final String MESSAGE_SAVE_VISIT_SUCCESS = "Successfully saved visit for the "
                                                            + "following patient: \n%1$s";
    private static final int NEW_VISIT = -1;

    private final Index patientIndex;
    private final Visit visit;

    private String diagnosis;
    private String prescription;
    private String comments;
    private String visitDate;
    private int visitIndex;

    /**
     * Saves new record to Visit List.
     */
    public SaveVisitCommand(int patientIndex, String visitDate, String diagnosis, String prescription,
                            String comments, int visitIndex) {
        CollectionUtil.requireAllNonNull(patientIndex, visitIndex, visitDate);

        this.patientIndex = Index.fromOneBased(patientIndex);
        this.visitDate = visitDate;
        this.visit = new Visit(visitDate);
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.comments = comments;
        this.visitIndex = visitIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        assert model != null : "Model cannot be null";

        List<Patient> lastShownList = model.getFilteredPatientList();
        int sizeOfList = lastShownList.size();

        if (patientIndex.getZeroBased() >= sizeOfList) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        Name patientName = patientToEdit.getName();
        visit.setPatientName(patientName);
        visit.setParameters(diagnosis, prescription, comments);
        try {
            visit.setVisitDate(visitDate);
        } catch (DateTimeParseException exception) {
            throw new CommandException(EditVisitCommand.MESSAGE_USAGE);
        }

        Patient patientEdited;

        if (visitIndex != NEW_VISIT) {
            VisitHistory newVisitHistory = VisitHistory.deepCopyVisitHistory(patientToEdit.getVisitHistory());
            // Edit existing Visit
            patientEdited = new Patient(patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getIcNumber(),
                    newVisitHistory.editVisit(visitIndex, visit), patientToEdit.getAddress(),
                    patientToEdit.getEmail(), patientToEdit.getProfilePicture(), patientToEdit.getSex(),
                    patientToEdit.getBloodType(), patientToEdit.getAllergies(), patientToEdit.getColorTag());
        } else {
            // Add new Visit
            VisitHistory newVisitHistory = VisitHistory.deepCopyVisitHistory(patientToEdit.getVisitHistory());
            patientEdited = new Patient(patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getIcNumber(),
                    newVisitHistory.addVisit(visit), patientToEdit.getAddress(),
                    patientToEdit.getEmail(), patientToEdit.getProfilePicture(), patientToEdit.getSex(),
                    patientToEdit.getBloodType(), patientToEdit.getAllergies(), patientToEdit.getColorTag());
        }

        assert patientEdited != null : "Patient cannot be null.";

        model.setPatient(patientToEdit, patientEdited);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        model.commitCliniCal(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT, "Add/Edit visit for the patient:\n",
            patientEdited));
        return new CommandResult(String.format(MESSAGE_SAVE_VISIT_SUCCESS, patientToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SaveVisitCommand)) {
            return false;
        }

        // state check
        SaveVisitCommand e = (SaveVisitCommand) other;
        return patientIndex.equals(e.patientIndex)
                && diagnosis.equals(e.diagnosis)
                && prescription.equals(e.prescription)
                && comments.equals(e.comments)
                && visit.getVisitDate().equals(e.visit.getVisitDate());
    }
}

