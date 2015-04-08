package lennart.magnus.borchert.GraphFramework;


import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.GUI.main.MainFrame;

public class GraphFramework {

	private static GraphParser _parser = new GraphParser();
	
	public static void main(String[] args){

		//TODO create MainFrame

		new MainFrame();

//		for (String file : args) {
//			try {
//				_parser.parse(file);
//			} catch (FileFormatException e) {
//				System.out.println(e.getLocalizedMessage());
//			}
//		}
	}
}
