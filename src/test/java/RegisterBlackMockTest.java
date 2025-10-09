import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Traveler;
import domain.User;
import testOperations.TestDataAccess;

public class RegisterBlackMockTest {
	static DataAccess sut;
	
	protected MockedStatic <Persistence> persistenceMock;

	@Mock
	protected  EntityManagerFactory entityManagerFactory;
	@Mock
	protected  EntityManager db;
	@Mock
    protected  EntityTransaction  et;
	

	@Before
    public  void init() {
        MockitoAnnotations.openMocks(this);
        persistenceMock = Mockito.mockStatic(Persistence.class);
		persistenceMock.when(() -> Persistence.createEntityManagerFactory(Mockito.any()))
        .thenReturn(entityManagerFactory);
        
        Mockito.doReturn(db).when(entityManagerFactory).createEntityManager();
		Mockito.doReturn(et).when(db).getTransaction();
	    sut=new DataAccess(db);


		
    }
	@After
    public  void tearDown() {
		persistenceMock.close();


		
    }
	
	@Test
	public void test_1() {
		
		String izena = null;
		String pasahitza = "pasahitza_1";
		String mota = "Traveler";
		String mail = "ander@gmail";
		
		Traveler t=new Traveler(mail,izena,pasahitza);
		
		try {
			Mockito.when(db.find(User.class, null)).thenReturn(null);
			
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				
				fail();
			}else {
				
				assertTrue(true);
			}
		}catch(Exception e) {
			System.out.println("ERROREA");
		}
	}
	
	@Test
	public void test_2() {
		
		String izena = "Ander";
		String pasahitza = null;
		String mota = "Traveler";
		String mail = "ander@gmail";
		
		Traveler t=new Traveler(mail,izena,pasahitza);
		
		try {
			
			Mockito.when(db.find(User.class, izena)).thenReturn(null);
			
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				
				fail();
			}else {
				
				assertTrue(true);
			}
		}catch(Exception e) {
			System.out.println("ERROREA");
		}
	}
	
	@Test
	public void test_3() {
		
		String izena = "Ander";
		String pasahitza = "pasahitza_1";
		String mota = null;
		String mail = "ander@gmail";
		
		Traveler t=new Traveler(mail,izena,pasahitza);
		
		try {
			
			Mockito.when(db.find(User.class, izena)).thenReturn(null);
			
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				
				fail();
			}else {
				
				assertTrue(true);
			}
		}catch(Exception e) {
			System.out.println("ERROREA");
		}
	}
	
	@Test
	public void test_4() {
		
		String izena = "Ander";
		String pasahitza = "pasahitza_1";
		String mota = "Traveler";
		String mail = null;
		
		Traveler t=new Traveler(mail,izena,pasahitza);
		
		try {
			
			Mockito.when(db.find(User.class, izena)).thenReturn(null);
			
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				
				fail();
			}else {
				
				assertTrue(true);
			}
		}catch(Exception e) {
			System.out.println("ERROREA");
		}
	}
	
	
	@Test
	public void test_5() {
		
		String izena = "Ander";
		String pasahitza = "pasahitza_1";
		String mota = "Traveler";
		String mail = "Ander@gmail.com";
		
		Traveler t=new Traveler(mail,izena,pasahitza);
		
		try {
			
			Mockito.when(db.find(User.class, izena)).thenReturn(t);
			
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				fail();
				
			}else {
				assertTrue(true);
				
				
			}
		}catch(Exception e) {
			System.out.println("ERROREA");
		}
	}
	
	@Test
	public void test_6() {
		
		String izena = "Ander";
		String pasahitza = "pasahitza_1";
		String mota = "Traveler";
		String mail = "Ander@gmail.com";
		
		Traveler t=new Traveler(mail,izena,pasahitza);
		
		try {
			
			Mockito.when(db.find(User.class, izena)).thenReturn(null);
			
			sut.open();
			User j = sut.register(izena, pasahitza, mota, mail);
			sut.close();
			
			if(j != null) {
				assertTrue(true);
				
				
			}else {
				
				fail();
				
			}
		}catch(Exception e) {
			System.out.println("ERROREA");
		}
	}
}
