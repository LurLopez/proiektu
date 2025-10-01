package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.Alerta;
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


public class AlertakIkusiGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	private BLFacade facade = MainGUI.getBusinessLogic();
	private JComboBox BidaiakErreserbatu = new JComboBox();
	private JComboBox ErreserbakComboBox = new JComboBox();
	private JComboBox AlertakIkusi;
	private JLabel AlertaHonekEzDuBidairik;
	private Traveler traveler;
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	
	public AlertakIkusiGUI(Traveler t) {
		
		
		
		
		
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
		
		JLabel BidaiaAukeratutxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BidaiaBaieztatuGUI.ErreserbaAukeratuText")); //$NON-NLS-1$ //$NON-NLS-2$
		BidaiaAukeratutxt.setBounds(26, 172, 142, 17);
		getContentPane().add(BidaiaAukeratutxt);
		
		AlertakIkusi = new JComboBox();
		AlertakIkusi.setBounds(186, 75, 309, 26);
		getContentPane().add(AlertakIkusi);
		
		JButton Erreserbatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaBaieztatuGUI.konfirmatu"));
		Erreserbatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(BidaiakErreserbatu.getItemCount()!=0) {
					
					Ride bidaia= (Ride) BidaiakErreserbatu.getSelectedItem();
					
					if(facade.LibreDaudenEserlekuKopurua(bidaia.getRideNumber())!=0) {
						System.out.println(bidaia.getRideNumber());
						facade.bidaiaErreserbatu(bidaia.getRideNumber(),t, bidaia.getPrice());
					}else {
						System.out.println("Ez da eserleku librerik geratzen");
					}
				}
				else System.out.println("Ez duzu bidairik alerta horretan");
			}
		});
		Erreserbatu.setBounds(186, 271, 105, 27);
		getContentPane().add(Erreserbatu);
		
		
		BidaiakErreserbatu.setBounds(186, 167, 309, 26);
		getContentPane().add(BidaiakErreserbatu);
		alertak_aktualizatu();
		
		JButton AlertaIkusibtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.BidaiarenErreserbak")); //$NON-NLS-1$ //$NON-NLS-2$
		AlertaIkusibtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AlertaHonekEzDuBidairik.setText("");
				BidaiakErreserbatu.removeAllItems();
				if(AlertakIkusi.getSelectedItem()!=null) {
					Alerta alerta=(Alerta) AlertakIkusi.getSelectedItem();
					List<Ride> bidaiak = facade.GetAlertaBatenBidaiak(alerta);
					if(bidaiak != null) {
						for(Ride r : bidaiak) {
							BidaiakErreserbatu.addItem(r);
						}
					}else {
						AlertaHonekEzDuBidairik.setText("Alerta honek ez du bidairik");
					}
				}

				
			}
		});
		AlertaIkusibtn.setBounds(186, 113, 184, 27);
		getContentPane().add(AlertaIkusibtn);
		
		JLabel AlertaAukeratuText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.ErreserbaAukeratuText")); //$NON-NLS-1$ //$NON-NLS-2$
		AlertaAukeratuText.setBounds(26, 80, 126, 17);
		getContentPane().add(AlertaAukeratuText);
		
		AlertaHonekEzDuBidairik = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		AlertaHonekEzDuBidairik.setBounds(186, 218, 309, 13);
		getContentPane().add(AlertaHonekEzDuBidairik);
		
		JLabel lblNewLabel = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(226, 19, 268, 13);
		getContentPane().add(lblNewLabel);
		
		
		int j = facade.zenbatBidaiAlertetan(t);
		lblNewLabel.setText(j + " bidai daude zure alertetan");		
			
	}
		

	
	
	
	private void alertak_aktualizatu() {
		AlertakIkusi.removeAllItems();
		for(Alerta a:facade.GetTravelerBatenAlertak(traveler)) {
			AlertakIkusi.addItem(a);
		}
	}
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}