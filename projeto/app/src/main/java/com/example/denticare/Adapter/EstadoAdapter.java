package com.example.denticare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.denticare.api.models.pessoa.Estado;

import java.util.List;

public class EstadoAdapter extends ArrayAdapter<Estado> {

    public EstadoAdapter(Context context, List<Estado> estados) {
        super(context, 0, estados);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createCustomView(position, convertView, parent);
    }

    private View createCustomView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_spinner_item, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);

        // Obtenha o objeto Estado da lista
        Estado estado = getItem(position);

        if (estado != null) {
            // Defina o texto para exibição (por exemplo, apenas o nome do estado)
            textView.setText(estado.getNmEstado());
        }

        return convertView;
    }
}
