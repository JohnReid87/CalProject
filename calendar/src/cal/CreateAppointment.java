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
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class CreateAppointment extends JFrame implements ActionListener {
	
	
	private static final long serialVersionUID = 1L;
	
	JButton	 attendeeAddBtn = new JButton("Add");
	JButton  createBtn = new JButton("Create");
	JButton  clearBtn = new JButton("Clear");
	JButton  closeBtn = new JButton("Close");
	JDatePickerImpl datePicker;
	UtilDateModel dateModel;
	JSpinner	timeStart = new JSpinner(new SpinnerDateModel() );
	JSpinner	timeEnd	= new JSpinner(new SpinnerDateModel() );
	JComboBox	attendeesPick = new JComboBox();
	JTextField	subjectText = new JTextField();
	JTextField	locationText = new JTextField();
	JTextField	detailsText = new JTextField();
	JTextArea	dateReview = new JTextArea();
	JTextArea	timeReview = new JTextArea();
	JTextArea	locReview = new JTextArea();
	JTextArea	attendeesReview = new JTextArea();
	JTextArea	subjectReview = new JTextArea();
	JTextArea	detailsReview = new JTextArea();	
	
	public CreateAppointment(){
		displayUI();
	}


	public CreateAppointment(UtilDateModel date){ //??
		if(date != null){
		dateModel.setDate(date.getYear(), date.getMonth(), date.getDay());
		}	
	}
	
	public CreateAppointment(SimpleDateFormat date, SimpleDateFormat time){
		if(date != null && time != null){
		// datePick.setDate(date);
		// timePick.setTime(time);
			
		}
	}
	
	private void displayUI() {
		
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Create Appointment");
		this.setSize(400, 500);
		
		JPanel reviewPanel = new JPanel(new GridLayout(13,0));					//right panel
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

		
		JPanel dataPanel = new JPanel(new GridLayout(13,0));				//left panel
		dataPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		dataPanel.add(new JLabel("Date:"));									
		
		dateModel = new UtilDateModel();									//Date section
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
		datePicker = new JDatePickerImpl(datePanel);
		/*datePicker.addPropertyChangeListener(new PropertyChangeListener() {	// 
			public void propertyChange(PropertyChangeEvent arg0) {
				dateReview.setText("");
				dateReview.setText( datePicker.toString() );
				}
				}); 
		*/
		dataPanel.add(datePicker);
		
		dataPanel.add(new JLabel("Time:"));									//Time section..
		JPanel timegrp = new JPanel(new GridLayout(0,2));
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeStart, "HH:mm");
		timeStart.setEditor(timeEditor);
		timeStart.setValue(new Date());
		timeStart.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				timeReview.setText(timeStart.getValue().toString());
			}
			
		});
		timegrp.add(timeStart);
		JSpinner.DateEditor	timeEndEdit = new JSpinner.DateEditor(timeEnd,"HH:mm");
		timeEnd.setEditor(timeEndEdit);
		timeEnd.setValue(new Date());
		timeEnd.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				Date cd = (Date) timeStart.getValue();
				if( cd.after( (Date) timeEnd.getValue() ))
					timeEnd.setValue(cd);
			}
		});
		timegrp.add(timeEnd);
		dataPanel.add(timegrp);
		
		dataPanel.add(new JLabel("Room Number:"));
		locationText.getDocument().addDocumentListener(new reviewUpdate(locationText, locReview));
		dataPanel.add(locationText);
		
		dataPanel.add(new JLabel("Attendees:"));
		JPanel attendeesgrp = new JPanel(new GridLayout(0,3));	
		attendeesgrp.add(attendeesPick, BorderLayout.EAST);
		attendeeAddBtn.addActionListener(this);
		attendeesgrp.add(attendeeAddBtn, BorderLayout.WEST);
		dataPanel.add(attendeesgrp);
		
		dataPanel.add(new JLabel("Subject:"));
		subjectText.getDocument().addDocumentListener(new reviewUpdate(subjectText, subjectReview));
		dataPanel.add(subjectText);
		
		dataPanel.add(new JLabel("Details:"));
		detailsText.getDocument().addDocumentListener(new reviewUpdate(detailsText, detailsReview));
		dataPanel.add(detailsText);
		
		JPanel basePanel = new JPanel(new GridLayout(0,2));		//CENTER panel to hold right (review) and left (appointment data) panels		
		basePanel.add(dataPanel);
		basePanel.add(reviewPanel);
		
		JPanel bottomPanel = new JPanel(new GridLayout(0,3));
		//bottomPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		bottomPanel.add(closeBtn);//, BorderLayout.PAGE_END);
		closeBtn.addActionListener(this);
		bottomPanel.add(clearBtn);//, BorderLayout.PAGE_END);
		clearBtn.addActionListener(this);
		bottomPanel.add(createBtn);//, BorderLayout.PAGE_END);
		createBtn.addActionListener(this);
		
		//this.getContentPane().add(dataPanel, BorderLayout.LINE_START);
		//this.getContentPane().add(reviewPanel, BorderLayout.LINE_END);
		this.getContentPane().add(basePanel, BorderLayout.CENTER);	
		this.getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if( e.getSource() == closeBtn)	//close button clicked
			this.dispose();
		
		if( e.getSource() == clearBtn){

			locationText.setText("");
			locReview.setText("");
			attendeesReview.setText("");
			subjectText.setText("");
			subjectReview.setText("");
			detailsText.setText("");
			detailsReview.setText("");
		
			
		}else if( e.getSource() == createBtn){
		 
			// prepare date vars for fetchPush query.
			SimpleDateFormat crf = new SimpleDateFormat("HHmm");
			SimpleDateFormat cdf = new SimpleDateFormat("DDmmyy");
			String curTime = crf.format(new Date());
		    String curDate = cdf.format(new Date());
		   
		    //
		    Date dS = (Date) timeStart.getValue();
		    Date dE = (Date) timeEnd.getValue();
		    long duration = dS.getTime() - dE.getTime();
		    Long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		    
		    String noteTxt = subjectReview.getText() + " \n" + detailsReview.getText(); 
		    
		    ArrayList<String> attendeesList = new ArrayList<String>();
		    for(int i=0; i < attendeesPick.getItemCount(); i++){
		    	attendeesList.add((String) attendeesPick.getItemAt(i));
		    }
		    
		    		
		//	PUSH appointment..
		String personID = Integer.toString( User.getUserId() );		// get personID
		String roomNum = locationText.getText().trim(); // get location (room number) text
		String appTime = curTime;
		String appDate = curDate;
		int appDuration = diffMinutes.intValue();
		String note = noteTxt;
		
		try{
			fetchPush.addAppointment(personID, roomNum, appTime, appDate, appDuration, note, attendeesList);
		}catch(Exception ex){
			ex.printStackTrace();

		}
		
		}
		
		if( e.getSource() == attendeeAddBtn){
			attendeesReview.append(String.format(attendeesPick.toString() + ", "));
		}
	}
	
	private static class reviewUpdate implements DocumentListener {
		
		private JTextField	sourceText;
		private JTextArea 	targetText;
		
		public reviewUpdate(JTextField sourceText, JTextArea targetText){
			this.sourceText = sourceText;
			this.targetText = targetText;
		}
	
		@Override
		public void insertUpdate(DocumentEvent e) {
			targetText.setText(sourceText.getText());
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			targetText.setText(sourceText.getText());			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			targetText.setText(sourceText.getText());			
		}
		
	}
}
