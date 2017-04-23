package Studentdao;

import java.util.*;
import java.sql.*;
import java.io.*;

import student.MainStudent;

/**
 * This class will setup the SQL for the student, it will contain SQL Queries.
 * @author Fahad Amjad
 *
 */
public class StudentSQL {

	private Connection myConn;
	
	public StudentSQL() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("student.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	public void delStudent(int studentID) throws SQLException {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from Student where studentid=?");
			
			// set param
			myStmt.setInt(1, studentID);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
	}
	

	
	public void addStudent(MainStudent theStudent) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into Student"
					+ " (firstname, lastname, email, course)"
					+ " values (?, ?, ?, ?)");
			
			// set params
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setString(4, theStudent.getCourse());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	
	public List<MainStudent> getStudents() throws Exception {
		List<MainStudent> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from Student order by lastname");
			
			while (myRs.next()) {
				MainStudent tempStudent = convertRowToStudent(myRs);
				list.add(tempStudent);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<MainStudent> searchStudent(String lastName) throws Exception {
		List<MainStudent> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			lastName += "%";
			myStmt = myConn.prepareStatement("select * from Student where lastname like ?  order by lastname");
			
			myStmt.setString(1, lastName);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				MainStudent tempStudent = convertRowToStudent(myRs);
				list.add(tempStudent);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private MainStudent convertRowToStudent(ResultSet myRs) throws SQLException {
		
		int studentid = myRs.getInt("studentid");
		String lastName = myRs.getString("lastname");
		String firstName = myRs.getString("firstname");
		String email = myRs.getString("email");
		String course = myRs.getString("course");
		
		MainStudent tempStudent = new MainStudent(studentid, lastName, firstName, email, course);
		
		return tempStudent;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
	
	public static void main(String[] args) throws Exception {
		
		StudentSQL dao = new StudentSQL();
		System.out.println(dao.searchStudent("Alan"));

		System.out.println(dao.getStudents());
	}

}
