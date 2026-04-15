package home.FurzefieldLeisureCentre;


import manger_services.LeisureCentreManager;
import manger_services.BookingService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import report.ViewReport;
import myBookings.MyBookingsPage;


public class FurzefieldLeisureCentre extends javax.swing.JFrame {
    
    
    LeisureCentreManager leisureCentreManager;
    BookingService bookingService;

    int memberId = 9; 
    /**
     * Creates new form PurchasePage
     */
    public FurzefieldLeisureCentre() {
        initComponents();
        
        leisureCentreManager = new LeisureCentreManager();
        bookingService = new BookingService();
        
        loadDataIntoTable();
        
        
        
     
        
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBtnBookAppointment = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableShowLessons = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

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
        setTitle("Furzefield Leisure Centre (FLC)");
        setBackground(new java.awt.Color(153, 153, 153));
        setLocation(new java.awt.Point(120, 140));
        setType(java.awt.Window.Type.POPUP);

        jBtnBookAppointment.setBackground(new java.awt.Color(0, 204, 202));
        jBtnBookAppointment.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jBtnBookAppointment.setForeground(new java.awt.Color(255, 255, 255));
        jBtnBookAppointment.setText("Book");
        jBtnBookAppointment.setActionCommand("btnBook");
        jBtnBookAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookAppointment(evt);
            }
        });

        jTableShowLessons.setBackground(new java.awt.Color(255, 153, 102));
        jTableShowLessons.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableShowLessons.setForeground(new java.awt.Color(51, 51, 51));
        jTableShowLessons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Exercise Name", "Lesson Date", "Day", "Time", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableShowLessons.setShowGrid(true);
        jScrollPane2.setViewportView(jTableShowLessons);

        jMenu1.setText("Availble Lessons");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showBookingPage(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Bookings");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMyBookingsPage(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Report");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                getReport(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnBookAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jBtnBookAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Loads all appointment sessions into the JTable
    private void loadDataIntoTable() {
    try {
            DefaultTableModel model = leisureCentreManager.getAllLessons((DefaultTableModel) jTableShowLessons.getModel());
            jTableShowLessons.setModel(model);
        } catch (Exception e) {
            System.out.println("BookingPage.loadDataIntoTable: Error loading lesson data.");
        }
    }


    

    private void bookAppointment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookAppointment
       
        
        int selectedRowIndex = jTableShowLessons.getSelectedRow();

        if (selectedRowIndex != -1) {
            try {
                int lessonId = Integer.parseInt(jTableShowLessons.getValueAt(selectedRowIndex, 0).toString());
                String status = "Booked";
                
         
                if (!bookingService.isLessonFull(lessonId)) {

                    if (!bookingService.hasTimeConflict(memberId, lessonId)) {

                        bookingService.createBooking(memberId, lessonId);
                        JOptionPane.showMessageDialog(null, "Lesson booked successfully.");

                    } else {
                        JOptionPane.showMessageDialog(null, 
                            "Cannot book: You already have a lesson at this time!");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, 
                        "This lesson is full (max 4 members).");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid lesson ID format.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "You forgot to select a lesson.");
        }
    }//GEN-LAST:event_bookAppointment
    
    
    private void showBookingPage(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showBookingPage
        new FurzefieldLeisureCentre().setVisible(true);
        new MyBookingsPage().dispose();
    }//GEN-LAST:event_showBookingPage

    private void showMyBookingsPage(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMyBookingsPage
        new MyBookingsPage().setVisible(true);
        new FurzefieldLeisureCentre().dispose();
    }//GEN-LAST:event_showMyBookingsPage

    private void getReport(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getReport
        new ViewReport().setVisible(true);
    }//GEN-LAST:event_getReport

 public static void main(String args[]) {
               
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FurzefieldLeisureCentre().setVisible(true);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBookAppointment;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTableShowLessons;
    // End of variables declaration//GEN-END:variables
    
}
