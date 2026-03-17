package colorearGrafo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Devorador {
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		String fichero = args[0];
		try (FileReader reader = new FileReader(fichero)) {
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			@SuppressWarnings("unchecked")
			Map<String, List<String>> grafo = (Map<String, List<String>>) jsonObject.get("grafo");

			Map<String, String> solucion = ColoreoGrafo.realizarVoraz(grafo);
			/** 
			try (FileWriter file = new FileWriter("solucion.json")) {
				file.write(new JSONObject(solucion).toJSONString());
			}
			
			if (solucion != null) {
				System.out.println("Solución encontrada: " + solucion);
			} else {
				System.out.println("No se encontró solución.");
			}

			*/
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
}