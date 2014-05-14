package cal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonthPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	protected int month;
	protected int year;
	protected JButton createApptBtn = new JButton("Create Appointment");
	protected JButton prevBtn = new JButton("<-");
	String thisPersonID;

	protected String[] monthNames = { "January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };

	protected String[] dayNames = { "S", "M", "T", "W", "T", "F", "S" };

	public MonthPanel(int month, int year, String PersonID) {
		this.month = month;
		this.year = year;
		thisPersonID = PersonID;

		this.add(createGUI());

	}

	protected JPanel createGUI() {
		createApptBtn.addActionListener(this);

		JPanel monthPanel = new JPanel(true);
		monthPanel.setLayout(new BorderLayout());
		monthPanel.setBackground(Color.WHITE);
		monthPanel.setForeground(Color.BLACK);

		monthPanel.add(createApptBtn);
		monthPanel.add(createTitleGUI(), BorderLayout.NORTH);
		monthPanel.add(createDaysGUI(), BorderLayout.SOUTH);

		return monthPanel;
	}

	protected JPanel createTitleGUI() {
		JPanel titlePanel = new JPanel(true);
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setBackground(Color.WHITE);

		JLabel label = new JLabel(monthNames[month] + " " + year);
		label.setForeground(SystemColor.activeCaption);

		titlePanel.add(label, BorderLayout.CENTER);
		return titlePanel;
	}

	protected JPanel createDaysGUI() {
		JPanel dayPanel = new JPanel(true);
		 JButton prevBtn = new JButton();		 
			prevBtn.setText("<-");	
		dayPanel.setLayout(new GridLayout(0, dayNames.length));
		dayPanel.setBounds(getVisibleRect());
		Calendar today = Calendar.getInstance();
		int tMonth = today.get(Calendar.MONTH);
		int tYear = today.get(Calendar.YEAR);
		int tDay = today.get(Calendar.DAY_OF_MONTH);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		Calendar iterator = (Calendar) calendar.clone();
		iterator.add(Calendar.DAY_OF_MONTH,
				-(iterator.get(Calendar.DAY_OF_WEEK) - 1));

		Calendar maximum = (Calendar) calendar.clone();
		maximum.add(Calendar.MONTH, +1);

		for (int i = 0; i < dayNames.length; i++) {
			JButton dJButton = new JButton();
			dJButton.setText(dayNames[i]);
			dayPanel.add(dJButton);

		}

		int count = 0;
		int limit = dayNames.length * 6;
		ArrayList<ArrayList<String>> appointments = fetchPush
				.fetchAppointments(thisPersonID);
		ArrayList<String> appdates = new ArrayList<String>();
		appdates.addAll(appointments.get(3));
		while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
			JButton b = new JButton();

			int lMonth = iterator.get(Calendar.MONTH);
			int lYear = iterator.get(Calendar.YEAR);

			b.setSize(40, 40);

			String dayLabel;

			if ((lMonth == month) && (lYear == year)) {
				int lDay = iterator.get(Calendar.DAY_OF_MONTH);
				dayLabel = (Integer.toString(lDay));
				for (int x = 0; x < appdates.size(); x++) {
					String appYear, appMonth, appDay;
					int appYeari, appMonthi, appDayi;
					appYear = appointments.get(3).get(x).substring(0, 4);
					appMonth = appointments.get(3).get(x).substring(5, 7);
					appDay = appointments.get(3).get(x).substring(8, 10);
					appYeari = Integer.parseInt(appYear);
					appMonthi = Integer.parseInt(appMonth);
					appMonthi = (appMonthi - 1);
					appDayi = Integer.parseInt(appDay);
					if ((appMonthi == month) && (appYeari == year)
							&& (appDayi == lDay)) {
						b.setBackground(Color.ORANGE);
					}
				}
			} else {
				dayLabel = (" ");
				b.setBackground(Color.WHITE);
			}
			b.setText(dayLabel);
			dayPanel.add(b);
			iterator.add(Calendar.DAY_OF_YEAR, +1);
			count++;
		}

		for (int i = count; i < limit; i++) {
			JButton dPanel = new JButton();
			String dayLabel;
			dayLabel = (" ");
			dPanel.setBackground(Color.WHITE);
			dPanel.setText(dayLabel);
			dayPanel.add(dPanel);
		}

		return dayPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createApptBtn) { // Create Appointment button
												// clicked..
			new CreateAppointment();
		}
	}
}
