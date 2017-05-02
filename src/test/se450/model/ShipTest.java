package test.se450.model;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.se450.model.Ship;

public class ShipTest 
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMidpointX() 
	{
		Ship ship = new Ship(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
				
		assertTrue(ship.getMidpointX1X2() == 125.0f);
	}

	@Test
	public void testGetMidpointY() 
	{
		Ship ship = new Ship(10.0f, 20.0f, 30.0f, 40.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);

		assertTrue(ship.getMidpointY1Y3() == 30.0f);
	}
	
	@Test
	public void testGetMidpointXAndYWithEqualValues(){
		Ship ship = new Ship(100.0f, 100.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertTrue(ship.getMidpointX1X2() == ship.getMidpointY1Y3());
	}
	
	@Test
	public void testGetMidpointXAndYWithDifferentValues(){
		Ship ship = new Ship(100.0f, 150.0f, 200.0f, 250.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertTrue(ship.getMidpointX1X2() != ship.getMidpointY1Y3());
	}
	
	@Test
	public void testGetMidpointXWithBadValues(){
		Ship ship = new Ship(Float.NaN, 100.0f, Float.NaN, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertTrue(Float.isNaN(ship.getMidpointX1X2()));
	}
	
	@Test
	public void testGetMidpointYWithBadValues(){
		Ship ship = new Ship(100.0f, Float.NaN, 200.0f, Float.NaN, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertTrue(Float.isNaN(ship.getMidpointY1Y2()));
	}
}
