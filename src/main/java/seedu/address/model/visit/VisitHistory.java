package seedu.address.model.visit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.patient.Name;

//@@author SQwQ-reused
//Reused from
//https://github.com/AY1920S1-CS2103T-F12-2/main/blob/master/src/main/java/unrealunity/visit/model/person/VisitList.java
//with modifications

/**
 * Represents a patient's visit history in the CliniCal application.
 */
public class VisitHistory {

    public static final String MESSAGE_CONSTRAINTS = "Visit history should take any valid Visit";

    private final ArrayList<Visit> visits;

    /**
     * Constructs a {@code VisitHistory}.
     *
     * @param visits A list of valid visits.
     */
    public VisitHistory(ArrayList<Visit> visits) {
        this.visits = visits;
    }

    public ObservableList<Visit> getObservableVisits() {
        return FXCollections.observableArrayList(this.visits);
    }

    /**
     * Adds a new visit to the visit history.
     */
    public VisitHistory addVisit(Visit toAdd) {
        this.visits.add(toAdd);
        Collections.sort(this.visits);
        Collections.reverse((this.visits));
        return this;
    }

    /**
     * Edits a visit(specified by index) from the visit history.
     */
    public VisitHistory editVisit(int idx, Visit toEdit) {
        this.visits.set(idx - 1, toEdit);
        Collections.sort(this.visits);
        Collections.reverse((this.visits));
        return this;
    }

    /**
     * Deletes a visit(specified by index) from the visit history.
     */
    public VisitHistory deleteVisit(int idx) throws IndexOutOfBoundsException {
        this.visits.remove(idx - 1);
        return this;
    }

    public Visit getVisitByIndex(int idx) throws IndexOutOfBoundsException {
        return visits.get(idx - 1);
    }

    public ArrayList<Visit> getVisits() {
        return this.visits;
    }

    /**
     * Deep-copies an existing visit history to facilitate undo/redo functions.
     * @return a new VisitHistory with no reference to the existingVisitHistory
     */
    public static VisitHistory deepCopyVisitHistory(VisitHistory existingVisitHistory) {
        ArrayList<Visit> newVisitArrayList = new ArrayList<>();
        for (Visit v : existingVisitHistory.getVisits()) {
            Visit newVisit = new Visit(LocalDate.parse(v.getVisitDate().toString()),
                new Name(v.getPatientName().toString()), v.getDiagnosis(), v.getPrescription(), v.getComment());
            newVisitArrayList.add(newVisit);
        }
        return new VisitHistory(newVisitArrayList);
    }

    /**
     * Returns true if both visit history have the same list of visits.
     * This defines a stronger notion of equality between two visit history.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof VisitHistory)) {
            return false;
        }

        VisitHistory otherVisitHistory = (VisitHistory) other;
        return otherVisitHistory.getVisits().equals(getVisits());
    }

    @Override
    public int hashCode() {
        return Objects.hash(visits);
    }

    @Override
    public String toString() {
        if (visits.isEmpty()) {
            return "This patient has no prior visits.";
        }
        final StringBuilder builder = new StringBuilder();
        builder.append("Most recent visit date: ");
        builder.append(visits.get(0).toString());
        return builder.toString();
    }
}
