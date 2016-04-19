package lennart.magnus.borchert.graphFramework.fileIO;

@SuppressWarnings("serial") //TODO ? ignoriert Serialization
public class FileFormatException extends Exception{ 

	public FileFormatException(String errorMessage){
		super(errorMessage);
	}
}
