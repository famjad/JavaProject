package student;



/**
 * 
 * @author Fahad Amjad
 *
 */
public class MainStudent {
//declare variables
	private int studentid;
	private String lastName;
	private String firstName;
	private String email;
	private String course;
	public MainStudent(String lastName, String firstName, String email,
			String course) {

		this(0, lastName, firstName, email, course);
	}
	//constructor
	public MainStudent(int id, String lastName, String firstName, String email,
			String course) {
		super();
		this.studentid = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.course = course;
	}
//getter and setter
	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}




	@Override
	public String toString() {
		return String
				.format("Student [studentid=%s, lastName=%s, firstName=%s, email=%s, course=%s]",
						studentid, lastName, firstName, email, course);
	}
	
	
		
}
