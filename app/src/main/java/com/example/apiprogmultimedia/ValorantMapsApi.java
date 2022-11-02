package com.example.apiprogmultimedia;

import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

public class ValorantMapsApi {
    void getValorantMaps() throws IOException {
        String url = "https://valorant-api.com/v1/maps";

        try {
            String result = HttpUtils.get(url);
            JSONObject jsonResult = new JSONObject(result);
            JSONArray results = jsonResult.getJSONArray("results");

            ArrayList<ValorantMaps> mapasValorant = new ArrayList<>();

            for (int i = 0; i < results.length(); i++) {
                JSONObject mapaJson = results.getJSONObject(i);

                ValorantMaps mapa = new ValorantMaps();
                mapa.setName(mapaJson.getString("displayName"));
                mapa.setCoordinates(mapaJson.getString("coordinates"));
                mapa.setLv_mapIcon(mapaJson.getString("listViewIcon"));

                mapasValorant.add(mapa);
            }

            Log.e("XXX MAPAS VALORANT XXX", mapasValorant.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
