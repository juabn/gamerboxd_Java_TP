
/*
package data;


import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import entities.Juego;
import entities.Plataforma;
import entities.Compania;

public class ApiJuego {
	private static final String API_KEY = "018d48659af84265982427914211cf95";
    private static final String URL_RAWG = "https://api.rawg.io/api/games?key=" + API_KEY + "&page_size=40";
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
            String urlDetalle = "https://api.rawg.io/api/games/" + j.getId_juego() + "?key="+API_KEY;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlDetalle)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            JsonObject jsonDetalle = JsonParser.parseString(response.body()).getAsJsonObject(); 
            System.out.println();
            if (jsonDetalle.has("description_raw")) {
                j.setDescripcion(jsonDetalle.get("description_raw").getAsString());
            }
            if (jsonDetalle.has("developers")) {
                JsonArray devsArray = jsonDetalle.getAsJsonArray("developers");
                ArrayList<Compania> listaDevs = new ArrayList<>();
                for (JsonElement element : devsArray) {
                    JsonObject devObj = element.getAsJsonObject();
                    Compania c = new Compania();
                    c.setNombre(devObj.get("name").getAsString());
                   
                    listaDevs.add(c);
                }
                j.setCompanias(listaDevs);
            }
            if (jsonDetalle.has("platforms")) {
                JsonArray platsArray = jsonDetalle.getAsJsonArray("platforms");
                ArrayList<Plataforma> listaPlats = new ArrayList<>();
                for (JsonElement element : platsArray) {
                   
                    JsonObject nestedPlatform = element.getAsJsonObject().get("platform").getAsJsonObject();
                    Plataforma p = new Plataforma();
                    p.setNombre(nestedPlatform.get("name").getAsString());
                    p.setId(nestedPlatform.get("id").getAsInt());
                    listaPlats.add(p);
                }
                j.setPlataformas(listaPlats);
            }

            
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


*/

