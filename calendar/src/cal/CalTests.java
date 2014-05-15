package cal;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

public class CalTests {

	static String URL = "jdbc:mysql://itsuite.it.bton.ac.uk/bb136";
	static String username = "bb136";
	static String password = "bb136";
	
	ArrayList<String> testRes = new ArrayList<String>();
	ArrayList<ArrayList<String>> testAppRes = new ArrayList<ArrayList<String>>();
	
	String testFirstName = "Joe";
	String testLastName = "Bloggs";
	String testTelNo = "01273595595";
	String testEmail = "j.bloggs@mail.com";
	String testPass = "password1";
	int testPassInt = testPass.hashCode();
	String testPassHash = String.valueOf(testPassInt);
	String testRoom = "101";
	String testTime = "10:00:00";
	String testDate = "20140730";
	String testDateRes = "2014-07-30";
	String testNote = "This is a test note.";
	String testAtt1 = "14";
	String testAtt2 = "15";
	String testAppID;
	int testDur = 1;
	String testDurString = String.valueOf(testDur);
	int testID;
	String testIDString;
	
	
	public ArrayList<String> makeAttendees(String att1, String att2){
		ArrayList<String> testAttendees = new ArrayList<String>();
		testAttendees.add(att1);
		testAttendees.add(att2);
		return testAttendees;
	}
	

	@Test
	public void testSignupAndLogin() {
		fetchPush.signup(testFirstName, testLastName, testTelNo, testEmail, testPass);
		testRes = fetchPersonData(testEmail);
		String firstName = testRes.get(1);
		String lastName = testRes.get(2);
		String telNo = testRes.get(3);
		String email = testRes.get(4);
		String userPass = testRes.get(5);
		assertEquals("Data is: " + firstName + "Expected: " + testFirstName, testFirstName, firstName);
		assertEquals("Data is: " + lastName + "Expected: " + testLastName, testLastName, lastName);
		assertEquals("Data is: " + telNo + "Expected: " + testTelNo, testTelNo, telNo);
		assertEquals("Data is: " + email + "Expected: " + testEmail, testEmail, email);
		assertEquals("Data is: " + userPass + "Expected: " + testPassHash, testPassHash, userPass);
		String ok = fetchPush.login(testEmail, testPass);	
		assertTrue(ok!="error");
		deletePerson(testEmail);
	}

	
	@Test
	public void testInvAttendees(){		
		testIDString = fetchPush.signup(testFirstName, testLastName, testTelNo, testEmail, testPass);
		testAppID = fetchPush.addAppointment(testIDString, testRoom, testTime, testDate, testDur, testNote, makeAttendees(testAtt1, testAtt2));
		testAppRes = fetchPush.fetchInvitations(testAtt1);
		
		String appRoom = testAppRes.get(1).get(0);
		String appTime = testAppRes.get(2).get(0);
		String appDate = testAppRes.get(3).get(0);
		String appDur = testAppRes.get(4).get(0);
		String appNote = testAppRes.get(5).get(0);
		
		assertEquals(testRoom, appRoom);
		assertEquals(testTime, appTime);
		assertEquals(testDateRes, appDate);
		assertEquals(testDurString, appDur);
		assertEquals(testNote, appNote);
		
		fetchPush.deleteAppointment(testAppID);
		testAppRes = fetchPush.fetchAppointments(testIDString);
		
		assertTrue("Size is: " + testAppRes.get(1).size() + ", expected: 0",testAppRes.get(1).size() == 0); //checks nothing in room array
		assertTrue("Size is: " + testAppRes.get(2).size() + ", expected: 0",testAppRes.get(2).size() == 0); //checks nothing in time array
		assertTrue("Size is: " + testAppRes.get(3).size() + ", expected: 0",testAppRes.get(3).size() == 0); //checks nothing in date array
		assertTrue("Size is: " + testAppRes.get(4).size() + ", expected: 0",testAppRes.get(4).size() == 0); //checks nothing in duration array
		assertTrue("Size is: " + testAppRes.get(5).size() + ", expected: 0",testAppRes.get(5).size() == 0); //checks nothing in note array
		
		deletePerson(testEmail);
	}

	
	@Test
	public void testAddAndDelApp(){
		testIDString = fetchPush.signup(testFirstName, testLastName, testTelNo, testEmail, testPass);
		testAppID = fetchPush.addAppointment(testIDString, testRoom, testTime, testDate, testDur, testNote, makeAttendees(testAtt1, testAtt2));
		testAppRes = fetchPush.fetchAppointments(testIDString);
		
		String appRoom = testAppRes.get(1).get(0);
		String appTime = testAppRes.get(2).get(0);
		String appDate = testAppRes.get(3).get(0);
		String appDur = testAppRes.get(4).get(0);
		String appNote = testAppRes.get(5).get(0);
		
		assertEquals(testRoom, appRoom);
		assertEquals(testTime, appTime);
		assertEquals(testDateRes, appDate);
		assertEquals(testDurString, appDur);
		assertEquals(testNote, appNote);
		
		fetchPush.deleteAppointment(testAppID);
		testAppRes = fetchPush.fetchAppointments(testIDString);
		
		assertTrue("Size is: " + testAppRes.get(1).size() + ", expected: 0",testAppRes.get(1).size() == 0); //checks nothing in room array
		assertTrue("Size is: " + testAppRes.get(2).size() + ", expected: 0",testAppRes.get(2).size() == 0); //checks nothing in time array
		assertTrue("Size is: " + testAppRes.get(3).size() + ", expected: 0",testAppRes.get(3).size() == 0); //checks nothing in date array
		assertTrue("Size is: " + testAppRes.get(4).size() + ", expected: 0",testAppRes.get(4).size() == 0); //checks nothing in duration array
		assertTrue("Size is: " + testAppRes.get(5).size() + ", expected: 0",testAppRes.get(5).size() == 0); //checks nothing in note array		
	
		deletePerson(testEmail);
	}
	
