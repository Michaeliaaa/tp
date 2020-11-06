package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.visit.Visit;

//@@author Q-gabe-reused
//Reused from
//https://github.com/AY1920S1-CS2103T-F12-2/main/blob/master/src/main/java/unrealunity/visit/ui/VisitRecordWindow.java
//with modifications
/**
 * Panel containing the list of visits.
 */
public class ProfileVisitPanel extends UiPart<Stage> {
    private static final String FXML = "ProfileVisitPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PatientListPanel.class);

    @FXML
    private ListView<Visit> visitHistoryView;

    /**
     * Creates a ProfileVisitPanel object.
     */
    public ProfileVisitPanel(Stage root) {
        super(FXML, root);
        setup(FXCollections.observableArrayList());
    }

    /**
     * Creates a new profile visit panel.
     */
    public ProfileVisitPanel() {
        this(new Stage());

    }

    public void setup(ObservableList<Visit> visitHistory) {
        visitHistoryView.setItems(visitHistory);
        visitHistoryView.setCellFactory(listView -> new VisitHistoryViewCell());
    }


    /**
     * Class to display {@code Visit} using {@code VisitHistoryViewCell}.
     */
    class VisitHistoryViewCell extends ListCell<Visit> {
        @Override
        protected void updateItem(Visit visit, boolean empty) {
            super.updateItem(visit, empty);

            if (visit == null || empty) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProfileVisitCard(visit, String.valueOf(getIndex() + 1)).getRoot());
            }
        }
    }

    /**
     * Displays the ProfileVisitPanel window.
     */
    public void show() {
        logger.fine("Displaying visitation log.");
        getRoot().show();
        getRoot().setAlwaysOnTop(true);
        getRoot().centerOnScreen();
        getRoot().addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            KeyCode userInput = event.getCode();
            if (userInput == KeyCode.ESCAPE) {
                this.hide();
            }
        });

    }

    /**
     * Checks if the ProfileVisitPanel window is being displayed.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the ProfileVisitPanel window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the ProfileVisitPanel window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}
