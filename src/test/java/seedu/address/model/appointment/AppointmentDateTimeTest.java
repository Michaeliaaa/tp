package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentDateTime(null));
        assertThrows(NullPointerException.class, () -> new AppointmentDateTime(null, 60));
    }

    @Test
    public void constructor_invalidAppointmentDateTime_throwsIllegalArgumentException() {
        String invalidAppointmentDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new AppointmentDateTime(invalidAppointmentDateTime));
        assertThrows(IllegalArgumentException.class, () -> new AppointmentDateTime(invalidAppointmentDateTime, 60));
    }

    @Test
    public void isValidDateTime() {
        // null appointment datetime
        assertThrows(NullPointerException.class, () -> AppointmentDateTime.isValidDateTime(null));

        // invalid appointment datetime
        assertFalse(AppointmentDateTime.isValidDateTime("")); // empty string
        assertFalse(AppointmentDateTime.isValidDateTime(" ")); // spaces only
        assertFalse(AppointmentDateTime.isValidDateTime("202012152336")); // invalid format
        assertFalse(AppointmentDateTime.isValidDateTime("25/Dec/2020 23:36")); // invalid month
        assertFalse(AppointmentDateTime.isValidDateTime("55/12/2020 23:36")); // invalid day
        assertFalse(AppointmentDateTime.isValidDateTime("55/12/2020 88:36")); // invalid hour
        assertFalse(AppointmentDateTime.isValidDateTime("25/12/2020 23:99")); // invalid minute

        assertFalse(AppointmentDateTime.isValidDateTime("31/11/2020 01:00")); // bug found in PE-D
        assertFalse(AppointmentDateTime.isValidDateTime("29/02/2021 00:00"));

        // valid appointment datetime
        assertTrue(AppointmentDateTime.isValidDateTime("25/12/2025 23:36"));
    }
}
