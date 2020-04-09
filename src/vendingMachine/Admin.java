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

public class Admin extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

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

	/**
	 * Create the frame.
	 */
	public Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(11, 3, 462, 355);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel coke = new JLabel("Coke");
		coke.setHorizontalAlignment(SwingConstants.LEFT);
		coke.setBounds(10, 37, 46, 14);
		panel.add(coke);
		
		JLabel creamsoda = new JLabel("Cream Soda");
		creamsoda.setHorizontalAlignment(SwingConstants.LEFT);
		creamsoda.setBounds(10, 62, 75, 14);
		panel.add(creamsoda);
		
		JLabel fanta = new JLabel("Fanta");
		fanta.setHorizontalAlignment(SwingConstants.LEFT);
		fanta.setBounds(10, 87, 46, 14);
		panel.add(fanta);
		
		JLabel gtwist = new JLabel("Granadilla Twist");
		gtwist.setHorizontalAlignment(SwingConstants.LEFT);
		gtwist.setBounds(10, 112, 75, 14);
		panel.add(gtwist);
		
		JLabel ironbrew = new JLabel("Ironbrew");
		ironbrew.setHorizontalAlignment(SwingConstants.LEFT);
		ironbrew.setBounds(10, 136, 46, 14);
		panel.add(ironbrew);
		
		JLabel ltwist = new JLabel("Lemon Twist");
		ltwist.setHorizontalAlignment(SwingConstants.LEFT);
		ltwist.setBounds(10, 161, 75, 14);
		panel.add(ltwist);
		
		JLabel nwater = new JLabel("Natural Water");
		nwater.setHorizontalAlignment(SwingConstants.LEFT);
		nwater.setBounds(10, 186, 75, 14);
		panel.add(nwater);
		
		JLabel swater = new JLabel("Sparkling Water");
		swater.setHorizontalAlignment(SwingConstants.LEFT);
		swater.setBounds(10, 211, 85, 14);
		panel.add(swater);
		
		JLabel stoney = new JLabel("Stoney");
		stoney.setHorizontalAlignment(SwingConstants.LEFT);
		stoney.setBounds(10, 238, 46, 14);
		panel.add(stoney);
		
		textField = new JTextField();
		textField.setBounds(90, 34, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(269, 34, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(176, 34, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(373, 34, 86, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel add = new JLabel("Add qty");
		add.setBounds(87, 11, 46, 14);
		panel.add(add);
		
		JLabel remove = new JLabel("Remove qty");
		remove.setBounds(175, 11, 46, 14);
		panel.add(remove);
		
		JLabel qty = new JLabel("Quantity");
		qty.setBounds(270, 9, 46, 14);
		panel.add(qty);
		
		JLabel stock = new JLabel("Stock value");
		stock.setBounds(363, 9, 46, 14);
		panel.add(stock);
		
		textField_4 = new JTextField();
		textField_4.setBounds(90, 60, 86, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(90, 86, 86, 20);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(90, 109, 86, 20);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(90, 133, 86, 20);
		panel.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(90, 158, 86, 20);
		panel.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(90, 183, 86, 20);
		panel.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(90, 208, 86, 20);
		panel.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(90, 235, 86, 20);
		panel.add(textField_11);
		textField_11.setColumns(10);
	}
}
