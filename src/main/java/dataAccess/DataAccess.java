package dataAccess;

import java.io.File;
import java.net.NoRouteToHostException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Administratzailea;
import domain.Alerta;
import domain.Balorazioa;
import domain.Driver;
import domain.Erreklamazio;
import domain.Erreserba;
import domain.Kotxe;
import domain.Mezua;
import domain.Mugimendua;
import domain.Ride;
import domain.Traveler;
import domain.User;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import gui.AdministratzaileaGUI;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;
	private static final String ETIKETAK = "Etiquetas";

	ConfigXML c=ConfigXML.getInstance();

     public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();

				  System.out.println("File deleted");
				} else {
				  System.out.println("Operation failed");
				}
		}
		open();
		if  (c.isDatabaseInitialized())initializeDB();
		
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}
     
    public DataAccess(EntityManager db) {
    	this.db=db;
    }

	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();

		try {

		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=1; year+=1;}  
	    
		   
		    //Create drivers 
			Driver driver1=new Driver("driver1@gmail.com","Aitor Fernandez");
			Driver driver2=new Driver("driver2@gmail.com","Ane Gaztañaga");
			Driver driver3=new Driver("driver3@gmail.com","Test driver");

			
			//Create rides
			
			
			
			final String DONOSTIA = "Donostia";
			final String BILBO = "Bilbo";
			final String GASTEIZ = "Gasteiz";
			final String IRUÑA = "Iruña";
			final String EIBAR = "Eibar";

			driver1.addRide(DONOSTIA, BILBO, UtilDate.newDate(year, month, 15), 4, 7);
			driver1.addRide(DONOSTIA, GASTEIZ, UtilDate.newDate(year, month, 6), 4, 8);
			driver1.addRide(BILBO, DONOSTIA, UtilDate.newDate(year, month, 25), 4, 4);
			driver1.addRide(DONOSTIA, IRUÑA, UtilDate.newDate(year, month, 7), 4, 8);

			driver2.addRide(DONOSTIA, BILBO, UtilDate.newDate(year, month, 15), 3, 3);
			driver2.addRide(BILBO, DONOSTIA, UtilDate.newDate(year, month, 25), 2, 5);
			driver2.addRide(EIBAR, GASTEIZ, UtilDate.newDate(year, month, 6), 2, 5);

			driver3.addRide(BILBO, DONOSTIA, UtilDate.newDate(year, month, 14), 1, 3);

			
						
			db.persist(driver1);
			db.persist(driver2);
			db.persist(driver3);

	
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns all the cities where rides depart 
	 * @return collection of cities
	 */
	public List<String> getDepartCities(){
			TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.from FROM Ride r ORDER BY r.from", String.class);
			List<String> cities = query.getResultList();
			return cities;
		
	}
	/**
	 * This method returns all the arrival destinations, from all rides that depart from a given city  
	 * 
	 * @param from the depart location of a ride
	 * @return all the arrival destinations
	 */
	public List<String> getArrivalCities(String from){
		TypedQuery<String> query = db.createQuery("SELECT DISTINCT r.to FROM Ride r WHERE r.from=?1 ORDER BY r.to",String.class);
		query.setParameter(1, from);
		List<String> arrivingCities = query.getResultList(); 
		return arrivingCities;
		
	}
	/**
	 * This method creates a ride for a driver
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @param nPlaces available seats
	 * @param driverEmail to which ride is added
	 * 
	 * @return the created ride, or null, or an exception
	 * @throws RideMustBeLaterThanTodayException if the ride date is before today 
 	 * @throws RideAlreadyExistException if the same ride already exists for the driver
	 */
	public Ride createRide(String from, String to, Date date, int nPlaces, float price, String driverName) throws  RideAlreadyExistException, RideMustBeLaterThanTodayException {
		System.out.println(">> DataAccess: createRide=> from= "+from+" to= "+to+" driver="+driverName+" date "+date);
		try {
			if(new Date().compareTo(date)>0) {
				throw new RideMustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("CreateRideGUI.ErrorRideMustBeLaterThanToday"));
			}
			db.getTransaction().begin();
			
			Driver driver = db.find(Driver.class, driverName);
			if (driver.doesRideExists(from, to, date)) {
				db.getTransaction().commit();
				throw new RideAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.RideAlreadyExist"));
			}
			Ride ride = driver.addRide(from, to, date, nPlaces, price);
			//next instruction can be obviated
			db.persist(driver); 
			
			db.getTransaction().commit();
			
			
			
			System.out.println(ride.getRideNumber());
			System.out.println(ride.getRideNumber());
			System.out.println(ride.getRideNumber());
			
			for(int i=0;i<nPlaces;i++) {
				db.getTransaction().begin();
				Erreserba erreserbi= new Erreserba(ride.getRideNumber());
				db.persist(erreserbi);
				db.getTransaction().commit();
			}
			
			
			
			
			return ride;
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			db.getTransaction().commit();
			return null;
		}
		
		
	}
	
	/**
	 * This method retrieves the rides from two locations on a given date 
	 * 
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride
	 * @param date the date of the ride 
	 * @return collection of rides
	 */
	public List<Ride> getRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getRides=> from= "+from+" to= "+to+" date "+date);

		List<Ride> res = new ArrayList<>();	
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date=?3",Ride.class);   
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, date);
		List<Ride> rides = query.getResultList();
	 	 for (Ride ride:rides){
		   res.add(ride);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * @param from the origin location of a ride
	 * @param to the destination location of a ride 
	 * @param date of the month for which days with rides want to be retrieved 
	 * @return collection of rides
	 */
	public List<Date> getThisMonthDatesWithRides(String from, String to, Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT r.date FROM Ride r WHERE r.from=?1 AND r.to=?2 AND r.date BETWEEN ?3 and ?4",Date.class);   
		
		query.setParameter(1, from);
		query.setParameter(2, to);
		query.setParameter(3, firstDayMonthDate);
		query.setParameter(4, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(){
		
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			  db = emf.createEntityManager();
    	   }
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());

		
	}

	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}
	
	public User login(String username, String pass) {
		
		
		
			
		
		
		
		
		
		
		
		db.getTransaction().begin();
		User u = db.find(User.class, username);
		
		if(u == null) {
			System.out.println("Erabiltzaile hau ez dago erregistratuta");
		
			db.getTransaction().commit();
			return u;
		}else {
			if(u.getPassword().equals(pass)) {
				System.out.println("Saioa hasi da");
				db.getTransaction().commit();
				System.out.println(u.getName());
				return u;
			}else {
				System.out.println("Pasahitza eta erabiltzailea ez datoz bat");
				db.getTransaction().commit();
				return null;
			}
		}
	}
	
	public User register(String username, String pass, String type, String mail) {
		
		db.getTransaction().begin();
		User u = db.find(User.class, username);;
		User j = null;
		if(u == null) {
			if(type == "Driver") {
				j = new Driver(mail,username,pass); 
				db.persist(j);
			}else if(type == "Traveler"){
				j = new Traveler(mail,username,pass);
				db.persist(j);
				
			}
		}else {
			System.out.println("Gidari hau erregistratuta dago jada");
		}
		db.getTransaction().commit();
		
		return j;
	}
	
	public float diruaSartu(float dirua, User u,boolean mugimendu) {    //true bada esan nahi du diruaSartu botoiari eman diola eta mugimendua sortu behar da. False, bada, esan nahi du beste metodo batek erabiltzen duela.
		db.getTransaction().begin();
		User bilatu = db.find(User.class, u.getName());
		bilatu.setSaldoa(bilatu.getSaldoa()+dirua);
		if(mugimendu&&dirua>0) {
			bilatu=MugimenduaGehitu(bilatu,dirua,2);
		}
		db.getTransaction().commit();
		return bilatu.getSaldoa();
	}
	
	
	
	public float diruaAtera(float dirua, User u,boolean mugimendu) {	//true bada esan nahi du diruaAtera botoiari eman diola eta mugimendua sortu behar da. False, bada, esan nahi du beste metodo batek erabiltzen duela.
		db.getTransaction().begin();
		User bilatu = db.find(User.class, u.getName());
		if((bilatu.getSaldoa()-dirua)>=0) {
			bilatu.setSaldoa(bilatu.getSaldoa()-dirua);
			if(mugimendu&&dirua>0) {
				bilatu=MugimenduaGehitu(bilatu,dirua,3);
			}
		}
		else {
			return -1;
		}
		db.getTransaction().commit();
		return bilatu.getSaldoa();
	}
	
	
	
	public void BidaiaErreserbatu(int kodea, Traveler traveler, float prezioa) {
		db.getTransaction().begin();
		traveler=db.find(Traveler.class, traveler.getName());
		db.getTransaction().commit();
		
		System.out.println(traveler.getSaldoa()-prezioa);
		if(traveler.getSaldoa()-prezioa>=0) {
			float dirukop = diruaAtera(prezioa, traveler,false);
			
			
			db.getTransaction().begin();
			traveler=(Traveler) (MugimenduaGehitu(  traveler,prezioa,5));
			traveler.setSaldoa(dirukop);
			db.getTransaction().commit();
			
			Erreserba eskaeragehitu=new Erreserba(kodea,traveler,true);
			
			db.getTransaction().begin();
			db.persist(eskaeragehitu);
			db.getTransaction().commit();
		
		}else {
			System.out.println("Ez duzu nahikoa dirurik bidaia hau erreserbatzeko");
		}
		
		
	}
	
	
	public List<Erreserba> EskaeraList(int kodea){
		TypedQuery<Erreserba> query = db.createQuery("SELECT e FROM Erreserba e WHERE e.bidaiaKodea = :bidaiaKodea", Erreserba.class);
		query.setParameter("bidaiaKodea", kodea);
		List<Erreserba> results = query.getResultList();
		return results;
	}
	
	public List<Erreserba> GetTravelerBatenErreserbak(Traveler t){
		TypedQuery<Erreserba> query = db.createQuery("SELECT e FROM Erreserba e WHERE e.traveler = :traveler", Erreserba.class);
		query.setParameter("traveler", t);
		List<Erreserba> results = query.getResultList();
		return results;
	
	}
	
	
		
	public void ErreserbaOnartu(int kodeeskaera) {
		db.getTransaction().begin();
		Erreserba erreserba = db.find(Erreserba.class, kodeeskaera);
		if(erreserba!=null) {
			Traveler bidaiari=erreserba.getTraveler();
			int kodebidaia=erreserba.getDriverKodea();
			Float prezioa = db.find(Ride.class, kodebidaia).getPrice();
			List<Erreserba> erreserbak=this.Bidaia_Baten_Erreserbak_Eta_Eskaerak(kodebidaia);
			Erreserba libre_erreserba=this.Bidaia_Baten_Erreserba_Libreak(erreserbak);
			if(libre_erreserba!=null) {
				db.getTransaction().commit();
				Driver gidari=Gidaria_Bilatu(kodebidaia);
				
				
				
				libre_erreserba.setOnartuta(true);
				libre_erreserba.setTraveler(bidaiari);
				db.merge(libre_erreserba);
			
				int librekop=LibreDaudenEserlekuKopurua(kodebidaia);
				db.getTransaction().begin();
				db.remove(erreserba);
				db.getTransaction().commit();
				db.getTransaction().begin();
				if(librekop==0) {
				
					List<Erreserba> eskaerak= Bidaia_Baten_Erreserbak_Eta_Eskaerak(kodebidaia);
					for(Erreserba erreserbak_kendu:eskaerak) {
						if(erreserbak_kendu.getErreserbatuta()==true) {
						db.getTransaction().commit();
						EskaeraDeuseztatu(erreserbak_kendu.getId(),0);
						db.getTransaction().begin();
					}
				}
				}
			}
		}
		else System.out.println("Ez dago eserleku librerik");
		
		
		db.getTransaction().commit();
	}
	
	public void EskaeraDeuseztatu(int kodeeskaera,int mezua) { //true bada esan nahi du eskaeraDeuseztatu botoiari eman diola eta mugimendua sortu behar da. False, bada, esan nahi du beste metodo batek erabiltzen duela.
		db.getTransaction().begin();
		Erreserba erreserba = db.find(Erreserba.class, kodeeskaera);
		Traveler bidaiari = erreserba.getTraveler();
		int rideKodea=erreserba.getDriverKodea();
		Ride bidaia = db.find(Ride.class, rideKodea);
		db.getTransaction().commit();
			if(mezua!=-1)
				bidaiari=(Traveler) (MugimenduaGehitu(  bidaiari,bidaia.getPrice(),mezua));
		
		diruaSartu(bidaia.getPrice(),bidaiari,false);
		db.getTransaction().begin();
		db.remove(erreserba);
		db.getTransaction().commit();
	}
	
	
	

	public List<Erreserba> Bidaia_Baten_Erreserbak_Eta_Eskaerak(int kodea){
		TypedQuery<Erreserba> query = db.createQuery("SELECT e FROM Erreserba e WHERE e.bidaiaKodea = :bidaiaKodea", Erreserba.class);
		query.setParameter("bidaiaKodea", kodea);
		List<Erreserba> results = query.getResultList();
		return results;
	}
	public Erreserba Bidaia_Baten_Erreserba_Libreak(List<Erreserba> erreserbalist) {
		for(Erreserba erreserba:erreserbalist) {
			if(erreserba.getErreserbatuta()==false&&erreserba.getOnartuta()==false) 
				return erreserba;
		}
		return null;
	}
	
	
	public int LibreDaudenEserlekuKopurua(int kodeaRide) {
		List<Erreserba> erreserbaketaeskaerak=this.Bidaia_Baten_Erreserbak_Eta_Eskaerak(kodeaRide);
		int kont=0;
		for(Erreserba erreserba:erreserbaketaeskaerak) {
			if(erreserba.getErreserbatuta()==false&&erreserba.getOnartuta()==false) {
				kont++;
			}
		}
		return kont;
	}
	
	public Driver Gidaria_Bilatu(int kodeBidaia) {
		db.getTransaction().begin();
		Ride bidaia = db.find(Ride.class, kodeBidaia);
		Driver gidari = bidaia.getDriver();
		db.getTransaction().commit();
		return gidari;
	}
	
	public int ErreserbarenRideKodea(int kodeerreserba) {
		db.getTransaction().begin();
		Erreserba e=db.find(Erreserba.class, kodeerreserba);
		db.getTransaction().commit();
		return e.getDriverKodea();
	}
	
	public Erreserba getErreserbaKodearekin(int kodeerreserba) {
		db.getTransaction().begin();
		Erreserba e=db.find(Erreserba.class, kodeerreserba);
		db.getTransaction().commit();
		return e;
	}
	
	
	
	
	
	public boolean KotxeaGehitu(Kotxe kotxea,Driver d)throws Exception {
		//String Marka,String Modeloa,String Matrikula,int Eserlekuak,
				Kotxe k=db.find(Kotxe.class, kotxea.getMatrikula());
				if(k==null) {
				db.getTransaction().begin();
				db.persist(kotxea);
				d.KotxeaGehitu(kotxea);
				db.merge(d);
				Driver dr=db.find(Driver.class, d.getName());
				db.getTransaction().commit();
				return true;
				}
				else {
					throw new Exception(ResourceBundle.getBundle("Etiquetas").getString("Errore.MatrikulaDagoenekoExistitu"));
				}
	}
	public User MugimenduaGehitu(User u,float kantitatea, int mezua) {

		Mugimendua m=new Mugimendua(kantitatea,mezua);
		u.MugimenduaGehitu(m);

		return u;
	}
	
	
	public User ErabiltzaileaFreskatu(User u) {
		db.getTransaction().begin();
		User r=null;
		if(u!=null) {
		r=db.find(User.class,u.getName());
		}
		db.getTransaction().commit();
		return r;
	}
	
	
	public void BidaiaKantzelatu(int bidaiaID,Driver driver) {
		List<Erreserba> eskaerak= Bidaia_Baten_Erreserbak_Eta_Eskaerak(bidaiaID);
		System.out.println(eskaerak.size());
		for(Erreserba erreserbak_kendu:eskaerak) {
			if(erreserbak_kendu.getErreserbatuta()==true||erreserbak_kendu.getOnartuta()==true) {
			EskaeraDeuseztatu(erreserbak_kendu.getId(),1);
			}
			else {
				db.getTransaction().begin();
				db.remove(erreserbak_kendu);
				db.getTransaction().commit();
			}
			}
		
		driver=(Driver) ErabiltzaileaFreskatu(driver);
		
		db.getTransaction().begin();
		Ride bidaia=db.find(Ride.class, bidaiaID);
		List<Ride> bidaiak=driver.getRides();
		bidaiak.remove(bidaia);
		driver.setRides(bidaiak);
		db.merge(driver);
		db.getTransaction().commit();
		
		db.getTransaction().begin();
		db.remove(bidaia);
		db.getTransaction().commit();
	}
	
	
	public void ErreserbaKendu(int erreserbaId) {
		Erreserba erreserba= db.find(Erreserba.class, erreserbaId);
		db.getTransaction().begin();
		db.remove(erreserba);
		db.getTransaction().commit();
	}
	
	
	public Ride bilatuBidaiaKodearekin(int bidaikodea) {
		
		db.getTransaction().begin();;
		Ride bidaia = db.find(Ride.class, bidaikodea);
		db.getTransaction().commit();
		return bidaia;
	}

	public List<Alerta> GetTravelerBatenAlertak(Traveler t) {
		TypedQuery<Alerta> query = db.createQuery("SELECT a FROM Alerta a WHERE a.traveler = :traveler", Alerta.class);
		query.setParameter("traveler", t);
		List<Alerta> results = query.getResultList();
		return results;
	}

	public boolean AlertaSortu(String nondik, String nora, Date data,Traveler t) {
		Alerta alerta = new Alerta(nondik, nora, data,t);
		db.getTransaction().begin();
		boolean bilatua = false;
		List<Alerta> alertaLista = GetTravelerBatenAlertak(t);
		for(Alerta a : alertaLista) {
			if(a.equals(alerta)) {
				bilatua = true;
			}
		}
		if(bilatua) {
			System.out.println("Dagoeneko alerta hau sortuta dago");
		}else {
			db.persist(alerta);
		}
		db.getTransaction().commit();
		return !bilatua;
	}

	public List<Ride> GetAlertaBatenBidaiak(Alerta a) {

		List<Ride> badago = new ArrayList<Ride>();
		TypedQuery<Ride> query = db.createQuery("SELECT r FROM Ride r", Ride.class);
		List<Ride> results = query.getResultList();
		String nora = a.getNora();
		String noraTxikiz = nora.toLowerCase();
		String nondik = a.getNondik();
		String nondikTxikiz = nondik.toLowerCase();
		String bnora;
		String bnoraTxikiz;
		String bnondik;
		String bnondikTxikiz;
		Date adata = a.getData();
		for(Ride r : results) {
			bnora = r.getTo();
			bnoraTxikiz = bnora.toLowerCase();
			bnondik = r.getFrom();
			bnondikTxikiz = bnondik.toLowerCase();
			if(bnoraTxikiz.equals(noraTxikiz) && bnondikTxikiz.equals(nondikTxikiz) && adata.equals(r.getDate())) {
				badago.add(r);
			}
		}
		
		
		return badago;
	}
	
	public boolean alertakKonprobatu(Traveler t) {
		boolean notifikatu = false;
		List<Alerta> alertaList = GetTravelerBatenAlertak(t);
		List<Ride> badago = new ArrayList<Ride>();
		int i = 0;
		while(!notifikatu && i < alertaList.size()) {
			badago = GetAlertaBatenBidaiak(alertaList.get(i));
			if(!badago.isEmpty()) {
				notifikatu = true;
			}else {
				i++;
			}
		}
		return notifikatu;
	}
	
	public int zenbatBidaiAlertetan(Traveler t) {
		int zenbat = 0;
		List<Alerta> alertaList = GetTravelerBatenAlertak(t);
		List<Ride> badago = new ArrayList<Ride>();
		int i = 0;
		while(i < alertaList.size()) {
			badago = GetAlertaBatenBidaiak(alertaList.get(i));
			zenbat += badago.size();
			i++;
		}
		return zenbat;
	}
	
	public Balorazioa balorazioaSortu(String testua, int nota, User nork, User nori) {
		db.getTransaction().begin();
		Balorazioa balorazio = new Balorazioa(testua, nota, nork, nori);	
		db.persist(balorazio);
		db.getTransaction().commit();
		
		return balorazio;
	}
	
	
	public Erreklamazio ErreklamazioaSortu(String gaia,String testua,User nork, User nori, Ride r) {
		ArrayList<Erreklamazio> ErreklamazioakNork=nork.getErreklamazioList();
		ArrayList<Erreklamazio> ErreklamazioakNori=nori.getErreklamazioList();
		Erreklamazio erreklamazio=new Erreklamazio(gaia,testua,nork,nori,r);
		ErreklamazioakNork.add(erreklamazio);
		ErreklamazioakNori.add(erreklamazio);
		
		db.getTransaction().begin();
		
		db.persist(erreklamazio);
		nork.setErreklamazioList(ErreklamazioakNork);
		nori.setErreklamazioList(ErreklamazioakNori);
		
		db.getTransaction().commit();
		
		
		return erreklamazio;
	}
	
	public ArrayList<Erreklamazio> ErreklamazioakKargatu(){
		TypedQuery<Erreklamazio> query = db.createQuery("SELECT e FROM Erreklamazio e", Erreklamazio.class);
		List<Erreklamazio> emaitza = query.getResultList();
		ArrayList<Erreklamazio> emaitzaArrayList = new ArrayList<>(emaitza);
		return emaitzaArrayList;
	}
		
	public ArrayList<Erreklamazio> ErreklamazioakKargatuErabiltzaile(User u){
		User itzuli=db.find(User.class,u.getErabiltzaileIzena());
		ArrayList<Erreklamazio>berreklamazioak=itzuli.getbidalitakoErreklamazioak();
		ArrayList<Erreklamazio>jerreklamazioak=itzuli.getjasotakoErreklamazioak();
		berreklamazioak.addAll(jerreklamazioak);
		return berreklamazioak;
	}
	
	
	
		public void MezuaGehitu(User nork,User nori,String testua,Erreklamazio e) {
			
			
			ArrayList<Mezua> mezulist=e.getMezuList();
			
			
			Mezua mezu=new Mezua(nork,nori,testua);
			
			
			db.getTransaction().begin();
			
			db.remove(db.find(Erreklamazio.class, e.getId()));
			db.getTransaction().commit();
			
			
			db.getTransaction().begin();
			db.persist(mezu);
			
			
			mezulist.add(mezu);
			
			e.setMezuList(mezulist);
			db.merge(e);
			db.getTransaction().commit();
			
			
				
			}
		public void ErreklamazioaEzabatu(Erreklamazio e) {
			db.getTransaction().begin();
			db.remove(db.find(Erreklamazio.class, e.getId()));
			db.getTransaction().commit();
		}

		public User getUser(String id) {
			db.getTransaction().begin();
			User u = db.find(User.class, id);
			
			if(u == null) {
				System.out.println("Erabiltzaile hau ez dago existitzen");
			}
			return u;
		}

		public void erabiltzaileaEzabatu(User d) {
			db.getTransaction().begin();
			User erabiltzaile = db.find(User.class, d.getErabiltzaileIzena());
			if(erabiltzaile instanceof Traveler) {
				List<Alerta> alertaList = GetTravelerBatenAlertak((Traveler) d);
				for(Alerta a : alertaList) {
					db.remove(a);
				}
				List<Erreserba> erreserbaList = GetTravelerBatenErreserbak((Traveler) d);
				db.getTransaction().commit();
				for(Erreserba e : erreserbaList) {
					ErreserbaEzabatu(e.getId());
				}
				db.getTransaction().begin();
			}
			
			
			    
		    if (erabiltzaile != null) {
		        db.remove(erabiltzaile);
		    }
			db.getTransaction().commit();
			
		}
		
		public void ErreserbaEzabatu(int Id) {
			db.getTransaction().begin();
			db.remove(db.find(Erreserba.class, Id));
			db.getTransaction().commit();
		}

		public List<Erreserba> bidaiBatenErreserbak(Ride bidaia) {
			TypedQuery<Erreserba> query = db.createQuery("SELECT b FROM Erreserba b WHERE b.bidaiaKodea = :zenbakia", Erreserba.class);
			query.setParameter("zenbakia", bidaia.getRideNumber());
			List<Erreserba> results = query.getResultList();
			return results;
		}
		
			public User erabiltzaileaBilatu(String izena) {
				db.getTransaction().begin();
				User erabiltzaile = db.find(User.class, izena);
				db.getTransaction().commit();
				return erabiltzaile;
				
			}
			
			public ArrayList<Balorazioa> balorazioakKargatu(User erabiltzailea){
				db.getTransaction().begin();
				TypedQuery<Balorazioa> emaitza = db.createQuery(
			            "SELECT b FROM Balorazioa b WHERE b.nori = :erabiltzailea", 
			            Balorazioa.class
			        );
				emaitza.setParameter("erabiltzailea", db.find(User.class, erabiltzailea.getErabiltzaileIzena()));
				
				ArrayList<Balorazioa> balorazioak=new ArrayList<Balorazioa>();
				try {
				balorazioak=new ArrayList<Balorazioa> (emaitza.getResultList());
				}catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return balorazioak;
			}
			
			public void travelerBaloratu(int erreserbakode) {
				db.getTransaction().begin();
				Erreserba e=db.find(Erreserba.class, erreserbakode);
				e.setTbaloratua(true);
				db.getTransaction().commit();
			}
			
			public void driverBaloratu(int erreserbakode) {
				db.getTransaction().begin();
				Erreserba e=db.find(Erreserba.class, erreserbakode);
				e.setDbaloratua(true);
				db.getTransaction().commit();
			}
			
			 public boolean isTravelerBaloratua(int erreserbakode) {
				 return db.find(Erreserba.class, erreserbakode).getTbaloratua();
			 }
			
			 public boolean isDriverBaloratua(int erreserbakode) {
				 return db.find(Erreserba.class, erreserbakode).getDbaloratua();
			 }
			
			 
			 public boolean getErreklamatuta(int erreserbakode) {
				 return db.find(Erreserba.class, erreserbakode).getErreklamatuta();
			 }
				
			 public void setErreklamatuta(int erreserbakode) {
				 db.getTransaction().begin();
				 Erreserba e=db.find(Erreserba.class, erreserbakode);
				 if(e!=null) {
				 e.setErreklamatuta(true);
				 }
				 db.getTransaction().commit();
			 }
			 
			 
			 
			
}
