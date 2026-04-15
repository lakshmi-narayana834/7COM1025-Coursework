package myBookings;


import manger_services.LeisureCentreManager;
import manger_services.BookingService;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import review.ReviewPage;


public class MyBookingsPage extends javax.swing.JFrame {
    
    
    LeisureCentreManager leisureCentreManager;
    BookingService bookingService;
    
    int memberId = 9;
    
     
    /**
     * Creates new form PurchasePage
     */
    public MyBookingsPage() {
        initComponents();
        
        leisureCentreManager = new LeisureCentreManager();
        bookingService = new BookingService();
        
        loadBookedLessonsIntoTable();
        
     
        
        
    }
    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableShowBookedLessons = new javax.swing.JTable();
        jBtnEditLesson = new javax.swing.JButton();
        jBtnReview1 = new javax.swing.JButton();
        jBtnReview2 = new javax.swing.JButton();

        jTable1.setBackground(new java.awt.Color(255, 204, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Duration", "Date"
            }
        ));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Booked Lessons");
        setBackground(new java.awt.Color(204, 255, 255));
        setLocation(new java.awt.Point(120, 140));
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 128, 59));
        jLabel1.setText("-----Booked Lessons------");

        jTableShowBookedLessons.setBackground(new java.awt.Color(255, 153, 153));
        jTableShowBookedLessons.setForeground(new java.awt.Color(51, 128, 59));
        jTableShowBookedLessons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Subject", "Date", "Day", "Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableShowBookedLessons.setShowGrid(true);
        jScrollPane2.setViewportView(jTableShowBookedLessons);
        if (jTableShowBookedLessons.getColumnModel().getColumnCount() > 0) {
            jTableShowBookedLessons.getColumnModel().getColumn(0).setResizable(false);
        }

        jBtnEditLesson.setBackground(new java.awt.Color(0, 153, 0));
        jBtnEditLesson.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnEditLesson.setForeground(new java.awt.Color(255, 255, 255));
        jBtnEditLesson.setText("Edit Booking");
        jBtnEditLesson.setToolTipText("Edit Booking");
        jBtnEditLesson.setActionCommand("btnBook");
        jBtnEditLesson.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnEditLesson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openEditBookingPage(evt);
            }
        });

        jBtnReview1.setBackground(new java.awt.Color(0, 153, 204));
        jBtnReview1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnReview1.setForeground(new java.awt.Color(255, 255, 255));
        jBtnReview1.setText("Cancel Booking");
        jBtnReview1.setActionCommand("btnBook");
        jBtnReview1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnReview1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBooking(evt);
            }
        });

        jBtnReview2.setBackground(new java.awt.Color(255, 102, 51));
        jBtnReview2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnReview2.setForeground(new java.awt.Color(255, 255, 255));
        jBtnReview2.setText("Review ");
        jBtnReview2.setActionCommand("btnBook");
        jBtnReview2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jBtnReview2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openReviewLesson(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jBtnEditLesson, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jBtnReview1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jBtnReview2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(370, 370, 370))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnEditLesson, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jBtnReview1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBtnReview2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Loads all booked lessons for the current student
    private void loadBookedLessonsIntoTable() {
        
        try {
            DefaultTableModel model = (DefaultTableModel) jTableShowBookedLessons.getModel();
            model.setRowCount(0);

            model = leisureCentreManager.getMemberBookings(model, memberId);
            jTableShowBookedLessons.setModel(model);

        } catch (Exception e) {
            System.out.println("loadBookedLessonsIntoTable: " + e.getMessage());
        }
    }


    
    
    private void openEditBookingPage(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openEditBookingPage
        
        int selectedRowIndex = jTableShowBookedLessons.getSelectedRow();

        if (selectedRowIndex != -1) {
            try {
                int bookingId = Integer.parseInt(
                    jTableShowBookedLessons.getValueAt(selectedRowIndex, 0).toString()
                );

                if (bookingService.isLessonAttended(bookingId)) {
                    JOptionPane.showMessageDialog(null, "You cannot edit an already attended lesson.");
                } else {
                    EditBookingsPage editPage = new EditBookingsPage(bookingId);
                    editPage.setVisible(true);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid booking ID format.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a lesson to edit.");
        }
    }//GEN-LAST:event_openEditBookingPage
    public void clearTable(DefaultTableModel model) {
        model.setRowCount(0);
    }
    // Cancels a selected booking if it’s not already attended or cancelled
    private void cancelBooking(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBooking
        int selectedRowIndex = jTableShowBookedLessons.getSelectedRow();

        if (selectedRowIndex != -1) {
            String bookingIdStr = jTableShowBookedLessons.getValueAt(selectedRowIndex, 0).toString();
            String bookingStatus = jTableShowBookedLessons.getValueAt(selectedRowIndex, 5).toString();  // Status column index!

            if ("Booked".equalsIgnoreCase(bookingStatus)) {
                int bookingId = Integer.parseInt(bookingIdStr);

                bookingService.cancelBooking(bookingId);

                clearTable((DefaultTableModel) jTableShowBookedLessons.getModel());
                loadBookedLessonsIntoTable();

                JOptionPane.showMessageDialog(null, "Lesson booking cancelled successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "You cannot cancel an attended or already cancelled lesson.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a lesson to cancel.");
        }
    }//GEN-LAST:event_cancelBooking

    private void openReviewLesson(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openReviewLesson
        
        int selectedRowIndex = jTableShowBookedLessons.getSelectedRow();

        if (selectedRowIndex != -1) {
            try {
                int bookingId = Integer.parseInt(
                    jTableShowBookedLessons.getValueAt(selectedRowIndex, 0).toString()
                );

                if (bookingService.checkAlreadyReviewed(bookingId)) {

                    JOptionPane.showMessageDialog(null,
                        "You already reviewed this lesson.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                    );

                } else {

                    if (bookingService.isLessonAttended(bookingId)) {

                        ReviewPage reviewPage = new ReviewPage(bookingId);
                        reviewPage.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null,
                            "You can only review attended lessons.");
                    }
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid booking ID format.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please select a lesson to review.");
        }
    }//GEN-LAST:event_openReviewLesson



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnEditLesson;
    private javax.swing.JButton jBtnReview1;
    private javax.swing.JButton jBtnReview2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableShowBookedLessons;
    // End of variables declaration//GEN-END:variables
    
}
