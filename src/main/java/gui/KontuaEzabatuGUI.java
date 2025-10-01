package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import domain.Administratzailea;
import domain.Balorazioa;
import domain.Driver;
import domain.FuntzioLaguntzaileak;
import domain.Mugimendua;

public class KontuaEzabatuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private BLFacade facade = MainGUI.getBusinessLogic();

	private User d;
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	
	public KontuaEzabatuGUI(User d,boolean administratzailea) {
		this.d=d;
			
			
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(1);
				}
			});
			
			this.getContentPane().setLayout(null);
			this.setSize(new Dimension(604, 370));
			
			
			getContentPane().setLayout(null);
			
			JLabel Izenatxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ProfilaIkusiGUI.Izena"));
			Izenatxt.setBounds(23, 69, 117, 17);
			getContentPane().add(Izenatxt);
			
			
			
			
			JButton ATZERA = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
			ATZERA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(administratzailea==false) {
					itxi_lehioa(arg0);
					if(d instanceof Driver) {
						DriverMenuaGUI lehioa=new DriverMenuaGUI((Driver) d);
						lehioa.setVisible(true);
					}else {
						TravelerMenuaGUI erreserbatu = new TravelerMenuaGUI((Traveler) d);
						erreserbatu.setVisible(true);
					}
					}
					else {
						AdministratzaileaGUI lehioa=new AdministratzaileaGUI(new Administratzailea());
						lehioa.setVisible(true);
						itxi_lehioa(arg0);
					}
					
				}
			});
			ATZERA.setBounds(23, 7, 105, 27);
			getContentPane().add(ATZERA);
			
			JLabel Izena = new JLabel(d.getName()); //$NON-NLS-1$ //$NON-NLS-2$
			Izena.setBounds(150, 71, 60, 13);
			getContentPane().add(Izena);
			
			JRadioButton rdbtnKonfirmatu = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("KontuaEzabatuGUI.konfirmatu")); //$NON-NLS-1$ //$NON-NLS-2$
			rdbtnKonfirmatu.setBounds(83, 246, 103, 21);
			getContentPane().add(rdbtnKonfirmatu);
			rdbtnKonfirmatu.setSelected(false);
			
			JLabel ZihurZaude = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
			ZihurZaude.setBounds(83, 154, 445, 36);
			getContentPane().add(ZihurZaude);
			
			JButton ezabatuKontua = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.EzabatuKontua")); //$NON-NLS-1$ //$NON-NLS-2$
			ezabatuKontua.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(rdbtnKonfirmatu.isSelected()) {
						if(d instanceof Traveler) {
							
						}else {
							Driver gidaria = (Driver) d;
							List<Ride> bidaiList= gidaria.getRides();
							for(Ride r : bidaiList) {
								facade.BidaiaKantzelatu(r.getRideNumber(), gidaria);
							}
						}
					facade.erabiltzaileaEzabatu(d);
					if(administratzailea==false) {
					LoginGUI loginera = new LoginGUI();
					loginera.setVisible(true);
					itxi_lehioa(e);
					}
					else {
						AdministratzaileaGUI lehioa=new AdministratzaileaGUI(new Administratzailea());
						lehioa.setVisible(true);
						itxi_lehioa(e);
					}
					}else {
						ZihurZaude.setText(ResourceBundle.getBundle("Etiquetas").getString("KontuaEzabatuGUI.ZihurZaude"));
					}
					
				}
			});
			ezabatuKontua.setBounds(336, 246, 192, 21);
			getContentPane().add(ezabatuKontua);
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}