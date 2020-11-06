package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.visit.Visit;
import seedu.address.testutil.TypicalPatients;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false, false, false,
                false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different idx value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null)));

        // different idx value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null, null, null,
                                                   1, 1)));

        // different idx value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", null, null)));

        // different date value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", "true", 1)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different idx value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", null, 1).hashCode());

        // different date value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", "false", 1).hashCode());
    }

    @Test
    public void getterMethods() {
        // Test for all false parameters
        CommandResult firstCommand = new CommandResult("feedback", false, false,
                                        false, false, false, false);

        assertEquals(firstCommand.isDisplayProfile(), false);
        assertEquals(firstCommand.isAddVisit(), false);
        assertEquals(firstCommand.isDisplayVisitHistory(), false);
        assertEquals(firstCommand.isEditVisit(), false);
        assertEquals(firstCommand.isExit(), false);
        assertEquals(firstCommand.isShowHelp(), false);

        // Test for display profile parameter
        CommandResult secondCommand = new CommandResult("feedback", false, false,
                false, false, true, false);

        assertEquals(secondCommand.isDisplayProfile(), true);
        assertEquals(secondCommand.isAddVisit(), false);
        assertEquals(secondCommand.isDisplayVisitHistory(), false);
        assertEquals(secondCommand.isEditVisit(), false);
        assertEquals(secondCommand.isExit(), false);
        assertEquals(secondCommand.isShowHelp(), false);
        assertNotEquals(secondCommand.isDisplayProfile(), false);

        // Test for valid visit date for patient with index 2
        CommandResult thirdCommand = new CommandResult("feedback", "10/11/2020", 2);

        assertNotEquals(thirdCommand.getPatientProfile(), TypicalPatients.ALICE);
        assertNotEquals(thirdCommand.getFeedbackToUser(), "test");
        assertNotEquals(thirdCommand.getVisitIndex(), 10);
        assertNotEquals(thirdCommand.getPatientIndex(), 5);
        assertNotEquals(thirdCommand.getVisitDate(), "9/11/2020");
        assertNotEquals(thirdCommand.getPreviousVisit(), new Visit("10/11/2020"));
        assertEquals(thirdCommand.getPatientIndex(), 2);
        assertEquals(thirdCommand.getVisitDate(), "10/11/2020");
    }

    @Test
    public void showHelp() {
        CommandResult commandResult = new CommandResult("help");
        assertEquals(commandResult.isShowHelp(), false);
    }

    @Test
    public void exit() {
        CommandResult commandResult = new CommandResult("exit");
        assertEquals(commandResult.isShowHelp(), false);
    }
}
