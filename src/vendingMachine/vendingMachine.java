package vendingMachine;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class vendingMachine extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTF;
	private JPasswordField passwordTF;

	//Main Method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vendingMachine frame = new vendingMachine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructor Method
	public vendingMachine() {
		initUI();
	}
	//Create applications layout in initUI
	public void initUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel adminpanel = new JPanel();
		adminpanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Admin", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		adminpanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		adminpanel.setBounds(143, 267, 320, 138);
		contentPane.add(adminpanel);
		adminpanel.setLayout(null);
		
		JLabel username = new JLabel("Username: ");
		username.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		username.setBounds(10, 21, 85, 20);
		adminpanel.add(username);
		
		JLabel password = new JLabel("Password: ");
		password.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		password.setBounds(10, 59, 85, 15);
		adminpanel.add(password);
		
		passwordTF = new JPasswordField();
		passwordTF.setBounds(89, 59, 85, 20);
		adminpanel.add(passwordTF);
		
		usernameTF = new JTextField();
		usernameTF.setBounds(89, 24, 85, 20);
		adminpanel.add(usernameTF);
		usernameTF.setColumns(10);
		
		JButton login = new JButton("Login");
		login.setBackground(new Color(38, 84, 124));
		login.setBorderPainted(false);
		login.setBounds(89, 90, 89, 23);
		adminpanel.add(login);
		
		JButton reset = new JButton("Reset");
		reset.setBackground(new Color(38, 84, 124));
		reset.setBorderPainted(false);
		reset.setBounds(221, 23, 89, 23);
		adminpanel.add(reset);
		
		JButton exit = new JButton("Exit");
		exit.setBackground(new Color(38, 84, 124));
		exit.setBorderPainted(false);
		exit.setBounds(221, 58, 89, 23);
		adminpanel.add(exit);
	}
}
