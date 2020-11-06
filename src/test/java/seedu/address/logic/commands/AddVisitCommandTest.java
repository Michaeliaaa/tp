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

public class AddVisitCommandTest {

    private static final String DATE_1 = "7/17/2020";
    private static final String DATE_2 = "8/18/2020";

    private ReadOnlyCliniCal sampleClinical = TypicalPatients.getTypicalCliniCal();
    private Model model = new ModelManager(sampleClinical, new UserPrefs());

    @Test
    public void constructor_nullVisit_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddVisitCommand(null, null));
    }

    @Test
    public void execute_unfilteredListInvalidPatientIndex_failure() {
        int outOfBoundInt = model.getFilteredPatientList().size() + 1;
        Index outOfBoundIndex = Index.fromOneBased(outOfBoundInt);
        AddVisitCommand addVisitCommand = new AddVisitCommand(DATE_1, outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(addVisitCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edits filtered patient list in the scenario where index is greater than size of filtered list, but
     * lesser than size of patient list in CliniCal
     */
    @Test
    public void execute_filteredListInvalidPatientIndex_failure() {
        Index indexOfFirstPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        Index indexOfSecondPatient = TypicalIndexes.INDEX_SECOND_PATIENT;
        CommandTestUtil.showPatientAtIndex(model, indexOfFirstPatient);
        Index outOfBoundIndex = indexOfSecondPatient;

        // Assert that outOfBoundIndex is still in bounds of size of patient list in CliniCal
        ReadOnlyCliniCal modelClinical = model.getCliniCal();
        ObservableList<Patient> sampleClinical = modelClinical.getPatientList();
        int sizeOfPatientList = sampleClinical.size();

        assertTrue(outOfBoundIndex.getZeroBased() < sizeOfPatientList);

        AddVisitCommand addVisitCommand = new AddVisitCommand(DATE_1, outOfBoundIndex);
        CommandTestUtil.assertCommandFailure(addVisitCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index indexOfFirstPatient = TypicalIndexes.INDEX_FIRST_PATIENT;
        Index indexOfSecondPatient = TypicalIndexes.INDEX_SECOND_PATIENT;

        AddVisitCommand testCommand = new AddVisitCommand(DATE_1, indexOfFirstPatient);

        // Null value. Returns false
        assertFalse(testCommand.equals(null));

        // Same object. Returns true
        assertTrue(testCommand.equals(new AddVisitCommand(DATE_1, indexOfFirstPatient)));

        // Different command. Returns false
        assertFalse(testCommand.equals(new ListCommand()));

        // Different index. Returns false
        assertFalse(testCommand.equals(new AddVisitCommand(DATE_1, indexOfSecondPatient)));

        // Different date. Returns false
        assertFalse(testCommand.equals(new AddVisitCommand(DATE_2, indexOfFirstPatient)));
    }
}
