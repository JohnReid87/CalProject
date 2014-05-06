package cal;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class CreateAppointment extends JFrame implements ActionListener{
	
	JButton	 attendeeAddBtn = new JButton("Add");
	JButton  createBtn = new JButton("Create");
	JButton  clearBtn = new JButton("Clear");
	JButton  closeBtn = new JButton("Close");
	JLabel	 dateLbl = new JLabel("Date");
	JLabel	 timeLbl = new JLabel("Time");
	JLabel	 locationLbl = new JLabel("Location");
	JLabel	 attendeesLbl = new JLabel("Attendees");
	JLabel	 subjectLbl = new JLabel("Subject");
	JLabel	 detailsLbl = new JLabel("Details");
	//JDatePicker datePick;	//
	JSpinner	timePick = new JSpinner(new SpinnerDateModel() );
	JComboBox	attendeesPick = new JComboBox();
	JTextField	subjectText = new JTextField();
	JTextField	locationText = new JTextField();
	JTextArea	detailsText = new JTextArea();
	JTextArea	reviewText;
	

	
	public CreateAppointment(){
		displayUI();
	}


	public CreateAppointment(SimpleDateFormat date){
		if(date != null){
		//	datePick.setDate(date);
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
		//this.setSize(400, 500);
		
		JPanel reviewPanel = new JPanel(new GridBagLayout());	//right panel
		reviewPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		reviewPanel.add(reviewText = new JTextArea());
		
		JPanel dataPanel = new JPanel(new GridLayout(13,0));				//left panel
		dataPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		dataPanel.add(dateLbl);												//
		//dataPanel.add(datePick);
		dataPanel.add(timeLbl);
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timePick, "HH:mm");
		timePick.setEditor(timeEditor);
		timePick.setValue(new Date());
		dataPanel.add(timePick);
		
		dataPanel.add(locationLbl);
		dataPanel.add(locationText);
		dataPanel.add(attendeesLbl);
	
		JPanel attendeesgrp = new JPanel(new GridLayout(0,2));	//group attendees properly together to avoid alignment issues
		attendeesgrp.add(attendeesPick, BorderLayout.EAST);
		attendeesgrp.add(attendeeAddBtn, BorderLayout.WEST);

		dataPanel.add(attendeesgrp);
		dataPanel.add(subjectLbl);
		dataPanel.add(subjectText);
		dataPanel.add(detailsLbl);
		dataPanel.add(detailsText);
		
		JPanel basePanel = new JPanel(new GridLayout(0,2));		//CENTER panel to hold right (review) and left (appointment data) panels		
		basePanel.add(dataPanel);
		reviewText.setText("Date and time and this and that...");
		basePanel.add(reviewPanel);

		JPanel bottomPanel = new JPanel(new GridLayout(0,3));
		bottomPanel.add(closeBtn);//, BorderLayout.PAGE_END);
		closeBtn.addActionListener(this);
		bottomPanel.add(clearBtn);//, BorderLayout.PAGE_END);
		clearBtn.addActionListener(this);
		bottomPanel.add(createBtn);//, BorderLayout.PAGE_END);
		createBtn.addActionListener(this);
		
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
			//datePick.clear();
			//timePick.clear();
			locationText.setText("");
			//attendeesPick.clear();
			subjectText.setText("");
			detailsText.setText("");
			reviewText.setText(""); //set default review text in the form of 
									// Date:\n Time:\n Location:\n etc
			
		}else if( e.getSource() == createBtn){
			//PUSH appointment..
			
		}
	}
}
