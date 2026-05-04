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
    private static final String URL_RAWG = "https://api.rawg.io/api/games?key=" + API_KEY + "&page_size=10";
	public ArrayList<Juego> obtenerJuegos(){
		ArrayList<Juego> lista = new ArrayList<>();
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_RAWG)).GET().build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String json = response.body();
			String[] bloques = json.split("\"name\":\"");
			for(int i = 1; i<5; i++) {
				Juego j = new Juego();
				String titulo = bloques[i].substring(0,bloques[i].indexOf("\""));
				String img = bloques[i].split("\background_image\":\"")[1];
				img = img.substring(0, img.indexOf("\""));
				if (bloques[i].contains("\"platforms\":")) {
			        String seccionPlat = bloques[i].split("\"platforms\":")[1];
			        String[] nombresPlat = seccionPlat.split("\"name\":\"");
			        StringBuilder sb = new StringBuilder();
			        for(int k = 1; k < Math.min(nombresPlat.length, 4); k++) { 
			             sb.append(nombresPlat[k].substring(0, nombresPlat[k].indexOf("\""))).append(", ");
			        }
			        j.setPlataforma(sb.toString());
			    }
				//esto es temporal hasta que vea como sacar la desc bien
				if (bloques[i].contains("\"slug\":\"")) {
			        String sub = bloques[i].split("\"slug\":\"")[1];
			        String slug = sub.substring(0, sub.indexOf("\""));
			        String descripcionGenerica = "reseña y detalles sobre " + slug.replace("-", " ");
			        j.setDescripcion(descripcionGenerica);
			    }
				j.setImagen(img);
				j.setTitulo(titulo);
				
			}
			System.out.println(response);
			
			
		}catch (Exception e) {
            e.printStackTrace();
        }
		return lista;
		
		
	}

        


    }




