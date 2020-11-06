package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PROFILE_PICTURE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_PICTURE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddProfilePictureCommand;

public class AddProfilePictureCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProfilePictureCommand.MESSAGE_USAGE);
    private static final String INVALID_FILEPATH_DESC = "Test string";
    private AddProfilePictureCommandParser parser = new AddProfilePictureCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PROFILE_PICTURE_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "2", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-7" + VALID_PROFILE_PICTURE_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_PROFILE_PICTURE_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name and filepath
        assertParseFailure(parser, "2" + INVALID_FILEPATH_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPicture_failure() {
        // invalid photo with blank filepath
        assertParseFailure(parser, "1" + INVALID_PROFILE_PICTURE_AMY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPictureFilePath_failure() {
        // invalid photo with blank filepath
        String invalidFilePath = "f/data/nonexistentfile.png";
        assertParseFailure(parser, "1" + invalidFilePath, MESSAGE_INVALID_FORMAT);
    }
}

