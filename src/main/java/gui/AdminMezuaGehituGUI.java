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


public class AdminMezuaGehituGUI extends JFrame {
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
	
	public AdminMezuaGehituGUI(Administratzailea nork, User Erreklamatzailea,User Erreklamatua,Erreklamazio erreklamazioa) {
		
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		JLabel Norklbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Nork"));
		Norklbl.setBounds(40, 35, 100, 17);
		getContentPane().add(Norklbl);
		
		JLabel Norilbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Nori")); //$NON-NLS-1$ //$NON-NLS-2$
		Norilbl.setBounds(40, 77, 72, 17);
		getContentPane().add(Norilbl);
		
		JLabel Testualbl = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Mezua")); //$NON-NLS-1$ //$NON-NLS-2$
		Testualbl.setBounds(40, 128, 72, 17);
		getContentPane().add(Testualbl);
		
		JLabel Norklbl2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Administratzailea")); //$NON-NLS-1$ //$NON-NLS-2$
		Norklbl2.setBounds(164, 35, 166, 17);
		getContentPane().add(Norklbl2);
		
		JComboBox NoriComboBox = new JComboBox();
		NoriComboBox.setBounds(160, 72, 184, 26);
		getContentPane().add(NoriComboBox);
		NoriComboBox.addItem(Erreklamatzailea.getName() + " ("+ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Erreklamatzailea")+")");
		NoriComboBox.addItem(Erreklamatua.getName() + " ("+ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Erreklamatua")+")");
		
		JTextPane Testua = new JTextPane();
		Testua.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		Testua.setBounds(150, 128, 332, 140);
		getContentPane().add(Testua);
		
		JButton Bidali = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Bidali")); //$NON-NLS-1$ //$NON-NLS-2$
		Bidali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!Testua.equals("")) {
					User nori;
					if(NoriComboBox.getSelectedIndex()==0||NoriComboBox.getSelectedIndex()==-1) {
						nori=Erreklamatzailea;
					}
					else nori=Erreklamatua;
					
					System.out.println(Testua.getText());
					facade.MezuaGehitu(null, nori, Testua.getText(), erreklamazioa);
					itxi_lehioa(arg0);
					AdministratzaileaGUI lehioa=new AdministratzaileaGUI(nork);
					lehioa.setVisible(true);
				}
				else {
					System.out.println("Mezuak ezin du hutsa izan");
				}
			}
		});
		Bidali.setBounds(214, 288, 105, 27);
		getContentPane().add(Bidali);
		
		JButton Atzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
		Atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				AdministratzaileaGUI lehioa=new AdministratzaileaGUI(nork);
				lehioa.setVisible(true);
			}
		});
		Atzera.setBounds(466, 12, 105, 27);
		getContentPane().add(Atzera);
		
		
		
		
		
		
		
	}
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}