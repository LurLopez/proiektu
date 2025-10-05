package gui;

import domain.Driver;
import domain.Traveler;
import domain.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class ErregistratuGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField userField;
	private JTextField KontuaField;
	private JTextField mailField;
	private ButtonGroup fareButtonGroup = new ButtonGroup();  
	private DataAccess da;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ErregistratuGUI frame = new ErregistratuGUI();
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
	public ErregistratuGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblNewLabel.setBounds(10, 73, 117, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel_1.setBounds(10, 105, 117, 21);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(132, 105, 292, 20);
		contentPane.add(passwordField);
		
		userField = new JTextField();
		userField.setBounds(132, 73, 292, 20);
		contentPane.add(userField);
		userField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		lblNewLabel_2.setBounds(224, 7, 181, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Current_Count"));
		lblNewLabel_3.setBounds(10, 138, 117, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		
		btnNewButton.setBounds(179, 220, 209, 30);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Email"));
		lblNewLabel_4.setBounds(10, 162, 117, 14);
		contentPane.add(lblNewLabel_4);
		
		KontuaField = new JTextField();
		KontuaField.setBounds(132, 134, 292, 20);
		contentPane.add(KontuaField);
		KontuaField.setColumns(10);
		
		mailField = new JTextField();
		mailField.setBounds(132, 159, 292, 20);
		contentPane.add(mailField);
		mailField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				itxi_lehioa(e);
			}
		});
		btnNewButton_1.setBounds(5, 224, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Register.AreYouRegistered"));
		lblNewLabel_5.setBounds(10, 199, 137, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame a = new FindRidesGUI();
				a.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(5, 11, 158, 23);
		contentPane.add(btnNewButton_2);
		
		JRadioButton rdbtnGIDARI = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Driver"));
		fareButtonGroup.add(rdbtnGIDARI);
		rdbtnGIDARI.setBounds(132, 40, 76, 25);
		contentPane.add(rdbtnGIDARI);
		
		
		JRadioButton rdbtnBIDAIARI = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Traveler"));
		fareButtonGroup.add(rdbtnBIDAIARI);
		rdbtnBIDAIARI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnGIDARI.isSelected()) {
					rdbtnGIDARI.setEnabled(false);
				}
			}
		});
		rdbtnBIDAIARI.setBounds(210, 40, 130, 25);
		contentPane.add(rdbtnBIDAIARI);
		
		JLabel lblErrorea = new JLabel("");
		lblErrorea.setBounds(132, 191, 292, 17);
		contentPane.add(lblErrorea);
		
		BLFacade facade = MainGUI.getBusinessLogic();

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((!rdbtnGIDARI.isSelected()&&!rdbtnBIDAIARI.isSelected())||userField.getText().trim().isEmpty()||passwordField.getPassword().length == 0||KontuaField.getText().trim().isEmpty()||mailField.getText().trim().isEmpty() ){
					System.out.println("errorea");
					lblErrorea.setText(ResourceBundle.getBundle("Etiquetas").getString("Login.ValueMissing"));
				}
				else { 
					lblErrorea.setText("");
					String erabiltzaileIzena = userField.getText();
					char[] p = passwordField.getPassword();
					String pasahitza = new String(p);
					String kontua = KontuaField.getText();
					String mail = mailField.getText();
					
					
					if(rdbtnGIDARI.isSelected()) {
						
						Driver u = (Driver) facade.register(erabiltzaileIzena, pasahitza, "Driver",mail);
						if(u == null) {
							lblErrorea.setText(ResourceBundle.getBundle("Etiquetas").getString("Register.AlreadyRegistered"));
						}
						else lblErrorea.setText(ResourceBundle.getBundle("Etiquetas").getString("Register.RegisterSuccessfully"));
					}else {
						Traveler u = (Traveler) facade.register(erabiltzaileIzena, pasahitza, "Traveler",mail);
						if(u == null) {
							lblErrorea.setText(ResourceBundle.getBundle("Etiquetas").getString("Register.AlreadyRegistered"));
						}
						else lblErrorea.setText(ResourceBundle.getBundle("Etiquetas").getString("Register.RegisterSuccessfully"));
					}
					
					
					
				}
			}
		});
		
		
		
		
		
	}
	
	
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}

