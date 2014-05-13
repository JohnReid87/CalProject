package cal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class WeekPanel extends JPanel implements ActionListener {


	private static final long serialVersionUID = 1L;
	
	private JPanel 	ctrlPanel;
	private JPanel 	gridPanel;
	private JButton createApptBtn;
	private JButton monthViewBtn;
	private JButton prevWeekBtn;
	private JButton nextWeekBtn;
	private JButton optionsBtn;
	
	private JTable weeklyTable;
	private JScrollPane scrollPane;
	
	public WeekPanel(){
		setup(new Date());////
		buildUI();

	}
	
	/** Calculate first, and last Date of needed month
	 * @param currentDate
	 */
	private static String begining;
	private static String end;
 
	public static void setup(Date currentDate) {
		
	
		SimpleDateFormat dayF = new SimpleDateFormat("EEEE-MMMM-dd-yyyy");
		
		//	figure out month's start date and day
	  	Calendar calendarStart = getCalendarForDate(currentDate);
		calendarStart.set(Calendar.DAY_OF_MONTH, calendarStart.getActualMinimum(Calendar.DAY_OF_MONTH));
		setTimeToBeginningOfDay( calendarStart );
		begining = dayF.format(calendarStart.getTime());

		// figure out end date/day
		Calendar calendarEnd = getCalendarForDate(currentDate);
		calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
		setTimeToEndOfDay( calendarEnd);
		end = dayF.format(calendarEnd.getTime());

		
}
		
	private static void setTimeToBeginningOfDay(Calendar calendar) {
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	}

	private static void setTimeToEndOfDay(Calendar calendar) {
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    calendar.set(Calendar.MILLISECOND, 999);
	}
	
	private static Calendar getCalendarForDate(Date currentDate) {
	    Calendar calendar = GregorianCalendar.getInstance();
	    calendar.setTime(currentDate);
	    return calendar;
	}

	
	protected JPanel buildUI() {
		
		this.setLayout(new BorderLayout());
				
		// build top panel
		ctrlPanel = new JPanel( new FlowLayout());
		
		createApptBtn = new JButton("Create Appt");
		createApptBtn.addActionListener(this);
		ctrlPanel.add(createApptBtn);
		
		monthViewBtn = new JButton("Monthly View");
		monthViewBtn.addActionListener(this);
		ctrlPanel.add(monthViewBtn);
		
		prevWeekBtn = new JButton("Prev Week");
		prevWeekBtn.addActionListener(this);
		ctrlPanel.add(prevWeekBtn);
		
		nextWeekBtn = new JButton("Next Week");
		nextWeekBtn.addActionListener(this);
		ctrlPanel.add(nextWeekBtn);
		
		optionsBtn = new JButton("Options");
		optionsBtn.addActionListener(this);
		ctrlPanel.add(optionsBtn);
		this.add(ctrlPanel, BorderLayout.NORTH);
		
		this.add(buildTable(), BorderLayout.CENTER);
		return this;
	
		
	}
	//priv JScrollPane
	private JPanel buildTable() {
		/**
		JScrollPane scrollPane = new JScrollPane();
	    JTable table;

	    table = new JTable( 24 ,8);
	    table.setModel(new DefaultTableModel());
	    table.setFillsViewportHeight( true );
	    scrollPane.setViewportView( table );
	    
	    return scrollPane;

	    //Declaring compound-renderer
	///    table.setDefaultRenderer( CellLabelRenderer.class, new CellLabelRenderer() );
	 * 
	 */
		String[] weekDays = { "Sunday","Monday","Tuesday", "Wednesday","Thursday","Friday","Saturday" };
		String begininDay = "";
		String endDay = "";
		
			JPanel gridTablePanel = new JPanel(new GridLayout(0, weekDays.length ,0,0));
			gridTablePanel.setBounds(getVisibleRect());
			
			for( int d=0; d < weekDays.length; d++){
				
				JButton dayBtn = new JButton();
				dayBtn.setPreferredSize(new Dimension(40,40));
				if( weekDays[d].length() < 6){
					gridTablePanel .add(new JButton(weekDays[d].substring(0, weekDays[d].length() - 5)));
				}else{
					gridTablePanel.add(new JButton(weekDays[d].substring(0, weekDays[d].length() - 3 )));
				}
				
				//set begining/end days
				if( begining.contains(weekDays[d]))
					begininDay = weekDays[d];
				
				if( end.contains(weekDays[d]))
					endDay = weekDays[d];
			}
			
			
			for( int i=0; i < 31; i++){
				
				while(! begininDay.equalsIgnoreCase(weekDays[i])){
					JLabel lb = new JLabel();
				}
				JLabel lbl = new JLabel("APP");
				
				lbl.setPreferredSize(new Dimension(40,40));
				gridTablePanel.add(lbl);
			}
			
			return gridTablePanel;
	  }
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if( e.getSource() == createApptBtn){
			new CreateAppointment();
		}else if(e.getSource() == monthViewBtn){
			CalendarFrame.setVisibleView("MONTHVIEW");
		}
	}
	
}
