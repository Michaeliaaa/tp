package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.Set;

import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Sex;

/**
 * Handles the conversion of {@code Patient} parameters into appropriate strings for display in {@code ProfileWindow}.
 */
public class PatientProfileUtil {

    /**
     * Converts the {@code Name} object into a {@code String} instance.
     *
     * @param patientName Name of patient
     * @return a {@code String} object converted from the {@code Name} object
     */
    public static String convertNameToString(Name patientName) {
        requireNonNull(patientName, "Patient's name cannot be null.");
        return patientName.fullName;
    }

    /**
     * Converts the {@code Allergy} set into a {@code String} instance.
     *
     * @param allergiesSet Allergies belonging to the patient
     * @return a {@code String} object converted from the {@code Allergy} set
     */
    public static String convertAllergiesToString(Set<Allergy> allergiesSet) {
        requireNonNull(allergiesSet, "Patient's allergies set cannot be null.");

        StringBuilder stringBuilder = new StringBuilder();
        int sizeOfSet = allergiesSet.size();

        if (sizeOfSet <= 0) {
            return "-";
        } else {
            for (Allergy allergy: allergiesSet) {
                stringBuilder.append(allergy.allergyName);
                stringBuilder.append("; ");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Converts the {@code Phone} object into a {@code String} instance.
     *
     * @param patientPhone Phone number of patient
     * @return a {@code String} object converted from the {@code Phone} object
     */
    public static String convertPhoneToString(Phone patientPhone) {
        requireNonNull(patientPhone, "Patient's phone cannot be null.");
        return patientPhone.value;
    }

    /**
     * Converts the {@code Email} object into a {@code String} instance.
     *
     * @param patientEmail Email of patient
     * @return a {@code String} object converted from the {@code Email} object
     */
    public static String convertEmailToString(Email patientEmail) {
        requireNonNull(patientEmail, "Patient's email cannot be null.");
        return patientEmail.value;
    }

    /**
     * Converts the {@code Address} object into a {@code String} instance.
     *
     * @param patientAddress Address of patient
     * @return a {@code String} object converted from the {@code Address} object
     */
    public static String convertAddressToString(Address patientAddress) {
        requireNonNull(patientAddress, "Patient's address cannot be null.");
        return patientAddress.value;
    }

    /**
     * Converts the {@code IcNumber} object into a {@code String} instance.
     *
     * @param icNumber IC number of patient
     * @return a {@code String} object converted from the {@code IcNumber} object
     */
    public static String convertIcToString(IcNumber icNumber) {
        requireNonNull(icNumber, "Patient's IC number cannot be null.");
        return icNumber.value;
    }

    /**
     * Converts the {@code BloodType} object into a {@code String} instance.
     *
     * @param bloodType Blood type of patient
     * @return a {@code String} object converted from the {@code BloodType} object
     */
    public static String convertBloodTypeToString(BloodType bloodType) {
        requireNonNull(bloodType, "Patient's blood type cannot be null.");
        return bloodType.type;
    }

    /**
     * Converts the {@code Sex} object into a {@code String} instance.
     *
     * @param sex Sex of patient
     * @return a {@code String} object converted from the {@code Sex} object
     */
    public static String convertSexToString(Sex sex) {
        requireNonNull(sex, "Patient's sex cannot be null.");
        return sex.value;
    }
}
