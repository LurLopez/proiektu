package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.Driver;
import domain.Erreserba;
import domain.FuntzioLaguntzaileak;
import domain.Mugimendua;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class ErreserbenEgoeraIkusiGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	
	public ErreserbenEgoeraIkusiGUI(Traveler t) {
		
		
		
		
		
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
		Traveler traveler=(Traveler) facade.ErabiltzaileaFreskatu(t);
		
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
		
		JLabel ErreserbaAukeratuText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.ErreserbaAukeratuText")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreserbaAukeratuText.setBounds(24, 70, 150, 17);
		getContentPane().add(ErreserbaAukeratuText);
		
		JComboBox ErreserbakComboBox = new JComboBox();
		ErreserbakComboBox.setBounds(192, 178, 309, 26);
		getContentPane().add(ErreserbakComboBox);
		
		JLabel ErreserbaEgoeraText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.BidaiarenErreserbak")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreserbaEgoeraText.setBounds(22, 185, 152, 13);
		getContentPane().add(ErreserbaEgoeraText);
		
		JLabel ErreserbaEgoeraAdierazi = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		ErreserbaEgoeraAdierazi.setBounds(192, 229, 309, 13);
		getContentPane().add(ErreserbaEgoeraAdierazi);
		
		JComboBox Bidaiak = new JComboBox();
		Bidaiak.setBounds(192, 65, 309, 26);
		getContentPane().add(Bidaiak);
		
		JButton ErreserbakBegiratu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EskaeraOnartuGUI.EskaeraLista")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreserbakBegiratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(Bidaiak.getSelectedItem()!=null) {
					String aukeratua = (String) Bidaiak.getSelectedItem().toString();
					
					
					String[] atalak = aukeratua.split(";");
					
					
					
					
					
					int bidaikodea = Integer.parseInt(atalak[0].trim());
					ErreserbakComboBox.removeAllItems();
					for(Erreserba e: facade.GetTravelerBatenErreserbak(traveler)) {
						if(e.getDriverKodea()==bidaikodea) {
							boolean egoera=e.getOnartuta();
							String egoeraString;
							if(egoera) egoeraString= ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.Onartua");
							else egoeraString=ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.Onartugabea");
								
							String gehitu=e.toString() +" "+ ResourceBundle.getBundle("Etiquetas").getString("ErreserbenEgoeraIkusiGUI.ErreserbaEgoeraText") +" " + egoeraString;
							ErreserbakComboBox.addItem(gehitu);
						}
					}
				}
			}
		});
		ErreserbakBegiratu.setBounds(244, 112, 194, 27);
		getContentPane().add(ErreserbakBegiratu);
		
		for(Ride bidaia:f.GetTravelerBatenBidaiak(traveler)) {
			Bidaiak.addItem(bidaia);
		}
		
		
		
	
		
	}
		

	
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}
