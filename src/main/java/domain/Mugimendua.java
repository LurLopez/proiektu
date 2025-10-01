package domain;



import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

import javax.persistence.*;
@Entity
public class Mugimendua {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int kodea;
	
	/* 
	 * 
	 * mezua==0 bada: "Erreserba Deuseztatu"
	 * mezua==1 bada: "Bidaia Kantzelatu"
	 * mezua==2 bada: "Dirua Sartu"
	 * mezua==3 bada: "Dirua Atera"
	 * mezua==4 bada: "Erreserba Konfirmatu Da"
	 * mezua==5 bada: "Eskaera Egin Da"
	 */


	private int mezua;
	private String mezuastring;
	private Date data;
	
	private String mezuatoString;   //Hurrengo hiru aldagaiak toStringean erabiliko ditugu. Ez dakigunez ze hizkuntzentan exekutatzen ari den, parametro gisa pasako dizkigute, ingelesez,gaztelaniaz edo euskaraz
	private String kantitateatoString;
	private String datatoString;
	@Column(name = "data", columnDefinition = "TIMESTAMP")
	public String getMezuastring() {
		return mezuastring;
	}

	public void setMezuastring(String mezuastring) {
		this.mezuastring = mezuastring;
	}


	private float kantitatea;
	
	
	public Mugimendua(float kantitatea, int mezua) {
		this.mezua=mezua;
		this.kantitatea=kantitatea;
		data=new Date();
	}
	
	

	public Mugimendua() {
		
	}


	public int getKodea() {
		return kodea;
	}

	public void setKodea(int kodea) {
		this.kodea = kodea;
	}
	

	public int getMezua() {
		return mezua;
	}


	public void setMezua(int mezua) {
		this.mezua = mezua;
	}


	public float getKantitatea() {
		return kantitatea;
	}


	public void setKantitatea(int kantitatea) {
		this.kantitatea = kantitatea;
	}


	@Override
	public String toString() {
		String d=Data_ToStringerako(this.data);
		return "Mugimendua ["+datatoString+":"+d+", "  +  mezuatoString+":"+ mezuastring + ", "+ kantitateatoString+ ":" + kantitatea + "]" ;
	}
	
	public String Data_ToStringerako(Date data) {
		
		SimpleDateFormat formatua = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dataString="";
		if(data!=null) {
		 dataString = formatua.format(data);
		}
		return dataString;
	}
	
	public void ToStringOsatzeko(String d,String m,String k) {
		this.datatoString=d;
		this.mezuatoString=m;
		this.kantitateatoString=k;
	}
	
}
