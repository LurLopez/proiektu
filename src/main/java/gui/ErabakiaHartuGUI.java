package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.User;
import exceptions.BaturakEzKoinzidituException;
import exceptions.HutsuneaException;
import exceptions.ZenbakiNegatiboaException;
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


public class ErabakiaHartuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	private BLFacade facade = MainGUI.getBusinessLogic();
	private JComboBox BidaiakErreserbatu = new JComboBox();
	private JComboBox ErreserbakComboBox = new JComboBox();
	private JComboBox AlertakIkusi;
	private JLabel AlertaHonekEzDuBidairik;
	private Traveler traveler;
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	
	
	private Ride bidaia;
	private float dirua;
	private JTextField ErreklamatzaileariDirukop;
	private JTextField ErreklamatuariDirukop;
	
	public ErabakiaHartuGUI(Administratzailea nork,Erreklamazio erreklamazioa,User erreklamatzailea,User erreklamatua) {
		
		
		
		bidaia=erreklamazioa.getBidaia();
		dirua=bidaia.getPrice();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		JLabel Dirukop = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dirua.AmountOfMoney"));
		Dirukop.setBounds(40, 35, 158, 17);
		getContentPane().add(Dirukop);
		
		JLabel Erreklamatzaileari = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Erreklamatzailea") + " ("+erreklamatzailea.getErabiltzaileIzena() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		Erreklamatzaileari.setBounds(40, 77, 131, 17);
		getContentPane().add(Erreklamatzaileari);
		
		JLabel Erreklamatuari = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Erreklamatua") + " ("+erreklamatua.getErabiltzaileIzena() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		Erreklamatuari.setBounds(40, 128, 136, 17);
		getContentPane().add(Erreklamatuari);
		
		
		
		
		JLabel Dirukopald = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		Dirukopald.setBounds(210, 35, 166, 17);
		getContentPane().add(Dirukopald);
		
		Dirukopald.setText(String.valueOf(dirua));
		
		
		JButton ErabakiaHartubtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AdminMezuaGehituGUI.Bidali")); //$NON-NLS-1$ //$NON-NLS-2$
		
		
		ErabakiaHartubtn.setBounds(210, 201, 105, 27);
		getContentPane().add(ErabakiaHartubtn);
		
		JButton Atzera = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
		Atzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				AdministratzaileaGUI lehioa=new AdministratzaileaGUI(nork);
				lehioa.setVisible(true);
			}
		});
		Atzera.setBounds(12, -4, 105, 27);
		getContentPane().add(Atzera);
		
		ErreklamatzaileariDirukop = new JTextField();
		
		ErreklamatzaileariDirukop.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		ErreklamatzaileariDirukop.setBounds(210, 75, 114, 21);
		getContentPane().add(ErreklamatzaileariDirukop);
		ErreklamatzaileariDirukop.setColumns(10);
		
		ErreklamatuariDirukop = new JTextField();
		
		ErreklamatuariDirukop.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		ErreklamatuariDirukop.setBounds(210, 126, 114, 21);
		getContentPane().add(ErreklamatuariDirukop);
		ErreklamatuariDirukop.setColumns(10);
		
		JLabel Erroretxt = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		Erroretxt.setBounds(114, 251, 318, 53);
		getContentPane().add(Erroretxt);
		
		JButton ErreklamatzaileaEzabatubtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErabakiaHartu.ErreklamatzaileaEzabatu")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreklamatzaileaEzabatubtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				KontuaEzabatuGUI lehioa=new KontuaEzabatuGUI(erreklamatzailea,true);
				lehioa.setVisible(true);
				
			}
		});
		ErreklamatzaileaEzabatubtn.setBounds(396, 72, 175, 27);
		getContentPane().add(ErreklamatzaileaEzabatubtn);
		
		JButton ErreklamatuaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErabakiaHartu.ErreklamatuaEzabatu")); //$NON-NLS-1$ //$NON-NLS-2$
		ErreklamatuaEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				KontuaEzabatuGUI lehioa=new KontuaEzabatuGUI(erreklamatua,true);
				lehioa.setVisible(true);
			}
		});
		ErreklamatuaEzabatu.setBounds(396, 123, 175, 27);
		getContentPane().add(ErreklamatuaEzabatu);
		
		
		
		
		ErreklamatzaileariDirukop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				float dirukop=Float.parseFloat(ErreklamatzaileariDirukop.getText());
				float dirukop2=dirua-dirukop;
				ErreklamatuariDirukop.setText(String.valueOf(dirukop2));
				}
				catch(java.lang.NumberFormatException e) {
					Erroretxt.setText("Zenbaki bat izan behar du");
				}
				
			}
		});
		
		ErreklamatuariDirukop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					float dirukop=Float.parseFloat(ErreklamatuariDirukop.getText());
					float dirukop2=dirua-dirukop;
					ErreklamatzaileariDirukop.setText(String.valueOf(dirukop2));
					}
					catch(java.lang.NumberFormatException e) {
						Erroretxt.setText("Zenbaki bat izan behar du");
					}
				
			}
		});
		
		ErabakiaHartubtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(ErreklamatuariDirukop.getText()==""||ErreklamatzaileariDirukop.getText()=="") {
						throw new HutsuneaException("Ezin da hutsunerik utzi");
					}
					float dirukop=Float.parseFloat(ErreklamatuariDirukop.getText());
					float dirukop2=Float.parseFloat(ErreklamatzaileariDirukop.getText());
					if(dirukop+dirukop2!=dirua) {
						throw new BaturakEzKoinzidituException("Diruaren baturak ez du koinziditzen");
						
					}
					
					if(dirukop<0 || dirukop2<0) {
						throw new ZenbakiNegatiboaException("Diruaren kopurua ezin du negatiboa izan");
						}
					
					
					facade.diruaSartu(dirukop, erreklamatzailea);
					facade.diruaSartu(dirukop2, erreklamatua);
					facade.ErreklamazioaEzabatu(erreklamazioa);
					itxi_lehioa(arg0);
					AdministratzaileaGUI lehioa=new AdministratzaileaGUI(nork);
					lehioa.setVisible(true);
				}catch(java.lang.NumberFormatException e) {
					if(ErreklamatuariDirukop.getText()!=""&&ErreklamatzaileariDirukop.getText()!="")
					Erroretxt.setText("Zenbaki bat izan behar du");
				}
				catch(Exception e) {
					Erroretxt.setText(e.getMessage());
				}
			}
		});
		
	}
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}