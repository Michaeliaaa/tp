package seedu.address.logic.parser.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.DURATION_DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PATIENTIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PATIENTNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PATIENTIC_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.PATIENTIC_DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.PATIENTNAME_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.PATIENTNAME_DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_IC_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_IC_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PATIENT_NAME_SECOND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_SECOND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointment.EditAppointmentCommand;
import seedu.address.logic.commands.appointment.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;

public class EditAppointmentCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAppointmentCommand.MESSAGE_USAGE);

    private EditAppointmentCommandParser parser = new EditAppointmentCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PATIENT_NAME_FIRST, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditAppointmentCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PATIENTNAME_DESC_FIRST, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PATIENTNAME_DESC_FIRST, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid patient name
        assertParseFailure(parser, "1" + INVALID_PATIENTNAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid patient ic
        assertParseFailure(parser, "1" + INVALID_PATIENTIC_DESC, IcNumber.MESSAGE_CONSTRAINTS);
        // invalid start time
        assertParseFailure(parser, "1" + INVALID_START_TIME_DESC, AppointmentDateTime.MESSAGE_CONSTRAINTS);
        // invalid duration
        assertParseFailure(parser, "1" + INVALID_DURATION_DESC, ParserUtil.MESSAGE_NEGATIVE_INVALID_DURATION);

        // invalid patient name followed by valid patient ic
        assertParseFailure(parser, "1" + INVALID_PATIENTNAME_DESC + PATIENTIC_DESC_FIRST,
                Name.MESSAGE_CONSTRAINTS);

        // valid patient name followed by invalid patient ic. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PATIENTNAME_DESC_FIRST + INVALID_PATIENTIC_DESC,
                IcNumber.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_PATIENTNAME_DESC + INVALID_PATIENTIC_DESC
                        + VALID_START_TIME_FIRST + VALID_DURATION_FIRST,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPOINTMENT;

        String userInput = targetIndex.getOneBased() + PATIENTNAME_DESC_SECOND + PATIENTIC_DESC_SECOND
                + START_TIME_DESC_SECOND + DURATION_DESC_SECOND;

        EditAppointmentCommand.EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientName(VALID_PATIENT_NAME_SECOND).withPatientIc(VALID_PATIENT_IC_SECOND)
                .withStartTime(VALID_START_TIME_SECOND).withDuration(VALID_DURATION_SECOND)
                .build();

        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + PATIENTNAME_DESC_SECOND + PATIENTIC_DESC_SECOND;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientName(VALID_PATIENT_NAME_SECOND).withPatientIc(VALID_PATIENT_IC_SECOND)
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // patient name
        Index targetIndex = INDEX_THIRD_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + PATIENTNAME_DESC_FIRST;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientName(VALID_PATIENT_NAME_FIRST)
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // ic number
        userInput = targetIndex.getOneBased() + PATIENTIC_DESC_FIRST;
        descriptor = new EditAppointmentDescriptorBuilder().withPatientIc(VALID_PATIENT_IC_FIRST).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start time
        userInput = targetIndex.getOneBased() + START_TIME_DESC_FIRST;
        descriptor = new EditAppointmentDescriptorBuilder().withStartTime(VALID_START_TIME_FIRST).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = targetIndex.getOneBased() + DURATION_DESC_FIRST;
        descriptor = new EditAppointmentDescriptorBuilder().withDuration(VALID_DURATION_FIRST).build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + PATIENTNAME_DESC_FIRST + PATIENTIC_DESC_FIRST
                + PATIENTNAME_DESC_FIRST + PATIENTIC_DESC_SECOND + PATIENTNAME_DESC_SECOND + PATIENTIC_DESC_SECOND
                + START_TIME_DESC_FIRST + DURATION_DESC_SECOND;

        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withPatientName(VALID_PATIENT_NAME_SECOND).withPatientIc(VALID_PATIENT_IC_SECOND)
                .withStartTime(VALID_START_TIME_FIRST).withDuration(VALID_DURATION_SECOND)
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + INVALID_DURATION_DESC + DURATION_DESC_FIRST;
        EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
                .withDuration(VALID_DURATION_FIRST)
                .build();
        EditAppointmentCommand expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + START_TIME_DESC_SECOND + INVALID_PATIENTNAME_DESC + DURATION_DESC_FIRST
                + PATIENTIC_DESC_SECOND + PATIENTNAME_DESC_SECOND;
        descriptor = new EditAppointmentDescriptorBuilder().withStartTime(VALID_START_TIME_SECOND)
                .withDuration(VALID_DURATION_FIRST)
                .withPatientIc(VALID_PATIENT_IC_SECOND)
                .withPatientName(VALID_PATIENT_NAME_SECOND)
                .build();
        expectedCommand = new EditAppointmentCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
