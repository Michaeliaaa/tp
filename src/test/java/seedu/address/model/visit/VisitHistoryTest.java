package seedu.address.model.visit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalVisits;

public class VisitHistoryTest {
    @Test
    public void equals() {
        VisitHistory visitHistory = TypicalVisits.getTypicalVisitHistory1();
        // same object -> returns true
        assertTrue(visitHistory.equals(visitHistory));

        // same values -> returns true
        VisitHistory visitHistoryCopy = new VisitHistory(visitHistory.getVisits());
        assertTrue(visitHistory.equals(visitHistoryCopy));

        // different types -> returns false
        assertFalse(visitHistory.equals(1));

        // null -> returns false
        assertFalse(visitHistory.equals(null));

        // different visitList -> returns false
        VisitHistory otherVisitHistory = TypicalVisits.getTypicalVisitHistory2();
        assertFalse(visitHistory.equals(otherVisitHistory));
    }
}