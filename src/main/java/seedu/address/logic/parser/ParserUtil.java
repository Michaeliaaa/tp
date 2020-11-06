package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Sex;
import seedu.address.model.tag.ColorTag;
import seedu.address.model.visit.Visit;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_EMPTY_VISIT_INDEX = "";
    public static final int MESSAGE_EMPTY_VISIT_INDICATOR = -1;
    public static final String MESSAGE_NEGATIVE_INVALID_DURATION = "The given duration should be a positive number.";
    public static final String MESSAGE_INVALID_DURATION = "The given duration is invalid. "
            + "It should be a positive integer, not exceeding 2,147,483,647.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String icNumber} into an {@code IcNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code icNumber} is invalid.
     */
    public static IcNumber parseIcNumber(String icNumber) throws ParseException {
        requireNonNull(icNumber);
        String trimmedIcNumber = icNumber.trim();
        if (!IcNumber.isValidIcNumber(trimmedIcNumber)) {
            throw new ParseException(IcNumber.MESSAGE_CONSTRAINTS);
        }
        return new IcNumber(trimmedIcNumber);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String bloodType} into an {@code BloodType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code bloodType} is invalid.
     */
    public static BloodType parseBloodType(String bloodType) throws ParseException {
        requireNonNull(bloodType);
        String trimmedBloodType = bloodType.trim();
        if (!BloodType.isValidBloodType(trimmedBloodType)) {
            throw new ParseException(BloodType.MESSAGE_CONSTRAINTS);
        }
        return new BloodType(trimmedBloodType);
    }

    /**
     * Parses a {@code String sex} into an {@code Sex}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code sex} is invalid.
     */
    public static Sex parseSex(String sex) throws ParseException {
        requireNonNull(sex);
        String trimmedSex = sex.trim();
        if (!Sex.isValidSex(trimmedSex)) {
            throw new ParseException(Sex.MESSAGE_CONSTRAINTS);
        }
        return new Sex(trimmedSex);
    }

    /**
     * Parses a {@code String allergy} into a {@code Allergy}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code allergy} is invalid.
     */
    public static Allergy parseAllergy(String allergy) throws ParseException {
        requireNonNull(allergy);
        String trimmedAllergy = allergy.trim();
        if (!Allergy.isValidAllergyName(trimmedAllergy)) {
            throw new ParseException(Allergy.MESSAGE_CONSTRAINTS);
        }
        return new Allergy(trimmedAllergy);
    }

    /**
     * Parses {@code Collection<String> allergies} into a {@code Set<Allergy>}.
     */
    public static Set<Allergy> parseAllergies(Collection<String> allergies) throws ParseException {
        requireNonNull(allergies);
        final Set<Allergy> allergySet = new HashSet<>();
        for (String allergyName : allergies) {
            allergySet.add(parseAllergy(allergyName));
        }
        return allergySet;
    }

    /**
     * Parses a {@code String colorTag} into a {@code ColorTag}.
     *
     * @throws ParseException if the given {@code ColorTag} is invalid.
     */
    public static ColorTag parseColorTag(String colorTag) throws ParseException {
        requireNonNull(colorTag);
        String trimmedColorTag = colorTag.trim();

        if (trimmedColorTag.equals("")) {
            return new ColorTag();
        }

        if (!ColorTag.isValidColorName(colorTag)) {
            throw new ParseException(ColorTag.MESSAGE_CONSTRAINTS);
        }
        return new ColorTag(trimmedColorTag);
    }

    /**
     * Validates {@code index} as a valid number.
     *
     * @throws ParseException if the given {@code index} is invalid.
     */
    public static int parseVisitIndex(String index) throws ParseException {
        if (!index.equals(MESSAGE_EMPTY_VISIT_INDEX)) {
            if (Integer.parseInt(index) < 1) {
                throw new ParseException(Messages.MESSAGE_INVALID_VISIT_INDEX);
            } else {
                return Integer.parseInt(index);
            }
        } else {
            return MESSAGE_EMPTY_VISIT_INDICATOR;
        }
    }

    /**
     * Validates {@code date} and  returns a  trimmed {@code trimmedDate}.
     *
     * @throws ParseException if the given {@code date} is invalid.
     */
    public static String parseVisit(String date) throws ParseException {
        requireNonNull(date);
        String trimmedDate = date.trim();
        if (Visit.isValidVisitDate(trimmedDate)) {
            // Do nothing.
        } else {
            throw new ParseException(Visit.MESSAGE_CONSTRAINTS);
        }
        return trimmedDate;
    }

    /**
    * Parses a {@code String dateTime} into an {@code AppointmentDateTime}.
    * Leading and trailing whitespaces will be trimmed.
    *
    * @throws ParseException if the given {@code dateTime} is invalid.
    */
    public static AppointmentDateTime parseDateTime(String dateTime) throws ParseException {
        requireNonNull(dateTime);
        String trimmedDateTime = dateTime.trim();
        if (!AppointmentDateTime.isValidUpcomingDateTime(trimmedDateTime)) {
            throw new ParseException(AppointmentDateTime.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentDateTime(trimmedDateTime);
    }

    /**
     * Parses a {@code String dateTimeStr, durationStr} into an {@code AppointmentDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTimeStr, durationStr} is invalid.
     */
    public static AppointmentDateTime parseDurationWithStart(String dateTimeStr, String durationStr)
            throws ParseException {
        requireNonNull(dateTimeStr, durationStr);
        String trimmedDateTime = dateTimeStr.trim();
        String trimmedDuration = durationStr.trim();
        int duration;

        if (!AppointmentDateTime.isValidUpcomingDateTime(trimmedDateTime)) {
            throw new ParseException(AppointmentDateTime.MESSAGE_CONSTRAINTS);
        }
        try {
            duration = Integer.parseInt(trimmedDuration);
            if (duration <= 0) {
                throw new ParseException(MESSAGE_NEGATIVE_INVALID_DURATION);
            }
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_DURATION);
        }

        return new AppointmentDateTime(trimmedDateTime, duration);
    }

    /**
     * Parses a {@code String durationStr} into an {@code Integer}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code durationStr} is invalid.
     */
    public static int parseDuration(String durationStr) throws ParseException {
        requireNonNull(durationStr);
        String trimmedDuration = durationStr.trim();
        int duration;

        try {
            duration = Integer.parseInt(trimmedDuration);
            if (duration <= 0) {
                throw new ParseException(MESSAGE_NEGATIVE_INVALID_DURATION);
            }
            if (duration > Integer.MAX_VALUE) {
                throw new ParseException(MESSAGE_INVALID_DURATION);
            }
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_DURATION);
        }

        return duration;
    }
}
