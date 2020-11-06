package seedu.address.logic.parser.appointment;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DURATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.appointment.AddAppointmentCommand;

public class AddAppointmentCommandParserTest {

    private AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);
        // missing start time
        assertParseFailure(parser, "1 d/60", expectedMessage);
        // missing duration
        assertParseFailure(parser, "1 st/12/12/2012 12:00", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAppointmentCommand.MESSAGE_USAGE);
        // invalid start time
        assertParseFailure(parser, "1" + INVALID_START_TIME_DESC, expectedMessage);
        // invalid duration
        assertParseFailure(parser, "1" + INVALID_DURATION_DESC, expectedMessage);
    }
}
