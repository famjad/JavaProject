package studentSearch;
/*
 * This is the Main Menu class, it will take it to the search tool and it also gives a user option to terminate the program
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Studentdao.StudentSQL;

import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;
	private StudentSQL studentdoa;
	private SearchStudent studentsearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 100, 850, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton delbtn = new JButton("Exit the Program");
		delbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		delbtn.setBounds(23, 238, 117, 47);
		contentPane.add(delbtn);
		
		JButton srchbtn = new JButton("Search Students");
		srchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchStudent stsearch = new SearchStudent();
				stsearch.setVisible(true);
				
			}
		});
		srchbtn.setBounds(23, 117, 117, 47);
		contentPane.add(srchbtn);
		
		
	}

}
