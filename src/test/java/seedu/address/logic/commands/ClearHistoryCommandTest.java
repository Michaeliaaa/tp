package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalCliniCal;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearHistoryCommandTest {

    private Model model = new ModelManager(getTypicalCliniCal(), new UserPrefs());

    @Test
    void execute() {
        ClearHistoryCommand clearHistoryCommand = new ClearHistoryCommand();
        assertCommandSuccess(clearHistoryCommand, model, ClearHistoryCommand.MESSAGE_SUCCESS, model);
    }



}
