package seedu.address.model.util;

import static seedu.address.model.appointment.AppointmentDateTime.DATE_TIME_FORMATTER;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.CliniCal;
import seedu.address.model.ReadOnlyCliniCal;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
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
 * Contains utility methods for populating {@code CliniCal} with sample data.
 */
public class SampleDataUtil {
    public static final VisitHistory VISIT_HISTORY1 =
        new VisitHistory(new ArrayList<Visit>()).addVisit(new Visit(LocalDate.of(2020, 10, 28),
            new Name("Alex Yeoh"), "Reflux esophagitis", "Synthroid", "No need to follow up"));
    public static final VisitHistory VISIT_HISTORY2 =
        new VisitHistory(new ArrayList<Visit>()).addVisit(new Visit(LocalDate.of(2020, 9, 3),
            new Name("Bernice Yu"), "COVID-19", "Remdesivir", "Quarantined for 14 days"));
    public static final VisitHistory EMPTY_VISIT_HISTORY3 = new VisitHistory(new ArrayList<Visit>());
    public static final VisitHistory EMPTY_VISIT_HISTORY4 = new VisitHistory(new ArrayList<Visit>());
    public static final VisitHistory EMPTY_VISIT_HISTORY5 = new VisitHistory(new ArrayList<Visit>());
    public static final VisitHistory EMPTY_VISIT_HISTORY6 = new VisitHistory(new ArrayList<Visit>());

    public static Patient[] getSamplePatients() {
        ProfilePicture profilePicture = new ProfilePicture("data/stock_picture.png");
        ColorTag placeholderColorTag = new ColorTag();

        return new Patient[] {
            new Patient(new Name("Alex Yeoh"), new Phone("87438807"), new IcNumber("S7908430A"), VISIT_HISTORY1,
                    new Address("Blk 30 Geylang Street 29, #06-40"), new Email("alexyeoh@example.com"),
                    profilePicture, new Sex("M"), new BloodType("A+"),
                    getAllergySet("penicillin"), placeholderColorTag),
            new Patient(new Name("Bernice Yu"), new Phone("99272758"), new IcNumber("G4329854B"), VISIT_HISTORY2,
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Email("berniceyu@example.com"),
                    profilePicture, new Sex("F"), new BloodType("B+"),
                    getAllergySet("sulfa", "penicillin"), new ColorTag("maroon")),
            new Patient(new Name("Charlotte Oliveiro"), new Phone("93210283"), new IcNumber("S7856411C"),
                EMPTY_VISIT_HISTORY4, new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    new Email("charlotte@example.com"), profilePicture, new Sex("F"), new BloodType("O+"),
                    getAllergySet("sulfa"), placeholderColorTag),
            new Patient(new Name("David Li"), new Phone("91031282"), new IcNumber("F1155948D"), EMPTY_VISIT_HISTORY3,
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Email("lidavid@example.com"),
                    profilePicture, new Sex("M"), new BloodType("AB+"),
                    getAllergySet("aspirin"), placeholderColorTag),
            new Patient(new Name("Irfan Ibrahim"), new Phone("92492021"), new IcNumber("S1568938I"),
                EMPTY_VISIT_HISTORY5, new Address("Blk 47 Tampines Street 20, #17-35"), new Email("irfan@example.com"),
                    profilePicture, new Sex("M"), new BloodType("A+"),
                    getAllergySet("aspirin", "sulfa"), placeholderColorTag),
            new Patient(new Name("Roy Balakrishnan"), new Phone("92624417"), new IcNumber("T9584423R"),
                EMPTY_VISIT_HISTORY6, new Address("Blk 45 Aljunied Street 85, #11-31"), new Email("royb@example.com"),
                    profilePicture, new Sex("M"), new BloodType("O+"),
                    getAllergySet("penicillin"), placeholderColorTag),
        };
    }

    public static Appointment[] getSampleAppointments() {
        String start1 = LocalDateTime.now().plusMinutes(10).format(DATE_TIME_FORMATTER);
        String end1 = LocalDateTime.now().plusMinutes(70).format(DATE_TIME_FORMATTER);
        String start2 = LocalDateTime.now().plusDays(1).minusMinutes(90).format(DATE_TIME_FORMATTER);
        String end2 = LocalDateTime.now().plusDays(1).minusMinutes(30).format(DATE_TIME_FORMATTER);
        String start3 = LocalDateTime.now().plusDays(1).plusMinutes(10).format(DATE_TIME_FORMATTER);
        String end3 = LocalDateTime.now().plusDays(1).plusMinutes(70).format(DATE_TIME_FORMATTER);
        String start4 = LocalDateTime.now().plusDays(2).plusMinutes(20).format(DATE_TIME_FORMATTER);
        String end4 = LocalDateTime.now().plusDays(2).plusMinutes(140).format(DATE_TIME_FORMATTER);
        String start5 = LocalDateTime.now().plusDays(2).plusMinutes(160).format(DATE_TIME_FORMATTER);
        String end5 = LocalDateTime.now().plusDays(2).plusMinutes(220).format(DATE_TIME_FORMATTER);
        return new Appointment[] {
            new Appointment(new Name("Alex Yeoh"), new IcNumber("G4329854B"),
                    new AppointmentDateTime(start1), new AppointmentDateTime(end1)),
            new Appointment(new Name("Bernice Yu"), new IcNumber("S7908430A"),
                    new AppointmentDateTime(start2), new AppointmentDateTime(end2)),
            new Appointment(new Name("Charlotte Oliveiro"), new IcNumber("S7856411C"),
                    new AppointmentDateTime(start3), new AppointmentDateTime(end3)),
            new Appointment(new Name("David Li"), new IcNumber("F1155948D"),
                    new AppointmentDateTime(start4), new AppointmentDateTime(end4)),
            new Appointment(new Name("Irfan Ibrahim"), new IcNumber("S1568938I"),
                    new AppointmentDateTime(start5), new AppointmentDateTime(end5))
        };
    }

    public static ReadOnlyCliniCal getSampleCliniCal() {
        CliniCal sampleAb = new CliniCal();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAb.addAppointment(sampleAppointment);
        }
        return sampleAb;
    }

    /**
     * Returns an allergy set containing the list of strings given.
     */
    public static Set<Allergy> getAllergySet(String... strings) {
        return Arrays.stream(strings)
                .map(Allergy::new)
                .collect(Collectors.toSet());
    }

}
