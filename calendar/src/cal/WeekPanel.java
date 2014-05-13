package cal;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

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
	
	public WeekPanel(){
		buildUI();
	}
	
	protected JPanel buildUI() {
		
		this.setLayout(new GridLayout(2,0));
		
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
		this.add(ctrlPanel);
		
		// build and add table
		this.add(buildTable());
		
		return this;
	
		
	}
	
	private JScrollPane buildTable() {
		
		JScrollPane scrollPane = new JScrollPane();
	    JTable table;

	    table = new JTable( 24 ,8);
	    table.setModel(new DefaultTableModel());
	    table.setFillsViewportHeight( true );
	    scrollPane.setViewportView( table );
	    
	    return scrollPane;

	    //Declaring compound-renderer
	///    table.setDefaultRenderer( CellLabelRenderer.class, new CellLabelRenderer() );
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
