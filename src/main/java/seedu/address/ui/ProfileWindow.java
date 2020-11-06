package seedu.address.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.PatientProfileUtil;
import seedu.address.logic.Logic;
import seedu.address.model.allergy.Allergy;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.BloodType;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.IcNumber;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.ProfilePicture;
import seedu.address.model.patient.Sex;
import seedu.address.model.visit.Visit;
import seedu.address.model.visit.VisitHistory;

//@@author Q-gabe-reused
//Reused from
//https://github.com/AY1920S1-CS2103T-F12-2/main/blob/master/src/main/java/unrealunity/visit/ui/ProfileWindow.java
//with modifications

/**
 * Popup window displaying patient's parameters.
 */
public class ProfileWindow extends UiPart<Stage> {
    private static final String FXML = "ProfileWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(ProfileWindow.class);

    private Logic logic;
    private Patient patient;

    @FXML
    private TextArea name;

    @FXML
    private TextArea allergy;

    @FXML
    private TextArea phone;

    @FXML
    private TextArea email;

    @FXML
    private TextArea address;

    @FXML
    private ListView<Visit> visitHistory;

    @FXML
    private TextArea icNumber;

    @FXML
    private TextArea sex;

    @FXML
    private TextArea bloodType;

    @FXML
    private ImageView profilePicture;

    /**
     * Creates a ProfileWindow object.
     */
    public ProfileWindow() {
        this(new Stage());
        this.getRoot().initModality(Modality.APPLICATION_MODAL);

        getRoot().addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            KeyCode userInput = event.getCode();
            if (userInput == KeyCode.ESCAPE) {
                logger.info("User pressed 'ESC'. Profile Panel closing..");
                getRoot().hide();
                logger.info("Profile Panel successfully closed.");
            }
        }
        );
    }

    /**
     * Creates a ProfileWindow object.
     */
    public ProfileWindow(Stage root) {
        super(FXML, root);
        populateVisitHistory(FXCollections.observableArrayList());
    }

    @FXML
    void mouseClickClose(MouseEvent e) {
        Label exitLabel = (Label) e.getSource();
        exitLabel.setUnderline(true);
    }

    @FXML
    void mouseExit(MouseEvent e) {
        Label exitLabel = (Label) e.getSource();
        exitLabel.setUnderline(false);
    }

    @FXML
    void mouseEnterClose(MouseEvent e) {
        logger.info("User pressed 'close'. Profile panel closing..");
        getRoot().hide();
        logger.info("Profile panel successfully closed.");
    }

    /**
     * Fills the Profile Window with the parameters from the specified patient.
     *
     * @param patient Patient to display in the Profile Window
     */
    public void setup(Patient patient, Logic logic) {
        this.logic = logic;
        this.patient = patient;

        assert patient != null || logic != null : "Patient and logic cannot be null";

        Name patientName = patient.getName();
        Set<Allergy> patientAllergy = patient.getAllergies();
        Phone patientPhone = patient.getPhone();
        Email patientEmail = patient.getEmail();
        Address patientAddress = patient.getAddress();
        BloodType patientBloodType = patient.getBloodType();
        Sex patientSex = patient.getSex();
        IcNumber patientIcNumber = patient.getIcNumber();

        name.setText(PatientProfileUtil.convertNameToString(patientName));
        allergy.setText(PatientProfileUtil.convertAllergiesToString(patientAllergy));
        phone.setText(PatientProfileUtil.convertPhoneToString(patientPhone));
        email.setText(PatientProfileUtil.convertEmailToString(patientEmail));
        address.setText(PatientProfileUtil.convertAddressToString(patientAddress));
        bloodType.setText(PatientProfileUtil.convertBloodTypeToString(patientBloodType));
        sex.setText(PatientProfileUtil.convertSexToString(patientSex));
        icNumber.setText(PatientProfileUtil.convertIcToString(patientIcNumber));

        ProfilePicture thisProfilePic = patient.getProfilePicture();
        File profilePic = new File(thisProfilePic.toString());
        try {
            assert profilePic != null : "Profile picture cannot be null";
            FileInputStream fileInputStream = new FileInputStream(profilePic);
            Image finalProfilePic = new Image(fileInputStream);
            profilePicture.setImage(finalProfilePic);
        } catch (IOException error) {
            error.getMessage();
        }

        VisitHistory visitHistory = patient.getVisitHistory();
        ObservableList<Visit> observableHistory = visitHistory.getObservableVisits();
        populateVisitHistory(observableHistory);
    }

    /**
     * Fills profile window with patient's visit history.
     * @param observableHistory Observable list of patient's visit history.
     *
     */
    public void populateVisitHistory(ObservableList<Visit> observableHistory) {
        visitHistory.setItems(observableHistory);
        visitHistory.setCellFactory(listView -> new ProfileVisitHistoryCell());
    }

    /**
     * Class to display {@code Visit} using a {@code ProfileVisitCard}.
     */
    class ProfileVisitHistoryCell extends ListCell<Visit> {
        @Override
        protected void updateItem(Visit visit, boolean empty) {
            super.updateItem(visit, empty);

            if (visit == null || empty) {
                setGraphic(null);
                setText(null);
            } else {
                ProfileVisitCard visitPanel = new ProfileVisitCard(visit, String.valueOf(getIndex() + 1));
                setGraphic(visitPanel.getRoot());
            }
        }
    }

    /**
     * Displays the Profile Panel.
     *
     */
    public void show() {
        logger.info("Showing Profile Panel");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Checks if ProfileWindow is being displayed.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the ProfileWindow.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on ProfileWindow.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}

