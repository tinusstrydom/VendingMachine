package vendingMachine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import vendingMachine.Admin;


public class VendingMachine extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTF;
	private JPasswordField passwordTF;

	//Main Method
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VendingMachine frame = new VendingMachine();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Constructor Method
	public VendingMachine() {
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
		adminpanel.setBounds(154, 312, 320, 138);
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
		
		JButton loginbtn = new JButton("Login");
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTF.getText();
				String password = passwordTF.getText();
				
				if(username.contains("Tinus") && password.contains("one")) {
					usernameTF.setText(null);
					passwordTF.setText(null);
					Admin admin = new Admin();
					admin.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Invalid login details", "Login Error",JOptionPane.ERROR_MESSAGE);
					usernameTF.setText(null);
					passwordTF.setText(null);
				}
			}
		});
		loginbtn.setBackground(new Color(38, 84, 124));
		loginbtn.setBorderPainted(false);
		loginbtn.setBounds(89, 90, 89, 23);
		adminpanel.add(loginbtn);
		
		JButton resetbtn = new JButton("Reset");
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameTF.setText(null);
				passwordTF.setText(null);
			}
		});
		resetbtn.setBackground(new Color(38, 84, 124));
		resetbtn.setBorderPainted(false);
		resetbtn.setBounds(221, 23, 89, 23);
		adminpanel.add(resetbtn);
		
		JButton exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitbtn.setBackground(new Color(38, 84, 124));
		exitbtn.setBorderPainted(false);
		exitbtn.setBounds(221, 58, 89, 23);
		adminpanel.add(exitbtn);
	}
}
