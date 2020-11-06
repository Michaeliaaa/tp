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

import jfxtras.icalendarfx.components.VEvent;

/**
 * A utility class containing a list of {@code Appointment} objects to be used in tests.
 */
public class TypicalVEvents {
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

    public static final VEvent VEVENT1 = new VEventBuilder().withPatientName("Alice Pauline")
            .withStartTime(START1).withEndTime(END1).build();

    public static final VEvent VEVENT2 = new VEventBuilder().withPatientName("Benson Meier")
            .withStartTime(START2).withEndTime(END2).build();

    public static final VEvent VEVENT3 = new VEventBuilder().withPatientName("Carl Kurz")
            .withStartTime(START3).withEndTime(END3).build();

    public static final VEvent VEVENT4 = new VEventBuilder().withPatientName("Daniel Meier")
            .withStartTime(START4).withEndTime(END4).build();

    public static final VEvent VEVENT5 = new VEventBuilder().withPatientName("Elle Meyer")
            .withStartTime(START5).withEndTime(END5).build();

    // Manually added - VEvent's details found in {@code CommandTestUtil}
    public static final VEvent FIRST_VEVENT = new VEventBuilder().withPatientName(VALID_PATIENT_NAME_FIRST)
            .withStartTime(VALID_START_TIME_FIRST).withEndTime(VALID_END_TIME_FIRST).build();
    public static final VEvent SECOND_VEVENT = new VEventBuilder().withPatientName(VALID_PATIENT_NAME_SECOND)
            .withStartTime(VALID_START_TIME_SECOND).withEndTime(VALID_END_TIME_SECOND).build();

    private TypicalVEvents() {} // prevents instantiation

    //    /**
    //     * Returns an {@code CliniCal} with all the typical appointments.
    //     */
    //    public static CliniCal getTypicalCliniCal() {
    //        CliniCal ab = new CliniCal();
    //        for (VEvent vEvent : getTypicalVEvents()) {
    //            ab.addVEvent(vEvent);
    //        }
    //        return ab;
    //    }

    public static List<VEvent> getTypicalVEvents() {
        return new ArrayList<>(Arrays.asList(VEVENT1, VEVENT2, VEVENT3, VEVENT4, VEVENT5));
    }
}
