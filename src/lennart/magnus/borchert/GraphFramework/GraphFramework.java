package lennart.magnus.borchert.GraphFramework;


import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;

public class GraphFramework {

	private static GraphParser _parser = new GraphParser();
	
	public static void main(String[] args){
		
		for (String string : args) {
			_parser.parse(string);
		}
	}
}
