package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_INDEX;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.EditVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditVisitCommandParser implements Parser<EditVisitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code EditVisitCommandParser}
     * and returns a {@code EditVisitCommandParser} object for execution.
     * @throws IllegalValueException if the user input does not conform the expected format
     * @throws NumberFormatException if the user input does not conform the expected format
     */
    public EditVisitCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_VISIT_INDEX, PREFIX_VISIT_DATE);

        Index patientIndex;
        int visitIndex;
        String visitDate;

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            String input = argMultimap.getValue(PREFIX_VISIT_INDEX).orElse(ParserUtil.MESSAGE_EMPTY_VISIT_INDEX);
            visitIndex = ParserUtil.parseVisitIndex(input);
            visitDate =
                ParserUtil.parseVisit(argMultimap.getValue(PREFIX_VISIT_DATE)
                    .orElse(EditVisitCommand.EMPTY_VISIT_DATE));
        } catch (IllegalValueException | NumberFormatException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                     EditVisitCommand.MESSAGE_USAGE), ive);
        }
        return new EditVisitCommand(patientIndex, visitIndex, visitDate);
    }
}

