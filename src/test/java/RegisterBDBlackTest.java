import static org.junit.Assert.*;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Traveler;
import domain.User;
import testOperations.TestDataAccess;

public class RegisterBDBlackTest {

	static DataAccess sut=new DataAccess();
	static TestDataAccess testDA=new TestDataAccess();
	

	@Test
	public void test_1() {
		
		String izena = null;
		String pasahitza = "pasahitza_1";
		String mota = "Traveler";
		String mail = "ander@gmail";
		
		
		
		try {
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				sut.open();
				sut.erabiltzaileaEzabatu(j);
				sut.close();
				fail();
			}else {
				
				assertTrue(true);
			}
		}
		finally {

			
			
		}
	}
	
	@Test
	public void test_2() {
		
		String izena = "Ander2";
		String pasahitza = null;
		String mota = "Traveler";
		String mail = "ander@gmail";
		
		User u = new Traveler(izena,pasahitza,mail);
		try {
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				sut.open();
				sut.erabiltzaileaEzabatu(j);
				sut.close();
				fail();
				
			}else {
				assertTrue(true);
			}
		}
		finally {

		
		}
	}
	
	@Test
	public void test_3() {
		
		String izena = "Ander3";
		String pasahitza = "pasahitza_1";
		String mota = null;
		String mail = "ander@gmail";
		
		
		try {
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				sut.open();
				sut.erabiltzaileaEzabatu(j);
				sut.close();
				fail();
			}else {
				
				assertTrue(true);
			}
		}
		finally {

	
			
		}
	}
	
	@Test
	public void test_4() {
		
		String izena = "Ander4";
		String pasahitza = "pasahitza_1";
		String mota = "Traveler";
		String mail = null;
		
		
		try {
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				sut.open();
				sut.erabiltzaileaEzabatu(j);
				sut.close();
				fail();
				
			}else {
				
				assertTrue(true);
			}
		}
		finally {

			
			
		}
	}
	
	@Test
	public void test_5() {
		
		String izena = "Ander5";
		String pasahitza = "pasahitza_1";
		String mota = "Traveler";
		String mail = "ander@gmail";
		
		User u = new Traveler(izena,pasahitza,mail);
		
		
		try {
			sut.open();
			User erabiltzailea = sut.register(izena, pasahitza, mota, mail);
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				fail();
				sut.open();
				sut.erabiltzaileaEzabatu(u);
				sut.close();
			}else {
				assertTrue(true);
			}
		}
		finally {

			
			
		}
	}
	
	@Test
	public void test_6() {
		
		String izena = "Ander6";
		String pasahitza = "pasahitza_1";
		String mota = "Traveler";
		String mail = "ander@gmail";
		
		
		
		
		try {
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				sut.open();
				sut.erabiltzaileaEzabatu(j);
				sut.close();
				assertTrue(true);
			}else {
				fail();
			}
		}
		finally {

			
			
		}
	}
	

}

