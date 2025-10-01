package domain;
/*
import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private ArrayList<Ride> rideList;
	private double money;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		money = 0;
		rideList = new ArrayList<Ride>();
	}
		
	
	
	public String getUsername() {
		return this.username;
	}
	public String getPassword() {
		return this.password;
	}
}
*/

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public abstract class User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@XmlID
	@Id 
	private String erabiltzaileIzena;
	private String pasahitza;
	private String email;
	private float saldoa;
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Ride> rides=new Vector<Ride>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Mugimendua> mugimenduak=new Vector<Mugimendua>();
	
	public String getErabiltzaileIzena() {
		return erabiltzaileIzena;
	}
	public void setErabiltzaileIzena(String erabiltzaileIzena) {
		this.erabiltzaileIzena = erabiltzaileIzena;
	}

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Balorazioa> balorazioList=new ArrayList<Balorazioa>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Erreklamazio> erreklamazioList=new ArrayList<Erreklamazio>();
	
	@OneToMany(mappedBy="nork", cascade=CascadeType.ALL)
	private List<Erreklamazio> bidalitakoErreklamazioak;

	@OneToMany(mappedBy="nori", cascade=CascadeType.ALL)
	private List<Erreklamazio> jasotakoErreklamazioak;
	
	
	
	public User() {
	}
	public User(String izena) {
		this.erabiltzaileIzena = izena;
	}

	public User(String izena, String pasahitza, String email) {
		this.erabiltzaileIzena = izena;
		this.pasahitza = pasahitza;
		this.email=email;
	}
	
	public ArrayList<Erreklamazio> getErreklamazioList(){
		return this.erreklamazioList;
	}
	
	public void setErreklamazioList(ArrayList<Erreklamazio> er) {
		erreklamazioList=er;
	}

	public ArrayList<Erreklamazio> getbidalitakoErreklamazioak(){
		return new ArrayList<>(bidalitakoErreklamazioak);
	}
	public ArrayList<Erreklamazio> getjasotakoErreklamazioak(){
		return new ArrayList<>(jasotakoErreklamazioak);
	}
	
	
	public void setSaldoa(float diru) {
		this.saldoa = diru;
	}
	public float getSaldoa() {
		return saldoa;
	}
	
	
	public List<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}
	public void setMugimenduak(List<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}
	public void MugimenduaGehitu(Mugimendua m) {
		this.mugimenduak.add(m);
	}
	
	public ArrayList<Balorazioa> getBalorazioak() {
		return balorazioList;
	}
	public void setBalorazioak(ArrayList<Balorazioa> balorazioak) {
		this.balorazioList = balorazioak;
	}
	public void BalorazioaGehitu(Balorazioa b) {
		this.balorazioList.add(b);
	}

	public String toString2() {
		return erabiltzaileIzena;
	}
	
	public String toString(){
		return erabiltzaileIzena+";"+pasahitza+rides;
	}
	
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	

	/**
	 * This method checks if the ride already exists for that driver
	 * 
	 * @param from the origin location 
	 * @param to the destination location 
	 * @param date the date of the ride 
	 * @return true if the ride exists and false in other case
	 */
	public boolean doesRideExists(String from, String to, Date date)  {	
		for (Ride r:rides)
			if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			 return true;
		
		return false;
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other;
		if(obj instanceof Driver) {
			other = (Driver) obj;
		}else {
			other = (Traveler) obj;
		}
		if (!erabiltzaileIzena.equals(other.getName()))
			return false;
		return true;
	}

	public String getName() {
		return erabiltzaileIzena;
	}

	public Ride removeRide(String from, String to, Date date) {
		boolean found=false;
		int index=0;
		Ride r=null;
		while (!found && index<=rides.size()) {
			r=rides.get(++index);
			if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to)) && (java.util.Objects.equals(r.getDate(),date)) )
			found=true;
		}
			
		if (found) {
			rides.remove(index);
			return r;
		} else return null;
	}

	public String getPassword() {
		
		return this.pasahitza;
	}
	
}