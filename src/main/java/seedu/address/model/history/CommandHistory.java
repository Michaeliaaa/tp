package seedu.address.model.history;

import java.util.Stack;

/**
 * Represents command history in the address book.
 */
public class CommandHistory {
    public static final String STRING = "Here is your command history:%s";
    private static Stack<String> commandHistory = new Stack<>();
    private static int stateKey = 0;

    /**
     * Adds command to command history.
     *
     * @param command
     */
    public static void addUsedCommand(String command) {
        commandHistory.push(command);
        stateKey = 0;
    }

    /**
     * Returns true if command history have past commands.
     */
    public static boolean hasCommand() {
        return commandHistory.size() != 1;
    }

    /**
     * Returns true if command history is empty.
     */
    public static boolean isEmpty() {
        return commandHistory.size() == 0;
    }

    /**
     * Prints a list of past used commands in the command history.
     *
     * @return history of commands
     */
    public static String getCommandHistory() {
        String history = "";
        for (int i = commandHistory.size() - 1; i > 0; i--) {
            history += ("\n -\t");
            history += (commandHistory.get(i - 1));
        }
        return String.format(STRING, history);
    }

    /**
     * Clears command history.
     */
    public static void clearHistory() {
        commandHistory.clear();
    }

    /**
     * Peeks at the next command in the command history.
     *
     * @return next command in the command box
     */
    public static String peekNext() {
        if (commandHistory.empty()) {
            return "";
        } else if (stateKey == commandHistory.size()) {
            return commandHistory.get(0);
        } else {
            stateKey++;
            int indexNext = commandHistory.size() - stateKey;
            return commandHistory.get(indexNext);
        }
    }

    /**
     *
     * Peeks at the previous command in the command history.
     *
     * @return previous command in the command box
     */
    public static String peekPrev() {
        if (commandHistory.empty() || stateKey == 0) {
            return "";
        } else if (stateKey == 1) {
            stateKey--;
            return "";
        } else {
            stateKey--;
            int indexPrev = commandHistory.size() - stateKey;
            return commandHistory.get(indexPrev);
        }
    }
}
