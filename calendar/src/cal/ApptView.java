package cal;
 
 
/**
 * CreateAppointment class
 *  constructs CreateAppointment dialog, responsible for using fetchPush class to 
 *  PUSH new appointments into database.
 *  
 *   uses JDatePicker Copyright 2004 Juan Heyns. All rights reserved.
 *   
 *   @author Zisis - 11845663
 */
 
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
 


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
 


import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.SwingConstants;
 
 
public class ApptView extends JFrame implements ActionListener {
 
 
    private static final long serialVersionUID = 1L;
 
 
    JButton  deleteBtn = new JButton("Delete");
    JButton  closeBtn = new JButton("Close");
    UtilDateModel dateModel;
    JTextArea   dateReview = new JTextArea();
    JTextArea   timeReview = new JTextArea();
    JTextArea   locReview = new JTextArea();
    JTextArea   attendeesReview = new JTextArea();
    JTextArea   subjectReview = new JTextArea();
    JTextArea   detailsReview = new JTextArea();    
    
    private String apptID;

 
 
    public ApptView(){
        displayUI();
    }
 
 
 
    private void displayUI() {
 
 
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Appointment");
        this.setSize(116, 357);
 
 
        JPanel reviewPanel = new JPanel(new GridLayout(13,0));                  //right panel
        reviewPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
        reviewPanel.setBackground(Color.WHITE);
        reviewPanel.add(new JLabel("Date:"));
        dateReview.setEditable(false);
        reviewPanel.add(dateReview);
 
 
        reviewPanel.add(new JLabel("Time:"));
        timeReview.setEditable(false);      
        reviewPanel.add(timeReview);
 
 
        reviewPanel.add(new JLabel("Room Number:"));
        locReview.setEditable(false);
        reviewPanel.add(locReview);
 
 
        reviewPanel.add(new JLabel("Attendees:"));
        attendeesReview.setEditable(false);     
        reviewPanel.add(attendeesReview);
 
 
        reviewPanel.add(new JLabel("Subject:"));
        subjectReview.setEditable(false);       
        reviewPanel.add(subjectReview);
 
 
        reviewPanel.add(new JLabel("Details:"));
        detailsReview.setEditable(false);       
        reviewPanel.add(detailsReview);
 
 
        dateModel = new UtilDateModel();                                    //Date section
        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
 
 
 
        JPanel basePanel = new JPanel(new GridLayout(0,2));
        basePanel.setBackground(Color.WHITE);
        basePanel.add(reviewPanel);
 
 
        JPanel bottomPanel = new JPanel(new GridLayout(0,3));
        bottomPanel.add(closeBtn);//, BorderLayout.PAGE_END);  
        bottomPanel.add(deleteBtn);
        closeBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        this.getContentPane().add(basePanel, BorderLayout.CENTER);  
        this.getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
        

        this.pack();
 
 
 
    }
    
    public void showAppointment(ArrayList<String> appointment){
         
        /** appointment.get()
         *  0 - apptID
         *  1 - roomNum
         *  2 - apptTime
         *  3 - apptDay
         *  4 - duration
         *  5 - note
         */
        dateReview.setText(appointment.get(3));
        timeReview.setText(appointment.get(2));
        locReview.setText(appointment.get(1));
        detailsReview.setText(appointment.get(5));
        apptID = appointment.get(0);
         
        this.setVisible(true);
    }
     
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == closeBtn){
            this.dispose();
            CalendarFrame.exitProcedure();
            CalendarFrame ca = new CalendarFrame();
            ca.run(); 
        }
         if (e.getSource() == deleteBtn){
        	 fetchPush.deleteAppointment(apptID);
             dateReview.setText("");
             timeReview.setText("");
             locReview.setText("");
             detailsReview.setText("Removed");
         }
    }
 
 
     
         
    }
