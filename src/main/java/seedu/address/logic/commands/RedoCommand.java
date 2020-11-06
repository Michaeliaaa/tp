package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Redoes the previously undone command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_SUCCESS = "Redo-ed the command:\n\n%1$s";

    public static final String MESSAGE_FAILURE = "No more commands to redo";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // Defensive programming
        if (!model.canRedoCliniCal()) {
            throw new CommandException(MESSAGE_FAILURE);
        }
        model.redoCliniCal();
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getRedoCommand()));
    }
}
