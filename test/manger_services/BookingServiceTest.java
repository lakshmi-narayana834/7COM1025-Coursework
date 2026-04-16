/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package manger_services;



import manger_services.BookingService;
import javax.swing.table.DefaultTableModel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kingj
 */
public class BookingServiceTest {
    
    public BookingServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        // Optional: set up database or test data
    }
    
    @AfterClass
    public static void tearDownClass() {
        // Optional: clean up database or test data
    }

    /**
     * Test of isLessonAttended method, of class BookingService.
     */
    @Test
    public void testIsLessonAttended() {
        System.out.println("isLessonAttended");
        int bookingId = 0;
        BookingService instance = new BookingService();
        boolean expResult = false;
        boolean result = instance.isLessonAttended(bookingId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isLessonFull method, of class BookingService.
     */
    @Test
    public void testIsLessonFull() {
        System.out.println("isLessonFull");
        int lessonId = 0;
        BookingService instance = new BookingService();
        boolean expResult = false;
        boolean result = instance.isLessonFull(lessonId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of hasTimeConflict method, of class BookingService.
     */
    @Test
    public void testHasTimeConflict() {
        System.out.println("hasTimeConflict");
        int memberId = 0;
        int lessonId = 0;
        BookingService instance = new BookingService();
        boolean expResult = false;
        boolean result = instance.hasTimeConflict(memberId, lessonId);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of createBooking method, of class BookingService.
     */
    @Test
    public void testCreateBooking() {
        System.out.println("createBooking");
        int memberId = 0;
        int lessonId = 0;
        BookingService instance = new BookingService();
        instance.createBooking(memberId, lessonId);
        
    }

    /**
     * Test of cancelBooking method, of class BookingService.
     */
    @Test
    public void testCancelBooking() {
        System.out.println("cancelBooking");
        int bookingId = 0;
        BookingService instance = new BookingService();
        instance.cancelBooking(bookingId);

    }

    /**
     * Test of fetchAvailableLessonId method, of class BookingService.
     */
    @Test
    public void testFetchAvailableLessonId() {
        System.out.println("fetchAvailableLessonId");
        String exerciseName = "";
        String date = "";
        String dayName = "";
        String timeSlot = "";
        BookingService instance = new BookingService();
        int expResult = 0;
        int result = instance.fetchAvailableLessonId(exerciseName, date, dayName, timeSlot);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of changeBooking method, of class BookingService.
     */
    @Test
    public void testChangeBooking() {
        System.out.println("changeBooking");
        int bookingId = 0;
        int newLessonId = 0;
        int memberId = 0;
        BookingService instance = new BookingService();
        instance.changeBooking(bookingId, newLessonId, memberId);
        
    }

    /**
     * Test of addReview method, of class BookingService.
     */
    @Test
    public void testAddReview() {
        System.out.println("addReview");
        int bookingId = 0;
        String review = "";
        int rating = 0;
        BookingService instance = new BookingService();
        instance.addReview(bookingId, review, rating);
        
    }

   

   

    /**
     * Test of checkAlreadyReviewed method, of class BookingService.
     */
    @Test
    public void testCheckAlreadyReviewed() {
        System.out.println("checkAlreadyReviewed");
        int bookingId = 0;
        BookingService instance = new BookingService();
        boolean expResult = false;
        boolean result = instance.checkAlreadyReviewed(bookingId);
        assertEquals(expResult, result);
        
    }
    
}
