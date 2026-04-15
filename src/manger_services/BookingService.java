package manger_services;

import database.DatabaseConnector;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class BookingService {

    private final DatabaseConnector db = new DatabaseConnector();

    // ===============================
    // CHECK IF LESSON ATTENDED
    // ===============================
    public boolean isLessonAttended(int bookingId) {
        String query = "SELECT 1 FROM bookings WHERE booking_id = ? AND status = 'attended'";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error checking attendance: " + e.getMessage());
        }

        return false;
    }

    // ===============================
    // CHECK IF LESSON FULL (MAX 4)
    // ===============================
    public boolean isLessonFull(int lessonId) {
        String query =
            "SELECT COUNT(*) FROM bookings " +
            "WHERE lesson_id = ? AND status IN ('booked','attended','changed')";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, lessonId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) >= 4;
            }

        } catch (SQLException e) {
            System.out.println("Error checking capacity: " + e.getMessage());
        }

        return false;
    }

    // ===============================
    // CHECK TIME CONFLICT (IMPORTANT RULE)
    // ===============================
    public boolean hasTimeConflict(int memberId, int lessonId) {

        String query =
            "SELECT COUNT(*) " +
            "FROM bookings b " +
            "JOIN lessons l ON b.lesson_id = l.lesson_id " +
            "JOIN lessons new_lesson ON new_lesson.lesson_id = ? " +
            "WHERE b.member_id = ? " +
            "AND l.lesson_date = new_lesson.lesson_date " +
            "AND l.time_slot = new_lesson.time_slot " +
            "AND b.status IN ('booked','attended','changed')";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, lessonId);
            ps.setInt(2, memberId);

            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;

        } catch (SQLException e) {
            System.out.println("Error checking conflict: " + e.getMessage());
        }

        return false;
    }

    // ===============================
    // CREATE BOOKING
    // ===============================
    public void createBooking(int memberId, int lessonId) {

        if (isLessonFull(lessonId)) {
            System.out.println("Lesson is full!");
            return;
        }

        if (hasTimeConflict(memberId, lessonId)) {
            System.out.println("Time conflict! Cannot book.");
            return;
        }

        String query =
            "INSERT INTO bookings (member_id, lesson_id, status) VALUES (?, ?, 'booked')";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, memberId);
            ps.setInt(2, lessonId);
            ps.executeUpdate();

            System.out.println("Booking successful.");

        } catch (SQLException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
    }

    // ===============================
    // CANCEL BOOKING
    // ===============================
    public void cancelBooking(int bookingId) {
        String query = "UPDATE bookings SET status = 'cancelled' WHERE booking_id = ?";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookingId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Cancel failed: " + e.getMessage());
        }
    }

    public int fetchAvailableLessonId(String exerciseName, String date, String dayName, String timeSlot) {
        int lessonId = 0;

        String query =
            "SELECT l.lesson_id FROM lessons l " +
            "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
            "WHERE e.exercise_name = ? " +
            "AND l.lesson_date = ? " +
            "AND l.day_name = ? " +
            "AND l.time_slot = ?";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, exerciseName);
            ps.setString(2, date);
            ps.setString(3, dayName);
            ps.setString(4, timeSlot);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lessonId = rs.getInt("lesson_id");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching lesson ID: " + e.getMessage());
        }

        return lessonId;
    }
    
    // ===============================
    // CHANGE BOOKING
    // ===============================
    public void changeBooking(int bookingId, int newLessonId, int memberId) {

        if (isLessonFull(newLessonId)) {
            System.out.println("New lesson is full!");
            return;
        }

        if (hasTimeConflict(memberId, newLessonId)) {
            System.out.println("Time conflict!");
            return;
        }

        String query = "UPDATE bookings SET lesson_id = ?, status='changed' WHERE booking_id = ?";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, newLessonId);
            ps.setInt(2, bookingId);
            ps.executeUpdate();

            System.out.println("Booking changed.");

        } catch (SQLException e) {
            System.out.println("Change failed: " + e.getMessage());
        }
    }

    // ===============================
    // ADD REVIEW (SEPARATE TABLE)
    // ===============================
    public void addReview(int bookingId, String review, int rating) {

        if (!isLessonAttended(bookingId)) {
            System.out.println("Cannot review. Lesson not attended.");
            return;
        }

        String query =
            "INSERT INTO reviews (booking_id, review_text, rating) VALUES (?, ?, ?)";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookingId);
            ps.setString(2, review);
            ps.setInt(3, rating);
            ps.executeUpdate();

            System.out.println("Review added.");

        } catch (SQLException e) {
            System.out.println("Review failed: " + e.getMessage());
        }
    }

    // ===============================
    // REPORT 1: LESSON SUMMARY
    // ===============================
    public DefaultTableModel generateLessonReport(DefaultTableModel model) {

        String query =
            "SELECT l.lesson_date, l.day_name, l.time_slot, e.exercise_name, " +
            "COUNT(b.booking_id) AS members, AVG(r.rating) AS avg_rating " +
            "FROM lessons l " +
            "JOIN exercise_types e ON l.exercise_id = e.exercise_id " +
            "LEFT JOIN bookings b ON l.lesson_id = b.lesson_id " +
            "LEFT JOIN reviews r ON b.booking_id = r.booking_id " +
            "GROUP BY l.lesson_id";

        try (Connection con = db.connect();
             ResultSet rs = con.createStatement().executeQuery(query)) {

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("lesson_date"),
                    rs.getString("day_name"),
                    rs.getString("time_slot"),
                    rs.getString("exercise_name"),
                    rs.getInt("members"),
                    rs.getDouble("avg_rating")
                });
            }

        } catch (SQLException e) {
            System.out.println("Report error: " + e.getMessage());
        }

        return model;
    }

    // ===============================
    // REPORT 2: HIGHEST INCOME
    // ===============================
    public DefaultTableModel generateIncomeReport(DefaultTableModel model) {

        String query =
            "SELECT e.exercise_name, e.price, COUNT(b.booking_id)*e.price AS total_income " +
            "FROM exercise_types e " +
            "JOIN lessons l ON e.exercise_id = l.exercise_id " +
            "LEFT JOIN bookings b ON l.lesson_id = b.lesson_id " +
            "GROUP BY e.exercise_id " +
            "ORDER BY total_income DESC";

        try (Connection con = db.connect();
             ResultSet rs = con.createStatement().executeQuery(query)) {

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("exercise_name"),
                    rs.getDouble("total_income")
                });
            }

        } catch (SQLException e) {
            System.out.println("Income report error: " + e.getMessage());
        }

        return model;
    }
    
    public boolean checkAlreadyReviewed(int bookingId) {

        String query =
            "SELECT 1 FROM reviews WHERE booking_id = ?";

        try (Connection con = db.connect();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error checking review status: " + e.getMessage());
        }

        return false;
    }
}