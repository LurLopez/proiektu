package domain;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Alerta {
	
	
	
	private String nondik;
	private String nora;
	private Date data;
	@ManyToOne
	private Traveler traveler;
	
	
	public Alerta(String f, String t, Date d, Traveler b) {
		nondik = f;
		nora = t;
		data =d;
		traveler = b;
	}
	
	public String getNondik() {
		return nondik;
	}
	
	public void setNondik(String n) {
		nondik = n;
	}
	
	public String getNora() {
		return nora;
	}
	
	public void setNora(String n) {
		nora = n;
	}
	
	public Traveler getTraveler() {
		return traveler;
	}
	
	public Date getData() {
		return data;
	}
	/*
	public boolean equals(Object berria) {
		if(berria instanceof Alerta) {
			Alerta alerta = (Alerta) berria;
			String bnondik = alerta.getNondik().toLowerCase();
			String bnora = alerta.getNora().toLowerCase();
			Date bdate= alerta.getData();
			User t = alerta.getTraveler();
			
			if(bnondik.equals(nondik.toLowerCase()) && bnora.equals(nora.toLowerCase()) && bdate.equals(data) && t.equals((User)traveler)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	*/
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || !(obj instanceof Alerta))
	        return false;

	    Alerta alerta = (Alerta) obj;

	    boolean sameFrom = this.nondik.equalsIgnoreCase(alerta.getNondik());
	    boolean sameTo = this.nora.equalsIgnoreCase(alerta.getNora());
	    User u = (User) traveler;
	    User u2 = (User) alerta.getTraveler();
	    boolean sameTraveler = u.equals(u2);
	   
	    boolean sameDate = data.equals(alerta.getData());
	    System.out.println(sameFrom + " " + sameTo + " " + sameTraveler + " " + sameDate);
	    return sameFrom && sameTo && sameTraveler && sameDate;
	}
	
	@Override
	public String toString() {
		return nondik + nora + data.toString();
	}
	
}
