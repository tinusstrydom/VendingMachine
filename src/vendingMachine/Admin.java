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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Insets;

@SuppressWarnings("serial")
public class Admin extends JFrame {
	
	private JPanel contentPane;
	public String dbUrl = "jdbc:mysql://localhost:3306/vmachine";
	public String dbUser = "myuser";
	public String dbPass = "myuser08";
	
	String name;
	int id, qty;
	int x = 10;
	int y = 40;
	int addX = 176;
	int addY = 37;
	int removeX = 272;
	int removeY = 37;
	int qtyX = 368;
	int qtyY = 37;

	private JTextField addTF = null;
	private JTextField removeTF;
	private JTextField qtyTF;
	List<JTextField> ladd = new ArrayList<JTextField>();
	List<JTextField> lremove = new ArrayList<JTextField>();
	List<JTextField> lqty = new ArrayList<JTextField>();
	
	//CONSTRUCTOR METHOD
	public Admin() {
		//Admin Layout
		//Set JFRAME
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
					
			
			
			//While loop to get each column from resultset
			while(rset.next()) {
				name = rset.getString("name");
				qty = rset.getInt("qty");
				
				JLabel lname = new JLabel(name);
				lname.setBounds(x, y, 75, 20);
				panel.add(lname);
				
				addTF = new JTextField();
				addTF.setText("0");
				addTF.setName(name);
				addTF.setBounds(addX, addY, 75, 20);
				addTF.setText(Integer.toString(0));
				ladd.add(addTF);
				panel.add(addTF);

				removeTF = new JTextField();
				removeTF.setBounds(removeX, removeY, 75, 20);
				removeTF.setText(Integer.toString(0));
				lremove.add(removeTF);
				panel.add(removeTF);
				removeTF.setColumns(10);
				
				qtyTF = new JTextField();
				qtyTF.setEditable(false);
				qtyTF.setBounds(qtyX, qtyY, 75, 20);
				qtyTF.setText(Integer.toString(qty));
				lqty.add(qtyTF);
				panel.add(qtyTF);
				qtyTF.setColumns(10);
				
				y+= 30;
				addY+=30;
				removeY += 30;
				qtyY += 30;
			}
		//Catch exception
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		//BUTTONS for adding and removing stock
		JButton addbtn = new JButton("Add");
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < ladd.size(); i++) {
					try {
						//added one to i ,(sql id starts at 1 up to 9)
						int ii = i+1;
						// Allocate database connection object
						Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
						// Allocate statement object in connection
						Statement stmt = conn.createStatement();	
						// Execute SQL update query
						String sqlUpdate = "update stock set qty=qty+'"+ladd.get(i).getText() +"' where id="+ii;
						stmt.executeUpdate(sqlUpdate);	
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				for(int i = 0; i < ladd.size(); i++) {
					try{
						// Allocate database connection object
						Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
						// Allocate statement object in connection
						Statement stmt = conn.createStatement();
						// Execute SQL select query
						// Query result is returned in the ResultSet object 
						String sqlSelect = "select * from stock";
						ResultSet rset = stmt.executeQuery(sqlSelect);	
						//While loop to get each column from resultset
						for(int j = 0; rset.next(); j++) {
							qty = rset.getInt("qty");
							lqty.get(j).setText(Integer.toString(qty));
							ladd.get(j).setText("0");
						}
					//Catch exception
					}catch(SQLException ex) {
						ex.printStackTrace();
					}	
				}
			}
		});
		addbtn.setMargin(new Insets(2, 7, 2, 7));
		addbtn.setBorderPainted(false);
		addbtn.setBounds(176, addY, 75, 23);
		panel.add(addbtn);
		
		JButton removebtn = new JButton("Remove");
		removebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < lremove.size(); i++) {
					try {
						// Allocate database connection object
						Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
						// Allocate statement object in connection
						Statement stmt = conn.createStatement();	
						int ii = i+1;
						// Execute SQL select query
						// Query result is returned in the ResultSet object 
						String sqlSelect = "update stock set qty=qty-'"+lremove.get(i).getText() +"' where id="+ii;
						stmt.executeUpdate(sqlSelect);
					}catch(SQLException ex) {
						ex.printStackTrace();
					}
				}
				for(int i = 0; i < lremove.size(); i++) {
					try{
						// Allocate database connection object
						Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
						// Allocate statement object in connection
						Statement stmt = conn.createStatement();
						// Execute SQL select query
						// Query result is returned in the ResultSet object 
						String sqlSelect = "select * from stock";
						ResultSet rset = stmt.executeQuery(sqlSelect);	
						//While loop to get each column from resultset
						for(int j = 0; rset.next(); j++) {
							qty = rset.getInt("qty");
							lqty.get(j).setText(Integer.toString(qty));
							lremove.get(j).setText("0");
						}
					//Catch exception
					}catch(SQLException ex) {
						ex.printStackTrace();
					}	
				}
			}
		});
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
	}

	//MAIN METHOD
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
