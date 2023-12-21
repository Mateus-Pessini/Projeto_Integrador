package com.example.denticare.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.ApiPreAgendamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.Estado;
import com.example.denticare.api.models.pessoa.PreAgendamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreAgendamentoAdapter extends BaseAdapter {

    private Context context;
    private List<PreAgendamento> listaAgendamento;
    private PreAgendamento agendamentoClicando;
    public PreAgendamentoAdapter(Context context, List<PreAgendamento> listaAgendamento) {
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

        ImageView iconRemover = convertView.findViewById(R.id.iconRemover);
        ImageView iconConfirmar = convertView.findViewById(R.id.iconConfirmar);
        TextView txtNomePessoa = convertView.findViewById(R.id.txtNomePessoa);
        TextView txtDataAgendamento = convertView.findViewById(R.id.txtDataAgendamento);

        PreAgendamento preAgendamento = listaAgendamento.get(position);



        txtNomePessoa.setText(preAgendamento.getPessoa().getNome());
        txtDataAgendamento.setText(preAgendamento.getData());

        iconRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 agendamentoClicando = listaAgendamento.get(position);

                SharedPreferences sharedPreferences = context.getSharedPreferences("MyToken", Context.MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");

                ApiPreAgendamento apiPreAgendamento = RetroFit.DELETE_PRE_AGENDAMENTO();

                Call<Void> call = apiPreAgendamento.DELETE_PRE_AGENDAMENTO("Bearer " + token, agendamentoClicando.getId());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            listaAgendamento.remove(getItem(position));
                            notifyDataSetChanged();
                        } else {
                            Log.e("","Id Agendamento"+ agendamentoClicando.getId());
                            //Toast.makeText(context, "Não foi possível excluir o pré-agendamento.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        // Lidar com a falha na chamada à API, se necessário
                    }
                });
            }


        });


        return convertView;

    }


    private void removerPreAgendamento(Long agendamentoClicado) {

    }

}