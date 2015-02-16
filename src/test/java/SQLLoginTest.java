import org.junit.*;
import static org.junit.Assert.* ;
import edu.csupomona.cs480.data.provider.SQLLogin;;

public class SQLLoginTest 
{
	@Test
	public void testSQLObjectReturn()
	{
		System.out.println("Testing if it returns an object or null\n"
				+ "Asserts to true if file is not found/returns null");
		SQLLogin sl = new SQLLogin();
		assertTrue(sl.getInfo() == null);
	}
	
	@Test
	public void testGetLogin()
	{
		System.out.println("Testing getLogin method");
		SQLLogin sl = new SQLLogin();
		assertTrue(sl.getLogin() == null);
	}
	
	@Test
	public void testGetPassword()
	{
		System.out.println("Testing getPassword method");
		SQLLogin sl = new SQLLogin();
		assertTrue(sl.getPassword() == null);
	}
	
	@Test
	public void testGetAddress()
	{
		System.out.println("Testing getAddress method");
		SQLLogin sl = new SQLLogin();
		assertTrue(sl.getAddress() == null);
	}
	
	@Test
	public void testGetdbName()
	{
		System.out.println("Testing getLogin method");
		SQLLogin sl = new SQLLogin();
		assertTrue(sl.getdbName() == null);
	}
}
