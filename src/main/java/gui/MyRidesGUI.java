package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.User;
import domain.Driver;
import domain.Erreserba;
import domain.Mugimendua;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class MyRidesGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	private ArrayList<Integer> erreserbaidlist=new ArrayList<Integer>();
	
	public MyRidesGUI(Driver d) {
		
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		BLFacade facade = MainGUI.getBusinessLogic();
		Driver driver=(Driver) facade.ErabiltzaileaFreskatu(d);
		
		JButton Back = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				
				DriverMenuaGUI a=new DriverMenuaGUI(driver);
				a.setVisible(true);
			}
		});
		Back.setBounds(26, 12, 105, 27);
		getContentPane().add(Back);
		
		JLabel BidaiakText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Rides")); //$NON-NLS-1$ //$NON-NLS-2$
		BidaiakText.setBounds(39, 70, 105, 17);
		getContentPane().add(BidaiakText);
		
		JComboBox BidaiakComboBox = new JComboBox();
		BidaiakComboBox.setBounds(186, 65, 406, 26);
		getContentPane().add(BidaiakComboBox);
		
		JButton ErabiltzaileakIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MyRides.BidaiaAukeratu")); //$NON-NLS-1$ //$NON-NLS-2$
		
		ErabiltzaileakIkusi.setBounds(218, 121, 196, 27);
		getContentPane().add(ErabiltzaileakIkusi);
		
		JComboBox travelerComboBox = new JComboBox();
		travelerComboBox.setBounds(186, 185, 406, 27);
		getContentPane().add(travelerComboBox);
		
		JLabel Erabiltzaileak = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		Erabiltzaileak.setBounds(39, 195, 92, 13);
		getContentPane().add(Erabiltzaileak);
		
		JButton Baloratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BaloratuEtaErreklamatu")); //$NON-NLS-1$ //$NON-NLS-2$ 
		Baloratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(travelerComboBox.getSelectedItem()!=null && travelerComboBox.getSelectedIndex()!=-1) {
				Ride r = (Ride) BidaiakComboBox.getSelectedItem();
				Date gaur = new Date();
				if(!r.getDate().before(gaur)) { 
				int erreserbaid=erreserbaidlist.get(travelerComboBox.getSelectedIndex());
				
				Erreserba erreserba=facade.erreserbaBilatu(erreserbaid);
				
				
				System.out.println(facade.isTravelerBaloratua(erreserbaid));
				facade.driverBaloratu(erreserbaid);
				System.out.println(facade.isTravelerBaloratua(erreserbaid));
				if(facade.isTravelerBaloratua(erreserbaid)) 
					facade.ErreserbaKendu(erreserbaid);
				User u = facade.erabiltzaileaBilatu((String)travelerComboBox.getSelectedItem());
				
				BaloratuGUI baloratzera = new BaloratuGUI(driver,u,r,erreserba);
				baloratzera.setVisible(true);
				itxi_lehioa(e);
				
				}
				else {
					System.out.println("Ezin duzu gaur baino beranduago izango den bidai baten konfirmazioa egin");
				}
				
				
				}
			}
		});
		Baloratu.setBounds(218, 239, 196, 27);
		getContentPane().add(Baloratu);
		
		
		for(Ride bidaia:driver.getRides()) {
			BidaiakComboBox.addItem(bidaia);
		}
		
		
		
		ErabiltzaileakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(BidaiakComboBox.getItemCount()!=0) {
					erreserbaidlist=new ArrayList<Integer>();
					travelerComboBox.removeAllItems();
					if(BidaiakComboBox.getSelectedItem()!=null) {
						Ride bidaia = (Ride) BidaiakComboBox.getSelectedItem();
						List<Erreserba> erreserbaList = facade.bidaiBatenErreserbak(bidaia);
						for(Erreserba e : erreserbaList) {
							if(e.getDbaloratua()==false &&e.getOnartuta()==true) {
							erreserbaidlist.add(e.getId());
							Traveler erabiltzailea = e.getTraveler();
							if(erabiltzailea != null) {
								String izena = erabiltzailea.toString2();
								travelerComboBox.addItem(izena);
							}
							}							
						}
					}
				}
			}
		});
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}