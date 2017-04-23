package studentSearch;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import studentSearch.Menu;

import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import java.util.List;

import student.MainStudent;
import Studentdao.StudentSQL;

public class SearchStudent extends JFrame {

	private JPanel contentPane;
	private JTextField lastNameTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;
	private StudentSQL studentDAO;
	private JPanel panel_1;
	private JButton btnAddstudent;
	
	private JButton btnDeleteStudent;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchStudent frame = new SearchStudent();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchStudent() {
		
		// create the Student DAO
		try {
			studentDAO = new StudentSQL();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("DIT Student Search Tool");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 100, 850, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblEnterLastName = new JLabel("Enter last name");
		panel.add(lblEnterLastName);
		
		lastNameTextField = new JTextField();
		panel.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get student from the last name

				// If last name is empty, then get all student

				// Print out student				
				
				try {
					String lastName = lastNameTextField.getText();

					List<MainStudent> student = null;

					if (lastName != null && lastName.trim().length() > 0) {
						student = studentDAO.searchEmployees(lastName);
					} else {
						student = studentDAO.getStudents();
					}
					
					// create the model and update the "table"
					StudentTable model = new StudentTable(student);
					
					table.setModel(model);
					
				
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(SearchStudent.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel.add(btnSearch);
		JButton btnClear = new JButton("Clear Search");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastNameTextField.setText("");
			}
		});
		panel.add(btnClear);
		
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnAddstudent = new JButton("Add Student");
		btnAddstudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create dialog
				StudentDialog dialog = new StudentDialog(SearchStudent.this, studentDAO);

				// show dialog
				dialog.setVisible(true);
			}
		});
		panel_1.add(btnAddstudent);
		

	
		
		btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					// get the selected row
					int row = table.getSelectedRow();

					// make sure a row is selected
					if (row < 0) {
						JOptionPane.showMessageDialog(SearchStudent.this, 
								"You must select an Student from the List", "Error", JOptionPane.ERROR_MESSAGE);				
						return;
					}

					// prompt the user
					int response = JOptionPane.showConfirmDialog(
							SearchStudent.this, "Do you want to delete this Student from the Student Record?", "Confirm", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (response != JOptionPane.YES_OPTION) {
						return;
					}

					// get the current student
					MainStudent tempStudent = (MainStudent) table.getValueAt(row, StudentTable.OBJECT_COL);

					// delete the student
					studentDAO.delStudent(tempStudent.getStudentid());

					// refresh GUI
					Studentlistrefresh();

					// show success message
					JOptionPane.showMessageDialog(SearchStudent.this,
							"Student deleted succesfully.", "Student Deleted",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (Exception exc) {
					JOptionPane.showMessageDialog(SearchStudent.this,
							"Error deleting Student: " + exc.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				
			}
		});
		panel_1.add(btnDeleteStudent);
		JButton btnBackToMain = new JButton("Back to Main Menu");
		panel_1.add(btnBackToMain);
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});
	}

	public void Studentlistrefresh() {

		try {
			List<MainStudent> student = studentDAO.getStudents();

			// create the model and update the "table"
			StudentTable model = new StudentTable(student);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
