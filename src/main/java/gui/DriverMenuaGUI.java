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
import domain.Kotxe;
import domain.Ride;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class DriverMenuaGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private Driver driver;

	
	



	
	


	
	private final JButton BidaiaKantzelatuBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaKantzelatu")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton myRides = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.BidaiaBaloratu")); //$NON-NLS-1$ //$NON-NLS-2$
	

	public DriverMenuaGUI(Driver driver) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		this.driver=driver;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));
		
		

		

		

		JButton Erreklamazioakbtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErreklamazioakIkusi")); //$NON-NLS-1$ //$NON-NLS-2$
		Erreklamazioakbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				ErreklamazioakIkusiGUI lehioa=new ErreklamazioakIkusiGUI(driver);
				lehioa.setVisible(true);
			}
		});
		Erreklamazioakbtn.setBounds(119, 167, 326, 27);
		getContentPane().add(Erreklamazioakbtn);
		
		
	

		
		
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		
		 
		
		
		
		
		
		JLabel lblNewLabel = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(16, 214, 45, 13);
		getContentPane().add(lblNewLabel);
		
		JButton btnDiruaAtera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.withdrawMoney")); //$NON-NLS-1$ //$NON-NLS-2$
		btnDiruaAtera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				DiruaAteraGUI sartu=new DiruaAteraGUI(driver);
				sartu.setVisible(true);
			}
		});
		btnDiruaAtera.setBounds(119, 206, 326, 27);
		getContentPane().add(btnDiruaAtera);
		
		JButton EskaeraOnartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Eskaerak")); //$NON-NLS-1$ //$NON-NLS-2$
		EskaeraOnartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				EskaeraOnartuGUI lehioa=new EskaeraOnartuGUI(driver);
				lehioa.setVisible(true);
				
			}
		});
		EskaeraOnartu.setBounds(119, 89, 326, 27);
		getContentPane().add(EskaeraOnartu);
		BidaiaKantzelatuBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				BidaiaKantzelatuGUI a=new BidaiaKantzelatuGUI(driver);
				a.setVisible(true);
			}
		});
		
	
		BidaiaKantzelatuBotoia.setBounds(119, 245, 326, 27);
		
		getContentPane().add(BidaiaKantzelatuBotoia);
		myRides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyRidesGUI bidaiak = new MyRidesGUI(driver);
				bidaiak.setVisible(true);
				itxi_lehioa(e);
			}
		});
		myRides.setBounds(119, 284, 326, 21);
		
		getContentPane().add(myRides);
		
		JButton BalorazioakIkusibtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.NireProfilaIkusi")); //$NON-NLS-1$ //$NON-NLS-2$
		BalorazioakIkusibtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				ProfilaIkusiGUI lehioa=new ProfilaIkusiGUI(driver,driver);
				lehioa.setVisible(true);
			}
		});
		BalorazioakIkusibtn.setBounds(119, 128, 326, 27);
		getContentPane().add(BalorazioakIkusibtn);
		
		JLabel Menua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Menua")); //$NON-NLS-1$ //$NON-NLS-2$
		Menua.setBounds(256, 12, 60, 17);
		getContentPane().add(Menua);
		
		JButton CreateRidebtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.CreateRide")); //$NON-NLS-1$ //$NON-NLS-2$
		CreateRidebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				CreateRideGUI lehioa=new CreateRideGUI(driver);
				lehioa.setVisible(true);
			}
		});
		CreateRidebtn.setBounds(119, 50, 326, 27);
		getContentPane().add(CreateRidebtn);
		
		JButton KontuaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.EzabatuKontua")); //$NON-NLS-1$ //$NON-NLS-2$
		KontuaEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				KontuaEzabatuGUI lehioa=new KontuaEzabatuGUI(driver,false);
				itxi_lehioa(arg0);
				lehioa.setVisible(true);
			}
		});
		KontuaEzabatu.setBounds(119, 311, 326, 15);
		getContentPane().add(KontuaEzabatu);
		
		
		 //Code for JCalendar
		
	}	 
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}
