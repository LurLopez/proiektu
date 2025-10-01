package domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;

import businessLogic.BLFacade;
import gui.MainGUI;

public class FuntzioLaguntzaileak {
	private BLFacade facade = MainGUI.getBusinessLogic();
	
	
	
	public ArrayList<Mugimendua> MugimenduakAktualizatu(User erabiltzailea) {
		User u= facade.ErabiltzaileaFreskatu(erabiltzailea);	
		ArrayList<Mugimendua> itzuli=new ArrayList<Mugimendua>();
		
		
		for(Mugimendua mug:u.getMugimenduak()) {
			int mezuaEuskaraz=mug.getMezua();
			String mezuaInter;
			switch(mezuaEuskaraz) {
			case(0):
				mezuaInter=ResourceBundle.getBundle("Etiquetas").getString("MugimenduMezua.ErreserbaDeuseztatu");
			break;
			case(1):
				mezuaInter=ResourceBundle.getBundle("Etiquetas").getString("MugimenduMezua.BidaiaKantzelatu");
			break;	
			case(2):
				mezuaInter=ResourceBundle.getBundle("Etiquetas").getString("MugimenduMezua.DiruaSartu");
			break;
			case(3):
				mezuaInter=ResourceBundle.getBundle("Etiquetas").getString("MugimenduMezua.DiruaAtera");
			break;
			case(4):
				mezuaInter=ResourceBundle.getBundle("Etiquetas").getString("MugimenduMezua.ErreserbaKonfirmatuDa");
			break;
			case(5):
				mezuaInter=ResourceBundle.getBundle("Etiquetas").getString("MugimenduMezua.EskaeraEginDa");
			break;
			default:
				mezuaInter="Mugimendu bat egin da";
			}
			
			mug.setMezuastring(mezuaInter);
			mug.ToStringOsatzeko(ResourceBundle.getBundle("Etiquetas").getString("MugimenduData"),ResourceBundle.getBundle("Etiquetas").getString("MugimenduMezua"),ResourceBundle.getBundle("Etiquetas").getString("MugimenduKantitatea"));
			
			itzuli.add(mug);
		}
		return itzuli;
		
		
	}	
	
	
	
	
		public String UrteHilabeteEgunString(Date data) {
			
			SimpleDateFormat formatua = new SimpleDateFormat("yyyy-MM-dd");
			String dataString = formatua.format(data);
			return dataString;
		}
		
		public String UrteHilabeteEgunOrduMinutuString(Date data) {

			SimpleDateFormat formatua = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String dataString = formatua.format(data);
			return dataString;
		}	
		
		
		
		
		public List<Ride> GetTravelerBatenBidaiaOnartuak(Traveler t){
			List<Erreserba> erreserbalist=facade.GetTravelerBatenErreserbak(t);
			ArrayList<Integer> bidaiaOnartuakInt=new ArrayList<Integer>();
			ArrayList<Ride> bidaiaOnartuak=new ArrayList<Ride>();
			for(Erreserba e:erreserbalist) {
				Ride erreserbarenbidaia=facade.bilatuBidaiaKodearekin(e.getDriverKodea());
				int bidaiarenkodea=erreserbarenbidaia.getRideNumber();
				
				if(!bidaiaOnartuakInt.contains(bidaiarenkodea)&&e.getOnartuta()==true) {
					bidaiaOnartuakInt.add(bidaiarenkodea);
				
				}
			}
			
			for(int i:bidaiaOnartuakInt) {
				Ride bidaia=facade.bilatuBidaiaKodearekin(i);
				bidaiaOnartuak.add(bidaia);
			}
			
			
			return bidaiaOnartuak;
		}
		
		
		public List<Erreserba> GetTravelerBatenBidaiaKonkretuBatenErreserbaOnartuak(Traveler t,Ride bidaia){
			int kodea=bidaia.getRideNumber();
			List<Erreserba> erreserbalist=facade.GetTravelerBatenErreserbak(t);
			List<Erreserba> itzuli=new ArrayList<Erreserba>();
			for(Erreserba e:erreserbalist) {
				if(e.getDriverKodea()==kodea&&e.getOnartuta()) {
					itzuli.add(e);
				}
			}
			return itzuli;
			
		}
		
		
		
		
		 public ArrayList<Ride> GetTravelerBatenBidaiak(Traveler t){
			List<Erreserba> erreserbalist=facade.GetTravelerBatenErreserbak(t);
			ArrayList<Integer> bidaiakkodeak=new ArrayList<Integer>();
			for(Erreserba e:erreserbalist) {
				if(!bidaiakkodeak.contains(e.getDriverKodea())) {
					bidaiakkodeak.add(e.getDriverKodea());
				}
			}
			ArrayList<Ride>bidaiak=new ArrayList<Ride>();
			for(Integer kodea:bidaiakkodeak) {
				bidaiak.add(facade.bilatuBidaiaKodearekin(kodea));
				
			}
			
			return bidaiak;
		}


		 

	
	
	
	
	
	
	
}
