package seedu.address.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_IC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_IC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalCliniCalWithAppointments;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.appointment.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.model.CliniCal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.testutil.AppointmentBuilder;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for EditAppointmentCommand.
 */
public class EditAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalCliniCalWithAppointments(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Appointment editedAppointment = new AppointmentBuilder().build();
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(editedAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new CliniCal(model.getCliniCal()), new UserPrefs());
        expectedModel.setAppointment(model.getFilteredAppointmentList().get(0), editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAppointment = Index.fromOneBased(model.getFilteredAppointmentList().size());
        Appointment lastAppointment = model.getFilteredAppointmentList().get(indexLastAppointment.getZeroBased());

        AppointmentBuilder appointmentInList = new AppointmentBuilder(lastAppointment);
        Appointment editedAppointment = appointmentInList
                .withPatientName(VALID_PATIENT_NAME_FIRST)
                .withPatientIc(VALID_PATIENT_IC_FIRST)
                .withStartTime(VALID_START_TIME_FIRST)
                .withEndTime(VALID_END_TIME_FIRST)
                .build();

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientName(VALID_PATIENT_NAME_FIRST)
                .withPatientIc(VALID_PATIENT_IC_FIRST)
                .withStartTime(VALID_START_TIME_FIRST)
                .withDuration(VALID_DURATION_FIRST)
                .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(indexLastAppointment, descriptor);

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new CliniCal(model.getCliniCal()), new UserPrefs());
        expectedModel.setAppointment(lastAppointment, editedAppointment);

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT,
                new EditAppointmentDescriptor());
        Appointment editedAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_APPOINTMENT.getZeroBased());

        String expectedMessage = String.format(EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
                editedAppointment);

        Model expectedModel = new ModelManager(new CliniCal(model.getCliniCal()), new UserPrefs());

        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAppointmentUnfilteredList_failure() {
        Appointment firstAppointment = model.getFilteredAppointmentList().get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder(firstAppointment).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_SECOND_APPOINTMENT,
                descriptor);

        assertCommandFailure(editAppointmentCommand, model, EditAppointmentCommand.MESSAGE_CONFLICTING_APPOINTMENT);
    }

    @Test
    public void execute_invalidAppointmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientName(VALID_PATIENT_NAME_SECOND)
                .withPatientIc(VALID_PATIENT_IC_SECOND).build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditAppointmentCommand standardCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, DESC_FIRST);

        // same values -> returns true
        EditAppointmentDescriptor copyDescriptor = new EditAppointmentDescriptor(DESC_FIRST);
        EditAppointmentCommand commandWithSameValues = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_SECOND_APPOINTMENT, DESC_FIRST)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, DESC_SECOND)));
    }

}
