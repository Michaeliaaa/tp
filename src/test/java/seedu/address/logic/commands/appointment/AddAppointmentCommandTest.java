package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_IC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_IC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CliniCal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.AppointmentBuilder;

public class AddAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalCliniCal(), new UserPrefs());
    @Test
    public void constructor_nullAppointment_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(null, null, null));
    }

    @Test
    public void constructor_nullIndex_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(null,
                new AppointmentDateTime("12/12/2020 12:00"), new AppointmentDateTime("12/12/2020 13:00")));
    }

    @Test
    public void constructor_nullStartTime_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(Index.fromZeroBased(0),
                null, new AppointmentDateTime("12/12/2020 13:00")));
    }

    @Test
    public void constructor_nullEndTime_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AddAppointmentCommand(Index.fromZeroBased(0),
                new AppointmentDateTime("12/12/2020 12:00"), null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToAdd = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        Appointment appointmentToAdd = new Appointment(patientToAdd.getName(), patientToAdd.getIcNumber(),
                new AppointmentDateTime("12/12/2020 12:00"), new AppointmentDateTime("12/12/2020 13:00"));
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(appointmentToAdd);

        String expectedMessage = String.format(AddAppointmentCommand.MESSAGE_SUCCESS, appointmentToAdd);

        ModelManager expectedModel = new ModelManager(model.getCliniCal(), new UserPrefs());
        expectedModel.addAppointment(appointmentToAdd);

        assertCommandSuccess(addAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(outOfBoundIndex,
                new AppointmentDateTime("12/12/2020 12:00"), new AppointmentDateTime("12/12/2020 13:00"));

        assertCommandFailure(addAppointmentCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_appointmentAcceptedByModel_addSuccessful() throws Exception {
        AddAppointmentCommandTest.ModelStubAcceptingAppointmentAdded modelStub = new AddAppointmentCommandTest
                .ModelStubAcceptingAppointmentAdded();
        Appointment validAppointment = new AppointmentBuilder()
                .withPatientName(VALID_PATIENT_NAME_FIRST)
                .withPatientIc(VALID_PATIENT_IC_FIRST)
                .withStartTime(VALID_START_TIME_FIRST)
                .withEndTime(VALID_END_TIME_FIRST)
                .build();

        CommandResult commandResult = new AddAppointmentCommand(validAppointment).execute(modelStub);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, validAppointment),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAppointment), modelStub.appointmentsAdded);
    }

    @Test
    public void execute_conflictingAppointment_throwsCommandException() {
        Appointment validAppointment = new AppointmentBuilder()
                .withPatientName(VALID_PATIENT_NAME_FIRST)
                .withPatientIc(VALID_PATIENT_IC_FIRST)
                .withStartTime(VALID_START_TIME_FIRST)
                .withEndTime(VALID_END_TIME_FIRST)
                .build();
        AddAppointmentCommand addAppointmentCommand = new AddAppointmentCommand(validAppointment);
        AddAppointmentCommandTest.ModelStub modelStub = new AddAppointmentCommandTest
                .ModelStubWithAppointment(validAppointment);
        assertThrows(CommandException.class, AddAppointmentCommand
                .MESSAGE_CONFLICTING_APP, () -> addAppointmentCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Appointment aliceAppointment = new AppointmentBuilder()
                .withPatientName(VALID_PATIENT_NAME_FIRST)
                .withPatientIc(VALID_PATIENT_IC_FIRST)
                .withStartTime(VALID_START_TIME_FIRST)
                .withEndTime(VALID_END_TIME_FIRST)
                .build();
        Appointment bobAppointment = new AppointmentBuilder()
                .withPatientName(VALID_PATIENT_NAME_SECOND)
                .withPatientIc(VALID_PATIENT_IC_SECOND)
                .withStartTime(VALID_START_TIME_SECOND)
                .withEndTime(VALID_END_TIME_SECOND)
                .build();
        AddAppointmentCommand addAliceAppointmentCommand = new AddAppointmentCommand(aliceAppointment);
        AddAppointmentCommand addBobAppointmentCommand = new AddAppointmentCommand(bobAppointment);

        // same object -> returns true
        Assertions.assertTrue(addAliceAppointmentCommand.equals(addAliceAppointmentCommand));

        // same values -> returns true
        AddAppointmentCommand addAliceCommandCopy = new AddAppointmentCommand(aliceAppointment);
        Assertions.assertTrue(addAliceAppointmentCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        Assertions.assertFalse(addAliceAppointmentCommand.equals(1));

        // null -> returns false
        Assertions.assertFalse(addAliceAppointmentCommand.equals(null));

        // different patient -> returns false
        Assertions.assertFalse(addAliceAppointmentCommand.equals(addBobAppointmentCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getCliniCalFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCliniCalFilePath(Path cliniCalFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCliniCal(ReadOnlyCliniCal cliniCal) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCliniCal getCliniCal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePatient(Patient target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAppointment(Appointment target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAppointment(Appointment target, Appointment editedAppointment) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Appointment> getFilteredAppointmentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitCliniCal(String command) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoCliniCal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoCliniCal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getUndoCommand() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getRedoCommand() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoCliniCal() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoCliniCal() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single patient.
     */
    private class ModelStubWithAppointment extends AddAppointmentCommandTest.ModelStub {
        private final Appointment appointment;

        ModelStubWithAppointment(Appointment appointment) {
            requireNonNull(appointment);
            this.appointment = appointment;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return this.appointment.isSameAppointmentTime(appointment);
        }
    }

    /**
     * A Model stub that always accept the patient being added.
     */
    private class ModelStubAcceptingAppointmentAdded extends AddAppointmentCommandTest.ModelStub {
        final ArrayList<Appointment> appointmentsAdded = new ArrayList<>();

        @Override
        public boolean hasAppointment(Appointment appointment) {
            requireNonNull(appointment);
            return appointmentsAdded.stream().anyMatch(appointment::isSameAppointmentTime);
        }

        @Override
        public void addAppointment(Appointment appointment) {
            requireNonNull(appointment);
            appointmentsAdded.add(appointment);
        }

        @Override
        public ReadOnlyCliniCal getCliniCal() {
            return new CliniCal();
        }

        @Override
        public void commitCliniCal(String command) {
            // Called by AddCommand#execute
        }
    }

}
