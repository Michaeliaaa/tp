package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VISIT_DATE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddVisitCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddVisitCommandParser object
 */
public class AddVisitCommandParser implements Parser<AddVisitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddVisitCommandParser}
     * and returns a {@code AddVisitCommandParser} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddVisitCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index patientIndex;
        String visitDate;

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_VISIT_DATE);

        try {
            patientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            visitDate = ParserUtil.parseVisit(argMultimap.getValue(PREFIX_VISIT_DATE)
                        .orElse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        } catch (IllegalValueException exception) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                                     AddVisitCommand.MESSAGE_USAGE), exception);
        }
        return new AddVisitCommand(visitDate, patientIndex);
    }
}
