package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.logging.Logger;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Driver extends User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@XmlID
	 
	private static final Logger logger = Logger.getLogger(Driver.class.getName());
	

	private int gidariZenbakia; 
	
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Ride> rides=new ArrayList<Ride>();
	
	@XmlIDREF
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private List<Kotxe> kotxeak=new ArrayList<Kotxe>(); 

	public Driver() {
		super();
	}

	public Driver( String email, String username, String password) {
		super(username,password, email);
		logger.info(email);
	}
	
	public Driver(String email, String username) {
		super(username);
		
	}
	
	public List<Ride> getRides() {
		return this.rides;
	}
	
	
	/*
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	*/
	
	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	public String getName() {
		return super.getName();
	}
	/*
	public void setName(String name) {
		this.name = name;
	}

	
	
	public String toString(){
		return email+";"+name+rides;
	}
	*/
	/**
	 * This method creates a bet with a question, minimum bet ammount and percentual profit
	 * 
	 * @param question to be added to the event
	 * @param betMinimum of that question
	 * @return Bet
	 */
	public Ride addRide(String from, String to, Date date, int nPlaces, float price)  {
        Ride ride=new Ride(from,to,date,nPlaces,price, this);
        rides.add(ride);
        return ride;
	}
	
	public void KotxeaGehitu(Kotxe k) {
		kotxeak.add(k);
	}

	public List<Kotxe> getKotxeak() {
		return kotxeak;
	}

	public void setKotxeak(List<Kotxe> kotxeak) {
		this.kotxeak = kotxeak;
	}

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
		Driver other = (Driver) obj;
		if (this.getName() != other.getName())
			return false;
		return true;
	}

	public Ride removeRide(String from, String to, Date date) {
		boolean found=false;
		int index=0;
		Ride r=null;
		while (!found && index<=rides.size()) {
			r=rides.get(++index);
			if ( (java.util.Objects.equals(r.getFrom(),from)) && (java.util.Objects.equals(r.getTo(),to))
					&& (java.util.Objects.equals(r.getDate(),date)) ) {
				found=true;
			}
		}
			
		if (found) {
			System.out.println("aaaa");
			rides.remove(index);
			return r;
		} else return null;
	}
	
	
	
}
