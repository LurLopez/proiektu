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
import domain.User;
import testOperations.TestDataAccess;

public class ErregistratuBDBlackTest { //a
	static DataAccess sut=new DataAccess();
	static TestDataAccess testDA=new TestDataAccess();
	
	
	
	
	
	@Test
	public void test6() {
		String username="Ander2";
		String pass="pasahitza_1";
		String type="Traveler";
		String mail="ander@gmail.com";

		try {
			sut.open();
			User erabiltzailea=sut.register(username, pass, type, mail);   
			sut.close();
			
			if(erabiltzailea==null) {
				fail();
			}
			else {
				assertTrue(true);
				sut.open();
				sut.erabiltzaileaEzabatu(erabiltzailea);
				sut.close();
			}
			
			
			
		}
		catch(Exception e) {
			fail();
		}
	}
	
	
	
	
	
}
