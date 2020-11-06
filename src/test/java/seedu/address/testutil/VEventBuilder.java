package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jfxtras.icalendarfx.components.VEvent;

/**
 * A utility class to help with building VEvent objects.
 */
public class VEventBuilder {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final String DEFAULT_NAME = "Danny Williams";
    public static final String DEFAULT_START_TIME = "02/02/2020 14:20";
    public static final String DEFAULT_END_TIME = "02/02/2020 14:35";

    private String patientName;
    private String startTime;
    private String endTime;

    /**
     * Creates a {@code VEventBuilder} with the default details.
     */
    public VEventBuilder() {
        patientName = DEFAULT_NAME;
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
    }

    /**
     * Initializes the VEventBuilder with the data of {@code vEventToCopy}.
     */
    public VEventBuilder(VEvent vEventToCopy) {
        patientName = vEventToCopy.getSummary().getValue();
        startTime = LocalDateTime.parse(vEventToCopy.getDateTimeStart().getValue().toString())
                .format(DATE_TIME_FORMATTER);
        endTime = LocalDateTime.parse(vEventToCopy.getDateTimeEnd().getValue().toString()).format(DATE_TIME_FORMATTER);
    }

    /**
     * Sets the {@code patientName} of the {@code VEvent} that we are building.
     */
    public VEventBuilder withPatientName(String patientName) {
        this.patientName = patientName;
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code VEvent} that we are building.
     */
    public VEventBuilder withStartTime(String startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code VEvent} that we are building.
     */
    public VEventBuilder withEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Builds {@code VEvent} with the given fields.
     */
    public VEvent build() {
        VEvent resultVEvent = new VEvent();
        resultVEvent.setSummary(patientName);
        resultVEvent.setDateTimeStart(LocalDateTime.parse(startTime, DATE_TIME_FORMATTER));
        resultVEvent.setDateTimeEnd(LocalDateTime.parse(endTime, DATE_TIME_FORMATTER));
        return resultVEvent;
    }

}
