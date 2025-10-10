package businessLogic;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
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

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;
	
	private static final Logger logger = Logger.getLogger(BLFacadeImplementation.class.getName());

	public BLFacadeImplementation()  {		
		logger.info("Creating BLFacadeImplementation instance");
		dbManager=new DataAccess();
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		dbManager=da;		
	}
    
    
    /**
     * {@inheritDoc}
     */
    @WebMethod public List<String> getDepartCities(){
    	dbManager.open();	
		
		 List<String> departLocations=dbManager.getDepartCities();		

		dbManager.close();
		
		return departLocations;
    	
    }
    /**
     * {@inheritDoc}
     */
	@WebMethod public List<String> getDestinationCities(String from){
		dbManager.open();	
		
		 List<String> targetCities=dbManager.getArrivalCities(from);		

		dbManager.close();
		
		return targetCities;
	}

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
   public Ride createRide( String from, String to, Date date, int nPlaces, float price, String driverEmail ) throws RideMustBeLaterThanTodayException, RideAlreadyExistException{
	   
		dbManager.open();
		Ride ride=dbManager.createRide(from, to, date, nPlaces, price, driverEmail);		
		dbManager.close();
		return ride;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Ride> getRides(String from, String to, Date date){
		dbManager.open();
		List<Ride>  rides=dbManager.getRides(from, to, date);
		dbManager.close();
		return rides;
	}

    
	/**
	 * {@inheritDoc}
	 */
	@WebMethod 
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date){
		dbManager.open();
		List<Date>  dates=dbManager.getThisMonthDatesWithRides(from, to, date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess();

		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    @WebMethod
    public User login(String log, String pass) {
    	dbManager.open();
    	User u = dbManager.login(log,pass);
    	
		dbManager.close();
    	return u;
	}
    @WebMethod
    public User register(String username, String password, String type, String mail) {
    	dbManager.open();
    	User u = dbManager.register(username, password,type, mail);
    	dbManager.close();
    	return u;
    }
    
    public float diruaSartu(float dirua, User u) {
		dbManager.open();
		float diru = dbManager.diruaSartu(dirua, u,true);
		dbManager.close();
		return diru;
	}
    
    public float diruaAtera(float dirua, Driver u) {
    	dbManager.open();
		float diru = dbManager.diruaAtera(dirua, u,true);
		dbManager.close();
		return diru;
    }

	public void bidaiaErreserbatu(int kodea,Traveler traveler, float prezioa) {
		dbManager.open();
		dbManager.BidaiaErreserbatu(kodea,traveler,prezioa);
		dbManager.close();
	}
    
	public List<Erreserba> EskaeraList(int kodea){
		dbManager.open();
		List<Erreserba> itzuli= dbManager.EskaeraList(kodea);
		dbManager.close();
		return itzuli;
	}
    
	public void ErreserbaOnartu(int kodea) {
		dbManager.open();
		dbManager.ErreserbaOnartu(kodea);
		dbManager.close();
	}
	
	public void EskaeraDeuseztatu(int kodea,int mezua) {
		dbManager.open();
		dbManager.EskaeraDeuseztatu(kodea,mezua);
		dbManager.close();
	}
	
	
	@WebMethod public int LibreDaudenEserlekuKopurua(int kodeaRide) {
		dbManager.open();
		int librekop= dbManager.LibreDaudenEserlekuKopurua(kodeaRide);
		dbManager.close();
		return librekop;
	}
	
	public int ErreserbarenRideKodea(int kodeerreserba) {
		dbManager.open();
		int librekop= dbManager.ErreserbarenRideKodea(kodeerreserba);
		dbManager.close();
		return librekop;
	}
	
	
	
	
	public boolean KotxeaGehitu(Kotxe k,Driver d) throws Exception {
		dbManager.open();
		boolean erantzuna=dbManager.KotxeaGehitu(k,d);
		dbManager.close();
		return erantzuna;
	}
	
	public User ErabiltzaileaFreskatu(User u) {
		dbManager.open();
		User r=dbManager.ErabiltzaileaFreskatu(u);
		dbManager.close();
		return r;
	}
	
	public void BidaiaKantzelatu(int bidaiaID,Driver driver) {
		dbManager.open();
		dbManager.BidaiaKantzelatu(bidaiaID,driver);
		dbManager.close();
	}
	
	@WebMethod public void ErreserbaKendu(int erreserbaId) {
		dbManager.open();
		dbManager.ErreserbaKendu(erreserbaId);
		dbManager.close();
	}
		
	public List<Erreserba> GetTravelerBatenErreserbak(Traveler t){
		dbManager.open();
		List<Erreserba> lista = dbManager.GetTravelerBatenErreserbak(t);
		dbManager.close();
		return lista;
	}
	
	public List<Alerta> GetTravelerBatenAlertak(Traveler t){
		dbManager.open();
		List<Alerta> lista = dbManager.GetTravelerBatenAlertak(t);
		dbManager.close();
		return lista;
	}
	
	public Erreserba erreserbaBilatu(int erreserbaKodea) {
		dbManager.open();
		Erreserba erreserba = dbManager.getErreserbaKodearekin(erreserbaKodea);
		dbManager.close();
		return erreserba;
	}
	
	
	public Driver gidariaBilatuBidaiKode(int bidaikodea) {
		dbManager.open();
		Driver gidari = dbManager.Gidaria_Bilatu(bidaikodea);
		dbManager.close();
		return gidari;
	}
	
	
	public Ride bilatuBidaiaKodearekin(int bidaikodea) {
		dbManager.open();
		Ride bidai = dbManager.bilatuBidaiaKodearekin(bidaikodea);
		dbManager.close();
		return bidai;
	}
	
	public User MugimenduaGehitu(User u,float kantitatea, int mezua) {
		dbManager.open();
		User user=dbManager.MugimenduaGehitu(u, kantitatea, mezua);
		dbManager.close();
		return user;
	}
	
	public boolean AlertaSortu(String nondik, String nora, Date data,Traveler t) {
		dbManager.open();
		boolean alerta = dbManager.AlertaSortu(nondik, nora, data, t);
		dbManager.close();
		return alerta;
	}

	@Override
	public List<Ride> GetAlertaBatenBidaiak(Alerta a) {
		
		dbManager.open();
		List<Ride> bidaiak = dbManager.GetAlertaBatenBidaiak(a);
		dbManager.close();
		return bidaiak;
	}

	@Override
	public boolean alertakKonprobatu(Traveler t) {

		dbManager.open();
		boolean notifikatu = dbManager.alertakKonprobatu(t);
		dbManager.close();

		return notifikatu;
	}

	@Override
	public int zenbatBidaiAlertetan(Traveler t) {
		dbManager.open();
		int zenbat = dbManager.zenbatBidaiAlertetan(t);
		dbManager.close();
		return zenbat;
	}
	
	@Override
	public Balorazioa balorazioaSortu(String testua, int nota, User nork, User nori) {
		dbManager.open();
		Balorazioa balorazio = dbManager.balorazioaSortu(testua, nota, nork, nori);
		dbManager.close();
		return balorazio;
	}
	
	public Erreklamazio ErreklamazioaSortu(String gaia,String testua,User nork, User nori, Ride r) {
		dbManager.open();
		Erreklamazio erreklamazio=dbManager.ErreklamazioaSortu(gaia,testua,nork,nori, r);
		dbManager.close();
		return erreklamazio;
	}

	public ArrayList<Erreklamazio> ErreklamazioakKargatu() {
		dbManager.open();
		ArrayList<Erreklamazio> itzuli =dbManager.ErreklamazioakKargatu();
		dbManager.close();
		return itzuli;
	}
	
	 public List ErreklamazioakKargatuErabiltzaile(User u) {
		 dbManager.open();
			ArrayList<Erreklamazio> itzuli =dbManager.ErreklamazioakKargatuErabiltzaile(u);
			dbManager.close();
			return itzuli;
	 }
	
	
	
	public void MezuaGehitu(User nork,User nori,String testua,Erreklamazio e) {
		dbManager.open();
		dbManager.MezuaGehitu(nork,nori,testua,e);
		dbManager.close();
	}
	public void ErreklamazioaEzabatu(Erreklamazio e) {
		dbManager.open();
		dbManager.ErreklamazioaEzabatu(e);
		dbManager.close();
	}

	@Override
	public User getUser(String gidariIzena) {
		dbManager.open();
		User erab = dbManager.getUser(gidariIzena);
		dbManager.close();
		
		return erab;
	}

	@Override
	public void erabiltzaileaEzabatu(User d) {
		dbManager.open();
		dbManager.erabiltzaileaEzabatu(d);
		dbManager.close();
	}

	@Override
	public List<Erreserba> bidaiBatenErreserbak(Ride bidaia) {
		dbManager.open();
		List<Erreserba> erreserbaList = dbManager.bidaiBatenErreserbak(bidaia);
		dbManager.close();
		
		return erreserbaList;
	}
	
	public User erabiltzaileaBilatu(String izena) {
		dbManager.open();
		User u = dbManager.erabiltzaileaBilatu(izena);
		dbManager.close();
		return u;
	}
	
	@WebMethod public ArrayList<Balorazioa> balorazioakKargatu(User erabiltzailea){
		dbManager.open();
		ArrayList<Balorazioa> itzuli=dbManager.balorazioakKargatu(erabiltzailea);
		dbManager.close();
		return itzuli;
	}
	
	public void travelerBaloratu(int erreserbakode) {
		dbManager.open();
		dbManager.travelerBaloratu(erreserbakode);
		dbManager.close();
	}
	
	public void driverBaloratu(int erreserbakode) {
		dbManager.open();
		dbManager.driverBaloratu(erreserbakode);
		dbManager.close();
	}
	
	 public boolean isTravelerBaloratua(int erreserbakode) {
		 dbManager.open();
		 boolean itzuli=dbManager.isTravelerBaloratua(erreserbakode);
		 dbManager.close();
		 return itzuli;
	 }
	
	 public boolean isDriverBaloratua(int erreserbakode) {
		 dbManager.open();
		 boolean itzuli=dbManager.isDriverBaloratua(erreserbakode);
		 dbManager.close();
		 return itzuli;
	 }
	 
		@WebMethod public boolean getErreklamatuta(int erreserbakode) {
			dbManager.open();
			boolean itzuli=dbManager.getErreklamatuta(erreserbakode);
			dbManager.close();
			return itzuli;
		}
		
		@WebMethod public void setErreklamatuta(int erreserbakode) {
			dbManager.open();
			dbManager.setErreklamatuta(erreserbakode);
			dbManager.close();
			
		}
	 
	 
	 
	 
}
    



