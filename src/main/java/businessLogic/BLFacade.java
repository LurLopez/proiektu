package businessLogic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Ride;
import domain.Traveler;
import domain.User;
import domain.Alerta;
import domain.Balorazioa;
import domain.Driver;
import domain.Erreklamazio;
import domain.Erreserba;
import domain.Kotxe;
import domain.Mugimendua;
import exceptions.RideMustBeLaterThanTodayException;
import exceptions.RideAlreadyExistException;

import javax.jws.WebMethod;
import javax.jws.WebService;
 
/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	@WebMethod public List<String> getDepartCities();
	
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	@WebMethod public List<String> getDestinationCities(String from);


	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driver to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail) throws RideMustBeLaterThanTodayException, RideAlreadyExistException;
	
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	@WebMethod public List<Ride> getRides(String from, String to, Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	@WebMethod public List<Date> getThisMonthDatesWithRides(String from, String to, Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public User register(String user, String pass, String type, String mail);
	
	@WebMethod public User login(String user, String pass);

	@WebMethod public float diruaSartu(float dirua, User u);

	@WebMethod public float diruaAtera(float diruaFloat, Driver driver); 
	
	@WebMethod public void bidaiaErreserbatu(int kodea, Traveler traveler, float prezioa); 
	
	@WebMethod public List<Erreserba> EskaeraList(int kodea); 
	
	@WebMethod public void ErreserbaOnartu(int kodeaId); 
	
	@WebMethod public void EskaeraDeuseztatu(int kodeaId,int mezua); 
	
	@WebMethod public int LibreDaudenEserlekuKopurua(int kodeaRide); 
	
	@WebMethod public int ErreserbarenRideKodea(int kodeerreserba); 
	
	@WebMethod public boolean KotxeaGehitu(String Marka,String Modeloa,String Matrikula,int Eserlekuak,Driver d) throws Exception; 
	
	@WebMethod public User ErabiltzaileaFreskatu(User erabiltzailea); 
	
	@WebMethod public void BidaiaKantzelatu(int bidaiaID,Driver driver); 
	
	@WebMethod public void ErreserbaKendu(int erreserbaID);
	
	@WebMethod public List<Erreserba> GetTravelerBatenErreserbak(Traveler t);
	
	@WebMethod public Erreserba erreserbaBilatu(int erreserbaKodea);
	
	@WebMethod public Driver gidariaBilatuBidaiKode(int bidaikodea);
	
	@WebMethod public Ride bilatuBidaiaKodearekin(int bidaikodea);
	
	@WebMethod public User MugimenduaGehitu(User u,float kantitatea, int mezua);
	
	@WebMethod public List<Alerta> GetTravelerBatenAlertak(Traveler t);
	
	@WebMethod public boolean AlertaSortu(String nondik, String nora, Date data,Traveler t);
	
	@WebMethod public List<Ride> GetAlertaBatenBidaiak(Alerta a);

	@WebMethod public boolean alertakKonprobatu(Traveler t);
	
	@WebMethod public int zenbatBidaiAlertetan(Traveler t);
	
	@WebMethod public Balorazioa balorazioaSortu(String t, int n, User u, User nori);
	
	@WebMethod public Erreklamazio ErreklamazioaSortu(String gaia,String testua,User nork, User nori, Ride r);
	
	@WebMethod public ArrayList ErreklamazioakKargatu();
	
	@WebMethod public List ErreklamazioakKargatuErabiltzaile(User u);
	
	
	@WebMethod public void MezuaGehitu(User nork,User nori,String testua,Erreklamazio e);
	
	@WebMethod public void ErreklamazioaEzabatu(Erreklamazio e);

	@WebMethod public User getUser(String gidariIzena);

	@WebMethod public void erabiltzaileaEzabatu(User d);

	@WebMethod public List<Erreserba> bidaiBatenErreserbak(Ride bidaia);
	
	@WebMethod public User erabiltzaileaBilatu(String izena);
	
	@WebMethod public ArrayList<Balorazioa> balorazioakKargatu(User erabiltzailea);
	
	@WebMethod public void travelerBaloratu(int erreserbakode);
	
	@WebMethod public void driverBaloratu(int erreserbakode);
	
	@WebMethod public boolean isTravelerBaloratua(int erreserbakode);
	
	@WebMethod public boolean isDriverBaloratua(int erreserbakode);
	
	@WebMethod public boolean getErreklamatuta(int erreserbakode);
	
	@WebMethod public void setErreklamatuta(int erreserbakode);
}
