package seedu.address.testutil;

import static seedu.address.model.appointment.AppointmentDateTime.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {

    public static final String DEFAULT_NAME = "Danny Williams";
    public static final String DEFAULT_IC = "S1234567A";
    public static final String DEFAULT_START_TIME = LocalDateTime.now().plusMinutes(10).format(DATE_TIME_FORMATTER);
    public static final String DEFAULT_END_TIME = LocalDateTime.now().plusMinutes(40).format(DATE_TIME_FORMATTER);

    private Name patientName;
    private IcNumber patientIC;
    private AppointmentDateTime startTime;
    private AppointmentDateTime endTime;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        patientName = new Name(DEFAULT_NAME);
        patientIC = new IcNumber(DEFAULT_IC);
        startTime = new AppointmentDateTime(DEFAULT_START_TIME);
        endTime = new AppointmentDateTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        patientName = appointmentToCopy.getPatientName();
        patientIC = appointmentToCopy.getPatientIc();
        startTime = appointmentToCopy.getStartTime();
        endTime = appointmentToCopy.getEndTime();
    }

    /**
     * Sets the {@code patientName} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientName(String patientName) {
        this.patientName = new Name(patientName);
        return this;
    }

    /**
     * Sets the {@code patientIC} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatientIc(String patientIC) {
        this.patientIC = new IcNumber(patientIC);
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withStartTime(String startTime) {
        this.startTime = new AppointmentDateTime(startTime);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withEndTime(String endTime) {
        this.endTime = new AppointmentDateTime(endTime);
        return this;
    }

    /**
     * Builds {@code Appointment} with the given fields.
     */
    public Appointment build() {
        return new Appointment(patientName, patientIC, startTime, endTime);
    }

}
