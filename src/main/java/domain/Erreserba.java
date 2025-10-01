package domain;

import javax.persistence.Entity;
import javax.persistence.*;
@Entity
public class Erreserba {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
	private int erreserbaId;
	
	private boolean eskatuta;
	private boolean onartuta;
	private Traveler traveler;
	private int bidaiaKodea;
	private boolean tbaloratua;
	private boolean dbaloratua;
	private boolean erreklamatuta;
	
	





	public Erreserba() {
		eskatuta = false;
		onartuta = false;
		traveler = null;
		tbaloratua=false;
		dbaloratua=false;
		erreklamatuta=false;
	}
	
	
	public Erreserba(int kodea) {
		eskatuta = false;
		onartuta = false;
		traveler = null;
		this.bidaiaKodea=kodea;
		tbaloratua=false;
		dbaloratua=false;
		erreklamatuta=false;
	}
	
	public Erreserba(int kodea,Traveler t,boolean erreserbatuta) {     //ESKAERAK SORTZEKO ERAIKITZAILEA
		traveler = t;
		this.bidaiaKodea=kodea;
		this.eskatuta=erreserbatuta;
		tbaloratua=false;
		dbaloratua=false;
		erreklamatuta=false;
	}
	
	
	public boolean getErreklamatuta() {
		return erreklamatuta;
	}


	public void setErreklamatuta(boolean erreklamatuta) {
		this.erreklamatuta = erreklamatuta;
	}
	
	
	
	public boolean getTbaloratua() {
		return tbaloratua;
	}


	public void setTbaloratua(boolean tbaloratua) {
		this.tbaloratua = tbaloratua;
	}


	public boolean getDbaloratua() {
		return dbaloratua;
	}


	public void setDbaloratua(boolean dbaloratua) {
		this.dbaloratua = dbaloratua;
	}
	
	
	public int getId() {
		return erreserbaId;
	}


	public void setId(int id) {
		erreserbaId = id;
	}


	public int getDriverKodea() {
		return bidaiaKodea;
	}


	public void setDriverKodea(int driverKodea) {
		bidaiaKodea = driverKodea;
	}

	
	
	
	public Traveler getTraveler() {
		return traveler;
	}


	
	
	
	public boolean getErreserbatuta() {
		return this.eskatuta;
	}
	
	public boolean getOnartuta() {
		return this.onartuta;
	}
	
	public void setOnartuta(boolean ezarri) {
		this.onartuta = ezarri;
	}
	
	public void setErreserbatuta(boolean ezarri) {
		this.eskatuta = ezarri;
	}
	public void setTraveler(Traveler t) {
		this.traveler = t;
	}
	
	public boolean erreserbatu(Traveler t) {
		if(eskatuta) {
			return false;
		}else {
			this.setErreserbatuta(true);
			this.setTraveler(t);
			return true;
		}
	}
	
	public String toString(){
		if(traveler !=null) 
		return erreserbaId+";;"+traveler.getName()+";";
		
		else return erreserbaId+";;";
	}
	
}
