package Login;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import studentSearch.EmployeeSearchApp;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField User_txt;
	private JTextField pass_txt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("DIT Staff Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 100, 850, 400);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		User_txt = new JTextField();
		User_txt.setBounds(218, 66, 130, 26);
		contentPane.add(User_txt);
		User_txt.setColumns(10);
		
		pass_txt = new JTextField();
		pass_txt.setBounds(218, 133, 130, 26);
		contentPane.add(pass_txt);
		pass_txt.setColumns(10);
		
		JLabel User_lbl = new JLabel("Username");
		User_lbl.setBounds(86, 71, 76, 16);
		contentPane.add(User_lbl);
		
		JLabel pass_lbl = new JLabel("Password");
		pass_lbl.setBounds(86, 138, 76, 16);
		contentPane.add(pass_lbl);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = User_txt.getText();
				String password = pass_txt.getText();
				if (username.equals("Admin") && password.equals("password")){
					MainMenu menu = new MainMenu();
					menu.setVisible(true);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Wrong username and passowrd");
					User_txt.setText("");
					pass_txt.setText("");
					User_txt.requestFocus();
					
					
					
					
				}
			}
		});
		loginbtn.setBounds(218, 215, 117, 29);
		contentPane.add(loginbtn);
		
		JLabel Ditlbl = new JLabel("New label");
		Image img = new ImageIcon(this.getClass().getResource("/DIT.png")).getImage();
		Ditlbl.setIcon(new ImageIcon(img));
		Ditlbl.setBounds(447, 44, 187, 162);
		contentPane.add(Ditlbl);
	}
}
