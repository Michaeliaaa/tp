package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_PATIENTIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_PATIENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_STARTTIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;

/**
 * Edits the details of an existing appointment in the CliniCal application.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "editappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the appointment identified "
            + "by the index number used in the displayed appointment list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_APP_PATIENTNAME + "PATIENT_NAME] "
            + "[" + PREFIX_APP_PATIENTIC + "PATIENT_IC] "
            + "[" + PREFIX_APP_STARTTIME + "START_TIME] "
            + "[" + PREFIX_APP_DURATION + "DURATION_IN_MINUTES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APP_STARTTIME + "23/12/2020 11:00 "
            + PREFIX_APP_DURATION + "40";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "NOTE : If you have edited patient name "
            + "or patient NRIC, please make sure to update the changes to the relevant patient, "
            + "using the 'edit' command.\n\nEdited appointment:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_CONFLICTING_APPOINTMENT =
            "The new appointment time clashes with another appointment time.";

    private final Index index;
    private final EditAppointmentDescriptor editAppointmentDescriptor;

    /**
     * @param index of the Appointment in the filtered Appointment list to edit
     * @param editAppointmentDescriptor details to edit the Appointment with
     */
    public EditAppointmentCommand(Index index, EditAppointmentDescriptor editAppointmentDescriptor) {
        requireNonNull(index);
        requireNonNull(editAppointmentDescriptor);

        this.index = index;
        this.editAppointmentDescriptor = new EditAppointmentDescriptor(editAppointmentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToEdit = lastShownList.get(index.getZeroBased());
        Appointment editedAppointment = createEditedAppointment(appointmentToEdit, editAppointmentDescriptor);

        // check if the updated appointment clashes with other appointments
        if (!appointmentToEdit.isSameAppointmentTime(editedAppointment)) {
            UniqueAppointmentList temp = new UniqueAppointmentList();
            temp.setAppointments(model.getFilteredAppointmentList());
            temp.remove(appointmentToEdit);
            if (temp.clashes(editedAppointment)) {
                throw new CommandException(MESSAGE_CONFLICTING_APPOINTMENT);
            }
        }

        model.setAppointment(appointmentToEdit, editedAppointment);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        model.commitCliniCal(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT, COMMAND_WORD + " for ",
            editedAppointment));
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedAppointment));
    }

    /**
     * Creates and returns a {@code Appointment} with the details of {@code appointmentToEdit}
     * edited with {@code editAppointmentDescriptor}.
     */
    private static Appointment createEditedAppointment(Appointment appointmentToEdit, EditAppointmentDescriptor
            editAppointmentDescriptor) throws CommandException {
        assert appointmentToEdit != null;

        int oldDuration = appointmentToEdit.getDuration();
        Name updatedPatientName = editAppointmentDescriptor.getPatientName().orElse(appointmentToEdit.getPatientName());
        IcNumber updatedPatientIc = editAppointmentDescriptor.getPatientIc().orElse(appointmentToEdit.getPatientIc());
        AppointmentDateTime updatedStartTime = appointmentToEdit.getStartTime();
        AppointmentDateTime updatedEndTime = appointmentToEdit.getEndTime();

        // when start edited, duration not edited
        // update endTime based on updated startTime
        if (editAppointmentDescriptor.getStartTime().isPresent()
                && !editAppointmentDescriptor.getDuration().isPresent()) {
            updatedStartTime = editAppointmentDescriptor.getStartTime().get();
            updatedEndTime = new AppointmentDateTime(updatedStartTime.getDateTimeStr(), oldDuration);
        }

        // when start not edited, duration edited
        // update endTime based on updated duration
        if (!editAppointmentDescriptor.getStartTime().isPresent()
                && editAppointmentDescriptor.getDuration().isPresent()) {
            updatedEndTime = new AppointmentDateTime(appointmentToEdit.getStartTime().getDateTimeStr(),
                    editAppointmentDescriptor.getDuration().get());
        }

        // when start edited, duration edited
        // update endTime based on updated startTime and duration
        if (editAppointmentDescriptor.getStartTime().isPresent()
                && editAppointmentDescriptor.getDuration().isPresent()) {
            updatedStartTime = editAppointmentDescriptor.getStartTime().get();
            updatedEndTime = new AppointmentDateTime(updatedStartTime.getDateTimeStr(),
                    editAppointmentDescriptor.getDuration().get());
        }

        // when start not edited, duration not edited
        // no need to update startTime and endTime
        if (!editAppointmentDescriptor.getStartTime().isPresent()
                && !editAppointmentDescriptor.getDuration().isPresent()) {
        }

        if (updatedStartTime.compareTo(updatedEndTime) >= 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_START_END);
        }

        return new Appointment(updatedPatientName, updatedPatientIc, updatedStartTime, updatedEndTime);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;
        return index.equals(e.index)
                && editAppointmentDescriptor.equals(e.editAppointmentDescriptor);
    }

    /**
     * Stores the details to edit the Appointment with. Each non-empty field value will replace the
     * corresponding field value of the Appointment.
     */
    public static class EditAppointmentDescriptor {
        private Name patientName;
        private IcNumber patientIc;
        private AppointmentDateTime startTime;
        private Integer duration;

        public EditAppointmentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code allergies} is used internally.
         */
        public EditAppointmentDescriptor(EditAppointmentDescriptor toCopy) {
            setPatientName(toCopy.patientName);
            setPatientIc(toCopy.patientIc);
            setStartTime(toCopy.startTime);
            setDuration(toCopy.duration);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(patientName, patientIc, startTime, duration);
        }

        public void setPatientName(Name patientName) {
            this.patientName = patientName;
        }

        public Optional<Name> getPatientName() {
            return Optional.ofNullable(patientName);
        }

        public void setPatientIc(IcNumber patientIc) {
            this.patientIc = patientIc;
        }

        public Optional<IcNumber> getPatientIc() {
            return Optional.ofNullable(patientIc);
        }

        public void setStartTime(AppointmentDateTime startTime) {
            this.startTime = startTime;
        }

        public Optional<AppointmentDateTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public Optional<Integer> getDuration() {
            return Optional.ofNullable(duration);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAppointmentDescriptor)) {
                return false;
            }

            // state check
            EditAppointmentDescriptor e = (EditAppointmentDescriptor) other;

            return getPatientName().equals(e.getPatientName())
                    && getPatientIc().equals(e.getPatientIc())
                    && getStartTime().equals(e.getStartTime())
                    && getDuration().equals(e.getDuration());
        }
    }
}
