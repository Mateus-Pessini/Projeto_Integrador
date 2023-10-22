package com.example.denticare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.denticare.api.models.pessoa.Pais;

import java.util.List;

public class PaisAdapter extends ArrayAdapter<Pais> {

    public PaisAdapter(Context context, List<Pais> paises) {
        super(context, 0, paises);
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

        // Obtenha o objeto Pais da lista
        Pais pais = getItem(position);

        if (pais != null) {
            // Defina o texto para exibição (por exemplo, apenas o nome do país)
            textView.setText(pais.getNmPais());
        }

        return convertView;
    }
}
