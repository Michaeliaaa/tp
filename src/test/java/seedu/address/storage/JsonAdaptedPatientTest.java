package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.model.patient.Sex;
import seedu.address.model.tag.ColorTag;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ICNUMBER = "!1234567Z";
    private static final String INVALID_VISITDATE = "32/13/2020";
    private static final String INVALID_VISITDIAGNOSIS = "no fever";
    private static final String INVALID_VISITPRESCRIPTION = "vitamin c";
    private static final String INVALID_VISITCOMMENT = "patient is male";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_PROFILEPICTURE = " ";
    private static final String INVALID_SEX = "MF";
    private static final String INVALID_BLOODTYPE = "C+";
    private static final String INVALID_ALLERGY = "#penicillin";
    private static final String INVALID_COLORTAG = "gibberish";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_ICNUMBER = BENSON.getIcNumber().toString();
    private static final List<JsonAdaptedVisit> VALID_VISITHISTORY = BENSON.getVisitHistory().getVisits().stream()
        .map(JsonAdaptedVisit::new)
        .collect(Collectors.toList());
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_PROFILEPICTURE = "data/stock_picture.png";
    private static final String VALID_SEX = BENSON.getSex().toString();
    private static final String VALID_BLOODTYPE = BENSON.getBloodType().toString();
    private static final List<JsonAdaptedAllergy> VALID_ALLERGIES = BENSON.getAllergies().stream()
            .map(JsonAdaptedAllergy::new)
            .collect(Collectors.toList());
    private static final String VALID_COLORTAG = BENSON.getColorTag().toString();

    @Test
    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, patient.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(INVALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                        VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                        VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(null, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, INVALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                        VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                        VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, null, VALID_ICNUMBER, VALID_VISITHISTORY,
                VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidIcNumber_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, INVALID_ICNUMBER, VALID_VISITHISTORY,
                        VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                        VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = IcNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullIcNumber_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, null, VALID_VISITHISTORY,
                VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, IcNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                        INVALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                        VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                null, VALID_EMAIL, VALID_PROFILEPICTURE,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                        VALID_ADDRESS, INVALID_EMAIL, VALID_PROFILEPICTURE,
                        VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                VALID_ADDRESS, null, VALID_PROFILEPICTURE,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    // Invalid picture input
    public void toModelType_invalidPicture_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                        VALID_ADDRESS, VALID_EMAIL, INVALID_PROFILEPICTURE,
                        VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = ProfilePicture.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    // Null picture input
    public void toModelType_nullPicture_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                VALID_ADDRESS, VALID_EMAIL, null,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProfilePicture.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidSex_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                        VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                        INVALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = Sex.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullSex_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                null, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Sex.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidBloodType_throwsIllegalValueException() {
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                        VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                        VALID_SEX, INVALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = BloodType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullBloodType_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                VALID_SEX, null, VALID_ALLERGIES, VALID_COLORTAG);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidAllergies_throwsIllegalValueException() {
        List<JsonAdaptedAllergy> invalidAllergies = new ArrayList<>(VALID_ALLERGIES);
        invalidAllergies.add(new JsonAdaptedAllergy(INVALID_ALLERGY));
        JsonAdaptedPatient patient =
                new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                        VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                        VALID_SEX, VALID_BLOODTYPE, invalidAllergies, VALID_COLORTAG);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

    @Test
    public void toModelType_invalidColor_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, INVALID_COLORTAG);
        String expectedMessage = ColorTag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullColor_throwsIllegalValueException() {
        JsonAdaptedPatient patient = new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, VALID_VISITHISTORY,
                VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ColorTag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_invalidVisitHistory_throwsIllegalValueException() {
        List<JsonAdaptedVisit> invalidVisits = new ArrayList<>(VALID_VISITHISTORY);
        invalidVisits.add(new JsonAdaptedVisit(INVALID_VISITDATE, INVALID_NAME, INVALID_VISITDIAGNOSIS,
            INVALID_VISITPRESCRIPTION, INVALID_VISITCOMMENT));
        JsonAdaptedPatient patient =
            new JsonAdaptedPatient(VALID_NAME, VALID_PHONE, VALID_ICNUMBER, invalidVisits,
                VALID_ADDRESS, VALID_EMAIL, VALID_PROFILEPICTURE,
                VALID_SEX, VALID_BLOODTYPE, VALID_ALLERGIES, VALID_COLORTAG);
        assertThrows(IllegalValueException.class, patient::toModelType);
    }

}
