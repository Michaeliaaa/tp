package seedu.address.storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Name;
import seedu.address.model.visit.Visit;

/**
 * Jackson-friendly version of {@link Visit}.
 */
class JsonAdaptedVisit {

    private final String visitDate;
    private final String patientName;
    private final String diagnosis;
    private final String prescription;
    private final String comment;

    /**
     * Constructs a {@code JsonAdaptedVisit} with the given visit details.
     */
    @JsonCreator
    public JsonAdaptedVisit(@JsonProperty("visitDate") String visitDate,
                            @JsonProperty("patientName") String patientName,
                            @JsonProperty("diagnosis") String diagnosis,
                            @JsonProperty("prescription") String prescription,
                            @JsonProperty("comment") String comment) {
        this.visitDate = visitDate;
        this.patientName = patientName;
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.comment = comment;
    }

    /**
     * Converts a given {@code Visit} into this class for Jackson use.
     */
    public JsonAdaptedVisit(Visit visit) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = visit.getVisitDate().format(formatter);
        visitDate = formattedString;
        patientName = visit.getPatientName().toString();
        diagnosis = visit.getDiagnosis();
        prescription = visit.getPrescription();
        comment = visit.getComment();
    }

    /**
     * Converts this Visit object into the model's {@code Visit} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted allergy.
     */
    public Visit toModelType() throws IllegalValueException {
        if (Visit.isValidVisitDate(visitDate)) {
            // Do nothing.
        } else {
            throw new IllegalValueException(Visit.MESSAGE_CONSTRAINTS);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new Visit(LocalDate.parse(visitDate, formatter), new Name(patientName), diagnosis, prescription,
            comment);
    }
}
