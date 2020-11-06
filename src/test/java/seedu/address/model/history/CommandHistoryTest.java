package seedu.address.model.history;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    @Test
    void clearHistoryTest() {
        CommandHistory.clearHistory();
        assertEquals(true, CommandHistory.isEmpty());
    }

    @Test
    void isEmptyTest() {
        CommandHistory.clearHistory();
        assertEquals(true, CommandHistory.isEmpty());
    }

    @Test
    void hasCommandTest1() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("history");
        assertEquals(false, CommandHistory.hasCommand());
        assertEquals(false, CommandHistory.isEmpty());
    }

    @Test
    void hasCommandTest2() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("list");
        CommandHistory.addUsedCommand("history");
        assertEquals(true, CommandHistory.hasCommand());
        assertEquals(false, CommandHistory.isEmpty());
    }

    @Test
    void getCommandHistoryTest1() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("history");
        assertEquals("Here is your command history:", CommandHistory.getCommandHistory());
    }

    @Test
    void getCommandHistoryTest2() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("list");
        CommandHistory.addUsedCommand("clear");
        CommandHistory.addUsedCommand("delete");
        CommandHistory.addUsedCommand("history");
        assertEquals("Here is your command history:\n" + " -\tdelete\n"
                + " -\tclear\n" + " -\tlist", CommandHistory.getCommandHistory());
    }

    @Test
    void peekNextTest1() {
        CommandHistory.clearHistory();
        assertEquals("", CommandHistory.peekNext());
    }

    @Test
    void peekNextTest2() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("list");
        CommandHistory.addUsedCommand("history");
        CommandHistory.addUsedCommand("clear");
        assertEquals("clear", CommandHistory.peekNext());
        assertEquals("history", CommandHistory.peekNext());
        assertEquals("list", CommandHistory.peekNext());
    }

    @Test
    void peekNextTest3() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("list");
        CommandHistory.addUsedCommand("history");
        CommandHistory.addUsedCommand("clear");
        assertEquals("clear", CommandHistory.peekNext());
        assertEquals("history", CommandHistory.peekNext());
        assertEquals("list", CommandHistory.peekNext());
        assertEquals("list", CommandHistory.peekNext());
        assertEquals("list", CommandHistory.peekNext());
    }

    @Test
    void peekPrevTest1() {
        CommandHistory.clearHistory();
        assertEquals("", CommandHistory.peekPrev());
    }

    @Test
    void peekPrevTest2() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("list");
        assertEquals("", CommandHistory.peekPrev());
    }

    @Test
    void peekPrevTest3() {
        CommandHistory.clearHistory();
        CommandHistory.addUsedCommand("list");
        CommandHistory.addUsedCommand("history");
        CommandHistory.addUsedCommand("clear");
        assertEquals("clear", CommandHistory.peekNext());
        assertEquals("history", CommandHistory.peekNext());
        assertEquals("list", CommandHistory.peekNext());
        assertEquals("history", CommandHistory.peekPrev());
        assertEquals("clear", CommandHistory.peekPrev());
        assertEquals("", CommandHistory.peekPrev());
    }
}
