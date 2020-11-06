package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;

public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy - h:mma");

    public final Appointment appointment;

    @FXML
    private HBox appointmentCardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label icNumber;
    @FXML
    private Label startTime;
    @FXML
    private Label duration;

    /**
     * Creates a {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);

        assert appointment != null;
        this.appointment = appointment;

        id.setText(displayedIndex + ". ");
        name.setText(appointment.getPatientName().fullName);
        icNumber.setText("IC: " + appointment.getPatientIc().value);
        startTime.setText(formatter.format(appointment.getStartTime().dateTime));

        int minutes = (int) ChronoUnit.MINUTES
                        .between(appointment.getStartTime().dateTime, appointment.getEndTime().dateTime);
        duration.setText("Duration: " + minutes + (minutes == 1 ? " minute" : " minutes"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentCard)) {
            return false;
        }

        AppointmentCard otherACard = (AppointmentCard) other;
        return id.getText().equals(otherACard.id.getText())
                && appointment.equals(otherACard.appointment);
    }

}
