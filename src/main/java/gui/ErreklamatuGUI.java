package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.User;
import domain.Driver;
import domain.Erreklamazio;
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


public class ErreklamatuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField ErreklamazioField;
	
	private Traveler t;
	private BLFacade facade = MainGUI.getBusinessLogic();
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	private JTextField GaiaField;
	public ErreklamatuGUI(User nork, User nori, Ride r,Erreserba erreserba) {
		
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		
		JLabel ErreklamazioLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BaloratuGUI.IdatziHemen"));
		ErreklamazioLabel.setBounds(10, 127, 110, 17);
		getContentPane().add(ErreklamazioLabel);
		
		ErreklamazioField = new JTextField();
		ErreklamazioField.setBounds(138, 87, 302, 77);
		getContentPane().add(ErreklamazioField);
		ErreklamazioField.setColumns(10);
		
		JLabel Errorea = new JLabel("");
		Errorea.setBounds(76, 233, 473, 77);
		getContentPane().add(Errorea);
		
		JButton Erreklamatubtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErreklamatuGUI.Erreklamatu"));
		Erreklamatubtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!ErreklamazioField.getText().equals("") && !GaiaField.getText().equals("")) {
					facade.setErreklamatuta(erreserba.getId());
					String gaia=GaiaField.getText();
					String testua = ErreklamazioField.getText();
					Erreklamazio erreklamazioa=facade.ErreklamazioaSortu(gaia,testua,nork,nori, r);
					itxi_lehioa(arg0);
					if(nork instanceof Traveler) {
						Traveler t= (Traveler) nork;
						BidaiaBaieztatuGUI lehioa=new BidaiaBaieztatuGUI(t);
						lehioa.setVisible(true);
					}
					else if(nork instanceof Driver) {
						Driver d=(Driver) nork;
						DriverMenuaGUI lehioa=new DriverMenuaGUI(d);
						lehioa.setVisible(true);
					}
					
					
				}
				else {
					System.out.println("Balio bat falta da");
				}
				
				
				
				
				
				
			}			
			
		});
		Erreklamatubtn.setBounds(311, 194, 148, 27);
		getContentPane().add(Erreklamatubtn);
		
		
		
		
		JButton EzErreklamatubtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErreklamatuGUI.ezErreklamatu"));
		EzErreklamatubtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nork instanceof Traveler) {
					itxi_lehioa(arg0);
					BidaiaBaieztatuGUI lehioa=new BidaiaBaieztatuGUI((Traveler)nork);
					facade.diruaSartu(r.getPrice(), nori);
					lehioa.setVisible(true);
				}else {
					itxi_lehioa(arg0);
					MyRidesGUI hau=new MyRidesGUI((Driver)nork);
					hau.setVisible(true);
				}
			}
		});
		EzErreklamatubtn.setBounds(76, 194, 163, 27);
		getContentPane().add(EzErreklamatubtn);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BaloratuGUI.IdatziHemen"));
		lblNewLabel.setBounds(44, 129, 45, 0);
		getContentPane().add(lblNewLabel);
		
		JLabel Gaialbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErreklamatuGUI.Gaia")); //$NON-NLS-1$ //$NON-NLS-2$
		Gaialbl.setBounds(30, 37, 60, 17);
		getContentPane().add(Gaialbl);
		
		GaiaField = new JTextField();
		GaiaField.setColumns(10);
		GaiaField.setBounds(138, 29, 302, 34);
		getContentPane().add(GaiaField);
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}