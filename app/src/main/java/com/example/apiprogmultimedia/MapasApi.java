package com.example.apiprogmultimedia;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class MapasApi {
    ArrayList<Mapas> getValorantMaps() throws IOException {
        String url = "https://valorant-api.com/v1/maps";

        try {
            String result = HttpUtils.get(url);
            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");

            ArrayList<Mapas> listaMapas = new ArrayList<Mapas>();

            for (int i = 0; i < result.length(); i++) {
                try {

                    JSONObject mapaJson = results.getJSONObject(i);

                    Mapas Mapa1 = new Mapas();
                    Mapa1.setName(mapaJson.getString("displayName"));
                    Mapa1.setCoordinates(mapaJson.getString("coordinates"));
                    Mapa1.setUuid(mapaJson.getString("uuid"));
                    Mapa1.setLv_mapIcon(mapaJson.getString("listViewIcon"));
                    Mapa1.setMapImage(mapaJson.getString("displayIcon"));

                    listaMapas.add(Mapa1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            System.out.println(listaMapas);
            return listaMapas;

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
