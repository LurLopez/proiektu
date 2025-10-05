package gui;

import java.text.DateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Driver;
import domain.Kotxe;
import domain.Ride;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class CreateRideGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	
	private Driver driver;
	private JTextField fieldOrigin=new JTextField();
	private JTextField fieldDestination=new JTextField();
	
	private JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
	private JLabel jLabelDestination = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.GoingTo")); 
	private JLabel jLabRideDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));
	private JLabel jLabelPrice = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.Price"));
	private JTextField jTextFieldPrice = new JTextField();

	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	
	private List<Date> datesWithEventsCurrentMonth;
	private final JButton KotxeaGehituBotton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.KotxeaGehitu")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JComboBox KotxeakComboBox = new JComboBox();
	private final JLabel KotxeakText = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Kotxeak")); //$NON-NLS-1$ //$NON-NLS-2$
	private JLabel Eserlekukop = new JLabel();


	public CreateRideGUI(Driver driver) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		this.driver=driver;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.CreateRide"));
		
		List<Kotxe> kotxelist=driver.getKotxeak();
		for(Kotxe k:kotxelist) {
			KotxeakComboBox.addItem(k.toString());
		}
		
		jLabelOrigin.setBounds(new Rectangle(6, 56, 92, 20));
		
		jLabelPrice.setBounds(new Rectangle(6, 191, 173, 20));
		jTextFieldPrice.setBounds(new Rectangle(128, 191, 60, 20));

		jCalendar.setBounds(new Rectangle(300, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setBounds(new Rectangle(100, 232, 130, 30));

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		
		
		jLabelMsg.setBounds(new Rectangle(275, 214, 305, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(22, 223, 320, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);
		this.getContentPane().add(jButtonCreate, null);
		this.getContentPane().add(jLabelOrigin, null);
		

		

		this.getContentPane().add(jCalendar, null);
		
		this.getContentPane().add(jLabelPrice, null);
		this.getContentPane().add(jTextFieldPrice, null);

		
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getThisMonthDatesWithRides("a","b",jCalendar.getDate());		
		
		jLabRideDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabRideDate.setBounds(298, 16, 140, 25);
		getContentPane().add(jLabRideDate);
		
		jLabelDestination.setBounds(6, 81, 80, 16);
		getContentPane().add(jLabelDestination);
		
		
		fieldOrigin.setBounds(100, 53, 130, 26);
		getContentPane().add(fieldOrigin);
		fieldOrigin.setColumns(10);
		
		
		fieldDestination.setBounds(104, 81, 123, 26);
		getContentPane().add(fieldDestination);
		fieldDestination.setColumns(10);
		
		 
		if(KotxeakComboBox.getSelectedItem()!=null)
		Eserlekukop = new JLabel(((String) KotxeakComboBox.getSelectedItem()).split(";")[1]); //$NON-NLS-1$ //$NON-NLS-2$
		
		
		Eserlekukop.setBounds(100, 162, 115, 17);
		getContentPane().add(Eserlekukop);
		
		
		
		JLabel lblNewLabel = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(16, 214, 45, 13);
		getContentPane().add(lblNewLabel);
		KotxeaGehituBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				KotxeaGehituGUI k=new KotxeaGehituGUI(driver);
				k.setVisible(true);
			}
		});
		KotxeaGehituBotton.setBounds(349, 216, 140, 27);
		
		getContentPane().add(KotxeaGehituBotton);
		KotxeakComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aukeratutakokotxea=(String) KotxeakComboBox.getSelectedItem();                    //Hurrengo hiru lerroetan aldaketak egin ditut, toStringetik eserlekukopurua lortzeko
				String [] aukeratutakokotxeaatributuak= aukeratutakokotxea.split(";");
				String eserlekukop=aukeratutakokotxeaatributuak[1];
				Eserlekukop.setText(eserlekukop);
			}
		});
		KotxeakComboBox.setBounds(100, 121, 188, 26);
		
		getContentPane().add(KotxeakComboBox);
		KotxeakText.setBounds(6, 126, 92, 17);
		
		getContentPane().add(KotxeakText);
		
		JLabel EserlekuakTxt = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Eserlekuak")); //$NON-NLS-1$ //$NON-NLS-2$
		EserlekuakTxt.setBounds(6, 162, 92, 17);
		getContentPane().add(EserlekuakTxt);
		
		JButton Back = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				DriverMenuaGUI lehioa=new DriverMenuaGUI(driver);
				lehioa.setVisible(true);
			}
		});
		Back.setBounds(12, 0, 105, 27);
		getContentPane().add(Back);
		
		
		 //Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//			
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolverá 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);						
	
					}
					jCalendar.setCalendar(calendarAct);
					int offset = jCalendar.getCalendar().get(Calendar.DAY_OF_WEEK);
					
						if (Locale.getDefault().equals(new Locale("es")))
							offset += 4;
						else
							offset += 5;
				Component o = (Component) jCalendar.getDayChooser().getDayPanel().getComponent(jCalendar.getCalendar().get(Calendar.DAY_OF_MONTH) + offset);
				}}});
		
	}	 
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		jLabelMsg.setText("");
		String error=field_Errors();
		
		if (error!=null) 
			jLabelMsg.setText(error);
		else
			try {
				BLFacade facade = MainGUI.getBusinessLogic();
				
				
			
				int inputSeats = Integer.parseInt(Eserlekukop.getText());
				float price = Float.parseFloat(jTextFieldPrice.getText());

				Ride r=facade.createRide(fieldOrigin.getText(), fieldDestination.getText(), UtilDate.trim(jCalendar.getDate()), inputSeats, price, driver.getName());
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideCreated"));

			} catch (RideMustBeLaterThanTodayException | RideAlreadyExistException e1) {
				// TODO Auto-generated catch block
				jLabelMsg.setText(e1.getMessage());
			} 
			catch(NullPointerException | NumberFormatException e2) {
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("Errore.KotxerikEz"));
			}
			

		}
	private String field_Errors() {
		
		try {
			if ((fieldOrigin.getText().length()==0) || (fieldDestination.getText().length()==0) ||  (jTextFieldPrice.getText().length()==0))
				return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorQuery");
			else {

				// trigger an exception if the introduced string is not a number
				

				
					float price = Float.parseFloat(jTextFieldPrice.getText());
					if (price <= 0) 
						return ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.PriceMustBeGreaterThan0");
					
					else 
						return null;
						
				
			}
		} catch (java.lang.NumberFormatException e1) {

			return  ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorNumber");		
		} catch (Exception e1) {

			e1.printStackTrace();
			return null;

		}
	}
	
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
}
