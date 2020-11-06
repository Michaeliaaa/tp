package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_PICTURE_DOES_NOT_EXIST;
import static seedu.address.commons.core.Messages.MESSAGE_PICTURE_HAS_WRONG_EXTENSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_PATH;

import java.io.File;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddProfilePictureCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddProfilePicture object
 */
public class AddProfilePictureCommandParser implements Parser<AddProfilePictureCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddProfilePicture
     * and returns an AddProfilePicture object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddProfilePictureCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_FILE_PATH);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   AddProfilePictureCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultiMap.getValue(PREFIX_FILE_PATH).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   AddProfilePictureCommand.MESSAGE_USAGE));
        }

        String filePath = argMultiMap.getValue(PREFIX_FILE_PATH).get();

        String extension = filePath.substring(filePath.lastIndexOf('.') + 1).toLowerCase();
        if (!extension.equals("jpg") && !extension.equals("png")) {
            throw new ParseException(MESSAGE_PICTURE_HAS_WRONG_EXTENSION);
        }

        File profilePicture = new File(filePath);

        if (!profilePicture.exists()) {
            throw new ParseException(String.format(MESSAGE_PICTURE_DOES_NOT_EXIST,
                                                   AddProfilePictureCommand.MESSAGE_USAGE));
        }

        return new AddProfilePictureCommand(profilePicture, index);
    }
}
