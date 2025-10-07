import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Traveler;
import domain.User;
import testOperations.TestDataAccess;

public class RegisterBDWhiteTest {

	static DataAccess sut=new DataAccess();
	static TestDataAccess testDA=new TestDataAccess();
	
	
	
	
	
	@Test
	public void test1() {
		
		
		String izena = "Lur";
		String pasahitza ="pasahitza_1";
		String type ="Driver";
		String mail ="ander@gmail";
		
		sut.open();
		User t= sut.register(izena, pasahitza, type, mail);
		sut.close();
		
		
		try {
			sut.open();
			User u= sut.register(izena, pasahitza, type, mail);
			sut.close();
			
			if(u != null ) {
				sut.open();
				sut.erabiltzaileaEzabatu(u);
				sut.close();
				fail();
			}
			else {
				assertTrue(true);
			}
			
			
			
		}
		finally {

			
			
		}
	}
	
	
	
	
	@Test
	public void test2() {
		
		String izena = "Lur";
		String pasahitza ="pasahitza_1";
		String type ="Driver";
		String mail ="lur@gmail";
		
		
		
		
		try {
			sut.open();
			User t= sut.register(izena, pasahitza, type, mail);
			sut.close();
			
			if(t != null) {
				assertTrue(true);
				sut.open();
				sut.erabiltzaileaEzabatu(t);
				sut.close();
			}
			else {
				fail();
			}
			
			
			
		}
		finally {
			
			
		}
	}
	
	
	@Test
	public void test3() {
		String izena = "Lur";
		String pasahitza ="pasahitza_1";
		String type ="Traveler";
		String mail ="lur@gmail";
		
		
		
		
		
		try {
			sut.open();
			User t= sut.register(izena, pasahitza, type, mail);
			sut.close();
			
			if(t != null) {
				sut.open();
				sut.erabiltzaileaEzabatu(t);
				sut.close();
				assertTrue(true);
				
				
			}
			else {
				fail();
			}
			
			
			
		}
		finally {
		
		}
	}
	
	
	
	@Test
	public void test4() {
		String izena = "Lur";
		String pasahitza ="pasahitza_1";
		String type =null;
		String mail ="lur@gmail";
		
		User u = new Traveler(izena,pasahitza,mail);
		
		
		
		try {
			sut.open();
			User t= sut.register(izena, pasahitza, type, mail);
			sut.close();
			
			if(t != null) {
				sut.open();
				sut.erabiltzaileaEzabatu(u);
				sut.close();
				fail();
			}
			else {
				
				assertTrue(true);
			}
			
			
			
		}
		finally {
			
			
		}
			
	}
}
