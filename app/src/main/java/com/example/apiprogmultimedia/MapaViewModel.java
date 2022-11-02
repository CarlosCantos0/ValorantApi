package com.example.apiprogmultimedia;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapaViewModel extends AndroidViewModel {
    private final Application app;
    private MutableLiveData<List<ValorantMaps>> mapas;

    public MapaViewModel(@NonNull Application application) {
        super(application);
        this.app = application;
    }

    public MutableLiveData<List<ValorantMaps>> getMapas() {
        if (mapas == null) {
            mapas = new MutableLiveData<>();
            refresh();
        }
        return mapas;
    }

    private void refresh() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(
                app.getApplicationContext()
        );

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            ValorantMapsApi api = new ValorantMapsApi();
            ArrayList<ValorantMaps> maps = null;
            try {
                maps = api.getValorantMaps();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.mapas.postValue(maps);
        });
    }
}