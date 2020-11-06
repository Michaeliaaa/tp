package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditVisitCommand;

class EditVisitCommandParserTest {

    private EditVisitCommandParser parser = new EditVisitCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteVisitCommand() {
        String visitDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Valid visit index
        String userinput1 =
            INDEX_FIRST_PATIENT.getOneBased() + " " + PREFIX_VISIT_INDEX + "1 " + PREFIX_VISIT_DATE + visitDate;
        assertParseSuccess(parser, userinput1 , new EditVisitCommand(INDEX_FIRST_PATIENT, 1, visitDate));

        // No visit index given
        String userinput2 = INDEX_FIRST_PATIENT.getOneBased() + " " + PREFIX_VISIT_INDEX;
        assertParseSuccess(parser, userinput2,
            new EditVisitCommand(INDEX_FIRST_PATIENT, -1, EditVisitCommand.EMPTY_VISIT_DATE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userinput3 = INDEX_FIRST_PATIENT.getOneBased() + " " + PREFIX_VISIT_INDEX + "aa";
        assertParseFailure(parser, userinput3 , String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EditVisitCommand.MESSAGE_USAGE));
    }
}
