package lennart.magnus.borchert.GraphFramework.Materialien;

//TODO: write documentation
/**
 * Basic Vertex implementation with 1 numeric Attribute.
 * 
 * @author Lenno
 *
 */
public class Vertex {
	private String identifier;
	private int attribute;
	
	public Vertex(String id, int attr){
		attribute = attr;
		identifier = id;
	}
	
	/**
	 * 
	 * @return this vertices attribute.
	 */
	public int getAttribute(){
		return attribute;
	}
	
	/**
	 * 
	 * @return this vertices identifier.
	 */
	public String getIdentifier(){
		return identifier;
	}
	
	public boolean equals(Object otherVertex){
		if(otherVertex == null)
			return false;
		if (!(otherVertex instanceof Vertex)) 
			return false;
		if(otherVertex == this)
			return true;
		return ((Vertex)otherVertex).getIdentifier().equals(identifier);
	}
	
	public int hashCode(){
		return identifier.hashCode();
	}
}
