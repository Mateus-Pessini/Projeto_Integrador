package com.example.denticare.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.denticare.R;
import com.example.denticare.api.Api.ApiPessoa;
import com.example.denticare.api.Api.RetroFit;
import com.example.denticare.api.models.enums.TpPessoaEnum;
import com.example.denticare.api.models.pessoa.Pessoa;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFoto extends AppCompatActivity {

    private LinearLayout btConsultaRecep;
    private LinearLayout btAgendarRecep;
    private LinearLayout btPdfRecep;
    private LinearLayout btCadClienteRecep;
    private LinearLayout btSair;
    private LinearLayout btMeusDados;
    private Button btDdCancel;
    private Button btDdSalvar;
    private TextView txtNomeCliente;
    private String nomeCliente;
    private Pessoa pessoaCarregada;
    private Long idPessoa;
    private ImageView ivImgDentista;
    private boolean isEditando;

    @Override
    public void onResume() {
        super.onResume();
        carregaDados(idPessoa);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfoto);

        btMeusDados = findViewById(R.id.btMeusDados);
        btAgendarRecep = findViewById(R.id.btAgendarRecep);
        btPdfRecep = findViewById(R.id.btPdfRecep);
        btSair = findViewById(R.id.btSair);
        btCadClienteRecep = findViewById(R.id.btCadClienteRecep);
        btConsultaRecep = findViewById(R.id.btConsultaRecep);
        btDdCancel = findViewById(R.id.btDdCancel);
        btDdSalvar = findViewById(R.id.btDdSalvar);
        txtNomeCliente = findViewById(R.id.txtNomeCliente);
        ivImgDentista = findViewById(R.id.ivImgDentista);

        buscaTipoUsuario();


        // Chame o método para ocultar a barra de navegação
        NavigationUtil.hideNavigation(this);

        idPessoa = getIntent().getLongExtra("id_cliente", 0);

        if (idPessoa == 0) {
            isEditando = false;
            Log.d("TST", "Caiu no novo");

        } else {
            isEditando = true;
            Log.d("TST", "Caiu no editando");

            carregaDados(idPessoa);

        }

        btDdCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoto.this, Clientes.class);
                startActivity(intent);
                finish();
            }
        });
        btDdSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoto.this, Clientes.class);
                startActivity(intent);

                // Exibir uma mensagem de confirmação
                Toast.makeText(AddFoto.this, "Dados salvos com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

        btConsultaRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoto.this, Consulta2.class);
                startActivity(intent);
            }
        });
        btAgendarRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoto.this, Agenda.class);
                startActivity(intent);
            }
        });
        btPdfRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoto.this, GeraPDF.class);
                startActivity(intent);
            }
        });
        btCadClienteRecep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoto.this, Clientes.class);
                startActivity(intent);
            }
        });
        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoto.this, NewLogin.class);
                startActivity(intent);
            }
        });
        btMeusDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFoto.this, DadosDentista.class);
                startActivity(intent);
            }
        });
    }

    private void carregaDados(Long idPessoa) {

        SharedPreferences sharedPreferences = getSharedPreferences("MyToken", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        ApiPessoa apiPessoa = RetroFit.GET_PESSOA();

        Call<Pessoa> call = apiPessoa.GET_PESSOA("Bearer " + token, idPessoa);
        call.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if (response.isSuccessful()) {

                    pessoaCarregada = response.body();
                    Log.d("TST", "Achou a pessoa e retornou + " + pessoaCarregada.getNome());
                    txtNomeCliente.setText(pessoaCarregada.getNome());

                } else {

                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {

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