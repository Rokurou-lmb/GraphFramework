package lennart.magnus.borchert.graphFramework.materials;

//TODO: write documentation
/**
 * Basic Vertex implementation with 1 numeric Attribute.
 * 
 * @author Lenno
 *
 */
public class Vertex {
	private String _identifier;
	private double _attribute;
	public static final int DEFAULT_ATTRIBUTE_VALUE = 1;
	
	public Vertex(String id, int attr){
		_attribute = attr;
		_identifier = id;
	}
	
	public Vertex(String id){
		this(id,1);
	}
	
	
	/**
	 * @param _attribute the _attribute to set
	 */
	public void setAttribute(double attribute) {
		_attribute = attribute;
	}

	/**
	 * 
	 * @return this vertices attribute.
	 */
	public double getAttribute(){
		return _attribute;
	}
	
	/**
	 * 
	 * @return this vertices identifier.
	 */
	public String getIdentifier(){
		return _identifier;
	}

	
	
	@Override
	public String toString() {
		if (_attribute != 1) {
			return _identifier + " : " + _attribute;
		}else{
			return _identifier;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((_identifier == null) ? 0 : _identifier.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Vertex)) {
			return false;
		}
		Vertex other = (Vertex) obj;
		if (_identifier == null) {
			if (other._identifier != null) {
				return false;
			}
		} else if (!_identifier.equals(other._identifier)) {
			return false;
		}
		return true;
	}

}
