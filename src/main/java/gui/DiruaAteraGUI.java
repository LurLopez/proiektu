package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import domain.Driver;
import domain.FuntzioLaguntzaileak;
import domain.Mugimendua;

public class DiruaAteraGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	private BLFacade facade = MainGUI.getBusinessLogic();
	private JComboBox MugimenduakComboBox = new JComboBox();
	private Driver d;
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	public DiruaAteraGUI(Driver d) {
	this.d=d;
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		
		JLabel DiruKop = new JLabel(String.valueOf(facade.diruaSartu(0, d)));
		DiruKop.setBounds(152, 49, 60, 17);
		getContentPane().add(DiruKop);
		
		JLabel TestuLag = new JLabel("");
		TestuLag.setBounds(143, 203, 290, 17);
		getContentPane().add(TestuLag);
		
		JLabel MugimenduakText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak")); //$NON-NLS-1$ //$NON-NLS-2$
		MugimenduakText.setBounds(254, 232, 89, 17);
		getContentPane().add(MugimenduakText);
		
		
		MugimenduakComboBox.setBounds(12, 268, 580, 26);
		getContentPane().add(MugimenduakComboBox);
		
		MugimenduakComboBoxAktualizatu();
		
		
		JLabel DiruaSartutxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.withdrawMoney"));
		DiruaSartutxt.setBounds(167, 12, 117, 17);
		getContentPane().add(DiruaSartutxt);
		
		JLabel DiruKoptxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dirua.AmountOfMoney"));
		DiruKoptxt.setBounds(23, 49, 117, 17);
		getContentPane().add(DiruKoptxt);
		
		JLabel SartuKopuruatxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dirua.Atera.AteraKopurua"));
		SartuKopuruatxt.setBounds(23, 108, 117, 17);
		getContentPane().add(SartuKopuruatxt);
		
		SartuKop = new JTextField();
		SartuKop.setBounds(150, 106, 114, 21);
		getContentPane().add(SartuKop);
		SartuKop.setColumns(10);
		
		JLabel Errorea = new JLabel("");
		Errorea.setBounds(150, 163, 60, 17);
		getContentPane().add(Errorea);
		
		JButton SartuBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.withdrawMoney"));
		SartuBotoia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(SartuKop.getText() != null) {
				String diruaString = SartuKop.getText();
				float diruaFloat = 0;
				try {
					diruaFloat = Float.parseFloat(diruaString);
					if(diruaFloat<0){
						throw new Exception();
					}
					float dirua=facade.diruaAtera(diruaFloat, d);
					if(dirua!=-1) {
					DiruKop.setText(String.valueOf(dirua)); 
					SartuKop.setText("");
					TestuLag.setText(diruaString + "€ "+ResourceBundle.getBundle("Etiquetas").getString("Dirua.Atera.AteraMezua"));
					}
					else {
						TestuLag.setText(ResourceBundle.getBundle("Etiquetas").getString("Dirua.NotEnoughMoney"));
					}
				}catch(NumberFormatException o) {
					TestuLag.setText(ResourceBundle.getBundle("Etiquetas").getString("Dirua.IncorrectValue"));
				}
				catch(Exception e) {
					TestuLag.setText(ResourceBundle.getBundle("Etiquetas").getString("Dirua.CannotBeNegative"));
				}
				

				MugimenduakComboBoxAktualizatu();
				
				
				
			}
				
			}
		});
		SartuBotoia.setBounds(131, 137, 148, 27);
		getContentPane().add(SartuBotoia);
		
		
		
		
		JButton ATZERA = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		ATZERA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				DriverMenuaGUI lehioa=new DriverMenuaGUI(d);
				lehioa.setVisible(true);
			}
		});
		ATZERA.setBounds(23, 7, 105, 27);
		getContentPane().add(ATZERA);
		
		
		
		
		
		
	}
	
	
	private void MugimenduakComboBoxAktualizatu() {
		MugimenduakComboBox.removeAllItems();
		ArrayList<Mugimendua> mug=f.MugimenduakAktualizatu(d);
		for(Mugimendua m:mug) {
			MugimenduakComboBox.addItem(m);
		}
	}
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}