package seedu.address.logic.parser.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_PATIENTIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_PATIENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APP_STARTTIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointment.EditAppointmentCommand;
import seedu.address.logic.commands.appointment.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AppointmentEditCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AppointmentEditCommand
     * and returns an AppointmentEditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_APP_PATIENTNAME, PREFIX_APP_PATIENTIC,
                        PREFIX_APP_STARTTIME, PREFIX_APP_DURATION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_APP_PATIENTNAME).isPresent()) {
            editAppointmentDescriptor.setPatientName(ParserUtil.parseName(
                    argMultimap.getValue(PREFIX_APP_PATIENTNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_APP_PATIENTIC).isPresent()) {
            editAppointmentDescriptor.setPatientIc(ParserUtil.parseIcNumber(
                    argMultimap.getValue(PREFIX_APP_PATIENTIC).get()));
        }
        if (argMultimap.getValue(PREFIX_APP_STARTTIME).isPresent()) {
            editAppointmentDescriptor.setStartTime(ParserUtil.parseDateTime(
                    argMultimap.getValue(PREFIX_APP_STARTTIME).get()));
        }
        if (argMultimap.getValue(PREFIX_APP_DURATION).isPresent()) {
            editAppointmentDescriptor.setDuration(ParserUtil.parseDuration(
                    argMultimap.getValue(PREFIX_APP_DURATION).get()));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }

}
