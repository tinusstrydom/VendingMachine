package vendingMachine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import vendingMachine.Admin;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;


public class VendingMachine extends JFrame {
	
	static Point currCoord;
	private JPanel contentPane;
	private JButton coke, creamsoda, fanta, gtwist, ironbrew, ltwist, nwater, stoney, swater;
	private JTextField insertTF;
	private JTextField changeTF;
	private JTextField priceTF;
	
	public String dbUrl = "jdbc:mysql://localhost:3306/vmachine";
	public String dbUser = "myuser";
	public String dbPass = "myuser08";
	String sqlSelect, selected, name;
	int qty;
	
	
	
	//Constructor Method
	public VendingMachine() {
		initUI();
	}
	
	//Create applications layout in initUI
	public void initUI() {
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
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
		topPanel.setBounds(0, 0, 600, 37);
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
		JLabel toplabel = new JLabel("Vending Machine");
		toplabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		toplabel.setForeground(Color.WHITE);
		toplabel.setBounds(37, 5, 218, 29);
		topPanel.add(toplabel);
		
		JButton admin = new JButton("Admin");
		admin.setForeground(Color.WHITE);
		admin.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		admin.setBackground(new Color(210,61,39));
		admin.setBounds(458, 6, 75, 25);
		admin.setBorderPainted(false);
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login Loginp = new Login();
				Loginp.setVisible(true);
				dispose();
			}
		});
		topPanel.add(admin);
			
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
		minimize.setBounds(537, 6, 25, 25);
				
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
		close.setBounds(566, 6, 25, 25);
		close.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
				
		//Close screen Action listerner
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		topPanel.add(close);
		
		JPanel itemPanel = new JPanel();
		itemPanel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Please select item:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		itemPanel.setBackground(new Color(32, 32, 32));
		itemPanel.setBounds(8, 43, 584, 200);
		contentPane.add(itemPanel);
		
		coke = new JButton("Coke");
		coke.setFocusable(false);
		coke.setForeground(Color.WHITE);
		coke.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		coke.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
		coke.setBackground(new Color(210,61,39));
		coke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(true);
				creamsoda.setSelected(false);
				fanta.setSelected(false);
				gtwist.setSelected(false);
				ironbrew.setSelected(false);
				ltwist.setSelected(false);
				nwater.setSelected(false);
				stoney.setSelected(false);
				swater.setSelected(false);
				if(coke.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokesel.png")));
					coke.setBackground(new Color(5, 110, 115));//green
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210,61,39));//orange
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210,61,39));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
					gtwist.setBackground(new Color(210, 61, 39));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
					ironbrew.setBackground(new Color(210, 61, 39));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
					ltwist.setBackground(new Color(210, 61, 39));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
					nwater.setBackground(new Color(210, 61, 39));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
					stoney.setBackground(new Color(210, 61, 39));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
					swater.setBackground(new Color(210, 61, 39));
					priceTF.setText("12.50");
				}
				
			}
		});
		itemPanel.setLayout(null);
		coke.setHorizontalAlignment(SwingConstants.LEFT);
		coke.setBorderPainted(false);
		coke.setBounds(7, 24, 185, 50);
		itemPanel.add(coke);
		
		creamsoda = new JButton("Creamsoda");
		creamsoda.setFocusable(false);
		creamsoda.setForeground(Color.WHITE);
		creamsoda.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		creamsoda.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
		creamsoda.setBackground(new Color(210,61,39));
		creamsoda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(false);
				creamsoda.setSelected(true);
				fanta.setSelected(false);
				gtwist.setSelected(false);
				ironbrew.setSelected(false);
				ltwist.setSelected(false);
				nwater.setSelected(false);
				stoney.setSelected(false);
				swater.setSelected(false);
				if(creamsoda.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
					coke.setBackground(new Color(210, 61, 39));
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodasel.png")));
					creamsoda.setBackground(new Color(5, 110, 115));
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210, 61, 39));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
					gtwist.setBackground(new Color(210, 61, 39));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
					ironbrew.setBackground(new Color(210, 61, 39));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
					ltwist.setBackground(new Color(210, 61, 39));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
					nwater.setBackground(new Color(210, 61, 39));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
					stoney.setBackground(new Color(210, 61, 39));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
					swater.setBackground(new Color(210, 61, 39));
					priceTF.setText("10");
				}
			}
		});
		creamsoda.setHorizontalAlignment(SwingConstants.LEFT);
		creamsoda.setBorderPainted(false);
		creamsoda.setBounds(199, 24, 185, 50);
		itemPanel.add(creamsoda);
		
		fanta = new JButton("Fanta");
		fanta.setFocusable(false);
		fanta.setForeground(Color.WHITE);
		fanta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		fanta.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
		fanta.setBackground(new Color(210,61,39));
		fanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(false);
				creamsoda.setSelected(false);
				fanta.setSelected(true);
				gtwist.setSelected(false);
				ironbrew.setSelected(false);
				ltwist.setSelected(false);
				nwater.setSelected(false);
				stoney.setSelected(false);
				swater.setSelected(false);
				if(fanta.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
					coke.setBackground(new Color(210, 61, 39));
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210, 61, 39));
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantasel.png")));
					fanta.setBackground(new Color(5, 110, 115));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
					gtwist.setBackground(new Color(210, 61, 39));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
					ironbrew.setBackground(new Color(210, 61, 39));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
					ltwist.setBackground(new Color(210, 61, 39));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
					nwater.setBackground(new Color(210, 61, 39));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
					stoney.setBackground(new Color(210, 61, 39));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
					swater.setBackground(new Color(210, 61, 39));
					priceTF.setText("10");
				}
			}
		});
		fanta.setHorizontalAlignment(SwingConstants.LEFT);
		fanta.setBorderPainted(false);
		fanta.setBounds(391, 24, 185, 50);
		itemPanel.add(fanta);
		
		gtwist = new JButton("Granadila Twist");
		gtwist.setFocusable(false);
		gtwist.setForeground(Color.WHITE);
		gtwist.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		gtwist.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
		gtwist.setBackground(new Color(210,61,39));
		gtwist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(false);
				creamsoda.setSelected(false);
				fanta.setSelected(false);
				gtwist.setSelected(true);
				ironbrew.setSelected(false);
				ltwist.setSelected(false);
				nwater.setSelected(false);
				stoney.setSelected(false);
				swater.setSelected(false);
				if(gtwist.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
					coke.setBackground(new Color(210, 61, 39));
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210, 61, 39));
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210, 61, 39));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistsel.png")));
					gtwist.setBackground(new Color(5, 110, 115));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
					ironbrew.setBackground(new Color(210, 61, 39));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
					ltwist.setBackground(new Color(210, 61, 39));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
					nwater.setBackground(new Color(210, 61, 39));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
					stoney.setBackground(new Color(210, 61, 39));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
					swater.setBackground(new Color(210, 61, 39));
					priceTF.setText("10");
				}
			}
		});
		gtwist.setHorizontalAlignment(SwingConstants.LEFT);
		gtwist.setBorderPainted(false);
		gtwist.setBounds(7, 81, 185, 50);
		itemPanel.add(gtwist);
		
		ironbrew = new JButton("Ironbrew");
		ironbrew.setFocusable(false);
		ironbrew.setForeground(Color.WHITE);
		ironbrew.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		ironbrew.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
		ironbrew.setBackground(new Color(210,61,39));
		ironbrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(false);
				creamsoda.setSelected(false);
				fanta.setSelected(false);
				gtwist.setSelected(false);
				ironbrew.setSelected(true);
				ltwist.setSelected(false);
				nwater.setSelected(false);
				stoney.setSelected(false);
				swater.setSelected(false);
				if(ironbrew.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
					coke.setBackground(new Color(210, 61, 39));
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210, 61, 39));
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210, 61, 39));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
					gtwist.setBackground(new Color(210, 61, 39));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewsel.png")));
					ironbrew.setBackground(new Color(5, 110, 115));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
					ltwist.setBackground(new Color(210, 61, 39));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
					nwater.setBackground(new Color(210, 61, 39));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
					stoney.setBackground(new Color(210, 61, 39));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
					swater.setBackground(new Color(210, 61, 39));
					priceTF.setText("10");
				}
			}
		});
		ironbrew.setHorizontalAlignment(SwingConstants.LEFT);
		ironbrew.setBorderPainted(false);
		ironbrew.setBounds(199, 81, 185, 50);
		itemPanel.add(ironbrew);
		
		ltwist = new JButton("Lemon Twist");
		ltwist.setFocusable(false);
		ltwist.setForeground(Color.WHITE);
		ltwist.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		ltwist.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
		ltwist.setBackground(new Color(210,61,39));
		ltwist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(false);
				creamsoda.setSelected(false);
				fanta.setSelected(false);
				gtwist.setSelected(false);
				ironbrew.setSelected(false);
				ltwist.setSelected(true);
				nwater.setSelected(false);
				stoney.setSelected(false);
				swater.setSelected(false);
				if(ltwist.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
					coke.setBackground(new Color(210, 61, 39));
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210, 61, 39));
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210, 61, 39));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
					gtwist.setBackground(new Color(210, 61, 39));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
					ironbrew.setBackground(new Color(210, 61, 39));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistsel.png")));
					ltwist.setBackground(new Color(5, 110, 115));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
					nwater.setBackground(new Color(210, 61, 39));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
					stoney.setBackground(new Color(210, 61, 39));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
					swater.setBackground(new Color(210, 61, 39));
					priceTF.setText("10");
				}
			}
		});
		ltwist.setHorizontalAlignment(SwingConstants.LEFT);
		ltwist.setBorderPainted(false);
		ltwist.setBounds(391, 81, 185, 50);
		itemPanel.add(ltwist);
		
		nwater = new JButton("Natural water");
		nwater.setFocusable(false);
		nwater.setForeground(Color.WHITE);
		nwater.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		nwater.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
		nwater.setBackground(new Color(210,61,39));
		nwater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(false);
				creamsoda.setSelected(false);
				fanta.setSelected(false);
				gtwist.setSelected(false);
				ironbrew.setSelected(false);
				ltwist.setSelected(false);
				nwater.setSelected(true);
				stoney.setSelected(false);
				swater.setSelected(false);
				if(nwater.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
					coke.setBackground(new Color(210, 61, 39));
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210, 61, 39));
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210, 61, 39));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
					gtwist.setBackground(new Color(210, 61, 39));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
					ironbrew.setBackground(new Color(210, 61, 39));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
					ltwist.setBackground(new Color(210, 61, 39));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwatersel.png")));
					nwater.setBackground(new Color(5, 110, 115));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
					stoney.setBackground(new Color(210, 61, 39));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
					swater.setBackground(new Color(210, 61, 39));
					priceTF.setText("7.50");
				}
			}
		});
		nwater.setHorizontalAlignment(SwingConstants.LEFT);
		nwater.setBorderPainted(false);
		nwater.setBounds(7, 138, 185, 50);
		itemPanel.add(nwater);
		
		stoney = new JButton("Stoney");
		stoney.setFocusable(false);
		stoney.setForeground(Color.WHITE);
		stoney.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		stoney.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
		stoney.setBackground(new Color(210,61,39));
		stoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(false);
				creamsoda.setSelected(false);
				fanta.setSelected(false);
				gtwist.setSelected(false);
				ironbrew.setSelected(false);
				ltwist.setSelected(false);
				nwater.setSelected(false);
				stoney.setSelected(true);
				swater.setSelected(false);
				if(stoney.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
					coke.setBackground(new Color(210, 61, 39));
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210, 61, 39));
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210, 61, 39));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
					gtwist.setBackground(new Color(210, 61, 39));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
					ironbrew.setBackground(new Color(210, 61, 39));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
					ltwist.setBackground(new Color(210, 61, 39));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
					nwater.setBackground(new Color(210, 61, 39));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneysel.png")));
					stoney.setBackground(new Color(5, 110, 115));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
					swater.setBackground(new Color(210, 61, 39));
					priceTF.setText("10");
				}
			}
		});
		stoney.setHorizontalAlignment(SwingConstants.LEFT);
		stoney.setBorderPainted(false);
		stoney.setBounds(199, 138, 185, 50);
		itemPanel.add(stoney);
		
		swater = new JButton("Sparkling water");
		swater.setFocusable(false);
		swater.setForeground(Color.WHITE);
		swater.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		swater.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
		swater.setBackground(new Color(210,61,39));
		swater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelected(false);
				creamsoda.setSelected(false);
				fanta.setSelected(false);
				gtwist.setSelected(false);
				ironbrew.setSelected(false);
				ltwist.setSelected(false);
				nwater.setSelected(false);
				stoney.setSelected(false);
				swater.setSelected(true);
				if(swater.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
					coke.setBackground(new Color(210, 61, 39));
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210, 61, 39));
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210, 61, 39));
					gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
					gtwist.setBackground(new Color(210, 61, 39));
					ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
					ironbrew.setBackground(new Color(210, 61, 39));
					ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
					ltwist.setBackground(new Color(210, 61, 39));
					nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
					nwater.setBackground(new Color(210, 61, 39));
					stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
					stoney.setBackground(new Color(210, 61, 39));
					swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swatersel.png")));
					swater.setBackground(new Color(5, 110, 115));
					priceTF.setText("8.50");
				}
			}
		});
		swater.setHorizontalAlignment(SwingConstants.LEFT);
		swater.setBorderPainted(false);
		swater.setBounds(391, 138, 185, 50);
		itemPanel.add(swater);
		
		JPanel purchasePanel = new JPanel();
		purchasePanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		purchasePanel.setForeground(Color.WHITE);
		purchasePanel.setBorder(new TitledBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(255, 255, 255)), "Payment", TitledBorder.CENTER, TitledBorder.TOP, null, Color.WHITE));
		purchasePanel.setBackground(new Color(32, 32, 32));
		purchasePanel.setBounds(8, 247, 584, 94);
		contentPane.add(purchasePanel);
		purchasePanel.setLayout(null);
		
		JLabel insert = new JLabel("Insert money:  R");
		insert.setHorizontalTextPosition(SwingConstants.RIGHT);
		insert.setHorizontalAlignment(SwingConstants.RIGHT);
		insert.setForeground(Color.WHITE);
		insert.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		insert.setBounds(17, 17, 90, 25);
		purchasePanel.add(insert);
		
		insertTF = new JTextField();
		insertTF.setBounds(111, 18, 86, 20);
		purchasePanel.add(insertTF);
		insertTF.setColumns(10);
		
		JLabel change = new JLabel("Change:  R");
		change.setHorizontalTextPosition(SwingConstants.RIGHT);
		change.setHorizontalAlignment(SwingConstants.RIGHT);
		change.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		change.setForeground(Color.WHITE);
		change.setBounds(214, 17, 60, 25);
		purchasePanel.add(change);
		
		changeTF = new JTextField();
		changeTF.setEditable(false);
		changeTF.setBounds(281, 18, 86, 20);
		purchasePanel.add(changeTF);
		changeTF.setColumns(10);
		
		JLabel price = new JLabel("Price:  R");
		price.setHorizontalTextPosition(SwingConstants.RIGHT);
		price.setForeground(Color.WHITE);
		price.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		price.setBounds(374, 17, 60, 25);
		purchasePanel.add(price);
		
		priceTF = new JTextField();
		priceTF.setText("0");
		priceTF.setBounds(440, 18, 86, 20);
		purchasePanel.add(priceTF);
		priceTF.setColumns(10);
		
		JButton purchase = new JButton("Purchase");
		purchase.setForeground(Color.WHITE);
		purchase.setBackground(new Color(5, 110, 115));
		purchase.setBorderPainted(false);
		purchase.setBounds(147, 60, 89, 23);
		purchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(insertTF.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please insert some money!", "No Money Error",JOptionPane.ERROR_MESSAGE);
				}else {
					double money = 0;
					money = Double.parseDouble(insertTF.getText());

					if(coke.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}else if(creamsoda.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}else if(fanta.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}else if(gtwist.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}else if(ironbrew.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}else if(ltwist.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}else if(nwater.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}else if(stoney.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}else if(swater.isSelected()) {
						money = money - Double.parseDouble(priceTF.getText());
					}
					
					if(money < 0) {
						JOptionPane.showMessageDialog(null, "Please insert money!", "No Money Error",JOptionPane.ERROR_MESSAGE);
					}else {
						changeTF.setText(Double.toString(money));
						JOptionPane.showMessageDialog(null, "Thank you.", "Thank you",JOptionPane.PLAIN_MESSAGE);
						try {
							// Allocate database connection object
							Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
							// Allocate statement object in connection
							Statement stmt = conn.createStatement();	
							// Execute SQL update query
							
							if(coke.isSelected()) {
								selected = "Coke";
							}else if(creamsoda.isSelected()) {
								selected = "Creamsoda";
							}else if(fanta.isSelected()) {
								selected = "Fanta";
							}else if(gtwist.isSelected()) {
								selected = "Gtwist";
							}else if(ironbrew.isSelected()) {
								selected = "IronBrew";
							}else if(ltwist.isSelected()) {
								selected = "Ltwist";
							}else if(nwater.isSelected()) {
								selected = "Nwater";
							}else if(stoney.isSelected()) {
								selected = "Stoney";
							}else if(swater.isSelected()) {
								selected = "Swater";
							}
							String sqlUpdate = "update stock set qty=qty-1 where name='"+selected+"'";
							stmt.executeUpdate(sqlUpdate);	
						}catch(SQLException ex) {
							ex.printStackTrace();
						}
						
						coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
						coke.setBackground(new Color(210, 61, 39));
						creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
						creamsoda.setBackground(new Color(210, 61, 39));
						fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
						fanta.setBackground(new Color(210, 61, 39));
						gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
						gtwist.setBackground(new Color(210, 61, 39));
						ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
						ironbrew.setBackground(new Color(210, 61, 39));
						ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
						ltwist.setBackground(new Color(210, 61, 39));
						nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
						nwater.setBackground(new Color(210, 61, 39));
						stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
						stoney.setBackground(new Color(210, 61, 39));
						swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
						swater.setBackground(new Color(210, 61, 39));
						insertTF.setText("");
						changeTF.setText("");
						priceTF.setText("");
					}
				}
			}
		});
		purchasePanel.add(purchase);
		
		JButton clear = new JButton("Clear");
		clear.setForeground(Color.WHITE);
		clear.setBackground(new Color(5, 110, 115));
		clear.setBorderPainted(false);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
				coke.setBackground(new Color(210, 61, 39));
				creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
				creamsoda.setBackground(new Color(210, 61, 39));
				fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
				fanta.setBackground(new Color(210, 61, 39));
				gtwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
				gtwist.setBackground(new Color(210, 61, 39));
				ironbrew.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
				ironbrew.setBackground(new Color(210, 61, 39));
				ltwist.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
				ltwist.setBackground(new Color(210, 61, 39));
				nwater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
				nwater.setBackground(new Color(210, 61, 39));
				stoney.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
				stoney.setBackground(new Color(210, 61, 39));
				swater.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
				swater.setBackground(new Color(210, 61, 39));
				insertTF.setText(" ");
				changeTF.setText(" ");
				priceTF.setText(" ");
			}
		});
		clear.setBounds(246, 60, 89, 23);
		purchasePanel.add(clear);
		
		JButton cancel = new JButton("Cancel");
		cancel.setForeground(Color.WHITE);
		cancel.setBorderPainted(false);
		cancel.setBackground(new Color(5, 110, 115));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancel.setBounds(345, 60, 89, 23);
		purchasePanel.add(cancel);
		
	}
	
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
}
