import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dataAccess.DataAccess;
import domain.Alerta;
import domain.Driver;
import domain.Ride;
import domain.Traveler;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;

public class AlertaSortuBlackMockTest { //a
	
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
		String nondik="Oiartzun";
		String nora="Ondarroa";
		Traveler t=new Traveler("lurlopez13@gmail.com","Lur","pasahitza_1");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data=null;;
		try {
			data = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		ArrayList datu_basekoa=new ArrayList<>();
		Alerta Alerta_1=new Alerta("Donostia","Bilbo",data,t);
		Alerta Alerta_2=new Alerta(nondik,nora,data,t);        
		datu_basekoa.add(Alerta_1);
		datu_basekoa.add(Alerta_2);
		
		
		try {
					
			doNothing().when(db).persist(any(Alerta.class));
			
			
			
			TypedQuery<Alerta> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.eq(Alerta.class))).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter(Mockito.eq("traveler"), Mockito.any(Traveler.class))).thenReturn(queryMock);
			Mockito.when(queryMock.getResultList()).thenReturn(datu_basekoa);
			
			sut.open();
			boolean ondo=sut.AlertaSortu(nondik, nora, data, t);
			sut.close();
			
			if(ondo) fail();
			else assertTrue(true);
			
		   } catch (Exception e) {
			   assertTrue(true);
		} 
	} 

	
	@Test
	public void test2() {
		String nondik="Oiartzun";
		String nora="Ondarroa";
		Traveler t=new Traveler("lurlopez13@gmail.com","Lur","pasahitza_1");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data=null;;
		try {
			data = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		ArrayList datu_basekoa=new ArrayList<>();
		Alerta Alerta_1=new Alerta("Donostia","Bilbo",data,t);
		datu_basekoa.add(Alerta_1);
		
		
		try {
					
			doNothing().when(db).persist(any(Alerta.class));
			
			
			
			TypedQuery<Alerta> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.eq(Alerta.class))).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter(Mockito.eq("traveler"), Mockito.any(Traveler.class))).thenReturn(queryMock);
			Mockito.when(queryMock.getResultList()).thenReturn(datu_basekoa);
			
			sut.open();
			boolean ondo=sut.AlertaSortu(nondik, nora, data, t);
			sut.close();
			
			if(ondo) assertTrue(true);
			else fail();
		
		   } catch (Exception e) {
		fail();
		} 
	} 
	
	
	@Test
	public void test3() {


        
		String nondik=null;
		String nora="Ondarroa";
		Traveler t=new Traveler("lurlopez13@gmail.com","Lur","pasahitza_1");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data=null;;
		try {
			data = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
					
			doNothing().when(db).persist(any(Alerta.class));
			ArrayList datu_basekoa=new ArrayList<>();
			
			
			TypedQuery<Alerta> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.eq(Alerta.class))).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter(Mockito.eq("traveler"), Mockito.any(Traveler.class))).thenReturn(queryMock);
			Mockito.when(queryMock.getResultList()).thenReturn(datu_basekoa);
			
			sut.open();
			boolean ondo=sut.AlertaSortu(nondik, nora, data, t);
			sut.close();
			
			if(ondo) fail();
			else assertTrue(true);
			
			
			
			
			
			
		   } catch (Exception e) {
			assertTrue(true);
		} 
	} 
	
	
	
	@Test
	public void test4() {


        
		String nondik="Oiartzun";
		String nora=null;
		Traveler t=new Traveler("lurlopez13@gmail.com","Lur","pasahitza_1");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data=null;;
		try {
			data = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
					
			doNothing().when(db).persist(any(Alerta.class));
			ArrayList datu_basekoa=new ArrayList<>();
			
			
			TypedQuery<Alerta> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.eq(Alerta.class))).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter(Mockito.eq("traveler"), Mockito.any(Traveler.class))).thenReturn(queryMock);
			Mockito.when(queryMock.getResultList()).thenReturn(datu_basekoa);
			
			sut.open();
			boolean ondo=sut.AlertaSortu(nondik, nora, data, t);
			sut.close();
			
			if(ondo) fail();
			else assertTrue(true);
			
			
			
			
			
			
		   } catch (Exception e) {
			assertTrue(true);
		} 
	} 
	
	
	@Test
	public void test5() {


        
		String nondik="Oiartzun";
		String nora="Ondarroa";
		Traveler t= null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data=null;;
		try {
			data = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
					
			ArrayList datu_basekoa=new ArrayList<>();
			
			doNothing().when(db).persist(any(Alerta.class));
			TypedQuery<Alerta> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.eq(Alerta.class))).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter(Mockito.eq("traveler"), Mockito.any(Traveler.class))).thenReturn(queryMock);
			Mockito.when(queryMock.getResultList()).thenReturn(datu_basekoa);
			
			sut.open();
			boolean ondo=sut.AlertaSortu(nondik, nora, data, t);
			sut.close();
			
			if(ondo) fail();
			else assertTrue(true);
			
			
			
			
			
			
		   } catch (Exception e) {
			assertTrue(true);
		} 
	} 
	
	
	@Test
	public void test6() {


        
		String nondik="Oiartzun";
		String nora="Ondarroa";
		Traveler t=new Traveler("lurlopez13@gmail.com","Lur","pasahitza_1");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date data=null;

		try {
					
			doNothing().when(db).persist(any(Alerta.class));
			
			ArrayList datu_basekoa=new ArrayList<>();
			
			TypedQuery<Alerta> queryMock = Mockito.mock(TypedQuery.class);
			Mockito.when(db.createQuery(Mockito.anyString(), Mockito.eq(Alerta.class))).thenReturn(queryMock);
			Mockito.when(queryMock.setParameter(Mockito.eq("traveler"), Mockito.any(Traveler.class))).thenReturn(queryMock);
			Mockito.when(queryMock.getResultList()).thenReturn(datu_basekoa);
			
			sut.open();
			boolean ondo=sut.AlertaSortu(nondik, nora, data, t);
			sut.close();
			
			if(ondo) fail();
			else assertTrue(true);
			
			
			
			
			
			
		   } catch (Exception e) {
			assertTrue(true);
		} 
	} 
	
	
}
