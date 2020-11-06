package seedu.address.model.patient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.allergy.Allergy;
import seedu.address.model.tag.ColorTag;
import seedu.address.model.visit.VisitHistory;

/**
 * Represents a Patient in the CliniCal application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    // Mandatory fields
    private final Name name;
    private final Phone phone;
    private final IcNumber icNumber;

    // Optional fields
    private final Address address;
    private final Email email;
    private final ProfilePicture profilePicture;
    private final Sex sex;
    private final BloodType bloodType;
    private final Set<Allergy> allergies = new HashSet<>();
    private final ColorTag colorTag;

    // List of past visits
    private final VisitHistory visitHistory;

    /**
     * Name, phone, and IC number must be present and not null.
     */
    public Patient(Name name, Phone phone, IcNumber icNumber, VisitHistory visitHistory, Address address, Email email,
                   ProfilePicture profilePicture, Sex sex, BloodType bloodType,
                   Set<Allergy> allergies, ColorTag colorTag) {
        requireAllNonNull(name, phone, icNumber);
        this.name = name;
        this.phone = phone;
        this.icNumber = icNumber;
        this.address = address;
        this.email = email;
        this.profilePicture = profilePicture;
        this.sex = sex;
        this.bloodType = bloodType;
        this.allergies.addAll(allergies);
        this.colorTag = colorTag;
        this.visitHistory = visitHistory;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public IcNumber getIcNumber() {
        return icNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Email getEmail() {
        return email;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public Sex getSex() {
        return sex;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    /**
     * Returns an immutable allergy set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Allergy> getAllergies() {
        return Collections.unmodifiableSet(allergies);
    }

    public ColorTag getColorTag() {
        return colorTag;
    }

    public VisitHistory getVisitHistory() {
        return visitHistory;
    }

    /**
     * Returns true if both patients have the same NRIC.
     * This defines a weaker notion of equality between two patients.
     */
    public boolean isSamePatient(Patient otherPatient) {
        if (otherPatient == this) {
            return true;
        }

        return otherPatient != null
                && otherPatient.getIcNumber().equals(getIcNumber());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two patients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPatient = (Patient) other;
        return otherPatient.getName().equals(getName())
                && otherPatient.getPhone().equals(getPhone())
                && otherPatient.getIcNumber().equals(getIcNumber())
                && otherPatient.getAddress().equals(getAddress())
                && otherPatient.getEmail().equals(getEmail())
                && otherPatient.getProfilePicture().equals(getProfilePicture())
                && otherPatient.getSex().equals(getSex())
                && otherPatient.getBloodType().equals(getBloodType())
                && otherPatient.getAllergies().equals(getAllergies())
                && otherPatient.getColorTag().equals(getColorTag())
                && otherPatient.getVisitHistory().equals(getVisitHistory());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, icNumber, address, email, profilePicture, sex, bloodType, allergies, colorTag);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName() + "\n")
                .append("Phone: ")
                .append(getPhone() + "\n")
                .append("NRIC: ")
                .append(getIcNumber() + "\n")
                .append("Address: ")
                .append(getAddress() + "\n")
                .append("Email: ")
                .append(getEmail() + "\n")
                .append("Sex: ")
                .append(getSex() + "\n")
                .append("Blood Type: ")
                .append(getBloodType() + "\n")
                .append("Color Tag: ")
                .append(getColorTag() + "\n")
                .append("Allergies:  ");
        getAllergies().forEach(builder::append);
        builder.append(", ");
        return builder.toString();
    }

}
