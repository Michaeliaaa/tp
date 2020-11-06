package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalPatients;

public class EditVisitCommandTest {

    private static final String DATE_1 = "7/17/2020";
    private static final String DATE_2 = "8/18/2020";
    private static final int EMPTY_INDEX = -1;
    private static final int VALID_INDEX = 1;
    private static final int INVALID_INDEX = 0;


    private Model model = new ModelManager(TypicalPatients.getTypicalCliniCal(), new UserPrefs());

    @Test
    public void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditVisitCommand(null, 1, null));
    }

    @Test
    public void execute_unfilteredListInvalidPatientIndex_failure() {
        int outOfBoundInt = model.getFilteredPatientList().size() + 1;
        Index outOfBoundIndex = Index.fromOneBased(outOfBoundInt);
        EditVisitCommand editVisitCommand = new EditVisitCommand(outOfBoundIndex, EMPTY_INDEX, DATE_1);

        CommandTestUtil.assertCommandFailure(editVisitCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edits filtered patient list in the scenario where index is greater than size of filtered list, but
     * lesser than size of patient list in CliniCal
     */
    @Test
    public void execute_filteredListInvalidPatientIndex_failure() {
        Index indexOfPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PATIENT;
        CommandTestUtil.showPatientAtIndex(model, indexOfPatient);

        ReadOnlyCliniCal modelClinical = model.getCliniCal();
        ObservableList<Patient> sampleClinical = modelClinical.getPatientList();
        int sizeOfPatientList = sampleClinical.size();

        // Assert that outOfBoundIndex is still in bounds of size of patient list in CliniCal
        assertTrue(outOfBoundIndex.getZeroBased() < sizeOfPatientList);

        EditVisitCommand editVisitCommand = new EditVisitCommand(outOfBoundIndex, EMPTY_INDEX, DATE_1);
        CommandTestUtil.assertCommandFailure(editVisitCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidVisitIndex_failure() {
        Index indexOfPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        Index indexOfSecondPatient = TypicalIndexes.INDEX_SECOND_PATIENT;

        EditVisitCommand editVisitCommand = new EditVisitCommand(indexOfPatient, INVALID_INDEX, null);

        // To check index out of bounds
        EditVisitCommand editCommand = new EditVisitCommand(indexOfSecondPatient,
                model.getFilteredPatientList().get(indexOfSecondPatient.getZeroBased()).getVisitHistory()
                                                      .getVisits().size() + 2, DATE_1);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_VISIT_HISTORY_INDEX);
        CommandTestUtil.assertCommandFailure(editVisitCommand, model, Messages.MESSAGE_INVALID_VISIT_HISTORY_INDEX);
    }

    @Test
    public void equals() {
        Index indexOfPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        Index indexOfSecondPatient = TypicalIndexes.INDEX_SECOND_PATIENT;

        final EditVisitCommand command = new EditVisitCommand(indexOfPatient, EMPTY_INDEX, DATE_1);

        // Null value. Returns false
        assertFalse(command.equals(null));

        // Same values. Returns true
        EditVisitCommand commandWithSameValues = new EditVisitCommand(indexOfPatient, EMPTY_INDEX, DATE_1);
        assertTrue(command.equals(commandWithSameValues));

        // Different visit index. Returns false
        EditVisitCommand commandWithDifferentIndex = new EditVisitCommand(indexOfPatient, VALID_INDEX, DATE_1);
        assertFalse(command.equals(commandWithDifferentIndex));

        // Different patient index. Returns false
        assertFalse(command.equals(new EditVisitCommand(indexOfSecondPatient, EMPTY_INDEX, DATE_1)));

        // Different date
        EditVisitCommand commandWithDifferentDate = new EditVisitCommand(indexOfPatient, EMPTY_INDEX, DATE_2);
        assertFalse(command.equals(commandWithDifferentDate));

        // Same object. Returns true
        assertTrue(command.equals(command));

        // Different type of command. Returns false
        assertFalse(command.equals(new ListCommand()));
    }
}

