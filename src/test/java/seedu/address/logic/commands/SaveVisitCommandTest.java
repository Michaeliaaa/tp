package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.CliniCal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;
import seedu.address.model.visit.Visit;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalPatients;
import seedu.address.testutil.TypicalVisits;

public class SaveVisitCommandTest {
    private static final int NEW_VISIT = -1;
    private Model model = new ModelManager(TypicalPatients.getTypicalCliniCal(), new UserPrefs());
    private Visit visit = TypicalVisits.VISIT_1;

    @Test
    public void constructor_nullValue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SaveVisitCommand(1, null,
                                                   null, null, null, 1));
    }

    @Test
    public void execute_unfilteredListSaveVisit_success() {
        Patient firstPerson = model.getFilteredPatientList().get(TypicalIndexes.INDEX_FIRST_PATIENT.getZeroBased());

        Patient editedPerson = new PatientBuilder(firstPerson)
                .withVisitHistory(TypicalVisits.getTypicalVisitHistoryAlice()).build();

        SaveVisitCommand saveVisitCommand = new SaveVisitCommand(
                TypicalIndexes.INDEX_FIRST_PATIENT.getOneBased(), "27/03/2019",
                visit.getPrescription(), visit.getDiagnosis(), visit.getComment(), NEW_VISIT);

        String expectedMessage = String.format(SaveVisitCommand.MESSAGE_SAVE_VISIT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new CliniCal(model.getCliniCal()), new UserPrefs());
        Model newModel = model;
        expectedModel.setPatient(firstPerson, editedPerson);

        CommandTestUtil.assertCommandPass(saveVisitCommand, model, expectedMessage, newModel);
    }

    @Test
    public void execute_unfilteredListInvalidPatientIndex_failure() {
        ObservableList<Patient> filteredPatientList = model.getFilteredPatientList();
        int sizeOfList = filteredPatientList.size();
        Index outOfBoundIndex = Index.fromOneBased(sizeOfList + 1);
        int indexInInt = outOfBoundIndex.getOneBased();
        String prescription = visit.getPrescription();
        String diagnosis = visit.getDiagnosis();
        String comment = visit.getComment();
        SaveVisitCommand saveVisitCommand = new SaveVisitCommand(indexInInt, "27/03/2019",
                                                                 prescription, diagnosis, comment, NEW_VISIT);

        CommandTestUtil.assertCommandFailure(saveVisitCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edits filtered patient list in the scenario where index is greater than size of filtered list, but
     * lesser than size of patient list in CliniCal
     */
    @Test
    public void execute_filteredListInvalidPatientIndex_failure() {
        Index firstPatientIndex = TypicalIndexes.INDEX_FIRST_PATIENT;
        CommandTestUtil.showPatientAtIndex(model, firstPatientIndex);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PATIENT;

        ReadOnlyCliniCal modelClinical = model.getCliniCal();
        ObservableList<Patient> sampleClinical = modelClinical.getPatientList();
        int sizeOfPatientList = sampleClinical.size();

        // Assert that outOfBoundIndex is still in bounds of size of patient list in CliniCal
        assertTrue(outOfBoundIndex.getZeroBased() < sizeOfPatientList);

        String prescription = visit.getPrescription();
        String diagnosis = visit.getDiagnosis();
        String comment = visit.getComment();

        SaveVisitCommand saveVisitCommand = new SaveVisitCommand(outOfBoundIndex.getOneBased(),
                                        "27/03/2019", prescription, diagnosis, comment, NEW_VISIT);
        CommandTestUtil.assertCommandFailure(saveVisitCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        SaveVisitCommand saveVisitCommand = new SaveVisitCommand(1, "08/10/2020", "cancer",
                                      "pain pills", "monitor closely", NEW_VISIT);

        // Null value. Returns false
        assertFalse(saveVisitCommand.equals(null));

        // Same object. Returns true
        SaveVisitCommand saveCommand = new SaveVisitCommand(1, "08/10/2020", "cancer",
                                 "pain pills", "monitor closely", NEW_VISIT);

        assertTrue(saveVisitCommand.equals(saveCommand));

        // Different type of input. Returns false
        assertFalse(saveVisitCommand.equals("test"));

        // Different type of input. Returns false
        assertFalse(saveVisitCommand.equals(1));

        // Different patient index. Returns false
        SaveVisitCommand differentIndex = new SaveVisitCommand(2, "08/10/2020", "cancer",
                                    "pain pills", "monitor closely", NEW_VISIT);
        assertFalse(saveCommand.equals(differentIndex));

        // Different visit date. Returns false
        SaveVisitCommand differentDate = new SaveVisitCommand(1, "10/08/2020", "cancer",
                                   "pain pills", "monitor closely", NEW_VISIT);
        assertFalse(saveCommand.equals(differentDate));

        // Different diagnosis. Returns false
        SaveVisitCommand differentDiagnosis = new SaveVisitCommand(1, "10/08/2020",
                "covid-19", "pain pills", "monitor closely", NEW_VISIT);
        assertFalse(saveCommand.equals(differentDiagnosis));

        // Different prescription. Returns false
        SaveVisitCommand differentPrescription = new SaveVisitCommand(1, "10/08/2020",
                                             "cancer", "panadol", "monitor closely",
                                                     NEW_VISIT);
        assertFalse(saveCommand.equals(differentPrescription));

        // Different comment. Returns false
        SaveVisitCommand differentComment = new SaveVisitCommand(1, "10/08/2020", "cancer",
                                      "pain pills", "hospitalisation required",
                                                 NEW_VISIT);

        assertFalse(saveCommand.equals(differentComment));
    }
}

