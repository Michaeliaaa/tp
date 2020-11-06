package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Helper functions for handling strings.
 */
public class StringUtil {

    /**
     * Returns true if the {@code sentence} is a subsequence of the {@code word}.
     *   Ignores case, and a full match is not necessary. Partial matching will be done.
     *   <br>examples:<pre>
     *       containsWordIgnoreCase("ABc def", "abc") == true
     *       containsWordIgnoreCase("ABc def", "DEF") == true
     *       containsWordIgnoreCase("ABc def", "gh") == false
     *       containsWordIgnoreCase("ABc def", "AB") == true // Partial matching
     *       </pre>
     * @param sentence cannot be null
     * @param word cannot be null, cannot be empty, must be a single word
     */

    public static boolean containsWordIgnoreCase(String sentence, String word) {
        requireNonNull(sentence);
        requireNonNull(word);

        String preppedWord = word.trim();
        checkArgument(!preppedWord.isEmpty(), "Word parameter cannot be empty");
        checkArgument(preppedWord.split("\\s+").length == 1, "Word parameter should be a single word");

        String preppedSentence = sentence;
        String[] wordsInPreppedSentence = preppedSentence.split("\\s+");

        boolean flagLower;
        boolean flagUpper;
        boolean checkFlag = false;

        for (String testString: wordsInPreppedSentence) {
            // To prepare user input in both lower and upper cases
            String preppedWordLower = preppedWord.toLowerCase();
            String preppedWordUpper = preppedWord.toUpperCase();
            // To prepare clinical data in both lower and upper cases
            String checkingStringLower = testString.toLowerCase();
            String checkingStringUpper = testString.toUpperCase();

            // Return true if user input string matches either partially or fully with patient's name or IC
            flagLower = checkingStringLower.contains(preppedWordLower);
            flagUpper = checkingStringUpper.contains(preppedWordUpper);
            if (flagLower || flagUpper) {
                checkFlag = true;
            }
        }
        return checkFlag;
    }

    /**
     * Returns a detailed message of the t, including the stack trace.
     */
    public static String getDetails(Throwable t) {
        requireNonNull(t);
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return t.getMessage() + "\n" + sw.toString();
    }

    /**
     * Returns true if {@code s} represents a non-zero unsigned integer
     * e.g. 1, 2, 3, ..., {@code Integer.MAX_VALUE} <br>
     * Will return false for any other non-null string input
     * e.g. empty string, "-1", "0", "+1", and " 2 " (untrimmed), "3 0" (contains whitespace), "1 a" (contains letters)
     * @throws NullPointerException if {@code s} is null.
     */
    public static boolean isNonZeroUnsignedInteger(String s) {
        requireNonNull(s);

        try {
            int value = Integer.parseInt(s);
            return value > 0 && !s.startsWith("+"); // "+1" is successfully parsed by Integer#parseInt(String)
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
