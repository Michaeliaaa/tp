package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import java.io.File;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AddProfilePictureCommandTest {

    private Model model = new ModelManager(getTypicalCliniCal(), new UserPrefs());
    private File profilePicture = new File("data/stock_picture.png");

    @Test
    // Null filepath.
    public void constructor_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProfilePictureCommand(null,
                                                                                    INDEX_FIRST_PATIENT));
    }

    @Test
    // Null patient index.
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddProfilePictureCommand(profilePicture, null));
    }

    @Test
    // Out of bounds patient index.
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        AddProfilePictureCommand addProfilePictureCommand = new AddProfilePictureCommand(profilePicture,
                                                                                         outOfBoundIndex);

        assertCommandFailure(addProfilePictureCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    /**
     * Edits filtered list where index is larger than size of filtered list,
     * but smaller than size of clinical
     */
    @Test
    public void execute_invalidPatientIndexFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);
        Index outOfBoundIndex = INDEX_SECOND_PATIENT;
        // Asserts that outOfBoundIndex is still in bounds of clinical list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getCliniCal().getPatientList().size());

        AddProfilePictureCommand addProfilePictureCommand = new AddProfilePictureCommand(profilePicture,
                                                                outOfBoundIndex);

        assertCommandFailure(addProfilePictureCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }
}
