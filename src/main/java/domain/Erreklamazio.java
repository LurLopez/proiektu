package domain;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Erreklamazio {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	private String gaia;
	private User nork;
	private User nori;
	private Date data;
	private Ride bidaia;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private ArrayList<Mezua> mezuList=new ArrayList<Mezua>();
	
	public int getId() {
		return id;
	}

	

	public Erreklamazio(String gaia,String testua, User nork, User nori, Ride bidaia) {
		this.gaia=gaia;
		this.nork = nork;
		this.nori = nori;
		this.bidaia = bidaia;
		this.data=new Date();
		Mezua mezu = new Mezua(nork, nori, testua);
		mezuList.add(mezu);
		
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(data!=null) {
		String dataFormatua = sdf.format(data);
		
		return "Erreklamazio [gaia=" + gaia + ", nork=" + nork.getName() + ", nori=" + nori.getName() + ", data=" + dataFormatua + "]";
		}
		else {
			return "Erreklamazio [gaia=" + gaia + ", nork=" + nork.getName() + ", nori=" + nori.getName() + ", data=" + data + "]";
		}
		}

	public String getGaia() {
		return gaia;
	}

	public void setGaia(String gaia) {
		this.gaia = gaia;
	}

	public User getNork() {
		return nork;
	}

	public void setNork(User nork) {
		this.nork = nork;
	}

	public User getNori() {
		return nori;
	}

	public void setNori(User nori) {
		this.nori = nori;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Ride getBidaia() {
		return bidaia;
	}

	public void setBidaia(Ride bidaia) {
		this.bidaia = bidaia;
	}

	public ArrayList<Mezua> getMezuList() {
		return mezuList;
	}

	public void setMezuList(ArrayList<Mezua> mezuList) {
		this.mezuList = mezuList;
	}
	
	
	
}
