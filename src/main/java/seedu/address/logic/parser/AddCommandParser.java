package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLORTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ICNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.model.patient.Sex;
import seedu.address.model.tag.ColorTag;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_ICNUMBER,
                        PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_SEX, PREFIX_BLOODTYPE, PREFIX_ALLERGY, PREFIX_COLORTAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_ICNUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // mandatory fields
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        IcNumber icNumber = ParserUtil.parseIcNumber(argMultimap.getValue(PREFIX_ICNUMBER).get());
        VisitHistory visitHistory = new VisitHistory(new ArrayList<Visit>());

        assert name != null : "Patient should be given a name.";
        assert phone != null : "Patient should be given a phone number.";
        assert icNumber != null : "Patient should be given an IC number.";
        // optional fields
        Address address = new Address();
        Email email = new Email();
        Sex sex = new Sex();
        BloodType bloodType = new BloodType();

        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }

        ProfilePicture profilePicture = new ProfilePicture("data/stock_picture.png");

        if (argMultimap.getValue(PREFIX_SEX).isPresent()) {
            sex = ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get());
        }

        if (argMultimap.getValue(PREFIX_BLOODTYPE).isPresent()) {
            bloodType = ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOODTYPE).get());
        }

        Set<Allergy> allergyList = ParserUtil.parseAllergies(argMultimap.getAllValues(PREFIX_ALLERGY));
        ColorTag colorTag = ParserUtil.parseColorTag(argMultimap.getValue(PREFIX_COLORTAG).orElse(""));

        Patient patient = new Patient(name, phone, icNumber, visitHistory, address, email, profilePicture,
                sex, bloodType, allergyList, colorTag);

        return new AddCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
