package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Sex;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ICNUMBER = "!1234567Z";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_SEX = "MF";
    private static final String INVALID_BLOODTYPE = "C+";
    private static final String INVALID_ALLERGY = "#penicillin";

    private static final String VALID_NAME = "Rachel Walker";
    private static final String VALID_PHONE = "123456";
    private static final String VALID_ICNUMBER = "S1234567Z";
    private static final String VALID_ADDRESS = "123 Main Street #0505";
    private static final String VALID_EMAIL = "rachel@example.com";
    private static final String VALID_SEX = "F";
    private static final String VALID_BLOODTYPE = "A+";
    private static final String VALID_ALLERGY_1 = "aspirin";
    private static final String VALID_ALLERGY_2 = "penicillin";

    private static final String WHITESPACE = " \t\r\n";

    // Test for invalid input.
    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    // Test for invalid input.
    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    // Test for valid input.
    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PATIENT, ParserUtil.parseIndex("  1  "));
    }

    // Test for null input.
    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    // Test for invalid input.
    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    // Test for valid input.
    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    // Test for valid input.
    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    // Test for null input.
    @Test
    public void parsePhone_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parsePhone((String) null));
    }

    // Test for invalid input.
    @Test
    public void parsePhone_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_PHONE));
    }

    // Test for valid input.
    @Test
    public void parsePhone_validValueWithoutWhitespace_returnsPhone() throws Exception {
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(VALID_PHONE));
    }

    // Test for valid input.
    @Test
    public void parsePhone_validValueWithWhitespace_returnsTrimmedPhone() throws Exception {
        String phoneWithWhitespace = WHITESPACE + VALID_PHONE + WHITESPACE;
        Phone expectedPhone = new Phone(VALID_PHONE);
        assertEquals(expectedPhone, ParserUtil.parsePhone(phoneWithWhitespace));
    }

    // Test for null input.
    @Test
    public void parseIcNumber_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIcNumber((String) null));
    }

    // Test for invalid input.
    @Test
    public void parseIcNumber_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parsePhone(INVALID_ICNUMBER));
    }

    // Test for valid input.
    @Test
    public void parseIcNumber_validValueWithoutWhitespace_returnsIcNumber() throws Exception {
        IcNumber expectedIcNumber = new IcNumber(VALID_ICNUMBER);
        assertEquals(expectedIcNumber, ParserUtil.parseIcNumber(VALID_ICNUMBER));
    }

    // Test for valid input.
    @Test
    public void parseIcNumber_validValueWithWhitespace_returnsTrimmedIcNumber() throws Exception {
        String icNumberWithWhitespace = WHITESPACE + VALID_ICNUMBER + WHITESPACE;
        IcNumber expectedIcNumber = new IcNumber(VALID_ICNUMBER);
        assertEquals(expectedIcNumber, ParserUtil.parseIcNumber(icNumberWithWhitespace));
    }

    // Test for null input.
    @Test
    public void parseAddress_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAddress((String) null));
    }

    // Test for invalid input.
    @Test
    public void parseAddress_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAddress(INVALID_ADDRESS));
    }

    // Test for valid input.
    @Test
    public void parseAddress_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(VALID_ADDRESS));
    }

    // Test for valid input.
    @Test
    public void parseAddress_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_ADDRESS + WHITESPACE;
        Address expectedAddress = new Address(VALID_ADDRESS);
        assertEquals(expectedAddress, ParserUtil.parseAddress(addressWithWhitespace));
    }

    // Test for null input.
    @Test
    public void parseEmail_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseEmail((String) null));
    }

    // Test for invalid input.
    @Test
    public void parseEmail_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseEmail(INVALID_EMAIL));
    }

    // Test for valid input.
    @Test
    public void parseEmail_validValueWithoutWhitespace_returnsEmail() throws Exception {
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(VALID_EMAIL));
    }

    // Test for valid input.
    @Test
    public void parseEmail_validValueWithWhitespace_returnsTrimmedEmail() throws Exception {
        String emailWithWhitespace = WHITESPACE + VALID_EMAIL + WHITESPACE;
        Email expectedEmail = new Email(VALID_EMAIL);
        assertEquals(expectedEmail, ParserUtil.parseEmail(emailWithWhitespace));
    }

    // Test for null input.
    @Test
    public void parseSex_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseSex((String) null));
    }

    // Test for invalid input.
    @Test
    public void parseSex_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseSex(INVALID_SEX));
    }

    // Test for valid input.
    @Test
    public void parseSex_validValueWithoutWhitespace_returnsSex() throws Exception {
        Sex expectedSex = new Sex(VALID_SEX);
        assertEquals(expectedSex, ParserUtil.parseSex(VALID_SEX));
    }

    // Test for valid input.
    @Test
    public void parseSex_validValueWithWhitespace_returnsTrimmedSex() throws Exception {
        String sexWithWhitespace = WHITESPACE + VALID_SEX + WHITESPACE;
        Sex expectedSex = new Sex(VALID_SEX);
        assertEquals(expectedSex, ParserUtil.parseSex(sexWithWhitespace));
    }

    // Test for null input.
    @Test
    public void parseBloodType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseBloodType((String) null));
    }

    // Test for invalid input.
    @Test
    public void parseBloodType_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseBloodType(INVALID_BLOODTYPE));
    }

    // Test for valid input.
    @Test
    public void parseBloodType_validValueWithoutWhitespace_returnsBloodType() throws Exception {
        BloodType expectedBloodType = new BloodType(VALID_BLOODTYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(VALID_BLOODTYPE));
    }

    // Test for valid input.
    @Test
    public void parseBloodType_validValueWithWhitespace_returnsTrimmedBloodType() throws Exception {
        String bloodTypeWithWhitespace = WHITESPACE + VALID_BLOODTYPE + WHITESPACE;
        BloodType expectedBloodType = new BloodType(VALID_BLOODTYPE);
        assertEquals(expectedBloodType, ParserUtil.parseBloodType(bloodTypeWithWhitespace));
    }

    // Test for null input.
    @Test
    public void parseAllergy_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseAllergy(null));
    }

    // Test for invalid input.
    @Test
    public void parseAllergy_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAllergy(INVALID_ALLERGY));
    }

    // Test for valid input.
    @Test
    public void parseAllergy_validValueWithoutWhitespace_returnsTag() throws Exception {
        Allergy expectedAllergy = new Allergy(VALID_ALLERGY_1);
        assertEquals(expectedAllergy, ParserUtil.parseAllergy(VALID_ALLERGY_1));
    }

    // Test for valid input.
    @Test
    public void parseAllergy_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_ALLERGY_1 + WHITESPACE;
        Allergy expectedAllergy = new Allergy(VALID_ALLERGY_1);
        assertEquals(expectedAllergy, ParserUtil.parseAllergy(tagWithWhitespace));
    }

    // Test for invalid allergy with symbol.
    @Test
    public void parseAllergy_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseAllergies(Arrays.asList(VALID_ALLERGY_1,
                INVALID_ALLERGY)));
    }

    // Valid input test for empty set.
    @Test
    public void parseAllergy_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseAllergies(Collections.emptyList()).isEmpty());
    }

    // Valid input test.
    @Test
    public void parseAllergy_collectionWithValidAllergies_returnsAllergySet() throws Exception {
        Set<Allergy> actualAllergySet = ParserUtil.parseAllergies(Arrays.asList(VALID_ALLERGY_1, VALID_ALLERGY_2));
        Set<Allergy> expectedAllergySet = new HashSet<Allergy>(Arrays.asList(new Allergy(VALID_ALLERGY_1),
                new Allergy(VALID_ALLERGY_2)));

        assertEquals(expectedAllergySet, actualAllergySet);
    }

    // Test for error in visit index. Non-int input.
    @Test
    public void parseVisitIndex_invalidInput_throwsParseException() {
        assertThrows(NumberFormatException.class, () -> ParserUtil.parseVisitIndex("10 a"));
    }

    // Test for exceeded index in visit index.
    @Test
    public void parseVisitIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, "The visit index provided is invalid", ()
            -> ParserUtil.parseVisitIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    // Test for error in visit date.
    @Test
    public void parseVisit_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, "Visits should take date in valid format.", ()
            -> ParserUtil.parseVisit("test"));
    }

    // Test for error in date.
    @Test
    public void parseDateTime_invalidDateTime_throwsParseException() {
        assertThrows(ParseException.class,
                AppointmentDateTime.MESSAGE_CONSTRAINTS, ()
                -> ParserUtil.parseDateTime("test"));
    }

    // Test for error in date time.
    @Test
    public void parseDuration_invalidDateTime_throwsParseException() {
        assertThrows(ParseException.class,
                AppointmentDateTime.MESSAGE_CONSTRAINTS, ()
                -> ParserUtil.parseDurationWithStart("test", "30"));
    }

    // Test for error in duration.
    @Test
    public void parseDuration_invalidDuration_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_INVALID_DURATION, ()
                -> ParserUtil.parseDurationWithStart("10/10/2030 09:30", "test"));
    }
}
