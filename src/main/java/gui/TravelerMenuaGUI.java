package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Balorazioa;
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


public class TravelerMenuaGUI extends JFrame {
	private static final long serialVersionUID = 1L;




	



	JButton btnErreserbakKonfirmatu = new JButton((String) null);
	private final JButton btnAlertaSortu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.alertaSortu"));
	private final JLabel notifikazio = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton kontuaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.EzabatuKontua")); //$NON-NLS-1$ //$NON-NLS-2$

	public TravelerMenuaGUI(Traveler t)
	{
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.FindRides"));
		BLFacade facade = MainGUI.getBusinessLogic();
		List<String> origins=facade.getDepartCities();
		boolean notifikatu = facade.alertakKonprobatu(t);
		if(!notifikatu) {
			notifikazio.setText(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.notifikazioaEzDuzu"));
		}else {
			notifikazio.setText(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.notifikazioaDuzu"));
		}


		// Code for JCalendar
		
		JLabel Menua = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Menua")); //$NON-NLS-1$ //$NON-NLS-2$
		Menua.setBounds(270, 12, 71, 17);
		getContentPane().add(Menua);
		
		JButton btnDiruaSartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Dirua.Sartu")); //$NON-NLS-1$ //$NON-NLS-2$
		btnDiruaSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButton2_actionPerformed(arg0);
				DiruaSartuGUI sartu=new DiruaSartuGUI(t);
				sartu.setVisible(true);
			}
		});
		btnDiruaSartu.setBounds(157, 240, 295, 27);
		getContentPane().add(btnDiruaSartu);
		
		JButton btnErreserbakIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.btnErreserbakIkusi"));
		btnErreserbakIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButton2_actionPerformed(arg0);
				ErreserbenEgoeraIkusiGUI erreserbaIkusi=new ErreserbenEgoeraIkusiGUI(t);
				erreserbaIkusi.setVisible(true);
			}
		});
		btnErreserbakIkusi.setBounds(157, 279, 295, 27);
		getContentPane().add(btnErreserbakIkusi);
		
		JButton btnErreserbakKonfirmatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.btnErreserbakKonfirmatu"));
		btnErreserbakKonfirmatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButton2_actionPerformed(arg0);
				BidaiaBaieztatuGUI BAIEZTATU=new BidaiaBaieztatuGUI(t);
				BAIEZTATU.setVisible(true);
			}
		});
		btnErreserbakKonfirmatu.setBounds(157, 318, 295, 27);
		
		getContentPane().add(btnErreserbakKonfirmatu);
		
		JButton btnNireProfilaIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.NireProfilaIkusi"));
		btnNireProfilaIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
				ProfilaIkusiGUI profila =new ProfilaIkusiGUI(t,t);
				profila.setVisible(true);
			}
		});
		btnNireProfilaIkusi.setBounds(157, 201, 295, 27);
		getContentPane().add(btnNireProfilaIkusi);
		btnAlertaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
				AlertaSortuGUI alerta =new AlertaSortuGUI(t);
				alerta.setVisible(true);
			}
		});
		btnAlertaSortu.setBounds(157, 162, 295, 27);
		
		getContentPane().add(btnAlertaSortu);
		
		JButton btnAlertaIkusi = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatuGUI.btnAlertaIkusi"));
		btnAlertaIkusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
				AlertakIkusiGUI ikusi =new AlertakIkusiGUI(t);
				ikusi.setVisible(true);
			}
		});
		btnAlertaIkusi.setBounds(157, 123, 295, 27);
		getContentPane().add(btnAlertaIkusi);
		notifikazio.setBounds(29, 396, 584, 49);
		
		getContentPane().add(notifikazio);
		kontuaEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KontuaEzabatuGUI ezabatu = new KontuaEzabatuGUI(t,false);
				ezabatu.setVisible(true);
				
				jButton2_actionPerformed(e);
				
			}
		});
		kontuaEzabatu.setBounds(157, 80, 295, 27);
		
		getContentPane().add(kontuaEzabatu);
		
		JButton BidaiaErreserbatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BidaiaErreserbatu")); //$NON-NLS-1$ //$NON-NLS-2$
		BidaiaErreserbatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButton2_actionPerformed(arg0);
				BidaiaErreserbatuGUI lehioa=new BidaiaErreserbatuGUI(t);
				lehioa.setVisible(true);
				
			}
		});
		BidaiaErreserbatu.setBounds(157, 41, 295, 27);
		getContentPane().add(BidaiaErreserbatu);
		
		JButton Erreklamazioakbtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ErreklamazioakIkusi")); //$NON-NLS-1$ //$NON-NLS-2$
		Erreklamazioakbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jButton2_actionPerformed(arg0);
				ErreklamazioakIkusiGUI lehioa=new ErreklamazioakIkusiGUI(t);
				lehioa.setVisible(true);
			}
		});
		Erreklamazioakbtn.setBounds(157, 357, 295, 27);
		getContentPane().add(Erreklamazioakbtn);
		

	}
	public static void paintDaysWithEvents(JCalendar jCalendar,List<Date> datesWithEventsCurrentMonth, Color color) {
		//		// For each day with events in current month, the background color for that day is changed to cyan.


		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;


		for (Date d:datesWithEventsCurrentMonth){

			calendar.setTime(d);


			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
			//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(color);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);


	}
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
