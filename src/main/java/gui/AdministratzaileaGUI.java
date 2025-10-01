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


public class AdministratzaileaGUI extends JFrame {
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
	
	public AdministratzaileaGUI(Administratzailea a) {
		
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		JComboBox ErreklamazioakComboBox = new JComboBox();
		
		ErreklamazioakComboBox.setBounds(141, 24, 451, 26);
		getContentPane().add(ErreklamazioakComboBox);
		
		JLabel Erreklamazioaklbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BidaiaBaieztatuGUI.ErreserbaAukeratuText"));
		Erreklamazioaklbl.setBounds(25, 29, 60, 17);
		getContentPane().add(Erreklamazioaklbl);
		
		JTextPane MezuarenTestua = new JTextPane();
		MezuarenTestua.setEditable(false);
		MezuarenTestua.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		MezuarenTestua.setBounds(141, 170, 451, 85);
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
		MezuakComboBox.setBounds(141, 120, 451, 26);
		getContentPane().add(MezuakComboBox);
		
		JLabel Mezuaklbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdministratzaileaGUI.Mezuak")); //$NON-NLS-1$ //$NON-NLS-2$
		Mezuaklbl.setBounds(25, 125, 60, 17);
		getContentPane().add(Mezuaklbl);
		
		
		
		ArrayList<Erreklamazio> erreklamazioak=facade.ErreklamazioakKargatu();
		
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
		ErreklamazioaAukeratuBtn.setBounds(239, 73, 105, 27);
		getContentPane().add(ErreklamazioaAukeratuBtn);
		
		JButton MezuaGehituBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdministratzaileaGUI.MezuaGehitu")); //$NON-NLS-1$ //$NON-NLS-2$
		MezuaGehituBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(ErreklamazioakComboBox.getSelectedItem()!=null) {
				itxi_lehioa(arg0);
				int i=ErreklamazioakComboBox.getSelectedIndex();
				Erreklamazio erreklamazioa=erreklamazioak.get(i);
				User erreklamatzailea=erreklamazioa.getNork();
				User erreklamatua=erreklamazioa.getNori();
				AdminMezuaGehituGUI lehioa=new AdminMezuaGehituGUI(a,erreklamatzailea,erreklamatua,erreklamazioa);
				lehioa.setVisible(true);
				}
				else {
					System.out.println("Ez dago Erreklamaziorik");
				}
				
			}
		});
		MezuaGehituBtn.setBounds(125, 275, 163, 27);
		getContentPane().add(MezuaGehituBtn);
		
		JButton ErabakiaHartuBtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdministratzaileaGUI.ErabakiaHartu")); //$NON-NLS-1$ //$NON-NLS-2$
		ErabakiaHartuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ErreklamazioakComboBox.getSelectedItem()!=null) {
				itxi_lehioa(arg0);
				int i=ErreklamazioakComboBox.getSelectedIndex();
				Erreklamazio erreklamazioa=erreklamazioak.get(i);
				User erreklamatzailea=erreklamazioa.getNork();
				User erreklamatua=erreklamazioa.getNori();
				ErabakiaHartuGUI lehioa=new ErabakiaHartuGUI(a,erreklamazioa,erreklamatzailea,erreklamatua);
				lehioa.setVisible(true);
			}else System.out.println("Ez dago Erreklamaziorik");
				}
		});
		ErabakiaHartuBtn.setBounds(325, 275, 187, 27);
		getContentPane().add(ErabakiaHartuBtn);
		
		
		ErreklamazioakComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MezuarenTestua.setText("");
			}
		});
		
		
		
		
		
		for(int i=0;i<erreklamazioak.size();i++) {
			ErreklamazioakComboBox.addItem(erreklamazioak.get(i).toString());
		}
		
	
		
		
		
	}

	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}