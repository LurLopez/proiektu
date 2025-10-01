package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.Ride;
import domain.Traveler;
import domain.User;
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


public class DiruaSartuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField SartuKop;
	
	private Traveler t;
	private JComboBox MugimenduakComboBox=new JComboBox();
	private BLFacade facade = MainGUI.getBusinessLogic();
	private FuntzioLaguntzaileak f=new FuntzioLaguntzaileak();
	public DiruaSartuGUI(Traveler t) {
		
		
		
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		
		
		getContentPane().setLayout(null);
		
		
		JLabel DiruKop = new JLabel(String.valueOf(facade.diruaSartu(0, t)));
		DiruKop.setBounds(152, 49, 60, 17);
		getContentPane().add(DiruKop);
		
		JLabel TestuLag = new JLabel("");
		TestuLag.setBounds(138, 203, 290, 17);
		getContentPane().add(TestuLag);
		
		this.t=t;
		MugimenduakComboBox.setBounds(23, 272, 569, 26);
		getContentPane().add(MugimenduakComboBox);
		MugimenduakComboBoxAktualizatu();
		
		
		JLabel DiruaSartutxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dirua.Sartu"));
		DiruaSartutxt.setBounds(167, 12, 117, 17);
		getContentPane().add(DiruaSartutxt);
		
		JLabel DiruKoptxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dirua.AmountOfMoney"));
		DiruKoptxt.setBounds(23, 49, 117, 17);
		getContentPane().add(DiruKoptxt);
		
		JLabel SartuKopuruatxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dirua.Sartu.SartuKopurua"));
		SartuKopuruatxt.setBounds(23, 108, 203, 17);
		getContentPane().add(SartuKopuruatxt);
		
		SartuKop = new JTextField();
		SartuKop.setBounds(152, 106, 114, 21);
		getContentPane().add(SartuKop);
		SartuKop.setColumns(10);
		
		JLabel Errorea = new JLabel("");
		Errorea.setBounds(72, 224, 60, 17);
		getContentPane().add(Errorea);
		
		JButton SartuBotoia = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Dirua.Sartu"));
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
					float dirua=facade.diruaSartu(diruaFloat, t);
					DiruKop.setText(String.valueOf(dirua)); 
					SartuKop.setText("");
					TestuLag.setText(diruaString + "€ "+ResourceBundle.getBundle("Etiquetas").getString("Dirua.Sartu.AteraMezua"));
				
					
					
					
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
		SartuBotoia.setBounds(138, 139, 148, 27);
		getContentPane().add(SartuBotoia);
		
		
		
		
		JButton ATZERA = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back"));
		ATZERA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				TravelerMenuaGUI lehioa=new TravelerMenuaGUI(t);
				lehioa.setVisible(true);
			}
		});
		ATZERA.setBounds(23, 7, 105, 27);
		getContentPane().add(ATZERA);
		
		JLabel MugimenduakText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak")); //$NON-NLS-1$ //$NON-NLS-2$
		MugimenduakText.setBounds(256, 243, 89, 17);
		getContentPane().add(MugimenduakText);
		
		
		
		
		
		
		
		
		
	}
	
	
	private void MugimenduakComboBoxAktualizatu() {
		MugimenduakComboBox.removeAllItems();
		ArrayList<Mugimendua> mug=f.MugimenduakAktualizatu(t);
		for(Mugimendua m:mug) {
			MugimenduakComboBox.addItem(m);
		}
	}
	
	
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}