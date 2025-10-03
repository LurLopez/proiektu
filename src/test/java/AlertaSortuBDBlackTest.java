import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;	
import org.junit.Test;
import dataAccess.DataAccess;
import domain.Ride;
import exceptions.RideAlreadyExistException;
import exceptions.RideMustBeLaterThanTodayException;
import testOperations.TestDataAccess;
import domain.Driver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import dataAccess.DataAccess;
import domain.Traveler;
import testOperations.TestDataAccess;

public class AlertaSortuBDBlackTest { //aa
	static DataAccess sut=new DataAccess();
	static TestDataAccess testDA=new TestDataAccess();
	
	
	
	
	
	@Test
	public void test1() {
		String nondik="Oiartzun";
		String nora="Ondarroa";
		
		sut.open();
		Traveler t=(Traveler) sut.register("Lur", "pasahitza_1", "Traveler", "lurlopez13@gmail.com");
		sut.close();
		

		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date alertDate=null;
		try {
			alertDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		boolean ondo;
		
		try {
			sut.open();
			sut.AlertaSortu(nondik, nora, alertDate,t);   //Lehenengo u datu basean sortu
			ondo=sut.AlertaSortu(nondik, nora, alertDate,t);   //u dagoeneko datu basean dago
			sut.close();
			
			if(ondo) {
				fail();
			}
			else {
				assertTrue(true);
			}
			
			
			
		}
		finally {

			sut.open();
			sut.erabiltzaileaEzabatu(t);
			sut.close();
			
		}
	}
	
	
	
	
	@Test
	public void test2() {
		String nondik="Oiartzun";
		String nora="Ondarroa";
		
		sut.open();
		Traveler t=(Traveler) sut.register("Lur", "pasahitza_1", "Traveler", "lurlopez13@gmail.com");
		sut.close();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date alertDate=null;;
		try {
			alertDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		boolean ondo;
		
		try {
			sut.open();
			ondo=sut.AlertaSortu(nondik, nora, alertDate,t);
			sut.close();
			
			if(ondo) {
				assertTrue(true);
			}
			else {
				fail();
			}
			
			
			
		}
		finally {
			sut.open();
			sut.erabiltzaileaEzabatu(t);
			sut.close();
			
		}
	}
	
	
	@Test
	public void test3() {
		String nondik="null";
		String nora="Ondarroa";
		
		sut.open();
		Traveler t=(Traveler) sut.register("Lur", "pasahitza_1", "Traveler", "lurlopez13@gmail.com");
		sut.close();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date alertDate=null;;
		try {
			alertDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		boolean ondo;
		
		try {
			sut.open();  
			ondo=sut.AlertaSortu(nondik, nora, alertDate,t);  		
			sut.close();
			
			if(ondo) {
				fail();
			}
			else {
				assertTrue(true);
			}
			
			
			
		}
		catch(Exception e) {
			assertTrue(true);
		}
		finally {
			sut.open();
			sut.erabiltzaileaEzabatu(t);
			sut.close();
			
		}
	}
	
	
	
	@Test
	public void test4() {
		String nondik="Oiartzun";
		String nora=null;
		
		sut.open();
		Traveler t=(Traveler) sut.register("Lur", "pasahitza_1", "Traveler", "lurlopez13@gmail.com");
		sut.close();
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date alertDate=null;;
		try {
			alertDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		boolean ondo;
		
		try {
			sut.open();  
			ondo=sut.AlertaSortu(nondik, nora, alertDate,t);  		
			sut.close();
			
			if(ondo) {
				fail();
			}
			else {
				assertTrue(true);
			}
			
			
			
		}
		catch(Exception e) {
			assertTrue(true);
		}
		finally {
			sut.open();
			sut.erabiltzaileaEzabatu(t);
			sut.close();
			
		}
	}
	
	
	
	@Test
	public void test5() {
		String nondik="Oiartzun";
		String nora="Ondarroa";
		
		sut.open();
		Traveler t=(Traveler) sut.register("Lur", "pasahitza_1", "Traveler", "lurlopez13@gmail.com");
		sut.close();
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date alertDate=null;;

		
		boolean ondo;
		
		try {
			sut.open();  
			ondo=sut.AlertaSortu(nondik, nora, alertDate,t);  		
			sut.close();
			
			if(ondo) {
				fail();
			}
			else {
				assertTrue(true);
			}
			
			
			
		}
		catch(Exception e) {
			assertTrue(true);
		}
		finally {
			sut.open();
			sut.erabiltzaileaEzabatu(t);
			sut.close();
			
		}
	}
	
	
	
	
	@Test
	public void test6() {
		String nondik="Oiartzun";
		String nora="Ondarroa";
		
		sut.open();
		Traveler t= null;
		sut.close();
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date alertDate=null;;
		try {
			alertDate = sdf.parse("05/10/2025");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		boolean ondo;
		
		try {
			sut.open();  
			ondo=sut.AlertaSortu(nondik, nora, alertDate,t);  		
			sut.close();
			
			if(ondo) {
				fail();
			}
			else {
				assertTrue(true);
			}
			
			
			
		}
		catch(Exception e) {
			assertTrue(true);
		}
		finally {
			testDA.open();
			System.out.println(testDA.TravelerNullalertaEzabatu(nondik, nora, alertDate, t));
			testDA.close();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
