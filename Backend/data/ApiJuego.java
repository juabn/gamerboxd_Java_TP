package data;

import java.sql.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;

import entities.Juego;

public class ApiJuego {
	private static final String API_KEY = "018d48659af84265982427914211cf95";
    private static final String URL_RAWG = "https://api.rawg.io/api/games?key=" + API_KEY + "&page_size=40";
	public ArrayList<Juego> obtenerJuegos(){
		ArrayList<Juego> lista = new ArrayList<>();
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_RAWG)).GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			//System.out.println("JSON: " + json.substring(0, 500) + "...");
			String[] bloques = json.split("\\{\"id\":");
			System.out.println(bloques);
			int limite = Math.min(bloques.length, 11);
			
			for (int i = 1; i < limite; i++) {
	            String bloque = bloques[i];
	            Juego j = new Juego();

	            try {
	                // 1. EXTRAER ID: Está justo al principio hasta la primera coma
	                String idStr = bloque.substring(0, bloque.indexOf(",")).trim();
	                j.setId_juego(Integer.parseInt(idStr));

	                // 2. EXTRAER SLUG: Buscamos "slug":"
	                if (bloque.contains("\"slug\":\"")) {
	                    String sub = bloque.split("\"slug\":\"")[1];
	                    String slug = sub.substring(0, sub.indexOf("\""));
	                    j.setDescripcion(slug); // Si tenés el setter
	                    j.setDescripcion("Reseña sobre " + slug.replace("-", " "));
	                }

	                // 3. EXTRAER NOMBRE: Buscamos "name":"
	                if (bloque.contains("\"name\":\"")) {
	                    String sub = bloque.split("\"name\":\"")[1];
	                    j.setTitulo(sub.substring(0, sub.indexOf("\"")));
	                }

	                // 4. EXTRAER IMAGEN: Buscamos "background_image":"
	                if (bloque.contains("\"background_image\":\"")) {
	                    String sub = bloque.split("\"background_image\":\"")[1];
	                    j.setImagen(sub.substring(0, sub.indexOf("\"")));
	                }

	                lista.add(j);

	            } catch (Exception e) {
	                System.out.println("Error en bloque " + i + ": " + e.getMessage());}
			}
	          }catch (Exception e) {
            e.printStackTrace();
        }
		return lista;
		
		
	}

        


    }




