package cal;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CalendarFrame implements Runnable {

	private JFrame frame;

	@Override
	public void run() {
		// Month is zero based
		Calendar ca = Calendar.getInstance();
		DateFormat curYear = new SimpleDateFormat("yyyy");
		DateFormat curMonth = new SimpleDateFormat("MM");
		
		String strMonth, strYear;
		strMonth = curMonth.format(ca.getTime());
		strYear = curYear.format(ca.getTime());
		int month, year;
		month = Integer.valueOf(strMonth) - 1;
		year = Integer.valueOf(strYear);
		MonthPanel panel = new MonthPanel(month, year);

		frame = new JFrame();
		frame.setTitle("Calendar");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

		frame.setLayout(new FlowLayout());
		frame.add(panel);
		frame.pack();
		frame.setBounds(400, 400, 400, 400);
		frame.setVisible(true);
	}

	public void exitProcedure() {
		frame.dispose();
		System.exit(0);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new CalendarFrame());

	}

}