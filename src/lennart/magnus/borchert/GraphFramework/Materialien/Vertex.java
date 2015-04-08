package lennart.magnus.borchert.GraphFramework.Materialien;

//TODO: write documentation
/**
 * Basic Vertex implementation with 1 numeric Attribute.
 * 
 * @author Lenno
 *
 */
public class Vertex {
	private String _identifier;
	private int _attribute;
	
	public Vertex(String id, int attr){
		_attribute = attr;
		_identifier = id;
	}
	
	/**
	 * 
	 * @return this vertices attribute.
	 */
	public int getAttribute(){
		return _attribute;
	}
	
	/**
	 * 
	 * @return this vertices identifier.
	 */
	public String getIdentifier(){
		return _identifier;
	}
	
	public boolean equals(Object otherVertex){
		if(otherVertex == null)
			return false;
		if (!(otherVertex instanceof Vertex)) 
			return false;
		if(otherVertex == this)
			return true;
		return ((Vertex)otherVertex).getIdentifier().equals(_identifier);
	}
	
	public int hashCode(){
		return _identifier.hashCode();
	}
	
	@Override
	public String toString() {
		if (_attribute != 0) {
			return _identifier + ": " + _attribute;
		}else{
			return _identifier;
		}
	}
}
