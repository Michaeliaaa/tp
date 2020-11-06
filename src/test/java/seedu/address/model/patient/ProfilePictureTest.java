package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProfilePictureTest {
    @Test
    // Null file path
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProfilePicture(null));
    }

    @Test
    public void isValidFilePath() {
        // Null file path
        assertThrows(NullPointerException.class, () -> ProfilePicture.isValidFilePath(null));
    }

    @Test
    // Valid test for toString
    public void toString_validInput_success() {
        ProfilePicture profilePicture = new ProfilePicture("data/pictures");
        String filePath = profilePicture.toString();
        assertEquals("data/pictures", filePath);
    }
}

