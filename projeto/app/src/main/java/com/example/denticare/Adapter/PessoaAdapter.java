package com.example.denticare.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.pessoa.Pessoa;
import com.example.denticare.view.AddFoto;
import com.example.denticare.view.CadCliente;
import com.example.denticare.view.CadClienteTest;
import com.example.denticare.view.Clientes;
import com.example.denticare.view.HistoricoDentario;
import com.example.denticare.view.MainActivity;
import com.example.denticare.view.NewLogin;

import java.util.List;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;


public class PessoaAdapter extends BaseAdapter {

    private Context context;
    private List<Pessoa> listaPessoa;
    private Pessoa pessoaClicada;

    public PessoaAdapter(Context context, List<Pessoa> listaPessoa) {
        this.context = context;
        this.listaPessoa = listaPessoa;
    }

    @Override
    public int getCount() {
        return listaPessoa.size();
    }

    @Override
    public Object getItem(int position) {
        return listaPessoa.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item_clientes_grid, parent, false);
        }
        pessoaClicada = listaPessoa.get(position);

        TextView txtNomeCliente = convertView.findViewById(R.id.txtNomeCliente);
        TextView txtCpfCliente = convertView.findViewById(R.id.txtCpfCliente);
        ImageView imgDeletarCliente = convertView.findViewById(R.id.imgDeletarPessoa);
        LinearLayout llItem = convertView.findViewById(R.id.llItem);
        ImageView ivHistorico = convertView.findViewById(R.id.ivHistorico);
        ImageView imgPessoa = convertView.findViewById(R.id.imgPessoa);
        ImageView imgPdfPessoa = convertView.findViewById(R.id.imgPdfPessoa);

        ivHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HistoricoDentario.class);
                intent.putExtra("id_cliente", pessoaClicada.getId());
                context.startActivity(intent);
            }
        });

        imgPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddFoto.class);
                intent.putExtra("id_cliente", pessoaClicada.getId());
                context.startActivity(intent);
            }
        });

        imgPdfPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Essa funcionalidade ainda não esta disponivel!", Toast.LENGTH_SHORT).show();
            }
        });


        llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoaClicada = listaPessoa.get(position);
                Intent intent = new Intent(context, CadClienteTest.class);
                intent.putExtra("id_cliente", pessoaClicada.getId());
                context.startActivity(intent);
            }
        });

        txtNomeCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoaClicada = listaPessoa.get(position);
                Intent intent = new Intent(context, CadClienteTest.class);
                intent.putExtra("id_cliente", pessoaClicada.getId());
                context.startActivity(intent);
            }
        });

        txtCpfCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoaClicada = listaPessoa.get(position);
                Intent intent = new Intent(context, CadClienteTest.class);
                intent.putExtra("id_cliente", pessoaClicada.getId());
                context.startActivity(intent);
            }
        });
        
        
        imgDeletarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removerPessoa(pessoaClicada);
            }
        });

        Pessoa pessoa = listaPessoa.get(position);

        txtNomeCliente.setText(pessoa.getNome());
        txtCpfCliente.setText(pessoa.getCpf());


        return convertView;
    }

    private void removerPessoa(Pessoa pessoa) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        ApiPessoa apiPessoa = RetroFit.DELETAR_PESSOA();

        Call<Void> call = apiPessoa.DELETAR_PESSOA("Bearer " + token, pessoa.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {

                    listaPessoa.remove(pessoa);
                    notifyDataSetChanged();

                } else {

                    Toast.makeText(context, "Não foi possível excluir a pessoa.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

    }


}
