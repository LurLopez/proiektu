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


public class BaloratuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuBalorazioa;
	
	private Traveler t;
	private BLFacade facade = MainGUI.getBusinessLogic();
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	private JComboBox NotaAukeratuBox;
	public BaloratuGUI(User nork, User nori, Ride r,Erreserba erreserba) {
		
		NotaAukeratuBox = new JComboBox();
		NotaAukeratuBox.setBounds(162, 47, 105, 21);
		getContentPane().add(NotaAukeratuBox);
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		this.t=t;
		notakHasieratu();
		
		JLabel notaTxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BaloratuGUI.Nota"));
		notaTxt.setBounds(23, 49, 117, 17);
		getContentPane().add(notaTxt);
		
		JLabel SartuTestuatxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BaloratuGUI.IdatziHemen"));
		SartuTestuatxt.setBounds(23, 108, 60, 17);
		getContentPane().add(SartuTestuatxt);
		
		SartuBalorazioa = new JTextField();
		SartuBalorazioa.setBounds(152, 106, 302, 77);
		getContentPane().add(SartuBalorazioa);
		SartuBalorazioa.setColumns(10);
		
		JLabel Errorea = new JLabel("");
		Errorea.setBounds(72, 224, 60, 17);
		getContentPane().add(Errorea);
		
		JButton BaloratuBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BaloratuGUI.Baloratu"));
		BaloratuBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String testua = SartuBalorazioa.getText();
				int nota = (int) NotaAukeratuBox.getSelectedItem();
				
				facade.balorazioaSortu(testua, nota, nork, nori);
				
				if(erreserba.getErreklamatuta()==false) {
				ErreklamatuGUI erreklam = new ErreklamatuGUI(nork, nori, r,erreserba);
				itxi_lehioa(arg0);
				erreklam.setVisible(true);
				}
				else {
					if(nork instanceof Traveler) {
						Traveler t= (Traveler) nork;
						BidaiaBaieztatuGUI lehioa=new BidaiaBaieztatuGUI(t);
						lehioa.setVisible(true);
						itxi_lehioa(arg0);
					}
					else if(nork instanceof Driver) {
						Driver d=(Driver) nork;
						DriverMenuaGUI lehioa=new DriverMenuaGUI(d);
						lehioa.setVisible(true);
						itxi_lehioa(arg0);
					}
				}
				
			}			
			
		});
		BaloratuBotoia.setBounds(306, 193, 148, 27);
		getContentPane().add(BaloratuBotoia);
		
		
		
		
		JButton EzBaloratubtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BaloratuGUI.EzBaloratu"));
		EzBaloratubtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(erreserba.getErreklamatuta()==false) {
				itxi_lehioa(arg0);
				ErreklamatuGUI lehioa=new ErreklamatuGUI(nork,nori,r,erreserba);
				lehioa.setVisible(true);
				}
				else {
					if(nork instanceof Traveler) {
						Traveler t= (Traveler) nork;
						BidaiaBaieztatuGUI lehioa=new BidaiaBaieztatuGUI(t);
						lehioa.setVisible(true);
						itxi_lehioa(arg0);
					}
					else if(nork instanceof Driver) {
						Driver d=(Driver) nork;
						DriverMenuaGUI lehioa=new DriverMenuaGUI(d);
						lehioa.setVisible(true);
						itxi_lehioa(arg0);
					}
				}
			}
		});
		EzBaloratubtn.setBounds(125, 195, 142, 27);
		getContentPane().add(EzBaloratubtn);
		
		
		
		
		
		
		
		
		
		
	}
	
	
	private void notakHasieratu() {
		for(int i = 0; i <= 10; i++) {
			NotaAukeratuBox.addItem(i);
		}
	}
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}