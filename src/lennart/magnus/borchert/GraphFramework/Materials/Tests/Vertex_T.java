package lennart.magnus.borchert.GraphFramework.Materials.Tests;

import static org.junit.Assert.*;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import org.junit.Before;
import org.junit.Test;

public class Vertex_T {

	private static Vertex _v1;
	private static Vertex _v2;
	
	@Before
	public void init(){
		_v1 = new Vertex("1", 0);
		_v2 = new Vertex("2", 0);
	}
	
	@Test
	public void testConstructor(){
		Vertex v3 = new Vertex("3", 0);
		Vertex v4 = new Vertex("4", 10);
		Vertex v5 = new Vertex("1", 3);
		
		assertTrue(!(v3.equals(_v1)));
		assertEquals(10, v4.getAttribute(), 0.1);
		assertTrue(v5.equals(_v1));
		assertNotEquals(v5.getAttribute(), _v2.getAttribute());
	}
}
