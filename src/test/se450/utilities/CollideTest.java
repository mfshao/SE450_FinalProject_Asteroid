package test.se450.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.se450.model.Circle;
import main.se450.model.Square;
import main.se450.model.Triangle;
import main.se450.utilities.Collide;

public class CollideTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception 
	{
	}

	@Before
	public void setUp() throws Exception 
	{
	}

	@After
	public void tearDown() throws Exception 
	{
	}
	
	@Test
	public void testNoCollusion() 
	{
		Circle circle1 = new Circle(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		Circle circle2 = new Circle(300.0f, 50.0f, 450.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertFalse(Collide.collided(circle1.getLineCollection(), circle2.getLineCollection()));
	}
	
	@Test
	public void testCircleCollusion() 
	{
		Circle circle1 = new Circle(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		Circle circle2 = new Circle(100.0f, 100.0f, 250.0f, 250.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertTrue(Collide.collided(circle1.getLineCollection(), circle2.getLineCollection()));
	}
	
	@Test
	public void testTriangleCollusion() 
	{
		Triangle triangle1 = new Triangle(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		Triangle triangle2 = new Triangle(100.0f, 100.0f, 250.0f, 250.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertTrue(Collide.collided(triangle1.getLineCollection(), triangle2.getLineCollection()));
	}
	
	@Test
	public void testSquareCollusion() 
	{
		Square square1 = new Square(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		Square square2 = new Square(100.0f, 100.0f, 250.0f, 250.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertTrue(Collide.collided(square1.getLineCollection(), square2.getLineCollection()));
	}
	
	@Test
	public void testDifferentShapeCollusion() 
	{
		Square square = new Square(50.0f, 50.0f, 200.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		Triangle triangle = new Triangle(60.0f, 50.0f, 210.0f, 200.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		Circle circle = new Circle(65.0f, 50.0f, 205.0f, 220.0f, 0.0f, 0.0f, 0.0f, Color.BLACK, null, null, 0, 0, 0);
		
		assertTrue(Collide.collided(square.getLineCollection(), triangle.getLineCollection()));
		assertTrue(Collide.collided(circle.getLineCollection(), triangle.getLineCollection()));
		assertTrue(Collide.collided(circle.getLineCollection(), square.getLineCollection()));
	}
}
