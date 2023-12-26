package com.example.denticare.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.denticare.Adapter.PessoaAdapter;
import com.example.denticare.R;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.ApiPreAgendamento;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.enums.TpPessoaEnum;
import com.example.denticare.api.models.pessoa.ConsultaList;
import com.example.denticare.api.models.pessoa.Pessoa;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Clientes extends AppCompatActivity {

    private LinearLayout btConsultaRecep;
    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadFotoRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;
    private LinearLayout btCadClienteRecep;
    private ImageView ivImgDentista;
    private TextView tvNomeDentista, tvNome;
    private Button btAddCliente, btBuscarCliente, btVoltar;
    private Pessoa listPessoaCliente;
    private EditText txtNomeCliente;
    private GridView gvClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        NavigationUtil.hideNavigation(this);

        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadFotoRecep = findViewById(R.id.btCadFotoRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        ivImgDentista = findViewById(R.id.ivImgDentista);
        btAddCliente = findViewById(R.id.btAddCliente);
        gvClientes = findViewById(R.id.gvClientes);
        txtNomeCliente = findViewById(R.id.txtNomeCliente);
        btBuscarCliente = findViewById(R.id.btBuscaCliente);
        tvNome = findViewById(R.id.tvNome);
        btVoltar = findViewById(R.id.btVoltar);


        buscaTipoUsuario();
        listaPessoas();

        btBuscarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parametro = txtNomeCliente.getText().toString();
                if (!parametro.equals("")) {
                    Log.e("TST", "parametro: " + parametro);

                    buscaPessoaByNome(parametro);
                } else {
                    listaPessoas();
                }
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btAddCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, CadClienteTest.class);
                startActivity(intent);
            }
        });
        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, Consulta2.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, GeraPDF.class);
                startActivity(intent);
            }
        });

        btCadFotoRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, SelClienteFoto.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, NewLogin.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, DadosDentista.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Clientes.this, Clientes.class);
                startActivity(intent);
            }
        });

    }

    private void listaPessoas() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        ApiPessoa apiPessoa = RetroFit.GET_PESSOA_CLIENTE();

        Call<List<Pessoa>> call = apiPessoa.GET_PESSOA_CLIENTE("Bearer " + token);
        call.enqueue(new Callback<List<Pessoa>>() {
            @Override
            public void onResponse(Call<List<Pessoa>> call, Response<List<Pessoa>> response) {
                if (response.isSuccessful()) {
                    List<Pessoa> listaPessoa = response.body();

                    PessoaAdapter pessoaAdapter = new PessoaAdapter(Clientes.this, listaPessoa);
                    gvClientes.setAdapter(pessoaAdapter);

                } else {
                    Log.e("", "Erro na resposta: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Pessoa>> call, Throwable t) {
                Log.e("", "Erro na requisição: " + t.getMessage());
            }
        });

    }

    private void buscaPessoaByNome(String nome) {

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        ApiPessoa apiPessoa = RetroFit.FIND_CLIENTES();

        Call<List<Pessoa>> call = apiPessoa.FIND_CLIENTES("Bearer " + token, nome);
        call.enqueue(new Callback<List<Pessoa>>() {
            @Override
            public void onResponse(Call<List<Pessoa>> call, Response<List<Pessoa>> response) {
                if (response.isSuccessful()) {
                    List<Pessoa> listaPessoa = response.body();

                    PessoaAdapter pessoaAdapter = new PessoaAdapter(Clientes.this, listaPessoa);
                    gvClientes.setAdapter(pessoaAdapter);
                    Log.e("TST", "Pessoas Filtro: " + listaPessoa.toString());


                } else {
                    Log.e("", "Erro na resposta: " + response.errorBody().toString());
                    Log.e("", "Erro na resposta cod: " + response.message() + response.code() + response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Pessoa>> call, Throwable t) {
                Log.e("", "Erro na requisição: " + t.getMessage());
            }
        });


    }

    private void buscaTipoUsuario() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        String role = "";
        if (!token.isEmpty()) {
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String[] tokenSplited = token.split("\\.");
            String header = new String(decoder.decode(tokenSplited[0]));
            String payload = new String(decoder.decode(tokenSplited[1]));
            String name;
            try {
                name = new JSONObject(payload).getString("Name");
                role = new JSONObject(payload).getString("Role");
            } catch (JSONException e) {
                name = "";
            }
            tvNome.setText(name);
            if (role.equals(TpPessoaEnum.DENTISTA.toString())) {
                btCadClienteRecep.setVisibility(View.GONE);
                btAgendarRecep.setVisibility(View.GONE);
                Log.d("TipoUsuario", "Usuário é um Dentista");
            } else if (role.equals(TpPessoaEnum.SECRETARIA.toString())) {
                btMeusDados.setVisibility(View.GONE);
                ivImgDentista.setVisibility(View.INVISIBLE);
                //tvNomeDentista.setVisibility(View.GONE);
                Log.d("TipoUsuario", "Usuário é uma Secretária");
            } else {

            }
        }
    }
}