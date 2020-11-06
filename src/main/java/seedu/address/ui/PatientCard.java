package seedu.address.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.ProfilePicture;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;
    private final int index;
    private final Consumer<String> executor;

    @FXML
    private HBox cardPane;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label icNumber;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label sex;
    @FXML
    private Label bloodType;
    @FXML
    private Label visitHistory;
    @FXML
    private FlowPane allergies;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex, Consumer<String> executor) {
        super(FXML);
        this.patient = patient;
        this.index = displayedIndex;
        this.executor = executor;

        ProfilePicture thisProfilePic = patient.getProfilePicture();
        File profilePic = new File(thisProfilePic.toString());

        try {
            assert profilePic != null : "Profile picture cannot be null";
            FileInputStream fileInputStream = new FileInputStream(profilePic);
            Image finalProfilePic = new Image(fileInputStream);
            setupImageView(finalProfilePic, profilePicture);
        } catch (IOException error) {
            error.getMessage();
        }

        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        phone.setText("Phone: " + patient.getPhone().value);
        icNumber.setText("NRIC: " + patient.getIcNumber().value);
        address.setText("Address: " + patient.getAddress().value);
        email.setText("Email: " + patient.getEmail().value);
        sex.setText("Sex: " + patient.getSex().value);
        bloodType.setText("Blood Type: " + patient.getBloodType().type);
        visitHistory.setText(patient.getVisitHistory().toString());
        patient.getAllergies().stream()
                .sorted(Comparator.comparing(tag -> tag.allergyName))
                .forEach(tag -> allergies.getChildren().add(new Label(tag.allergyName)));
        cardPane.setStyle("-fx-background-color: " + patient.getColorTag().cssColor + ";");
    }

    private static void setupImageView(Image img, ImageView imgView) {
        // Adopted from https://stackoverflow.com/a/43669816
        double width = img.getWidth();
        double height = img.getHeight();
        double newMeasure = Math.min(width, height);
        double x = (width - newMeasure) / 2;
        double y = (height - newMeasure) / 2;

        Rectangle2D rect = new Rectangle2D(x, y, newMeasure, newMeasure);
        imgView.setViewport(rect);
        imgView.setImage(img);
    }

    @FXML
    private void dragPictureOver(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        } else {
            // Do nothing.
        }
    }

    @FXML
    private void dropPicture(DragEvent event) throws IOException, CommandException, IllegalValueException {
        Dragboard dragboard = event.getDragboard();
        List<File> fileToTransfer = dragboard.getFiles();
        File imageFile = fileToTransfer.get(0);
        assert imageFile != null : "Profile picture cannot be null";
        executor.accept("addpicture " + index + " f/" + imageFile.getCanonicalPath());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        PatientCard card = (PatientCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }
}
