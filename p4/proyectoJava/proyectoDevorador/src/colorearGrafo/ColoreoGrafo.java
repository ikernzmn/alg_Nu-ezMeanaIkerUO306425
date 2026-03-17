package colorearGrafo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ColoreoGrafo {

    public static Map<String, String> realizarVoraz(Map<String, List<String>> grafo){
        String[] colours = {"red", "blue", "green", 
        "yellow", "orange", "purple", "cyan", "magenta", "lime"}; 
        Map<String,String> result = new HashMap<String,String>();

        List<String> vecinos;
        HashSet<String> coloursUsed;

        for(String nodo : grafo.keySet()){
            vecinos = grafo.get(nodo);
            coloursUsed = new HashSet<String>();

            for(String nodoVecino : vecinos){
                if(result.containsKey(nodoVecino)){
                    coloursUsed.add(result.get(nodoVecino));
                }
            }
        
            for(int j = 0; j < colours.length; j++){
                if(!coloursUsed.contains(colours[j])){
                    result.put(nodo, colours[j]);
                    break;
                }
            }
        }
        
        return result;
    }
}
