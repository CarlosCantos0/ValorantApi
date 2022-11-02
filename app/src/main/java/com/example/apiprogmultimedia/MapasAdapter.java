package com.example.apiprogmultimedia;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;

public class MapasAdapter extends ArrayAdapter<ValorantMaps> {
    public MapasAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ValorantMaps mapas = getItem(position);
        Log.w("XXXX", mapas.toString());

        //lvMapasRowBinding binding = null;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lv_valorantapi, parent, false);
        }

        TextView txtMapa = convertView.findViewById(R.id.txtMapa);
        ImageView imgMapa = convertView.findViewById(R.id.imgMapa);

        txtMapa.setText(mapas.getName());
        Glide.with(getContext()).load(mapas.getMapImage()).into(imgMapa);


        return convertView;

    }
}