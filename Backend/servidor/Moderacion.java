package servidor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Moderacion {

    
	static ArrayList<String> PALABRAS_PROHIBIDAS = new ArrayList<>(List.of("mierda", "puto", "puta", "pendejo", "pendeja", 
		    "verga", "joder", "coño", "maricon", "mamada", "mamon", "chingar",
		    "concha", "boludo", "boluda", "tarado", "tarada",
		    "retrasado", "retrasada", "mogolico", "mogolica", 
		    "suicidate", "invalido", "invalida","conchudo","conchuda",
		    "free-skins", "sorteo"));
		    


 
    public static boolean contienePalabrasProhibidas(String texto) {
        if (texto == null) return false;

        for (String palabra : PALABRAS_PROHIBIDAS) {
            if(texto.contains(palabra))
            
                return true;
            
        }
        return false;
    }

    
    
}
