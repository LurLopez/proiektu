package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Mezua {
	private User nork;
	private User nori;
	private String testua;
	private Date data;
	
	public Mezua(User nork, User nori, String testua) {
		this.nork = nork;
		this.nori = nori;
		this.testua = testua;
		data = new Date();
	}
	
	public String ToString() {
		String norkmezu;
		String norimezu;
		if(nork==null) norkmezu="Administratzailea";
		else norkmezu=nork.getName();
	
		
		if(nori==null)norimezu="Administratzailea";
		else norimezu=nori.getName();
		
		String dataFormatua="";
		if(data!=null) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    dataFormatua = sdf.format(data);
		}
		return ("nork: "+norkmezu+ " nori: " + norimezu + " data: " +  data );
		
	}

	public String getMezua() {
		return testua;
	}

	public void setMezua(String mezua) {
		this.testua = mezua;
	}
}
