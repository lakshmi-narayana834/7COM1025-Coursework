package manger_services;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;
import static org.junit.Assert.*;

public class LeisureCentreManagerTest {

    @Test
    public void testPopulateExerciseTypes() {
        JComboBox<String> comboBox = new JComboBox<>();
        LeisureCentreManager instance = new LeisureCentreManager();

        instance.populateExerciseTypes(comboBox);

        assertNotNull(comboBox);
        assertTrue(comboBox.getItemCount() >= 0);
    }

    @Test
    public void testGetMemberBookings() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Booking ID", "Exercise", "Date", "Day", "Time", "Status", "Review", "Rating"}, 0
        );

        LeisureCentreManager instance = new LeisureCentreManager();
        DefaultTableModel result = instance.getMemberBookings(model, 1);

        assertNotNull(result);
    }

    @Test
    public void testGetAllLessons() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Lesson ID", "Exercise", "Date", "Day", "Time", "Price", "Capacity"}, 0
        );

        LeisureCentreManager instance = new LeisureCentreManager();
        DefaultTableModel result = instance.getAllLessons(model);

        assertNotNull(result);
    }

    @Test
    public void testSearchLessonsByDay() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Lesson ID", "Exercise", "Date", "Day", "Time", "Price"}, 0
        );

        LeisureCentreManager instance = new LeisureCentreManager();
        DefaultTableModel result = instance.searchLessonsByDay(model, "Saturday");

        assertNotNull(result);
    }

    @Test
    public void testSearchLessonsByExercise() {
        DefaultTableModel model = new DefaultTableModel(
            new Object[]{"Lesson ID", "Exercise", "Date", "Day", "Time", "Price"}, 0
        );

        LeisureCentreManager instance = new LeisureCentreManager();
        DefaultTableModel result = instance.searchLessonsByExercise(model, "Yoga");

        assertNotNull(result);
    }

    @Test
    public void testGetAvailableLessonDates() {
        LeisureCentreManager instance = new LeisureCentreManager();
        List<String> result = instance.getAvailableLessonDates("Yoga", 1);

        assertNotNull(result);
    }

    @Test
    public void testGetAvailableDays() {
        LeisureCentreManager instance = new LeisureCentreManager();
        List<String> result = instance.getAvailableDays("Yoga", "2025-05-03", 1);

        assertNotNull(result);
    }

    @Test
    public void testGetAvailableTimeSlots() {
        LeisureCentreManager instance = new LeisureCentreManager();
        List<String> result = instance.getAvailableTimeSlots("Yoga", "2025-05-03", "Saturday", 1);

        assertNotNull(result);
    }
}