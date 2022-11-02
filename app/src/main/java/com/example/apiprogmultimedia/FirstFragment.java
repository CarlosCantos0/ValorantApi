package com.example.apiprogmultimedia;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apiprogmultimedia.databinding.FragmentFirstBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.Cipher;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayAdapter<ValorantMaps> adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ArrayList<ValorantMaps> items = new ArrayList<>();

        adapter = new ArrayAdapter<ValorantMaps>(
                getContext(),
                R.layout.lv_valorantapi,
                items
        );

        binding.lvMapas.setAdapter(adapter);

        refresh();

        MapaViewModel viewModel = new ViewModelProvider(getActivity()).get(MapaViewModel.class);
        viewModel.getMapas().observe(getActivity(), mapas -> {
            adapter.clear();
            adapter.addAll(mapas);
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void refresh() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            ValorantMapsApi api = new ValorantMapsApi();
            String result = null;
            try {
                result = String.valueOf(api.getValorantMaps());
            } catch (IOException e) {
                e.printStackTrace();
            }

            String finalResult = result;
            handler.post(() -> {
                adapter.clear();
                for (ValorantMaps mapas: Mapas) {
                    adapter.add(mapas;
                }

                    Log.d("DEBUG", finalResult);


                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}