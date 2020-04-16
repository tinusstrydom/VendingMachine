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
	private JTextField usernameTF;
	private JPasswordField passwordTF;
	private JButton coke, creamsoda, fanta, gtwist, ironbrew, ltwist, nwater, stoney, swater;
	
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
		setBounds(100, 100,615, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//TOP PANEL
		//Customization for top panel 
		JPanel toppanel = new JPanel();
		toppanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		toppanel.setBackground(new Color(210, 61, 39));
		toppanel.setBounds(0, 0, 615, 37);
		toppanel.setLayout(null);
		getContentPane().add(toppanel);
				
		//Mouse event listener and Mouse Motion listener added to move vending machine frame onscreen
		currCoord = null;
		toppanel.addMouseListener(new MouseListener() {
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
				
		toppanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}
			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				setLocation(currCoords.x - currCoord.x, currCoords.y - currCoord.y);
			}
		});
		
		//Vending machine label
		JLabel label = new JLabel("Vending Machine");
		label.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		label.setForeground(Color.WHITE);
		label.setBounds(37, 6, 218, 29);
		toppanel.add(label);
				
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
		minimize.setBackground(new Color(105, 105, 105));
		minimize.setBounds(550, 6, 25, 25);
				
		//Minimize action listerner
		minimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		toppanel.add(minimize);
						
		JButton close = new JButton("X");
		close.setMargin(new Insets(2, 5, 2, 5));
		close.setFocusPainted(false);
		close.setPreferredSize(new Dimension(25, 25));
		close.setMaximumSize(new Dimension(25, 25));
		close.setMinimumSize(new Dimension(25, 25));
		close.setForeground(Color.WHITE);
		close.setBorderPainted(false);
		close.setBackground(new Color(255, 0, 0));
		close.setBounds(580, 6, 25, 25);
		close.setFont(new Font("Segoe UI Semibold", Font.BOLD, 12));
				
		//Close screen Action listerner
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toppanel.add(close);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Please select item:", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBackground(new Color(32, 32, 32));
		panel.setBounds(9, 40, 583, 195);
		contentPane.add(panel);
		panel.setLayout(null);
		
		coke = new JButton("Coke");
		coke.setForeground(Color.WHITE);
		coke.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		coke.setFocusable(false);
		coke.setSelected(true);
		coke.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokeinit.png")));
		coke.setBackground(new Color(210,61,39));
		coke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(coke.isSelected()) {
					coke.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/cokesel.png")));
					coke.setBackground(new Color(5, 110, 115));//green
					creamsoda.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
					creamsoda.setBackground(new Color(210,61,39));//orange
					fanta.setSelectedIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
					fanta.setBackground(new Color(210,61,39));//orange
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
				}
			}
		});
		coke.setHorizontalAlignment(SwingConstants.LEFT);
		coke.setBorderPainted(false);
		coke.setBounds(7, 19, 185, 50);
		panel.add(coke);
		
		creamsoda = new JButton("Creamsoda");
		creamsoda.setForeground(Color.WHITE);
		creamsoda.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		creamsoda.setFocusable(false);
		creamsoda.setSelected(true);
		creamsoda.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/creamsodainit.png")));
		creamsoda.setBackground(new Color(210,61,39));
		creamsoda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				}
			}
		});
		creamsoda.setHorizontalAlignment(SwingConstants.LEFT);
		creamsoda.setBorderPainted(false);
		creamsoda.setBounds(199, 19, 185, 50);
		panel.add(creamsoda);
		
		fanta = new JButton("Fanta");
		fanta.setForeground(Color.WHITE);
		fanta.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		fanta.setFocusable(false);
		fanta.setSelected(true);
		fanta.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/fantainit.png")));
		fanta.setBackground(new Color(210,61,39));
		fanta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				}
			}
		});
		fanta.setHorizontalAlignment(SwingConstants.LEFT);
		fanta.setBorderPainted(false);
		fanta.setBounds(391, 19, 185, 50);
		panel.add(fanta);
		
		gtwist = new JButton("Granadila Twist");
		gtwist.setForeground(Color.WHITE);
		gtwist.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		gtwist.setFocusable(false);
		gtwist.setSelected(true);
		gtwist.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/gtwistinit.png")));
		gtwist.setBackground(new Color(210,61,39));
		gtwist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				}
			}
		});
		gtwist.setHorizontalAlignment(SwingConstants.LEFT);
		gtwist.setBorderPainted(false);
		gtwist.setBounds(7, 76, 185, 50);
		panel.add(gtwist);
		
		ironbrew = new JButton("Ironbrew");
		ironbrew.setForeground(Color.WHITE);
		ironbrew.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		ironbrew.setFocusable(false);
		ironbrew.setSelected(true);
		ironbrew.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ironbrewinit.png")));
		ironbrew.setBackground(new Color(210,61,39));
		ironbrew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gtwist.isSelected()) {
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
				}
			}
		});
		ironbrew.setHorizontalAlignment(SwingConstants.LEFT);
		ironbrew.setBorderPainted(false);
		ironbrew.setBounds(199, 76, 185, 50);
		panel.add(ironbrew);
		
		ltwist = new JButton("Lemon Twist");
		ltwist.setForeground(Color.WHITE);
		ltwist.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		ltwist.setFocusable(false);
		ltwist.setSelected(true);
		ltwist.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/ltwistinit.png")));
		ltwist.setBackground(new Color(210,61,39));
		ltwist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gtwist.isSelected()) {
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
				}
			}
		});
		ltwist.setHorizontalAlignment(SwingConstants.LEFT);
		ltwist.setBorderPainted(false);
		ltwist.setBounds(391, 76, 185, 50);
		panel.add(ltwist);
		
		nwater = new JButton("Natural water");
		nwater.setForeground(Color.WHITE);
		nwater.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		nwater.setFocusable(false);
		nwater.setSelected(true);
		nwater.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/nwaterinit.png")));
		nwater.setBackground(new Color(210,61,39));
		nwater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gtwist.isSelected()) {
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
				}
			}
		});
		nwater.setHorizontalAlignment(SwingConstants.LEFT);
		nwater.setBorderPainted(false);
		nwater.setBounds(7, 133, 185, 50);
		panel.add(nwater);
		
		stoney = new JButton("Stoney");
		stoney.setForeground(Color.WHITE);
		stoney.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		stoney.setFocusable(false);
		stoney.setSelected(true);
		stoney.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/stoneyinit.png")));
		stoney.setBackground(new Color(210,61,39));
		stoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gtwist.isSelected()) {
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
				}
			}
		});
		stoney.setHorizontalAlignment(SwingConstants.LEFT);
		stoney.setBorderPainted(false);
		stoney.setBounds(199, 133, 185, 50);
		panel.add(stoney);
		
		swater = new JButton("Sparkling water");
		swater.setForeground(Color.WHITE);
		swater.setFont(new Font("Segoe UI Semibold", Font.BOLD, 14));
		swater.setFocusable(false);
		swater.setSelected(true);
		swater.setIcon(new ImageIcon(VendingMachine.class.getResource("/img/Drinks/swaterinit.png")));
		swater.setBackground(new Color(210,61,39));
		swater.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gtwist.isSelected()) {
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
				}
			}
		});
		swater.setHorizontalAlignment(SwingConstants.LEFT);
		swater.setBorderPainted(false);
		swater.setBounds(391, 133, 185, 50);
		panel.add(swater);
		
		JPanel adminpanel = new JPanel();
		adminpanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Admin", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		adminpanel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		adminpanel.setBounds(10, 357, 582, 93);
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
		loginbtn.setBackground(new Color(38, 84, 124));
		loginbtn.setBorderPainted(false);
		loginbtn.setBounds(184, 23, 89, 56);
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
		resetbtn.setBounds(298, 23, 89, 23);
		adminpanel.add(resetbtn);
		
		JButton exitbtn = new JButton("Exit");
		exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitbtn.setBackground(new Color(38, 84, 124));
		exitbtn.setBorderPainted(false);
		exitbtn.setBounds(298, 55, 89, 23);
		adminpanel.add(exitbtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(32, 32, 32));
		panel_1.setBounds(9, 240, 583, 103);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(271, 5, 46, 14);
		panel_1.add(lblNewLabel);
	}
	
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
}
