package cal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CalendarFrame implements Runnable {

	private static JFrame frame;
	private static JPanel cards;
	
	
	@Override
	public void run() {
		
		
		// Month is zero based
		Calendar ca = Calendar.getInstance();
		DateFormat curYear = new SimpleDateFormat("yyyy");
		DateFormat curMonth = new SimpleDateFormat("MM");
		
		String strMonth, strYear;
		strMonth = curMonth.format(ca.getTime());
		strYear = curYear.format(ca.getTime());
		int month;
		int year;
		month = Integer.valueOf(strMonth) - 1;
		year = Integer.valueOf(strYear);
		int PersonID = User.getUserId();
		String IDString = String.valueOf(PersonID);
		MonthPanel panel = new MonthPanel(month, year, IDString);
		//WeekPanel weeklyPanel = new WeekPanel(); ///////////
		
		
		frame = new JFrame();
		frame.setTitle("Calendar");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});

	
		//card handling panel
		//cards = new JPanel(new CardLayout());
		//cards.add(panel, C.MONTHLY_VIEW);
		//cards.add(weeklyPanel, C.WEEKLY_VIEW);
		
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setBounds(400, 400, 400, 400);
		frame.setVisible(true);
	}
	
	public static void setVisibleView(String viewTag){
		
		CardLayout cl = (CardLayout) cards.getLayout();
		switch(viewTag){
		
		case C.MONTHLY_VIEW:
				cl.show(cards, C.MONTHLY_VIEW);
				break;
		case C.WEEKLY_VIEW:
				cl.show(cards, C.WEEKLY_VIEW);
				break;
		case C.DAILY_VIEW:
				cl.show(cards, C.DAILY_VIEW);
				break;
		}
	}
	

	public static void exitProcedure() {
		frame.dispose();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new CalendarFrame());

	}

}