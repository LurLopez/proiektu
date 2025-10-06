import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccess;
import domain.Driver;
import domain.Ride;
import domain.Traveler;
import domain.User;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class ErregistratuMockitoBlackTest {
	
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
	public void test1() {
		String username=null;
		String pass="pasahitza_1";
		String type="Traveler";
		String mail="ander@gmail.com";
		
		User erabiltzailea=new Traveler(mail, username, pass);
		Mockito.when(db.find(User.class, username)).thenReturn(null);   //datu basean ez dago username hori duen Userrik
		  
		 
		 
		 sut.open();
		 User itzuli=sut.register(username, pass, type, mail);
		 sut.close();
		
		
			if(itzuli==null) {
				assertTrue(true);
			}
			else {
				fail();

			}
	}
	
	@Test
	public void test5() {
		String username="Ander2";
		String pass="pasahitza_1";
		String type="Traveler";
		String mail="ander@gmail.com";
		
		User erabiltzailea=new Traveler(mail, username, pass);
		
		Mockito.when(db.find(User.class, erabiltzailea.getErabiltzaileIzena())).thenReturn(erabiltzailea); 		//datu basean  badago username hori duen Userra
		
		
		 sut.open();
		 User itzuli=sut.register(username, pass, type, mail);
		 sut.close();
		
		
			if(itzuli==null) {
				assertTrue(true);
			}
			else {
				fail();
				System.out.println(itzuli.getErabiltzaileIzena());

			}
	}
			@Test
			public void test6() {
				String username="Ander2";
				String pass="pasahitza_1";
				String type="Traveler";
				String mail="ander@gmail.com";
				
				User erabiltzailea=new Traveler(mail, username, pass);
				Mockito.when(db.find(User.class, username)).thenReturn(null);   //datu basean ez dago username hori duen Userrik
				  
				 
				 
				 sut.open();
				 User itzuli=sut.register(username, pass, type, mail);
				 sut.close();
				
				
					if(itzuli==null) {
						fail();
					}
					else {
						assertTrue(true);

					}
				
		
		
		
		
		
	}
	
	}