package lennart.magnus.borchert.graphFramework.fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Reader is used to verify that a given file was using the correct format and
 * translate the file into an easy to work with list, 
 * cleaned of unusable blank lines and unnessecary whitespace characters.
 * 
 * @author Lenno
 */
public class GraphReader {

	//TODO test RegEx
	//TODO implement java Reader/Scanner to use utf-8 as encoding
	private static String graphOptionsLine = "(#directed)?\\s*(#attributed)?\\s*(#weighted)?";
	private static String attribute = "(:-?\\d+)?\\s*";
	private static String node = "[^\\s,:]+\\s*";
	private static String weight = "(::-?\\d+)?\\s*";
	private static String graphDefinitionLine = node + attribute + "," + "\\s*" + node + attribute + weight;


	/**
	 * 
	 * @param file to be read
	 * @return list filled with individual lines from the given file.
	 * @throws FileFormatException in case the given file didn't comply with the GKAGraph-format.
	 */
	public List<String> readFile(String file) throws FileFormatException{
		boolean correctFormat = true;
		List<String> lines = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))){
			String line = "";
			if ((line = br.readLine()) != null && line.matches(graphOptionsLine)) {
				lines.add(line);
			}
			do {
				if (!line.matches("\\s*")) {
					if (!line.matches(graphDefinitionLine)) {
						correctFormat = false;
					}
					line = line.replaceAll("\\s", "");
					lines.add(line);
				}
			}while (correctFormat && ((line = br.readLine()) != null));
		} catch (IOException e) {
			System.out.println("Es gab leider einen Fehler beim lesen der Datei, bitte überprüfen sie den Dateipfad.");
			e.printStackTrace();
		}

		if (!correctFormat) {
			throw new FileFormatException("Die Datei ist nicht im GKAGraph-Format");
		}
		return lines;
	}
}