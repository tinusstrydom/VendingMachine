package vendingMachine;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Insets;
import java.awt.Point;

@SuppressWarnings("serial")
public class Admin extends JFrame {
	
	static Point currCoord;
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
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 450);
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
		topPanel.setBounds(0, 0, 484, 37);
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
		JLabel toplabel = new JLabel("Admin");
		toplabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		toplabel.setForeground(Color.WHITE);
		toplabel.setBounds(10, 5, 218, 29);
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
		minimize.setBackground(new Color(210,61,39));
		minimize.setBounds(419, 6, 25, 25);
						
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
		close.setBackground(new Color(210,61,39));
		close.setBounds(448, 6, 25, 25);
		close.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
						
		//Close screen Action listerner
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		topPanel.add(close);
		
		//JPANEL
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(32, 32, 32));
		mainPanel.setBounds(10, 47, 464, 354);
		contentPane.add(mainPanel);
		mainPanel.setLayout(null);
		
		//Headings
		JLabel addlabel = new JLabel("Add");
		addlabel.setForeground(Color.WHITE);
		addlabel.setBounds(176, 11, 46, 14);
		mainPanel.add(addlabel);
		
		JLabel removelabel = new JLabel("Remove");
		removelabel.setForeground(Color.WHITE);
		removelabel.setBounds(272, 11, 46, 14);
		mainPanel.add(removelabel);
		
		JLabel qtylabel = new JLabel("Quantity");
		qtylabel.setForeground(Color.WHITE);
		qtylabel.setBounds(368, 11, 46, 14);
		mainPanel.add(qtylabel);
		
		
		
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
				lname.setForeground(Color.WHITE);
				mainPanel.add(lname);
				
				addTF = new JTextField();
				addTF.setText("0");
				addTF.setName(name);
				addTF.setBounds(addX, addY, 75, 20);
				addTF.setText(Integer.toString(0));
				ladd.add(addTF);
				mainPanel.add(addTF);

				removeTF = new JTextField();
				removeTF.setBounds(removeX, removeY, 75, 20);
				removeTF.setText(Integer.toString(0));
				lremove.add(removeTF);
				mainPanel.add(removeTF);
				removeTF.setColumns(10);
				
				qtyTF = new JTextField();
				qtyTF.setEditable(false);
				qtyTF.setBounds(qtyX, qtyY, 75, 20);
				qtyTF.setText(Integer.toString(qty));
				lqty.add(qtyTF);
				mainPanel.add(qtyTF);
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
		addbtn.setBackground(Color.WHITE);
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
		mainPanel.add(addbtn);
		
		JButton removebtn = new JButton("Remove");
		removebtn.setBackground(Color.WHITE);
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
		mainPanel.add(removebtn);
		
		JButton adminExit = new JButton("Exit");
		adminExit.setBorderPainted(false);
		adminExit.setBackground(new Color(210, 61, 39));
		adminExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendingMachine vMachine = new VendingMachine();
				vMachine.setVisible(true);
				dispose();
			}
		});
		adminExit.setBounds(316, 412, 158, 23);
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
