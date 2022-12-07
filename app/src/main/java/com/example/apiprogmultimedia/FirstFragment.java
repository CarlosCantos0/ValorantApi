package com.example.apiprogmultimedia;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.apiprogmultimedia.databinding.FragmentFirstBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ArrayAdapter<Mapas> adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ArrayList<Mapas> items = new ArrayList<>();

        adapter = new ArrayAdapter<Mapas>(
                getContext(),
                R.layout.lv_valorantapi,
                R.id.txtMapa,
                items
        );

        binding.lvMapas.setAdapter(adapter);

        refresh();

        //MapaViewModel viewModel = new ViewModelProvider(getActivity()).get(MapaViewModel.class);
        //viewModel.getMapas().observe(getActivity(), mapas -> {
        //    adapter.clear();
        //    adapter.addAll(mapas);
        //});

        super.onViewCreated(view, savedInstanceState);
    }

    //public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    //    super.onCreateOptionsMenu(menu, inflater);
    //    inflater.inflate(R.menu.menu_main, menu);
    //}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            refresh();
           // return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void refresh() {
        Toast.makeText(getContext(),"Refrescando..", Toast.LENGTH_LONG).show();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            MapasApi api = new MapasApi();
            try {
                ArrayList<Mapas> mapas = api.getValorantMaps();
                System.out.println(mapas);

                handler.post(() -> {
                    adapter.clear();
                    adapter.addAll(mapas);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}