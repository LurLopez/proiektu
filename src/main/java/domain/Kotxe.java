package domain;

import java.util.ResourceBundle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@Entity
public class Kotxe {
	@XmlID
	@Id 
	private String matrikula;
	private int eserlekukop;
	private String marka;
	private String modeloa;
	
	
	
	public Kotxe(int e,String mat,String mar,String mode) throws Exception {
		if(!mar.isEmpty()&&!mode.isEmpty()&&!mat.isEmpty()) {
		this.eserlekukop=e;
		this.matrikula=mat;
		this.marka=mar;
		this.modeloa=mode;
	}
	else { throw new Exception(ResourceBundle.getBundle("Etiquetas").getString("Errore.BalioaFalta"));
	}
	
	}	
		
	public String toString() {
		return this.matrikula +";"+this.eserlekukop +";" + this.marka +";"+this.modeloa+";";
	}
	
	
	public int getEserlekukop() {
		return eserlekukop;
	}
	public void setEserlekukop(int eserlekukop) {
		this.eserlekukop = eserlekukop;
	}
	public String getMatrikula() {
		return matrikula;
	}
	public void setMatrikula(String matrikula) {
		this.matrikula = matrikula;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getModeloa() {
		return modeloa;
	}
	public void setModeloa(String modeloa) {
		this.modeloa = modeloa;
	}
	
	

}
