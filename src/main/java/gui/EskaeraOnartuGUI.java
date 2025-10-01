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
import domain.Erreserba;
import domain.Ride;
import domain.User;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class EskaeraOnartuGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private Driver driver;



	public EskaeraOnartuGUI(Driver driver) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		this.driver=driver;
		this.getContentPane().setLayout(null);
		
		JLabel EskaeraOnartutext = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		EskaeraOnartutext.setBounds(230, 30, 132, 17);
		getContentPane().add(EskaeraOnartutext);
		
		JComboBox Bidaiak = new JComboBox();
		Bidaiak.setBounds(210, 59, 374, 26);
		getContentPane().add(Bidaiak);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		
		JLabel EserlekuLibreKopText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NPlaces")); //$NON-NLS-1$ //$NON-NLS-2$
		EserlekuLibreKopText.setBounds(12, 146, 96, 17);
		getContentPane().add(EserlekuLibreKopText);
		
		JLabel EserlekuLibreKopKop = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		EserlekuLibreKopKop.setBounds(105, 146, 60, 17);
		getContentPane().add(EserlekuLibreKopKop);
		
		
		JLabel ErreserbarikEz = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EskaeraOnartuGUI.ErreserbarikEz")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreserbarikEz.setBounds(190, 146, 238, 17);
		getContentPane().add(ErreserbarikEz);
		ErreserbarikEz.setVisible(false);
		
		
		JComboBox Eskaerak = new JComboBox();
		Eskaerak.setBounds(190, 187, 186, 26);
		getContentPane().add(Eskaerak);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));
		for(Ride bidaia: driver.getRides()) {
			Bidaiak.addItem(bidaia.toString());
		}
		
		
		JButton DEUSEZTATU = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EskaeraOnartu.DEUSEZTATU")); //$NON-NLS-1$ //$NON-NLS-2$
		DEUSEZTATU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Eskaerak.getItemCount()!=0) {
					
					String aukerastringeskaera = (String) Eskaerak.getSelectedItem();                                   //Ondoko hiru lerro hauek, toStringetik erreserbaren kodea lortzeko dira
					String id = aukerastringeskaera.split(";;")[0];
					int erreserbaID= Integer.parseInt(id) ;	
					
					
					System.out.println(erreserbaID);
					facade.EskaeraDeuseztatu(erreserbaID,0);
					Eskaerak.removeItem(Eskaerak.getSelectedItem());
					}
					else System.out.println("Ez da eskaerarik gelditzen");
				
			}
		});
		DEUSEZTATU.setBounds(125, 225, 105, 27);
		getContentPane().add(DEUSEZTATU);
	
		
		
		JButton ONARTU = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EskaeraOnartu.ONARTU"));//$NON-NLS-1$ //$NON-NLS-2$
		
		ONARTU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Eskaerak.getItemCount()!=0) {
					
					String aukerastringeskaera = (String) Eskaerak.getSelectedItem();                                   //Ondoko hiru lerro hauek, toStringetik erreserbaren kodea lortzeko dira
					String id = aukerastringeskaera.split(";;")[0];
					int erreserbaID= Integer.parseInt(id) ;	
					System.out.println(erreserbaID);
					int ridekode=facade.ErreserbarenRideKodea(erreserbaID);
					
					
					
					System.out.println(facade.LibreDaudenEserlekuKopurua(ridekode));
					if(facade.LibreDaudenEserlekuKopurua(ridekode)==1) {
						facade.ErreserbaOnartu(erreserbaID);
						Eskaerak.removeItem(Eskaerak.getSelectedItem());
						
						System.out.println(Eskaerak.getItemCount());
						
							for (int i = 0; i < Eskaerak.getItemCount(); i++) {
								String eskaeraOsoa = (String)Eskaerak.getItemAt(i);
								String eskaeraID = eskaeraOsoa.split(";;")[0];
								int erreserbaIDint= Integer.parseInt(eskaeraID);
								facade.ErreserbaOnartu(erreserbaIDint);
								Eskaerak.removeItem(Eskaerak.getSelectedItem());
							}
						
						Eskaerak.removeAllItems();
					}
					else {
						facade.ErreserbaOnartu(erreserbaID);
						Eskaerak.removeItem(Eskaerak.getSelectedItem());
					}
					
					EserlekuLibreKopKop.setText(String.valueOf(facade.LibreDaudenEserlekuKopurua(ridekode)));
					
				}
				else System.out.println("Ez da eskaerarik gelditzen");
			}
		});
		
		ONARTU.setBounds(305, 225, 105, 27);
		getContentPane().add(ONARTU);
		
		
		
		JButton EskaeraLista = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
		EskaeraLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eskaerak.removeAllItems();
				
				
				String aukerastring = (String) Bidaiak.getSelectedItem();                                   //Ondoko hiru lerro hauek, toStringetik bidaiaren kodea lortzeko dira
				String id = aukerastring.split(";;")[0];
				int bidaia= Integer.parseInt(id) ;
				try {
				EserlekuLibreKopKop.setText(String.valueOf(facade.LibreDaudenEserlekuKopurua(bidaia)));
				}
				catch(Exception e) {}
				
				
				
				List<Erreserba>erreserbaeskaeralist=facade.EskaeraList(bidaia);
				
				boolean hutsa=true;
				for(Erreserba erreserba:erreserbaeskaeralist) {
					if(erreserba.getErreserbatuta()==true) {
						hutsa=false;
						Eskaerak.addItem(erreserba.toString());
					}
					
				}
				if(hutsa) {
					ErreserbarikEz.setVisible(true);
				}
				else ErreserbarikEz.setVisible(false);
			}
		});
		
		EskaeraLista.setText(ResourceBundle.getBundle("Etiquetas").getString("EskaeraOnartuGUI.EskaeraLista")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$
		EskaeraLista.setBounds(181, 107, 195, 27);
		getContentPane().add(EskaeraLista);
		
		JButton ATZERA = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
		ATZERA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				DriverMenuaGUI lehioa= new DriverMenuaGUI(driver);
				lehioa.setVisible(true);
			}
		});
		ATZERA.setBounds(27, 12, 105, 27);
		getContentPane().add(ATZERA);
		
		JButton profilaIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EskaeraOnartuGUI.TravelerrarenProfila")); //$NON-NLS-1$ //$NON-NLS-2$
		profilaIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((String) Eskaerak.getSelectedItem()!=null) {
				String aukerastringeskaera = (String) Eskaerak.getSelectedItem();                                   //Ondoko hiru lerro hauek, toStringetik erreserbaren kodea lortzeko dira
				String izena = aukerastringeskaera.split(";;")[1].replace(";", "");
				User erabiltzailea = facade.erabiltzaileaBilatu(izena);
				ProfilaIkusiGUI prof = new ProfilaIkusiGUI(driver, erabiltzailea);
				prof.setVisible(true);
				itxi_lehioa(e);
				}
				else {
					System.out.println("Aukeratu eskaeraren bat");
				}
			}
		});
		profilaIkusi.setBounds(151, 278, 277, 21);
		getContentPane().add(profilaIkusi);
		
		
		
	
	}
	
	
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}
