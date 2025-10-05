package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.User;
import domain.Administratzailea;
import domain.Alerta;
import domain.Driver;
import domain.Erreklamazio;
import domain.Erreserba;
import domain.FuntzioLaguntzaileak;
import domain.Mezua;
import domain.Mugimendua;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class ErreklamazioakIkusiGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	private BLFacade facade = MainGUI.getBusinessLogic();
	private JComboBox BidaiakErreserbatu = new JComboBox();
	private JComboBox ErreserbakComboBox = new JComboBox();
	private JComboBox AlertakIkusi;
	private JLabel AlertaHonekEzDuBidairik;
	private Traveler traveler;
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	
	private ArrayList<Mezua> mezulista=new ArrayList<Mezua>();
	private ArrayList<Erreklamazio> erreklamazioak;
	private JComboBox ErreklamazioakComboBox = new JComboBox();
	JRadioButton ErreklamatzaileaAukera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Erreklamatzailea")); //$NON-NLS-1$ //$NON-NLS-2$
	JRadioButton ErreklamatuaAukera = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Erreklamatua")); //$NON-NLS-1$ //$NON-NLS-2$
	public ErreklamazioakIkusiGUI(User erabiltzailea) {
		
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		
		ErreklamatzaileaAukera.setSelected(true);
		Erreklamazioakgehitu(true,erabiltzailea);
		
		ErreklamazioakComboBox.setBounds(141, 54, 451, 26);
		getContentPane().add(ErreklamazioakComboBox);
		
		JLabel Erreklamazioaklbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BidaiaBaieztatuGUI.ErreserbaAukeratuText"));
		Erreklamazioaklbl.setBounds(38, 59, 60, 17);
		getContentPane().add(Erreklamazioaklbl);
		
		JTextPane MezuarenTestua = new JTextPane();
		MezuarenTestua.setEditable(false);
		MezuarenTestua.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		MezuarenTestua.setBounds(118, 202, 451, 85);
		getContentPane().add(MezuarenTestua);
		
		
		JComboBox MezuakComboBox = new JComboBox();
		MezuakComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				int mezuindize=MezuakComboBox.getSelectedIndex();
				
				
				if(mezuindize==-1) {
					mezuindize=0;
				}
				
					Mezua Mezu=mezulista.get(mezuindize);
					MezuarenTestua.setText(Mezu.getMezua());
					
				
			}
		});
		MezuakComboBox.setBounds(141, 138, 451, 26);
		getContentPane().add(MezuakComboBox);
		
		JLabel Mezuaklbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdministratzaileaGUI.Mezuak")); //$NON-NLS-1$ //$NON-NLS-2$
		Mezuaklbl.setBounds(38, 143, 60, 17);
		getContentPane().add(Mezuaklbl);
		
		
		
		
		
		JButton ErreklamazioaAukeratuBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdministratzaileaGUI.ErreklamazioaAukeratu")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreklamazioaAukeratuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ErreklamazioakComboBox.getSelectedItem()!=null) {
				int i=ErreklamazioakComboBox.getSelectedIndex();
				Erreklamazio erreklamazioa=erreklamazioak.get(i);
				
				mezulista=erreklamazioa.getMezuList();
				MezuakComboBox.removeAllItems();
				
				for(Mezua mezu:mezulista) {
					MezuakComboBox.addItem(mezu.ToString());
				}
				
				Mezua mezua=mezulista.get(0);
				MezuarenTestua.setText(mezua.getMezua());
				}
				else System.out.println("Ez dago Erreklamaziorik");
			}
		});
		ErreklamazioaAukeratuBtn.setBounds(302, 99, 105, 27);
		getContentPane().add(ErreklamazioaAukeratuBtn);
		
		JButton MezuaGehituBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdministratzaileaGUI.MezuaGehitu")); //$NON-NLS-1$ //$NON-NLS-2$
		MezuaGehituBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(ErreklamazioakComboBox.getSelectedItem()!=null&&(ErreklamatuaAukera.isSelected()==true||ErreklamatzaileaAukera.isSelected()==true)) {
				itxi_lehioa(arg0);
				int i=ErreklamazioakComboBox.getSelectedIndex();
				Erreklamazio erreklamazioa=erreklamazioak.get(i);
				User erreklamatzailea=erreklamazioa.getNork();
				User erreklamatua=erreklamazioa.getNori();
				
				if(ErreklamatzaileaAukera.isSelected()) {
					ErabiltzaileMezuaGehituGUI lehioa=new ErabiltzaileMezuaGehituGUI(erabiltzailea,erreklamatua,erreklamazioa);
					lehioa.setVisible(true);
				}
				if(ErreklamatuaAukera.isSelected()) {
					ErabiltzaileMezuaGehituGUI lehioa=new ErabiltzaileMezuaGehituGUI(erabiltzailea,erreklamatzailea,erreklamazioa);
					lehioa.setVisible(true);
				}
				
				else {
					System.out.println("Ez dago Erreklamaziorik");
				}
				}
			}
		});
		MezuaGehituBtn.setBounds(185, 299, 222, 27);
		getContentPane().add(MezuaGehituBtn);
		
		JButton Backbtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
		Backbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				
				if(erabiltzailea instanceof Traveler) {
				TravelerMenuaGUI lehioa=new TravelerMenuaGUI((Traveler) erabiltzailea); 
				lehioa.setVisible(true);}
				
				else {
				DriverMenuaGUI lehioa=new DriverMenuaGUI((Driver) erabiltzailea);
				lehioa.setVisible(true);} 
					
				
			}
		});
		Backbtn.setBounds(12, 0, 105, 27);
		getContentPane().add(Backbtn);
		
		
		ErreklamatuaAukera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MezuakComboBox.removeAllItems();
				ErreklamazioakComboBox.removeAllItems();
				Erreklamazioakgehitu(false,erabiltzailea);
				if(ErreklamatzaileaAukera.isSelected()) {
					ErreklamatzaileaAukera.setSelected(false);
				}
			}
		});
		
		ErreklamatzaileaAukera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MezuakComboBox.removeAllItems();
				ErreklamazioakComboBox.removeAllItems();
				Erreklamazioakgehitu(true,erabiltzailea);
				if(ErreklamatuaAukera.isSelected()) {
					
					ErreklamatuaAukera.setSelected(false);
				}
			}
		});
		ErreklamatzaileaAukera.setBounds(185, 21, 130, 25);
		getContentPane().add(ErreklamatzaileaAukera);
		
		
		ErreklamatuaAukera.setBounds(319, 21, 130, 25);
		getContentPane().add(ErreklamatuaAukera);
		
	
		
		
		
		ErreklamazioakComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MezuarenTestua.setText("");
			}
		});
		
		
		
		
		
		
	
		
		
		
	}

	
	public void Erreklamazioakgehitu(boolean nork,User norkuser) {
		erreklamazioak=(ArrayList<Erreklamazio>) facade.ErreklamazioakKargatuErabiltzaile(norkuser);
		System.out.println(erreklamazioak.size() +"aaaa");
		if(erreklamazioak!=null) {
		if(nork) {
		for(int i=0;i<erreklamazioak.size();i++) {
			if(erreklamazioak.get(i).getNork().equals(norkuser))
			ErreklamazioakComboBox.addItem(erreklamazioak.get(i).toString());
		}
		}
		else {
			for(int i=0;i<erreklamazioak.size();i++) {
				if(!erreklamazioak.get(i).getNork().equals(norkuser))
				ErreklamazioakComboBox.addItem(erreklamazioak.get(i).toString()); 
		}
		}
		}
		}
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}