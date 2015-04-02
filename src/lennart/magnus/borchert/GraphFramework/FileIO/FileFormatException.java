package lennart.magnus.borchert.GraphFramework.FileIO;

@SuppressWarnings("serial") //TODO ? ignoriert Serialization
public class FileFormatException extends Exception{ 

	public FileFormatException(String errorMessage){
		super(errorMessage);
	}
}
