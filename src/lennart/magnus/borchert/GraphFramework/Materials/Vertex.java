package lennart.magnus.borchert.GraphFramework.Materials;

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

	
	
	@Override
	public String toString() {
		if (_attribute != 0) {
			return _identifier + ": " + _attribute;
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
		result = prime * result + _attribute;
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
		if (_attribute != other._attribute) {
			return false;
		}
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
