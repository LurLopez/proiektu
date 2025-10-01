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

public class ProfilaIkusiGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private BLFacade facade = MainGUI.getBusinessLogic();
	private JComboBox BalorazioakComboBox = new JComboBox();
	private User d;
	private User ikusi;
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	JLabel Nota = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	
	public ProfilaIkusiGUI(User d, User ikusi) {
		this.d=d;
		this.ikusi = ikusi;
			
			
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(1);
				}
			});
			
			this.getContentPane().setLayout(null);
			this.setSize(new Dimension(604, 370));
			
			
			getContentPane().setLayout(null);
			
			
			
			
			Nota.setBounds(150, 61, 60, 17);
			getContentPane().add(Nota);
			
			JLabel BalorazioakText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ProfilaIkusiGUI.Balorazioak")); //$NON-NLS-1$ //$NON-NLS-2$
			BalorazioakText.setBounds(260, 127, 72, 17);
			getContentPane().add(BalorazioakText);
			
			
			BalorazioakComboBox.setBounds(10, 157, 570, 26);
			getContentPane().add(BalorazioakComboBox);
			
			BalorazioakComboBoxAktualizatu();
			

			JTextArea textArea = new JTextArea();
			textArea.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
			textArea.setBounds(10, 227, 570, 96);
			getContentPane().add(textArea);
			
			JLabel Notatxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ProfilaIkusiGUI.Nota"));
			Notatxt.setBounds(25, 61, 117, 17);
			getContentPane().add(Notatxt);
			
			JLabel Izenatxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ProfilaIkusiGUI.Izena"));
			Izenatxt.setBounds(23, 108, 117, 17);
			getContentPane().add(Izenatxt);
			
			JLabel Errorea = new JLabel("");
			Errorea.setBounds(150, 163, 60, 17);
			getContentPane().add(Errorea);	
			
			
			
			
			JButton ATZERA = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
			ATZERA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					itxi_lehioa(arg0);
					
					if(d==null) {
						AdministratzaileaGUI lehioa=new AdministratzaileaGUI(new Administratzailea());
						lehioa.setVisible(true);
					}
					
					if(d instanceof Driver) {
						DriverMenuaGUI lehioa=new DriverMenuaGUI((Driver) d);
						lehioa.setVisible(true);
					}else if(d instanceof Traveler) {
						TravelerMenuaGUI erreserbatu = new TravelerMenuaGUI((Traveler) d);
						erreserbatu.setVisible(true);
					}
					
					
				}
			});
			ATZERA.setBounds(23, 7, 105, 27);
			getContentPane().add(ATZERA);
			
			JLabel Izena = new JLabel(ikusi.getName()); //$NON-NLS-1$ //$NON-NLS-2$
			Izena.setBounds(152, 110, 60, 13);
			getContentPane().add(Izena);
			
			JButton irakurri = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ProfilaIkusiGUI.Irakurri")); //$NON-NLS-1$ //$NON-NLS-2$
			irakurri.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(BalorazioakComboBox.getSelectedItem() != null) {
						Balorazioa balorazio = (Balorazioa) BalorazioakComboBox.getSelectedItem();
						textArea.setText(balorazio.getTestua());
					}
					
				}
			});
			irakurri.setBounds(204, 193, 172, 21);
			getContentPane().add(irakurri);
			
			
			
			
			
			
			
		}
		
		
	private void BalorazioakComboBoxAktualizatu() {
		float kopurua=0;
		float guztira=0;
		BalorazioakComboBox.removeAllItems();
		ArrayList<Balorazioa> mug = facade.balorazioakKargatu(ikusi);
		for(Balorazioa m:mug) {
			BalorazioakComboBox.addItem(m);
			guztira=guztira+m.getNota();
			kopurua++;
		}
		if(kopurua==0) {
			Nota.setText("Ez duzu baloraziorik");
		}
		else {
			float batezbestekonota=guztira/kopurua;
			Nota.setText(String.valueOf(Math.round(batezbestekonota * 10) / 10f));
		}
	}
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}