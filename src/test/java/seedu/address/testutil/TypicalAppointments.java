package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;
import static seedu.address.model.appointment.AppointmentDateTime.DATE_TIME_FORMATTER;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.CliniCal;
import seedu.address.model.appointment.Appointment;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final String START1 = LocalDateTime.now().plusMinutes(10).format(DATE_TIME_FORMATTER);
    public static final String END1 = LocalDateTime.now().plusMinutes(50).format(DATE_TIME_FORMATTER);
    public static final String START2 = LocalDateTime.now().plusMinutes(60).format(DATE_TIME_FORMATTER);
    public static final String END2 = LocalDateTime.now().plusMinutes(90).format(DATE_TIME_FORMATTER);
    public static final String START3 = LocalDateTime.now().plusMinutes(100).format(DATE_TIME_FORMATTER);
    public static final String END3 = LocalDateTime.now().plusMinutes(150).format(DATE_TIME_FORMATTER);
    public static final String START4 = LocalDateTime.now().plusMinutes(170).format(DATE_TIME_FORMATTER);
    public static final String END4 = LocalDateTime.now().plusMinutes(200).format(DATE_TIME_FORMATTER);
    public static final String START5 = LocalDateTime.now().plusMinutes(240).format(DATE_TIME_FORMATTER);
    public static final String END5 = LocalDateTime.now().plusMinutes(300).format(DATE_TIME_FORMATTER);

    public static final Appointment APP1 = new AppointmentBuilder().withPatientName("Alice Pauline")
            .withStartTime(START1).withEndTime(END1).build();

    public static final Appointment APP2 = new AppointmentBuilder().withPatientName("Benson Meier")
            .withStartTime(START2).withEndTime(END2).build();

    public static final Appointment APP3 = new AppointmentBuilder().withPatientName("Carl Kurz")
            .withStartTime(START3).withEndTime(END3).build();

    public static final Appointment APP4 = new AppointmentBuilder().withPatientName("Daniel Meier")
            .withStartTime(START4).withEndTime(END4).build();

    public static final Appointment APP5 = new AppointmentBuilder().withPatientName("Elle Meyer")
            .withStartTime(START5).withEndTime(END5).build();

    // Manually added - Appointment's details found in {@code CommandTestUtil}
    public static final Appointment FIRST_APP = new AppointmentBuilder().withPatientName(VALID_PATIENT_NAME_FIRST)
            .withStartTime(VALID_START_TIME_FIRST).withEndTime(VALID_END_TIME_FIRST).build();
    public static final Appointment SECOND_APP = new AppointmentBuilder().withPatientName(VALID_PATIENT_NAME_SECOND)
            .withStartTime(VALID_START_TIME_SECOND).withEndTime(VALID_END_TIME_SECOND).build();

    // Appointments for checking conflicts
    public static final Appointment CONFLICTING_APPOINTMENT1 = new AppointmentBuilder()
            .withPatientName("Conflict One").withStartTime(START1).withEndTime(START2).build();
    public static final Appointment CONFLICTING_APPOINTMENT2 = new AppointmentBuilder()
            .withPatientName("Conflict Two").withStartTime(END1).withEndTime(END2).build();

    public static final Appointment NOCONFLICT_APPOINTMENT1 = new AppointmentBuilder()
            .withPatientName("NoConflict One").withStartTime(START1).withEndTime(END1)
            .build();
    public static final Appointment NOCONFLICT_APPOINTMENT2 = new AppointmentBuilder()
            .withPatientName("NoConflict One").withStartTime(START2).withEndTime(END2)
            .build();

    private TypicalAppointments() {} // prevents instantiation

    /**
     * Returns an {@code CliniCal} with all the typical appointments.
     */
    public static CliniCal getTypicalCliniCalWithAppointments() {
        CliniCal ab = new CliniCal();
        for (Appointment appointment : getTypicalAppointments()) {
            ab.addAppointment(appointment);
        }
        return ab;
    }

    public static List<Appointment> getTypicalAppointments() {
        return new ArrayList<>(Arrays.asList(APP1, APP2, APP3, APP4, APP5));
    }
}
