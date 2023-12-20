package com.example.denticare.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.denticare.R;
import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.Estado;
import com.example.denticare.api.models.pessoa.PreAgendamento;

import java.util.List;

public class PreAgendamentoAdapter extends BaseAdapter {

    private Context context;
    private List<ConsultaList> listaAgendamento;

    public PreAgendamentoAdapter(Context context, List<ConsultaList> listaAgendamento) {
        this.context = context;
        this.listaAgendamento = listaAgendamento;
    }

    @Override
    public int getCount() {
        return listaAgendamento.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAgendamento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_preagendamento_grid, parent, false);
        }

        TextView txtNomePessoa = convertView.findViewById(R.id.txtNomePessoa);
        TextView txtDataAgendamento = convertView.findViewById(R.id.txtDataAgendamento);

        ConsultaList consultaList = listaAgendamento.get(position);

        txtNomePessoa.setText(consultaList.getNome());
        txtDataAgendamento.setText(consultaList.getData());


        return convertView;
    }
}