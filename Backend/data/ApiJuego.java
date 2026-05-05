package data;


import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
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
			JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
			JsonArray results = json.getAsJsonArray("results");
			Gson gson = new Gson();
            return gson.fromJson(results, new TypeToken<ArrayList<Juego>>(){}.getType());
			
	          }catch (Exception e) {
            e.printStackTrace();
            return lista;
        }
		
		
		
	}

        


    }




