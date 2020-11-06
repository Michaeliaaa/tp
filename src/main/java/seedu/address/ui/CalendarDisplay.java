package seedu.address.ui;

import static seedu.address.commons.util.VEventUtil.appsToVEventsMapper;

import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import jfxtras.icalendarfx.VCalendar;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.icalendar.ICalendarAgenda;
import seedu.address.model.appointment.Appointment;
import seedu.address.ui.skin.CustomEightDaySkin;

/**
 * A ui for the calendar displayed in one of the tabs of the application.
 */
public class CalendarDisplay extends UiPart<Region> {

    private static final String FXML = "CalendarDisplay.fxml";
    private ICalendarAgenda calendar;

    @FXML
    private StackPane calendarPlaceholder;

    /**
     * Creates a {@code Calendar} with a blank {@code Agenda}.
     */
    public CalendarDisplay(ObservableList<Appointment> appointmentList) {
        super(FXML);
        VCalendar vCalendar = new VCalendar().withVEvents(appsToVEventsMapper(appointmentList));
        calendar = new ICalendarAgenda(vCalendar);
        calendar.setSkin(new CustomEightDaySkin(calendar));
        disableMouseInteraction(calendar);
        calendarPlaceholder.getChildren().add(calendar);
        appointmentList.addListener((Change<? extends Appointment> c) -> {
            calendarPlaceholder.getChildren().clear();
            VCalendar vCalendarNew = new VCalendar().withVEvents(appsToVEventsMapper(c.getList()));
            calendar = new ICalendarAgenda(vCalendarNew);
            calendar.setSkin(new CustomEightDaySkin(calendar));
            disableMouseInteraction(calendar);
            calendarPlaceholder.getChildren().add(calendar);
        });
    }

    private static void disableMouseInteraction(ICalendarAgenda agenda) {
        agenda.setAllowDragging(false);
        agenda.setAllowResize(false);
        agenda.setActionCallback((Agenda.Appointment n) -> null);
        agenda.setNewAppointmentCallback((Agenda.LocalDateTimeRange n) -> null);
        agenda.setSelectedOneAppointmentCallback((Agenda.Appointment n) -> null);
        agenda.setNewAppointmentDrawnCallback((Agenda.Appointment a) -> null);
        agenda.setAppointmentChangedCallback((Agenda.Appointment a) -> null);
        agenda.setOnMouseClicked((MouseEvent e) -> {});
        agenda.setOnMousePressed((MouseEvent e) -> {});
        agenda.setAllowDragging(false);
        agenda.setOnMouseDragEntered((MouseDragEvent e) -> {});
        agenda.setOnMouseDragExited((MouseDragEvent e) -> {});
        agenda.setOnTouchPressed((TouchEvent e) -> {});
        agenda.setOnMouseEntered((MouseEvent e) -> {});
        agenda.setOnMouseExited((MouseEvent e) -> {});
    }

}
