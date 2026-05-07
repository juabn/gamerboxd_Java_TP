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
import entities.Plataforma;
import entities.Compania;

public class ApiJuego {
	private static final String API_KEY = "018d48659af84265982427914211cf95";
    private static final String URL_RAWG = "https://api.rawg.io/api/games?key=" + API_KEY + "&page_size=5";
    private String obtenerDescripcionRAWG(int id_Juego) {
        try {
            String urlDetalle = "https://api.rawg.io/api/games/" + id_Juego + "?key=018d48659af84265982427914211cf95";
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlDetalle)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            JsonObject jsonDetalle = JsonParser.parseString(response.body()).getAsJsonObject();
            return jsonDetalle.get("description_raw").getAsString();
            
        } catch (Exception e) {
            return "no hay nada.";
        }
    }
    
    private void cargarDetallesIndividuales(Juego j, Gson gson) {
        try {
            String urlDetalle = "https://api.rawg.io/api/games/" + j.getId_juego() + "?key=TU_KEY_AQUÍ";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlDetalle)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            JsonObject jsonDetalle = JsonParser.parseString(response.body()).getAsJsonObject(); 
            
            j.setDescripcion(jsonDetalle.get("description_raw").getAsString());
            
            JsonArray devsArray = jsonDetalle.getAsJsonArray("developers");
            ArrayList<Compania> listaDevs = gson.fromJson(devsArray, new TypeToken<ArrayList<Compania>>(){}.getType());
            j.setCompanias(listaDevs); 
            
            

            
        } catch (Exception e) {
            System.err.println("Error cargando detalle del juego: " + j.getTitulo());
        }
    }
    
    public ArrayList<Juego> obtenerJuegosRAWG() {
        ArrayList<Juego> lista = new ArrayList<>();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL_RAWG)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray results = json.getAsJsonArray("results");
            Gson gson = new Gson();
            lista = gson.fromJson(results, new TypeToken<ArrayList<Juego>>(){}.getType());

            for (Juego j : lista) {

                cargarDetallesIndividuales(j, gson);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

        


    }




