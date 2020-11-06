package seedu.address.model.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_START_END;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;


/**
 * Represents a patient's Appointment in the CliniCal application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    private Name patientName;
    private IcNumber patientIc;
    private AppointmentDateTime startTime;
    private AppointmentDateTime endTime;

    /**
     * Patient name, appointment time must be present and not null.
     */
    public Appointment(Name patientName, IcNumber patientIc, AppointmentDateTime startTime,
                       AppointmentDateTime endTime) {
        requireAllNonNull(patientName, startTime, endTime);
        checkArgument(isValidStartEnd(startTime, endTime), MESSAGE_INVALID_APPOINTMENT_START_END);
        // should also check whether a patient is inside the patient database
        this.patientName = patientName;
        this.patientIc = patientIc;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Validates that starting time of appointment provided is earlier than ending time provided
     *
     * @param startTime starting time of the appointment
     * @param endTime   ending time of the appointment
     * @return true if starting time is earlier than ending time
     */
    public static boolean isValidStartEnd(AppointmentDateTime startTime, AppointmentDateTime endTime) {
        return startTime.compareTo(endTime) < 0;
    }

    public static int compare(Appointment appointment1, Appointment appointment2) {
        return appointment1.getStartTime().compareTo(appointment2.getStartTime());
    }

    public Name getPatientName() {
        return patientName;
    }

    public IcNumber getPatientIc() {
        return patientIc;
    }

    public AppointmentDateTime getStartTime() {
        return startTime;
    }

    public AppointmentDateTime getEndTime() {
        return endTime;
    }

    public int getDuration() {
        return (int) getStartTime().computeDuration(getEndTime());
    }

    /**
     * Returns true if both appointments have the same patient name and appointment time.
     * This defines a stronger notion of equality between two appointments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Appointment)) {
            return false;
        }
        Appointment otherAppointment = (Appointment) other;
        return otherAppointment.getPatientName().equals(getPatientName())
                && otherAppointment.getPatientIc().equals(getPatientIc())
                && otherAppointment.getStartTime().equals(getStartTime())
                && otherAppointment.getEndTime().equals(getEndTime());
    }

    /**
     * Returns true if both appointments have the same appointment time.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointmentTime(Appointment other) {
        if (other == this) {
            return true;
        }

        return other != null
                && other.getStartTime().equals(getStartTime())
                && other.getEndTime().equals(getEndTime());
    }

    /**
     * Returns true if both appointments have overlapping appointment time that causes time conflict.
     */
    public boolean hasTimeConflict(Appointment other) {
        int startDiff = this.getStartTime().compareTo(other.getStartTime());
        int endDiff = this.getEndTime().compareTo(other.getEndTime());
        int startEndDiff = this.getStartTime().compareTo(other.getEndTime());
        int endStartDiff = this.getEndTime().compareTo(other.getStartTime());
        if (startDiff * endDiff <= 0) {
            return true;
        }
        if (startDiff * startEndDiff < 0) {
            return true;
        }
        if (endDiff * endStartDiff < 0) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patientName, startTime, endTime);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient Name: ")
                .append(getPatientName() + "\n")
                .append("Patient NRIC: ")
                .append(getPatientIc() + "\n")
                .append("Starting Time: ")
                .append(getStartTime() + "\n")
                .append("Ending Time: ")
                .append(getEndTime());
        return builder.toString();
    }

}
