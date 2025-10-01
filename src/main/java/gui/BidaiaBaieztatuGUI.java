package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.User;
import domain.Driver;
import domain.Erreserba;
import domain.FuntzioLaguntzaileak;
import domain.Mugimendua;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class BidaiaBaieztatuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	private BLFacade facade = MainGUI.getBusinessLogic();
	private JComboBox BidaiaOnartuak = new JComboBox();
	private JComboBox ErreserbakComboBox = new JComboBox();
	private Traveler traveler;
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	public BidaiaBaieztatuGUI(Traveler t) {
		
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		traveler=(Traveler) facade.ErabiltzaileaFreskatu(t);
		
		JButton ATZERA = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		ATZERA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				TravelerMenuaGUI lehioa=new TravelerMenuaGUI(t);
				lehioa.setVisible(true);
			}
		});
		
		
		ATZERA.setBounds(26, 12, 105, 27);
		getContentPane().add(ATZERA);
		
		JLabel ErreserbaAukeratuText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BidaiaBaieztatuGUI.ErreserbaAukeratuText")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreserbaAukeratuText.setBounds(26, 172, 105, 17);
		getContentPane().add(ErreserbaAukeratuText);
		
		JComboBox ErreserbakComboBox = new JComboBox();
		ErreserbakComboBox.setBounds(186, 167, 309, 26);
		getContentPane().add(ErreserbakComboBox);
		
		JButton konfirmatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaBaieztatuGUI.konfirmatu"));
		konfirmatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ErreserbakComboBox.getItemCount()!=0) {
					
					Erreserba erreserba= (Erreserba) ErreserbakComboBox.getSelectedItem();
					
					int erreserbaID= erreserba.getId();
					int ridekode=facade.ErreserbarenRideKodea(erreserbaID);
					Ride bidaia = facade.bilatuBidaiaKodearekin(ridekode);
					ErreserbakComboBox.removeItem(ErreserbakComboBox.getSelectedItem());
					Driver gidari = facade.gidariaBilatuBidaiKode(ridekode);
					Date gaur = new Date();
					if(!bidaia.getDate().before(gaur)) {    //bideoan ! erabili dugu gauzak errazteko
						
						facade.travelerBaloratu(erreserba.getId());
						if(facade.isDriverBaloratua(erreserbaID)) 
						facade.ErreserbaKendu(erreserbaID);
						
						
						
						BaloratuGUI baloratzera = new BaloratuGUI((User)t, (User)gidari, bidaia,erreserba);
						itxi_lehioa(arg0);
						baloratzera.setVisible(true);
						
						if(ErreserbakComboBox.getItemCount()==0) bidaiak_aktualizatu();
						
					}else {
						System.out.println("Ezin duzu gaur baino beranduago izango den bidai baten konfirmazioa egin");
					}
					
					
					
					
				}
				else System.out.println("Ez da eskaerarik gelditzen");
			}
		});
		konfirmatu.setBounds(186, 271, 105, 27);
		getContentPane().add(konfirmatu);
		
		
		BidaiaOnartuak.setBounds(186, 75, 309, 26);
		getContentPane().add(BidaiaOnartuak);
		bidaiak_aktualizatu();
		
		JButton ErreserbaOnartuak = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.BidaiarenErreserbak")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreserbaOnartuak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ErreserbakComboBox.removeAllItems();
				if(BidaiaOnartuak.getSelectedItem()!=null) {
					Ride bidaia=(Ride) BidaiaOnartuak.getSelectedItem();
					for(Erreserba e:f.GetTravelerBatenBidaiaKonkretuBatenErreserbaOnartuak(traveler, bidaia)) {
						if(e.getTbaloratua()==false) 
						ErreserbakComboBox.addItem(e);
					}
				}

				
			}
		});
		ErreserbaOnartuak.setBounds(186, 113, 184, 27);
		getContentPane().add(ErreserbaOnartuak);
		
		JLabel BidaiaAukeratuText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.ErreserbaAukeratuText")); //$NON-NLS-1$ //$NON-NLS-2$
		BidaiaAukeratuText.setBounds(26, 80, 126, 17);
		getContentPane().add(BidaiaAukeratuText);
		
		
		
		
			
	}
		

	
	
	
	private void bidaiak_aktualizatu() {
		BidaiaOnartuak.removeAllItems();
		for(Ride r:f.GetTravelerBatenBidaiaOnartuak(traveler)) {
			BidaiaOnartuak.addItem(r);
		}
	}
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}