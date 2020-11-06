package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_PATIENTIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_PATIENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLORTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_PATH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ICNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.model.appointment.AppointmentDateTime.DATE_TIME_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointment.EditAppointmentCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.CliniCal;
import seedu.address.model.Model;
import seedu.address.model.patient.NameContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.EditPatientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // for Patient
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_ICNUMBER_AMY = "S1111111A";
    public static final String VALID_ICNUMBER_BOB = "S2222222B";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_PROFILE_PICTURE_AMY = "data/stock_picture.png";
    public static final String VALID_PROFILE_PICTURE_BOB = "data/stock_picture.png";
    public static final String VALID_SEX_AMY = "F";
    public static final String VALID_SEX_BOB = "M";
    public static final String VALID_BLOODTYPE_AMY = "A+";
    public static final String VALID_BLOODTYPE_BOB = "B+";
    public static final String VALID_ALLERGY_ASPIRIN = "aspirin";
    public static final String VALID_ALLERGY_PENICILLIN = "penicillin";
    public static final String VALID_COLORTAG_ORANGE = "orange";
    public static final String VALID_COLORTAG_RED = "red";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String ICNUMBER_DESC_AMY = " " + PREFIX_ICNUMBER + VALID_ICNUMBER_AMY;
    public static final String ICNUMBER_DESC_BOB = " " + PREFIX_ICNUMBER + VALID_ICNUMBER_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String PROFILE_PICTURE_DESC_AMY = " " + PREFIX_FILE_PATH + VALID_PROFILE_PICTURE_AMY;
    public static final String PROFILE_PICTURE_DESC_BOB = " " + PREFIX_FILE_PATH + VALID_PROFILE_PICTURE_BOB;
    public static final String SEX_DESC_AMY = " " + PREFIX_SEX + VALID_SEX_AMY;
    public static final String SEX_DESC_BOB = " " + PREFIX_SEX + VALID_SEX_BOB;
    public static final String BLOODTYPE_DESC_AMY = " " + PREFIX_BLOODTYPE + VALID_BLOODTYPE_AMY;
    public static final String BLOODTYPE_DESC_BOB = " " + PREFIX_BLOODTYPE + VALID_BLOODTYPE_BOB;
    public static final String ALLERGY_DESC_AMY = " " + PREFIX_ALLERGY + VALID_ALLERGY_ASPIRIN;
    public static final String ALLERGY_DESC_BOB = " " + PREFIX_ALLERGY + VALID_ALLERGY_PENICILLIN;
    public static final String COLORTAG_DESC_ORANGE = " " + PREFIX_COLORTAG + VALID_COLORTAG_ORANGE;
    public static final String COLORTAG_DESC_RED = " " + PREFIX_COLORTAG + VALID_COLORTAG_RED;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_ICNUMBER_DESC = " " + PREFIX_ICNUMBER
            + "S1234567!"; // '!' not allowed in ic numbers
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_SEX_DESC = " " + PREFIX_SEX + "MF"; // 'MF' not allowed in sex
    public static final String INVALID_BLOODTYPE_DESC = " " + PREFIX_BLOODTYPE + "C+"; // 'C' not allowed in blood types
    public static final String INVALID_ALLERGY_DESC = " " + PREFIX_ALLERGY
            + "penicillin*"; // '*' not allowed in allergies
    public static final String INVALID_PROFILE_PICTURE_AMY = "data/nosuchpictureexists.png";
    public static final String INVALID_COLORTAG_DESC = " " + PREFIX_COLORTAG + "gibberish"; // non-color name

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPatientDescriptor DESC_AMY;
    public static final EditCommand.EditPatientDescriptor DESC_BOB;
    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_FIRST;
    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_SECOND;

    // for Visit
    public static final LocalDate VALID_VISITDATE_ONE = LocalDate.of(2019, 3, 19);
    public static final LocalDate VALID_VISITDATE_TWO = LocalDate.of(2018, 7, 6);
    public static final String VALID_DIAGNOSIS_ONE = "Hyperlipidemia";
    public static final String VALID_DIAGNOSIS_TWO = "Reflux esophagitis";
    public static final String VALID_PRESCRIPTION_ONE = "Vicodin";
    public static final String VALID_PRESCRIPTION_TWO = "Synthroid";
    public static final String VALID_COMMENT_ONE = "Need immediate attention";
    public static final String VALID_COMMENT_TWO = "No need to follow up";

    // for Appointment
    public static final String VALIDSTART_1 = LocalDateTime.now().plusDays(1).plusMinutes(10)
            .format(DATE_TIME_FORMATTER);
    public static final String VALIDEND_1 = LocalDateTime.now().plusDays(1).plusMinutes(40)
            .format(DATE_TIME_FORMATTER);
    public static final String VALIDSTART_2 = LocalDateTime.now().plusDays(1).plusMinutes(60)
            .format(DATE_TIME_FORMATTER);
    public static final String VALIDEND_2 = LocalDateTime.now().plusDays(1).plusMinutes(100)
            .format(DATE_TIME_FORMATTER);

    public static final String VALID_PATIENT_NAME_FIRST = "Fiona Apple";
    public static final String VALID_PATIENT_NAME_SECOND = "Seth MacFarlane";
    public static final String VALID_PATIENT_IC_FIRST = "S9631111F";
    public static final String VALID_PATIENT_IC_SECOND = "G1984861R";
    public static final String VALID_START_TIME_FIRST = VALIDSTART_1;
    public static final String VALID_START_TIME_SECOND = VALIDSTART_2;
    public static final String VALID_END_TIME_FIRST = VALIDEND_1;
    public static final String VALID_END_TIME_SECOND = VALIDEND_2;
    public static final String VALID_DURATION_FIRST = "" + Duration.between(
            LocalDateTime.parse(VALIDSTART_1, DATE_TIME_FORMATTER),
            LocalDateTime.parse(VALIDEND_1, DATE_TIME_FORMATTER)).toMinutes();
    public static final String VALID_DURATION_SECOND = "" + Duration.between(
            LocalDateTime.parse(VALIDSTART_2, DATE_TIME_FORMATTER),
            LocalDateTime.parse(VALIDEND_2, DATE_TIME_FORMATTER)).toMinutes();

    public static final String PATIENTNAME_DESC_FIRST = " " + PREFIX_APP_PATIENTNAME + VALID_PATIENT_NAME_FIRST;
    public static final String PATIENTNAME_DESC_SECOND = " " + PREFIX_APP_PATIENTNAME + VALID_PATIENT_NAME_SECOND;
    public static final String PATIENTIC_DESC_FIRST = " " + PREFIX_APP_PATIENTIC + VALID_PATIENT_IC_FIRST;
    public static final String PATIENTIC_DESC_SECOND = " " + PREFIX_APP_PATIENTIC + VALID_PATIENT_IC_SECOND;
    public static final String START_TIME_DESC_FIRST = " " + PREFIX_APP_STARTTIME + VALID_START_TIME_FIRST;
    public static final String START_TIME_DESC_SECOND = " " + PREFIX_APP_STARTTIME + VALID_START_TIME_SECOND;
    public static final String DURATION_DESC_FIRST = " " + PREFIX_APP_DURATION + VALID_DURATION_FIRST;
    public static final String DURATION_DESC_SECOND = " " + PREFIX_APP_DURATION + VALID_DURATION_SECOND;

    // '@' not allowed in Name
    public static final String INVALID_PATIENTNAME_DESC = " " + PREFIX_APP_PATIENTNAME + "Jane@";
    // '&' not allowed in IcNumber
    public static final String INVALID_PATIENTIC_DESC = " " + PREFIX_APP_PATIENTIC + "&1234567Z";
    // '!' not allowed in AppointmentDateTime
    public static final String INVALID_START_TIME_DESC = " " + PREFIX_APP_STARTTIME + "2020!11!11 11!50";
    // negative number not allowed in duration
    public static final String INVALID_DURATION_DESC = " " + PREFIX_APP_DURATION + "-50";

    static {
        DESC_AMY = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                .withIcNumber(VALID_ICNUMBER_AMY).withAddress(VALID_ADDRESS_AMY).withEmail(VALID_EMAIL_AMY)
                .withProfilePicture(VALID_PROFILE_PICTURE_AMY).withSex(VALID_SEX_AMY).withBloodType(VALID_BLOODTYPE_AMY)
                .withAllergies(VALID_ALLERGY_ASPIRIN)
                .build();
        DESC_BOB = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withIcNumber(VALID_ICNUMBER_BOB).withAddress(VALID_ADDRESS_BOB).withEmail(VALID_EMAIL_BOB)
                .withProfilePicture(VALID_PROFILE_PICTURE_BOB).withSex(VALID_SEX_BOB).withBloodType(VALID_BLOODTYPE_BOB)
                .withAllergies(VALID_ALLERGY_PENICILLIN)
                .build();
        DESC_FIRST = new EditAppointmentDescriptorBuilder().withPatientName(VALID_PATIENT_NAME_FIRST)
                .withPatientIc(VALID_PATIENT_IC_FIRST)
                .withStartTime(VALID_START_TIME_FIRST)
                .withDuration(VALID_DURATION_FIRST)
                .build();
        DESC_SECOND = new EditAppointmentDescriptorBuilder().withPatientName(VALID_PATIENT_NAME_SECOND)
                .withPatientIc(VALID_PATIENT_IC_SECOND)
                .withStartTime(VALID_START_TIME_SECOND)
                .withDuration(VALID_DURATION_SECOND)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the clinical, filtered patient list and selected patient in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        CliniCal expectedCliniCal = new CliniCal(actualModel.getCliniCal());
        List<Patient> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPatientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedCliniCal, actualModel.getCliniCal());
        assertEquals(expectedFilteredList, actualModel.getFilteredPatientList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - {@code expectedModel} <br> matches {@code actualModel}
     */
    public static void assertCommandPass(Command command, Model expectedModel,
                                         String expectedMessage, Model actualModel) {
        assertEquals(expectedModel, actualModel);
    }
    /**
     * Updates {@code model}'s filtered list to show only the patient at the given {@code targetIndex} in the
     * {@code model}'s clinical.
     */
    public static void showPatientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPatientList().size());

        Patient patient = model.getFilteredPatientList().get(targetIndex.getZeroBased());
        final String[] splitName = patient.getName().fullName.split("\\s+");
        model.updateFilteredPatientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPatientList().size());
    }

    /**
     * Deletes the last patient in {@code model}'s filtered list from {@code model}'s CliniCal.
     */
    public static void deleteLastPatient(Model model) {
        ObservableList<Patient> listOfPatients = model.getFilteredPatientList();
        Index lastPatientIndex = Index.fromZeroBased(listOfPatients.size() - 1);
        Patient lastPatient = listOfPatients.get(lastPatientIndex.getZeroBased());
        model.deletePatient(lastPatient);
        String deleteLastInput = "delete" + lastPatientIndex.getOneBased();
        model.commitCliniCal(deleteLastInput);
    }

}
