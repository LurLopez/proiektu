package domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

import businessLogic.BLFacade;
import gui.MainGUI;



public class Administratzailea {
	private BLFacade facade = MainGUI.getBusinessLogic();
	
	private ArrayList<Erreklamazio> erreklamaziolist;
	
	public Administratzailea() {
		erreklamaziolist= facade.ErreklamazioakKargatu();
	}

	public ArrayList<Erreklamazio> getErreklamaziolist() {
		return erreklamaziolist;
	}

	public void setErreklamaziolist(ArrayList<Erreklamazio> erreklamaziolist) {
		this.erreklamaziolist = erreklamaziolist;
	}
	

	
}
