package gui;

import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Driver;
import domain.Ride;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import gui.CreateRideGUI;
import domain.Kotxe;

public class KotxeaGehituGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private Driver driver;
	private JTextField KotxeMarkaField;
	private JTextField KotxeModeloaField;
	private JTextField MatrikulaField;
	
	



	public KotxeaGehituGUI(Driver driver) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		BLFacade facade = MainGUI.getBusinessLogic();
		this.driver=driver;
		this.getContentPane().setLayout(null);
		
		JLabel KotxeaGehituTitulua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.KotxeaGehitu")); //$NON-NLS-1$ //$NON-NLS-2$
		KotxeaGehituTitulua.setBounds(258, 27, 60, 17);
		getContentPane().add(KotxeaGehituTitulua);
		
		JButton ATZERA = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
		ATZERA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				CreateRideGUI r=new CreateRideGUI(driver);
				r.setVisible(true);
				
			}
		});
		ATZERA.setBounds(38, 22, 105, 27);
		getContentPane().add(ATZERA);
		
		JLabel KotxeMarkaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("KotxeaGehitu.Marka")); //$NON-NLS-1$ //$NON-NLS-2$
		KotxeMarkaText.setBounds(38, 90, 124, 17);
		getContentPane().add(KotxeMarkaText);
		
		JLabel errorea = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		errorea.setBounds(114, 259, 270, 17);
		getContentPane().add(errorea);
		
		JComboBox EserlekuakComboBox = new JComboBox();
		EserlekuakComboBox.setBounds(204, 212, 114, 26);
		getContentPane().add(EserlekuakComboBox);
		
		KotxeMarkaField = new JTextField();
		KotxeMarkaField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		KotxeMarkaField.setBounds(204, 88, 114, 21);
		getContentPane().add(KotxeMarkaField);
		KotxeMarkaField.setColumns(10);
		
		JLabel KotxeModeloaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("KotxeaGehitu.Modeloa")); //$NON-NLS-1$ //$NON-NLS-2$
		KotxeModeloaText.setBounds(38, 130, 124, 17);
		getContentPane().add(KotxeModeloaText);
		
		KotxeModeloaField = new JTextField();
		KotxeModeloaField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		KotxeModeloaField.setBounds(204, 128, 114, 21);
		getContentPane().add(KotxeModeloaField);
		KotxeModeloaField.setColumns(10);
		
		JLabel MatrikulaText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("KotxeaGehitu.KotxearenMatrikula")); //$NON-NLS-1$ //$NON-NLS-2$
		MatrikulaText.setBounds(38, 173, 105, 17);
		getContentPane().add(MatrikulaText);
		
		MatrikulaField = new JTextField();
		MatrikulaField.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		MatrikulaField.setBounds(204, 171, 114, 21);
		getContentPane().add(MatrikulaField);
		MatrikulaField.setColumns(10);
		
		JLabel EserlekuakText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.NumberOfSeats")); //$NON-NLS-1$ //$NON-NLS-2$
		EserlekuakText.setBounds(38, 217, 105, 17);
		getContentPane().add(EserlekuakText);
		
		
		for(int i=1;i<11;i++) {
			EserlekuakComboBox.addItem(i);
		}
		
		
		JButton KotxeaGehituBotton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.KotxeaGehitu")); //$NON-NLS-1$ //$NON-NLS-2$
		KotxeaGehituBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Marka=KotxeMarkaField.getText();
				String Modeloa=KotxeModeloaField.getText();
				String Matrikula=MatrikulaField.getText();
				int Eserlekuak=(int) EserlekuakComboBox.getSelectedItem();
				
				try {
				facade.KotxeaGehitu(Marka,Modeloa,Matrikula,Eserlekuak,driver);
				errorea.setText("");
				}
				catch(Exception e) {
					errorea.setText(e.getMessage());
				}
				
				
			}
		});
		KotxeaGehituBotton.setBounds(204, 299, 105, 27);
		getContentPane().add(KotxeaGehituBotton);
		
	
		
		
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));

		
	}
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
	
}
