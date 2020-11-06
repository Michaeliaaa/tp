package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALLERGY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COLORTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ICNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
import seedu.address.model.visit.VisitHistory;

/**
 * Edits the details of an existing patient in the CliniCal application.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the patient identified "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive number) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ICNUMBER + "NRIC] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_SEX + "SEX] "
            + "[" + PREFIX_BLOODTYPE + "BLOODTYPE] "
            + "[" + PREFIX_ALLERGY + "ALLERGY] "
            + "[" + PREFIX_COLORTAG + "COLORTAG]\n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "NOTE : If you have edited patient name or patient NRIC, "
            + "please make sure to update the changes to any relevant appointments, using the 'editappt' command.\n\n"
            + "Edited patient:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the list of patients.";

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index of the patient in the filtered patient list to edit
     * @param editPatientDescriptor details to edit the patient with
     */
    public EditCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(index);
        requireNonNull(editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = createEditedPatient(patientToEdit, editPatientDescriptor);

        if (!patientToEdit.isSamePatient(editedPatient) && model.hasPatient(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.setPatient(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);
        model.commitCliniCal(String.format(Messages.MESSAGE_UNDONE_REDONE_INPUT, COMMAND_WORD, editedPatient));
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code patientToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient patientToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert patientToEdit != null : "Patient cannot be null.";

        Name updatedName = editPatientDescriptor.getName().orElse(patientToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(patientToEdit.getPhone());
        IcNumber updatedIcNumber = editPatientDescriptor.getIcNumber().orElse(patientToEdit.getIcNumber());
        VisitHistory updatedVisitHistory =
            editPatientDescriptor.getVisitHistory().orElse(patientToEdit.getVisitHistory());
        Address updatedAddress = editPatientDescriptor.getAddress().orElse(patientToEdit.getAddress());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(patientToEdit.getEmail());
        ProfilePicture updatedProfilePicture = editPatientDescriptor.getProfilePicture()
                .orElse(patientToEdit.getProfilePicture());
        Sex updatedSex = editPatientDescriptor.getSex().orElse(patientToEdit.getSex());
        BloodType updatedBloodtype = editPatientDescriptor.getBloodType().orElse(patientToEdit.getBloodType());
        Set<Allergy> updatedAllergies = editPatientDescriptor.getAllergies().orElse(patientToEdit.getAllergies());
        ColorTag updatedColorTag = editPatientDescriptor.getColorTag().orElse(patientToEdit.getColorTag());
        return new Patient(updatedName, updatedPhone, updatedIcNumber, updatedVisitHistory,
                updatedAddress, updatedEmail, updatedProfilePicture,
                updatedSex, updatedBloodtype, updatedAllergies, updatedColorTag);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPatientDescriptor.equals(e.editPatientDescriptor);
    }

    /**
     * Stores the details to edit the patient with. Each non-empty field value will replace the
     * corresponding field value of the patient.
     */
    public static class EditPatientDescriptor {
        private Name name;
        private Phone phone;
        private IcNumber icNumber;
        private VisitHistory visitHistory;
        private Address address;
        private Email email;
        private ProfilePicture profilePicture;
        private Sex sex;
        private BloodType bloodType;
        private Set<Allergy> allergies;
        private ColorTag colorTag;

        public EditPatientDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code allergies} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setIcNumber(toCopy.icNumber);
            setVisitHistory(toCopy.visitHistory);
            setAddress(toCopy.address);
            setEmail(toCopy.email);
            setProfilePicture(toCopy.profilePicture);
            setSex(toCopy.sex);
            setBloodType(toCopy.bloodType);
            setAllergies(toCopy.allergies);
            setColorTag(toCopy.colorTag);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, icNumber, address, email, sex, bloodType,
                    allergies, colorTag);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setIcNumber(IcNumber icNumber) {
            this.icNumber = icNumber;
        }

        public Optional<VisitHistory> getVisitHistory() {
            return Optional.ofNullable(visitHistory);
        }

        public void setVisitHistory(VisitHistory visitHistory) {
            this.visitHistory = visitHistory;
        }

        public Optional<IcNumber> getIcNumber() {
            return Optional.ofNullable(icNumber);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setProfilePicture(ProfilePicture profilePicture) {
            this.profilePicture = profilePicture;
        }

        public Optional<ProfilePicture> getProfilePicture() {
            return Optional.ofNullable(profilePicture);
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }

        public Optional<Sex> getSex() {
            return Optional.ofNullable(sex);
        }

        public void setBloodType(BloodType bloodType) {
            this.bloodType = bloodType;
        }

        public Optional<BloodType> getBloodType() {
            return Optional.ofNullable(bloodType);
        }

        /**
         * Sets {@code allergies} to this object's {@code allergies}.
         * A defensive copy of {@code allergies} is used internally.
         */
        public void setAllergies(Set<Allergy> allergies) {
            this.allergies = (allergies != null) ? new HashSet<>(allergies) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code allergies} is null.
         */
        public Optional<Set<Allergy>> getAllergies() {
            return (allergies != null) ? Optional.of(Collections.unmodifiableSet(allergies)) : Optional.empty();
        }

        public void setColorTag(ColorTag colorTag) {
            this.colorTag = colorTag;
        }

        public Optional<ColorTag> getColorTag() {
            return Optional.ofNullable(colorTag);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPatientDescriptor)) {
                return false;
            }

            // state check
            EditPatientDescriptor e = (EditPatientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getIcNumber().equals(e.getIcNumber())
                    && getAddress().equals(e.getAddress())
                    && getEmail().equals(e.getEmail())
                    && getProfilePicture().equals(e.getProfilePicture())
                    && getSex().equals(e.getSex())
                    && getBloodType().equals(e.getBloodType())
                    && getAllergies().equals(e.getAllergies())
                    && getColorTag().equals(e.getColorTag());
        }
    }
}
