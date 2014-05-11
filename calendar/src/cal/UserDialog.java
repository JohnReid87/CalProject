package cal;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UserDialog implements Runnable{
	
	
	
	private JFrame frame;
	private JPanel cards;
	
	private JPanel loginPanel;
	private static JTextField emailLoginTF;
	private static JPasswordField passwdLoginTF;
	private static JProgressBar loginProgress;
	
	private JPanel signupPanel;
	private static JTextField fnameTF;
	private static JTextField lnameTF;
	private static JTextField phoneNumTF;
	private static JTextField emailTF;
	private static JPasswordField passwdTF;
	private static JProgressBar signupProgress;
	
	private JButton loginBtn;
	private JButton signupBtn;
	
	final static String LOGINPANEL = "Log In";
	final static String SIGNUPPANEL = "Sign Up";
	final static ArrayList<JTextField> signupFields = new ArrayList<JTextField>();
	final static ArrayList<JTextField> loginFields = new ArrayList<JTextField>();
	
	@Override
	public void run() {
		
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("");
		frame.setSize(250,300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//build login panel
		loginPanel = new JPanel(new GridLayout(3,2));
		loginPanel.add(new JLabel("Email:"));
		loginPanel.add(emailLoginTF = new JTextField());
		emailLoginTF.getDocument().addDocumentListener(new errorCorrection(emailLoginTF));
		loginFields.add(emailLoginTF);
		
		loginPanel.add(new JLabel("Password:"));
		loginPanel.add(passwdLoginTF = new JPasswordField());
		passwdLoginTF.getDocument().addDocumentListener(new errorCorrection(passwdLoginTF));
		loginFields.add(passwdLoginTF);
		
		loginBtn = new JButton("Login:");
		loginBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				login(emailLoginTF.getText(), passwdLoginTF.getPassword());
				
			}
			
		});
		loginPanel.add(loginBtn);
		loginProgress = new JProgressBar();
		loginProgress.setIndeterminate(true);
		loginProgress.setVisible(false);
		loginPanel.add(loginProgress);
		
		//build sign up panel
		signupPanel = new JPanel(new GridLayout(6,2));							// panel layout
		signupPanel.add(new JLabel("First name:"));				
		signupPanel.add(fnameTF = new JTextField());							// add text field
		fnameTF.getDocument().addDocumentListener(new errorCorrection(fnameTF));// attach document listener
		signupFields.add(fnameTF);												// add textfield to our sign up fields array list. <- makes for easy looping afterwards
		
		signupPanel.add(new JLabel("Last name:"));
		signupPanel.add(lnameTF = new JTextField());
		lnameTF.getDocument().addDocumentListener(new errorCorrection(lnameTF));
		signupFields.add(lnameTF);
		
		signupPanel.add(new JLabel("Phone Number:"));
		signupPanel.add(phoneNumTF = new JTextField());
		phoneNumTF.getDocument().addDocumentListener(new errorCorrection(phoneNumTF));
		signupFields.add(phoneNumTF);
		
		signupPanel.add(new JLabel("Email:"));
		signupPanel.add(emailTF = new JTextField());
		emailTF.getDocument().addDocumentListener(new errorCorrection(emailTF));
		signupFields.add(emailTF);
		
		signupPanel.add(new JLabel("Password:"));
		signupPanel.add(passwdTF = new JPasswordField());
		passwdTF.getDocument().addDocumentListener(new errorCorrection(passwdTF));
		signupFields.add(passwdTF);
		
		signupBtn = new JButton("Sign up!");
		signupBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				signup();
				
			}
			
		});
		signupPanel.add(signupBtn);
		signupProgress = new JProgressBar();
		signupProgress.setIndeterminate(true);
		signupProgress.setVisible(true);			/////
		signupPanel.add(signupProgress);
		
		//build the card handling panel
		cards = new JPanel(new CardLayout(15,15));
		cards.add(loginPanel, LOGINPANEL);
		cards.add(signupPanel, SIGNUPPANEL);

		//
		JPanel cbPanel = new JPanel(new FlowLayout());
		String cbItems[] = {LOGINPANEL,SIGNUPPANEL};
		JComboBox cb = new JComboBox(cbItems);
		cb.setEditable(false);
		cb.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				CardLayout cl = (CardLayout) cards.getLayout();
				cl.show(cards, (String)e.getItem());
			}
			
		});
		cbPanel.add(new JLabel("<html><b>Welcome!</b></html>"));
		cbPanel.add(cb);
		cbPanel.add(new JSeparator());
		
		frame.add(cbPanel,BorderLayout.NORTH);
		frame.add(cards, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
		
	}
	User usr;
	public void login(String email, char[] pw) {
		
		boolean error = false;
		
		for( int i=0; i< loginFields.size(); i++){
			if(loginFields.get(i) != null){
				if(loginFields.get(i).getText().length() <= 3 ){
					loginFields.get(i).setBorder(BorderFactory.createLineBorder(Color.RED));
					error = true;
				}
			}
		}
		
		if(error != true){
			int res;
			res = fetchPush.login(email, pw.toString());
			if(!(res < 0)) // all good continue
				usr = new User(res);
		}
		
	}
	
	public void signup(){
		
		boolean error = false;
	
		//loop through TextFields array
		for(int i=0; i < signupFields.size(); i++){
			if(signupFields.get(i) != null){
				if(signupFields.get(i).getText().length() <= 3 ){
					signupFields.get(i).setBorder(BorderFactory.createLineBorder(Color.RED));
					error = true;
				}
			}
		}
		
		if(error != true){
			fetchPush.signup(fnameTF.getText(),
							lnameTF.getText(),
							phoneNumTF.getText(),
							emailTF.getText(),
							passwdTF.toString());
		}
	}
	public void signup(String fname, String lname, String phoneNum, String email, char[] pw){
		
		String passwd = pw.toString();
		fetchPush.signup(fname, lname, phoneNum, email, passwd);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new UserDialog());

	}

	private static class errorCorrection implements DocumentListener{

		JTextField textField;
		
		public errorCorrection(JTextField tf){
			this.textField = tf;
		}
		
		@Override
		public void insertUpdate(DocumentEvent e) {
			textField.setBorder( UIManager.getBorder("TextField.border" ));
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			textField.setBorder( UIManager.getBorder("TextField.border" ));
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			textField.setBorder( UIManager.getBorder("TextField.border" ));
			
		}
		
	}

}
