package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_STARTTIME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "addappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds appointment to the appointment list.\n"
            + "Parameters: "
            + "PATIENT_INDEX "
            + PREFIX_APP_STARTTIME + "START_TIME "
            + PREFIX_APP_DURATION + "DURATION_IN_MINUTES\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_APP_STARTTIME + "12/12/2020 10:00 "
            + PREFIX_APP_DURATION + "15";

    public static final String MESSAGE_SUCCESS = "New appointment added:\n%1$s";
    public static final String MESSAGE_CONFLICTING_APP =
            "This appointment clashes with another appointment in your schedule.";

    private Appointment toAdd;
    private final Index targetIndex;
    private final AppointmentDateTime startTime;
    private final AppointmentDateTime endTime;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Index targetIndex, AppointmentDateTime startTime, AppointmentDateTime endTime) {
        assert targetIndex != null : "targetIndex cannot be null.";
        assert startTime != null : "startTime cannot be null.";
        assert endTime != null : "endTime cannot be null.";
        this.targetIndex = targetIndex;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Appointment}
     */
    public AddAppointmentCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
        startTime = appointment.getStartTime();
        endTime = appointment.getEndTime();
        targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (targetIndex == null) {
            requireNonNull(toAdd);
            if (model.hasAppointment(toAdd)) {
                throw new CommandException(MESSAGE_CONFLICTING_APP);
            }
            model.addAppointment(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } else {
            List<Patient> lastShownList = model.getFilteredPatientList();
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
            }

            Patient patient = lastShownList.get(targetIndex.getZeroBased());

            Name patientName = patient.getName();
            IcNumber patientIC = patient.getIcNumber();

            toAdd = new Appointment(patientName, patientIC, startTime, endTime);

            if (model.hasAppointment(toAdd)) {
                throw new CommandException(MESSAGE_CONFLICTING_APP);
            }

            model.addAppointment(toAdd);
            model.commitCliniCal(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT, COMMAND_WORD + " for ",
                toAdd));
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddAppointmentCommand)) {
            return false;
        }

        AddAppointmentCommand e = (AddAppointmentCommand) other;
        return toAdd.equals(e.toAdd);
    }
}
