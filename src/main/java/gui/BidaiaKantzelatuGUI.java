package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.Driver;
import domain.Mugimendua;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class BidaiaKantzelatuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	
	
	public BidaiaKantzelatuGUI(Driver d) {
		
		
		
		
		
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
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				
				DriverMenuaGUI a=new DriverMenuaGUI(driver);
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(26, 12, 105, 27);
		getContentPane().add(btnNewButton);
		
		JLabel BidaiakText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Rides")); //$NON-NLS-1$ //$NON-NLS-2$
		BidaiakText.setBounds(39, 70, 105, 17);
		getContentPane().add(BidaiakText);
		
		JComboBox BidaiakComboBox = new JComboBox();
		BidaiakComboBox.setBounds(186, 65, 309, 26);
		getContentPane().add(BidaiakComboBox);
		
		JButton BidaiaKantzelatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaKantzelatu")); //$NON-NLS-1$ //$NON-NLS-2$
		
		BidaiaKantzelatu.setBounds(186, 156, 105, 27);
		getContentPane().add(BidaiaKantzelatu);
		
		
		for(Ride bidaia:driver.getRides()) {
			BidaiakComboBox.addItem(bidaia.toString());
		}
		
		
		
		BidaiaKantzelatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(BidaiakComboBox.getItemCount()!=0) {
					String aukerastring = (String) BidaiakComboBox.getSelectedItem();                                   //Ondoko hiru lerro hauek, toStringetik bidaiaren kodea lortzeko dira
					String id = aukerastring.split(";;")[0];
					int bidaia= Integer.parseInt(id) ;
					facade.BidaiaKantzelatu(bidaia,driver);
					Driver driver=(Driver) facade.ErabiltzaileaFreskatu(d);
					
					BidaiakComboBox.removeAllItems();
					for(Ride bidai:driver.getRides()) {
						BidaiakComboBox.addItem(bidai.toString());
					}
				}
			}
		});
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}