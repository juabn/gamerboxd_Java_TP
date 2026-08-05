package servidor;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Moderacion {

    
	private static final List<String> PALABRAS_PROHIBIDAS = Arrays.asList(
		    "mierda", "puto", "puta", "pendejo", "pendeja", "cabron", "cabrona",
		    "verga", "joder", "coño", "maricon", "mamada", "mamon", "chingar",
		    "concha", "boludo", "boluda", "tarado", "tarada",
		    "retrasado", "retrasada", "mogolico", "mogolica", 
		    "suicidate", "invalido", "invalida","conchudo","conchuda",
		    "free-skins", "sorteo"
		);

 
    public static boolean contienePalabrasProhibidas(String texto) {
        if (texto == null) return false;

        for (String palabra : PALABRAS_PROHIBIDAS) {
            String regex = "(?i)\\b" + Pattern.quote(palabra) + "\\b";
            if (Pattern.compile(regex).matcher(texto).find()) {
                return true;
            }
        }
        return false;
    }

    
    
}
