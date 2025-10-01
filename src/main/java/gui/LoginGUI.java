package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import domain.Driver;
import domain.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		
		
		 
	  
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		getContentPane().setLayout(null);
		
		
		JPanel contentPane;
		
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		lblNewLabel.setBounds(10, 81, 135, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblNewLabel_1.setBounds(10, 123, 99, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 120, 167, 20);
		contentPane.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(175, 78, 167, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Login.NotRegistered"));
		lblNewLabel_2.setBounds(10, 236, 156, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel erroreMezua = new JLabel("");
		erroreMezua.setBounds(50, 193, 314, 13);
		contentPane.add(erroreMezua);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ErregistratuGUI erregistratu=new ErregistratuGUI();
				erregistratu.setVisible(true);
				itxi_lehioa(e);
			}
		});
		
		
		btnNewButton.setBounds(164, 232, 135, 23);
		contentPane.add(btnNewButton);
		JButton btnNewButton_1 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
	
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().trim().isEmpty() || passwordField.getPassword().length == 0) {
					System.out.println("errorea");
					erroreMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("Login.ValueMissing"));
				}else {
					BLFacade facade = MainGUI.getBusinessLogic();
					String izena = textField.getText();
					char[] p = passwordField.getPassword();
					String pasahitza = new String(p);
					
					if(izena.equals("admin")&&pasahitza.equals("admin")) {
						itxi_lehioa(e);
						JFrame admin=new AdministratzaileaGUI(new Administratzailea());
						admin.setVisible(true);
					}
					
					
					User u= facade.login(izena, pasahitza);

				

					
					
					
						
						if(u !=null) {
						if(u instanceof Driver) {
							itxi_lehioa(e);
							JFrame a = new DriverMenuaGUI((Driver) u);
							a.setVisible(true);
						}else if(u instanceof Traveler) {
							
							itxi_lehioa(e);
							Traveler t=(Traveler) u;
							JFrame b = new TravelerMenuaGUI(t);
							System.out.println("1");
							b.setVisible(true);
						}
						
						
						
					}
					else erroreMezua.setText(ResourceBundle.getBundle("Etiquetas").getString("Login.LoginError"));
				}
				
			}
		});
		btnNewButton_1.setBounds(153, 151, 146, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		lblNewLabel_3.setBounds(174, 42, 125, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_2 = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.QueryRides"));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFrame a = new FindRidesGUI();
				a.setVisible(true);
				
				
			}
		});
		btnNewButton_2.setBounds(11, 11, 155, 23);
		contentPane.add(btnNewButton_2);
		
		
	}
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
	
}
