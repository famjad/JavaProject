package studentSearch;


import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import student.MainStudent;
import Studentdao.StudentSQL;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField emailText;
	private JTextField courseText;

	private StudentSQL studentDAO;

	private SearchStudent studentsearch;

	private MainStudent previousEmployee = null;

	public StudentDialog(SearchStudent theStudent,
			StudentSQL ThestudenDAO, MainStudent thePreviousStudent) {
		this();
		studentDAO = ThestudenDAO;
		studentsearch = theStudent;

		previousEmployee = thePreviousStudent;
		}
		
		

	public StudentDialog(SearchStudent theEmployeeSearchApp,
			StudentSQL theEmployeeDAO) {
		this(theEmployeeSearchApp, theEmployeeDAO, null);
	}

	/**
	 * Create the dialog.
	 */
	public StudentDialog() {
		setTitle("Add Student");
		setBounds(100, 100, 450, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));
		{
			JLabel lblFirstName = new JLabel("First Name");
			contentPanel.add(lblFirstName, "2, 2, right, default");
		}
		{
			firstNameText = new JTextField();
			contentPanel.add(firstNameText, "4, 2, fill, default");
			firstNameText.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("Last Name");
			contentPanel.add(lblLastName, "2, 4, right, default");
		}
		{
			lastNameText = new JTextField();
			contentPanel.add(lastNameText, "4, 4, fill, default");
			lastNameText.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("Email");
			contentPanel.add(lblNewLabel, "2, 6, right, default");
		}
		{
			emailText = new JTextField();
			contentPanel.add(emailText, "4, 6, fill, default");
			emailText.setColumns(10);
		}
		{
			JLabel courselbl = new JLabel("Course");
			contentPanel.add(courselbl, "2, 8, right, default");
		}
		{
			courseText = new JTextField();
			contentPanel.add(courseText, "4, 8, fill, default");
			courseText.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveStudenttoRecord();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}



	protected void saveStudenttoRecord() {

		// get the employee info from gui
		String firstName = firstNameText.getText();
		String lastName = lastNameText.getText();
		String email = emailText.getText();

		String course = courseText.getText();

		MainStudent student = new MainStudent(lastName,
				firstName, email, course);



		try {
			
			studentDAO.addStudent(student);
			

			// close dialog
			setVisible(false);
			dispose();

			// refresh gui list
			studentsearch.Studentlistrefresh();

			// show success message
			JOptionPane.showMessageDialog(studentsearch,
					"Student Recorcd saved succesfully.", "Student Record",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(studentsearch,
					"Error saving student: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
