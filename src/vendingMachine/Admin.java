package vendingMachine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import vendingMachine.VendingMachine;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.*;
import java.sql.*;
import java.awt.Insets;

public class Admin extends JFrame {
	
	private JPanel contentPane;
	public String dbUrl = "jdbc:mysql://localhost:3306/vmachine";
	public String dbUser = "myuser";
	public String dbPass = "myuser08";
	
	String name;
	int x = 10;
	int y = 40;
	int addX = 176;
	int addY = 37;
	int removeX = 272;
	int removeY = 37;
	int qtyX = 368;
	int qtyY = 37;
	
	int id, qty;
	private JTextField addTF;
	private JTextField removeTF;
	private JTextField qtyTF;
	private JLabel lname;
	
	/**
	 * Create the frame.
	 */
	public Admin() {
		// Try/catch statement 
		try{
			// Allocate database connection object
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
			// Allocate statement object in connection
			Statement stmt = conn.createStatement();
			// Execute SQL select query
			// Query result is returned in the ResultSet object 
			String sqlSelect = "select * from stock";
			ResultSet rset = stmt.executeQuery(sqlSelect);
					
			//Admin Layout
			//Set Jframe
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 500, 500);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 0, 51));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			//JPANEL
			JPanel panel = new JPanel();
			panel.setBounds(10, 11, 464, 399);
			contentPane.add(panel);
			panel.setLayout(null);
			
			//Headings
			JLabel add = new JLabel("Add");
			add.setBounds(176, 11, 46, 14);
			panel.add(add);
			
			JLabel remove = new JLabel("Remove");
			remove.setBounds(272, 11, 46, 14);
			panel.add(remove);
			
			JLabel qty1 = new JLabel("Quantity");
			qty1.setBounds(368, 11, 46, 14);
			panel.add(qty1);
			
			//While loop to get each column from resultset
			while(rset.next()) {
				name = rset.getString("name");
				qty = rset.getInt("qty");
				
				lname = new JLabel(name);
				lname.setBounds(x, y, 75, 20);
				panel.add(lname);
				
				addTF = new JTextField();
				addTF.setBounds(addX, addY, 75, 20);
				panel.add(addTF);
				addTF.setColumns(10);
				
				removeTF = new JTextField();
				removeTF.setBounds(removeX, removeY, 75, 20);
				panel.add(removeTF);
				removeTF.setColumns(10);
				
				qtyTF = new JTextField();
				qtyTF.setEditable(false);
				qtyTF.setBounds(qtyX, qtyY, 75, 20);
				qtyTF.setText(Integer.toString(qty));
				panel.add(qtyTF);
				qtyTF.setColumns(10);
				
				y+= 30;
				addY += 30;
				removeY += 30;
				qtyY += 30;
				
			}
			JButton addbtn = new JButton("Add");
			addbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println(addTF);
				}
			});
			addbtn.setMargin(new Insets(2, 7, 2, 7));
			addbtn.setBorderPainted(false);
			addbtn.setBounds(176, addY, 75, 23);
			panel.add(addbtn);
			
			JButton removebtn = new JButton("Remove");
			removebtn.setMargin(new Insets(2, 7, 2, 7));
			removebtn.setBorderPainted(false);
			removebtn.setBounds(272, removeY, 75, 23);
			panel.add(removebtn);
			
			JButton adminExit = new JButton("Exit");
			adminExit.setBackground(new Color(0, 204, 255));
			adminExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VendingMachine vMachine = new VendingMachine();
					vMachine.setVisible(true);
					dispose();
				}
			});
			adminExit.setBounds(303, 427, 158, 23);
			contentPane.add(adminExit);
			
			
			
			
			
			
			
		//Catch exception
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
