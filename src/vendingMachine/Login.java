package vendingMachine;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

public class Login extends JFrame{
	
	static Point currCoord;
	private JPanel contentPane;
	private JTextField usernameTF;
	private JPasswordField passwordTF;
	
	public Login() {
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 420, 160);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(5, 110, 115));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//TOP PANEL
		//Customization for top panel 
		JPanel topPanel = new JPanel();
		topPanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		topPanel.setBackground(new Color(5, 110, 115));
		topPanel.setBounds(0, 0, 420, 37);
		topPanel.setLayout(null);
		getContentPane().add(topPanel);
						
		//Mouse event listener and Mouse Motion listener added to move vending machine frame onscreen
		currCoord = null;
		topPanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				currCoord = null;
			}
			public void mousePressed(MouseEvent e) {
				currCoord = e.getPoint();
			}
			public void mouseExited(MouseEvent e) {
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseClicked(MouseEvent e) {
			}
		});
						
		topPanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				setLocation(currCoords.x - currCoord.x, currCoords.y - currCoord.y);
			}
		});
				
		//Vending machine label
		JLabel toplabel = new JLabel("Login");
		toplabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		toplabel.setForeground(Color.WHITE);
		toplabel.setBounds(15, 6, 218, 29);
		topPanel.add(toplabel);
					
		//Minimize button 
		JButton minimize = new JButton("__");
		minimize.setMargin(new Insets(2, 3, 2, 3));
		minimize.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		minimize.setFocusPainted(false);
		minimize.setPreferredSize(new Dimension(25, 25));
		minimize.setBorderPainted(false);
		minimize.setMinimumSize(new Dimension(25, 25));
		minimize.setMaximumSize(new Dimension(25, 25));
		minimize.setForeground(Color.WHITE);
		minimize.setBackground(new Color(210, 61, 39));
		minimize.setBounds(354, 6, 25, 25);		
		//Minimize action listerner
		minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		topPanel.add(minimize);
								
		JButton close = new JButton("X");
		close.setMargin(new Insets(2, 5, 2, 5));
		close.setFocusPainted(false);
		close.setPreferredSize(new Dimension(25, 25));
		close.setMaximumSize(new Dimension(25, 25));
		close.setMinimumSize(new Dimension(25, 25));
		close.setForeground(Color.WHITE);
		close.setBorderPainted(false);
		close.setBackground(new Color(210, 61, 39));
		close.setBounds(385, 6, 25, 25);
		close.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));	
		//Close screen Action listerner
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		topPanel.add(close);
		
		JPanel adminpanel = new JPanel();
		adminpanel.setBackground(new Color(32, 32, 32));
		adminpanel.setForeground(Color.WHITE);
		adminpanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.WHITE));
		adminpanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		adminpanel.setBounds(7, 49, 406, 94);
		contentPane.add(adminpanel);
		adminpanel.setLayout(null);
		
		JLabel username = new JLabel("Username: ");
		username.setForeground(Color.WHITE);
		username.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		username.setBounds(10, 18, 85, 20);
		adminpanel.add(username);
		
		JLabel password = new JLabel("Password: ");
		password.setForeground(Color.WHITE);
		password.setFont(new Font("Segoe UI Semibold", Font.BOLD, 15));
		password.setBounds(10, 56, 85, 15);
		adminpanel.add(password);
		
		passwordTF = new JPasswordField();
		passwordTF.setBounds(89, 56, 85, 20);
		adminpanel.add(passwordTF);
		
		usernameTF = new JTextField();
		usernameTF.setBounds(89, 21, 85, 20);
		adminpanel.add(usernameTF);
		usernameTF.setColumns(10);
		
		JButton loginbtn = new JButton("Login");
		loginbtn.setForeground(Color.WHITE);
		loginbtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTF.getText();
				char[] password = passwordTF.getPassword();
				char[] correctPass = {'o','n','e'};
				
				if(username.contains("Tinus") && Arrays.equals(password, correctPass)) {
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
		loginbtn.setBackground(new Color(5, 110, 115));
		loginbtn.setBorderPainted(false);
		loginbtn.setBounds(184, 20, 89, 56);
		adminpanel.add(loginbtn);
		
		JButton resetbtn = new JButton("Reset");
		resetbtn.setForeground(Color.WHITE);
		resetbtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernameTF.setText(null);
				passwordTF.setText(null);
			}
		});
		resetbtn.setBackground(new Color(5, 110, 115));
		resetbtn.setBorderPainted(false);
		resetbtn.setBounds(298, 20, 89, 23);
		adminpanel.add(resetbtn);
		
		JButton exitbtn = new JButton("Exit");
		exitbtn.setForeground(Color.WHITE);
		exitbtn.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendingMachine vMachine = new VendingMachine();
				vMachine.setVisible(true);
				dispose();
			}
		});
		exitbtn.setBackground(new Color(5, 110, 115));
		exitbtn.setBorderPainted(false);
		exitbtn.setBounds(298, 52, 89, 23);
		adminpanel.add(exitbtn);
	}
}
