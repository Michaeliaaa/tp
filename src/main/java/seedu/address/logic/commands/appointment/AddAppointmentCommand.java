package seedu.address.logic.commands.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_STARTTIME;

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

    public static final String COMMAND_WORD = "newappt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds appointment to the appointment list.\n"
            + "Parameters: "
            + "PATIENT_INDEX "
            + PREFIX_APPT_STARTTIME + "START_TIME "
            + PREFIX_APPT_DURATION + "DURATION_IN_MINUTES\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + PREFIX_APPT_STARTTIME + "2020-10-23 21:00 "
            + PREFIX_APPT_DURATION + "60";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPT = "This appointment already exists in the calendar.";

    private Appointment toAdd;
    private final Index targetIndex;
    private final AppointmentDateTime startTime;
    private final AppointmentDateTime endTime;

    /**
     * Creates an AddAppointmentCommand to add the specified {@code Patient}
     */
    public AddAppointmentCommand(Index targetIndex, AppointmentDateTime startTime, AppointmentDateTime endTime) {
        this.targetIndex = targetIndex;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patient = lastShownList.get(targetIndex.getZeroBased());

        Name patientName = patient.getName();
        IcNumber patientIC = patient.getIcNumber();

        Appointment toAdd = new Appointment(patientName, patientIC, startTime, endTime);

        if (model.getFilteredAppointmentList().contains(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPT);
        } else {
            model.addAppointment(toAdd);
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
