package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AppointmentDateTime {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final String MESSAGE_CONSTRAINTS =
            "Please check if you have entered a valid date in the following format: dd/MM/yyyy HH:mm\n"
                    + "Please note that only appointments of upcoming dates (including today) will be accepted.\n"
                    + "\nInput YYYY as 2xxx."
                    + "\nInput MM as 01 to 12."
                    + "\nInput dd as 01 to 31 (29 - 31 only if it is a valid day in that month)."
                    + "\nInput HH as 00 to 23."
                    + "\nInput mm as 00 to 59.";
    // Compared to other classes, this class uses the LocalDateTime class to check validity of the String
    // rather than a regex.

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code AppointmentDateTime}.
     *
     * @param dateTime a valid dateTime string to be parsed by LocalDateTime.
     */
    public AppointmentDateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    /**
     * Constructs a {@code AppointmentDateTime}.
     *
     * @param dateTime a valid dateTime string to be parsed by LocalDateTime.
     * @param duration a valid duration to add on
     */
    public AppointmentDateTime(String dateTime, int duration) {
        assert duration > 0 : "Invalid duration";
        requireAllNonNull(dateTime, duration);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER).plusMinutes(duration);
    }

    public String getDateTimeStr() {
        return this.dateTime.format(DATE_TIME_FORMATTER);
    }

    public long computeDuration(AppointmentDateTime other) {
        return Duration.between(this.dateTime, other.dateTime).toMinutes();
    }

    /**
     * Returns true if a given string is a valid AppointmentDateTime.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime parsed = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return parsed.format(DATE_TIME_FORMATTER).equals(test);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid upcoming AppointmentDateTime.
     */
    public static boolean isValidUpcomingDateTime(String test) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        try {
            LocalDateTime parsed = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            if (parsed.isBefore(currentDateTime)) {
                return false;
            }
            return parsed.format(DATE_TIME_FORMATTER).equals(test);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid AppointmentDateTime.
     */
    public static boolean isValidDateTimeForJson(String test) {
        try {
            LocalDateTime parsed = LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return parsed.format(DATE_TIME_FORMATTER).equals(test);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public int compareTo(AppointmentDateTime other) {
        return dateTime.compareTo(other.dateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AppointmentDateTime
                && dateTime.equals(((AppointmentDateTime) other).dateTime));
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public String toString() {
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
