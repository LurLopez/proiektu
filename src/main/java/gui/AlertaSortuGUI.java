package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Alerta;
import domain.Ride;
import domain.Traveler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

import javax.swing.table.DefaultTableModel;


public class AlertaSortuGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<String> originLocations = new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> destinationCities = new DefaultComboBoxModel<String>();

	private JLabel jLabelOrigin = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.LeavingFrom"));
	private JLabel jLabelDestination = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.GoingTo"));
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.RideDate"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;

	private List<Date> datesWithRidesCurrentMonth = new Vector<Date>();

	private DefaultTableModel tableModelRides;


	private String[] columnNamesRides = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Driver"), 
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.NPlaces"), 
			ResourceBundle.getBundle("Etiquetas").getString("FindRidesGUI.Price")
	};
	JButton btnErreserbakKonfirmatu = new JButton((String) null);
	private JTextField originCity;
	private JTextField DestinationCity;
	private final JTextArea textArea = new JTextArea();

	public AlertaSortuGUI(Traveler t)
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

		jLabelEventDate.setBounds(new Rectangle(457, 6, 140, 25));

		this.getContentPane().add(jLabelEventDate, null);
		BLFacade facade = MainGUI.getBusinessLogic();
		List<String> origins=facade.getDepartCities();
		
		for(String location:origins) originLocations.addElement(location);
		
		jLabelOrigin.setBounds(new Rectangle(6, 49, 92, 20));
		jLabelDestination.setBounds(6, 81, 61, 16);
		getContentPane().add(jLabelOrigin);

		getContentPane().add(jLabelDestination);
		jCalendar1.setBounds(new Rectangle(300, 50, 225, 150));


		// Code for JCalendar
		jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					

					
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);

					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						

						jCalendar1.setCalendar(calendarAct);

					}
					
					
				}
			}
		});

		this.getContentPane().add(jCalendar1, null);
		tableModelRides = new DefaultTableModel(null, columnNamesRides);

		tableModelRides.setDataVector(null, columnNamesRides);
		tableModelRides.setColumnCount(4);
		paintDaysWithEvents(jCalendar1,datesWithRidesCurrentMonth,Color.CYAN);
		
		originCity = new JTextField();
		//originCity.setText(ResourceBundle.getBundle("Etiquetas").getString("AlertaSortuGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		originCity.setBounds(108, 50, 160, 19);
		getContentPane().add(originCity);
		originCity.setColumns(10);
		
		DestinationCity = new JTextField();
		//DestinationCity.setText(ResourceBundle.getBundle("Etiquetas").getString("AlertaSortuGUI.textField_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		DestinationCity.setBounds(108, 80, 160, 19);
		getContentPane().add(DestinationCity);
		DestinationCity.setColumns(10);
		
		JButton AlertaSortubtn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AlertaSortuGUI.AlertaSortu")); //$NON-NLS-1$ //$NON-NLS-2$
		AlertaSortubtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				String nondik = originCity.getText();
				String nora = DestinationCity.getText();
				Date data = UtilDate.trim(jCalendar1.getDate());
				boolean sortuda = facade.AlertaSortu(nondik, nora, data,t);	
				if(!sortuda) {
					textArea.setText("Alerta hau dagoeneko sortuta zegoen");
				}else {
					textArea.setText("Alerta berri hau zuzenki sortu da, jakinarazpena\n"
							+ " izango duzu bidai bat sortzen bada");
				}
			}
		});
		AlertaSortubtn.setBounds(183, 282, 191, 31);
		getContentPane().add(AlertaSortubtn);
		textArea.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		textArea.setBounds(23, 163, 245, 68);
		
		getContentPane().add(textArea);
		
		JButton Back = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Back")); //$NON-NLS-1$ //$NON-NLS-2$
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itxi_lehioa(arg0);
				TravelerMenuaGUI lehioa=new TravelerMenuaGUI(t);
				lehioa.setVisible(true);
			}
		});
		Back.setBounds(12, 5, 105, 27);
		getContentPane().add(Back);

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
	private void itxi_lehioa(ActionEvent e) {
		this.setVisible(false);
	}
	
	
}