	@Test
	public void testAcceptInv(){
		testIDString = fetchPush.signup(testFirstName, testLastName, testTelNo, testEmail, testPass);
		testAppID = fetchPush.addAppointment(testIDString, testRoom, testTime, testDate, testDur, testNote, makeAttendees(testAtt1, testAtt2));
		fetchPush.acceptAppointment(testAppID, testAtt1);
		
		testAppRes = fetchPush.fetchInvitations(testAtt1);
		
		assertTrue("Size is: " + testAppRes.get(1).size() + ", expected: 0",testAppRes.get(1).size() == 0); //checks nothing in room array
		assertTrue("Size is: " + testAppRes.get(2).size() + ", expected: 0",testAppRes.get(2).size() == 0); //checks nothing in time array
		assertTrue("Size is: " + testAppRes.get(3).size() + ", expected: 0",testAppRes.get(3).size() == 0); //checks nothing in date array
		assertTrue("Size is: " + testAppRes.get(4).size() + ", expected: 0",testAppRes.get(4).size() == 0); //checks nothing in duration array
		assertTrue("Size is: " + testAppRes.get(5).size() + ", expected: 0",testAppRes.get(5).size() == 0); //checks nothing in note array		
		
		fetchPush.deleteAppointment(testAppID);
	}
	
	public static void deletePerson(String email){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(URL, username,
					password);
			String deleteStatement = "DELETE FROM PERSON WHERE email = "
					+ "'" + email + "'";
			System.out.println(deleteStatement);
			Statement fsmt = con.createStatement();
			fsmt.executeUpdate(deleteStatement);
			con.close();
		} catch (SQLException | InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
			
	}

	public static ArrayList<String> fetchPersonData(String email) {
		
		ArrayList<String> results = new ArrayList<String>();
		
		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(URL, username,
					password);
			String fetchSessionID = "SELECT * FROM PERSON WHERE email =  "
					+ "'" + email + "'";
			Statement fsmt = con.createStatement();
			ResultSet res = fsmt.executeQuery(fetchSessionID);
			
			while (res.next()) {
				results.add(res.getString(1));
				results.add(res.getString(2));
				results.add(res.getString(3));
				results.add(res.getString(4));
				results.add(res.getString(5));
				results.add(res.getString(6));

			}
			con.close();
		} catch (SQLException | InstantiationException | IllegalAccessException

		| ClassNotFoundException e) {

			e.printStackTrace();

		}
		return results;
	}
}
