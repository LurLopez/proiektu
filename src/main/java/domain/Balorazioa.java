package domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Balorazioa {
	private float nota;
	private String testua;
	private User nork;
	private User nori;
	private Date data;
	
	
	public Balorazioa(String txt, float n, User nork, User nori) {
		this.nota = n;
		this.testua = txt;
		this.nork = nork;
		this.nori = nori;
		this.data = new Date();
	}
	
	public float getNota() {
		return nota;
	}
	public String getTestua() {
		return testua;
	}
	
	public String toString() {
		return nota + " " + nork.toString2() + data.toString();
	}
}
