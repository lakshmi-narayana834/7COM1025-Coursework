package manger_services;

import database.DatabaseConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class LeisureCentreManager {

    private final DatabaseConnector db = new DatabaseConnector();

    public LeisureCentreManager() {}

    // Populate exercise names into combo box
    public void populateExerciseTypes(JComboBox<String> comboBox) {
        String query = "SELECT exercise_name FROM exercise_types ORDER BY exercise_name ASC";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            comboBox.removeAllItems();
            while (rs.next()) {
                comboBox.addItem(rs.getString("exercise_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading exercise types: " + e.getMessage());
        }
    }

    // Get bookings for a member
    public DefaultTableModel getMemberBookings(DefaultTableModel model, int memberId) {
        String query =
            "SELECT b.booking_id, e.exercise_name, l.lesson_date, l.day_name, l.time_slot, " +
            "b.status, r.review_text, r.rating " +
            "FROM bookings b " +
            "JOIN lessons l ON b.lesson_id = l.lesson_id " +
            "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
            "LEFT JOIN reviews r ON b.booking_id = r.booking_id " +
            "WHERE b.member_id = ? " +
            "ORDER BY l.lesson_date, l.time_slot";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, memberId);

            try (ResultSet rs = ps.executeQuery()) {
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("booking_id"),
                        rs.getString("exercise_name"),
                        rs.getString("lesson_date"),
                        rs.getString("day_name"),
                        rs.getString("time_slot"),
                        rs.getString("status"),
                        rs.getString("review_text"),
                        rs.getObject("rating")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving member bookings: " + e.getMessage());
        }

        return model;
    }

    // Get all lessons
    public DefaultTableModel getAllLessons(DefaultTableModel model) {
        String query =
            "SELECT l.lesson_id, e.exercise_name, l.lesson_date, l.day_name, l.time_slot, e.price, l.capacity " +
            "FROM lessons l " +
            "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
            "ORDER BY l.lesson_date, l.time_slot";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("lesson_id"),
                    rs.getString("exercise_name"),
                    rs.getString("lesson_date"),
                    rs.getString("day_name"),
                    rs.getString("time_slot"),
                    rs.getDouble("price"),
                    rs.getInt("capacity")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error loading lessons: " + e.getMessage());
        }

        return model;
    }

    // Search lessons by day (Saturday or Sunday)
    public DefaultTableModel searchLessonsByDay(DefaultTableModel model, String dayName) {
        String query =
            "SELECT l.lesson_id, e.exercise_name, l.lesson_date, l.day_name, l.time_slot, e.price " +
            "FROM lessons l " +
            "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
            "WHERE l.day_name = ? " +
            "ORDER BY l.lesson_date, l.time_slot";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, dayName);

            try (ResultSet rs = ps.executeQuery()) {
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("lesson_id"),
                        rs.getString("exercise_name"),
                        rs.getString("lesson_date"),
                        rs.getString("day_name"),
                        rs.getString("time_slot"),
                        rs.getDouble("price")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching lessons by day: " + e.getMessage());
        }

        return model;
    }

    // Search lessons by exercise name
    public DefaultTableModel searchLessonsByExercise(DefaultTableModel model, String exerciseName) {
        String query =
            "SELECT l.lesson_id, e.exercise_name, l.lesson_date, l.day_name, l.time_slot, e.price " +
            "FROM lessons l " +
            "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
            "WHERE e.exercise_name = ? " +
            "ORDER BY l.lesson_date, l.time_slot";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, exerciseName);

            try (ResultSet rs = ps.executeQuery()) {
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("lesson_id"),
                        rs.getString("exercise_name"),
                        rs.getString("lesson_date"),
                        rs.getString("day_name"),
                        rs.getString("time_slot"),
                        rs.getDouble("price")
                    });
                }
            }
        } catch (SQLException e) {
            System.out.println("Error searching lessons by exercise: " + e.getMessage());
        }

        return model;
    }

    // Get available lesson dates for an exercise
public List<String> getAvailableLessonDates(String exerciseName, int memberId) {
    List<String> dates = new ArrayList<>();

    String query =
        "SELECT DISTINCT l.lesson_date " +
        "FROM lessons l " +
        "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
        "WHERE e.exercise_name = ? " +
        "AND ( " +
        "    SELECT COUNT(*) FROM bookings b2 " +
        "    WHERE b2.lesson_id = l.lesson_id " +
        "    AND b2.status IN ('booked', 'attended', 'changed') " +
        ") < 4 " +
        "ORDER BY l.lesson_date";

    try (Connection con = db.connect();
         PreparedStatement ps = con.prepareStatement(query)) {

        ps.setString(1, exerciseName);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                dates.add(rs.getString("lesson_date"));
            }
        }

    } catch (SQLException e) {
        System.out.println("Error retrieving available lesson dates: " + e.getMessage());
    }

    return dates;
}
    // Get available days for a selected exercise and date
    public List<String> getAvailableDays(String exerciseName, String lessonDate, int memberId) {
        List<String> days = new ArrayList<>();

        String query =
            "SELECT DISTINCT l.day_name " +
            "FROM lessons l " +
            "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
            "WHERE e.exercise_name = ? " +
            "AND l.lesson_date = ? " +
            "AND l.lesson_id NOT IN ( " +
            "    SELECT b.lesson_id FROM bookings b " +
            "    WHERE b.member_id = ? AND b.status IN ('booked', 'attended', 'changed') " +
            ") " +
            "AND ( " +
            "    SELECT COUNT(*) FROM bookings b2 " +
            "    WHERE b2.lesson_id = l.lesson_id " +
            "    AND b2.status IN ('booked', 'attended', 'changed') " +
            ") < 4";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, exerciseName);
            ps.setString(2, lessonDate);
            ps.setInt(3, memberId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    days.add(rs.getString("day_name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving available days: " + e.getMessage());
        }

        return days;
    }

    // Get available time slots
    public List<String> getAvailableTimeSlots(String exerciseName, String lessonDate, String dayName, int memberId) {
        List<String> slots = new ArrayList<>();

        String query =
            "SELECT DISTINCT l.time_slot " +
            "FROM lessons l " +
            "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
            "WHERE e.exercise_name = ? " +
            "AND l.lesson_date = ? " +
            "AND l.day_name = ? " +
            "AND l.lesson_id NOT IN ( " +
            "    SELECT b.lesson_id FROM bookings b " +
            "    WHERE b.member_id = ? AND b.status IN ('booked', 'attended', 'changed') " +
            ") " +
            "AND ( " +
            "    SELECT COUNT(*) FROM bookings b2 " +
            "    WHERE b2.lesson_id = l.lesson_id " +
            "    AND b2.status IN ('booked', 'attended', 'changed') " +
            ") < 4 " +
            "ORDER BY CASE l.time_slot " +
            "    WHEN 'morning' THEN 1 " +
            "    WHEN 'afternoon' THEN 2 " +
            "    WHEN 'evening' THEN 3 " +
            "END";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, exerciseName);
            ps.setString(2, lessonDate);
            ps.setString(3, dayName);
            ps.setInt(4, memberId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    slots.add(rs.getString("time_slot"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving available time slots: " + e.getMessage());
        }

        return slots;
    }
}